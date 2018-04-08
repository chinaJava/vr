package io.renren.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TStoreProducttypeEntity;
import io.renren.service.TStoreProducttypeService;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-01 14:52:58
 */
@RestController
@RequestMapping("/api/tstoreproducttype")
@Api(value="/api/tstoreproducttype",description="商店大分类")
public class TStoreProducttypeController {
	@Autowired
	private TStoreProducttypeService tStoreProducttypeService;
	
	/**
	 * 列表
	 */
	@IgnoreAuth
	@GetMapping("/list")
	@ApiOperation(value="查询所有分类")
	public R list(){
		List<TStoreProducttypeEntity> tStoreProducttypeList = tStoreProducttypeService.queryAllObject();
		return R.ok().put("tStoreProducttypeList", tStoreProducttypeList);
	}
}
