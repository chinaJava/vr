package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 09:54:19
 */
public class TAppuserTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long taskId;
	//
	private String appuserId;
	//
	private String status;
	//
	private Date receiveTime;
	//
	private Date finishTime;
	//
	private String remark;
	
	private String taskName;
	
	private String userMobile;
	
	private String finishTaskPic;

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
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：
	 */
	public Long getTaskId() {
		return taskId;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * 获取：
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getFinishTaskPic() {
		return finishTaskPic;
	}
	public void setFinishTaskPic(String finishTaskPic) {
		this.finishTaskPic = finishTaskPic;
	}
	
}
