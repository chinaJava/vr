package io.renren.api.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserAddressEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserAddressService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/tappuseraddress")
@Api(value="/api/tappuseraddress",description="app用户地址")
public class TAppuserAddressApiController {
	@Autowired
	private TAppuserAddressService tAppuserAddressService;
	@Autowired
    private TokenService tokenService;
	
	/** 
	 * 方法名称: info 根据用户登录的token获取用户地址信息
	 * 描述: TODO
	 * @param request
	 * @return
	 * @create 2017年8月9日 下午1:42:32 
	 * @author  llh  
	 * @version 1.0.0 
	 *
	 */
	@PostMapping("info")
	@ApiOperation(value = "根据登录的token查询用户地址信息，请求头加上token信息")
	public R info(@RequestHeader("token") String token){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		List<TAppuserAddressEntity> tAppuserAddressList = tAppuserAddressService.queryListByAppUserId(tokenEntity.getUserId());
		return R.ok().put("tAppuserAddressList", tAppuserAddressList);
	}
	
	/**
	 * 新增
	 */
	@PostMapping("add")
	@ApiOperation(value = "新增用户地址信息，请求头加上token信息")
	public R add(@RequestHeader("token") String token, @RequestBody TAppuserAddressEntity tAppuserAddress){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		tAppuserAddress.setAppuserid(tokenEntity.getUserId());
		tAppuserAddress.setStatus(Constant.Status.NORMAL.getValue());
		tAppuserAddressService.save(tAppuserAddress);
		return R.ok();
	}
	
	
	/**
	 * 修改
	 */
	@PostMapping("update")
	@ApiOperation(value = "修改用户地址信息(地址对象id,appuserid必须传入)，请求头加上token信息")
	public R update(@RequestHeader("token") String token, @RequestBody TAppuserAddressEntity tAppuserAddress){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		tAppuserAddress.setAppuserid(tokenEntity.getUserId());
		tAppuserAddressService.update(tAppuserAddress);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("delete")
	@ApiOperation(value = "删除用户地址信息，请求头加上token信息")
	public R delete(@RequestHeader("token") String token, @RequestBody Long id){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		Map map = new HashMap<>();
		map.put("id", id);
		map.put("appUserId", tokenEntity.getUserId());
		tAppuserAddressService.deleteByAppUserId(map);;
		return R.ok();
	}
	
}
