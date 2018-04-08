package io.renren.dao;

import io.renren.entity.TStoreOrdersEntity;

/**
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-04 14:30:26
 */
public interface TStoreOrdersDao extends BaseDao<TStoreOrdersEntity> {
	
	void updateBatch(Long[] ids);
}
