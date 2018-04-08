package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TMemberGiftsDao;
import io.renren.entity.TMemberGiftsEntity;
import io.renren.service.TMemberGiftsService;
import io.renren.utils.Query;



@Service("tMemberGiftsService")
public class TMemberGiftsServiceImpl implements TMemberGiftsService {
	@Autowired
	private TMemberGiftsDao tMemberGiftsDao;
	
	@Override
	public TMemberGiftsEntity queryObject(Integer id){
		return tMemberGiftsDao.queryObject(id);
	}
	
	@Override
	public List<TMemberGiftsEntity> queryList(Map<String, Object> map){
		return tMemberGiftsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tMemberGiftsDao.queryTotal(map);
	}
	
	@Override
	public void save(TMemberGiftsEntity tMemberGifts){
		tMemberGiftsDao.save(tMemberGifts);
	}
	
	@Override
	public void update(TMemberGiftsEntity tMemberGifts){
		tMemberGiftsDao.update(tMemberGifts);
	}
	
	@Override
	public void delete(Integer id){
		tMemberGiftsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tMemberGiftsDao.deleteBatch(ids);
	}

	@Override
	public void receive(int id) {
		 tMemberGiftsDao.receive(id);
		
	}

	@Override
	public List<TMemberGiftsEntity> queryUsableList() {
		return tMemberGiftsDao.queryUsableList();
	}

	@Override
	public List<TMemberGiftsEntity> queryListByMemberLevel(Query query) {
		// TODO Auto-generated method stub
		return tMemberGiftsDao.queryListByMemberLevel(query);
	}

	
	
}
