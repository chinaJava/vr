package io.renren.service;

import io.renren.entity.TAppuserMemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-14 10:23:23
 */
public interface TAppuserMemberService {
	
	TAppuserMemberEntity queryObject(Integer id);
	
	List<TAppuserMemberEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TAppuserMemberEntity tAppuserMember);
	
	void update(TAppuserMemberEntity tAppuserMember);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

}
