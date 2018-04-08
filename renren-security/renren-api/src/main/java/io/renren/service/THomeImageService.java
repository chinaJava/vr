package io.renren.service;

import io.renren.entity.THomeImageEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-08-16 16:30:59
 */
public interface THomeImageService {
	
	THomeImageEntity queryObject(Long id);
	
	List<THomeImageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(THomeImageEntity tHomeImage);
	
	void update(THomeImageEntity tHomeImage);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
