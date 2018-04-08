package io.renren.api.controller;

import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TokenEntity;
import io.renren.service.TUserRepaymentService;
import io.renren.service.TokenService;
import io.renren.service.WXService;
import io.renren.service.ZFBService;
import io.renren.utils.Constant;
import io.renren.utils.FastJsonUtil;
import io.renren.utils.R;
import io.renren.utils.WXUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 19:16:10
 */
@RestController
@RequestMapping("/api/tuserrepayment")
@Api(value="/api/tuserrepayment",description="用户交易")
public class TUserRepaymentAPIController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TUserRepaymentAPIController.class);
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TUserRepaymentService tUserRepaymentService;
	@Autowired
	private WXService wxService;
	@Autowired
	private ZFBService zfbService;
	
	@PostMapping(value="userPayment")
	@ApiOperation(value="用户开通会员;platform=and或IOS；payMethod=zfb或wx;memberId=会员id；openTime=开通时间，传1表示一个季度")
	public R userPayment(@RequestHeader String token,@RequestBody Map<String,Object> map){
		try {
			LOGGER.info(" 用户调起支付,发送的请求参数 {]=",map);
			TokenEntity tokenEntity=tokenService.queryByToken(token);
			map.put("userId",tokenEntity.getUserId());
			R result=tUserRepaymentService.userPayment(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("用户支付失败",e);
			}
		}
		return R.error("用户支付失败");
	}
	
	
	/**
	 * TODO 微信支付回调地址
	 * */
	 @IgnoreAuth
	 @PostMapping(value = "/callback")
	 @ApiOperation(value = "微信支付回调")
	 @ResponseBody
	    public void payNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 	String resXml = "";
		 	ServletInputStream in = request.getInputStream();
			String xmlMsg = WXUtils.inputStream2String(in);
			LOGGER.info("微信回调响应数据 {} ==="+xmlMsg);
			Map<String,Object> callback = WXUtils.doXMLParse(xmlMsg);
			if(callback != null ){
				String sign_receive = (String)callback.get("sign");
				String appid = (String)callback.get("appid");
				callback.remove("sign");
				String orderno=callback.get("out_trade_no").toString();
				String checkSign="";
				if(appid.equals(Constant.WX.ANDRONID_USER_APPID)){
					checkSign=WXUtils.getSign(callback, Constant.WX.ANDRONID_USER_KEY);
				}else if(appid.equals(Constant.WX.IOS_USER_APPID)){
					checkSign=WXUtils.getSign(callback, Constant.WX.IOS_USER_KEY);
				}
				LOGGER.info("微信回调参数验证签名 ="+checkSign);
				try {
					if(StringUtils.isNotBlank(checkSign) && sign_receive != null && checkSign.equals(sign_receive.trim())){
						String return_code=callback.get("return_code").toString();
						
						if("SUCCESS".equals(return_code) ){
							R callbackInfo=tUserRepaymentService.callback(orderno);
							//保存数据库成功
							if(callbackInfo.get("code").equals("200")){
								LOGGER.info("微信支付信息保存数据库成功");
								resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
										+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
								// 保存数据库不成功，则需要微信再次通知
							}else{
								LOGGER.info("微信支付信息保存数据库失败");
								resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
										+ "<return_msg><![CDATA[FAIL]]></return_msg>" + "</xml> ";
							}
						}else{
							if(callback.containsKey("return_msg")){
								LOGGER.info("微信支付失败 {}="+callback.get("return_msg").toString());
							}
							resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
						}
					}
				} catch (Exception e) {
					if(LOGGER.isErrorEnabled()){
						LOGGER.info("微信支付回调成功，处理业务失败 {}=",e);
					}
					e.printStackTrace();
					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[FAIL]]></return_msg>" + "</xml> ";
				}
			}
			LOGGER.info("微信回调结束");
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			out.write(resXml.getBytes("UTF-8"));
			out.flush();
			out.close();
	    }
	 
	 @PostMapping("syncInfo")
		@ApiOperation(value = "支付宝异步通知")
		@IgnoreAuth
		public String parseAppSyncResult(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, String[]> requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					}
					params.put(name, valueStr);
				}
			LOGGER.info("支付宝异步通知参数==================" + FastJsonUtil.map2Json(params));
			String parseAppSyncResult = zfbService.parseAppSyncResult(params);
			LOGGER.info("异步通知后，参数校验结果======"+parseAppSyncResult);
			R callbackInfo=null;
			try {
				if(parseAppSyncResult.equals("success")){
					String appNo=params.get("out_trade_no").toString();
					callbackInfo=tUserRepaymentService.callback(appNo);
				}
				if(callbackInfo.get("code").toString().equals("200")){
					LOGGER.info("支付宝异步返回成功通知");
					return "success";
				}else{
					LOGGER.info("支付宝异步返回错误通知");
					return "FAIL";
				}
			} catch (Exception e) {
				if(LOGGER.isErrorEnabled()){
					LOGGER.info("支付宝回调，处理业务时抛出异常 {}=",e);
				}
				e.printStackTrace();
			}
			return "FAIL";
		}
}
