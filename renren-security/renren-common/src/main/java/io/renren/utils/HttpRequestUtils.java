package io.renren.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtils {

	
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 120000;  
  
    static {
        RequestConfig.Builder configBuilder = RequestConfig.custom();  
        // 设置连接超时  
        configBuilder.setConnectTimeout(MAX_TIMEOUT);  
        // 设置读取超时  
        configBuilder.setSocketTimeout(MAX_TIMEOUT);  
        // 设置从连接池获取连接实例的超时  
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);  
        requestConfig = configBuilder.build();  
    } 
    
	
	public static String sendPost(String url, Map<String,String> map){
		return sendPost(url, map, "UTF-8",null,null);
	}
	
	
	/** 
	 * 方法名称: sendPost XML格式数据post请求
	 * 描述: TODO
	 * @param url
	 * @param map
	 * @param charset 数据格式 UTF-8
	 * @return
	 * @create 2017年4月24日 上午10:32:03 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static String sendPost(String url, Map<String,String> map,String charset,String token_type,String token){
		String body = "";  
		//创建httpclient对象  
		CloseableHttpClient client = HttpClients.createDefault();  
		//创建post方式请求对象  
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try{
			httpPost.setConfig(requestConfig);
	        //装填参数  
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	        if(map!=null){  
	            for (Entry<String, String> entry : map.entrySet()) {  
	                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	            }
	        }
	        //设置参数到请求对象中  
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));  
	        //System.out.println("请求地址："+url);  
	        //System.out.println("请求参数："+nvps.toString());  
	        //设置header信息  
	        //指定报文头【Content-type】、【User-Agent】  
	        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
	        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        //添加http请求证书
	        if(token_type!=null && !"".equals(token_type)){
	        	httpPost.setHeader("Authorization",token_type+" "+token);
	        }
	        //执行请求操作，并拿到结果（同步阻塞）  
	        response = client.execute(httpPost);  
	        //获取结果实体  
	        entity = response.getEntity();  
	        if (entity != null) {  
	            //按指定编码转换结果实体为String类型  
	            body = EntityUtils.toString(entity, charset);
	        }  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(entity!=null){
					EntityUtils.consume(entity);
				}
				if(httpPost!=null){
					httpPost.abort();
				}
				if(response!=null){
					response.close();
				}
				if(client!=null){
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
        return body;  
	}
	
	/** 
	 * 方法名称: sendPostJson json数据格式的post请求
	 * 描述: TODO
	 * @param url
	 * @param json
	 * @return
	 * @create 2017年4月24日 上午10:50:22 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static String sendPostJson(String url, Object json){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(url);  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;
  
        try {  
            httpPost.setConfig(requestConfig);  
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");  
            stringEntity.setContentType("application/json");  
            //httpPost.setHeader("Authorization",token_type+" "+token);
            httpPost.setEntity(stringEntity);  
            response = httpClient.execute(httpPost);  
            entity = response.getEntity();  
            //System.out.println(response.getStatusLine().getStatusCode());
            if(entity!=null){
            	httpStr = EntityUtils.toString(entity, "UTF-8");  
            }
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {  
        	try {
				if(entity!=null){
					EntityUtils.consume(entity);
				}
				if(httpPost!=null){
					httpPost.abort();
				}
				if(response!=null){
					response.close();
				}
				if(httpClient!=null){
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}   
        }  
        return httpStr; 
	}

	public static String doXml(String url, String xmlStr,String token_type,String token)  {
		//创建httpclient对象  
		CloseableHttpClient httpClient = HttpClients.createDefault();  
		// 创建httpPost连接
		HttpPost httpPost = new HttpPost(url);
		// 设置表单项
		httpPost.setEntity(new StringEntity(xmlStr, "UTF-8"));
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //添加http请求证书
        if(token_type!=null && !"".equals(token_type)){
        	httpPost.setHeader("Authorization",token_type+" "+token);
        }
		CloseableHttpResponse response = null;
		// 利用httpClient执行httpPost请求
		try {
			response = httpClient.execute(httpPost);
			// 处理结果
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(response.getEntity(), "utf-8");
				return content;
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(response!=null){
					response.close();
				}
				if(httpPost!=null){
					httpPost.abort();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	
	
	public static void main(String[] args){
		
		String url = "http://192.168.1.116/api/tappuseraddress/delete";
		Map<String,String> map = new HashMap<String,String>();
		//map.put("id", 111223);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json);
		System.out.println(HttpRequestUtils.sendPost(url, map, "UTF-8", "", ""));
		System.out.println(HttpRequestUtils.sendPostJson(url, json));
	}
}
