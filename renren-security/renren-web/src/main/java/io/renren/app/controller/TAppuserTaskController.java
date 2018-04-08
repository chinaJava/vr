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

import io.renren.entity.TAppuserTaskEntity;
import io.renren.service.TAppuserTaskService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 09:54:19
 */
@RestController
@RequestMapping("tappusertask")
public class TAppuserTaskController {
	@Autowired
	private TAppuserTaskService tAppuserTaskService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappusertask:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserTaskEntity> tAppuserTaskList = tAppuserTaskService.queryList(query);
		int total = tAppuserTaskService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserTaskList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappusertask:info")
	public R info(@PathVariable("id") Long id){
		TAppuserTaskEntity tAppuserTask = tAppuserTaskService.queryObject(id);
		
		return R.ok().put("tAppuserTask", tAppuserTask);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappusertask:save")
	public R save(@RequestBody TAppuserTaskEntity tAppuserTask){
		tAppuserTaskService.save(tAppuserTask);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappusertask:update")
	public R update(@RequestBody TAppuserTaskEntity tAppuserTask){
		tAppuserTaskService.update(tAppuserTask);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappusertask:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserTaskService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
