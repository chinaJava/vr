package io.renren.entity;

import java.io.Serializable;
import java.util.Date;


public class TGameTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long gameid;
	//
	private String title;
	//
	private Integer earnGold;
	//最后完成时间
	private Date deadline;
	//任务开始时间
	private Date beginTime;
	//
	private Date createTime;
	//
	private String creator;
	//
	private String status;
	//任务奖励份数
	private Integer restNumber;
	//任务难度
	private String difficult;
	//任务详情
	private String detail;
	//
	private String logoUrl;
	//
	private String taskType;
	
	private String logoImgData;
	
	private String gameName;
	
	private String gameIntroduce;
	
	private String myTaskStatus;

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
	public void setGameid(Long gameid) {
		this.gameid = gameid;
	}
	/**
	 * 获取：
	 */
	public Long getGameid() {
		return gameid;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setEarnGold(Integer earnGold) {
		this.earnGold = earnGold;
	}
	/**
	 * 获取：
	 */
	public Integer getEarnGold() {
		return earnGold;
	}
	/**
	 * 设置：最后完成时间
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * 获取：最后完成时间
	 */
	public Date getDeadline() {
		return deadline;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 获取：
	 */
	public String getCreator() {
		return creator;
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
	 * 设置：任务奖励份数
	 */
	public void setRestNumber(Integer restNumber) {
		this.restNumber = restNumber;
	}
	/**
	 * 获取：任务奖励份数
	 */
	public Integer getRestNumber() {
		return restNumber;
	}
	/**
	 * 设置：任务难度
	 */
	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}
	/**
	 * 获取：任务难度
	 */
	public String getDifficult() {
		return difficult;
	}
	/**
	 * 设置：任务详情
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取：任务详情
	 */
	public String getDetail() {
		return detail;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 设置：
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	/**
	 * 获取：
	 */
	public String getTaskType() {
		return taskType;
	}
	public String getLogoImgData() {
		return logoImgData;
	}
	public void setLogoImgData(String logoImgData) {
		this.logoImgData = logoImgData;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public String getGameIntroduce() {
		return gameIntroduce;
	}
	public void setGameIntroduce(String gameIntroduce) {
		this.gameIntroduce = gameIntroduce;
	}
	public String getMyTaskStatus() {
		return myTaskStatus;
	}
	public void setMyTaskStatus(String myTaskStatus) {
		this.myTaskStatus = myTaskStatus;
	}
}
