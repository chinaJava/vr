package io.renren.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.renren.entity.TGameInfoEntity;
import io.renren.entity.TGameLabelTempEntity;
import io.renren.service.TGameInfoService;
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
 * @date 2017-07-21 10:03:19
 */
@RestController
@RequestMapping("tgameinfo")
public class TGameInfoController {
	private static final Logger LOGGER=LoggerFactory.getLogger(TGameInfoController.class);
	
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	@Autowired
	private TGameInfoService tGameInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgameinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(params.get("typeid")==null || "".equals(params.get("typeid"))){
			params.remove("typeid");
		}
		//查询列表数据
        Query query = new Query(params);
        
		List<TGameInfoEntity> tGameInfoList = tGameInfoService.queryList(query);
		int total = tGameInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGameInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 根据游戏名模糊查询游戏名
	 */
	@RequestMapping("/queryGameName")
	@RequiresPermissions("tgameinfo:list")
	public R queryGameName(@RequestParam(name="gameName") String gameName){
		//if(gameName.trim()!="" && gameName.trim().length()>0){
			if (gameName==null || gameName.equals("")) {
				gameName=null;
			}
			List<TGameInfoEntity> tGameInfoList = tGameInfoService.queryGameName(gameName);
			return R.ok().put("tGameInfoList", tGameInfoList);
		//}
	}
	
