package io.renren.service;

import io.renren.entity.TVideoInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-02-11 15:00:35
 */
public interface TVideoInfoService {
	
	TVideoInfoEntity queryObject(Integer id);
	
	List<TVideoInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TVideoInfoEntity tVideoInfo);
	
	void update(TVideoInfoEntity tVideoInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
