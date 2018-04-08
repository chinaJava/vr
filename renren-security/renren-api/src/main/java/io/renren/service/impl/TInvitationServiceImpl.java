package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import io.renren.dao.TInvitationDao;
import io.renren.dao.TInvitationLikesDao;
import io.renren.entity.TInvitationEntity;
import io.renren.entity.TInvitationLikesEntity;
import io.renren.service.TInvitationService;



@Service("tInvitationService")
public class TInvitationServiceImpl implements TInvitationService {
	@Autowired
	private TInvitationDao tInvitationDao;
	@Autowired
	private TInvitationLikesDao tLikeDao;
	
	@Override
	public TInvitationEntity queryObject(Long invitationId){
		return tInvitationDao.queryObject(invitationId);
	}
	
	@Override
	public List<TInvitationEntity> queryList(Map<String, Object> map){
		return tInvitationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tInvitationDao.queryTotal(map);
	}
	
	@Override
	public void save(TInvitationEntity tInvitation){
		tInvitationDao.save(tInvitation);
	}
	
	@Override
	public void update(TInvitationEntity tInvitation){
		tInvitationDao.update(tInvitation);
	}
	
	@Override
	public void delete(Long invitationId){
		tInvitationDao.delete(invitationId);
	}
	
	@Override
	public void deleteBatch(Long[] invitationIds){
		tInvitationDao.deleteBatch(invitationIds);
	}

	@Override
	public void updateBatch(Map<String, Object> map) {
		tInvitationDao.updateBatch(map);
	}

	@Override
	public TInvitationEntity queryByUser(String userId) {
		// TODO Auto-generated method stub
		return tInvitationDao.queryByUser(userId);
	}

	@Override
	@Transactional
	public void updateLikes(TInvitationEntity tInvitationObj,
			TInvitationLikesEntity invitationLikesObj,String type) {
		if("add".equals(type)){
			tLikeDao.save(invitationLikesObj);
		}else if("del".equals(type)){
			tLikeDao.delete(invitationLikesObj.getLikeId());
		}
		tInvitationDao.update(tInvitationObj);
	}
}
