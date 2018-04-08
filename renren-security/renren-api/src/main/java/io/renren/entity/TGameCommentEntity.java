package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public class TGameCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long gameid;
	//
	private String appuserid;
	//
	private String comment;
	//
	private Date createtime;
	//
	private Float starLevel;
	//
	private Long pid;
	private Integer status;
	private String realname;
	private String headPic;
	private Integer isRecommend;
	private String gameName;
	private String userName;
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
	public void setAppuserid(String appuserid) {
		this.appuserid = appuserid;
	}
	/**
	 * 获取：
	 */
	public String getAppuserid() {
		return appuserid;
	}
	/**
	 * 设置：
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setStarLevel(Float starLevel) {
		this.starLevel = starLevel;
	}
	/**
	 * 获取：
	 */
	public Float getStarLevel() {
		return starLevel;
	}
	/**
	 * 设置：
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public Long getPid() {
		return pid;
	}

	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
