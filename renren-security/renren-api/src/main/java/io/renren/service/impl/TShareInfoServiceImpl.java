package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TShareInfoDao;
import io.renren.entity.TShareInfoEntity;
import io.renren.service.TShareInfoService;



@Service("tShareInfoService")
public class TShareInfoServiceImpl implements TShareInfoService {
	@Autowired
	private TShareInfoDao tShareInfoDao;
	
	@Override
	public TShareInfoEntity queryObject(Integer id){
		return tShareInfoDao.queryObject(id);
	}
	
	@Override
	public List<TShareInfoEntity> queryList(Map<String, Object> map){
		return tShareInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tShareInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(TShareInfoEntity tShareInfo){
		tShareInfoDao.save(tShareInfo);
	}
	
	@Override
	public void update(TShareInfoEntity tShareInfo){
		tShareInfoDao.update(tShareInfo);
	}
	
	@Override
	public void delete(Integer id){
		tShareInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tShareInfoDao.deleteBatch(ids);
	}

	@Override
	public TShareInfoEntity queryShareInfo(String type) {
		return tShareInfoDao.queryShareInfo(type);
	}
	
}
