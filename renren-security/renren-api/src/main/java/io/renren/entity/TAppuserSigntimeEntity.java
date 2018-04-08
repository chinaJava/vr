package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 15:21:44
 */
public class TAppuserSigntimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	//
	private String appuserId;
	//
	private Date signTime;
	//临时变量
	private StringBuffer signhistroy;
	//临时变量
	private boolean status;
	//临时变量
	private Integer signcount;
	//用户名
	private String username;
	
	public Integer getSigncount() {
		return signcount;
	}  
	public void setSigncount(Integer signcount) {
		this.signcount = signcount;
	}
	public StringBuffer getSignhistroy() {
		return signhistroy;
	}
	public void setSignhistroy(StringBuffer signhistroy) {
		this.signhistroy = signhistroy;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setAppuserId(String appuserId) {
		this.appuserId = appuserId;
	}
	/**
	 * 获取：
	 */
	public String getAppuserId() {
		return appuserId;
	}
	/**
	 * 设置：
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	/**
	 * 获取：
	 */
	public Date getSignTime() {
		return signTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
