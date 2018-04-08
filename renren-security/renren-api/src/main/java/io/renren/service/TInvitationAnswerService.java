package io.renren.service;

import io.renren.entity.TInvitationAnswerEntity;

import java.util.List;
import java.util.Map;

/**
 * 回帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public interface TInvitationAnswerService {
	
	TInvitationAnswerEntity queryObject(Long answerId);
	
	List<TInvitationAnswerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TInvitationAnswerEntity tAnswer);
	
	void update(TInvitationAnswerEntity tAnswer);
	
	void delete(Long answerId);
	
	void deleteBatch(Long[] answerIds);
	
	void updateBatch(Map<String, Object> map);
	
	TInvitationAnswerEntity queryOne(Map<String, Object> param);
	
	int queryTotalByChild(Long answerId);
}
