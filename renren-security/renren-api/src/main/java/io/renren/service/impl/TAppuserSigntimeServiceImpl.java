package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserSigntimeDao;
import io.renren.entity.TAppuserSigntimeEntity;
import io.renren.service.TAppuserSigntimeService;



@Service("tAppuserSigntimeService")
public class TAppuserSigntimeServiceImpl implements TAppuserSigntimeService {
	@Autowired
	private TAppuserSigntimeDao tAppuserSigntimeDao;
	

	@Override
	public TAppuserSigntimeEntity queryObject(Long id){
		return tAppuserSigntimeDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserSigntimeEntity> queryList(Map<String, Object> map){
		return tAppuserSigntimeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserSigntimeDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserSigntimeEntity tAppuserSigntime){
		tAppuserSigntimeDao.save(tAppuserSigntime);
	}
	
	@Override
	public void update(TAppuserSigntimeEntity tAppuserSigntime){
		tAppuserSigntimeDao.update(tAppuserSigntime);
	}
	
	@Override
	public void delete(Long id){
		tAppuserSigntimeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserSigntimeDao.deleteBatch(ids);
	}

	@Override
	public List<TAppuserSigntimeEntity> queryListByappuserId(String appuserID) {
	
		return tAppuserSigntimeDao.queryListByappuserId(appuserID) ;
	}

	@Override
	public int queryTotalByToday(String appuserId ) {
		
		return tAppuserSigntimeDao.queryTotalByToday(appuserId);
	}

	@Override
	public int queryAllByToDay() {
		
		 return tAppuserSigntimeDao.queryAllByToDay();
	}

	
	
}
