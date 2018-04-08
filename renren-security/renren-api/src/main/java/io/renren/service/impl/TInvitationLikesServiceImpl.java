package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TInvitationLikesDao;
import io.renren.entity.TInvitationLikesEntity;
import io.renren.service.TInvitationLikesService;



@Service("tLikeService")
public class TInvitationLikesServiceImpl implements TInvitationLikesService {
	@Autowired
	private TInvitationLikesDao tLikeDao;
	
	@Override
	public TInvitationLikesEntity queryObject(Long likeId){
		return tLikeDao.queryObject(likeId);
	}
	
	@Override
	public List<TInvitationLikesEntity> queryList(Map<String, Object> map){
		return tLikeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tLikeDao.queryTotal(map);
	}
	
	@Override
	public void save(TInvitationLikesEntity tLike){
		tLikeDao.save(tLike);
	}
	
	@Override
	public void update(TInvitationLikesEntity tLike){
		tLikeDao.update(tLike);
	}
	
	@Override
	public void delete(Long likeId){
		tLikeDao.delete(likeId);
	}
	
	@Override
	public void deleteBatch(Long[] likeIds){
		tLikeDao.deleteBatch(likeIds);
	}

	@Override
	public void updateBatch(Map<String, Object> map) {
		tLikeDao.updateBatch(map);
	}

	@Override
	public TInvitationLikesEntity get(Map<String, Object> map) {
		return tLikeDao.get(map);
	}

	@Override
	public TInvitationLikesEntity queryObjectByUserId(Map<String, Object> map) {
		return tLikeDao.queryObjectByUserId(map);
	}
	
}
