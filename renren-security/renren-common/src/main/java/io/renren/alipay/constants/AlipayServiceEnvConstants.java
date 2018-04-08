
/**

 * Alipay.com Inc.

 * Copyright (c) 2004-2014 All Rights Reserved.

 */

package io.renren.alipay.constants;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49
 *          taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

	/** 支付宝公钥-从支付宝生活号详情页面获取 */
	 public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5ro76J1NSHLrUuAYhZpdIHIe6LqTKq3JKahR+2nFVTH1CvWnZ8a4SjOqWTg9PWM6zuilus7O0/2gmsPh+PsqDMWXdgw+ngqLa+QWPHJ1pvCn/cPdMUm5+6fx67vyVwk8j2PigcuR6azRONlqgALhciuBNJLJULOtYuWMX4pZlMUvpy8GRjNetcnI37wygkZlSgB6RbDNgkLMbSSa8kf/LX1CbbVbGw3SFzo2niogpvbej3oRdWKFmruw2Hsu3H+dp4Kk4luGbeazHKkL4fyIEbQBcm+MGt7NiX4kG3O846HburvmSA+BHXmRjZwLIPDjd9GVfHrUA3kYOkSfaYaqPwIDAQAB";
	// TODO !!!! 注：该私钥为测试账号私钥 开发者必须设置自己的私钥 , 否则会存在安全隐患
	public  static final String PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5NTtiWFHaKcAgIzSairIq31eR6VcG1+Rxyu53ez+DgdBEEyVdjMSQkmmfawafiEnEDdypYMFwdStKPiL9Jbs0KX6V0qyjtY9yl0pBMuUjUnnEHvppGol9nFs1ASBLCooH5cNht4JjVCwwogpOU7WZ/KPBsdfeY3hwM0ZU5+8RcXolOinEcHldLgF1IvadiagpiEQYlDVMqju1B/bOrWu8v/jMbJPT759+W+l2pPiJCfdEdIDUkAgxZFStH7Tn7aQzn4DpG7IDDoATVGFppmrJzu+6vE9qk23NNUN7YZYH2wObrnCUyCfa9eQ8RAjA47OIsbNfCYNM6vrHLnwz/AoFAgMBAAECggEAd8+z5t7ztIolu6SKtx7aO1nYMJAeuFKZefOpaKnWYKBttlwcY/RYhJ3yripyUmm8LpQ5F2l8Od/SbWjdAZ6TCB9/wDRVcmJVte+hHaRrDvqfwvu3Sp7cKuInLpbX6TOcL5e3WVN5igA9xghNWnZUmJUS6o698yOuGgdRjEOuKD1/OKdBgy2jIn1jvCLpDdVJqW18i/z0LX7YSb1mnlameRpqQRUcLzO0jXl+TL/lJP4Q2sQOSK+fEayd8ZrK3cUOu43MffX61eG4C0+mc5a4dEOsI2zN7trGc/dtTsSmMVQ4d1MBLFi0jJwq22NyEEZX9w/pCAnWnBYN+CykAYJWgQKBgQD/9WedkmUQOBMzyMe8iYSer34odJgSKuhiAQANh/fKtR2HnMOJF2MAJKn3rte7n69Bz5VunIxejppAZ6nB6VXQezP8+kapxznHKVaYHR6zOwTlPlCh1aDWZWPCfEWozyx9bFbeZCXq2X2o1LGrdtwXBrbIvC1kp8WQ4WD5Uwt7qQKBgQC5POYGtcgIjjE0RFYr8a8N+hrh7fs36bpHmMzOXv/97LLGChscJiwJA3raMYHR9KmtvyMUjqPx5P7IqIhmQBKq00ZwnJ+ycTr5tf3GkwCOKk+hR7A9qGktBKh1ulVCtj1hL1hjppAFeDPbhNsOjSYp6pan7N9EjdWTKX0ORzC0/QKBgQD9P4cMjcs21/SP2QlezsWL6GEvj+TinNeeGBVAThZq1vZntkB1hNEsVBpjePf0qNNTjxrv/S6ZZAjUznxU4xRIGY5fsBV4aCkPmCQYjz9P4IZAd356EQ948YpZ5HFby9feJPJySa6Ghjhhckh0OT8b/UWeqSlUnPbKGWTEa1lCOQKBgE0LMTk1Pj+gtIJisdyRJxgPIEM78jLtOZcxzG1BQgPhAPIxndj1X3dYRS6fTLmAo1oql3KjaTjg37+hqSmuGJWITlHkMt/LDJZzxVtkJOT8YCZaZMkgVK5us4pTt8aJeboEMSUxT9GWjysMdPf2aRsiAQtTEAG5ZE1TAjqADZgZAoGAfn6GSNcfNmruty/JOv3s7WscuQ/xC1y/Zs4UO1ngJ5e2dJBCA3ewA3oMMx3CnbgXnUIlvzr5ey6+D5rLnQ61PzKq1qvs3sOdSimEuAWvzeU6usk3M4hfoIKjkoMF7NpxAF2drkR59jgYQt/vrm6RjxHeDkteeXThTh+CRaywlgo=";
				/** 签名编码-视支付宝服务窗要求 */
	public static final String SIGN_CHARSET = "UTF-8";

	/** 字符编码-传递给支付宝的数据编码 */
	public static final String CHARSET = "UTF-8";

	/** 签名类型-视支付宝服务窗要求 */
	public static final String SIGN_TYPE = "RSA2";

	/** 开发者账号PID */
	public static final String PARTNER = "2088411305715021";

	public static final String FORMAT = "json";
	/** 服务窗appId */
	public static String APP_ID = "2017112800222932";// 应用APPID

	
	// public static final String PRIVATE_KEY = "";

	// TODO !!!! 注：该公钥为测试账号公钥 开发者必须设置自己的公钥 ,否则会存在安全隐患
	// public static final String PUBLIC_KEY = "";
	//public static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAotGRufttF7S59CuyKdFT5au4OHXRKfX+qtOND/mApfZxuBpPNvBGsQBWCxYpRUl7WSaY0wr3nP5ylDllTbtdWwQtSeZpYlGYxBgmgPQI9X5ELZgm1bqvHVc/zYKe9No/hVFJgMqRzyVoyDBuP1y3ozVimMxEoRAVwTavhf02fuOlV5Y3PhdCzAM4f9XYjh0ScHZC0B/nEAyOZMVTDefsxlx+iy94+Gwu6Gp0SlSz7GUxwCV0vnEGiDyd9k8DEKXtrqD4N2LdKFxPzPLeQhvsN3DWEblX4xWSMuuw55wZnvaZxQnT2c17OOfxv8KLhvvxg9R0Y4OeLBXw2FZHw5b1twIDAQAB-----END PUBLIC KEY-----";
	/** 支付宝网关 */
	public static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
	// public static final String ALIPAY_GATEWAY =
	// "https://openapi.alipaydev.com/gateway.do";// 蚂蚁沙箱网关地址
	public static final String TRANSFER="https://openapi.alipay.com/gateway.do";
	/** 授权访问令牌的授权类型 */
	public static final String GRANT_TYPE = "authorization_code";
	
	/** 沙箱环境账号 */
	public static final String SX_URL="https://openapi.alipaydev.com/gateway.do";
	public static final String SX_APPID="2016090800464708";
	public static final String SX_PRI_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5NTtiWFHaKcAgIzSairIq31eR6VcG1+Rxyu53ez+DgdBEEyVdjMSQkmmfawafiEnEDdypYMFwdStKPiL9Jbs0KX6V0qyjtY9yl0pBMuUjUnnEHvppGol9nFs1ASBLCooH5cNht4JjVCwwogpOU7WZ/KPBsdfeY3hwM0ZU5+8RcXolOinEcHldLgF1IvadiagpiEQYlDVMqju1B/bOrWu8v/jMbJPT759+W+l2pPiJCfdEdIDUkAgxZFStH7Tn7aQzn4DpG7IDDoATVGFppmrJzu+6vE9qk23NNUN7YZYH2wObrnCUyCfa9eQ8RAjA47OIsbNfCYNM6vrHLnwz/AoFAgMBAAECggEAd8+z5t7ztIolu6SKtx7aO1nYMJAeuFKZefOpaKnWYKBttlwcY/RYhJ3yripyUmm8LpQ5F2l8Od/SbWjdAZ6TCB9/wDRVcmJVte+hHaRrDvqfwvu3Sp7cKuInLpbX6TOcL5e3WVN5igA9xghNWnZUmJUS6o698yOuGgdRjEOuKD1/OKdBgy2jIn1jvCLpDdVJqW18i/z0LX7YSb1mnlameRpqQRUcLzO0jXl+TL/lJP4Q2sQOSK+fEayd8ZrK3cUOu43MffX61eG4C0+mc5a4dEOsI2zN7trGc/dtTsSmMVQ4d1MBLFi0jJwq22NyEEZX9w/pCAnWnBYN+CykAYJWgQKBgQD/9WedkmUQOBMzyMe8iYSer34odJgSKuhiAQANh/fKtR2HnMOJF2MAJKn3rte7n69Bz5VunIxejppAZ6nB6VXQezP8+kapxznHKVaYHR6zOwTlPlCh1aDWZWPCfEWozyx9bFbeZCXq2X2o1LGrdtwXBrbIvC1kp8WQ4WD5Uwt7qQKBgQC5POYGtcgIjjE0RFYr8a8N+hrh7fs36bpHmMzOXv/97LLGChscJiwJA3raMYHR9KmtvyMUjqPx5P7IqIhmQBKq00ZwnJ+ycTr5tf3GkwCOKk+hR7A9qGktBKh1ulVCtj1hL1hjppAFeDPbhNsOjSYp6pan7N9EjdWTKX0ORzC0/QKBgQD9P4cMjcs21/SP2QlezsWL6GEvj+TinNeeGBVAThZq1vZntkB1hNEsVBpjePf0qNNTjxrv/S6ZZAjUznxU4xRIGY5fsBV4aCkPmCQYjz9P4IZAd356EQ948YpZ5HFby9feJPJySa6Ghjhhckh0OT8b/UWeqSlUnPbKGWTEa1lCOQKBgE0LMTk1Pj+gtIJisdyRJxgPIEM78jLtOZcxzG1BQgPhAPIxndj1X3dYRS6fTLmAo1oql3KjaTjg37+hqSmuGJWITlHkMt/LDJZzxVtkJOT8YCZaZMkgVK5us4pTt8aJeboEMSUxT9GWjysMdPf2aRsiAQtTEAG5ZE1TAjqADZgZAoGAfn6GSNcfNmruty/JOv3s7WscuQ/xC1y/Zs4UO1ngJ5e2dJBCA3ewA3oMMx3CnbgXnUIlvzr5ey6+D5rLnQ61PzKq1qvs3sOdSimEuAWvzeU6usk3M4hfoIKjkoMF7NpxAF2drkR59jgYQt/vrm6RjxHeDkteeXThTh+CRaywlgo=";
	public static final String SX_PUB_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5ro76J1NSHLrUuAYhZpdIHIe6LqTKq3JKahR+2nFVTH1CvWnZ8a4SjOqWTg9PWM6zuilus7O0/2gmsPh+PsqDMWXdgw+ngqLa+QWPHJ1pvCn/cPdMUm5+6fx67vyVwk8j2PigcuR6azRONlqgALhciuBNJLJULOtYuWMX4pZlMUvpy8GRjNetcnI37wygkZlSgB6RbDNgkLMbSSa8kf/LX1CbbVbGw3SFzo2niogpvbej3oRdWKFmruw2Hsu3H+dp4Kk4luGbeazHKkL4fyIEbQBcm+MGt7NiX4kG3O846HburvmSA+BHXmRjZwLIPDjd9GVfHrUA3kYOkSfaYaqPwIDAQAB";
	
	
}