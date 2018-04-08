package io.renren.dao;

import io.renren.entity.TAppuserEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:20
 */
public interface TAppuserDao extends BaseDao<TAppuserEntity> {
	
	TAppuserEntity queryObjectByMobile(String mobile);
	
	TAppuserEntity queryObjectByUsername(String username);
	
	TAppuserEntity queryObjectByOpenId(String openId);
	
}
