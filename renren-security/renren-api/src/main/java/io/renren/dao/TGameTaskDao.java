package io.renren.dao;

import io.renren.entity.TGameTaskEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-06 17:17:34
 */
public interface TGameTaskDao extends BaseDao<TGameTaskEntity> {
	
	TGameTaskEntity queryObjectById(Long id);
}
