package io.renren.api;

import io.renren.annotation.IgnoreAuth;
import io.renren.sendMessage.SendMessageClient;
import io.renren.utils.ConfigConstant;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 类名称: ApiSendMsgController 发送短信消息类
 * 描述: TODO
 * @create 2017年8月4日 下午3:18:58 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api")
@Api(value="/api",description="发送短信消息")
public class ApiSendMsgController {
	@Autowired
    protected MemcachedClient memcachedClient;
	
	/** 
	 * 方法名称: sendRegisterMsgCode 发送注册短信验证码
	 * 描述: TODO
	 * @param mobile
	 * @return
	 * @create 2017年8月4日 下午3:57:57 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	@IgnoreAuth
    @RequestMapping(value = "/get/registerCode",method = RequestMethod.GET)
	@ApiOperation(value = "发送注册短信验证码")
    public R sendRegisterMsgCode(@RequestParam("mobile") String mobile){

		String msgCode_mem = (String) memcachedClient.get(mobile+"_registerCode");
    	String msgCode= "";
    	boolean flag = false;
    	//memcache中存在是直接取出,否则重新生成
    	if(msgCode_mem!=null&&!"".equals(msgCode_mem)){
    		msgCode = msgCode_mem;
    	}else{
    		msgCode = ConfigConstant.getRandNum(6);
    		flag  = true;
    	}
    	boolean tag = SendMessageClient.registerMsgCode(mobile, msgCode);
		if(flag && tag){
			memcachedClient.set(mobile+"_registerCode", 60*10, msgCode);
		}
		if(tag){
			return R.ok();
		}else{
			return R.error("验证码发送失败");
		}
    }
    
    /** 
     * 方法名称: checkMessageCode 判断验证码是否正确
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @create 2017年8月4日 下午3:58:08 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/registerCode",method = RequestMethod.GET)
    @ApiOperation(value = "校验注册短信验证码")
    public R checkRegisterMsgCode(@RequestParam("mobile") String mobile,@RequestParam("msgCode") String msgCode){
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_registerCode");
    	if(msgCode.equals(msgCode_mem)){
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    
    /** 
     * 方法名称: sendLoginMsgCode 发送登录验证码
     * 描述: TODO
     * @param mobile
     * @return
     * @create 2017年8月4日 下午4:02:32 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/get/loginCode",method = RequestMethod.GET)
    @ApiOperation(value = "发送登录短信验证码")
    public R sendLoginMsgCode(@RequestParam("mobile") String mobile){

		String msgCode_mem = (String) memcachedClient.get(mobile+"_loginCode");
    	String msgCode= "";
    	boolean flag = false;
    	//memcache中存在是直接取出,否则重新生成
    	if(msgCode_mem!=null&&!"".equals(msgCode_mem)){
    		msgCode = msgCode_mem;
    	}else{
    		msgCode = ConfigConstant.getRandNum(6);
    		flag  = true;
    	}
    	boolean tag = SendMessageClient.loginMsgCode(mobile, msgCode);
		if(flag && tag){
			memcachedClient.set(mobile+"_loginCode", 60*10, msgCode);
		}
		if(tag){
			return R.ok();
		}else{
			return R.error("验证码发送失败");
		}
    }
    
    /** 
     * 方法名称: checkLoginCode 校验登录验证码
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @create 2017年8月4日 下午4:02:42 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/loginCode",method = RequestMethod.GET)
    @ApiOperation(value = "校验登录短信验证码")
    public R checkLoginMsgCode(@RequestParam("mobile") String mobile,@RequestParam("msgCode") String msgCode){
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_loginCode");
    	if(msgCode.equals(msgCode_mem)){
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    /** 
     * 方法名称: sendLoginOffsiteMsgCode 异地登录短信验证码
     * 描述: TODO
     * @param mobile
     * @return
     * @create 2017年8月4日 下午4:06:23 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/get/loginOffsiteCode",method = RequestMethod.GET)
    @ApiOperation(value = "发送异地登录短信验证码")
    public R sendLoginOffsiteMsgCode(@RequestParam("mobile") String mobile){

		String msgCode_mem = (String) memcachedClient.get(mobile+"_loginOffsiteCode");
    	String msgCode= "";
    	boolean flag = false;
    	//memcache中存在是直接取出,否则重新生成
    	if(msgCode_mem!=null&&!"".equals(msgCode_mem)){
    		msgCode = msgCode_mem;
    	}else{
    		msgCode = ConfigConstant.getRandNum(6);
    		flag  = true;
    	}
    	boolean tag = SendMessageClient.loginOffsiteMsgCode(mobile, msgCode);
		if(flag && tag){
			memcachedClient.set(mobile+"_loginOffsiteCode", 60*10, msgCode);
		}
		if(tag){
			return R.ok();
		}else{
			return R.error("验证码发送失败");
		}
    }
    
    /** 
     * 方法名称: checkLoginOffsiteMsgCode 校验异地登录验证码
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @create 2017年8月4日 下午4:06:39 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/loginOffsiteCode",method = RequestMethod.GET)
    @ApiOperation(value = "校验异地登录短信验证码")
    public R checkLoginOffsiteMsgCode(@RequestParam("mobile") String mobile,@RequestParam("msgCode") String msgCode){
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_loginOffsiteCode");
    	if(msgCode.equals(msgCode_mem)){
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    
    /** 
     * 方法名称: sendModifyPwdMsgCode 发送修改密码验证码
     * 描述: TODO
     * @param mobile
     * @return
     * @create 2017年8月4日 下午4:11:03 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/get/modifyPwdCode",method = RequestMethod.GET)
    @ApiOperation(value = "发送修改密码短信验证码")
    public R sendModifyPwdMsgCode(@RequestParam("mobile") String mobile){

		String msgCode_mem = (String) memcachedClient.get(mobile+"_modifyPwdCode");
    	String msgCode= "";
    	boolean flag = false;
    	//memcache中存在是直接取出,否则重新生成
    	if(msgCode_mem!=null&&!"".equals(msgCode_mem)){
    		msgCode = msgCode_mem;
    	}else{
    		msgCode = ConfigConstant.getRandNum(6);
    		flag  = true;
    	}
    	boolean tag = SendMessageClient.modifyPwdMsgCode(mobile, msgCode);
		if(flag && tag){
			memcachedClient.set(mobile+"_modifyPwdCode", 60*10, msgCode);
		}
		if(tag){
			return R.ok();
		}else{
			return R.error("验证码发送失败");
		}
    }
    
    /** 
     * 方法名称: checkModifyPwdMsgCode 校验修改密码验证码
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @create 2017年8月4日 下午4:11:16 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/modifyPwdCode",method = RequestMethod.GET)
    @ApiOperation(value = "校验修改密码短信验证码")
    public R checkModifyPwdMsgCode(@RequestParam("mobile") String mobile,@RequestParam("msgCode") String msgCode){
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_modifyPwdCode");
    	if(msgCode.equals(msgCode_mem)){
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    
    /** 
     * 方法名称: sendChangeInfoMsgCode 发送修改信息短信验证码
     * 描述: TODO
     * @param mobile
     * @return
     * @create 2017年8月4日 下午4:12:52 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/get/changeInfoCode",method = RequestMethod.GET)
    @ApiOperation(value = "发送修改信息短信验证码")
    public R sendChangeInfoMsgCode(@RequestParam("mobile") String mobile){

		String msgCode_mem = (String) memcachedClient.get(mobile+"_changeInfoCode");
    	String msgCode= "";
    	boolean flag = false;
    	//memcache中存在是直接取出,否则重新生成
    	if(msgCode_mem!=null&&!"".equals(msgCode_mem)){
    		msgCode = msgCode_mem;
    	}else{
    		msgCode = ConfigConstant.getRandNum(6);
    		flag  = true;
    	}
    	boolean tag = SendMessageClient.changeInfoMsgCode(mobile, msgCode);
		if(flag && tag){
			memcachedClient.set(mobile+"_changeInfoCode", 60*10, msgCode);
		}
		if(tag){
			return R.ok();
		}else{
			return R.error("验证码发送失败");
		}
    }
    
    /** 
     * 方法名称: checkChangeInfoMsgCode 校验修改信息验证码
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @create 2017年8月4日 下午4:13:08 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/changeInfoCode",method = RequestMethod.GET)
    @ApiOperation(value = "校验修改信息短信验证码")
    public R checkChangeInfoMsgCode(@RequestParam("mobile") String mobile,@RequestParam("msgCode") String msgCode){
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_changeInfoCode");
    	if(msgCode.equals(msgCode_mem)){
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }
    
    
    /** 
     * 方法名称: sendIdentificationMsgCode 发送身份验证短信验证码
     * 描述: TODO
     * @param mobile
     * @return
     * @create 2017年8月4日 下午4:15:37 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/get/identificationCode",method = RequestMethod.GET)
    @ApiOperation(value = "发送身份验证短信验证码")
    public R sendIdentificationMsgCode(@RequestParam("mobile") String mobile){

		String msgCode_mem = (String) memcachedClient.get(mobile+"_identificationCode");
    	String msgCode= "";
    	boolean flag = false;
    	//memcache中存在是直接取出,否则重新生成
    	if(msgCode_mem!=null&&!"".equals(msgCode_mem)){
    		msgCode = msgCode_mem;
    	}else{
    		msgCode = ConfigConstant.getRandNum(6);
    		flag  = true;
    	}
    	boolean tag = SendMessageClient.changeInfoMsgCode(mobile, msgCode);
		if(flag && tag){
			memcachedClient.set(mobile+"_identificationCode", 60*10, msgCode);
		}
		if(tag){
			return R.ok();
		}else{
			return R.error("验证码发送失败");
		}
    }
    /** 
     * 方法名称: checkIdentificationMsgCode 校验身份验证短信验证码
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @create 2017年8月4日 下午4:15:53 
     * @author  llh  
     * @version 1.0.0 
     */
    @IgnoreAuth
    @RequestMapping(value = "/check/identificationCode",method = RequestMethod.GET)
    @ApiOperation(value = "校验身份验证短信验证码")
    public R checkIdentificationMsgCode(@RequestParam("mobile") String mobile,@RequestParam("msgCode") String msgCode){
    	String msgCode_mem = (String) memcachedClient.get(mobile+"_identificationCode");
    	if(msgCode.equals(msgCode_mem)){
    		return R.ok();
    	}else{
    		return R.error("验证码输入错误");
    	}
    }

}
