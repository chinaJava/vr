package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserAddressDao;
import io.renren.entity.TAppuserAddressEntity;
import io.renren.service.TAppuserAddressService;



@Service("tAppuserAddressService")
public class TAppuserAddressServiceImpl implements TAppuserAddressService {
	@Autowired
	private TAppuserAddressDao tAppuserAddressDao;
	
	@Override
	public TAppuserAddressEntity queryObject(Long id){
		return tAppuserAddressDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserAddressEntity> queryList(Map<String, Object> map){
		return tAppuserAddressDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserAddressDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserAddressEntity tAppuserAddress){
		tAppuserAddressDao.save(tAppuserAddress);
	}
	
	@Override
	public void update(TAppuserAddressEntity tAppuserAddress){
		tAppuserAddressDao.update(tAppuserAddress);
	}
	
	@Override
	public void delete(Long id){
		tAppuserAddressDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserAddressDao.deleteBatch(ids);
	}

	@Override
	public List<TAppuserAddressEntity> queryListByAppUserId(String appUserId) {
		return tAppuserAddressDao.queryListByAppUserId(appUserId);
	}

	@Override
	public void deleteByAppUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		tAppuserAddressDao.deleteByAppUserId(map);
	}
	
}
