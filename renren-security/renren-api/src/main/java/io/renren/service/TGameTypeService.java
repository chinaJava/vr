package io.renren.service;

import io.renren.entity.TGameTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameTypeService {
	
	TGameTypeEntity queryObject(Integer id);
	
	List<TGameTypeEntity> queryList(Map<String, Object> map);
	
	List<TGameTypeEntity> queryParentObject(Map<String, Object> map);
	
	List<TGameTypeEntity> queryAllObject();
	
	List<TGameTypeEntity> queryObjectByPid(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameTypeEntity tGameType);
	
	void update(TGameTypeEntity tGameType);
	
	void updateBatch(Integer[] ids);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
