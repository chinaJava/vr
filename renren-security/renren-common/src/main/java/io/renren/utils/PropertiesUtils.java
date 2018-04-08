package io.renren.utils;

import java.util.Properties;  
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PropertiesUtils {

	private static ResourceLoader loader = ResourceLoader.getInstance();  
    private static ConcurrentMap<String, String> configMap = new ConcurrentHashMap<String, String>();  
    private static final String DEFAULT_CONFIG_FILE = "sendMessage.properties";  
  
    private static Properties prop = null;  
  
    public static String getStringByKey(String key, String propName,Boolean isCache) {  
        try {  
            prop = loader.getPropFromProperties(propName);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        key = key.trim();  
        if(isCache){
        	if (!configMap.containsKey(key)) {  
        		if (prop.getProperty(key) != null) {  
        			configMap.put(key, prop.getProperty(key));  
        		}  
        	}  
        	return configMap.get(key);  
        }else{
        	return prop.getProperty(key);
        }
    }  
  
    public static String getStringByKey(String key,Boolean isCache) {  
        return getStringByKey(key, DEFAULT_CONFIG_FILE,isCache);  
    }
    
    public static String getStringByKey(String key) {
        return getStringByKey(key, DEFAULT_CONFIG_FILE,true);  
    }
    
    public static void setStringByKey(String key,String value){
    	try {  
            prop = loader.getPropFromProperties(DEFAULT_CONFIG_FILE);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        key = key.trim();
		prop.setProperty(key, value);
    }
  
    public static Properties getProperties() {  
        try {  
            return loader.getPropFromProperties(DEFAULT_CONFIG_FILE);  
        } catch (Exception e) {
            e.printStackTrace();  
            return null;  
        }  
    } 
    
}
