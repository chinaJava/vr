package io.renren.service;

import io.renren.entity.TAppuserGiftsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 09:37:57
 */
public interface TAppuserGiftsService {
	
	TAppuserGiftsEntity queryObject(Long id);
	
	TAppuserGiftsEntity queryObjectByAppuserId(String appuserId,int giftsId);
	
	List<TAppuserGiftsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserGiftsEntity tAppuserGifts);
	
	void update(TAppuserGiftsEntity tAppuserGifts);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
