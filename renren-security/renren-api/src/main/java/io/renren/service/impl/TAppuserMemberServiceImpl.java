package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TAppuserMemberDao;
import io.renren.entity.TAppuserMemberEntity;
import io.renren.service.TAppuserMemberService;



@Service("tAppuserMemberService")
public class TAppuserMemberServiceImpl implements TAppuserMemberService {
	@Autowired
	private TAppuserMemberDao tAppuserMemberDao;
	

	@Override
	public TAppuserMemberEntity queryObject(Integer id){
		return tAppuserMemberDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserMemberEntity> queryList(Map<String, Object> map){
		return tAppuserMemberDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserMemberDao.queryTotal(map);
	}
	
	@Override
	public void save(TAppuserMemberEntity tAppuserMember){
		tAppuserMemberDao.save(tAppuserMember);
	}
	
	@Override
	public void update(TAppuserMemberEntity tAppuserMember){
		tAppuserMemberDao.update(tAppuserMember);
	}
	
	@Override
	public void delete(Integer id){
		tAppuserMemberDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tAppuserMemberDao.deleteBatch(ids);
	}
	
}
