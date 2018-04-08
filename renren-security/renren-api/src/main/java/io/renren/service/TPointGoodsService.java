package io.renren.service;

import io.renren.entity.TPointGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:30
 */
public interface TPointGoodsService {
	
	TPointGoodsEntity queryObject(Integer id);
	
	List<TPointGoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TPointGoodsEntity tPointGoods);
	
	void update(TPointGoodsEntity tPointGoods);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
