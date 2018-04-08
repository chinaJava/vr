package io.renren.entity;

import java.io.Serializable;
import java.util.Date;


/** 
 * 类名称: TAppuserEntity 手机用户注册登录表
 * 描述: TODO 
 * @create 2017年7月26日 上午9:45:30 
 * @author llh  
 * @version 1.0.0 
 */
public class TAppuserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String mobile;
	
	private String password;
	
	private Date createtime;
	//第三方注册登录ID
	private String openid;
	
	private String username;
	
	private String status;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
