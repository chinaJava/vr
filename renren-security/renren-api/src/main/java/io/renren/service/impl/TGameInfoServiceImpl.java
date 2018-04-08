package io.renren.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.renren.dao.TGameInfoDao;
import io.renren.entity.TGameInfoEntity;
import io.renren.entity.TGameLabelTempEntity;
import io.renren.entity.TGameRecommentEntity;
import io.renren.service.TGameInfoService;
import io.renren.utils.Query;



@Service("tGameInfoService")
public class TGameInfoServiceImpl implements TGameInfoService {
	private static final Logger LOGGER=LoggerFactory.getLogger(TGameInfoServiceImpl.class);
	@Autowired
	private TGameInfoDao tGameInfoDao;
	
	@Override
	public TGameInfoEntity queryObject(Long id){
		TGameInfoEntity gameInfoEntity=tGameInfoDao.queryObject(id);
		List<TGameLabelTempEntity> gameLabelTemps=tGameInfoDao.queryListByLabelId(gameInfoEntity.getId());
		String labelTmp="";
		for (int i = 0; i < gameLabelTemps.size(); i++) {
			labelTmp+=gameLabelTemps.get(i).getLabelName()+",";
		}
		gameInfoEntity.setGameLable(labelTmp);	
		return gameInfoEntity;
		
	}
	
	@Override
	public List<TGameInfoEntity> queryList(Map<String, Object> map){
		return tGameInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGameInfoDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(TGameInfoEntity tGameInfo){
		try {
			tGameInfoDao.save(tGameInfo);
			String gameLable=tGameInfo.getGameLable();
			String[] lable=gameLable.trim().split(",");
			saveByGameIdAndLable(lable,tGameInfo.getId());
		} catch (Exception e) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("保存游戏信息失败 {}=",e);
			}
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public void update(TGameInfoEntity tGameInfo){
		
		tGameInfoDao.deleteByGameId(tGameInfo.getId());
		String[] lable=tGameInfo.getGameLable().trim().split(",");
		saveByGameIdAndLable(lable,tGameInfo.getId());
		tGameInfoDao.update(tGameInfo);
	}
	
	
	/**
	 * @param arr 所有标签
	 * @param gameId 游戏ID
	 * @return 根据游戏ID，批量保存游戏标签
	 */
	private Integer saveByGameIdAndLable(String[] arr,Long gameId){
		List<TGameLabelTempEntity> gameLableTemps=new ArrayList<TGameLabelTempEntity>();
		for (int i = 0; i < arr.length; i++) {
			TGameLabelTempEntity TGameLabelTempEntity=new TGameLabelTempEntity();
			TGameLabelTempEntity.setGameid(gameId);
			TGameLabelTempEntity.setLabelid(Integer.valueOf(arr[i]));
			gameLableTemps.add(TGameLabelTempEntity);
		}
		Integer result =tGameInfoDao.saveByGameIdAndLable(gameLableTemps);
		return result;
	}
	
	@Override
	public void delete(Long id){
		tGameInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tGameInfoDao.deleteBatch(ids);
	}

	@Override
	public List<TGameLabelTempEntity> queryListByGameId(Long gameid) {
		return tGameInfoDao.queryListByGameId(gameid);
	}

	
	/**
	 * @author lw
	 * @param 分页查询参数
	 * @date 2017年8月9日下午4:04:37
	 * @description  
	 */
	@Override
	public List<TGameInfoEntity> queryListByType(Query query) {
		try {
			List<TGameInfoEntity> list=tGameInfoDao.queryListByType( query);
			for (TGameInfoEntity tGameInfoEntity : list) {
				List<TGameLabelTempEntity> gameLables=tGameInfoDao.queryListByLabelId(tGameInfoEntity.getId());
				if(gameLables!=null && gameLables.size()>0){
					String arr="";
					for (int i = 0; i < gameLables.size(); i++) {
						arr+=gameLables.get(i).getLabelName()+",";
					}
					tGameInfoEntity.setGameLable(arr.toString());
				}
			}
			return list;
		} catch (Exception e) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("游戏列表查询失败 {}=",e);
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TGameInfoEntity> queryGameName(String name) {
		return tGameInfoDao.queryGameName(name);
	}

	@Override
	public void updateGameStatus(TGameInfoEntity tGameInfo) {
		 tGameInfoDao.update(tGameInfo);
	}

	@Override
	public List<TGameRecommentEntity> queryListByReCommend() {
		// TODO Auto-generated method stub
		return tGameInfoDao.queryListByReCommend();
	}
	
	public List<TGameInfoEntity> queryAllGame(){
		return tGameInfoDao.queryAllGame();
	}

	@Override
	public List<TGameInfoEntity> queryARorVRGame(Map<String, Object> map) {
		return tGameInfoDao.queryARorVRGame(map);
	}
}
