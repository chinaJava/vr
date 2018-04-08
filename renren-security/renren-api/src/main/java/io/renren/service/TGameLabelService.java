package io.renren.service;

import io.renren.entity.TGameLabelEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameLabelService {
	
	TGameLabelEntity queryObject(Integer id);
	
	List<TGameLabelEntity> queryAllObject();
	
	List<TGameLabelEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameLabelEntity tGameLabel);
	
	void update(TGameLabelEntity tGameLabel);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
