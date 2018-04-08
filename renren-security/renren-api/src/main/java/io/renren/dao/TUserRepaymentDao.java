package io.renren.dao;

import org.apache.ibatis.annotations.Param;

import io.renren.entity.TUserRepaymentEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 19:16:10
 */
public interface TUserRepaymentDao extends BaseDao<TUserRepaymentEntity> {

	Integer updateSelecByAppNoAndStatus(@Param(value="appNo") String appNo,@Param(value="status") String status);

	TUserRepaymentEntity queryRepaymentByAppNoAndStatus(@Param(value="appNo") String appNo,@Param(value="status") String status);

	
}
