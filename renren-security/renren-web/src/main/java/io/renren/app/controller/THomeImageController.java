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

import io.renren.entity.TGameInfoEntity;
import io.renren.entity.THomeImageEntity;
import io.renren.service.THomeImageService;
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
 * @date 2017-08-16 16:30:59
 */
@RestController
@RequestMapping("thomeimage")
public class THomeImageController {
	@Autowired
	private THomeImageService tHomeImageService;
	
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	//游戏详情和文章详情H5存放的文件名
	private static String SKIP_FILE_PATH = "game-app";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("thomeimage:list")
	public R list(@RequestParam Map<String, Object> params){
		if(params.get("typeId")==null || "".equals(params.get("typeId"))){
			params.remove("typeId");
		}
		//查询列表数据
        Query query = new Query(params);

		List<THomeImageEntity> tHomeImageList = tHomeImageService.queryList(query);
		int total = tHomeImageService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tHomeImageList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("thomeimage:info")
	public R info(@PathVariable("id") Long id){
		THomeImageEntity tHomeImage = tHomeImageService.queryObject(id);
		return R.ok().put("tHomeImage", tHomeImage);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("thomeimage:save")
	public R save(HttpServletRequest request,@RequestBody THomeImageEntity tHomeImage){
		//图片流
		String imgData = tHomeImage.getImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		String[] gamePic_name = null;
		String articles = tHomeImage.getSkipToArticle();
		String games = tHomeImage.getSkipToGame();
		try{
			//上传图片
			if(imgData!=null && !"".equals(imgData)){
				String[] imgDatas = imgData.split("\\$");
				StringBuffer sb = new StringBuffer();
				StringBuffer sb_path = new StringBuffer();
				gamePic_name = new String[imgDatas.length];
				for(int i=0;i<imgDatas.length;i++){
					if(imgDatas[i]!=null && !"".equals(imgDatas[i])){
						String filename = DateUtils.getFormatedDateString(new Date())+"i"+PICTURE_SUFFIX;
						gamePic_name[i] = filename;
						boolean tag = Base64Util.GenerateImage(imgDatas[i].substring(imgDatas[i].indexOf(",")+1), REALPATH+File.separator+path, filename, 0);
						if(tag){
							sb.append(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename);
							if(i<imgDatas.length-1){
								sb.append(";");
							}
						}
					}
					if(articles!=null && articles.indexOf((i+1)+":")>-1){
						String[] articleIds = articles.split("\\;");
						for(int a=0;a<articleIds.length;a++){
							if(articleIds[a].indexOf((i+1)+":")>-1){
								String id = articleIds[a].substring(articleIds[a].indexOf(":"));
								sb_path.append(realPath+SKIP_FILE_PATH+File.separator+"articleDetails.html?id="+id+";");
								break;
							}
						}
					}else if(games!=null && games.indexOf((i+1)+":")>-1){
						String[] gameIds = games.split("\\;");
						for(int g=0;g<gameIds.length;g++){
							if(gameIds[g].indexOf((i+1)+":")>-1){
								String id = gameIds[g].substring(gameIds[g].indexOf(":"));
								sb_path.append(realPath+SKIP_FILE_PATH+File.separator+"details.html?id="+id+";");
								break;
							}
						}
					}else{
						sb_path.append(";");
					}
				}
				//跳转路径
				tHomeImage.setPath(sb_path.toString());
				tHomeImage.setPicUrl(sb.toString());
				tHomeImage.setImgData(null);
			}
			tHomeImageService.save(tHomeImage);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			if(gamePic_name!=null && gamePic_name.length>0){
				for(int i=0;i<gamePic_name.length;i++){
					Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+gamePic_name[i]);
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
	@RequiresPermissions("thomeimage:update")
	public R update(HttpServletRequest request,@RequestBody THomeImageEntity tHomeImage){
		
		THomeImageEntity homeImage_old = tHomeImageService.queryObject(tHomeImage.getId());
		String pictureUrl_old = homeImage_old.getPicUrl();
		//图片流
		String imgData = tHomeImage.getImgData();
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
			if(pictureUrl_old!=null && !"".equals(pictureUrl_old)){
				String[] pictureUrl_olds = pictureUrl_old.split(";");
				for(int g=0;g<pictureUrl_olds.length;g++){
					Base64Util.deleteFile(REALPATH+File.separator+pictureUrl_olds[g].substring(pictureUrl_olds[g].indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tHomeImage.setPicUrl(sb.toString());
			tHomeImage.setImgData(null);
		}
		tHomeImageService.update(tHomeImage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("thomeimage:delete")
	public R delete(@RequestBody Long[] ids){
		tHomeImageService.deleteBatch(ids);
		return R.ok();
	}
	
}
