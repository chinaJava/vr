package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserDetailDao;
import io.renren.dao.TGameStoreDao;
import io.renren.dao.TStoreOrdersDao;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TGameStoreEntity;
import io.renren.entity.TStoreOrdersEntity;
import io.renren.service.TGameStoreService;



@Service("tGameStoreService")
public class TGameStoreServiceImpl implements TGameStoreService {
	@Autowired
	private TGameStoreDao tGameStoreDao;
	@Autowired
	private TStoreOrdersDao tStoreOrdersDao;
	@Autowired
	private TAppuserDetailDao tAppuserDetailDao;
	
	@Override
	public TGameStoreEntity queryObject(Long id){
		return tGameStoreDao.queryObject(id);
	}
	
	@Override
	public List<TGameStoreEntity> queryList(Map<String, Object> map){
		return tGameStoreDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameStoreDao.queryTotal(map);
	}
	
	@Override
	public void save(TGameStoreEntity tGameStore){
		tGameStoreDao.save(tGameStore);
	}
	
	@Override
	public void update(TGameStoreEntity tGameStore){
		tGameStoreDao.update(tGameStore);
	}
	
	@Override
	public void delete(Long id){
		tGameStoreDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tGameStoreDao.deleteBatch(ids);
	}

	@Override
	@Transactional
	public void createOrder(TStoreOrdersEntity tStoreOrders) {
		/*******创建商品兑换订单，修改商品库存，更新用户金币数量*******************************/
		String userId = tStoreOrders.getAppuserId();
		TAppuserDetailEntity appuserDetailObj = tAppuserDetailDao.queryObjectByAppuserId(userId);
		TGameStoreEntity storeObj = tGameStoreDao.queryObject(tStoreOrders.getProductId());
		appuserDetailObj.setGameGold(appuserDetailObj.getGameGold()-storeObj.getGameGold());//减用户金币数
		storeObj.setInventory(storeObj.getInventory()-tStoreOrders.getProductAmount());//减商品库存
		tStoreOrdersDao.save(tStoreOrders);
		tAppuserDetailDao.update(appuserDetailObj);
		tGameStoreDao.update(storeObj);
	}
	
}
