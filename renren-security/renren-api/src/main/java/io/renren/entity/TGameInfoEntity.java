package io.renren.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public class TGameInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	//
	private String name;
	//
	private Long sizeIos;

	private Long sizeAndroid;
	// 简介
	private String summary;
	// 是否收费
	private String ischarge;
	// 安卓下载量
	private Long downloadingAndroidNum;
	//
	private Integer typeid;

	private String typeName;
	//
	private String logoUrl;
	// 保存游戏图标二进制流数据
	private String logoImgData;
	//
	private String versionIos;

	private String versionAndroid;
	// 游戏介绍
	private String introduce;
	//
	private String language;
	// 更新内容提醒
	private String updateContent;
	//
	private Float starLevel;
	// 苹果下载地址
	private String downloadingIosUrl;
	// 安卓下载地址
	private String downloadingAndroidUrl;
	// 苹果下载量
	private Long downloadingIosNum;

	private String gameUrl;

	private String gameVideoUrl;
	private String gameLable;
	// 是否推荐游戏1：推荐 0 不推荐
	private Integer isRecommend;
	// 0:下架，1：上架
	private Integer status;
	// 保存游戏图片的二进制流数据
	private String imgData;
	// 保存游戏图片的URL地址
	private String gamePictureUrl;
	// 是否热门游戏 1：热门 0：非热门
	private String isHotGame;
	// 是否精品游戏 1：精品 0：其他
	private String isBoutique;
	// 是否新品游戏1：是 0：否
	private String isNewGame;

	private String packageNameIos;

	private String packageNameAndroid;

	private String gameUrlH5;

	private String gameChannel;
	// 下载所需积分
	private Long downloadPoints;

	private List<TGameCommentEntity> tGameCommentEntities;

	private int commentTotal;

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

	/**
	 * 设置：简介
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * 获取：简介
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * 设置：是否收费
	 */
	public void setIscharge(String ischarge) {
		this.ischarge = ischarge;
	}

	/**
	 * 获取：是否收费
	 */
	public String getIscharge() {
		return ischarge;
	}

	/**
	 * 设置：安卓下载量
	 */
	public void setDownloadingAndroidNum(Long downloadingAndroidNum) {
		this.downloadingAndroidNum = downloadingAndroidNum;
	}

	/**
	 * 获取：安卓下载量
	 */
	public Long getDownloadingAndroidNum() {
		return downloadingAndroidNum;
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
	 * 设置：游戏介绍
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * 获取：游戏介绍
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * 设置：
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获取：
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置：更新内容提醒
	 */
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	/**
	 * 获取：更新内容提醒
	 */
	public String getUpdateContent() {
		return updateContent;
	}

	/**
	 * 设置：
	 */
	public void setStarLevel(Float starLevel) {
		this.starLevel = starLevel;
	}

	/**
	 * 获取：
	 */
	public Float getStarLevel() {
		return starLevel;
	}

	/**
	 * 设置：苹果下载地址
	 */
	public void setDownloadingIosUrl(String downloadingIosUrl) {
		this.downloadingIosUrl = downloadingIosUrl;
	}

	/**
	 * 获取：苹果下载地址
	 */
	public String getDownloadingIosUrl() {
		return downloadingIosUrl;
	}

	/**
	 * 设置：安卓下载地址
	 */
	public void setDownloadingAndroidUrl(String downloadingAndroidUrl) {
		this.downloadingAndroidUrl = downloadingAndroidUrl;
	}

	/**
	 * 获取：安卓下载地址
	 */
	public String getDownloadingAndroidUrl() {
		return downloadingAndroidUrl;
	}

	/**
	 * 设置：苹果下载量
	 */
	public void setDownloadingIosNum(Long downloadingIosNum) {
		this.downloadingIosNum = downloadingIosNum;
	}

	/**
	 * 获取：苹果下载量
	 */
	public Long getDownloadingIosNum() {
		return downloadingIosNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getGameUrl() {
		return gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	public String getGameVideoUrl() {
		return gameVideoUrl;
	}

	public void setGameVideoUrl(String gameVideoUrl) {
		this.gameVideoUrl = gameVideoUrl;
	}

	public String getLogoImgData() {
		return logoImgData;
	}

	public void setLogoImgData(String logoImgData) {
		this.logoImgData = logoImgData;
	}

	public String getGameLable() {
		return gameLable;
	}

	public void setGameLable(String gameLable) {
		this.gameLable = gameLable;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}

	public String getGamePictureUrl() {
		return gamePictureUrl;
	}

	public void setGamePictureUrl(String gamePictureUrl) {
		this.gamePictureUrl = gamePictureUrl;
	}

	public String getIsHotGame() {
		return isHotGame;
	}

	public void setIsHotGame(String isHotGame) {
		this.isHotGame = isHotGame;
	}

	public String getIsBoutique() {
		return isBoutique;
	}

	public void setIsBoutique(String isBoutique) {
		this.isBoutique = isBoutique;
	}

	public String getIsNewGame() {
		return isNewGame;
	}

	public void setIsNewGame(String isNewGame) {
		this.isNewGame = isNewGame;
	}

	public String getPackageNameIos() {
		return packageNameIos;
	}

	public void setPackageNameIos(String packageNameIos) {
		this.packageNameIos = packageNameIos;
	}

	public String getPackageNameAndroid() {
		return packageNameAndroid;
	}

	public void setPackageNameAndroid(String packageNameAndroid) {
		this.packageNameAndroid = packageNameAndroid;
	}

	public String getGameUrlH5() {
		return gameUrlH5;
	}

	public void setGameUrlH5(String gameUrlH5) {
		this.gameUrlH5 = gameUrlH5;
	}

	public Long getSizeIos() {
		return sizeIos;
	}

	public void setSizeIos(Long sizeIos) {
		this.sizeIos = sizeIos;
	}

	public Long getSizeAndroid() {
		return sizeAndroid;
	}

	public void setSizeAndroid(Long sizeAndroid) {
		this.sizeAndroid = sizeAndroid;
	}

	public String getVersionIos() {
		return versionIos;
	}

	public void setVersionIos(String versionIos) {
		this.versionIos = versionIos;
	}

	public String getVersionAndroid() {
		return versionAndroid;
	}

	public void setVersionAndroid(String versionAndroid) {
		this.versionAndroid = versionAndroid;
	}

	public String getGameChannel() {
		return gameChannel;
	}

	public void setGameChannel(String gameChannel) {
		this.gameChannel = gameChannel;
	}

	public Long getDownloadPoints() {
		return downloadPoints;
	}

	public void setDownloadPoints(Long downloadPoints) {
		this.downloadPoints = downloadPoints;
	}

	public List<TGameCommentEntity> gettGameCommentEntities() {
		return tGameCommentEntities;
	}

	public void settGameCommentEntities(List<TGameCommentEntity> tGameCommentEntities) {
		this.tGameCommentEntities = tGameCommentEntities;
	}

	public int getCommentTotal() {
		return commentTotal;
	}

	public void setCommentTotal(int commentTotal) {
		this.commentTotal = commentTotal;
	}

}
