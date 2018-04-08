package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.TAppuserPromotionListEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 11:52:09
 */
public interface TAppuserPromotionListDao extends BaseDao<TAppuserPromotionListEntity> {
	
	List<TAppuserPromotionListEntity> queryListByUserId(String appUserId);
	
	TAppuserPromotionListEntity queryObjectByPromotionUserId(String promotionUserId);
	
	int queryTotalValid();
	
	int queryMemberValid(String appUserId);
	
	List<TAppuserPromotionListEntity> queryPromotionList(Map<String, Object> map);
	
	List<TAppuserPromotionListEntity> queryDetail(Map<String, Object> map);
	
	int queryTotalByAppuserId(String id);
}
