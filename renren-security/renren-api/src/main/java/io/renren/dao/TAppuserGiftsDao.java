package io.renren.dao;

import io.renren.entity.TAppuserGiftsEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 09:37:57
 */
public interface TAppuserGiftsDao extends BaseDao<TAppuserGiftsEntity> {
	TAppuserGiftsEntity queryObjectByAppuserId(String appuserId,int giftsId);
}
