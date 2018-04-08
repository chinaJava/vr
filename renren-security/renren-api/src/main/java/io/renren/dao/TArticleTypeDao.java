package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.TArticleTypeEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TArticleTypeDao extends BaseDao<TArticleTypeEntity> {
	
	List<TArticleTypeEntity> queryParentList(Map<String, Object> map);
	
	List<TArticleTypeEntity> queryListByPid(Map<String, Object> map);
	
	List<TArticleTypeEntity> queryAllObject();
	
}
