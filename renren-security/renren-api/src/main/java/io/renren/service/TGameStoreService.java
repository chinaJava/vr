package io.renren.service;

import io.renren.entity.TGameStoreEntity;
import io.renren.entity.TStoreOrdersEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-25 17:28:03
 */
public interface TGameStoreService {
	
	TGameStoreEntity queryObject(Long id);
	
	List<TGameStoreEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameStoreEntity tGameStore);
	
	void update(TGameStoreEntity tGameStore);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	//商品兑换下单
	void createOrder(TStoreOrdersEntity tStoreOrders);
}
