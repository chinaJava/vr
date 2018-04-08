package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TGameArticleDao;
import io.renren.entity.TGameArticleEntity;
import io.renren.service.TGameArticleService;



@Service("tGameArticleService")
public class TGameArticleServiceImpl implements TGameArticleService {
	@Autowired
	private TGameArticleDao tGameArticleDao;
	
	@Override
	public TGameArticleEntity queryObject(Long id){
		return tGameArticleDao.queryObject(id);
	}
	
	@Override
	public List<TGameArticleEntity> queryList(Map<String, Object> map){
		return tGameArticleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameArticleDao.queryTotal(map);
	}
	
	@Override
	public void save(TGameArticleEntity tGameArticle){
		tGameArticleDao.save(tGameArticle);
	}
	
	@Override
	public void update(TGameArticleEntity tGameArticle){
		tGameArticleDao.update(tGameArticle);
	}
	
	@Override
	public void delete(Long id){
		tGameArticleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tGameArticleDao.deleteBatch(ids);
	}

	@Override
	public List<TGameArticleEntity> queryListByGame(Map<String, Object> map) {
		return tGameArticleDao.queryListByGame(map);
	}
	
}
