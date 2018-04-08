package io.renren.dao;

import io.renren.entity.TDetectionVersionEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-16 20:39:26
 */
public interface TDetectionVersionDao extends BaseDao<TDetectionVersionEntity> {

	TDetectionVersionEntity queryObjectVersion(String client);
	
}
