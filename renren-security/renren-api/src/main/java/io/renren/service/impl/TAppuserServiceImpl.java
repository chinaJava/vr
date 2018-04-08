package io.renren.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.renren.dao.TAppuserDao;
import io.renren.dao.TAppuserDetailDao;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TAppuserEntity;
import io.renren.service.TAppuserService;
import io.renren.utils.Constant;
import io.renren.utils.RRException;
import io.renren.validator.Assert;



@Service("tAppuserService")
public class TAppuserServiceImpl implements TAppuserService {
	@Autowired
	private TAppuserDao tAppuserDao;
	@Autowired
	private TAppuserDetailDao tAppuserDetailDao;
	
	@Override
	public TAppuserEntity queryObject(String id){
		return tAppuserDao.queryObject(id);
	}
	
	@Override
	public List<TAppuserEntity> queryList(Map<String, Object> map){
		return tAppuserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tAppuserDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(TAppuserEntity tAppuser){
		tAppuser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tAppuser.setPassword(DigestUtils.sha256Hex(tAppuser.getPassword()));
		TAppuserEntity userObj = tAppuserDao.queryObjectByMobile(tAppuser.getMobile());
		Assert.isNotNull(userObj, "手机号已经注册");
		//初始化用户详情信息
		TAppuserDetailEntity appuserDetail = new TAppuserDetailEntity();
		appuserDetail.setAppuserid(tAppuser.getId());
		appuserDetail.setNikename(tAppuser.getUsername());
		//appuserDetail.setSex(Constant.Sex.FEMALE.getValue());
		tAppuserDetailDao.save(appuserDetail);
		tAppuserDao.save(tAppuser);
	}
	
	@Override
	public void update(TAppuserEntity tAppuser){
		TAppuserEntity userObj = tAppuserDao.queryObjectByMobile(tAppuser.getMobile());
		if(!userObj.getPassword().equals(tAppuser.getPassword())){
			tAppuser.setPassword(DigestUtils.sha256Hex(tAppuser.getPassword()));
		}
		tAppuserDao.update(tAppuser);
	}
	
	@Override
	public void delete(String id){
		tAppuserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		tAppuserDao.deleteBatch(ids);
	}

	@Override
	public TAppuserEntity queryObjectByMobile(String mobile) {
		return tAppuserDao.queryObjectByMobile(mobile);
	}

	@Override
	public TAppuserEntity queryObjectByUsername(String username) {
		return tAppuserDao.queryObjectByUsername(username);
	}
	
	@Override
	public TAppuserEntity queryObjectByOpenId(String openId) {
		return tAppuserDao.queryObjectByOpenId(openId);
	}
	
	@Override
	public String login(String username, String mobile, String password) {
		TAppuserEntity user = null;
		if(username!=null && !"".equals(username)){
			user = queryObjectByUsername(username);
		}
		//根据用户名没有查询到用户，则根据手机继续查询
		if(user==null || (user.getId()==null || "".equals(user.getId()))){
			user = queryObjectByMobile(mobile);
		}
		Assert.isNull(user, "用户名、手机号或密码错误");
		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(password))){
			throw new RRException("用户名、手机号或密码错误");
		}
		return user.getId();
	}
	
}
