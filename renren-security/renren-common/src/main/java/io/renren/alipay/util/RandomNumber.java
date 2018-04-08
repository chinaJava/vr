package io.renren.alipay.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RandomNumber {

	
	public static String reversal(){
		
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		
		
		// 8位日期
		String strTime = s.substring(8, s.length());
		// 四位随机数
		String strRandom = buildRandom(4) + "";
		
		return strRandom;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	private static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
}
