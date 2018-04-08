package io.renren.service;

import io.renren.entity.TGameArticleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameArticleService {
	
	TGameArticleEntity queryObject(Long id);
	
	List<TGameArticleEntity> queryList(Map<String, Object> map);
	
	List<TGameArticleEntity> queryListByGame(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameArticleEntity tGameArticle);
	
	void update(TGameArticleEntity tGameArticle);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
