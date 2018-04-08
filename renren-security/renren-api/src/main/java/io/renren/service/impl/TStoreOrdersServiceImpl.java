package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TStoreOrdersDao;
import io.renren.entity.TStoreOrdersEntity;
import io.renren.service.TStoreOrdersService;



@Service("tStoreOrdersService")
public class TStoreOrdersServiceImpl implements TStoreOrdersService {
	@Autowired
	private TStoreOrdersDao tStoreOrdersDao;
	
	@Override
	public TStoreOrdersEntity queryObject(Long id){
		return tStoreOrdersDao.queryObject(id);
	}
	
	@Override
	public List<TStoreOrdersEntity> queryList(Map<String, Object> map){
		return tStoreOrdersDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tStoreOrdersDao.queryTotal(map);
	}
	
	@Override
	public void save(TStoreOrdersEntity tStoreOrders){
		tStoreOrdersDao.save(tStoreOrders);
	}
	
	@Override
	public void update(TStoreOrdersEntity tStoreOrders){
		tStoreOrdersDao.update(tStoreOrders);
	}
	
	@Override
	public void delete(Long id){
		tStoreOrdersDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tStoreOrdersDao.deleteBatch(ids);
	}

	@Override
	public void updateBatch(Long[] ids) {
		tStoreOrdersDao.updateBatch(ids);
	}
	
}
