package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 11:52:09
 */
public class TMemberInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private String code;
	//
	private Integer validNumber;
	//
	private Integer chargeQuarter;

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
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：
	 */
	public void setValidNumber(Integer validNumber) {
		this.validNumber = validNumber;
	}
	/**
	 * 获取：
	 */
	public Integer getValidNumber() {
		return validNumber;
	}
	/**
	 * 设置：
	 */
	public void setChargeQuarter(Integer chargeQuarter) {
		this.chargeQuarter = chargeQuarter;
	}
	/**
	 * 获取：
	 */
	public Integer getChargeQuarter() {
		return chargeQuarter;
	}
}
