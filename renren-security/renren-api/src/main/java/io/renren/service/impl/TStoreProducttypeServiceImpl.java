package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TStoreProducttypeDao;
import io.renren.entity.TStoreProducttypeEntity;
import io.renren.service.TStoreProducttypeService;



@Service("tStoreProducttypeService")
public class TStoreProducttypeServiceImpl implements TStoreProducttypeService {
	@Autowired
	private TStoreProducttypeDao tStoreProducttypeDao;
	
	@Override
	public TStoreProducttypeEntity queryObject(Long id){
		return tStoreProducttypeDao.queryObject(id);
	}
	
	@Override
	public List<TStoreProducttypeEntity> queryList(Map<String, Object> map){
		return tStoreProducttypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tStoreProducttypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TStoreProducttypeEntity tStoreProducttype){
		tStoreProducttypeDao.save(tStoreProducttype);
	}
	
	@Override
	public void update(TStoreProducttypeEntity tStoreProducttype){
		tStoreProducttypeDao.update(tStoreProducttype);
	}
	
	@Override
	public void delete(Long id){
		tStoreProducttypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tStoreProducttypeDao.deleteBatch(ids);
	}

	@Override
	public List<TStoreProducttypeEntity> queryAllObject() {
		// TODO Auto-generated method stub
		return tStoreProducttypeDao.queryAllObject();
	}
	
}
