package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserTaskDao;
import io.renren.entity.TAppuserTaskEntity;
import io.renren.service.TAppuserTaskService;



@Service("tAppuserTaskService")
public class TAppuserTaskServiceImpl implements TAppuserTaskService {
	@Autowired
	private TAppuserTaskDao tAppuserTaskDao;
	
	@Override
	public TAppuserTaskEntity queryObject(Long id){
		return tAppuserTaskDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserTaskEntity> queryList(Map<String, Object> map){
		return tAppuserTaskDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserTaskDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserTaskEntity tAppuserTask){
		tAppuserTaskDao.save(tAppuserTask);
	}
	
	@Override
	public void update(TAppuserTaskEntity tAppuserTask){
		tAppuserTaskDao.update(tAppuserTask);
	}
	
	@Override
	public void delete(Long id){
		tAppuserTaskDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserTaskDao.deleteBatch(ids);
	}

	@Override
	public TAppuserTaskEntity queryObjectByTaskId(Map<String, Object> map) {
		return tAppuserTaskDao.queryObjectByTaskId(map);
	}
	
}
