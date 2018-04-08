package io.renren.service;

import io.renren.entity.TAppuserDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TAppuserDetailService {
	
	TAppuserDetailEntity queryObject(Long id);
	
	TAppuserDetailEntity queryObjectByAppuserId(String appUserId);
	
	List<TAppuserDetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserDetailEntity tAppuserDetail);
	
	void update(TAppuserDetailEntity tAppuserDetail);
	
	void updateGameGold(Map<String,Object> map);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
