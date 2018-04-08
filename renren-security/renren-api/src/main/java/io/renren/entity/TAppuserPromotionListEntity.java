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
public class TAppuserPromotionListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String appuserId;
	//
	private String promotionUserid;
	//0推广名单1有效推广名单
	private String status;
	//累计获得积分
	private Integer gainPoints;
	
	private String userMobile;
	
	private String promotionMobile;
	
	private String promotionName;
	
	private String promotionHeadPic;
	
	private String promotionMemberLevelId;
	
	private String memberLevel;
	
	private Integer pointTotal;
	
	private Integer promotionNum;
	
	private Date memberEndTime;
	
	private Integer point;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppuserId() {
		return appuserId;
	}

	public void setAppuserId(String appuserId) {
		this.appuserId = appuserId;
	}

	public String getPromotionUserid() {
		return promotionUserid;
	}

	public void setPromotionUserid(String promotionUserid) {
		this.promotionUserid = promotionUserid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getGainPoints() {
		return gainPoints;
	}

	public void setGainPoints(Integer gainPoints) {
		this.gainPoints = gainPoints;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getPromotionMobile() {
		return promotionMobile;
	}

	public void setPromotionMobile(String promotionMobile) {
		this.promotionMobile = promotionMobile;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromotionHeadPic() {
		return promotionHeadPic;
	}

	public void setPromotionHeadPic(String promotionHeadPic) {
		this.promotionHeadPic = promotionHeadPic;
	}

	public String getPromotionMemberLevelId() {
		return promotionMemberLevelId;
	}

	public void setPromotionMemberLevelId(String promotionMemberLevelId) {
		this.promotionMemberLevelId = promotionMemberLevelId;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Integer getPointTotal() {
		return pointTotal;
	}

	public void setPointTotal(Integer pointTotal) {
		this.pointTotal = pointTotal;
	}

	public Integer getPromotionNum() {
		return promotionNum;
	}

	public void setPromotionNum(Integer promotionNum) {
		this.promotionNum = promotionNum;
	}

	public Date getMemberEndTime() {
		return memberEndTime;
	}

	public void setMemberEndTime(Date memberEndTime) {
		this.memberEndTime = memberEndTime;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
}
