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
public class TAppuserDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String appuserid;
	//
	private String nikename;
	//
	private String realname;
	//
	private String sex;
	//
	private Date birthday;
	
	private String birthdayStr;
	//
	private String headPic;
	//
	private String city;
	//当前积分数
	private Integer point;
	//
	private String usercode;
	
	private String mobile;
	
	private String username;
	//游戏金币
	private Integer gameGold;
	//会员登录ID
	private Integer memberLevelId;
	//会员等级
	private String memberLevel;
	//会员开始时间
	private Date memberStartTime;
	//会员到期时间
	private Date memberEndTime;
	//总积分数
	private Integer pointTotal;
	

	public Integer getGameGold() {
		return gameGold;
	}
	public void setGameGold(Integer gameGold) {
		this.gameGold = gameGold;
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
	public void setAppuserid(String appuserid) {
		this.appuserid = appuserid;
	}
	/**
	 * 获取：
	 */
	public String getAppuserid() {
		return appuserid;
	}
	/**
	 * 设置：
	 */
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	/**
	 * 获取：
	 */
	public String getNikename() {
		return nikename;
	}
	/**
	 * 设置：
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	/**
	 * 获取：
	 */
	public String getHeadPic() {
		return headPic;
	}
	/**
	 * 设置：
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}
	/**
	 * 获取：
	 */
	public Integer getPoint() {
		return point;
	}
	/**
	 * 设置：
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	/**
	 * 获取：
	 */
	public String getUsercode() {
		return usercode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getMemberLevelId() {
		return memberLevelId;
	}
	public void setMemberLevelId(Integer memberLevelId) {
		this.memberLevelId = memberLevelId;
	}
	public Date getMemberStartTime() {
		return memberStartTime;
	}
	public void setMemberStartTime(Date memberStartTime) {
		this.memberStartTime = memberStartTime;
	}
	public Date getMemberEndTime() {
		return memberEndTime;
	}
	public void setMemberEndTime(Date memberEndTime) {
		this.memberEndTime = memberEndTime;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}
	public Integer getPointTotal() {
		return pointTotal;
	}
	public void setPointTotal(Integer pointTotal) {
		this.pointTotal = pointTotal;
	}
}
