package io.renren.service;

import io.renren.entity.TAppuserPromotionListEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 11:52:09
 */
public interface TAppuserPromotionListService {
	
	TAppuserPromotionListEntity queryObject(String id);
	
	List<TAppuserPromotionListEntity> queryList(Map<String, Object> map);
	
	List<TAppuserPromotionListEntity> queryPromotionList(Map<String, Object> map);
	
	List<TAppuserPromotionListEntity> queryListByUserId(String appUserId);
	
	List<TAppuserPromotionListEntity> queryDetail(Map<String, Object> map);
	
	int queryTotalByAppuserId(String id);
	
	TAppuserPromotionListEntity queryObjectByPromotionUserId(String promotionUserId);
	
	int queryTotal(Map<String, Object> map);
	
	int queryTotalValid();
	
	int queryMemberValid(String appUserId);
	
	void save(TAppuserPromotionListEntity tAppuserPromotionList);
	
	void update(TAppuserPromotionListEntity tAppuserPromotionList);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
