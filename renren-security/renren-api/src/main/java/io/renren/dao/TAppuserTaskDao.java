package io.renren.dao;

import java.util.Map;

import io.renren.entity.TAppuserTaskEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 09:54:19
 */
public interface TAppuserTaskDao extends BaseDao<TAppuserTaskEntity> {
	
	TAppuserTaskEntity queryObjectByTaskId(Map<String, Object> map);
}
