package io.renren.service;

import io.renren.entity.TAppSuggestEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-09 14:18:56
 */
public interface TAppSuggestService {
	
	TAppSuggestEntity queryObject(Integer id);
	
	List<TAppSuggestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppSuggestEntity tAppSuggest);
	
	void update(TAppSuggestEntity tAppSuggest);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
