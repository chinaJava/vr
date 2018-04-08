package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 回帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public class TInvitationAnswerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// id
	private Long answerId;
	// 回复帖子ID
	@NotNull
	private Long invitationId;
	// 回复内容
	@NotNull
	private String answerContent;
	// 回复作者
	private String userId;
	// 回复时间
	private Date answerTime;
	// 父类ID
	private Long parentId;

	private int state;

	private String coverAnswerUserName;

	private String nikeName;

	private String sex;

	private String headPic;

	private int answerNum;

	private String invitationContent;

	/**
	 * 设置：id
	 */
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	/**
	 * 获取：id
	 */
	public Long getAnswerId() {
		return answerId;
	}

	/**
	 * 设置：回复帖子ID
	 */
	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	/**
	 * 获取：回复帖子ID
	 */
	public Long getInvitationId() {
		return invitationId;
	}

	/**
	 * 设置：回复内容
	 */
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	/**
	 * 获取：回复内容
	 */
	public String getAnswerContent() {
		return answerContent;
	}

	/**
	 * 设置：回复时间
	 */
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	/**
	 * 获取：回复时间
	 */
	public Date getAnswerTime() {
		return answerTime;
	}

	/**
	 * 设置：父类ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：父类ID
	 */
	public Long getParentId() {
		return parentId;
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

	public String getCoverAnswerUserName() {
		return coverAnswerUserName;
	}

	public void setCoverAnswerUserName(String coverAnswerUserName) {
		this.coverAnswerUserName = coverAnswerUserName;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public String getInvitationContent() {
		return invitationContent;
	}

	public void setInvitationContent(String invitationContent) {
		this.invitationContent = invitationContent;
	}

}
