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

import io.renren.entity.TStoreOrdersEntity;
import io.renren.service.TStoreOrdersService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-04 14:30:26
 */
@RestController
@RequestMapping("tstoreorders")
public class TStoreOrdersController {
	@Autowired
	private TStoreOrdersService tStoreOrdersService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tstoreorders:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TStoreOrdersEntity> tStoreOrdersList = tStoreOrdersService.queryList(query);
		int total = tStoreOrdersService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tStoreOrdersList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tstoreorders:info")
	public R info(@PathVariable("id") Long id){
		TStoreOrdersEntity tStoreOrders = tStoreOrdersService.queryObject(id);
		
		return R.ok().put("tStoreOrders", tStoreOrders);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tstoreorders:save")
	public R save(@RequestBody TStoreOrdersEntity tStoreOrders){
		tStoreOrdersService.save(tStoreOrders);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tstoreorders:update")
	public R update(@RequestBody TStoreOrdersEntity tStoreOrders){
		tStoreOrdersService.update(tStoreOrders);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tstoreorders:delete")
	public R delete(@RequestBody Long[] ids){
		tStoreOrdersService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@RequestMapping("/send")
	public R send(@RequestBody Long[] ids){
		tStoreOrdersService.updateBatch(ids);
		return R.ok();
	}
	
}
