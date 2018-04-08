package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import io.renren.entity.TGameInfoEntity;
import io.renren.entity.TGameLabelTempEntity;
import io.renren.entity.TGameRecommentEntity;
import io.renren.utils.Query;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameInfoDao extends BaseDao<TGameInfoEntity> {
	
	List<TGameLabelTempEntity>  queryListByGameId(Long gameid);

	List<TGameInfoEntity> queryListByType(Query query);

	Integer saveByGameIdAndLable(List<TGameLabelTempEntity> gameLableTemps);

	void deleteByGameId(Long id);

	List<TGameInfoEntity> queryGameName(@Param(value="name") String name);

	List<TGameLabelTempEntity> queryListByLabelId(Long id);

	TGameInfoEntity queryObjectName(String name);
	
	List<TGameRecommentEntity> queryListByReCommend();
	
	List<TGameInfoEntity> queryAllGame();
	
	List<TGameInfoEntity> queryARorVRGame(Map<String, Object> map);
}
