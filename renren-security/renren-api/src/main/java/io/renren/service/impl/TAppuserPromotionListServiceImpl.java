package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserPromotionListDao;
import io.renren.entity.TAppuserPromotionListEntity;
import io.renren.service.TAppuserPromotionListService;



@Service("tAppuserPromotionListService")
public class TAppuserPromotionListServiceImpl implements TAppuserPromotionListService {
	@Autowired
	private TAppuserPromotionListDao tAppuserPromotionListDao;
	
	@Override
	public TAppuserPromotionListEntity queryObject(String id){
		return tAppuserPromotionListDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserPromotionListEntity> queryList(Map<String, Object> map){
		return tAppuserPromotionListDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserPromotionListDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserPromotionListEntity tAppuserPromotionList){
		tAppuserPromotionListDao.save(tAppuserPromotionList);
	}
	
	@Override
	public void update(TAppuserPromotionListEntity tAppuserPromotionList){
		tAppuserPromotionListDao.update(tAppuserPromotionList);
	}
	
	@Override
	public void delete(String id){
		tAppuserPromotionListDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		tAppuserPromotionListDao.deleteBatch(ids);
	}

	@Override
	public List<TAppuserPromotionListEntity> queryListByUserId(String appUserId) {
		return tAppuserPromotionListDao.queryListByUserId(appUserId);
	}

	@Override
	public int queryTotalValid() {
		return tAppuserPromotionListDao.queryTotalValid();
	}

	@Override
	public int queryMemberValid(String appUserId) {
		return tAppuserPromotionListDao.queryMemberValid(appUserId);
	}

	@Override
	public TAppuserPromotionListEntity queryObjectByPromotionUserId(
			String promotionUserId) {
		return tAppuserPromotionListDao.queryObjectByPromotionUserId(promotionUserId);
	}

	@Override
	public List<TAppuserPromotionListEntity> queryPromotionList(Map<String, Object> map) {
		
		return tAppuserPromotionListDao.queryPromotionList(map);
	}

	@Override
	public List<TAppuserPromotionListEntity> queryDetail(Map<String, Object> map) {
		
		return tAppuserPromotionListDao.queryDetail(map);
	}

	@Override
	public int queryTotalByAppuserId(String id) {
		
		return tAppuserPromotionListDao.queryTotalByAppuserId(id);
	}
	
}
