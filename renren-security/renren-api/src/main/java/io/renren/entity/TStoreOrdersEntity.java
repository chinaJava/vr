package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-04 14:30:26
 */
public class TStoreOrdersEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String appuserId;
	//
	private Long productId;
	//
	private Date createtime;
	//
	private Long addressId;
	//订单状态：0未发货1已发货2确认签收
	private String status;
	//
	private Integer productAmount;
	
	private String userMobile;
	
	private String productName;
	
	private String contactPerson;
	
	private String contactPhone;
	
	private String addressDetail;

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
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * 获取：
	 */
	public Long getProductId() {
		return productId;
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
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * 获取：
	 */
	public Integer getProductAmount() {
		return productAmount;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
}
