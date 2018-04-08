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

import io.renren.entity.TGameTypeEntity;
import io.renren.service.TGameTypeService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/** 
 * 类名称: TGameTypeController 游戏分类
 * 描述: TODO
 * @create 2017年7月27日 上午9:19:50 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("tgametype")
public class TGameTypeController {
	@Autowired
	private TGameTypeService tGameTypeService;
	
	/** 
	 * 方法名称: list  查询列表
	 * 描述: TODO
	 * @param params
	 * @return
	 * @create 2017年7月27日 上午9:20:07 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgametype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TGameTypeEntity> tGameTypeList = tGameTypeService.queryList(query);
		int total = tGameTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tGameTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/** 
	 * 方法名称: getParentList 获取所有父类型的游戏分类
	 * 描述: TODO
	 * @return
	 * @create 2017年7月27日 上午9:58:05 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/getParentList")
	public R getParentList(){
		List<TGameTypeEntity> tGameTypeList = tGameTypeService.queryParentObject(null);
		return R.ok().put("parentGameTypeList", tGameTypeList);
	}
	
	/** 
	 * 方法名称: getAllList 查询所有有效的游戏类型
	 * 描述: TODO
	 * @return
	 * @create 2017年7月29日 下午4:02:35 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@RequestMapping("/getAllList")
	public R getAllList(){
		List<TGameTypeEntity> tGameTypeList = tGameTypeService.queryAllObject();
		return R.ok().put("parentGameTypeList", tGameTypeList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgametype:info")
	public R info(@PathVariable("id") Integer id){
		TGameTypeEntity tGameType = tGameTypeService.queryObject(id);
		
		return R.ok().put("tGameType", tGameType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tgametype:save")
	public R save(@RequestBody TGameTypeEntity tGameType){
		tGameType.setCreator(ShiroUtils.getUserEntity().getUsername());
		tGameType.setCreatetime(new Date());
		tGameTypeService.save(tGameType);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tgametype:update")
	public R update(@RequestBody TGameTypeEntity tGameType){
		tGameTypeService.update(tGameType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgametype:delete")
	public R delete(@RequestBody Integer[] ids){
		tGameTypeService.deleteBatch(ids);
		return R.ok();
	}
	
}
