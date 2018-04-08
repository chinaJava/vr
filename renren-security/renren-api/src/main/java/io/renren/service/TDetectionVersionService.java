package io.renren.service;

import io.renren.entity.TDetectionVersionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-16 20:39:26
 */
public interface TDetectionVersionService {
	
	TDetectionVersionEntity queryObject(Long id);
	
	List<TDetectionVersionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDetectionVersionEntity tDetectionVersion);
	
	void update(TDetectionVersionEntity tDetectionVersion);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	TDetectionVersionEntity queryObjectVersion(String client);
}
