package io.renren.app.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserPointgoodsEntity;
import io.renren.entity.TAppuserPromotionListEntity;
import io.renren.service.TAppuserPointgoodsService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:29
 */
@RestController
@RequestMapping("tappuserpointgoods")
public class TAppuserPointgoodsController {
	@Autowired
	private TAppuserPointgoodsService tAppuserPointgoodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappuserpointgoods:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserPointgoodsEntity> tAppuserPointgoodsList = tAppuserPointgoodsService.queryList(query);
		int total = tAppuserPointgoodsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserPointgoodsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/detail/{qid}")
	@RequiresPermissions("tappuserpromotionlist:list")
	public R detail(@RequestParam Map<String, Object> params,@PathVariable("qid") String qid){
		//查询列表数据
		Logger log = Logger.getLogger("TAppuserPromotionListController");
		log.info(qid);
		params.put("id", qid);
        Query query = new Query(params);

		List<TAppuserPromotionListEntity> tAppuserPromotionListList = tAppuserPointgoodsService.queryDetail(query);
		int total = tAppuserPointgoodsService.queryTotalByAppuserId(qid);
		
		PageUtils pageUtil = new PageUtils(tAppuserPromotionListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappuserpointgoods:info")
	public R info(@PathVariable("id") Long id){
		TAppuserPointgoodsEntity tAppuserPointgoods = tAppuserPointgoodsService.queryObject(id);
		
		return R.ok().put("tAppuserPointgoods", tAppuserPointgoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappuserpointgoods:save")
	public R save(@RequestBody TAppuserPointgoodsEntity tAppuserPointgoods){
		tAppuserPointgoodsService.save(tAppuserPointgoods);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappuserpointgoods:update")
	public R update(@RequestBody TAppuserPointgoodsEntity tAppuserPointgoods){
		tAppuserPointgoodsService.update(tAppuserPointgoods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappuserpointgoods:delete")
	public R delete(@RequestBody Long[] ids){
		tAppuserPointgoodsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
