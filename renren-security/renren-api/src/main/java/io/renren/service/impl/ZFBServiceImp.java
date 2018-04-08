package io.renren.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import io.renren.alipay.constants.AlipayServiceEnvConstants;
import io.renren.alipay.factory.AlipayAPIClientFactory;
import io.renren.service.ZFBService;
import io.renren.utils.R;

@Service
public class ZFBServiceImp extends AlipayAPIClientFactory implements ZFBService{

	private static final Logger LOGGER=LoggerFactory.getLogger(TUserRepaymentServiceImpl.class);
	
	@Override
	public R userPayment(Map<String, Object> map) {
		try {
			String paymentPric=map.get("paymentPric").toString(); // 支付金额
			AlipayClient client = getAlipayClient();
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setOutTradeNo(map.get("appNo").toString());
			model.setSubject("UGameBox");
			model.setProductCode("QUICK_MSECURITY_PAY");
			model.setTotalAmount(paymentPric);
			request.setNotifyUrl(map.get("notifyUrl").toString());
			request.setBizModel(model);
			AlipayTradeAppPayResponse response = client.sdkExecute(request);
			LOGGER.info("支付宝加签,响应内容:" + response.getBody());
			return R.ok().put("zfb", response.getBody());
		} catch (AlipayApiException e) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.info("支付宝预支付失败 {}=",e);
			}
			e.printStackTrace();
		}
			return R.error("支付宝支付失败 {}=");
	}
	
	/** 
	 * @author lw
	 * @date 2017年7月15日下午3:32:33
	 * @param resultMap  支付宝回调参数
	 * @return  SUCCESS:验签正确；FAIL：验签失败
	 * @throws AlipayApiException 
	 * @description 
	 */
	@Override
	public String parseAppSyncResult(Map resultMap) throws AlipayApiException {

		String fund_bill_list = resultMap.get("fund_bill_list").toString().replaceAll("&quot;", "\"");
		resultMap.put("fund_bill_list", fund_bill_list);
		String orderNo = (String) resultMap.get("out_trade_no");
		boolean signVerified = AlipaySignature.rsaCheckV1(resultMap, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY,
				AlipayServiceEnvConstants.CHARSET, AlipayServiceEnvConstants.SIGN_TYPE); // 调用SDK验证签名
		LOGGER.info("支付宝验签结果========"+signVerified);
		if (signVerified) {
			
			return "success";
		} else {
			// TODO 验签失败则记录异常日志，并在response中返回failure.
			return "failure";
		}
	}
}
