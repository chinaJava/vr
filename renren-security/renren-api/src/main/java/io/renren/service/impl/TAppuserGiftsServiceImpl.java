package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserGiftsDao;
import io.renren.entity.TAppuserGiftsEntity;
import io.renren.service.TAppuserGiftsService;



@Service("tAppuserGiftsService")
public class TAppuserGiftsServiceImpl implements TAppuserGiftsService {
	@Autowired
	private TAppuserGiftsDao tAppuserGiftsDao;
	
	@Override
	public TAppuserGiftsEntity queryObject(Long id){
		return tAppuserGiftsDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserGiftsEntity> queryList(Map<String, Object> map){
		return tAppuserGiftsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserGiftsDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserGiftsEntity tAppuserGifts){
		tAppuserGiftsDao.save(tAppuserGifts);
	}
	
	@Override
	public void update(TAppuserGiftsEntity tAppuserGifts){
		tAppuserGiftsDao.update(tAppuserGifts);
	}
	
	@Override
	public void delete(Long id){
		tAppuserGiftsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserGiftsDao.deleteBatch(ids);
	}

	@Override
	public TAppuserGiftsEntity queryObjectByAppuserId(String appuserId,int giftsId) {
		
		return tAppuserGiftsDao.queryObjectByAppuserId(appuserId,giftsId);
	}
	
}
