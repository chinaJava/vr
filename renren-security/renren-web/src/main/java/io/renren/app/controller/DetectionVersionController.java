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

import io.renren.entity.TDetectionVersionEntity;
import io.renren.service.TDetectionVersionService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-16 20:39:26
 */
@RestController
@RequestMapping("tdetectionversion")
public class DetectionVersionController {
	@Autowired
	private TDetectionVersionService tDetectionVersionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tdetectionversion:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDetectionVersionEntity> tDetectionVersionList = tDetectionVersionService.queryList(query);
		int total = tDetectionVersionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDetectionVersionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tdetectionversion:info")
	public R info(@PathVariable("id") Long id){
		TDetectionVersionEntity tDetectionVersion = tDetectionVersionService.queryObject(id);
		
		return R.ok().put("tDetectionVersion", tDetectionVersion);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tdetectionversion:save")
	public R save(@RequestBody TDetectionVersionEntity tDetectionVersion){
		tDetectionVersionService.save(tDetectionVersion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tdetectionversion:update")
	public R update(@RequestBody TDetectionVersionEntity tDetectionVersion){
		tDetectionVersionService.update(tDetectionVersion);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tdetectionversion:delete")
	public R delete(@RequestBody Long[] ids){
		tDetectionVersionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
