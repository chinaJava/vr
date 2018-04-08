package io.renren.service;

import io.renren.entity.TAppuserSigntimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 15:21:44
 */
public interface TAppuserSigntimeService {
	
	TAppuserSigntimeEntity queryObject(Long id);
	
	List<TAppuserSigntimeEntity> queryList(Map<String, Object> map);
	
	List<TAppuserSigntimeEntity> queryListByappuserId(String appuserID);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserSigntimeEntity tAppuserSigntime);
	
	void update(TAppuserSigntimeEntity tAppuserSigntime);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	int queryTotalByToday(String appuserId);
	
	int queryAllByToDay();
}
