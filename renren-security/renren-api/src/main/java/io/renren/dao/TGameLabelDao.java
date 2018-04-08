package io.renren.dao;

import java.util.List;

import io.renren.entity.TGameLabelEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameLabelDao extends BaseDao<TGameLabelEntity> {
	
	List<TGameLabelEntity> queryAllObject();
}
