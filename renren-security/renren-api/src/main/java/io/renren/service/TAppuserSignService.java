package io.renren.service;

import io.renren.entity.TAppuserSignEntity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 13:49:33
 */
public interface TAppuserSignService {
	
	TAppuserSignEntity queryObject(Long id);
	
	List<TAppuserSignEntity> queryList(Map<String, Object> map);
	
	TAppuserSignEntity queryUser(String appuserId);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserSignEntity tAppuserSign);
	
	void update(TAppuserSignEntity tAppuserSign);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	void interrupt(TAppuserSignEntity tAppuserSignEntity);

}
