package io.renren.dao;

import io.renren.entity.TCollectionEntity;

/**
 * 收藏表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
public interface TCollectionDao extends BaseDao<TCollectionEntity> {

	void deleteByState(Integer state);

	TCollectionEntity queryByInvitation(Long invitationId);
	
}
