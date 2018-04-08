package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-23 11:58:00
 */
public class TAppuserFavoritesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String appuserId;
	//
	private Long gameId;
	//
	private Date createtime;
	//1:收藏;2推荐
	private String type;
	//游戏名字
	private String gameName;
	//用户手机号
	private String userMobile;
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
	 * 设置：
	 */
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	/**
	 * 获取：
	 */
	public Long getGameId() {
		return gameId;
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
	 * 设置：1:收藏;2推荐
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：1:收藏;2推荐
	 */
	public String getType() {
		return type;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
}
