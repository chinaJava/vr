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

import io.renren.entity.TShareInfoEntity;
import io.renren.service.TShareInfoService;
import io.renren.utils.Base64Util;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-12-19 09:37:43
 */
@RestController
@RequestMapping("tshareinfo")
public class TShareInfoController {
	@Autowired
	private TShareInfoService tShareInfoService;
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tshareinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TShareInfoEntity> tShareInfoList = tShareInfoService.queryList(query);
		int total = tShareInfoService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tShareInfoList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tshareinfo:info")
	public R info(@PathVariable("id") Integer id){
		TShareInfoEntity tShareInfo = tShareInfoService.queryObject(id);
		return R.ok().put("tShareInfo", tShareInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tshareinfo:save")
	public R save(HttpServletRequest request,@RequestBody TShareInfoEntity tShareInfo){
		//图片流
		String imgData = tShareInfo.getImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		String[] storePic_name = null;
		try{
			//上传图片
			if(imgData!=null && !"".equals(imgData)){
				String[] imgDatas = imgData.split("\\$");
				StringBuffer sb = new StringBuffer();
				storePic_name = new String[imgDatas.length];
				for(int i=0;i<imgDatas.length;i++){
					if(imgDatas[i]!=null && !"".equals(imgDatas[i])){
						String filename = DateUtils.getFormatedDateString(new Date())+"i"+PICTURE_SUFFIX;
						storePic_name[i] = filename;
						boolean tag = Base64Util.GenerateImage(imgDatas[i].substring(imgDatas[i].indexOf(",")+1), REALPATH+File.separator+path, filename, 0);
						if(tag){
							sb.append(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename);
							if(i<imgDatas.length-1){
								sb.append(";");
							}
						}
					}
				}
				tShareInfo.setPic(sb.toString().replaceAll("\\\\","/"));
				tShareInfo.setImgData(null);
			}
			tShareInfo.setCreatetime(nowDate);
			tShareInfoService.save(tShareInfo);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			if(storePic_name!=null && storePic_name.length>0){
				for(int i=0;i<storePic_name.length;i++){
					Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+storePic_name[i]);
				}
			}
			e.printStackTrace();
		}
		return R.error("保存失败");
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tshareinfo:update")
	public R update(HttpServletRequest request,@RequestBody TShareInfoEntity tShareInfo){
		
		TShareInfoEntity shareInfo_old = tShareInfoService.queryObject(tShareInfo.getId());
		String pic_old = shareInfo_old.getPic();
		//图片流
		String imgData = tShareInfo.getImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		//上传信息图片
		if(imgData!=null && !"".equals(imgData)){
			String[] imgDatas = imgData.split("\\$");
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<imgDatas.length;i++){
				if(imgDatas[i]!=null && !"".equals(imgDatas[i])){
					String filename = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
					boolean tag = Base64Util.GenerateImage(imgDatas[i].substring(imgDatas[i].indexOf(",")+1), REALPATH+File.separator+path, filename, 0);
					if(tag){
						sb.append(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename);
						if(i<imgDatas.length-1){
							sb.append(";");
						}
					}
				}
			}
			//删除之前上传的游戏图片
			if(pic_old!=null && !"".equals(pic_old)){
				String[] pictureUrl_olds = pic_old.split(";");
				for(int g=0;g<pictureUrl_olds.length;g++){
					Base64Util.deleteFile(REALPATH+File.separator+pictureUrl_olds[g].substring(pictureUrl_olds[g].indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tShareInfo.setPic(sb.toString().replaceAll("\\\\","/"));
			tShareInfo.setImgData(null);
		}
		
		tShareInfoService.update(tShareInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tshareinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		tShareInfoService.deleteBatch(ids);
		return R.ok();
	}
	
}
