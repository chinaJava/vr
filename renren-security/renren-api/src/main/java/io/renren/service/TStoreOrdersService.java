package io.renren.service;

import io.renren.entity.TStoreOrdersEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-04 14:30:26
 */
public interface TStoreOrdersService {
	
	TStoreOrdersEntity queryObject(Long id);
	
	List<TStoreOrdersEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TStoreOrdersEntity tStoreOrders);
	
	void update(TStoreOrdersEntity tStoreOrders);
	
	void updateBatch(Long[] ids);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
