package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserSignDao;
import io.renren.entity.TAppuserSignEntity;
import io.renren.service.TAppuserSignService;



@Service("tAppuserSignService")
public class TAppuserSignServiceImpl implements TAppuserSignService {
	@Autowired
	private TAppuserSignDao tAppuserSignDao;

	
	@Override
	public TAppuserSignEntity queryObject(Long id){
		return tAppuserSignDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserSignEntity> queryList(Map<String, Object> map){
		return tAppuserSignDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserSignDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserSignEntity tAppuserSign){
		tAppuserSignDao.save(tAppuserSign);
	}
	
	@Override
	public void update(TAppuserSignEntity tAppuserSign){
		tAppuserSignDao.update(tAppuserSign);
	}
	
	@Override
	public void delete(Long id){
		tAppuserSignDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tAppuserSignDao.deleteBatch(ids);
	}

	@Override
	public TAppuserSignEntity queryUser(String appuserId) {
		return	tAppuserSignDao.queryUser(appuserId);	
	}

	@Override
	public void interrupt(TAppuserSignEntity tAppuserSignEntity) {
		tAppuserSignDao.interrupt( tAppuserSignEntity);
		
	}


}
