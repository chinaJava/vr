package io.renren.service;

import io.renren.entity.TGameCommentEntity;
import io.renren.utils.Query;
import io.renren.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
public interface TGameCommentService {
	
	TGameCommentEntity queryObject(Long id);
	
	List<TGameCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGameCommentEntity tGameComment);
	
	void update(TGameCommentEntity tGameComment);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void batchUpdateById(TGameCommentEntity tGameComment);


}
