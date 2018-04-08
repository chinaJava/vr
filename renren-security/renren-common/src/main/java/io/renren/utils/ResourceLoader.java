package io.renren.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;  
import java.util.Map;  
import java.util.Properties;  
  
public final class ResourceLoader {  
  
    private static ResourceLoader loader = new ResourceLoader();  
    private static Map<String, Properties> loaderMap = new HashMap<String, Properties>();  
  
    private ResourceLoader() {  
    }  
  
    public static ResourceLoader getInstance() {  
        return loader;  
    }  
      
    public Properties getPropFromProperties(String fileName) throws Exception {  
          
        Properties prop = loaderMap.get(fileName);  
        if (prop != null) {  
            return prop;  
        }  
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream(fileName);  
        BufferedReader br=new BufferedReader(new InputStreamReader(ins,"UTF-8"));
        prop = new Properties();  
        prop.load(br);
        loaderMap.put(fileName, prop);
        ins.close();
        return prop;  
    }  
}  