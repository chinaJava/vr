package io.renren.app.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TGameTaskEntity;
import io.renren.service.TGameTaskService;
import io.renren.utils.Base64Util;
import io.renren.utils.Constant;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-06 17:17:34
 */
@RestController
@RequestMapping("tgametask")
public class TGameTaskController {
	@Autowired
	private TGameTaskService tGameTaskService;
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgametask:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGameTaskEntity> tGameTaskList = tGameTaskService.queryList(query);
		int total = tGameTaskService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGameTaskList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgametask:info")
	public R info(@PathVariable("id") Long id){
		TGameTaskEntity tGameTask = tGameTaskService.queryObject(id);
		
		return R.ok().put("tGameTask", tGameTask);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tgametask:save")
	public R save(HttpServletRequest request,@RequestBody TGameTaskEntity tGameTask){
		//任务logo图片流
		String logoImgData = tGameTask.getLogoImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		//String url = request.getScheme()+"\\://"+request.getServerName()+"\\:"+request.getServerPort();
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		String logoPic_name = "";
		try{
			//上传任务logo图片
			if(logoImgData!=null && !"".equals(logoImgData)){
				logoImgData = logoImgData.substring(logoImgData.indexOf(",")+1);
				logoPic_name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
				boolean flag = Base64Util.GenerateImage(logoImgData, REALPATH+File.separator+path, logoPic_name, 0);
				if(flag){
					tGameTask.setLogoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+logoPic_name);
				}
				tGameTask.setLogoImgData(null);
			}
			tGameTask.setStatus(Constant.TaskStatus.DISABLE.getValue());
			tGameTask.setCreateTime(new Date());
			tGameTask.setCreator(ShiroUtils.getUserEntity().getUsername());
			tGameTaskService.save(tGameTask);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+logoPic_name);
			e.printStackTrace();
		}
		return R.error("保存失败");
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tgametask:update")
	public R update(HttpServletRequest request,@RequestBody TGameTaskEntity tGameTask){
		
		TGameTaskEntity tGameTask_old = tGameTaskService.queryObject(tGameTask.getId());
		//任务logo图片流
		String logoImgData = tGameTask.getLogoImgData();
		String logoImgUrl_old = tGameTask_old.getLogoUrl();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		//上传任务logo图片
		if(logoImgData!=null && !"".equals(logoImgData)){
			logoImgData = logoImgData.substring(logoImgData.indexOf(",")+1);
			String name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
			boolean flag = Base64Util.GenerateImage(logoImgData, REALPATH+File.separator+path, name, 0);
			if(flag){
				tGameTask.setLogoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+name);
				if(logoImgUrl_old!=null && logoImgUrl_old.indexOf(PICTURE_SAVE_PTH)>0){
					//删除之前的头像图片
					Base64Util.deleteFile(REALPATH+File.separator+logoImgUrl_old.substring(logoImgUrl_old.indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tGameTask.setLogoImgData(null);
		}
		tGameTaskService.update(tGameTask);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgametask:delete")
	public R delete(@RequestBody Long[] ids){
		tGameTaskService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
