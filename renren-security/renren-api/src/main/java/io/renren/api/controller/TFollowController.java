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

import io.renren.entity.TFollowEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TFollowService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 关注表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
@RestController
@RequestMapping("/api/tfollow")
@Api(value = "/api/tfollow", description = "关注")
public class TFollowController {
	@Autowired
	private TFollowService tFollowService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private TAppuserDetailService tAppuserDetailService;

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "tfollow::list")
	public R list(@RequestHeader String token, @RequestBody Map<String, Object> params) {
		
		TokenEntity tokenEntity = tokenService.queryByToken(token);

		params.put("userId", tokenEntity.getUserId());

		if (params.get("page") == null || "".equals(params.get("page"))) {
			params.put("page", "1");
		}
		if (params.get("limit") == null || "".equals(params.get("limit"))) {
			params.put("limit", "999");
		}
		params.put("sidx", "");
		params.put("order", "");
		// 查询列表数据
		Query query = new Query(params);

		List<TFollowEntity> tFollowList = tFollowService.queryList(query);

		if (!tFollowList.isEmpty()) {
			for (TFollowEntity tFollowEntity : tFollowList) {
				tFollowEntity.settAppuserDetailEntity(
						tAppuserDetailService.queryObjectByAppuserId(tFollowEntity.getFollowUserId()));
			}
		}

		int total = tFollowService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tFollowList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation(value = "tfollow:save")
	public R save(@RequestHeader String token, @RequestBody TFollowEntity tFollow) {

		Map<String, Object> map = new HashMap<>();
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		map.put("followUserId", tFollow.getFollowUserId()); 
		map.put("userId", tokenEntity.getUserId());
		TFollowEntity tFollowEntity =  tFollowService.get(map);
		if(tFollowEntity!=null){
			if(tFollowEntity.getState()==Constant.State.effective){
				tFollowEntity.setState(Constant.State.Invalid);
			}else{
				tFollowEntity.setState(Constant.State.effective);
			}
			
			tFollowService.update(tFollowEntity);
		}else{
			tFollow.setUserId(tokenEntity.getUserId());
			tFollow.setState(Constant.State.effective);
			tFollow.setCreateTime(new Date());
			tFollowService.save(tFollow);
		}
		return R.ok();
	}

	/**
	 * 删除
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value = "tfollow:delete")
	public R delete(@RequestHeader String token,@RequestBody TFollowEntity tfFollowEntity) {

		tfFollowEntity.setState(Constant.State.Invalid);
		tFollowService.update(tfFollowEntity);

		return R.ok();
	}

}
