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

import io.renren.entity.TUserRepaymentEntity;
import io.renren.service.TUserRepaymentService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 19:16:10
 */
@RestController
@RequestMapping("tuserrepayment")
public class TUserRepaymentController {
	@Autowired
	private TUserRepaymentService tUserRepaymentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuserrepayment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TUserRepaymentEntity> tUserRepaymentList = tUserRepaymentService.queryList(query);
		int total = tUserRepaymentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tUserRepaymentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuserrepayment:info")
	public R info(@PathVariable("id") Long id){
		TUserRepaymentEntity tUserRepayment = tUserRepaymentService.queryObject(id);
		
		return R.ok().put("tUserRepayment", tUserRepayment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuserrepayment:save")
	public R save(@RequestBody TUserRepaymentEntity tUserRepayment){
		tUserRepaymentService.save(tUserRepayment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuserrepayment:update")
	public R update(@RequestBody TUserRepaymentEntity tUserRepayment){
		tUserRepaymentService.update(tUserRepayment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tuserrepayment:delete")
	public R delete(@RequestBody Long[] ids){
		tUserRepaymentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
