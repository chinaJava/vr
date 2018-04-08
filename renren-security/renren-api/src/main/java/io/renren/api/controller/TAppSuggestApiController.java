package io.renren.api.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppSuggestEntity;
import io.renren.service.TAppSuggestService;
import io.renren.utils.Base64Util;
import io.renren.utils.DateUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-09 14:18:56
 */
@RestController
@RequestMapping("/api/tappsuggest")
@Api(value="/api/tappsuggest",description="app用户问题反馈")
public class TAppSuggestApiController {
	
	@Autowired
	private TAppSuggestService tAppSuggestService;
	
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	
	/**
	 * 保存
	 */
	@IgnoreAuth
	@PostMapping("/add")
	@ApiOperation(value = "问题反馈")
	public R save(HttpServletRequest request,@RequestBody TAppSuggestEntity tAppSuggest){
		
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		String logoPic_name = "";
		String picData = tAppSuggest.getPicData();
		if("".equals(tAppSuggest.getSuggest())||"".equals(tAppSuggest.getContact())||tAppSuggest.getContact()==null||tAppSuggest.getSuggest()==null){
			return R.error("内容或联系方式不能为空");
		}
		try{
			
			//上传图片
			if(picData!=null && !"".equals(picData)){
				picData = picData.substring(picData.indexOf(",")+1);
				logoPic_name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
				boolean flag = Base64Util.GenerateImage(picData, REALPATH+File.separator+path, logoPic_name, 0);
				if(flag){
					tAppSuggest.setPicUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+logoPic_name);
				}
				tAppSuggest.setPicData(null);
			}
			tAppSuggestService.save(tAppSuggest);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+logoPic_name);
			e.printStackTrace();
		}
		return R.error("保存失败");
	}
	
}
