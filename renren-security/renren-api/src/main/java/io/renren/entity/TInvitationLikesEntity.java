package io.renren.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 点赞表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
public class TInvitationLikesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// id
	private Long likeId;
	// 回复ID
	private Long invitationId;
	// 用户ID
	private String userId;
	// 创建时间
	private Date createTime;
	// 状态
	private Integer state;

	private TInvitationAnswerEntity tAnswerEntitie;

	public Long getLikeId() {
		return likeId;
	}

	public void setLikeId(Long likeId) {
		this.likeId = likeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	public TInvitationAnswerEntity gettAnswerEntitie() {
		return tAnswerEntitie;
	}

	public void settAnswerEntitie(TInvitationAnswerEntity tAnswerEntitie) {
		this.tAnswerEntitie = tAnswerEntitie;
	}

	
}
