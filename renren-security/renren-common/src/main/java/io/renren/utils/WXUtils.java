package io.renren.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class WXUtils {

	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String,Object> doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map<String,Object> m = new HashMap<String,Object>();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	public static String ArrayToXml(Map<String, Object> result) {
		String xml = "<xml>";
		Iterator<Entry<String, Object>> iter = result.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue().toString();
			if (StringUtils.isNotBlank(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";
			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}
    /** 
     * 随机字符串 
     * @return 
     */  
    public static  String genNonceStr(int length) {  
    	 String base = "abcdefghijklmnopqrstuvwxyz0123456789";
         Random random = new Random();
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < length; i++) {
             int number = random.nextInt(base.length());
             sb.append(base.charAt(number));
         }
         return sb.toString(); 
    }
    public static String getSign(Map<String,Object> map,String key){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key="+key;
        result = MD5.MD5Encode(result).toUpperCase();
        return result;
    } 
    public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException{
        if(in == null)
            return "";
        
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }
 // 将交易金额单位，由元改为分
 	public static int revesalFraction(double num) {
 		String price = Double.toString(num * 100);
 		price = price.substring(0, price.indexOf("."));

 		int priceA = Integer.parseInt(price);
 		if (priceA > 0) {
 			return priceA;
 		}
 		return 0;
 	}
 	/**  提现订单号*/ 
 	public static  String randomOrderno(){
 		String withdraw="RA"+System.currentTimeMillis()/1000;
 		return withdraw;
 	}
 	/**
 	 * @author lw
 	 * @date 2017年6月16日下午3:42:40
 	 * @truen String 生成退款订单号
 	 */
 	/*public static  String randomRefund(){
 		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
 		String result = "WX"+dateFormat.format(new Date()) ;
 		return result;
 	}*/
 	
 	/**
 	 * @author lw
 	 * @date 2017年6月16日下午3:42:40
 	 * @truen String 生成充值订单号
 	 */
 	public static  String randomRefund(String u){
 		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
 		String result = u+dateFormat.format(new Date()) ;
 		return result;
 	}
}
