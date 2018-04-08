package io.renren.dao;

import java.util.Map;

import io.renren.entity.TInvitationAnswerEntity;

/**
 * 回帖表
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2018-03-20 09:25:56
 */
public interface TInvitationAnswerDao extends BaseDao<TInvitationAnswerEntity> {
	TInvitationAnswerEntity queryOne(Map<String, Object> param);
	int queryTotalByChild(Long answerId);
}
