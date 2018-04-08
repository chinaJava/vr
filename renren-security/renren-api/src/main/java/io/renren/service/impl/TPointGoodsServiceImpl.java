package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TPointGoodsDao;
import io.renren.entity.TPointGoodsEntity;
import io.renren.service.TPointGoodsService;



@Service("tPointGoodsService")
public class TPointGoodsServiceImpl implements TPointGoodsService {
	@Autowired
	private TPointGoodsDao tPointGoodsDao;
	
	@Override
	public TPointGoodsEntity queryObject(Integer id){
		return tPointGoodsDao.queryObject(id);
	}
	
	@Override
	public List<TPointGoodsEntity> queryList(Map<String, Object> map){
		return tPointGoodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tPointGoodsDao.queryTotal(map);
	}
	
	@Override
	public void save(TPointGoodsEntity tPointGoods){
		tPointGoodsDao.save(tPointGoods);
	}
	
	@Override
	public void update(TPointGoodsEntity tPointGoods){
		tPointGoodsDao.update(tPointGoods);
	}
	
	@Override
	public void delete(Integer id){
		tPointGoodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tPointGoodsDao.deleteBatch(ids);
	}
	
}
