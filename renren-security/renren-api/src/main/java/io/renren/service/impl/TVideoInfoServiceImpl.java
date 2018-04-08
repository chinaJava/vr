package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TVideoInfoDao;
import io.renren.entity.TVideoInfoEntity;
import io.renren.service.TVideoInfoService;



@Service("tVideoInfoService")
public class TVideoInfoServiceImpl implements TVideoInfoService {
	@Autowired
	private TVideoInfoDao tVideoInfoDao;
	
	@Override
	public TVideoInfoEntity queryObject(Integer id){
		return tVideoInfoDao.queryObject(id);
	}
	
	@Override
	public List<TVideoInfoEntity> queryList(Map<String, Object> map){
		return tVideoInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tVideoInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(TVideoInfoEntity tVideoInfo){
		tVideoInfoDao.save(tVideoInfo);
	}
	
	@Override
	public void update(TVideoInfoEntity tVideoInfo){
		tVideoInfoDao.update(tVideoInfo);
	}
	
	@Override
	public void delete(Integer id){
		tVideoInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tVideoInfoDao.deleteBatch(ids);
	}
	
}
