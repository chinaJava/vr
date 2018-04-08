package io.renren.service;

import io.renren.entity.TInvitationEntity;
import io.renren.entity.TInvitationLikesEntity;

import java.util.List;
import java.util.Map;

/**
 * 发帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public interface TInvitationService {
	
	TInvitationEntity queryObject(Long invitationId);
	
	List<TInvitationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TInvitationEntity tInvitation);
	
	void update(TInvitationEntity tInvitation);
	
	void updateLikes(TInvitationEntity tInvitationObj,TInvitationLikesEntity invitationLikesObj,String type);
	
	void delete(Long invitationId);
	
	void deleteBatch(Long[] invitationIds);
	
	void updateBatch(Map<String, Object> map);

	TInvitationEntity queryByUser(String userId);
}
