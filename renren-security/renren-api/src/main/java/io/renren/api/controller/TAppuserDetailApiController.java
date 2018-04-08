package io.renren.api.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TMemberInfoEntity;
import io.renren.entity.TokenEntity;
import io.renren.oss.OSSFactory;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TMemberInfoService;
import io.renren.service.TokenService;
import io.renren.utils.Base64Util;
import io.renren.utils.DateUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.R;
import io.renren.utils.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/tappuserdetail")
@Api(value="/api/tappuserdetail",description="app用户详情")
public class TAppuserDetailApiController {
	@Autowired
	private TAppuserDetailService tAppuserDetailService;
	@Autowired
    private TokenService tokenService;
	@Autowired
	private TMemberInfoService tMemberInfoService;
	
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	
	/** 
	 * 方法名称: info 根据token查询用户详情
	 * 描述: TODO
	 * @param token
	 * @return
	 * @create 2017年8月9日 上午11:06:40 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@PostMapping("info")
	@ApiOperation(value = "根据登录的token查询用户详细信息，请求头加上token信息")
	public R info(@RequestHeader("token") String token){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TAppuserDetailEntity tAppuserDetail = tAppuserDetailService.queryObjectByAppuserId(tokenEntity.getUserId());
		return R.ok().put("tAppuserDetail", tAppuserDetail);
	}
	
	
	/** 
	 * 方法名称: update 修改用户详细信息
	 * 描述: TODO
	 * @param tAppuserDetail
	 * @return
	 * @create 2017年8月9日 上午11:42:58 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@PostMapping("update")
	@ApiOperation(value = "修改用户详细信息,请求头加上token信息")
	public R update(HttpServletRequest request,@RequestHeader("token") String token,@RequestBody TAppuserDetailEntity tAppuserDetail){
		//从header中获取token
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        TAppuserDetailEntity tAppuserDetailObj = tAppuserDetailService.queryObjectByAppuserId(tokenEntity.getUserId());
        tAppuserDetail.setId(tAppuserDetailObj.getId());
        tAppuserDetail.setAppuserid(tAppuserDetailObj.getAppuserid());
        
        Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		String logoPic_name = "";
		String picData = tAppuserDetail.getHeadPic();
		String birthdayStr = tAppuserDetail.getBirthdayStr();
		if(birthdayStr!=null&&!"".equals(birthdayStr)){
			tAppuserDetail.setBirthday(DateUtils.getDateByStr(birthdayStr, "yyyy-MM-dd"));
		}
        try{
			//上传图片
			if(picData!=null && !"".equals(picData)){
				picData = picData.substring(picData.indexOf(",")+1);
				logoPic_name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
				boolean flag = Base64Util.GenerateImage(picData, REALPATH+File.separator+path, logoPic_name, 0);
				if(flag){
					tAppuserDetail.setHeadPic(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+logoPic_name);
				}
			}
			tAppuserDetailService.update(tAppuserDetail);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+logoPic_name);
			e.printStackTrace();
		}
        return R.error("保存失败");
	}
	
	
	
	/** 
	 * 方法名称: uploadHeadPic 修改用户头像
	 * 描述: TODO
	 * @param request
	 * @param file
	 * @return
	 * @create 2017年8月9日 上午11:53:18 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@PostMapping("upload")
	@ApiOperation(value = "修改用户头像，请求头加上token")
	public R uploadHeadPic(@RequestHeader("token") String token, @RequestParam("file") MultipartFile file) {
		
		try{
			if (file.isEmpty()) {
				throw new RRException("上传文件不能为空");
			}
			//上传文件
			String url = OSSFactory.build().upload(file.getBytes());
			TokenEntity tokenEntity = tokenService.queryByToken(token);
			TAppuserDetailEntity tAppuserDetail = tAppuserDetailService.queryObjectByAppuserId(tokenEntity.getUserId());
			tAppuserDetail.setHeadPic(url);
			tAppuserDetailService.update(tAppuserDetail);
			return R.ok().put("url", url);
		}catch(IOException e){
			e.printStackTrace();
		}
		return R.error("头像上传失败");
	}
	
	
	/** 
	 * 方法名称: getMemberList  查询系统中的会员等级信息
	 * 描述: TODO
	 * @return
	 * @create 2017年11月16日 下午2:24:45 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@IgnoreAuth
	@GetMapping("memberList")
	@ApiOperation(value = "查询会员等级列表,获取各会员级别对应的推广人数和季度费用")
	public R getMemberList(){
		//从header中获取token
		List<TMemberInfoEntity> memberList = tMemberInfoService.queryAllList();
		return R.ok().put("memberList", memberList);
	}
	
	
}
