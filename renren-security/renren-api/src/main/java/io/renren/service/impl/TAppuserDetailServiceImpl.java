package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserDetailDao;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.service.TAppuserDetailService;



@Service("tAppuserDetailService")
public class TAppuserDetailServiceImpl implements TAppuserDetailService {
	@Autowired
	private TAppuserDetailDao tAppuserDetailDao;
	
	@Override
	public TAppuserDetailEntity queryObject(Long id){
		return tAppuserDetailDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserDetailEntity> queryList(Map<String, Object> map){
		return tAppuserDetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserDetailDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserDetailEntity tAppuserDetail){
		tAppuserDetailDao.save(tAppuserDetail);
	}
	
	@Override
	public void update(TAppuserDetailEntity tAppuserDetail){
		tAppuserDetailDao.update(tAppuserDetail);
	}
	
	@Override
	public void delete(Long id){
		tAppuserDetailDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserDetailDao.deleteBatch(ids);
	}

	@Override
	public TAppuserDetailEntity queryObjectByAppuserId(String appUserId) {
		return tAppuserDetailDao.queryObjectByAppuserId(appUserId);
	}

	@Override
	public void updateGameGold(Map<String, Object> map) {
		tAppuserDetailDao.updateGameGold(map);
	}
	
}
