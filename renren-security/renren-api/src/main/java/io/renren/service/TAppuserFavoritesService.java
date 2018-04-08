package io.renren.service;

import io.renren.entity.TAppuserFavoritesEntity;
import io.renren.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-23 11:58:00
 */
public interface TAppuserFavoritesService {
	
	TAppuserFavoritesEntity queryObject(Long id);
	
	TAppuserFavoritesEntity queryObjectByAppuserIdAndGameId(String appuserId,long gameId);
	
	List<TAppuserFavoritesEntity> queryList(Map<String, Object> map);
	
	List<TAppuserFavoritesEntity> queryRecommend();
	
	List<TAppuserFavoritesEntity> queryListByGameId(Query query);
	
	int queryNumByGameIdAndAppuserId(String appuserId,long gameId);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserFavoritesEntity tAppuserFavorites);
	
	void update(TAppuserFavoritesEntity tAppuserFavorites);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
