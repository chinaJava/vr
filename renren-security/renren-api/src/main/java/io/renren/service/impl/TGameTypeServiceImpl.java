package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dao.TGameTypeDao;
import io.renren.entity.TGameTypeEntity;
import io.renren.service.TGameTypeService;
import io.renren.utils.Constant;



@Service("tGameTypeService")
public class TGameTypeServiceImpl implements TGameTypeService {
	@Autowired
	private TGameTypeDao tGameTypeDao;
	
	@Override
	public TGameTypeEntity queryObject(Integer id){
		return tGameTypeDao.queryObject(id);
	}
	
	@Override
	public List<TGameTypeEntity> queryList(Map<String, Object> map){
		return tGameTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TGameTypeEntity tGameType){
		tGameTypeDao.save(tGameType);
	}
	
	@Override
	@Transactional
	public void update(TGameTypeEntity tGameType){
		//修改父类型状态时，子类型同时修改
		if(tGameType.getPid() == null && Constant.Status.DISABLE.getValue().equals(tGameType.getStatus())){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tGameType.getId());
			map.put("status", Constant.Status.NORMAL.getValue());
			List<TGameTypeEntity> typeList = tGameTypeDao.queryObjectByPid(map);
			Integer[] ids = new Integer[typeList.size()];
			for(int i=0;i<typeList.size();i++){
				ids[i] = typeList.get(i).getId();
			}
			tGameTypeDao.updateBatch(ids);
		}
		tGameTypeDao.update(tGameType);
	}
	
	@Override
	public void delete(Integer id){
		tGameTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGameTypeDao.deleteBatch(ids);
	}

	@Override
	public List<TGameTypeEntity> queryParentObject(Map<String, Object> map) {
		return tGameTypeDao.queryParentObject(map);
	}

	@Override
	public void updateBatch(Integer[] ids) {
		tGameTypeDao.updateBatch(ids);		
	}

	@Override
	public List<TGameTypeEntity> queryObjectByPid(Map<String, Object> map) {
		return tGameTypeDao.queryObjectByPid(map);
	}

	@Override
	public List<TGameTypeEntity> queryAllObject() {
		return tGameTypeDao.queryAllObject();
	}
	
}
