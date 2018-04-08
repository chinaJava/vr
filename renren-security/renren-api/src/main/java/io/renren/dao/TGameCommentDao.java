package io.renren.dao;

import org.apache.ibatis.annotations.Param;
import io.renren.entity.TGameCommentEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameCommentDao extends BaseDao<TGameCommentEntity> {

	void batchUpdateById(@Param("id")Long id,@Param("status") Integer status);

}
