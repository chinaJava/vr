package io.renren.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 发帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public class TInvitationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// id
	private Long invitationId;
	// 标题
	private String title;
	// 内容
	@NotNull
	private String content;
	// 发帖作者
	private String userId;
	// 发帖时间
	private Date sendTime;

	private int state;

	private TInvitationAnswerEntity tAnswerEntities;
	// 型号
	private String model;

	private String picPath;
	// 图片上传字节流
	private String picPathData;
	// 点赞数
	private Long likesNum;

	private String nikeName;

	private String sex;

	private String headPic;

	private Integer answerNum;

	private int likesState;

	/**
	 * 设置：id
	 */
	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	/**
	 * 获取：id
	 */
	public Long getInvitationId() {
		return invitationId;
	}

	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置：发帖时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取：发帖时间
	 */
	public Date getSendTime() {
		return sendTime;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getPicPathData() {
		return picPathData;
	}

	public void setPicPathData(String picPathData) {
		this.picPathData = picPathData;
	}

	public Long getLikesNum() {
		return likesNum;
	}

	public void setLikesNum(Long likesNum) {
		this.likesNum = likesNum;
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

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public TInvitationAnswerEntity gettAnswerEntities() {
		return tAnswerEntities;
	}

	public void settAnswerEntities(TInvitationAnswerEntity tAnswerEntities) {
		this.tAnswerEntities = tAnswerEntities;
	}

	public int getLikesState() {
		return likesState;
	}

	public void setLikesState(int likesState) {
		this.likesState = likesState;
	}

}
