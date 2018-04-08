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

import io.renren.entity.TAppSuggestEntity;
import io.renren.service.TAppSuggestService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-09 14:18:56
 */
@RestController
@RequestMapping("tappsuggest")
public class TAppSuggestController {
	@Autowired
	private TAppSuggestService tAppSuggestService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappsuggest:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppSuggestEntity> tAppSuggestList = tAppSuggestService.queryList(query);
		int total = tAppSuggestService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppSuggestList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappsuggest:info")
	public R info(@PathVariable("id") Integer id){
		TAppSuggestEntity tAppSuggest = tAppSuggestService.queryObject(id);
		
		return R.ok().put("tAppSuggest", tAppSuggest);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappsuggest:save")
	public R save(@RequestBody TAppSuggestEntity tAppSuggest){
		tAppSuggestService.save(tAppSuggest);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappsuggest:update")
	public R update(@RequestBody TAppSuggestEntity tAppSuggest){
		tAppSuggestService.update(tAppSuggest);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappsuggest:delete")
	public R delete(@RequestBody Integer[] ids){
		tAppSuggestService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
