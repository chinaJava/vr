package io.renren.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TAppuserGiftsEntity;
import io.renren.entity.TMemberGiftsEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TAppuserGiftsService;
import io.renren.service.TMemberGiftsService;
import io.renren.service.TokenService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/tappusergifts")
@Api(value="/api/tappusergifts",description="会员礼包领取")
public class TAppuserGiftsApiController {
	@Autowired
	private TAppuserGiftsService tAppuserGiftsService;
	@Autowired
	private TMemberGiftsService tMemberGiftsService;
	@Autowired
	private TAppuserDetailService tAppuserDetailService;
	@Autowired
	private TokenService tokenService;
	/**
	 * 列表
	 */
//	@IgnoreAuth
	@PostMapping("info")
	@ApiOperation(value = "查询礼包信息;page:查询第几页(默认为1);limit:查询的条数(默认为3);sidx:按某个字段排序(默认为会员等级MEMBER_LEVEL_ID);order:升序或降序(默认desc)")
	public R info(@RequestHeader("token")String token,@RequestBody Map<String,Object> map){
		//从header中获取token
		//TokenEntity tokenEntity = tokenService.queryByToken(token);
		if(map.get("page")==null || "".equals(map.get("page"))){
			map.put("page", "1");
		}
		if(map.get("limit")==null || "".equals(map.get("limit"))){
			map.put("limit", "3");
		}
		if(map.get("sidx")==null || "".equals(map.get("sidx"))){
			map.put("sidx", "MEMBER_LEVEL_ID");
		}
		if(map.get("order")==null || "".equals(map.get("order"))){
			map.put("order", "desc");
		}
		map.put("status", "1");
		Query query = new Query(map);

		List<TMemberGiftsEntity> tMemberGiftsList = tMemberGiftsService.queryList(query);
		int total = tMemberGiftsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tMemberGiftsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("gifts", pageUtil);
	}
	

	@PostMapping("receive")
	@ApiOperation(value = "会员领取礼包,传入用户登录的token和giftsId(礼包的ID)")
	@Transactional
	public R receive(@RequestHeader("token") String token,@RequestBody Map<String,Object> map){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TAppuserDetailEntity tAppuserDetailEntity  =  tAppuserDetailService.queryObjectByAppuserId(tokenEntity.getUserId());
		int userMemberLevel = tAppuserDetailEntity.getMemberLevelId();
		int giftsId = (int) map.get("giftsId");
		TMemberGiftsEntity tMemberGiftsEntity = tMemberGiftsService.queryObject(giftsId);
		int giftMemberLevel = tMemberGiftsEntity.getMemberLevelId();
		int giftAmount = tMemberGiftsEntity.getAmount();
		boolean flag = false;
		String msg = "";
		if(giftAmount<=0){
			msg = "礼包已被领完";
		}else{
			//判断会员等级够不够,目前根据数据库里面数据用会员等级表的ID来区别等级的高低
			if(userMemberLevel<giftMemberLevel){
				msg = "会员等级不够";
			}else{
				//判断有没有领取礼包
				TAppuserGiftsEntity tAppuserGiftsEntity = tAppuserGiftsService.queryObjectByAppuserId(tokenEntity.getUserId(),giftsId);
				if(tAppuserGiftsEntity!=null){
					msg = "您已经领取过该礼包，不能重复领取";
				}else{
					TAppuserGiftsEntity tAppuserGiftsEntitys = new TAppuserGiftsEntity();
					tAppuserGiftsEntitys.setGiftsId(Integer.valueOf(giftsId));
					tAppuserGiftsEntitys.setAppuserId(tokenEntity.getUserId());
					tAppuserGiftsEntitys.setCreatetime(new Date());
					tAppuserGiftsService.save(tAppuserGiftsEntitys);
					tMemberGiftsEntity.setAmount(giftAmount-1);
					tMemberGiftsService.update(tMemberGiftsEntity);
					msg = "礼包领取成功";
					flag = true;
				}
			}
		}
		if(flag){
			return R.ok(msg);
		}else{
			return R.error(msg);
		}
	}
}
