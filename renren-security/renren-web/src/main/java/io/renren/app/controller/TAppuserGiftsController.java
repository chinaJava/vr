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

import io.renren.entity.TAppuserGiftsEntity;
import io.renren.service.TAppuserGiftsService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 09:37:57
 */
@RestController
@RequestMapping("tappusergifts")
public class TAppuserGiftsController {
	@Autowired
	private TAppuserGiftsService tAppuserGiftsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappusergifts:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserGiftsEntity> tAppuserGiftsList = tAppuserGiftsService.queryList(query);
		int total = tAppuserGiftsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserGiftsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappusergifts:info")
	public R info(@PathVariable("id") Long id){
		TAppuserGiftsEntity tAppuserGifts = tAppuserGiftsService.queryObject(id);
		
		return R.ok().put("tAppuserGifts", tAppuserGifts);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappusergifts:save")
	public R save(@RequestBody TAppuserGiftsEntity tAppuserGifts){
		tAppuserGiftsService.save(tAppuserGifts);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappusergifts:update")
	public R update(@RequestBody TAppuserGiftsEntity tAppuserGifts){
		tAppuserGiftsService.update(tAppuserGifts);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappusergifts:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserGiftsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
