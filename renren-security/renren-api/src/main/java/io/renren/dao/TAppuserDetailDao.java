package io.renren.dao;

import java.util.Map;

import io.renren.entity.TAppuserDetailEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TAppuserDetailDao extends BaseDao<TAppuserDetailEntity> {
	
	TAppuserDetailEntity queryObjectByAppuserId(String appUserId);
	void updateGameGold(Map<String,Object> map);
}
