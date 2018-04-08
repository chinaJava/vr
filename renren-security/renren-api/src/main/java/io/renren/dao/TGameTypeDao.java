package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.TGameTypeEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameTypeDao extends BaseDao<TGameTypeEntity> {
	
	List<TGameTypeEntity> queryParentObject(Map<String, Object> map);
	
	List<TGameTypeEntity> queryObjectByPid(Map<String, Object> map);
	
	List<TGameTypeEntity> queryAllObject();
	
	void updateBatch(Integer[] id);
}
