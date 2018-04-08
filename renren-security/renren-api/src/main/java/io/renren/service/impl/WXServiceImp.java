package io.renren.service.impl;

import io.renren.service.TMemberInfoService;
import io.renren.service.TUserRepaymentService;
import io.renren.service.WXService;
import io.renren.utils.Constant;
import io.renren.utils.HttpRequestUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.R;
import io.renren.utils.WXUtils;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lw
 * @time 2017年6月16日下午4:06:33
 * @description 微信支付业务层
 */
@Service
public class WXServiceImp implements WXService{

	private static final Logger LOGGER=LoggerFactory.getLogger(WXServiceImp.class);
	
	@Autowired
	private TUserRepaymentService userRepaymentService;
	@Autowired
	private TMemberInfoService memberInfoService;
	
	
	
	
	/** 
	 * @author lw
	 * @date 2017年6月16日下午3:16:24
	 * @param order 订单
	 * @return Map<String, Object> 安卓端参数
	 * 说明 ：调用预支付成功后，返回给安卓端参数
	 */
	@Override
	@Transactional
	public R createTransactionNumber(Map<String,Object> map) {
		try {
			String appNo=map.get("appNo").toString();
			String paymentPrice=String.valueOf(map.get("paymentPric"));
			String notifyUrl=map.get("notifyUrl").toString();
			// 金额单位转为分
			String pric=String.valueOf(Double.parseDouble(paymentPrice)*100);
			if(pric.indexOf(".")!=-1){
				pric=pric.substring(0,pric.indexOf("."));
			}
			String platForm=map.get("platform").toString();
			String nonce_str = WXUtils.genNonceStr(10);
			String[] arr=userPay(platForm);
				// 拼接数据，生成签名
				Map<String,Object> map1=new TreeMap<String, Object>();
				map1.put("appid", arr[0]);   
				map1.put("body", "UGameBox"); 
				map1.put("mch_id", arr[1]);   
				map1.put("nonce_str", appNo); 
				map1.put("notify_url",notifyUrl );
				map1.put("out_trade_no",appNo);   
				map1.put("total_fee", pric);
				map1.put("trade_type", "APP");
				String sign=WXUtils.getSign(map1,arr[2]);
				StringBuffer sb = new StringBuffer();
				sb.append("<xml>");
				sb.append("<appid>" +arr[0] + "</appid>");
				sb.append("<body>" + "UGameBox" + "</body>");
				sb.append("<mch_id>" +arr[1]+ "</mch_id>");
				sb.append("<nonce_str>" + appNo + "</nonce_str>");
				sb.append("<out_trade_no>" + appNo + "</out_trade_no>");
				sb.append("<notify_url>" + notifyUrl + "</notify_url>");
				sb.append("<sign><![CDATA[" + sign + "]]></sign>");
				sb.append("<total_fee>" + pric + "</total_fee>");
				sb.append("<trade_type>" + "APP" + "</trade_type>");
				sb.append("</xml>");
				String content = HttpRequestUtils.doXml(Constant.WX.PLACE, sb.toString(), null, null);
				LOGGER.info("微信预支付,返回信息 {}=",content);
				if (content.indexOf("prepay_id") != -1) {
					Map<String, Object> map2 = WXUtils.doXMLParse(content);
					if (map2.containsKey("prepay_id")) {
						// 微信返回支付订单号
						String prepay_id =map2.get("prepay_id").toString();
						
						// 当前系统时间，（单位：秒）
						String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
						// 生成随机字符串
						 nonce_str = WXUtils.genNonceStr(32);
						Map<String,Object> map3=new HashMap<String, Object>();
						map3.put("appid", arr[0]);
						map3.put("partnerid", arr[1]);
						map3.put("prepayid", prepay_id);
						map3.put("noncestr", nonce_str);
						map3.put("timestamp", timeStamp);
						map3.put("package", "Sign=WXPay");
						//String calbackParam="appid="+arr[0]+"&partnerid="+arr[1]+"&prepayid="+prepay_id+"&noncestr="+nonce_str+"&timestamp="+nonce_str+"&package=Sign=WXPay";
						// 返回APP参数，再次签名
						sign=WXUtils.getSign(map3,arr[2]);
						map3.put("sign", sign);
						return R.ok().put("wx", map3);
					}
				}else{
					LOGGER.info("微信签名失败 ,签名时间="+new Date());
				}
		}  catch (Exception e) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("微信预支付失败 {}=",e);
			}
			e.printStackTrace();
		}
			return R.error();
	}
	
	
	/**
	 * @author lw
	 * @date 2017年7月10日下午2:10:52
	 * @param mobile 手机类型
	 * @return 支付参数
	 * @truen String[] 包含appid,mchid,key
	 */
	private String[] userPay(String mobile) {
		String[] arr1=new String[3];
		if(mobile.equalsIgnoreCase("IOS")){
			arr1[0]=Constant.WX.IOS_USER_APPID;
			arr1[1]=Constant.WX.IOS_USER_MACH_ID;
			arr1[2]=Constant.WX.IOS_USER_KEY;
			
		}else if(mobile.equalsIgnoreCase("and")){
			arr1[0]=Constant.WX.ANDRONID_USER_APPID;
			arr1[1]=Constant.WX.ANDRONID_USER_MCH_ID;
			arr1[2]=Constant.WX.ANDRONID_USER_KEY;
		}
		return arr1;
	}
	
}
	
