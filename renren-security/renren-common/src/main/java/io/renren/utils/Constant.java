package io.renren.utils;

/**
 * 常量
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/**
	 * 菜单类型
	 * 
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    public enum Status {
        /**
         * 不可用
         */
    	DISABLE("0"),
        /**
         * 正常
         */
    	NORMAL("1");

        private String value;

        private Status(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    public enum Sex {
    	/**
    	 * 男
    	 */
    	MAN("M"),
    	/**
    	 * 女
    	 */
    	FEMALE("F");
    	
    	private String value;
    	
    	private Sex(String value) {
    		this.value = value;
    	}
    	
    	public String getValue() {
    		return value;
    	}
    }
    
    public enum TaskStatus {
        /**
         * 未开始
         */
    	DISABLE("0"),
        /**
         * 进行中
         */
        CONTINUE("1"),
        /**
         * 已结束
         */
        FINISH("2");

        private String value;

        private TaskStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
    public interface State {
    	public static  final int Invalid=0;
    	public static  final int effective=1;
    }
    
    /** TODO 微信APP地址和常量 */
	public interface WX{
		/** IOS客户端 */
		public static final String IOS_USER_APPID="wxea0d776ae8a39c69";
		public static final String IOS_USER_MACH_ID="1482553272";
		public static final String IOS_USER_KEY="29077ba97ec0551cc1f3445013ab6ac6";
		public static final String IOS_USER_CERTIFICATE="/usr/certificate/pointmaster/user/ios/apiclient_cert.p12";
		/** 安卓客户端支付APPID */ 
		public static final String ANDRONID_USER_APPID = "wx5b5c92f0d18d2237";
		public  static final String ANDRONID_USER_MCH_ID = "1493172272";
		public static final String ANDRONID_USER_KEY="25fe4fabde4e76d33156f9a0147ab820";
		public static final String ANDRONID_USER_CERTIFICATE="/usr/certificate/pointmaster/user/apiclient_cert.p12";
		/** 微信支付地址  */ 
		public static final String PLACE = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	}
}
