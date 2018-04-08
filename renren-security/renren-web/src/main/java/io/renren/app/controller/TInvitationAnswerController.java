package io.renren.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TInvitationAnswerEntity;
import io.renren.entity.TInvitationEntity;
import io.renren.service.TInvitationAnswerService;
import io.renren.service.TInvitationService;
import io.renren.service.TAppuserDetailService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.ApiOperation;

/**
 * 回帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */

@RestController
@RequestMapping("tanswer")
public class TInvitationAnswerController {
	@Autowired
	private TInvitationAnswerService tAnswerService;
	@Autowired
	private TAppuserDetailService tAppuserDetailService;
	@Autowired
	private TInvitationService tInvitationService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tanswer:list")
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

		List<TInvitationAnswerEntity> tAnswerList = tAnswerService.queryList(query);
		if (!tAnswerList.isEmpty()) {
			for (TInvitationAnswerEntity tAnswerEntity : tAnswerList) {
				TInvitationAnswerEntity tInvitationAnswerEntity = tAnswerService
						.queryObject(tAnswerEntity.getParentId());
				if (tInvitationAnswerEntity != null) {
					TAppuserDetailEntity tAppuserDetailEntity = tAppuserDetailService
							.queryObjectByAppuserId(tInvitationAnswerEntity.getUserId());
					if (tAppuserDetailEntity != null) {
						tAnswerEntity.setCoverAnswerUserName(tAppuserDetailEntity.getNikename());
					}
				}
				if (tAnswerEntity.getParentId() == null) {
					tAnswerEntity.setAnswerNum(tAnswerService.queryTotalByChild(tAnswerEntity.getAnswerId()));
				}
				TInvitationEntity tInvitationEntity = tInvitationService
						.queryObject(tAnswerEntity.getInvitationId());
				if (tInvitationEntity != null) {
					tAnswerEntity.setInvitationContent(tInvitationEntity.getContent());
				}

			}
		}

		int total = tAnswerService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tAnswerList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tanswer:delete")
	public R delete(@RequestBody Long[] answerIds) {
		Map<String, Object> param = new HashMap<>();
		param.put("answerIds", answerIds);
		param.put("state", 0);
		tAnswerService.updateBatch(param);
		return R.ok();
	}

}
