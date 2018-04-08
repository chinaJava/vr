package io.renren.api.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TInvitationAnswerEntity;
import io.renren.entity.TInvitationEntity;
import io.renren.entity.TInvitationLikesEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TInvitationAnswerService;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TInvitationLikesService;
import io.renren.service.TInvitationService;
import io.renren.service.TokenService;
import io.renren.utils.Base64Util;
import io.renren.utils.Constant;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 发帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */

@RestController
@RequestMapping("/api/tinvitation")
@Api(value = "/api/tinvitation", description = "圈子文章相关接口")
public class TInvitationApiController {

	@Autowired
	private TInvitationService tInvitationService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TInvitationAnswerService tAnswerService;
	@Autowired
	private TInvitationLikesService tLikeService;
	
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "查询圈子文章列表，page页数、limit每页记录数 likesState 字段0 为未点赞 1 为点赞")
	public R list(@RequestHeader String token,@RequestBody Map<String, Object> params) {
		TokenEntity tokenEntity = tokenService.queryByToken(token);
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
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!tInvitationList.isEmpty()) {
			for (TInvitationEntity tInvitationEntity : tInvitationList) {
				paramMap.put("invitationId", tInvitationEntity.getInvitationId());
				paramMap.put("parentId", null);
				TInvitationAnswerEntity tAnswer = tAnswerService.queryOne(paramMap);
				tInvitationEntity.settAnswerEntities(tAnswer);
				paramMap.put("userId", tokenEntity.getUserId());
				tInvitationEntity.setLikesState(tLikeService.queryTotal(paramMap));
			}
		}
		
		int total = tInvitationService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tInvitationList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation(value = "保存圈子文章")
	public R save(@RequestHeader String token, @RequestBody@Valid TInvitationEntity tInvitation,BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return R.error("请检查输入的数据");
		}
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		String picPathData = tInvitation.getPicPathData();
		String[] pic_name = null;
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		String realPath = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		try {
			//上传图片
			if(picPathData!=null && !"".equals(picPathData)){
				String[] imgDatas = picPathData.split("\\$");
				StringBuffer sb = new StringBuffer();
				pic_name = new String[imgDatas.length];
				for(int i=0;i<imgDatas.length;i++){
					if(imgDatas[i]!=null && !"".equals(imgDatas[i])){
						String filename = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
						pic_name[i] = filename;
						boolean tag = Base64Util.GenerateImage(imgDatas[i].substring(imgDatas[i].indexOf(",")+1), realPath+File.separator+path, filename, 0);
						if(tag){
							sb.append(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename);
							if(i<imgDatas.length-1){
								sb.append(";");
							} 
						}
					}
				}
				tInvitation.setPicPath(sb.toString());
				tInvitation.setPicPathData(null);
			}
			tInvitation.setUserId(tokenEntity.getUserId());
			tInvitation.setSendTime(new Date());
			tInvitation.setState(Constant.State.effective);
			tInvitationService.save(tInvitation);
			return R.ok("保存成功");
		} catch (Exception e) {
			if(pic_name!=null && pic_name.length>0){
				for(int i=0;i<pic_name.length;i++){
					Base64Util.deleteFile(realPath+File.separator+path+File.separator+pic_name[i]);
				}
			}
			e.printStackTrace();
		}
		return R.error();
	}
	

	@PostMapping("/delete")
	@ApiOperation(value = "删除圈子文章,传入token、invitationId圈子文章id")
	public R delete(@RequestHeader String token,@RequestBody TInvitationEntity tInvitationEntity) {
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TInvitationEntity invitationObj = tInvitationService.queryObject(tInvitationEntity.getInvitationId());
		if(invitationObj.getUserId().equals(tokenEntity.getUserId())){
			tInvitationEntity.setState(Constant.State.Invalid);
			tInvitationService.update(tInvitationEntity);
		}
		return R.ok();
	}
	
	@GetMapping("/likes")
	@ApiOperation(value = "圈子文章点赞接口,传入token、invitationId圈子文章id")
	public R invitationLikes(@RequestHeader String token,@RequestParam Long invitationId) {
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TInvitationEntity invitationObj = tInvitationService.queryObject(invitationId);
		if(invitationObj!=null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", tokenEntity.getUserId());
			map.put("invitationId", invitationId);
			TInvitationLikesEntity likesObj = tLikeService.queryObjectByUserId(map);
			if(likesObj==null){
				invitationObj.setLikesNum(invitationObj.getLikesNum()+1);
				TInvitationLikesEntity invitationLikesObj =  new TInvitationLikesEntity();
				invitationLikesObj.setCreateTime(new Date());
				invitationLikesObj.setInvitationId(invitationId);
				invitationLikesObj.setUserId(tokenEntity.getUserId());
				invitationLikesObj.setState(Constant.State.effective);
				tInvitationService.updateLikes(invitationObj, invitationLikesObj,"add");
			}else{
				return R.error("已经对该文章点赞了");
			}
		}
		return R.ok();
	}
	
	@GetMapping("/unLikes")
	@ApiOperation(value = "圈子文章点赞接口,传入token、invitationId圈子文章id")
	public R invitationUnLikes(@RequestHeader String token,@RequestParam Long invitationId) {
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TInvitationEntity invitationObj = tInvitationService.queryObject(invitationId);
		if(invitationObj!=null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", tokenEntity.getUserId());
			map.put("invitationId", invitationId);
			TInvitationLikesEntity likesObj = tLikeService.queryObjectByUserId(map);
			if(likesObj!=null){
				if(invitationObj.getLikesNum()>=1){
					invitationObj.setLikesNum(invitationObj.getLikesNum()-1);
				}
				tInvitationService.updateLikes(invitationObj, likesObj,"del");
			}
		}
		return R.ok();
	}
	
}
