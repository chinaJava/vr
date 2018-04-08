package io.renren.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.THomeImageEntity;
import io.renren.service.THomeImageService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-08-16 16:30:59
 */
@RestController
@RequestMapping("/api/thomeimage")
@Api(value="/api/thomeimage",description="页面广告图片查询接口")
public class THomeImageApiController {
	@Autowired
	private THomeImageService tHomeImageService;
	
	/**
	 * 列表
	 */
	@IgnoreAuth
	@GetMapping("/queryList")
	@ApiOperation("查询页面广告图片包含banner图，AV/VR/独立游戏传入对应的typeId,推荐页面typeId=0")
	public R list(@RequestParam String typeId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", 1);
		map.put("typeId", typeId);
		List<THomeImageEntity> tHomeImageList = tHomeImageService.queryList(map);
		return R.ok().put("tHomeImageList", tHomeImageList);
	}
	
}
