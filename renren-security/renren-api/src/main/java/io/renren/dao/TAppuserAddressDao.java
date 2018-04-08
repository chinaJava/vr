package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.TAppuserAddressEntity;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:20
 */
public interface TAppuserAddressDao extends BaseDao<TAppuserAddressEntity> {
	
	List<TAppuserAddressEntity> queryListByAppUserId(String appUserId);
	
	void deleteByAppUserId(Map<String, Object> map);
}
