package io.renren.service;

import io.renren.entity.TStoreProducttypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-01 14:52:58
 */
public interface TStoreProducttypeService {
	
	TStoreProducttypeEntity queryObject(Long id);
	
	List<TStoreProducttypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TStoreProducttypeEntity tStoreProducttype);
	
	void update(TStoreProducttypeEntity tStoreProducttype);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<TStoreProducttypeEntity> queryAllObject();
}
