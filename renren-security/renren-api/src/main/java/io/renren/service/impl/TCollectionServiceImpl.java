package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TCollectionDao;
import io.renren.entity.TCollectionEntity;
import io.renren.service.TCollectionService;



@Service("tCollectionService")
public class TCollectionServiceImpl implements TCollectionService {
	@Autowired
	private TCollectionDao tCollectionDao;
	
	@Override
	public TCollectionEntity queryObject(Long collectionId){
		return tCollectionDao.queryObject(collectionId);
	}
	
	@Override
	public List<TCollectionEntity> queryList(Map<String, Object> map){
		return tCollectionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCollectionDao.queryTotal(map);
	}
	
	@Override
	public void save(TCollectionEntity tCollection){
		tCollectionDao.save(tCollection);
	}
	
	@Override
	public void update(TCollectionEntity tCollection){
		tCollectionDao.update(tCollection);
	}
	
	@Override
	public void delete(Long collectionId){
		tCollectionDao.delete(collectionId);
	}
	
	@Override
	public void deleteBatch(Long[] collectionIds){
		tCollectionDao.deleteBatch(collectionIds);
	}

	

	@Override
	public void updateBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		tCollectionDao.updateBatch(map);
	}

	
	@Override
	public TCollectionEntity get(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tCollectionDao.get(map);
	}

	/* (non-Javadoc)
	 * @see io.renren.service.TCollectionService#queryByInvitation(java.lang.Long)
	 */
	@Override
	public TCollectionEntity queryByInvitation(Long invitationId) {
		// TODO Auto-generated method stub
		return tCollectionDao.queryByInvitation(invitationId);
	}

	
	
}
