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

import io.renren.entity.TGameStoreEntity;
import io.renren.entity.THomeImageEntity;
import io.renren.service.TGameStoreService;
import io.renren.utils.Base64Util;
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
 * @date 2017-10-25 17:28:03
 */
@RestController
@RequestMapping("tgamestore")
public class TGameStoreController {
	@Autowired
	private TGameStoreService tGameStoreService;
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgamestore:list")
	public R list(@RequestParam Map<String, Object> params){
		if(params.get("productType")==null || "".equals(params.get("productType"))){
			params.remove("productType");
		}
		//查询列表数据
        Query query = new Query(params);

		List<TGameStoreEntity> tGameStoreList = tGameStoreService.queryList(query);
		int total = tGameStoreService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGameStoreList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgamestore:info")
	public R info(@PathVariable("id") Long id){
		TGameStoreEntity tGameStore = tGameStoreService.queryObject(id);
		
		return R.ok().put("tGameStore", tGameStore);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tgamestore:save")
	public R save(HttpServletRequest request,@RequestBody TGameStoreEntity tGameStore){
		tGameStore.setCreator(ShiroUtils.getUserEntity().getUsername());
		//图片流
		String imgData = tGameStore.getImgData();
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
				tGameStore.setProductUrl(sb.toString().replaceAll("\\\\","/"));
				tGameStore.setImgData(null);
			}
			tGameStoreService.save(tGameStore);
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
	@RequiresPermissions("tgamestore:update")
	public R update(HttpServletRequest request,@RequestBody TGameStoreEntity tGameStore){
		TGameStoreEntity storeImage_old = tGameStoreService.queryObject(tGameStore.getId());
		String productUrl_old = storeImage_old.getProductUrl();
		//图片流
		String imgData = tGameStore.getImgData();
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
			if(productUrl_old!=null && !"".equals(productUrl_old)){
				String[] pictureUrl_olds = productUrl_old.split(";");
				for(int g=0;g<pictureUrl_olds.length;g++){
					Base64Util.deleteFile(REALPATH+File.separator+pictureUrl_olds[g].substring(pictureUrl_olds[g].indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tGameStore.setProductUrl(sb.toString().replaceAll("\\\\","/"));
			tGameStore.setImgData(null);
		}
		tGameStoreService.update(tGameStore);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgamestore:delete")
	public R delete(@RequestBody Long[] ids){
		tGameStoreService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
