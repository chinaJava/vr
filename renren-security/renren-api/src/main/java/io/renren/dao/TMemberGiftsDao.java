package io.renren.dao;

import java.util.List;

import io.renren.entity.TMemberGiftsEntity;
import io.renren.utils.Query;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 09:37:57
 */
public interface TMemberGiftsDao extends BaseDao<TMemberGiftsEntity> {
	
	void receive(int id);
	List<TMemberGiftsEntity> queryListByMemberLevel(Query query);
	List<TMemberGiftsEntity> queryUsableList();
}
