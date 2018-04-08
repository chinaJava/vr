package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.THomeImageDao;
import io.renren.entity.THomeImageEntity;
import io.renren.service.THomeImageService;



@Service("tHomeImageService")
public class THomeImageServiceImpl implements THomeImageService {
	@Autowired
	private THomeImageDao tHomeImageDao;
	
	@Override
	public THomeImageEntity queryObject(Long id){
		return tHomeImageDao.queryObject(id);
	}
	
	@Override
	public List<THomeImageEntity> queryList(Map<String, Object> map){
		return tHomeImageDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tHomeImageDao.queryTotal(map);
	}
	
	@Override
	public void save(THomeImageEntity tHomeImage){
		tHomeImageDao.save(tHomeImage);
	}
	
	@Override
	public void update(THomeImageEntity tHomeImage){
		tHomeImageDao.update(tHomeImage);
	}
	
	@Override
	public void delete(Long id){
		tHomeImageDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tHomeImageDao.deleteBatch(ids);
	}
	
}
