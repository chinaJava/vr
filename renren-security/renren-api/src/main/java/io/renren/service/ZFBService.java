package io.renren.service;

import io.renren.utils.R;

import java.util.Map;

import com.alipay.api.AlipayApiException;

public interface ZFBService {

	public R userPayment(Map<String, Object> map);
	
	public String parseAppSyncResult(Map resultMap) throws AlipayApiException ;
}
