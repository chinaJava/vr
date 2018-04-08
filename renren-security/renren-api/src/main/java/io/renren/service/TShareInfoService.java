package io.renren.service;

import io.renren.entity.TShareInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-12-19 09:37:43
 */
public interface TShareInfoService {
	
	TShareInfoEntity queryObject(Integer id);
	
	TShareInfoEntity queryShareInfo(String type);
	
	List<TShareInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TShareInfoEntity tShareInfo);
	
	void update(TShareInfoEntity tShareInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
