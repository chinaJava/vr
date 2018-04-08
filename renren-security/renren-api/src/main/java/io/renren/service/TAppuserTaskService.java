package io.renren.service;

import io.renren.entity.TAppuserTaskEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 09:54:19
 */
public interface TAppuserTaskService {
	
	TAppuserTaskEntity queryObject(Long id);
	
	TAppuserTaskEntity queryObjectByTaskId(Map<String, Object> map);
	
	List<TAppuserTaskEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserTaskEntity tAppuserTask);
	
	void update(TAppuserTaskEntity tAppuserTask);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
