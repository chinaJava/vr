package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public class TGameArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String title;
	//
	private String subheading;
	//
	private String creator;
	//
	private Date createtime;
	//
	private Integer pageViews;
	//
	private String content;
	//
	private Integer typeid;
	
	private String typeName;
	
	private Long gameid;
	
	private String gameName;
	//
	private String picUrl;
	
	private String author;

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
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setSubheading(String subheading) {
		this.subheading = subheading;
	}
	/**
	 * 获取：
	 */
	public String getSubheading() {
		return subheading;
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
	public void setPageViews(Integer pageViews) {
		this.pageViews = pageViews;
	}
	/**
	 * 获取：
	 */
	public Integer getPageViews() {
		return pageViews;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：
	 */
	public Integer getTypeid() {
		return typeid;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getGameid() {
		return gameid;
	}
	public void setGameid(Long gameid) {
		this.gameid = gameid;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
}
