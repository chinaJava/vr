package io.renren.service;

import io.renren.entity.TFollowEntity;

import java.util.List;
import java.util.Map;

/**
 * 关注表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public interface TFollowService {
	
	TFollowEntity queryObject(Long followId);
	
	List<TFollowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFollowEntity tFollow);
	
	void update(TFollowEntity tFollow);
	
	void delete(Long followId);
	
	void deleteBatch(Long[] followIds);
	
	void updateBatch(Map<String, Object> map);
	
	TFollowEntity get(Map<String, Object> map);
}
