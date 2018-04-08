package io.renren.sendMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import io.renren.utils.PropertiesUtils;

/** 
 * 类名称: SendMessageClient 短信通知类
 * 描述: TODO
 * @create 2017年6月13日 上午10:00:12 
 * @author llh  
 * @version 1.0.0 
 */
public class SendMessageClient {
	
	//产品名称:云通信短信API产品,开发者无需替换
	private static final String PRODUCT = "Dysmsapi";
    //产品域名,开发者无需替换
	private static final String DOMAIN = "dysmsapi.aliyuncs.com";
	//短信签名-可在短信控制台中找到
	private static final String SIGN_NAME = "奇惑VR";
	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String ACCESSKEYID = PropertiesUtils.getStringByKey("ACCESSKEYID");
	private static final String ACCESSKEYSECRET = PropertiesUtils.getStringByKey("ACCESSKEYSECRET");
	
	//超时设置
	private static final String DEFAULTCONNECTTIMEOUT = "10000";
	private static final String DEFAULTREADTIMEOUT = "10000";
	
	
	//注册手机验证码短信模板ID
	private static final String REGISTER_CODE_TEMPLET = PropertiesUtils.getStringByKey("REGISTER_CODE_TEMPLET");
	//身份验证验证码
	private static final String IDENTIFICATION_CODE_TEMPLET = PropertiesUtils.getStringByKey("IDENTIFICATION_CODE_TEMPLET");
	//登录验证码
	private static final String LOGIN_CODE_TEMPLET = PropertiesUtils.getStringByKey("LOGIN_CODE_TEMPLET");
	//登录异常验证码
	private static final String LOGIN_OFFSITE_CODE_TEMPLET = PropertiesUtils.getStringByKey("LOGIN_OFFSITE_CODE_TEMPLET");
	//修改密码验证码
	private static final String MODIFY_PASSWORD_CODE_TEMPLET = PropertiesUtils.getStringByKey("MODIFY_PASSWORD_CODE_TEMPLET");
	//信息变更验证码
	private static final String CHANGEINFO_CODE_TEMPLET = PropertiesUtils.getStringByKey("CHANGEINFO_CODE_TEMPLET");
	
	
	
	/** 
	 * 方法名称: registerMsgCode 用户注册手机验证码
	 * 描述: TODO
	 * @param mobile
	 * @param msgCode
	 * @return
	 * @create 2017年6月13日 上午11:48:53 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static boolean registerMsgCode(String mobile,String msgCode){
		String jsonData = "{\"code\":\""+msgCode+"\"}";
		return sendSms(mobile, REGISTER_CODE_TEMPLET, jsonData);
	}
	
	/** 
	 * 方法名称: identificationMsgCode 身份验证验证码
	 * 描述: TODO
	 * @param mobile
	 * @param msgCode
	 * @return
	 * @create 2017年8月4日 下午2:13:30 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static boolean identificationMsgCode(String mobile,String msgCode){
		String jsonData = "{\"code\":\""+msgCode+"\"}";
		return sendSms(mobile, IDENTIFICATION_CODE_TEMPLET, jsonData);
	}
	/** 
	 * 方法名称: loginMsgCode 登录验证码
	 * 描述: TODO
	 * @param mobile
	 * @param msgCode
	 * @return
	 * @create 2017年8月4日 下午2:13:46 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static boolean loginMsgCode(String mobile,String msgCode){
		String jsonData = "{\"code\":\""+msgCode+"\"}";
		return sendSms(mobile, LOGIN_CODE_TEMPLET, jsonData);
	}
	/** 
	 * 方法名称: loginErrorMsgCode 异地登录验证码
	 * 描述: TODO
	 * @param mobile
	 * @param msgCode
	 * @return
	 * @create 2017年8月4日 下午2:13:54 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static boolean loginOffsiteMsgCode(String mobile,String msgCode){
		String jsonData = "{\"code\":\""+msgCode+"\"}";
		return sendSms(mobile, LOGIN_OFFSITE_CODE_TEMPLET, jsonData);
	}
	/** 
	 * 方法名称: modifyPwdMsgCode 修改密码验证码
	 * 描述: TODO
	 * @param mobile
	 * @param msgCode
	 * @return
	 * @create 2017年8月4日 下午2:14:08 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static boolean modifyPwdMsgCode(String mobile,String msgCode){
		String jsonData = "{\"code\":\""+msgCode+"\"}";
		return sendSms(mobile, MODIFY_PASSWORD_CODE_TEMPLET, jsonData);
	}
	/** 
	 * 方法名称: changeInfoMsgCode 修改信息验证码
	 * 描述: TODO
	 * @param mobile
	 * @param msgCode
	 * @return
	 * @create 2017年8月4日 下午2:14:15 
	 * @author  llh  
	 * @version 1.0.0 
	 */
	public static boolean changeInfoMsgCode(String mobile,String msgCode){
		String jsonData = "{\"code\":\""+msgCode+"\"}";
		return sendSms(mobile, CHANGEINFO_CODE_TEMPLET, jsonData);
	}
	
	
    /** 
     * 方法名称: sendSms  发送短信
     * 描述: TODO
     * @param mobile
     * @param msgCode
     * @return
     * @throws ClientException
     * @create 2017年8月4日 上午11:03:50 
     * @author  llh  
     * @version 1.0.0 
     */
    public static boolean sendSms(String mobile,String msgTemplate,String jsonData) {
    	
    	boolean flag = false;
    	try{
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", DEFAULTCONNECTTIMEOUT);
	        System.setProperty("sun.net.client.defaultReadTimeout", DEFAULTREADTIMEOUT);
	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
	        IAcsClient acsClient = new DefaultAcsClient(profile);
	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //必填:待发送手机号
	        request.setPhoneNumbers(mobile);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(SIGN_NAME);
	        //必填:短信模板-可在短信控制台中找到
	        request.setTemplateCode(msgTemplate);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
	        request.setTemplateParam(jsonData);
	        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
	        //request.setSmsUpExtendCode("90997");
	        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	        //request.setOutId("yourOutId");
        	SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        	if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
        		flag = true;
        	}
        }catch(ClientException e){
        	e.printStackTrace();
        }
        return flag;
    }


    /** 
     * 方法名称: querySendDetails 查询短信发送情况
     * 描述: TODO
     * @param bizId
     * @return
     * @throws ClientException
     * @create 2017年8月4日 上午11:37:50 
     * @author  llh  
     * @version 1.0.0 
     */
    public static QuerySendDetailsResponse querySendDetails(String mobile,String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", DEFAULTCONNECTTIMEOUT);
        System.setProperty("sun.net.client.defaultReadTimeout", DEFAULTREADTIMEOUT);

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(mobile);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }
	
	
    public static void main(String[] args) throws ClientException, InterruptedException {

    	registerMsgCode("13268001165", "123456");
        //发短信
        /*SendSmsResponse response = sendSms("13268001165","SMS_81615052","");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

        Thread.sleep(3000L);

        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails("",response.getBizId());
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }*/

    }

}
