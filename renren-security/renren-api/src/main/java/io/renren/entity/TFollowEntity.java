package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 关注表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public class TFollowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long followId;
	//关注用户ID
	private String followUserId;
	//用户ID
	private String userId;
	//状态
	private int state;
	//创建时间
	private Date createTime;
	
	private TAppuserDetailEntity tAppuserDetailEntity;

	/**
	 * 设置：ID
	 */
	public void setFollowId(Long followId) {
		this.followId = followId;
	}
	/**
	 * 获取：ID
	 */
	public Long getFollowId() {
		return followId;
	}
	
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(String followUserId) {
		this.followUserId = followUserId;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public TAppuserDetailEntity gettAppuserDetailEntity() {
		return tAppuserDetailEntity;
	}
	public void settAppuserDetailEntity(TAppuserDetailEntity tAppuserDetailEntity) {
		this.tAppuserDetailEntity = tAppuserDetailEntity;
	}
	
	
}
