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

import io.renren.entity.TMemberInfoEntity;
import io.renren.service.TMemberInfoService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 11:52:09
 */
@RestController
@RequestMapping("tmemberinfo")
public class TMemberInfoController {
	@Autowired
	private TMemberInfoService tMemberInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tmemberinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TMemberInfoEntity> tMemberInfoList = tMemberInfoService.queryList(query);
		int total = tMemberInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tMemberInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tmemberinfo:info")
	public R info(@PathVariable("id") Integer id){
		TMemberInfoEntity tMemberInfo = tMemberInfoService.queryObject(id);
		
		return R.ok().put("tMemberInfo", tMemberInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tmemberinfo:save")
	public R save(@RequestBody TMemberInfoEntity tMemberInfo){
		tMemberInfoService.save(tMemberInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tmemberinfo:update")
	public R update(@RequestBody TMemberInfoEntity tMemberInfo){
		tMemberInfoService.update(tMemberInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tmemberinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		tMemberInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
