package io.renren.utils;

import java.math.BigDecimal;

/**
 * 系统参数相关Key
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 10:33
 */
public class ConfigConstant {
    /**
     * 云存储配置KEY
     */
    public final static String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";
    
    /** 
     * 方法名称: getRandNum 生成随机数
     * 描述: TODO
     * @param count
     * @return
     * @create 2017年6月13日 上午10:57:28 
     * @author  llh  
     * @version 1.0.0 
     */
    public static String getRandNum(int count) {
   	 String vcode = "";
        for (int i = 0; i < count; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
   }
    
    /** 
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节) 
     *  
     * @param bytes 
     * @return 
     */  
    public static String bytes2kb(long bytes) {  
        BigDecimal filesize = new BigDecimal(bytes);  
        BigDecimal megabyte = new BigDecimal(1024 * 1024);  
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)  
                .floatValue();  
        if (returnValue > 1)  
            return (returnValue + "MB");  
        BigDecimal kilobyte = new BigDecimal(1024);  
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)  
                .floatValue();  
        return (returnValue + "KB");  
    }
    
}
