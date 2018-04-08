package io.renren.service;

import io.renren.entity.TAppuserAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:20
 */
public interface TAppuserAddressService {
	
	TAppuserAddressEntity queryObject(Long id);
	
	List<TAppuserAddressEntity> queryListByAppUserId(String appUserId);
	
	List<TAppuserAddressEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserAddressEntity tAppuserAddress);
	
	void update(TAppuserAddressEntity tAppuserAddress);
	
	void delete(Long id);
	
	void deleteByAppUserId(Map<String, Object> map);
	
	void deleteBatch(Long[] ids);
}
