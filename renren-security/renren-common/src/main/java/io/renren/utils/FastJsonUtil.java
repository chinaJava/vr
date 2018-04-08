package io.renren.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名称: FastJsonUtil
 * 描述: JSON工具类
 * @create 2015-10-22 下午5:12:37 
 * @author lgm  
 * @version 1.0.0
 */
public class FastJsonUtil {

	private static Logger logger = Logger.getLogger(FastJsonUtil.class);

	public static String convertListToJsonStr(List<?> list) {
		JsonConfig jsonConfig = new JsonConfig();  
//		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorImpl());  
		
		net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray();
		net.sf.json.JSONObject jsonObject = null;
		for (int j = 0; j < list.size(); j++) {
			jsonObject = net.sf.json.JSONObject.fromObject(list.get(j),jsonConfig);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}
	
	public static String convertListToFastJsonStr(List<?> list) {
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (int j = 0; j < list.size(); j++) {
			jsonObject = (JSONObject) JSON.toJSON(list.get(j));
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

	public static String[] convertListToJsonStrs(List<?> list) {
		JSONObject jsonObject = null;
		if (list != null && list.size() > 0) {
			String jsonStrs[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				jsonObject = (JSONObject) JSON.toJSON(list.get(j));
				jsonStrs[j] = jsonObject.toString();
			}
			return jsonStrs;
		}
		return null;
	}
	
	public static String toJsonStrByList(List<String> list){
		return  JSON.toJSONString(list,true);
	}
	
	public static String map2Json(Map map){
		return JSON.toJSONString(map, true);  
	}
	
	public static String array2Json(String[] arr){
		return  JSON.toJSONString(arr, true); 
	}
	
	public static String json2Array(String str){
		return  JSON.parseArray(str).toString();  
	}
	
	 /** 
     * 将json对象转换成Map 
     * @param jsonObject json对象 
     * @return Map对象 
     */ 
    @SuppressWarnings("unchecked") 
    public static Map<String, String> toMap(net.sf.json.JSONObject jsonObject) 
    { 
        Map<String, String> result = new HashMap<String, String>(); 
        Iterator<String> iterator = jsonObject.keys(); 
        String key = null; 
        String value = null; 
        while (iterator.hasNext()) 
        { 
            key = iterator.next(); 
            value = jsonObject.getString(key); 
            result.put(key, value); 
        } 
        return result; 
    } 
    
    /**
     * 方法名称: getList
     * 描述: 转换对象集
     * @param jsonString
     * @param cls
     * @return
     * @create 2015-10-22 下午5:12:19 
     * @author  lgm  
     * @version 1.0.0
     */
    public static List getList(String jsonString, Class cls) {
          List list = new ArrayList();
          try {
              list = JSON.parseArray(jsonString, cls);
          } catch (Exception e) {
            e.printStackTrace();
          }
          return list;
      }
    
}
