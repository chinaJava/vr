package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 收藏表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
public class TCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long collectionId;
	//帖子ID
	private Long invitationId;
	//用户ID
	private String userId;
	//收藏时间
	private Date createTime;
	//状态
	private Integer state;
	//发布公告
	private TInvitationEntity tInvitationEntitie;
	
	public TInvitationEntity gettInvitationEntitie() {
		return tInvitationEntitie;
	}
	
	public void settInvitationEntitie(TInvitationEntity tInvitationEntitie) {
		this.tInvitationEntitie = tInvitationEntitie;
	}
	
	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}
	/**
	 * 获取：id
	 */
	public Long getCollectionId() {
		return collectionId;
	}
	/**
	 * 设置：帖子ID
	 */
	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}
	/**
	 * 获取：帖子ID
	 */
	public Long getInvitationId() {
		return invitationId;
	}
	
	/**
	 * 获取：用户ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 设置：收藏时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：收藏时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public Integer getState() {
		return state;
	}
}