	/** 
	 * 方法名称: getGameLabelList 获取已选择的游戏标签
	 * 描述: TODO
	 * @param gameId
	 * @return
	 * @create 2017年7月31日 下午8:00:19 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/getGameLabelList/{gameId}")
	public R getGameLabelList(@PathVariable("gameId") Long gameId){
		List<TGameLabelTempEntity> gameLabelTempList = tGameInfoService.queryListByGameId(gameId);
		return R.ok().put("gameLabelTempList", gameLabelTempList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgameinfo:info")
	public R info(@PathVariable("id") Long id){
		TGameInfoEntity tGameInfo = tGameInfoService.queryObject(id);
		return R.ok().put("tGameInfo", tGameInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tgameinfo:save")
	public R save(HttpServletRequest request,@RequestBody TGameInfoEntity tGameInfo){
		//游戏logo图片流
		String logoImgData = tGameInfo.getLogoImgData();
		//游戏安装包上传路劲与游戏视频上传路径
		String gameUrl = tGameInfo.getGameUrl();
		String gameVideoUrl = tGameInfo.getGameVideoUrl();
		//游戏信息图片流
		String imgData = tGameInfo.getImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String realPath = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
		//String url = request.getScheme()+"\\://"+request.getServerName()+"\\:"+request.getServerPort();
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		String logoPic_name = "";
		String[] gamePic_name = null;
		try{
			//上传游戏logo图片
			if(logoImgData!=null && !"".equals(logoImgData)){
				logoImgData = logoImgData.substring(logoImgData.indexOf(",")+1);
				logoPic_name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
				boolean flag = Base64Util.GenerateImage(logoImgData, realPath+File.separator+path, logoPic_name, 0);
				if(flag){
					tGameInfo.setLogoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+logoPic_name);
				}
				tGameInfo.setLogoImgData(null);
			}
			//上传游戏信息图片
			if(imgData!=null && !"".equals(imgData)){
				String[] imgDatas = imgData.split("\\$");
				StringBuffer sb = new StringBuffer();
				gamePic_name = new String[imgDatas.length];
				for(int i=0;i<imgDatas.length;i++){
					if(imgDatas[i]!=null && !"".equals(imgDatas[i])){
						String filename = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
						gamePic_name[i] = filename;
						boolean tag = Base64Util.GenerateImage(imgDatas[i].substring(imgDatas[i].indexOf(",")+1), realPath+File.separator+path, filename, 0);
						if(tag){
							sb.append(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename);
							if(i<imgDatas.length-1){
								sb.append(";");
							} 
						}
					}
				}
				tGameInfo.setGamePictureUrl(sb.toString());
				tGameInfo.setImgData(null);
			}
			
			if(gameUrl!=null&&!"".equals(gameUrl)){
				String[] gameFilesUrl = gameUrl.split(";");
				String game_fileUrl = "";
				for(int i=0;i<gameFilesUrl.length;i++){
					game_fileUrl += PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameFilesUrl[i]+";";
				}
				tGameInfo.setGameUrl(game_fileUrl.substring(0, game_fileUrl.lastIndexOf(";")).replace("\\", "/"));
			}
			if(gameVideoUrl!=null&&!"".equals(gameVideoUrl)){
				tGameInfo.setGameVideoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameVideoUrl);
			}
			tGameInfo.setStatus(1);
			tGameInfoService.save(tGameInfo);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			Base64Util.deleteFile(realPath+File.separator+path+File.separator+logoPic_name);
			Base64Util.deleteFile(realPath+File.separator+gameUrl);
			Base64Util.deleteFile(realPath+File.separator+gameVideoUrl);
			if(gamePic_name!=null && gamePic_name.length>0){
				for(int i=0;i<gamePic_name.length;i++){
					Base64Util.deleteFile(realPath+File.separator+path+File.separator+gamePic_name[i]);
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
	@RequiresPermissions("tgameinfo:update")
	public R update(HttpServletRequest request,@RequestBody TGameInfoEntity tGameInfo){
		
		TGameInfoEntity gameInfo_old = tGameInfoService.queryObject(tGameInfo.getId());
		//游戏logo图片流
		String logoImgData = tGameInfo.getLogoImgData();
		String logoImgUrl_old = gameInfo_old.getLogoUrl();
		String gamePictureUrl_old = gameInfo_old.getGamePictureUrl();
		//游戏信息图片流
		String imgData = tGameInfo.getImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String realPath = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		//上传游戏logo图片
		if(logoImgData!=null && !"".equals(logoImgData)){
			logoImgData = logoImgData.substring(logoImgData.indexOf(",")+1);
			String name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
			boolean flag = Base64Util.GenerateImage(logoImgData, realPath+File.separator+path, name, 0);
			if(flag){
				tGameInfo.setLogoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+name);
				if(logoImgUrl_old!=null && logoImgUrl_old.indexOf(PICTURE_SAVE_PTH)>0){
					//删除之前的头像图片
					Base64Util.deleteFile(realPath+File.separator+logoImgUrl_old.substring(logoImgUrl_old.indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tGameInfo.setLogoImgData(null);
		}
		//上传游戏信息图片
		if(imgData!=null && !"".equals(imgData)){
			String[] imgDatas = imgData.split("\\$");
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<imgDatas.length;i++){
				if(imgDatas[i]!=null && !"".equals(imgDatas[i])){
					String filename = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
					boolean tag = Base64Util.GenerateImage(imgDatas[i].substring(imgDatas[i].indexOf(",")+1), realPath+File.separator+path, filename, 0);
					if(tag){
						sb.append(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename);
						if(i<imgDatas.length-1){
							sb.append(";");
						}
					}
				}
			}
			//删除之前上传的游戏图片
			if(gamePictureUrl_old!=null && !"".equals(gamePictureUrl_old)){
				String[] gamePictureUrl_olds = gamePictureUrl_old.split(";");
				for(int g=0;g<gamePictureUrl_olds.length;g++){
					Base64Util.deleteFile(realPath+File.separator+gamePictureUrl_olds[g].substring(gamePictureUrl_olds[g].indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tGameInfo.setGamePictureUrl(sb.toString());
			tGameInfo.setImgData(null);
		}
		//游戏安装包地址和游戏视频地址
		String gameUrl = tGameInfo.getGameUrl();
		String gameVideoUrl = tGameInfo.getGameVideoUrl();
		//旧地址信息
		String gameUrl_old = gameInfo_old.getGameUrl();
		String gameVideoUrl_old = gameInfo_old.getGameVideoUrl();
		//判断删除之前上传的安装包和视频文件
		if(gameUrl!=null && !"".equals(gameUrl)){
			String[] gameFilesUrl = gameUrl.split(";");
			String game_fileUrl = "";
			for(int i=0;i<gameFilesUrl.length;i++){
				game_fileUrl += PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameFilesUrl[i]+";";
			}
			game_fileUrl = game_fileUrl.substring(0, game_fileUrl.lastIndexOf(";")).replace("\\", "/");
			if(gameUrl_old!=null && !"".equals(gameUrl_old)){
				if(!game_fileUrl.equals(gameUrl_old.replace("\\", "/"))){
					tGameInfo.setGameUrl(game_fileUrl);
					//tGameInfo.setGameUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameUrl);
					String[] gameFilesUrl_old = gameUrl_old.split(";");
					for(int j=0;j<gameFilesUrl_old.length;j++){
						Base64Util.deleteFile(realPath+File.separator+gameFilesUrl_old[j].substring(gameFilesUrl_old[j].indexOf(PICTURE_SAVE_PTH)));
					}
				}
			}else{
				//tGameInfo.setGameUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameUrl);
				tGameInfo.setGameUrl(game_fileUrl);
			}
		}
		if(gameVideoUrl!=null && !"".equals(gameVideoUrl)){
			if(gameVideoUrl_old!=null && !"".equals(gameVideoUrl_old)){
				if(!gameVideoUrl.equals(gameVideoUrl_old)){
					tGameInfo.setGameUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameVideoUrl);
					Base64Util.deleteFile(realPath+File.separator+gameVideoUrl_old.substring(gameVideoUrl_old.indexOf(PICTURE_SAVE_PTH)));
				}
			}else{
				tGameInfo.setGameUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+gameVideoUrl);
			}
		}
		tGameInfoService.update(tGameInfo);
		return R.ok();
	}
	
	/** 
	 * 方法名称: uploadGameInfo  文件上传
	 * 描述: TODO
	 * @param request
	 * @param response
	 * @return
	 * @create 2017年9月26日 下午8:31:14 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/uploadGameInfoFile")
	public R uploadGameInfo(HttpServletRequest request,HttpServletResponse response){
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
				String realPath = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
				String path = PICTURE_SAVE_PTH+File.separator+nowDay;
				Base64Util.uploadFile(multipartFile, realPath+File.separator+path);
				filePath = path+File.separator+filename;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(filePath!=null && !"".equals(filePath)){
			return R.ok().put("path", filePath).put("size", multipartFile.getSize());
		}else{
			return R.error("文件上传失败");
		}
	}
	
	/**
	 *游戏上架或下架
	 */
	@RequestMapping("/updateGameStatus")
	//@RequiresPermissions("tgameinfo:update")
	public R updateGameStatus(@RequestBody TGameInfoEntity tGameInfo){
		tGameInfoService.updateGameStatus(tGameInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgameinfo:delete")
	public R delete(@RequestBody Long[] ids){
		tGameInfoService.deleteBatch(ids);
		return R.ok();
	}
	
}
