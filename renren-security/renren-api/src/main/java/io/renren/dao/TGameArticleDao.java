package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.TGameArticleEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameArticleDao extends BaseDao<TGameArticleEntity> {
	
	List<TGameArticleEntity> queryListByGame(Map<String, Object> map);
	
}
