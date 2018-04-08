package io.renren.app.controller;

import io.renren.entity.TAppuserPromotionListEntity;
import io.renren.service.TAppuserPromotionListService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 类名称: TAppuserPromotionListController 会员推广名单管理
 * 描述: TODO
 * @create 2017年11月16日 下午2:58:34 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("tappuserpromotionlist")
public class TAppuserPromotionListController {
	@Autowired
	private TAppuserPromotionListService tAppuserPromotionListService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappuserpromotionlist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserPromotionListEntity> tAppuserPromotionListList = tAppuserPromotionListService.queryPromotionList(query);
		int total = tAppuserPromotionListService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserPromotionListList, total, query.getLimit(), query.getPage());
		
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

		List<TAppuserPromotionListEntity> tAppuserPromotionListList = tAppuserPromotionListService.queryDetail(query);
		int total = tAppuserPromotionListService.queryTotalByAppuserId(qid);
		
		PageUtils pageUtil = new PageUtils(tAppuserPromotionListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappuserpromotionlist:info")
	public R info(@PathVariable("id") String id){
		TAppuserPromotionListEntity tAppuserPromotionList = tAppuserPromotionListService.queryObject(id);
		
		return R.ok().put("tAppuserPromotionList", tAppuserPromotionList);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappuserpromotionlist:save")
	public R save(@RequestBody TAppuserPromotionListEntity tAppuserPromotionList){
		tAppuserPromotionList.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tAppuserPromotionListService.save(tAppuserPromotionList);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappuserpromotionlist:update")
	public R update(@RequestBody TAppuserPromotionListEntity tAppuserPromotionList){
		tAppuserPromotionListService.update(tAppuserPromotionList);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappuserpromotionlist:delete")
	public R delete(@RequestBody String[] ids){
		tAppuserPromotionListService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
