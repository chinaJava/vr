package io.renren.service;

import io.renren.entity.TUserRepaymentEntity;
import io.renren.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 19:16:10
 */
public interface TUserRepaymentService {
	
	TUserRepaymentEntity queryObject(Long id);
	
	List<TUserRepaymentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserRepaymentEntity tUserRepayment);
	
	void update(TUserRepaymentEntity tUserRepayment);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	R userPayment(Map<String, Object> map);

	R callback(String appNo);

}
