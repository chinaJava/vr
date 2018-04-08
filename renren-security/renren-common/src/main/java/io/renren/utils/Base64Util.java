package io.renren.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.InputStream;  

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

public class Base64Util {
	

    /** 
     * 方法名称: GetImageBase64Str 图片转化成base64字符串
     * 描述: TODO
     * @param imgFile 图片地址（全路径）
     * @return
     * @create 2017年8月24日 上午10:22:52
     * @author  llh
     * @version 1.0.0
     */
    public static String GetImageBase64Str(String imgFile)  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        //String imgFile = "f://timg.jpeg";//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    /** 
     * 方法名称: GenerateImage  base64字符串转化成图片  
     * 描述: TODO
     * @param imgStr  图片Base64字节流
     * @param fileStr 图片保存的路径（全路径f://tmp//222.jpg）
     * @param sacle 压缩比例0.2/0.5
     * @return
     * @create 2017年8月23日 下午6:52:58 
     * @author  llh  
     * @version 1.0.0 
     */
    public static boolean GenerateImage(String imgStr,String filePath,String name,double sacle)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try   
        {
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {
                if(b[i]<0)
                {//调整异常数据 
                    b[i]+=256;  
                }
            }
            InputStream inputStreams = new ByteArrayInputStream(b);
            File file = new File(filePath);
            if(!file.exists()){
            	file.mkdirs();
            }
            File filename = new File(file,name);
            //图片压缩保存到指定文件目录
            if(sacle>0){
            	Thumbnails.of(inputStreams).scale(sacle).toFile(filename);
            }else{
            	Thumbnails.of(inputStreams).scale(1d).toFile(filename);
            }
            inputStreams.close();
            //生成jpeg图片  
            /*String imgFilePath = "f://222.jpg";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();*/
            return true;
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
    
    /** 
     * 方法名称: uploadFile  文件上传
     * 描述: TODO
     * @param file
     * @param path
     * @create 2017年9月19日 下午2:34:46 
     * @author  llh  
     * @version 1.0.0 
     * @throws IOException 
     */
    public static void uploadFile(MultipartFile file,String path) throws IOException{
    	File tempDirectory = new File(path);
		if (!tempDirectory.exists()){
			tempDirectory.mkdirs(); // 建立目录
		}
		/** 获取文件的后缀* */
        String filename = file.getOriginalFilename();
		path = path + File.separator + filename;
		path = backslashToSlash(path);
		File newPath = new File(path);
		file.transferTo(newPath);
    }
    
    /** 
     * 方法名称: uploadFile 通过CommonsMultipartFile 进行文件上传
     * 描述: TODO
     * @param file
     * @param path
     * @throws IOException
     * @create 2017年11月10日 下午3:01:53 
     * @author  llh  
     * @version 1.0.0 
     */
    public static void uploadFile(CommonsMultipartFile file,String path) throws IOException{
    	File tempDirectory = new File(path);
    	if (!tempDirectory.exists()){
    		tempDirectory.mkdirs(); // 建立目录
    	}
    	/** 获取文件的后缀* */
    	String filename = file.getOriginalFilename();
    	path = path + File.separator + filename;
    	path = backslashToSlash(path);
    	File newPath = new File(path);
    	file.transferTo(newPath);
    }
    
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
       boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
	 * @function: 反斜杠"\"转换为斜杠"/"
	 * @param backslash 包括反斜杠的字符串
	 * @return
	 */
	public static String backslashToSlash(String backslash)
	{
		if (backslash == null)
		{
			return null;
		}
		return backslash.replace("\\", "/");
	}
    
}
