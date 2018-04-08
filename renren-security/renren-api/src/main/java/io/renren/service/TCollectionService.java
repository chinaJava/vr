package io.renren.service;

import io.renren.entity.TCollectionEntity;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:55
 */
public interface TCollectionService {
	
	TCollectionEntity queryObject(Long collectionId);
	
	List<TCollectionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCollectionEntity tCollection);
	
	void update(TCollectionEntity tCollection);
	
	void delete(Long collectionId);
	
	void deleteBatch(Long[] collectionIds);

	void updateBatch(Map<String, Object> params);

	TCollectionEntity get(Map<String, Object> map);

	TCollectionEntity queryByInvitation(Long invitationId);

}
