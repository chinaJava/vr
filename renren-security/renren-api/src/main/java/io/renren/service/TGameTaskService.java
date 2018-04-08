package io.renren.service;

import io.renren.entity.TGameTaskEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-06 17:17:34
 */
public interface TGameTaskService {
	
	TGameTaskEntity queryObject(Long id);
	
	TGameTaskEntity queryObjectById(Long id);
	
	List<TGameTaskEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameTaskEntity tGameTask);
	
	void update(TGameTaskEntity tGameTask);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
