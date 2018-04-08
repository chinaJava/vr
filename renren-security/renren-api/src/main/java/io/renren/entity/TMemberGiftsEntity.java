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
public class TMemberGiftsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//礼包标题
	private String title;
	//logo地址
	private String logoUrl;
	//礼包说明
	private String remark;
	//会员等级id
	private Integer memberLevelId;
	//
	private String status;
	//
	private Date createtime;
	//
	private String creator;
	//剩余数量
	private Integer amount;
	
	private Date giftsStartTime;
	
	private Date giftsEndTime;
	
	private String logoImgData;
	
	private String memberLevel;
	

	public String getLogoImgData() {
		return logoImgData;
	}
	public void setLogoImgData(String logoImgData) {
		this.logoImgData = logoImgData;
	}
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：礼包标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：礼包标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：logo地址
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取：logo地址
	 */
	public String getLogoUrl() {
		return logoUrl;
	}
	/**
	 * 设置：礼包说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：礼包说明
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：会员等级id
	 */
	public void setMemberLevelId(Integer memberLevelId) {
		this.memberLevelId = memberLevelId;
	}
	/**
	 * 获取：会员等级id
	 */
	public Integer getMemberLevelId() {
		return memberLevelId;
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
	 * 设置：剩余数量
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取：剩余数量
	 */
	public Integer getAmount() {
		return amount;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public Date getGiftsStartTime() {
		return giftsStartTime;
	}
	public void setGiftsStartTime(Date giftsStartTime) {
		this.giftsStartTime = giftsStartTime;
	}
	public Date getGiftsEndTime() {
		return giftsEndTime;
	}
	public void setGiftsEndTime(Date giftsEndTime) {
		this.giftsEndTime = giftsEndTime;
	}
	
}
