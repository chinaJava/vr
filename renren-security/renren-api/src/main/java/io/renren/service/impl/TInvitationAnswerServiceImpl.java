package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TInvitationAnswerDao;
import io.renren.entity.TInvitationAnswerEntity;
import io.renren.service.TInvitationAnswerService;



@Service("tAnswerService")
public class TInvitationAnswerServiceImpl implements TInvitationAnswerService {
	@Autowired
	private TInvitationAnswerDao tAnswerDao;
	
	@Override
	public TInvitationAnswerEntity queryObject(Long answerId){
		return tAnswerDao.queryObject(answerId);
	}
	
	@Override
	public List<TInvitationAnswerEntity> queryList(Map<String, Object> map){
		return tAnswerDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAnswerDao.queryTotal(map);
	}
	
	@Override
	public void save(TInvitationAnswerEntity tAnswer){
		tAnswerDao.save(tAnswer);
	}
	
	@Override
	public void update(TInvitationAnswerEntity tAnswer){
		tAnswerDao.update(tAnswer);
	}
	
	@Override
	public void delete(Long answerId){
		tAnswerDao.delete(answerId);
	}
	
	@Override
	public void deleteBatch(Long[] answerIds){
		tAnswerDao.deleteBatch(answerIds);
	}

	@Override
	public void updateBatch(Map<String, Object> map) {
		tAnswerDao.updateBatch(map);
	}

	@Override
	public TInvitationAnswerEntity queryOne(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return tAnswerDao.queryOne(param);
	}

	@Override
	public int queryTotalByChild(Long answerId) {

		return tAnswerDao.queryTotalByChild(answerId);
	}
	
	
}
