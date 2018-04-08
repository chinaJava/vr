package io.renren.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TGameLabelEntity;
import io.renren.service.TGameLabelService;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/** 
 * 类名称: TGameLabelApiController 游戏标签接口
 * 描述: TODO
 * @create 2017年7月28日 上午11:23:48 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api/tgamelabel")
@Api(value="/api/tgamelabel",description="获取游戏标签")
public class TGameLabelApiController {
	@Autowired
	private TGameLabelService tGameLabelService;
	
	/** 
	 * 方法名称: list 查询所有有效的游戏标签
	 * 描述: TODO
	 * @return
	 * @create 2017年7月28日 上午11:28:00 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@IgnoreAuth
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ApiOperation(value = "查询所有游戏标签")
	public R list(){
		List<TGameLabelEntity> gameLabelList = tGameLabelService.queryAllObject();
		return R.ok().put("gameLabelList", gameLabelList);
	}
	
	
	
}
