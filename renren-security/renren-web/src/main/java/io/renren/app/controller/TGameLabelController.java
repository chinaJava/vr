package io.renren.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TGameLabelEntity;
import io.renren.service.TGameLabelService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/** 
 * 类名称: TGameLabelController 游戏标签
 * 描述: TODO
 * @create 2017年7月28日 上午10:18:39 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("tgamelabel")
public class TGameLabelController {
	@Autowired
	private TGameLabelService tGameLabelService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgamelabel:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGameLabelEntity> tGameLabelList = tGameLabelService.queryList(query);
		int total = tGameLabelService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGameLabelList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/** 
	 * 方法名称: getAllList 获取所有游戏标签
	 * 描述: TODO
	 * @return
	 * @create 2017年7月29日 下午4:54:00 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/getAllList")
	public R getAllList(){
		List<TGameLabelEntity> tGameLabelList = tGameLabelService.queryAllObject();
		return R.ok().put("gameLabelList", tGameLabelList);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgamelabel:info")
	public R info(@PathVariable("id") Integer id){
		TGameLabelEntity tGameLabel = tGameLabelService.queryObject(id);
		return R.ok().put("tGameLabel", tGameLabel);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tgamelabel:save")
	public R save(@RequestBody TGameLabelEntity tGameLabel){
		tGameLabel.setCreator(ShiroUtils.getUserEntity().getUsername());
		tGameLabel.setCreatetime(new Date());
		tGameLabelService.save(tGameLabel);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tgamelabel:update")
	public R update(@RequestBody TGameLabelEntity tGameLabel){
		tGameLabelService.update(tGameLabel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgamelabel:delete")
	public R delete(@RequestBody Integer[] ids){
		tGameLabelService.deleteBatch(ids);
		return R.ok();
	}
	
}
