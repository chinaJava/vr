package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.TAppuserPointgoodsEntity;
import io.renren.entity.TAppuserPromotionListEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:29
 */
public interface TAppuserPointgoodsDao extends BaseDao<TAppuserPointgoodsEntity> {
	
	TAppuserPointgoodsEntity queryByAppuserIdAndGoodsId(Map<String, Object> map);
	
	List<TAppuserPointgoodsEntity> queryByAppuserId(String appuserId);
	
	void updateByAppuserId(String appuserId);
	
	List<TAppuserPromotionListEntity> queryDetail(Map<String, Object> map);
	int queryTotalByAppuserId(String id);
}
