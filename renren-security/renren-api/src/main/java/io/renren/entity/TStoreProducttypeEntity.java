package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-01 14:52:58
 */
public class TStoreProducttypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//
	private String name;
	//
	private String proType;
	//
	private String creator;
	//
	private String status;

	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 设置：
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public int getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setProType(String proType) {
		this.proType = proType;
	}
	/**
	 * 获取：
	 */
	public String getProType() {
		return proType;
	}
}
