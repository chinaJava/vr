package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-02-11 15:00:35
 */
public class TVideoInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String title;
	//
	private String remark;
	//
	private String videoUrl;
	//
	private String status;
	//
	private Date createtime;
	//
	private String creator;

	private Integer duration;

	private String videoPicPath;

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
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置：
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
	 * 获取：
	 */
	public String getVideoUrl() {
		return videoUrl;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getVideoPicPath() {
		return videoPicPath;
	}

	public void setVideoPicPath(String videoPicPath) {
		this.videoPicPath = videoPicPath;
	}

}
