package io.renren.service;

import io.renren.entity.TMemberInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 11:52:09
 */
public interface TMemberInfoService {
	
	TMemberInfoEntity queryObject(Integer id);
	
	List<TMemberInfoEntity> queryList(Map<String, Object> map);
	
	List<TMemberInfoEntity> queryAllList();
	
	int queryTotal(Map<String, Object> map);
	
	void save(TMemberInfoEntity tMemberInfo);
	
	void update(TMemberInfoEntity tMemberInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
