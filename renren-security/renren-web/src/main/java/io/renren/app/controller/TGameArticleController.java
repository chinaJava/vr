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

import io.renren.entity.TGameArticleEntity;
import io.renren.service.TGameArticleService;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/** 
 * 类名称: TGameArticleController 游戏相关文章
 * 描述: TODO
 * @create 2017年7月29日 上午9:21:10 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("tgamearticle")
public class TGameArticleController {
	@Autowired
	private TGameArticleService tGameArticleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgamearticle:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGameArticleEntity> tGameArticleList = tGameArticleService.queryList(query);
		int total = tGameArticleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGameArticleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgamearticle:info")
	public R info(@PathVariable("id") Long id){
		TGameArticleEntity tGameArticle = tGameArticleService.queryObject(id);
		
		return R.ok().put("tGameArticle", tGameArticle);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tgamearticle:save")
	public R save(@RequestBody TGameArticleEntity tGameArticle){
		tGameArticle.setCreatetime(new Date());
		tGameArticle.setCreator(ShiroUtils.getUserEntity().getUsername());
		tGameArticleService.save(tGameArticle);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tgamearticle:update")
	public R update(@RequestBody TGameArticleEntity tGameArticle){
		tGameArticleService.update(tGameArticle);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgamearticle:delete")
	public R delete(@RequestBody Long[] ids){
		tGameArticleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
