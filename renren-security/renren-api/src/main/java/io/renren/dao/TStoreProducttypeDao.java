package io.renren.dao;

import java.util.List;

import io.renren.entity.TStoreProducttypeEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-01 14:52:58
 */
public interface TStoreProducttypeDao extends BaseDao<TStoreProducttypeEntity> {

	List<TStoreProducttypeEntity> queryAllObject();
	
}
