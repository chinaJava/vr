package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-25 17:28:03
 */
public class TGameStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String name;
	//
	private Integer gameGold;
	//
	private String productUrl;
	//
	private Long productValue;
	//
	private int productType;
	//
	private String creator;
	//
	private String status;
	
	private int inventory;
	
	private String detail;
	
	private String productTypeName;
	private String imgData;
	
	public String getImgData() {
		return imgData;
	}
	public void setImgData(String imgData) {
		this.imgData = imgData;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	public Integer getGameGold() {
		return gameGold;
	}
	public void setGameGold(Integer gameGold) {
		this.gameGold = gameGold;
	}
	/**
	 * 设置：
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * 获取：
	 */
	public String getProductUrl() {
		return productUrl;
	}
	public Long getProductValue() {
		return productValue;
	}
	public void setProductValue(Long productValue) {
		this.productValue = productValue;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
