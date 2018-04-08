package io.renren.dao;

import java.util.List;

import io.renren.entity.TMemberInfoEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-16 11:52:09
 */
public interface TMemberInfoDao extends BaseDao<TMemberInfoEntity> {
	
	List<TMemberInfoEntity> queryAllList();
}
