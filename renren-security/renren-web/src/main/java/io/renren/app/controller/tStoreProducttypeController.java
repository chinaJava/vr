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

import io.renren.entity.TStoreProducttypeEntity;
import io.renren.service.TStoreProducttypeService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-01 14:52:58
 */
@RestController
@RequestMapping("tstoreproducttype")
public class tStoreProducttypeController {
	@Autowired
	private TStoreProducttypeService tStoreProducttypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tstoreproducttype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TStoreProducttypeEntity> tStoreProducttypeList = tStoreProducttypeService.queryList(query);
		int total = tStoreProducttypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tStoreProducttypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/ALLlist")
	public R ALLlist(){
		List<TStoreProducttypeEntity> tStoreProducttypeList = tStoreProducttypeService.queryAllObject();
		return R.ok().put("StoreProducttypeList", tStoreProducttypeList);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tstoreproducttype:info")
	public R info(@PathVariable("id") Long id){
		TStoreProducttypeEntity tStoreProducttype = tStoreProducttypeService.queryObject(id);
		
		return R.ok().put("tStoreProducttype", tStoreProducttype);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tstoreproducttype:save")
	public R save(@RequestBody TStoreProducttypeEntity tStoreProducttype){
		tStoreProducttype.setCreator(ShiroUtils.getUserEntity().getUsername());
		tStoreProducttypeService.save(tStoreProducttype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tstoreproducttype:update")
	public R update(@RequestBody TStoreProducttypeEntity tStoreProducttype){
		tStoreProducttypeService.update(tStoreProducttype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tstoreproducttype:delete")
	public R delete(@RequestBody Long[] ids){
		tStoreProducttypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
