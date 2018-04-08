package io.renren.service;

import io.renren.entity.TAppuserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:20
 */
public interface TAppuserService {
	
	TAppuserEntity queryObject(String id);
	
	TAppuserEntity queryObjectByMobile(String mobile);
	
	TAppuserEntity queryObjectByUsername(String username);
	
	TAppuserEntity queryObjectByOpenId(String openId);
	
	List<TAppuserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserEntity tAppuser);
	
	void update(TAppuserEntity tAppuser);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	/**
	 * 用户登录
	 * @param mobile    手机号
	 * @param password  密码
	 * @return          返回用户ID
	 */
	String login(String username, String mobile, String password);
}
