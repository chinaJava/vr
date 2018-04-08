package io.renren.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TArticleTypeEntity;
import io.renren.service.TArticleTypeService;
import io.renren.utils.Constant;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/** 
 * 类名称: TArticleTypeApiController 文章分类
 * 描述: TODO
 * @create 2017年7月28日 下午2:48:20 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api/tarticletype")
@Api(value="/api/tarticletype",description="文章信息")
public class TArticleTypeApiController {
	@Autowired
	private TArticleTypeService tArticleTypeService;
	
	/** 
	 * 方法名称: getAllList 查询所有可用游戏类型（stauts=1）
	 * 描述: TODO
	 * @return
	 * @create 2017年7月27日 下午4:40:15 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@IgnoreAuth
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ApiOperation(value = "查询所有文章分类")
	public R getAllList(){
		List<TArticleTypeEntity> tArticleTypeList = tArticleTypeService.queryAllObject();
		return R.ok().put("articleList", tArticleTypeList);
	}
	
	
	/** 
	 * 方法名称: getParentList 查询所有父类型
	 * 描述: TODO
	 * @return
	 * @create 2017年7月27日 下午4:42:55 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@IgnoreAuth
	@RequestMapping(value = "/pList",method = RequestMethod.GET)
	@ApiOperation(value = "查询所有父分类")
	public R getParentList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Constant.Status.NORMAL.getValue());
		List<TArticleTypeEntity> tArticleTypeList = tArticleTypeService.queryParentList(map);
		return R.ok().put("articleList", tArticleTypeList);
	}
	
	
	/** 
	 * 方法名称: getParentObjectByPid 通过父类型ID查询其子类型
	 * 描述: TODO
	 * @return
	 * @create 2017年7月27日 下午4:50:01 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@IgnoreAuth
	@RequestMapping(value = "/cList",method = RequestMethod.GET)
	@ApiOperation(value = "根据父ID查询其对应的子分类")
	public R getObjectByPid(@RequestParam("pId") Integer pId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", pId);
		map.put("status", Constant.Status.NORMAL.getValue());
		List<TArticleTypeEntity> tArticleTypeList = tArticleTypeService.queryListByPid(map);
		return R.ok().put("articleList", tArticleTypeList);
	}
	
}
