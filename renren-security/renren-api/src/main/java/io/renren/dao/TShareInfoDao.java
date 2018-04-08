package io.renren.dao;

import io.renren.entity.TShareInfoEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-12-19 09:37:43
 */
public interface TShareInfoDao extends BaseDao<TShareInfoEntity> {
	
	TShareInfoEntity queryShareInfo(String type);
}
