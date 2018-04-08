package io.renren.dao;

import io.renren.entity.TAppuserSignEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 13:49:33
 */
public interface TAppuserSignDao extends BaseDao<TAppuserSignEntity> {

	TAppuserSignEntity queryUser(String appuserId);

	void interrupt(TAppuserSignEntity tAppuserSignEntity);
}
