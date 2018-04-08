package io.renren.service;

import io.renren.entity.TInvitationLikesEntity;

import java.util.List;
import java.util.Map;

/**
 * 点赞表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
public interface TInvitationLikesService {
	
	TInvitationLikesEntity queryObject(Long likeId);
	
	TInvitationLikesEntity queryObjectByUserId(Map<String, Object> map);
	
	List<TInvitationLikesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TInvitationLikesEntity tLike);
	
	void update(TInvitationLikesEntity tLike);
	
	void delete(Long likeId);
	
	void deleteBatch(Long[] likeIds);
	
	void updateBatch(Map<String, Object> map);
	
	TInvitationLikesEntity get(Map<String, Object> map);
}
