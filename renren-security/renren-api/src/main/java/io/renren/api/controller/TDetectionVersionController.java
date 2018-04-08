package io.renren.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TDetectionVersionEntity;
import io.renren.service.TDetectionVersionService;
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
 * @date 2017-10-16 20:39:26
 */
@RestController
@RequestMapping("/api/tdetectionversion")
@Api(value="/api/tdetectionversion",description="镜像空间版本")
public class TDetectionVersionController {
	@Autowired
	private TDetectionVersionService tDetectionVersionService;
		
	/**
	 * 信息
	 */
	@IgnoreAuth
	@GetMapping("/versionInfo")
	@ApiOperation(value="版本查询")
	public R versionInfo(@RequestParam String client){
		TDetectionVersionEntity tDetectionVersion = tDetectionVersionService.queryObjectVersion(client);
		
		return R.ok().put("tDetectionVersion", tDetectionVersion);
	}
}
