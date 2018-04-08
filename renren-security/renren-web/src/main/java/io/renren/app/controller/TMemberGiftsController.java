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

import io.renren.entity.TMemberGiftsEntity;
import io.renren.service.TMemberGiftsService;
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
 * @date 2017-11-16 09:37:57
 */
@RestController
@RequestMapping("tmembergifts")
public class TMemberGiftsController {
	@Autowired
	private TMemberGiftsService tMemberGiftsService;
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tmembergifts:list")
	public R list(@RequestParam Map<String, Object> params){
		if(params.get("memberLevel")==null || "".equals(params.get("memberLevel"))){
			params.remove("memberLevel");
		}
		//查询列表数据
        Query query = new Query(params);

		List<TMemberGiftsEntity> tMemberGiftsList = tMemberGiftsService.queryList(query);
		int total = tMemberGiftsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tMemberGiftsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tmembergifts:info")
	public R info(@PathVariable("id") Integer id){
		TMemberGiftsEntity tMemberGifts = tMemberGiftsService.queryObject(id);
		
		return R.ok().put("tMemberGifts", tMemberGifts);
	}
	
	/**
	 * 保存
	 *
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tmembergifts:save")
	public R save(HttpServletRequest request,@RequestBody TMemberGiftsEntity tMemberGifts){
		//图片流
		String logoData = tMemberGifts.getLogoImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		try{
			//上传图片
			if(logoData!=null && !"".equals(logoData)){
				String str = "";
				logoData = logoData.substring(logoData.indexOf(",")+1);
				String filename = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
				boolean tag = Base64Util.GenerateImage(logoData, REALPATH+File.separator+path, filename, 0);
				if(tag){
					str=PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+filename;
					tMemberGifts.setLogoUrl(str);
				}
			}
			tMemberGifts.setCreatetime(new Date());
			tMemberGifts.setCreator(ShiroUtils.getUserEntity().getUsername());
			tMemberGiftsService.save(tMemberGifts);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+ DateUtils.getFormatedDateString(new Date())+"i"+PICTURE_SUFFIX);
			e.printStackTrace();
		}
		return R.error("保存失败");
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tmembergifts:update")
	public R update(HttpServletRequest request,@RequestBody TMemberGiftsEntity tMemberGifts){
		TMemberGiftsEntity giftsObj = tMemberGiftsService.queryObject(tMemberGifts.getId());
		String logoUrl_old = giftsObj.getLogoUrl();
		String logoData = tMemberGifts.getLogoImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		if(logoData!=null && !"".equals(logoData)){
			logoData = logoData.substring(logoData.indexOf(",")+1);
			String name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
			boolean flag = Base64Util.GenerateImage(logoData, REALPATH+File.separator+path, name, 0);
			if(flag){
				tMemberGifts.setLogoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+name);
				if(logoUrl_old!=null && logoUrl_old.indexOf(PICTURE_SAVE_PTH)>0){
					//删除之前的头像图片
					Base64Util.deleteFile(REALPATH+File.separator+logoUrl_old.substring(logoUrl_old.indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tMemberGifts.setLogoImgData(null);
		}
		tMemberGiftsService.update(tMemberGifts);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tmembergifts:delete")
	public R delete(@RequestBody Integer[] ids){
		tMemberGiftsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
