package io.renren.api;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserService;
import io.renren.service.TokenService;
import io.renren.utils.R;
import io.renren.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** 
 * 类名称: ApiLoginController app用户登录
 * 描述: TODO
 * @create 2017年8月8日 下午1:40:28 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api")
@Api(value="/api",description="app用户登录")
public class ApiLoginController {
    @Autowired
    private TAppuserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    protected MemcachedClient memcachedClient;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "app用户登录接口，根据用户名或者手机号+密码登录")
    public R login(@RequestBody TAppuserEntity user){
    	String mobile = user.getMobile();
    	String password = user.getPassword();
    	String username = user.getUsername();
    	if(username==null || "".equals(username)){
    		username = mobile;
    	}
        Assert.isBlank(password, "密码不能为空");
        //用户登录
        String userId = userService.login(username, mobile, password);
        //生成token
        Map<String, Object> map = tokenService.createToken(userId);
        return R.ok(map);
    }
    
    /** 
     * 方法名称: loginByQQWechat QQ或微信第三方登录接口
     * 描述: TODO
     * @param user
     * @return
     * @create 2017年9月27日 下午4:52:36 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @PostMapping("loginByQQWechat")
    @ApiOperation(value = "QQ或微信第三方授权登录")
    public R loginByQQWechat(@RequestBody TAppuserEntity user){
    	String openId = user.getOpenid();
    	Assert.isBlank(openId, "授权信息不能为空");
    	TAppuserEntity tAppuser = userService.queryObjectByOpenId(openId);
    	if(tAppuser!=null && tAppuser.getMobile()!=null && !"".equals(tAppuser.getMobile())){
    		//生成token
            Map<String, Object> map = tokenService.createToken(tAppuser.getId());
            return R.ok(map);
    	}else{
    		return R.error(403,"请绑定手机号");
    	}
    }
    
    
    /** 
     * 方法名称: fastLogin 快速登录接口
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @param offsite
     * @return
     * @create 2017年8月8日 下午5:59:07 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @PostMapping("fastLogin")
    @ApiOperation(value = "app快速登录，手机号+验证码,offsite='1'表示异地登录")
    public R fastLogin(@RequestBody Map<String,String> map){
    	String mobile = map.get("mobile");
    	String msgCode = map.get("msgCode");
    	String offsite = map.get("offsite");
    	Assert.isBlank(mobile, "手机号不能为空");
    	Assert.isBlank(msgCode, "短信验证码不能为空");
    	String msgCode_mem = null;
    	if("1".equals(offsite)){
    		msgCode_mem = (String) memcachedClient.get(mobile+"_loginOffsiteCode");
    	}else{
    		msgCode_mem = (String) memcachedClient.get(mobile+"_loginCode");
    	}
    	if(msgCode.equals(msgCode_mem)){
    		TAppuserEntity tAppuser = userService.queryObjectByMobile(mobile);
    		Assert.isNull(tAppuser, "用户不存在");
    		//生成token
    		Map<String, Object> map_token = tokenService.createToken(tAppuser.getId());
    		return R.ok(map_token);
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    
    
    /** 
     * 方法名称: forgetPwd 忘记密码时修改密码接口
     * 描述: TODO
     * @param mobile
     * @param newPassword
     * @return
     * @create 2017年8月8日 下午5:14:28 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @PostMapping("forgetPwd")
    @ApiOperation(value = "忘记密码，修改密码接口,传入手机号mobile、新密码newPassword、验证码msgCode")
    public R forgetPwd(@RequestBody Map<String,String> map){
    	String mobile = map.get("mobile");
    	String newPassword = map.get("newPassword");
    	String msgCode = map.get("msgCode");
    	Assert.isBlank(mobile, "手机号不能为空");
    	Assert.isBlank(msgCode, "短信验证码不能为空");
    	Assert.isBlank(newPassword, "新密码不能为空");
    	//验证验证码是否正确
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_modifyPwdCode");
    	if(msgCode.equals(msgCode_mem)){
    		TAppuserEntity tAppuser = userService.queryObjectByMobile(mobile);
    		Assert.isNull(tAppuser, "修改密码出错");
    		tAppuser.setPassword(newPassword);
    		userService.update(tAppuser);
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    /** 
     * 方法名称: modifyPwd 根据旧密码修改新密码
     * 描述: TODO
     * @param token
     * @param oldPassword
     * @param newPassword
     * @return
     * @create 2017年8月9日 上午11:16:22 
     * @author  llh  
     * @version 1.0.0 
     */
    @PostMapping("modifyPwd")
	@ApiOperation(value = "根据token和旧密码修改用户登录密码,请求头加上token;oldPassword就密码newPassword新密码")
	public R modifyPwd(@RequestHeader("token") String token,@RequestBody Map<String,String> map){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TAppuserEntity appUserObj = userService.queryObject(tokenEntity.getUserId());
		String oldPassword = map.get("oldPassword");
		String newPassword = map.get("newPassword");
		if(appUserObj.getPassword().equals(DigestUtils.sha256Hex(oldPassword))){
			appUserObj.setPassword(newPassword);
			userService.update(appUserObj);
			return R.ok();
		}else{
			return R.error("旧密码输入有误!");
		}
	}
    
}
