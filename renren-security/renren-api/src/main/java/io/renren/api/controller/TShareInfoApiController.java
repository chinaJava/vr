package io.renren.api.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TShareInfoEntity;
import io.renren.service.TShareInfoService;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-12-19 09:37:43
 */
@RestController
@RequestMapping("/api/tshareinfo")
@Api(value="/api/tshareinfo",description="分享信息接口")
public class TShareInfoApiController {
	@Autowired
	private TShareInfoService tShareInfoService;
	
	/**
	 * 分享信息
	 */
	@IgnoreAuth
	@PostMapping("/getShareInfo")
	@ApiOperation(value="分享信息,传入type='1'详情页分享内容,'0'个人中心分享内容")
	public R getShareInfo(@RequestBody Map<String,String> map){
		String type = map.get("type");
		//查询列表数据
		TShareInfoEntity tShareInfo = tShareInfoService.queryShareInfo(type);
		return R.ok().put("shareInfo", tShareInfo);
	}
	
}
