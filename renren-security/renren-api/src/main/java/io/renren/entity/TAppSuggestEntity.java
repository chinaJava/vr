package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-09 14:18:56
 */
public class TAppSuggestEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String suggest;
	//
	private String picUrl;
	//
	private String contact;
	
	private String picData;

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
	 * 设置：
	 */
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	/**
	 * 获取：
	 */
	public String getSuggest() {
		return suggest;
	}
	/**
	 * 设置：
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取：
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * 设置：
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取：
	 */
	public String getContact() {
		return contact;
	}
	public String getPicData() {
		return picData;
	}
	public void setPicData(String picData) {
		this.picData = picData;
	}
	
}
