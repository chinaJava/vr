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

import io.renren.entity.TAppuserSignEntity;
import io.renren.service.TAppuserSignService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 13:49:33
 */
@RestController
@RequestMapping("tappusersign")
public class TAppuserSignController {
	@Autowired
	private TAppuserSignService tAppuserSignService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappusersign:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserSignEntity> tAppuserSignList = tAppuserSignService.queryList(query);
		int total = tAppuserSignService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserSignList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappusersign:info")
	public R info(@PathVariable("id") Long id){
		TAppuserSignEntity tAppuserSign = tAppuserSignService.queryObject(id);
		
		return R.ok().put("tAppuserSign", tAppuserSign);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappusersign:save")
	public R save(@RequestBody TAppuserSignEntity tAppuserSign){
		tAppuserSignService.save(tAppuserSign);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappusersign:update")
	public R update(@RequestBody TAppuserSignEntity tAppuserSign){
		tAppuserSignService.update(tAppuserSign);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappusersign:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserSignService.deleteBatch(ids);
		return R.ok();
	}
	
}
