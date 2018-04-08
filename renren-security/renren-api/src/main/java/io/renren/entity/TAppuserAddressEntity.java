package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:20
 */
public class TAppuserAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String appuserid;
	//
	private String consiger;
	//
	private String telephone;
	//
	private String address;
	//
	private String areaCity;
	//
	private String status;
	
	private String mobile;
	
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
	public void setConsiger(String consiger) {
		this.consiger = consiger;
	}
	/**
	 * 获取：
	 */
	public String getConsiger() {
		return consiger;
	}
	/**
	 * 设置：
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setAreaCity(String areaCity) {
		this.areaCity = areaCity;
	}
	/**
	 * 获取：
	 */
	public String getAreaCity() {
		return areaCity;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
