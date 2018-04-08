package io.renren.app.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserFavoritesEntity;
import io.renren.service.TAppuserFavoritesService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-23 11:58:00
 */
@RestController
@RequestMapping("tappuserfavorites")
public class TAppuserFavoritesController {
	@Autowired
	private TAppuserFavoritesService tAppuserFavoritesService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappuserfavorites:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserFavoritesEntity> tAppuserFavoritesList = tAppuserFavoritesService.queryList(query);
		int total = tAppuserFavoritesService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserFavoritesList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappuserfavorites:info")
	public R info(@PathVariable("id") Long id){
		TAppuserFavoritesEntity tAppuserFavorites = tAppuserFavoritesService.queryObject(id);
		
		return R.ok().put("tAppuserFavorites", tAppuserFavorites);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappuserfavorites:save")
	public R save(@RequestBody TAppuserFavoritesEntity tAppuserFavorites){
		tAppuserFavoritesService.save(tAppuserFavorites);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappuserfavorites:update")
	public R update(@RequestBody TAppuserFavoritesEntity tAppuserFavorites){
		tAppuserFavoritesService.update(tAppuserFavorites);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappuserfavorites:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserFavoritesService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
