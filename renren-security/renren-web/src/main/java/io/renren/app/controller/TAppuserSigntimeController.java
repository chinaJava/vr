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

import io.renren.entity.TAppuserSigntimeEntity;
import io.renren.service.TAppuserSigntimeService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 15:21:44
 */
@RestController
@RequestMapping("tappusersigntime")
public class TAppuserSigntimeController {
	@Autowired
	private TAppuserSigntimeService tAppuserSigntimeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappusersigntime:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserSigntimeEntity> tAppuserSigntimeList = tAppuserSigntimeService.queryList(query);
		int total = tAppuserSigntimeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserSigntimeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappusersigntime:info")
	public R info(@PathVariable("id") Long id){
		TAppuserSigntimeEntity tAppuserSigntime = tAppuserSigntimeService.queryObject(id);
		
		return R.ok().put("tAppuserSigntime", tAppuserSigntime);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappusersigntime:save")
	public R save(@RequestBody TAppuserSigntimeEntity tAppuserSigntime){
		tAppuserSigntimeService.save(tAppuserSigntime);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappusersigntime:update")
	public R update(@RequestBody TAppuserSigntimeEntity tAppuserSigntime){
		tAppuserSigntimeService.update(tAppuserSigntime);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappusersigntime:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserSigntimeService.deleteBatch(ids);
		return R.ok();
	}
	
}
