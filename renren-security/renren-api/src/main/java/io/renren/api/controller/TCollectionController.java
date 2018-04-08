package io.renren.api.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TCollectionEntity;
import io.renren.entity.TInvitationEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TCollectionService;
import io.renren.service.TInvitationService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 收藏表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
@RestController
@RequestMapping("/api/tcolection")
@Api(value="/api/tcolection",description="收藏")
public class TCollectionController {
	@Autowired
	private TCollectionService tCollectionService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TInvitationService tInvitationService;
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value="查看列表数据")
	public R list(@RequestHeader String token,@RequestParam Map<String, Object> params){
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

		List<TCollectionEntity> tCollectionList = tCollectionService.queryList(query);
		if(!tCollectionList.isEmpty()){
			for(TCollectionEntity tCollectionEntity : tCollectionList ){
				TInvitationEntity tInvitationEntity = tInvitationService.queryObject(tCollectionEntity.getInvitationId());
				tCollectionEntity.settInvitationEntitie(tInvitationEntity);
			}
		}
		int total = tCollectionService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tCollectionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{collectionId}")
	@RequiresPermissions("tcollection:info")
	public R info(@PathVariable("collectionId") Long collectionId){
		TCollectionEntity tCollection = tCollectionService.queryObject(collectionId);
		
		return R.ok().put("tCollection", tCollection);
	}
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation(value="保存收藏")
	public R save(@RequestHeader String token,@RequestBody TCollectionEntity tCollection){
		Map<String, Object> map = new HashMap<>();
		TokenEntity tokenEntity=tokenService.queryByToken(token);
		map.put("invitationId", tCollection.getInvitationId());
		map.put("userId",tokenEntity.getUserId());
		TCollectionEntity result = tCollectionService.get(map);
		if(result!=null){
			TInvitationEntity tInvitationEntity = tInvitationService.queryByUser(tokenEntity.getUserId());
			tCollection = tCollectionService.queryByInvitation(tInvitationEntity.getInvitationId());
			tCollection.setInvitationId(tInvitationEntity.getInvitationId());
			tCollection.setUserId(tokenEntity.getUserId());
			tCollection.setState(Constant.State.effective);
			tCollection.setCreateTime(new Date());
			tCollectionService.save(tCollection);
		}
		
		return R.ok();
	}
	
	
	@DeleteMapping("/delete")
	@ApiOperation(value="删除收藏")
	public R delete(@RequestHeader String token,@RequestBody TCollectionEntity tCollectionEntity){

		tCollectionEntity.setState(Constant.State.Invalid);
		tCollectionService.update(tCollectionEntity);
		
		return R.ok();
	}
}
