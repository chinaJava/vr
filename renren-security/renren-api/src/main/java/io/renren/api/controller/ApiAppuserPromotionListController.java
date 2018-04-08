package io.renren.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TAppuserEntity;
import io.renren.entity.TAppuserPromotionListEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TAppuserPromotionListService;
import io.renren.service.TAppuserService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
 * 类名称: TAppuserPromotionListController 会员推广名单接口
 * 描述: TODO
 * @create 2017年11月16日 下午2:58:34 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api/promotion")
@Api(value="/api/promotion",description="会员推广相关操作接口")
public class ApiAppuserPromotionListController {
	@Autowired
	private TAppuserPromotionListService tAppuserPromotionListService;
	@Autowired
    private TokenService tokenService;
	@Autowired
    private TAppuserService userService;
	@Autowired
	private TAppuserDetailService userDetailService;
	
	
	@PostMapping("/list")
	@ApiOperation(value="查询会员所有的推广名单列表")
	public R getPromotionList(@RequestHeader("token") String token){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		List<TAppuserPromotionListEntity> promotionList = tAppuserPromotionListService.queryListByUserId(tokenEntity.getUserId());
		return R.ok().put("promotionList", promotionList);
	}
	
	@PostMapping("/modify")
	@ApiOperation(value="推广名单和有效推广的修改;传入id(推广记录ID)和状态（status=0推广名单1有效推广）")
	public R modifyPromotionInfo(@RequestHeader("token") String token,@RequestBody Map<String,String> map){
		String id = map.get("id");
		String status = map.get("status");
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		String msg = "";
		boolean flag = false;
		TAppuserPromotionListEntity promotionInfo = tAppuserPromotionListService.queryObject(id);
		if(promotionInfo!=null){
			if(promotionInfo.getAppuserId().equals(tokenEntity.getUserId())){
				//修改为有效推广时需要判断，会员级别对应的有效推广数是否超了
				if("1".equals(status)){
					int valid = tAppuserPromotionListService.queryTotalValid();
					int memberValid = tAppuserPromotionListService.queryMemberValid(tokenEntity.getUserId());
					//会员级别的有效推广数大于目前的有效推广数时，可以继续增加有效推广数，否则添加失败
					if(memberValid>valid){
						promotionInfo.setStatus(status);
						flag = true;
					}else{
						msg = "有效推广数已满，请升级会员等级。";
					}
				}else{
					promotionInfo.setStatus(status);
					flag = true;
				}
			}else{
				msg = "数据有误无法修改。";
			}
		}else{
			msg="推广名单不存在无法修改。";
		}
		if(flag){
			tAppuserPromotionListService.update(promotionInfo);
			return R.ok();
		}else{
			return R.error(msg);
		}
	}
	
	@PostMapping("/add")
	@ApiOperation(value="增加推广名单,传入推荐人的mobile手机号")
	public R addPromotionInfo(@RequestHeader("token") String token,@RequestBody Map<String,String> map){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		String mobile = map.get("mobile");
		TAppuserEntity userObj = userService.queryObjectByMobile(mobile);
		if(userObj!=null){
			TAppuserPromotionListEntity promotionListObj = tAppuserPromotionListService.queryObjectByPromotionUserId(tokenEntity.getUserId());
			//如果当前用户已经存在被推荐的用户，则不能再增加
			if(promotionListObj==null){
				TAppuserPromotionListEntity promotionObj = new TAppuserPromotionListEntity();
				promotionObj.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				promotionObj.setPromotionUserid(tokenEntity.getUserId());
				promotionObj.setStatus(Constant.Status.DISABLE.getValue());
				promotionObj.setAppuserId(userObj.getId());
				promotionObj.setGainPoints(0);
				tAppuserPromotionListService.save(promotionObj);
				return R.ok();
			}else{
				return R.error("您已经提交过推荐人信息了，不能重复提交");
			}
		}else{
			return R.error("用户信息不存在");
		}
	}
	
	
	@IgnoreAuth
	@PostMapping("/gainPoint")
	@Transactional
	@ApiOperation(value="计算推荐积分,传入用户ID(userId)和充值数(rechargeNum),供外部使用接口")
	public R gainPoint(@RequestBody Map<String,Object> map){
		String userId = (String) map.get("userId");
		int rechargeNum = (int) map.get("rechargeNum");
		if(rechargeNum>=10){
			TAppuserPromotionListEntity promotionListObj = tAppuserPromotionListService.queryObjectByPromotionUserId(userId);
			if(promotionListObj!=null){
				Integer point = promotionListObj.getGainPoints();
				Integer point_gain = (int) (rechargeNum*0.3);
				TAppuserDetailEntity userDetailObj = userDetailService.queryObjectByAppuserId(promotionListObj.getAppuserId());
				promotionListObj.setGainPoints(point+point_gain);
				tAppuserPromotionListService.update(promotionListObj);
				userDetailObj.setPoint(userDetailObj.getPoint()+point_gain);
				userDetailObj.setPointTotal(userDetailObj.getPointTotal()+point_gain);
				userDetailService.update(userDetailObj);
			}
		}
		return R.ok();
	}
	
	@IgnoreAuth
	@PostMapping("/addList")
	@ApiOperation(value="增加推广名单,传入推荐人id（userId）和被推荐人id(promotionUserId),供外部使用接口")
	public R addPromotionUser(@RequestBody Map<String,String> map){
		//推荐人id
		String userId = map.get("userId");
		TAppuserEntity userObj = userService.queryObject(userId);
		//被推荐人id
		String promotionUserId = map.get("promotionUserId");
		TAppuserPromotionListEntity promotionListObj = tAppuserPromotionListService.queryObjectByPromotionUserId(promotionUserId);
		if(userObj!=null){
			//如果当前用户已经存在被推荐的用户，则不能再增加
			if(promotionListObj==null){
				TAppuserPromotionListEntity promotionObj = new TAppuserPromotionListEntity();
				promotionObj.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				promotionObj.setPromotionUserid(promotionUserId);
				promotionObj.setStatus(Constant.Status.DISABLE.getValue());
				promotionObj.setAppuserId(userId);
				promotionObj.setGainPoints(0);
				tAppuserPromotionListService.save(promotionObj);
				return R.ok();
			}else{
				return R.error("您已经提交过推荐人信息了，不能重复提交");
			}
		}else{
			return R.error("用户信息不存在");
		}
	}
	
}
