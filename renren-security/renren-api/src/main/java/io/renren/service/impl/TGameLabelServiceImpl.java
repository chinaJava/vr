package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TGameLabelDao;
import io.renren.entity.TGameLabelEntity;
import io.renren.service.TGameLabelService;



@Service("tGameLabelService")
public class TGameLabelServiceImpl implements TGameLabelService {
	@Autowired
	private TGameLabelDao tGameLabelDao;
	
	@Override
	public TGameLabelEntity queryObject(Integer id){
		return tGameLabelDao.queryObject(id);
	}
	
	@Override
	public List<TGameLabelEntity> queryList(Map<String, Object> map){
		return tGameLabelDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameLabelDao.queryTotal(map);
	}
	
	@Override
	public void save(TGameLabelEntity tGameLabel){
		tGameLabelDao.save(tGameLabel);
	}
	
	@Override
	public void update(TGameLabelEntity tGameLabel){
		tGameLabelDao.update(tGameLabel);
	}
	
	@Override
	public void delete(Integer id){
		tGameLabelDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGameLabelDao.deleteBatch(ids);
	}

	@Override
	public List<TGameLabelEntity> queryAllObject() {
		return tGameLabelDao.queryAllObject();
	}
	
}
