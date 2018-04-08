package io.renren.service;

import io.renren.entity.TGameInfoEntity;
import io.renren.entity.TGameLabelTempEntity;
import io.renren.entity.TGameRecommentEntity;
import io.renren.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameInfoService {
	
	TGameInfoEntity queryObject(Long id);
	
	List<TGameInfoEntity> queryList(Map<String, Object> map);
	
	List<TGameLabelTempEntity>  queryListByGameId(Long gameid);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameInfoEntity tGameInfo);
	
	void update(TGameInfoEntity tGameInfo);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<TGameInfoEntity> queryListByType(Query query);

	List<TGameInfoEntity> queryGameName(String name);

	void updateGameStatus(TGameInfoEntity tGameInfo);

	List<TGameRecommentEntity> queryListByReCommend();
	
	List<TGameInfoEntity> queryAllGame();
	
	List<TGameInfoEntity> queryARorVRGame(Map<String, Object> map);
}
