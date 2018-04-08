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

import io.renren.entity.TAppuserDetailEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
@RestController
@RequestMapping("tappuserdetail")
public class TAppuserDetailController {
	@Autowired
	private TAppuserDetailService tAppuserDetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappuserdetail:list")
	public R list(@RequestParam Map<String, Object> params){
		if(params.get("memberLevel")==null || "".equals(params.get("memberLevel"))){
			params.remove("memberLevel");
		}
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserDetailEntity> tAppuserDetailList = tAppuserDetailService.queryList(query);
		int total = tAppuserDetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserDetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappuserdetail:info")
	public R info(@PathVariable("id") Long id){
		TAppuserDetailEntity tAppuserDetail = tAppuserDetailService.queryObject(id);
		
		return R.ok().put("tAppuserDetail", tAppuserDetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappuserdetail:save")
	public R save(@RequestBody TAppuserDetailEntity tAppuserDetail){
		tAppuserDetailService.save(tAppuserDetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappuserdetail:update")
	public R update(@RequestBody TAppuserDetailEntity tAppuserDetail){
		tAppuserDetailService.update(tAppuserDetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappuserdetail:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserDetailService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
