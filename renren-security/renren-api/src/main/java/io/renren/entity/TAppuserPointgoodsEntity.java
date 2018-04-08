package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:29
 */
public class TAppuserPointgoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private String appuserId;
	//积分商品ID
	private Integer goodsId;
	//
	private String status;
	
	private Date createtime;
	
	private String remark;
	
	private String goodsName;
	
	private String userMobile;

	private Integer exchangePoints;
	
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
	 * 设置：用户ID
	 */
	public void setAppuserId(String appuserId) {
		this.appuserId = appuserId;
	}
	/**
	 * 获取：用户ID
	 */
	public String getAppuserId() {
		return appuserId;
	}
	/**
	 * 设置：积分商品ID
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：积分商品ID
	 */
	public Integer getGoodsId() {
		return goodsId;
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
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public Integer getExchangePoints() {
		return exchangePoints;
	}
	public void setExchangePoints(Integer exchangePoints) {
		this.exchangePoints = exchangePoints;
	}
}
