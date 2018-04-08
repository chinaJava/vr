package io.renren.app.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.renren.entity.TVideoInfoEntity;
import io.renren.service.TVideoInfoService;
import io.renren.utils.Base64Util;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-02-11 15:00:35
 */
@RestController
@RequestMapping("tvideoinfo")
public class TVideoInfoController {
	@Autowired
	private TVideoInfoService tVideoInfoService;
	
	//音频上传的文件夹名称
	private static String FILE_SAVE_PTH = "video";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("EBOOK_UPLOAD_PATH");
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tvideoinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TVideoInfoEntity> tVideoInfoList = tVideoInfoService.queryList(query);
		int total = tVideoInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tVideoInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tvideoinfo:info")
	public R info(@PathVariable("id") Integer id){
		TVideoInfoEntity tVideoInfo = tVideoInfoService.queryObject(id);
		
		return R.ok().put("tVideoInfo", tVideoInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tvideoinfo:save")
	public R save(@RequestBody TVideoInfoEntity tVideoInfo){
		//视频存放地址
		String videoFileUrl = tVideoInfo.getVideoUrl();
		if(StringUtils.isNotEmpty(videoFileUrl)){
			tVideoInfo.setVideoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+videoFileUrl);
		}
		tVideoInfo.setCreatetime(new Date());
		tVideoInfo.setCreator(ShiroUtils.getUserEntity().getUsername());
		tVideoInfoService.save(tVideoInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tvideoinfo:update")
	public R update(@RequestBody TVideoInfoEntity tVideoInfo){
		String videoFileUrl = tVideoInfo.getVideoUrl();
		if(StringUtils.isNotEmpty(videoFileUrl) && videoFileUrl.indexOf(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL"))<0){
			tVideoInfo.setVideoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+videoFileUrl);
		}
		tVideoInfoService.update(tVideoInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tvideoinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		tVideoInfoService.deleteBatch(ids);
		return R.ok();
	}
	
	@RequestMapping("/uploadVideoFile")
	public R uploadVideoFile(HttpServletRequest request,HttpServletResponse response){
		String filePath = null;
		MultipartFile multipartFile = null;
		try {
			request.setCharacterEncoding("UTF-8");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			/** 页面控件的文件流* */
	        Map map =multipartRequest.getFileMap();
	        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
	           Object obj = i.next();
	           multipartFile=(MultipartFile) map.get(obj);
	        }
	        String filename = multipartFile.getOriginalFilename();
			if(!multipartFile.isEmpty()){
				Date nowDate = new Date();
				String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
				//String realPath = request.getSession().getServletContext().getRealPath("/");
				//String realPath = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
				String path = FILE_SAVE_PTH+File.separator+nowDay;
				Base64Util.uploadFile(multipartFile, REALPATH+File.separator+path);
				filePath = path+File.separator+filename;
				if(filePath!=null && !"".equals(filePath)){
					String audioPath = REALPATH+File.separator+path+File.separator+ filename;
					File file =  new File(audioPath);
					Encoder encoder = new Encoder();
					MultimediaInfo m = encoder.getInfo(file);
					//System.out.println("此视频时长为:" + m.getDuration() / 60000 + "分" + m.getDuration() / 1000 + "秒！");
					return R.ok().put("path", filePath).put("size", m.getDuration());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error("文件上传失败");
	}
	
}
