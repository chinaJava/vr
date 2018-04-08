package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-14 10:23:23
 */
public class TAppuserMemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//
	private String appuserId;
	//
	private String memberLevel;
	//
	private Date startTime;
	//
	private Date endTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
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
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	/**
	 * 获取：
	 */
	public String getMemberLevel() {
		return memberLevel;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
	}
}
