package io.renren.entity;

public class TGameRecommentEntity {
	private Long gameId;
	
	private Long favoritesId;
	
	private String gameName;
	
	private String gameType ;
	
	private String logoUrl;

	private String  recommender;
	
	private int isRecommend;
	
	private String headPic;
	
	private String content;
	
	private String version;
	
	private String downloadingAndroidUrl;
	
	private String downloadingIosUrl;
	
	private String packageNameIos;
	
	private String gameLogoUrl;
	
	public String getGameLogoUrl() {
		return gameLogoUrl;
	}

	public void setGameLogoUrl(String gameLogoUrl) {
		this.gameLogoUrl = gameLogoUrl;
	}

	private String gameUrl;
	
	public String getGameUrl() {
		return gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	

	public String getDownloadingIosUrl() {
		return downloadingIosUrl;
	}

	public void setDownloadingIosUrl(String downloadingIosUrl) {
		this.downloadingIosUrl = downloadingIosUrl;
	}

	public String getPackageNameIos() {
		return packageNameIos;
	}

	public void setPackageNameIos(String packageNameIos) {
		this.packageNameIos = packageNameIos;
	}

	private String packageNameAndroid;
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDownloadingAndroidUrl() {
		return downloadingAndroidUrl;
	}

	public void setDownloadingAndroidUrl(String downloadingAndroidUrl) {
		this.downloadingAndroidUrl = downloadingAndroidUrl;
	}

	public String getPackageNameAndroid() {
		return packageNameAndroid;
	}

	public void setPackageNameAndroid(String packageNameAndroid) {
		this.packageNameAndroid = packageNameAndroid;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGamename(String gameName) {
		this.gameName = gameName;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public Long getFavoritesId() {
		return favoritesId;
	}

	public void setFavoritesId(Long favoritesId) {
		this.favoritesId = favoritesId;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	
}
