package io.renren.dao;

import java.util.List;

import io.renren.entity.TAppuserFavoritesEntity;
import io.renren.utils.Query;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-23 11:58:00
 */
public interface TAppuserFavoritesDao extends BaseDao<TAppuserFavoritesEntity> {
	TAppuserFavoritesEntity queryObjectByAppuserIdAndGameId(String appuserId,long gameId);
	
	int queryNumByGameIdAndAppuserId(String appuserId,long gameId);
	
	List<TAppuserFavoritesEntity> queryRecommend();
	
	List<TAppuserFavoritesEntity> queryListByGameId(Query query);
}
