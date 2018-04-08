package io.renren.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-08-16 16:30:59
 */
public class THomeImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String path;
	//图片路径
	private String picUrl;
	//是否显示
	private String status;
	//图片所属页面类型：AR游戏、VR游戏、独立游戏、推荐页
	private int typeId;
	//是否banner图
	private String isBanner;
	//图片展示顺序
	private int index;
	
	private String typeName;
	
	private String imgData;
	
	private String skipToArticle;
	
	private String skipToGame;

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
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：
	 */
	public String getPath() {
		return path;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getIsBanner() {
		return isBanner;
	}
	public void setIsBanner(String isBanner) {
		this.isBanner = isBanner;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getImgData() {
		return imgData;
	}
	public void setImgData(String imgData) {
		this.imgData = imgData;
	}
	public String getSkipToArticle() {
		return skipToArticle;
	}
	public void setSkipToArticle(String skipToArticle) {
		this.skipToArticle = skipToArticle;
	}
	public String getSkipToGame() {
		return skipToGame;
	}
	public void setSkipToGame(String skipToGame) {
		this.skipToGame = skipToGame;
	}
}
