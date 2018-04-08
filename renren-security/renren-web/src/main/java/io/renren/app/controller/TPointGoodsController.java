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

import io.renren.entity.TPointGoodsEntity;
import io.renren.service.TPointGoodsService;
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
 * @date 2017-11-30 16:45:30
 */
@RestController
@RequestMapping("tpointgoods")
public class TPointGoodsController {
	@Autowired
	private TPointGoodsService tPointGoodsService;
	
	//上传图片默认的后缀
	private static String PICTURE_SUFFIX = ".jpg";
	//图片上传的文件夹名称
	private static String PICTURE_SAVE_PTH = "img";
	
	private static String REALPATH = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH");
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tpointgoods:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TPointGoodsEntity> tPointGoodsList = tPointGoodsService.queryList(query);
		int total = tPointGoodsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tPointGoodsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tpointgoods:info")
	public R info(@PathVariable("id") Integer id){
		TPointGoodsEntity tPointGoods = tPointGoodsService.queryObject(id);
		
		return R.ok().put("tPointGoods", tPointGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tpointgoods:save")
	public R save(HttpServletRequest request,@RequestBody TPointGoodsEntity tPointGoods){
		//图片流
		String logoData = tPointGoods.getLogoImgData();
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
					tPointGoods.setLogoUrl(str);
				}
			}
			tPointGoods.setCreatetime(new Date());
			tPointGoodsService.save(tPointGoods);
			return R.ok();
		}catch(Exception e){
			//异常时删除上传的图片
			Base64Util.deleteFile(REALPATH+File.separator+path+File.separator+ DateUtils.getFormatedDateString(new Date())+"i"+PICTURE_SUFFIX);
			e.printStackTrace();
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tpointgoods:update")
	public R update(HttpServletRequest request,@RequestBody TPointGoodsEntity tPointGoods){
		TPointGoodsEntity goodsObj = tPointGoodsService.queryObject(tPointGoods.getId());
		String logoUrl_old = goodsObj.getLogoUrl();
		String logoData = tPointGoods.getLogoImgData();
		Date nowDate = new Date();
		String nowDay = DateUtils.getFormatedDayStr(nowDate).replaceAll("\\-", "");
		//String realPath = request.getSession().getServletContext().getRealPath("/");
		String path = PICTURE_SAVE_PTH+File.separator+nowDay;
		if(logoData!=null && !"".equals(logoData)){
			logoData = logoData.substring(logoData.indexOf(",")+1);
			String name = DateUtils.getFormatedDateString(new Date())+PICTURE_SUFFIX;
			boolean flag = Base64Util.GenerateImage(logoData, REALPATH+File.separator+path, name, 0);
			if(flag){
				tPointGoods.setLogoUrl(PropertiesUtils.getStringByKey("MIRROR_SPACE_URL")+File.separator+path+File.separator+name);
				if(logoUrl_old!=null && logoUrl_old.indexOf(PICTURE_SAVE_PTH)>0){
					//删除之前的头像图片
					Base64Util.deleteFile(REALPATH+File.separator+logoUrl_old.substring(logoUrl_old.indexOf(PICTURE_SAVE_PTH)));
				}
			}
			tPointGoods.setLogoImgData(null);
		}
		tPointGoodsService.update(tPointGoods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tpointgoods:delete")
	public R delete(@RequestBody Integer[] ids){
		tPointGoodsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
