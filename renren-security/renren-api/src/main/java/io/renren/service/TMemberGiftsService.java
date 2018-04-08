package io.renren.service;

import io.renren.entity.TMemberGiftsEntity;
import io.renren.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 09:37:57
 */
public interface TMemberGiftsService {
	
	TMemberGiftsEntity queryObject(Integer id);
	
	List<TMemberGiftsEntity> queryListByMemberLevel(Query query);
	
	List<TMemberGiftsEntity> queryList(Map<String, Object> map);
	
	List<TMemberGiftsEntity> queryUsableList();
	
	int queryTotal(Map<String, Object> map);
	
	void save(TMemberGiftsEntity tMemberGifts);
	
	void update(TMemberGiftsEntity tMemberGifts);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	void receive(int id);
}
