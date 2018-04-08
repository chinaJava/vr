package io.renren.service;

import io.renren.entity.TAppuserPointgoodsEntity;
import io.renren.entity.TAppuserPromotionListEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:29
 */
public interface TAppuserPointgoodsService {
	
	TAppuserPointgoodsEntity queryObject(Long id);
	
	TAppuserPointgoodsEntity queryByAppuserIdAndGoodsId(Map<String, Object> map);
	
	List<TAppuserPointgoodsEntity> queryByAppuserId(String appuserId);
	
	List<TAppuserPointgoodsEntity> queryList(Map<String, Object> map);
	
	List<TAppuserPromotionListEntity> queryDetail(Map<String, Object> map);
	
	int queryTotalByAppuserId(String id);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserPointgoodsEntity tAppuserPointgoods);
	
	void update(TAppuserPointgoodsEntity tAppuserPointgoods);
	
	void updateByAppuserId(String appuserId);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
