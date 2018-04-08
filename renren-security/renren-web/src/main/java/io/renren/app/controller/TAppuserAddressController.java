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

import io.renren.entity.TAppuserAddressEntity;
import io.renren.service.TAppuserAddressService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/** 
 * 类名称: TAppuserAddressController app用户详情
 * 描述: TODO
 * @create 2017年8月9日 下午5:01:28 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("tappuseraddress")
public class TAppuserAddressController {
	@Autowired
	private TAppuserAddressService tAppuserAddressService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappuseraddress:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserAddressEntity> tAppuserAddressList = tAppuserAddressService.queryList(query);
		int total = tAppuserAddressService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserAddressList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappuseraddress:info")
	public R info(@PathVariable("id") Long id){
		TAppuserAddressEntity tAppuserAddress = tAppuserAddressService.queryObject(id);
		
		return R.ok().put("tAppuserAddress", tAppuserAddress);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappuseraddress:save")
	public R save(@RequestBody TAppuserAddressEntity tAppuserAddress){
		tAppuserAddressService.save(tAppuserAddress);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappuseraddress:update")
	public R update(@RequestBody TAppuserAddressEntity tAppuserAddress){
		tAppuserAddressService.update(tAppuserAddress);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappuseraddress:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserAddressService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
