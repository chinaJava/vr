package io.renren.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TInvitationAnswerEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserFavoritesService;
import io.renren.service.TFollowService;
import io.renren.service.TInvitationAnswerService;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 回帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */

@RestController
@RequestMapping("/api/tanswer")
@Api(value = "/api/tanswer", description = "回复")
public class TInvitationAnswerApiController {
	@Autowired
	private TInvitationAnswerService tAnswerService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TAppuserDetailService tAppuserDetailService;

	/**
	 * 列表
	 */
	@IgnoreAuth
	@PostMapping("/list")
	@ApiOperation(value = "tanswer:list")
	public R list(@RequestBody Map<String, Object> params) {

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
				TInvitationAnswerEntity tInvitationAnswerEntity = tAnswerService.queryObject(tAnswerEntity.getParentId());
				if(tInvitationAnswerEntity!=null){
					TAppuserDetailEntity tAppuserDetailEntity = tAppuserDetailService.queryObjectByAppuserId(tInvitationAnswerEntity.getUserId());
					if(tAppuserDetailEntity!=null){
						tAnswerEntity.setCoverAnswerUserName(tAppuserDetailEntity.getNikename());
					}
				}
				if(tAnswerEntity.getParentId()==null){
					tAnswerEntity.setAnswerNum(tAnswerService.queryTotalByChild(tAnswerEntity.getAnswerId()));
				}
			}
		}
		
		int total = tAnswerService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tAnswerList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation(value = "tanswer:save  invitationId 文章ID 必传 ,如回复某评论 取某评论answerId 作为parentId传入 ")
	public R save(@RequestHeader("token") String token, @RequestBody@Valid TInvitationAnswerEntity tAnswer,BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return R.error("请检查输入数据");
		}
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		tAnswer.setUserId(tokenEntity.getUserId());
		tAnswer.setAnswerTime(new Date());
		tAnswer.setState(Constant.State.effective);
		tAnswerService.save(tAnswer);

		return R.ok("保存成功");

	}

	/**
	 * 删除
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value = "tanswer:delete")
	public R delete(@RequestHeader String token,@RequestBody TInvitationAnswerEntity tAnswerEntity) {

		tAnswerEntity.setState(Constant.State.Invalid);
		tAnswerService.update(tAnswerEntity);

		return R.ok();
	}

}
