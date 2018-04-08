package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 13:49:33
 */
public class TAppuserSignEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	//
	private String appuserId;
	//签到日期
	private Date signTime;
	//
	private Integer signCount;
	//
	private Integer signAllCount;
	//
	private Date lastSignDate;
	//临时变量
	private StringBuffer signhistroy;
	//临时变量
	private boolean status;
	//用户名
	private String username;
	//当天签到人数
	private int signCountToday;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public StringBuffer getSignhistroy() {
		return signhistroy;
	}
	public void setSignhistroy(StringBuffer signhistroy) {
		this.signhistroy = signhistroy;
	}
	public Date getLastSignDate() {
		return lastSignDate;
	}
	public void setLastSignDate(Date lastSignDate) {
		this.lastSignDate = lastSignDate;
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
	 * 设置：签到日期
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	/**
	 * 获取：签到日期
	 */
	public Date getSignTime() {
		return signTime;
	}
	/**
	 * 设置：
	 */
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	/**
	 * 获取：
	 */
	public Integer getSignCount() {
		return signCount;
	}
	/**
	 * 设置：
	 */
	public void setSignAllCount(Integer signAllCount) {
		this.signAllCount = signAllCount;
	}
	/**
	 * 获取：
	 */
	public Integer getSignAllCount() {
		return signAllCount;
	}
	/**
	 * 设置：
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSignCountToday() {
		return signCountToday;
	}
	public void setSignCountToday(int signCountToday) {
		this.signCountToday = signCountToday;
	}

}
