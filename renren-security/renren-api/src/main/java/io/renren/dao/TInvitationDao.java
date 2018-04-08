package io.renren.dao;

import io.renren.entity.TInvitationEntity;

/**
 * 发帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public interface TInvitationDao extends BaseDao<TInvitationEntity> {

	TInvitationEntity queryByUser(String userId);
	
}
