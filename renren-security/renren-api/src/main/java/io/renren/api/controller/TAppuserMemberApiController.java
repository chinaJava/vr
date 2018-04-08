package io.renren.api.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.mysql.fabric.Response;

import io.renren.entity.TAppuserMemberEntity;
import io.renren.service.TAppuserMemberService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-14 10:23:23
 */

public class TAppuserMemberApiController {
	@Autowired
	private TAppuserMemberService tAppuserMemberService;
	
	/**
	 * 列表
	 */
	
	/*@RequestMapping("/payClient")
	@RequiresPermissions("tappusermember:payClient")
	public  String aliPay(@RequestParam PayMent payMent){
		//查询列表数据
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APP_ID, AlipayConfig.RSA2_PRIVATE, "json", AlipayConfig.CHARSET, AlipayConfig.RSA2_PUBLIC, "RSA2");
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest(); 
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();   
        model.setSubject(payMent.getSubject());  
        model.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+(int)(Math.random()*90000+10000));  
        model.setTimeoutExpress("30m");  
        model.setTotalAmount(payMent.getAmount());  
        model.setProductCode("QUICK_MSECURITY_PAY");  
        request.setBizModel(model);  
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl("AlipayConfig.app_return_url");//回调地址  
       // request.setNotifyUrl("");
        String orderInfo = null;  
        try {  
            //这里和普通的接口调用不同，使用的是sdkExecute  
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);  
            //response.getBody()就是orderString 可以直接给客户端请求，无需再做处理。  
            orderInfo = response.getBody();  
            System.out.println(orderInfo);//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {  
            e.printStackTrace();  
        }  
        return orderInfo;  
	}
	@ResponseBody
	@RequestMapping("/aliPay_notify")
	@RequiresPermissions("tappusermember:/aliPay_notify")
	 public String aliPay_notify(HttpServletRequest request){
	        System.out.println("支付宝支付结果通知"+request.toString());
	        //获取支付宝POST过来反馈信息
	        Map<String,String> params = new HashMap<String,String>();  
	        Map requestParams = request.getParameterMap();  
	        
	        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
	            String name = (String) iter.next();
	            String[] values = (String[]) requestParams.get(name);
	            String valueStr = "";
	            for (int i = 0; i < values.length; i++) {
	                valueStr = (i == values.length - 1) ? valueStr + values[i]
	                            : valueStr + values[i] + ",";
	          }
	          //乱码解决，这段代码在出现乱码时使用。
	          //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	          params.put(name, valueStr);
	         }
	        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
	        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
	        try {
	            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.RSA2_PUBLIC, AlipayConfig.CHARSET, "RSA2");
	            if(flag){
	                if("TRADE_SUCCESS".equals(params.get("trade_status"))){
	                    //付款金额
	                    String amount = params.get("buyer_pay_amount");
	                    //商户订单号
	                    String out_trade_no = params.get("out_trade_no");
	                    //支付宝交易号
	                    String trade_no = params.get("trade_no");
	                    return "success";
	                }
	            }
	        } catch (AlipayApiException e) {
	            // TODO Auto-generated catch block
	        	//验签失败则记录异常日志，并在response中返回failure.
	            e.printStackTrace();
	           
	        }
	        return "failure";  
	    }*/
}
