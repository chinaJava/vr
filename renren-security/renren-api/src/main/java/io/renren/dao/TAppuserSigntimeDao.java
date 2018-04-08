package io.renren.dao;

import java.util.List;

import io.renren.entity.TAppuserSigntimeEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 15:21:44
 */
public interface TAppuserSigntimeDao extends BaseDao<TAppuserSigntimeEntity> {

	List<TAppuserSigntimeEntity> queryListByappuserId(String appuserID);
	
	int queryTotalByToday(String appuserId);
	
	int queryAllByToDay();
	
}
