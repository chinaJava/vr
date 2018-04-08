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

import io.renren.entity.TAppuserEntity;
import io.renren.service.TAppuserService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/** 
 * 类名称: TAppuserController app用户注册信息
 * 描述: TODO
 * @create 2017年8月9日 下午5:01:00 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("tappuser")
public class TAppuserController {
	@Autowired
	private TAppuserService tAppuserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappuser:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserEntity> tAppuserList = tAppuserService.queryList(query);
		int total = tAppuserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappuser:info")
	public R info(@PathVariable("id") String id){
		TAppuserEntity tAppuser = tAppuserService.queryObject(id);
		
		return R.ok().put("tAppuser", tAppuser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappuser:save")
	public R save(@RequestBody TAppuserEntity tAppuser){
		tAppuserService.save(tAppuser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappuser:update")
	public R update(@RequestBody TAppuserEntity tAppuser){
		tAppuserService.update(tAppuser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappuser:delete")
	public R delete(@RequestBody String[] ids){
		tAppuserService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
