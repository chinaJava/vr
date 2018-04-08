package io.renren.api.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserSignEntity;
import io.renren.entity.TAppuserSigntimeEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TAppuserSignService;
import io.renren.service.TAppuserSigntimeService;
import io.renren.service.TokenService;
import io.renren.utils.DateUtils;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-09 13:49:33
 */
@RestController
@RequestMapping("/api/tappusersign")
@Api(value="/api/tappusersign",description="app用户签到")
public class TAppuserSignApiController {
	@Autowired
	private TAppuserSignService tAppuserSignService;
	@Autowired
	private TAppuserSigntimeService tAppuserSigntimeService;
	@Autowired
    private TokenService tokenService;
	@Autowired
	private TAppuserDetailService tAppuserDetailService;
	
	@PostMapping("sign")
	@ApiOperation(value = "用户签到")
	@Transactional
	public R sign(@RequestHeader("token") String token){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		//判断是否数据库是否存在数据了
		if(tAppuserSigntimeService.queryTotalByToday(tokenEntity.getUserId())==0){
			TAppuserSignEntity tAppuserSignEntity = tAppuserSignService.queryUser(tokenEntity.getUserId());
			Date date = new Date();
			//日期格式转换
			Calendar today = Calendar.getInstance();
			Calendar lastday = Calendar.getInstance();
			
			//判断是否为第一次签到
			if(tAppuserSignEntity==null){
				tAppuserSignEntity = new TAppuserSignEntity();
				tAppuserSignEntity.setAppuserId(tokenEntity.getUserId());
				tAppuserSignEntity.setSignTime(date);
				tAppuserSignEntity.setSignAllCount(1);
				tAppuserSignEntity.setSignCount(1);
				tAppuserSignEntity.setLastSignDate(date);
				tAppuserSignService.save(tAppuserSignEntity);
			}else{
				//今天日期转换
				today.setTime(date);
				lastday.setTime(tAppuserSignEntity.getLastSignDate());
				tAppuserSignEntity.setLastSignDate(date);
				tAppuserSignEntity.setSignTime(date);
				if(DateUtils.differenceDaysToCount(lastday, today)>2){
					tAppuserSignService.interrupt(tAppuserSignEntity);
					//签到中断后，连续签到天数设为1
					tAppuserSignEntity.setSignCount(1);
				}else if(DateUtils.differenceDaysToCount(lastday, today)==2){
					tAppuserSignService.update(tAppuserSignEntity);
					//签到没中断，连续签到天数加1
					tAppuserSignEntity.setSignCount(tAppuserSignEntity.getSignCount()+1);
				}
				//总签到数加1
				tAppuserSignEntity.setSignAllCount(tAppuserSignEntity.getSignAllCount()+1);
			}
			//添加金币
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("appuserId", tokenEntity.getUserId());
			
			if(tAppuserSignEntity.getSignCount()>0&&tAppuserSignEntity.getSignCount()<7){
				map.put("gameGold",5+(tAppuserSignEntity.getSignCount()-1));
			}else{
				map.put("gameGold",20);
			}
			tAppuserDetailService.updateGameGold(map);
			//签到历史实体类
			TAppuserSigntimeEntity  tAppuserSigntimeEntity = new TAppuserSigntimeEntity();
			tAppuserSigntimeEntity.setAppuserId(tokenEntity.getUserId());
			tAppuserSigntimeEntity.setSignTime(date);
			tAppuserSigntimeService.save(tAppuserSigntimeEntity);
			
			//今天签到总数
			tAppuserSignEntity.setSignCountToday(tAppuserSigntimeService.queryAllByToDay());
			return R.ok().put("sign",tAppuserSignEntity);
		}else{
			return R.error("今日您已经签到");
		}
	}
	
	@PostMapping("signHistory")
	@ApiOperation(value = "用户签到历史")
	public R signHistory(@RequestHeader("token") String token){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		List<TAppuserSigntimeEntity> tappuserSigntimeEntitys = tAppuserSigntimeService.queryListByappuserId(tokenEntity.getUserId());
		//今日签到人数
		int allSignNum_today = tAppuserSigntimeService.queryAllByToDay();
		TAppuserSignEntity tAppuserSignEntity = new TAppuserSignEntity();
		Logger log=Logger.getLogger(TAppuserSignApiController.class);
		//验证签到历史是否为空
		if(!tappuserSigntimeEntitys.isEmpty() && tappuserSigntimeEntitys != null){
			
			StringBuffer sb = new StringBuffer();
			for(TAppuserSigntimeEntity ts:tappuserSigntimeEntitys){
				String dateformat = DateUtils.format(ts.getSignTime());
				sb.append(dateformat+";");
			}
			tAppuserSignEntity = tAppuserSignService.queryUser(tokenEntity.getUserId());
			if(tAppuserSignEntity!=null && tAppuserSignEntity.getId()>0){
				tAppuserSignEntity.setSignhistroy(sb);
				Calendar today = Calendar.getInstance();
				today.setTime(new Date());
				Calendar lastday = Calendar.getInstance();
				lastday.setTime(tAppuserSignEntity.getSignTime());
				if(DateUtils.differenceDaysToCount(lastday, today)==1){
					tAppuserSignEntity.setStatus(false);
				}else{
					tAppuserSignEntity.setStatus(true);
				}
				if(DateUtils.differenceDaysToCount(lastday, today)>2){
					tAppuserSignEntity.setSignCount(0);
				}
				tAppuserSignEntity.setSignCountToday(allSignNum_today);
			}
			return R.ok().put("signHistory",tAppuserSignEntity);
		}else{
			tAppuserSignEntity.setStatus(true);
			tAppuserSignEntity.setSignCountToday(allSignNum_today);
			return R.ok().put("signHistory",tAppuserSignEntity);
		}
		
		//设置今日签到人数
		
	}
	
}
