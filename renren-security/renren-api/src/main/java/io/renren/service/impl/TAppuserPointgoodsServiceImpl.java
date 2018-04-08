package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserPointgoodsDao;
import io.renren.entity.TAppuserPointgoodsEntity;
import io.renren.entity.TAppuserPromotionListEntity;
import io.renren.service.TAppuserPointgoodsService;



@Service("tAppuserPointgoodsService")
public class TAppuserPointgoodsServiceImpl implements TAppuserPointgoodsService {
	@Autowired
	private TAppuserPointgoodsDao tAppuserPointgoodsDao;
	
	@Override
	public TAppuserPointgoodsEntity queryObject(Long id){
		return tAppuserPointgoodsDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserPointgoodsEntity> queryList(Map<String, Object> map){
		return tAppuserPointgoodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserPointgoodsDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserPointgoodsEntity tAppuserPointgoods){
		tAppuserPointgoodsDao.save(tAppuserPointgoods);
	}
	
	@Override
	public void update(TAppuserPointgoodsEntity tAppuserPointgoods){
		tAppuserPointgoodsDao.update(tAppuserPointgoods);
	}
	
	@Override
	public void delete(Long id){
		tAppuserPointgoodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserPointgoodsDao.deleteBatch(ids);
	}

	@Override
	public TAppuserPointgoodsEntity queryByAppuserIdAndGoodsId(
			Map<String, Object> map) {
		return tAppuserPointgoodsDao.queryByAppuserIdAndGoodsId(map);
	}

	@Override
	public List<TAppuserPointgoodsEntity> queryByAppuserId(String appuserId) {
		return tAppuserPointgoodsDao.queryByAppuserId(appuserId);
	}

	@Override
	public void updateByAppuserId(String appuserId) {
		tAppuserPointgoodsDao.updateByAppuserId(appuserId);
	}

	@Override
	public List<TAppuserPromotionListEntity> queryDetail(Map<String, Object> map) {
		
		return tAppuserPointgoodsDao.queryDetail(map);
	}

	@Override
	public int queryTotalByAppuserId(String id) {
		// TODO Auto-generated method stub
		return tAppuserPointgoodsDao.queryTotalByAppuserId(id);
	}
	
}
