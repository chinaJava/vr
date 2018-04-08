package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TArticleTypeDao;
import io.renren.entity.TArticleTypeEntity;
import io.renren.service.TArticleTypeService;



@Service("tArticleTypeService")
public class TArticleTypeServiceImpl implements TArticleTypeService {
	@Autowired
	private TArticleTypeDao tArticleTypeDao;
	
	@Override
	public TArticleTypeEntity queryObject(Integer id){
		return tArticleTypeDao.queryObject(id);
	}
	
	@Override
	public List<TArticleTypeEntity> queryList(Map<String, Object> map){
		return tArticleTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tArticleTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TArticleTypeEntity tArticleType){
		tArticleTypeDao.save(tArticleType);
	}
	
	@Override
	public void update(TArticleTypeEntity tArticleType){
		tArticleTypeDao.update(tArticleType);
	}
	
	@Override
	public void delete(Integer id){
		tArticleTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tArticleTypeDao.deleteBatch(ids);
	}

	@Override
	public List<TArticleTypeEntity> queryParentList(Map<String, Object> map) {
		return tArticleTypeDao.queryParentList(map);
	}

	@Override
	public List<TArticleTypeEntity> queryListByPid(Map<String, Object> map) {
		return tArticleTypeDao.queryListByPid(map);
	}

	@Override
	public List<TArticleTypeEntity> queryAllObject() {
		return tArticleTypeDao.queryAllObject();
	}
	
}
