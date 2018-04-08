package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 09:37:57
 */
public class TAppuserGiftsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String appuserId;
	//礼包ID
	private Integer giftsId;
	//领取时间
	private Date createtime;
	
	private String userMobile;
	
	private String giftsTitle;

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
	 * 设置：礼包ID
	 */
	public void setGiftsId(Integer giftsId) {
		this.giftsId = giftsId;
	}
	/**
	 * 获取：礼包ID
	 */
	public Integer getGiftsId() {
		return giftsId;
	}
	/**
	 * 设置：领取时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：领取时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getGiftsTitle() {
		return giftsTitle;
	}
	public void setGiftsTitle(String giftsTitle) {
		this.giftsTitle = giftsTitle;
	}
	@Override
	public String toString() {
		return "TAppuserGiftsEntity [id=" + id + ", appuserId=" + appuserId + ", giftsId=" + giftsId + ", createtime="
				+ createtime + ", userMobile=" + userMobile + ", giftsTitle=" + giftsTitle + "]";
	}
	
}
