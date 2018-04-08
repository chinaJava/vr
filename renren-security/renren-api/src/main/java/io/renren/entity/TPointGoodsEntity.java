package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:30
 */
public class TPointGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//商品名称
	private String goodsName;
	//
	private String logoUrl;
	//
	private String status;
	//兑换积分
	private Integer exchangePoints;
	//总库存
	private Integer totalNum;
	//剩余库存
	private Integer remainNum;
	//
	private Date createtime;
	
	private String remark;
	
	private String logoImgData;

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
	 * 设置：商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取：
	 */
	public String getLogoUrl() {
		return logoUrl;
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
	 * 设置：兑换积分
	 */
	public void setExchangePoints(Integer exchangePoints) {
		this.exchangePoints = exchangePoints;
	}
	/**
	 * 获取：兑换积分
	 */
	public Integer getExchangePoints() {
		return exchangePoints;
	}
	/**
	 * 设置：总库存
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 获取：总库存
	 */
	public Integer getTotalNum() {
		return totalNum;
	}
	/**
	 * 设置：剩余库存
	 */
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}
	/**
	 * 获取：剩余库存
	 */
	public Integer getRemainNum() {
		return remainNum;
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
	public String getLogoImgData() {
		return logoImgData;
	}
	public void setLogoImgData(String logoImgData) {
		this.logoImgData = logoImgData;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
