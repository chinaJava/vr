package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TFollowDao;
import io.renren.entity.TFollowEntity;
import io.renren.service.TFollowService;



@Service("tFollowService")
public class TFollowServiceImpl implements TFollowService {
	@Autowired
	private TFollowDao tFollowDao;
	
	@Override
	public TFollowEntity queryObject(Long followId){
		return tFollowDao.queryObject(followId);
	}
	
	@Override
	public List<TFollowEntity> queryList(Map<String, Object> map){
		return tFollowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFollowDao.queryTotal(map);
	}
	
	@Override
	public void save(TFollowEntity tFollow){
		tFollowDao.save(tFollow);
	}
	
	@Override
	public void update(TFollowEntity tFollow){
		tFollowDao.update(tFollow);
	}
	
	@Override
	public void delete(Long followId){
		tFollowDao.delete(followId);
	}
	
	@Override
	public void deleteBatch(Long[] followIds){
		tFollowDao.deleteBatch(followIds);
	}

	@Override
	public void updateBatch(Map<String, Object> map) {
		tFollowDao.updateBatch(map);
	}

	@Override
	public TFollowEntity get(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tFollowDao.get(map);
	}

	
}
