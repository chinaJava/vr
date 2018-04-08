package io.renren.api;

import java.util.Date;
import java.util.Map;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserEntity;
import io.renren.service.TAppuserService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.R;
import io.renren.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 类名称: ApiRegisterController app用户注册
 * 描述: TODO
 * @create 2017年8月8日 下午1:40:14 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api")
@Api(value="/api",description="app用户注册")
public class ApiRegisterController {
    @Autowired
    private TAppuserService userService;
    @Autowired
    private TokenService tokenService;

    /** 
     * 方法名称: register app用户注册接口
     * 描述: TODO
     * @param user
     * @return
     * @create 2017年7月26日 下午2:49:29 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @PostMapping("/register")
    @ApiOperation(value = "app用户注册接口，填入手机号、用户名和密码")
    public R register(@RequestBody TAppuserEntity user){
        String mobile = user.getMobile();
    	Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(user.getPassword(), "密码不能为空");
        if(user.getUsername()==null || "".equals(user.getUsername())){
        	user.setUsername(mobile);
        }
        user.setCreatetime(new Date());
        user.setStatus(Constant.Status.NORMAL.getValue());
        userService.save(user);
        return R.ok();
    }
    
    
    
    /** 
     * 方法名称: QQWechatBandMobile QQ或微信登录授权成功之后绑定手机号
     * 描述: TODO
     * @param map
     * @return
     * @create 2017年9月27日 下午5:06:13 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @PostMapping("/bankMobile")
    @ApiOperation(value = "app用户注册接口，填入手机号、用户名和密码")
    public R QQWechatBandMobile(@RequestBody TAppuserEntity user){
    	String mobile = user.getMobile();
    	String openId = user.getOpenid();
    	Assert.isBlank(mobile, "手机号不能为空");
    	Assert.isBlank(openId, "授权信息不能为空");
    	TAppuserEntity userObj = userService.queryObjectByMobile(mobile);
    	if(userObj!=null){
    		userObj.setOpenid(openId);
    		userService.update(userObj);
    	}else{
    		user.setPassword(DigestUtils.sha256Hex("111111"));
    		userService.save(user);
    	}
    	//绑定成功之后默认登录
    	TAppuserEntity userEntity = userService.queryObjectByMobile(mobile);
    	//生成token
        Map<String, Object> map = tokenService.createToken(userEntity.getId());
        return R.ok(map);
    }
    
    
    /** 
     * 方法名称: checkUser 根据手机号判断用户是否存在
     * 描述: TODO
     * @param mobile
     * @return
     * @create 2017年7月26日 下午5:41:39 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/user",method = RequestMethod.GET)
    @ApiOperation(value = "验证手机号是否已经注册")
    public R checkUser(@RequestParam("mobile") String mobile){
    	Assert.isBlank(mobile, "手机号不能为空");
    	TAppuserEntity user = userService.queryObjectByMobile(mobile);
    	if(user==null){
    		return R.ok();
    	}else{
    		return R.error("用户已经存在");
    	}
    }
    
    /** 
     * 方法名称: checkUsername 根据用户名判断用户是否存在
     * 描述: TODO
     * @param username
     * @return
     * @create 2017年8月8日 下午1:32:57 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/username",method = RequestMethod.GET)
    @ApiOperation(value = "验证用户名是否已经注册")
    public R checkUsername(@RequestParam("username") String username){
    	Assert.isBlank(username, "用户名不能为空");
    	TAppuserEntity user = userService.queryObjectByUsername(username);
    	if(user==null){
    		return R.ok();
    	}else{
    		return R.error("用户已经存在");
    	}
    }
    
    
}
