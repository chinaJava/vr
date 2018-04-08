package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppSuggestDao;
import io.renren.entity.TAppSuggestEntity;
import io.renren.service.TAppSuggestService;



@Service("tAppSuggestService")
public class TAppSuggestServiceImpl implements TAppSuggestService {
	@Autowired
	private TAppSuggestDao tAppSuggestDao;
	
	@Override
	public TAppSuggestEntity queryObject(Integer id){
		return tAppSuggestDao.queryObject(id);
	}
	
	@Override
	public List<TAppSuggestEntity> queryList(Map<String, Object> map){
		return tAppSuggestDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppSuggestDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppSuggestEntity tAppSuggest){
		tAppSuggestDao.save(tAppSuggest);
	}
	
	@Override
	public void update(TAppSuggestEntity tAppSuggest){
		tAppSuggestDao.update(tAppSuggest);
	}
	
	@Override
	public void delete(Integer id){
		tAppSuggestDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tAppSuggestDao.deleteBatch(ids);
	}
	
}
