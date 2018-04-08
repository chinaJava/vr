package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TMemberInfoDao;
import io.renren.entity.TMemberInfoEntity;
import io.renren.service.TMemberInfoService;



@Service("tMemberInfoService")
public class TMemberInfoServiceImpl implements TMemberInfoService {
	@Autowired
	private TMemberInfoDao tMemberInfoDao;
	
	@Override
	public TMemberInfoEntity queryObject(Integer id){
		return tMemberInfoDao.queryObject(id);
	}
	
	@Override
	public List<TMemberInfoEntity> queryList(Map<String, Object> map){
		return tMemberInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tMemberInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(TMemberInfoEntity tMemberInfo){
		tMemberInfoDao.save(tMemberInfo);
	}
	
	@Override
	public void update(TMemberInfoEntity tMemberInfo){
		tMemberInfoDao.update(tMemberInfo);
	}
	
	@Override
	public void delete(Integer id){
		tMemberInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tMemberInfoDao.deleteBatch(ids);
	}

	@Override
	public List<TMemberInfoEntity> queryAllList() {
		return tMemberInfoDao.queryAllList();
	}
	
}
