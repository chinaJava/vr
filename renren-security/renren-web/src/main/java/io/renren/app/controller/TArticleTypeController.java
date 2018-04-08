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

import io.renren.entity.TArticleTypeEntity;
import io.renren.service.TArticleTypeService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
@RestController
@RequestMapping("tarticletype")
public class TArticleTypeController {
	@Autowired
	private TArticleTypeService tArticleTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tarticletype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TArticleTypeEntity> tArticleTypeList = tArticleTypeService.queryList(query);
		int total = tArticleTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tArticleTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/** 
	 * 方法名称: getParentList 获取所有父类型
	 * 描述: TODO
	 * @return
	 * @create 2017年7月28日 下午2:03:53 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/getParentList")
	public R getParentList(){
		List<TArticleTypeEntity> tArticleTypeList = tArticleTypeService.queryParentList(null);
		return R.ok().put("pArticleTypeList", tArticleTypeList);
	}
	
	/** 
	 * 方法名称: getAllList 查询所有有效的文章类型
	 * 描述: TODO
	 * @return
	 * @create 2017年7月28日 下午8:01:30 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/getArticleTypeList")
	public R getAllList(){
		List<TArticleTypeEntity> tArticleTypeList = tArticleTypeService.queryAllObject();
		return R.ok().put("articleTypeList", tArticleTypeList);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tarticletype:info")
	public R info(@PathVariable("id") Integer id){
		TArticleTypeEntity tArticleType = tArticleTypeService.queryObject(id);
		
		return R.ok().put("tArticleType", tArticleType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tarticletype:save")
	public R save(@RequestBody TArticleTypeEntity tArticleType){
		tArticleType.setCreator(ShiroUtils.getUserEntity().getUsername());
		tArticleType.setCreatetime(new Date());
		tArticleTypeService.save(tArticleType);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tarticletype:update")
	public R update(@RequestBody TArticleTypeEntity tArticleType){
		tArticleTypeService.update(tArticleType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tarticletype:delete")
	public R delete(@RequestBody Integer[] ids){
		tArticleTypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
