package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.renren.dao.TGameCommentDao;
import io.renren.dao.TGameInfoDao;
import io.renren.entity.TGameCommentEntity;
import io.renren.entity.TGameInfoEntity;
import io.renren.service.TGameCommentService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.RRException;



@Service("tGameCommentService")
public class TGameCommentServiceImpl implements TGameCommentService {
	@Autowired
	private TGameCommentDao tGameCommentDao;
	@Autowired
	private TGameInfoDao tGameInfoDao;
	
	@Override
	public TGameCommentEntity queryObject(Long id){
		return tGameCommentDao.queryObject(id);
	}
	
	@Override
	public List<TGameCommentEntity> queryList(Map<String, Object> map){
		return tGameCommentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameCommentDao.queryTotal(map);
	}
	
	@Override
	public void save(TGameCommentEntity tGameComment){
		
		tGameCommentDao.save(tGameComment);
	}
	
	@Override
	public void update(TGameCommentEntity tGameComment){
		//根据游戏名字查询游戏id
		//String gameName=tGameComment.getGameName();
		
		TGameInfoEntity tGameInfo=tGameInfoDao.queryObject(tGameComment.getGameid());
		if (tGameInfo==null) {
			throw new RRException("此游戏名不存在");
		}
//		Long gameid=tGameInfo.getId();
//		tGameComment.setGameid(gameid);
		tGameCommentDao.update(tGameComment);
	}
	
	@Override
	public void delete(Long id){
		tGameCommentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tGameCommentDao.deleteBatch(ids);
	}

	@Override
	@Transactional
	public void batchUpdateById(TGameCommentEntity tGameComment) {
		tGameCommentDao.batchUpdateById(tGameComment.getId(),tGameComment.getStatus());
	}

	

}
