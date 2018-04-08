package io.renren.service;

import io.renren.entity.TArticleTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TArticleTypeService {
	
	TArticleTypeEntity queryObject(Integer id);
	
	List<TArticleTypeEntity> queryList(Map<String, Object> map);
	
	List<TArticleTypeEntity> queryParentList(Map<String, Object> map);
	
	List<TArticleTypeEntity> queryListByPid(Map<String, Object> map);
	
	List<TArticleTypeEntity> queryAllObject();
	
	int queryTotal(Map<String, Object> map);
	
	void save(TArticleTypeEntity tArticleType);
	
	void update(TArticleTypeEntity tArticleType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
