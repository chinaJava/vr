package io.renren.alipay.util;

import io.renren.alipay.constants.AlipayServiceEnvConstants;
import io.renren.alipay.factory.AlipayAPIClientFactory;
import io.renren.utils.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradePayResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestMain {

	public static void tradeCreate(){
		AlipayClient client = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY, AlipayServiceEnvConstants.APP_ID, 
				AlipayServiceEnvConstants.PRIVATE_KEY, "json", AlipayServiceEnvConstants.CHARSET, 
				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		AlipayTradeCreateModel model = new AlipayTradeCreateModel();
		model.setOutTradeNo("2131231412");
		model.setSellerId("2088102168512190");
		model.setTotalAmount("0.01");
		model.setBuyerLogonId("501624560335vj@sandbox.com");
		model.setSubject("iphone7");
		request.setBizModel(model);
		AlipayTradeCreateResponse response = new AlipayTradeCreateResponse();
		try {
			response = client.execute(request);
			System.out.println(response.getBody());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static AlipayTradeAppPayResponse handleReuslt(Map<String, String> resultMap) throws AlipayApiException {
        AlipayClient client = AlipayAPIClientFactory.getAlipayClient();
        AlipayTradeAppPayResponse response = client.parseAppSyncResult(resultMap,
            AlipayTradeAppPayRequest.class);
        return response;
    }
	
	public static void tradePay(){
		AlipayClient client = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY, AlipayServiceEnvConstants.APP_ID, 
				AlipayServiceEnvConstants.PRIVATE_KEY, "json", AlipayServiceEnvConstants.CHARSET, 
				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		AlipayTradePayModel model = new AlipayTradePayModel();
		model.setOutTradeNo("1324124124");
		model.setScene("bar_code");
		model.setAuthCode("289179686864713886");
		model.setSubject("iphone7");
		model.setTotalAmount("0.01");
		request.setBizModel(model);
		AlipayTradePayResponse response = new AlipayTradePayResponse();
		try {
			response = client.execute(request);
			System.out.println(response.getBody());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws AlipayApiException {
//		String string = "{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2016073001685235\",\"auth_app_id\":\"2016073001685235\",\"charset\":\"UTF-8\",\"timestamp\":\"2017-05-19 13:52:22\",\"total_amount\":\"0.10\",\"trade_no\":\"2017051921001004360227336651\",\"seller_id\":\"2088411305715021\",\"out_trade_no\":\"B1705190000932\"},\"sign\":\"DoC9mTY45Cz4nmdIu42xtepqDQ889jPI2Jlkk9LswuLbtSUW5vikegKnCc/eeWj4Ttyaa/v87ZVrzANpFsBIFpwfPI9QDS7MlyvgKy5ZyeewBGiX7RHqH8itGlQ8KGLO7ffjM/qvwJnTn9GBFXq0iTgpcwXf0q/bk5fEtUpzuW85/8bqW0AA1Xw/j55Rixye/Al1Ois24SoVUJNY8Dkm96nusVXaJeDn0uP2yZZnMKFm5+/W5Xltgav1x4gqeL5hbke7ypZ9HGy+VA91xldhm05jn9mzJMqtBmHXdRJDFDDxPgN3hhiLJXIil0b7yXB0VQ40rpLP2+jIsTbCGoGgxA==\",\"sign_type\":\"RSA2\"}";
//		tradeCreate();
//		tradePay();
		String string2="{\"memo\":\"\",\"result\":\"{\\\"alipay_trade_app_pay_response\\\":{\\\"code\\\":\\\"10000\\\",\\\"msg\\\":\\\"Success\\\",\\\"app_id\\\":\\\"2016073001685235\\\",\\\"auth_app_id\\\":\\\"2016073001685235\\\",\\\"charset\\\":\\\"UTF-8\\\",\\\"timestamp\\\":\\\"2017-05-20 10:52:09\\\",\\\"total_amount\\\":\\\"0.10\\\",\\\"trade_no\\\":\\\"2017052021001004360228793314\\\",\\\"seller_id\\\":\\\"2088411305715021\\\",\\\"out_trade_no\\\":\\\"B1705200000936\\\"},\\\"sign\\\":\\\"X6Nr5r1BhXHqy3r3XHXxTtunjXo8tjljqpV/9iVUIR+YN2bOwmpCeBQEKhMRnDM7bquCdgw33n/LsOs77YwFidnURVuscQf8PIgYTjK8xDPveF1MmnWe5W7XHmuXfMjp/cUnn9vF9Tyw2WUYISC/dAETA24c2VRGjgFYCqOAHQLmeXr2nPlWSe9NFg783WqvRw/u21SyxHo66OjyNjzDz+sd0nVL9iwvp7EDF9UeQdiQlqhjzuaYNDXk1COZhaTuhy8WhPoDce2R0HDiPMvHhYPhgglkELyzY6o4cFkITgyvBWE1ngZaca0G6HHEcY+4DUjLBEFMjsgNJh058eP37A\\u003d\\u003d\\\",\\\"sign_type\\\":\\\"RSA2\\\"}\",\"resultStatus\":\"9000\"}";
		AlipayClient client = AlipayAPIClientFactory.getAlipayClient();
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String> map = FastJsonUtil.toMap(JSONObject.fromObject(string2));
		System.out.println(map);
//		System.out.println(array[0]);
//		AlipayTradeAppPayResponse response = client.parseAppSyncResult(map, AlipayTradeAppPayRequest.class);
		AlipayTradeAppPayResponse handleReuslt = handleReuslt(map);
		if (handleReuslt.isSuccess()) {
			String body = handleReuslt.getBody();
			Map<String, String> map1 = FastJsonUtil.toMap(JSONObject.fromObject(body));
			System.out.println(body);
			String rsaCheckV1 = rsaCheckV1(map1);
			
		}
	}
	
	
	public static String rsaCheckV1(Map<String, String> map) throws AlipayApiException {
		
//		handleReuslt(map);
		
//		boolean signVerified = AlipaySignature.rsaCheckV1(map, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.CHARSET); //调用SDK验证签名
		boolean signVerified = AlipaySignature.rsaCheckV1(map, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.CHARSET, AlipayServiceEnvConstants.SIGN_TYPE);
		if(signVerified){
		   // TODO 验签成功后
			System.out.println("success");
		   //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
		}else{
			System.out.println("failure");
		    // TODO 验签失败则记录异常日志，并在response中返回failure.
		}
		
		 
		return "";
	}
}
