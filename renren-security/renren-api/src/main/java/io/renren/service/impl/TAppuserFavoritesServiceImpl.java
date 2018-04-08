package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserFavoritesDao;
import io.renren.entity.TAppuserFavoritesEntity;
import io.renren.service.TAppuserFavoritesService;
import io.renren.utils.Query;



@Service("tAppuserFavoritesService")
public class TAppuserFavoritesServiceImpl implements TAppuserFavoritesService {
	@Autowired
	private TAppuserFavoritesDao tAppuserFavoritesDao;
	
	@Override
	public TAppuserFavoritesEntity queryObject(Long id){
		return tAppuserFavoritesDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserFavoritesEntity> queryList(Map<String, Object> map){
		return tAppuserFavoritesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserFavoritesDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserFavoritesEntity tAppuserFavorites){
		tAppuserFavoritesDao.save(tAppuserFavorites);
	}
	
	@Override
	public void update(TAppuserFavoritesEntity tAppuserFavorites){
		tAppuserFavoritesDao.update(tAppuserFavorites);
	}
	
	@Override
	public void delete(Long id){
		tAppuserFavoritesDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserFavoritesDao.deleteBatch(ids);
	}

	@Override
	public TAppuserFavoritesEntity queryObjectByAppuserIdAndGameId(String appuserId,long gameId) {
	
		return tAppuserFavoritesDao.queryObjectByAppuserIdAndGameId(appuserId,gameId);
	}

	@Override
	public List<TAppuserFavoritesEntity> queryRecommend() {
		
		return tAppuserFavoritesDao.queryRecommend();
	}

	

	@Override
	public int queryNumByGameIdAndAppuserId(String appuserId,long gameId) {
		// TODO Auto-generated method stub
		return tAppuserFavoritesDao.queryNumByGameIdAndAppuserId(appuserId, gameId);
	}

	@Override
	public List<TAppuserFavoritesEntity> queryListByGameId(Query query) {
		// TODO Auto-generated method stub
		return tAppuserFavoritesDao.queryListByGameId( query);
	}
	
}
