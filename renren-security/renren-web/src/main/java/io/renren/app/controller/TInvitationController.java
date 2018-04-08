package io.renren.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TInvitationEntity;
import io.renren.service.TInvitationService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;

/**
 * 发帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */

@RestController
@RequestMapping("tinvitation")
public class TInvitationController {

	@Autowired
	private TInvitationService tInvitationService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tinvitation:list")
	public R list(@RequestParam Map<String, Object> params) {
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
		List<TInvitationEntity> tInvitationList = tInvitationService.queryList(query);

		int total = tInvitationService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tInvitationList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	@RequestMapping("/delete")
	@RequiresPermissions("tinvitation:delete")
	public R delete(@RequestBody Long[] invitationIds) {
		Map<String, Object> param = new HashMap<>();
		param.put("invitationIds", invitationIds);
		param.put("state", 0);
		tInvitationService.updateBatch(param);
		return R.ok();
	}

}
