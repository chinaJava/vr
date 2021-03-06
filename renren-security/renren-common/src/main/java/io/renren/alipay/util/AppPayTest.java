package io.renren.alipay.util;


import io.renren.alipay.factory.AlipayAPIClientFactory;

import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

public class AppPayTest {

    public static void main(String[] args) throws AlipayApiException {

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setOutTradeNo("fdsafdasfdsaf14343fs1fsda12321321312");
        model.setSubject("标题");
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setTotalAmount("0.01");
        request.setBizModel(model);
        request.setNotifyUrl(
            "http://ruanke.ngrok.sapronlee.com/alipay-wappay-demo/alipay_wappay_notify.html");
//        boolean flag = AlipaySignature.rsaCheckV1(params, alipaypublicKey, charset, "RSA2");
        System.out.println(populateRequest(request));
    }

    public static AlipayTradeAppPayResponse handleReuslt(Map<String, String> resultMap) throws AlipayApiException {
        AlipayClient client = AlipayAPIClientFactory.getAlipayClient();
        AlipayTradeAppPayResponse response = client.parseAppSyncResult(resultMap,
            AlipayTradeAppPayRequest.class);
        return response;
    }

    public static <T extends AlipayResponse> String populateRequest(AlipayRequest<T> request) throws AlipayApiException {
        AlipayClient client = AlipayAPIClientFactory.getAlipayClient();
        return client.sdkExecute(request).getBody();//返回给客户端处理
    }
}
