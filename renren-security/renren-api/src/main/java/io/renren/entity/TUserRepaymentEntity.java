package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 19:16:10
 */
public class TUserRepaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String userId;
	//支付方式
	private String paymentMethod;
	//支付状态
	private String status;
	//交易号
	private String paymentNo;
	//交易时间
	private Date paymentTime;
	//会员等级
	private Integer mermberLevelId;
	//开通时间,季度为单位
	private String mermberTime;
	//金额单位：分
	private int amountMoney;

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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * 获取：
	 */
	public String getPaymentMethod() {
		return paymentMethod;
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
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	/**
	 * 获取：
	 */
	public String getPaymentNo() {
		return paymentNo;
	}
	/**
	 * 设置：
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	/**
	 * 获取：
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}
	/**
	 * 设置：
	 */
	public void setMermberLevelId(Integer mermberLevelId) {
		this.mermberLevelId = mermberLevelId;
	}
	/**
	 * 获取：
	 */
	public Integer getMermberLevelId() {
		return mermberLevelId;
	}
	/**
	 * 设置：
	 */
	public void setMermberTime(String mermberTime) {
		this.mermberTime = mermberTime;
	}
	/**
	 * 获取：
	 */
	public String getMermberTime() {
		return mermberTime;
	}
	public int getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(int amountMoney) {
		this.amountMoney = amountMoney;
	}
}
