package io.renren.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TInvitationAnswerEntity;
import io.renren.entity.TInvitationLikesEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TInvitationAnswerService;
import io.renren.service.TInvitationLikesService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 点赞表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */

@RestController
@RequestMapping("/api/tlike")
@Api(value="/api/tlike",description="点赞")
public class TInvitationLikesApiController {
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TInvitationLikesService tLikeService;
	@Autowired
	private TInvitationAnswerService tAnswerService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value="tlike:list")
	public R list(@RequestHeader String token,@RequestBody Map<String, Object> params){
		TokenEntity tokenEntity=tokenService.queryByToken(token);
		
		params.put("userId", tokenEntity.getUserId());
		
		if(params.get("page")==null || "".equals(params.get("page"))){
			params.put("page", "1");
		}
		if(params.get("limit")==null || "".equals(params.get("limit"))){
			params.put("limit", "999");
		}
		params.put("sidx", "");
		params.put("order", "");
		
		//查询列表数据
		Query query = new Query(params);

		List<TInvitationLikesEntity> tLikeList = tLikeService.queryList(query);
		
		if(!tLikeList.isEmpty()){
			for (TInvitationLikesEntity tLikeEntity : tLikeList) {
				TInvitationAnswerEntity tAnswerEntity =  tAnswerService.queryObject(tLikeEntity.getInvitationId());
				tLikeEntity.settAnswerEntitie(tAnswerEntity);
			}
		}
			
		int total = tLikeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tLikeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation(value="tlike:save")
	public R save(@RequestHeader String token,@RequestBody TInvitationLikesEntity tLike){
		
		Map<String, Object> map = new HashMap<>();
		TokenEntity tokenEntity=tokenService.queryByToken(token);
		map.put("invitationId",tLike.getInvitationId());
		map.put("userId",tokenEntity.getUserId());
		TInvitationLikesEntity result =  tLikeService.get(map);
		if(result!=null){
			if(result.getState()==Constant.State.effective){
				result.setState(Constant.State.Invalid);
			}else{
				result.setState(Constant.State.effective);
			}
			tLikeService.update(result);
		}else{
			tLike.setUserId( tokenEntity.getUserId());
			tLike.setState(Constant.State.effective);
			tLike.setCreateTime(new Date());
			tLikeService.save(tLike);
		}
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value="tlike:delete")
	public R delete(@RequestHeader String token,@RequestBody TInvitationLikesEntity tLikeEntity){

		tLikeEntity.setState(Constant.State.Invalid);
		tLikeService.update(tLikeEntity);
		return R.ok();
	}
	
}
