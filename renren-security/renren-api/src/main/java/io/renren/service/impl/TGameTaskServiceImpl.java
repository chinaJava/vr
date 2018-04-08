package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TGameTaskDao;
import io.renren.entity.TGameTaskEntity;
import io.renren.service.TGameTaskService;



@Service("tGameTaskService")
public class TGameTaskServiceImpl implements TGameTaskService {
	@Autowired
	private TGameTaskDao tGameTaskDao;
	
	@Override
	public TGameTaskEntity queryObject(Long id){
		return tGameTaskDao.queryObject(id);
	}
	
	@Override
	public List<TGameTaskEntity> queryList(Map<String, Object> map){
		return tGameTaskDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameTaskDao.queryTotal(map);
	}
	
	@Override
	public void save(TGameTaskEntity tGameTask){
		tGameTaskDao.save(tGameTask);
	}
	
	@Override
	public void update(TGameTaskEntity tGameTask){
		tGameTaskDao.update(tGameTask);
	}
	
	@Override
	public void delete(Long id){
		tGameTaskDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tGameTaskDao.deleteBatch(ids);
	}

	@Override
	public TGameTaskEntity queryObjectById(Long id) {
		return tGameTaskDao.queryObjectById(id);
	}
	
}
