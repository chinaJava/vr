package io.renren.dao;

import java.util.Map;

import io.renren.entity.TInvitationLikesEntity;

/**
 * 点赞表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
public interface TInvitationLikesDao extends BaseDao<TInvitationLikesEntity> {
	
	TInvitationLikesEntity queryObjectByUserId(Map<String, Object> map);
}
