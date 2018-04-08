package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TDetectionVersionDao;
import io.renren.entity.TDetectionVersionEntity;
import io.renren.service.TDetectionVersionService;



@Service("tDetectionVersionService")
public class TDetectionVersionServiceImpl implements TDetectionVersionService {
	@Autowired
	private TDetectionVersionDao tDetectionVersionDao;
	
	@Override
	public TDetectionVersionEntity queryObject(Long id){
		return tDetectionVersionDao.queryObject(id);
	}
	
	@Override
	public List<TDetectionVersionEntity> queryList(Map<String, Object> map){
		return tDetectionVersionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDetectionVersionDao.queryTotal(map);
	}
	
	@Override
	public void save(TDetectionVersionEntity tDetectionVersion){
		tDetectionVersionDao.save(tDetectionVersion);
	}
	
	@Override
	public void update(TDetectionVersionEntity tDetectionVersion){
		tDetectionVersionDao.update(tDetectionVersion);
	}
	
	@Override
	public void delete(Long id){
		tDetectionVersionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tDetectionVersionDao.deleteBatch(ids);
	}

	@Override
	public TDetectionVersionEntity queryObjectVersion(String client) {
		return tDetectionVersionDao.queryObjectVersion(client);
	}
	
}
