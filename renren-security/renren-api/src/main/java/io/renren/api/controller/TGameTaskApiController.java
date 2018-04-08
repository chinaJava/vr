package io.renren.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.RequestGameTakEntity;
import io.renren.entity.TAppuserTaskEntity;
import io.renren.entity.TGameTaskEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserTaskService;
import io.renren.service.TGameTaskService;
import io.renren.service.TokenService;
import io.renren.utils.Base64Util;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.PropertiesUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-06 17:17:34
 */
@RestController
@RequestMapping("/api/tgametask")
@Api(value="/api/tgametask",description="游戏任务信息接口")
public class TGameTaskApiController {
	@Autowired
	private TGameTaskService tGameTaskService;
	@Autowired
    private TokenService tokenService;
	@Autowired
	private TAppuserTaskService tAppuserTaskService;
	
	/**
	 * 列表
	 */
	@IgnoreAuth
	@PostMapping("/list")
	@ApiOperation(value="查询游戏任务列表,page=1当前页数，limit=10每页显示记录数,taskType=1:性价比2:最新上线3:金币最多")
	public R list(@RequestBody Map<String, Object> params){
		//默认按照ID大小排序
		if(params.get("sidx")==null){
			params.put("sidx", "");
		}
		if(params.get("order")==null){
			params.put("order", "");
		}
		if(params.get("page")==null){
			params.put("page", "1");
		}
		if(params.get("limit")==null){
			params.put("limit", "999");
		}
		//查询列表数据
        Query query = new Query(params);
		List<TGameTaskEntity> tGameTaskList = tGameTaskService.queryList(query);
		int total = tGameTaskService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tGameTaskList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	@IgnoreAuth
	@GetMapping("/detail/{taskId}")
	@ApiOperation(value="根据任务ID查询任务详情")
	public R getDetail(@PathVariable("taskId") Long taskId){
		TGameTaskEntity tGameTask = tGameTaskService.queryObjectById(taskId);
		return R.ok().put("tGameTask", tGameTask);
	}
	
	@PostMapping("/getMyTaskList")
	@ApiOperation(value="查询当前用户所有任务列表,page=1当前页数，limit=10每页显示记录数可传入myTaskStatus查询对应状态;返回数据中myTaskStatus=1:进行中2:已完成3:已领奖4:已结束")
	public R getMyTaskList(@RequestHeader("token") String token,@RequestBody Map<String, Object> params){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		params.put("appuserId", tokenEntity.getUserId());
		//默认按照ID大小排序
		if(params.get("sidx")==null){
			params.put("sidx", "");
		}
		if(params.get("order")==null){
			params.put("order", "");
		}
		if(params.get("page")==null){
			params.put("page", "1");
		}
		if(params.get("limit")==null){
			params.put("limit", "999");
		}
		//查询列表数据
		Query query = new Query(params);
		List<TGameTaskEntity> tGameTaskList = tGameTaskService.queryList(query);
		int total = tGameTaskService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tGameTaskList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	@PostMapping("/takeTask")
	@ApiOperation(value="领取任务，传入参数taskId任务ID")
	public R takeTask(@RequestHeader("token") String token,@RequestBody Map<String, Long> params){
		if(params.get("taskId")==null){
			return R.error("任务信息不能为空");
		}
		Long taskId = params.get("taskId");
		TGameTaskEntity tGameTask = tGameTaskService.queryObject(taskId);
		String msg = null;
		boolean flag = false;
		if(tGameTask!=null){
			//任务结束时间大于当前系统时间（任务还未结束）
			if(tGameTask.getDeadline().getTime() > System.currentTimeMillis()){
				TokenEntity tokenEntity = tokenService.queryByToken(token);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("taskId", taskId);
				map.put("appuserId", tokenEntity.getUserId());
				if(tAppuserTaskService.queryObjectByTaskId(map)==null){
					TAppuserTaskEntity userTaskObj = new TAppuserTaskEntity();
					userTaskObj.setAppuserId(tokenEntity.getUserId());
					userTaskObj.setReceiveTime(new Date());
					userTaskObj.setTaskId(taskId);
					userTaskObj.setStatus("1");//进行中
					tAppuserTaskService.save(userTaskObj);
					flag = true;
				}else{
					msg = "该游戏任务已经领取，无法再次领取";
				}
			}else{
				msg = "该游戏任务已经结束，无法领取";
			}
		}else{
			msg = "该游戏任务不存在，无法领取";
		}
		if(flag){
			return R.ok();
		}else{
			return R.error(msg);
		}
	}
	
	@PostMapping("/finishTask")
	@ApiOperation(value="完成任务")
	public R finishTask(@RequestHeader("token") String token,@RequestBody RequestGameTakEntity taskEntity) throws IOException{

		TGameTaskEntity tGameTask = tGameTaskService.queryObject(taskEntity.getTaskId());
		String msg = null;
		boolean flag = false;
		if(tGameTask!=null){
			//任务结束时间大于当前系统时间（任务还未结束）
			TokenEntity tokenEntity = tokenService.queryByToken(token);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskId", taskEntity.getTaskId());
			map.put("appuserId", tokenEntity.getUserId());
			TAppuserTaskEntity userTaskObj = tAppuserTaskService.queryObjectByTaskId(map);
			if(userTaskObj!=null){
				if(tGameTask.getDeadline().getTime() >= System.currentTimeMillis()){
					String nowDay = DateUtils.getFormatedDayStr(new Date()).replaceAll("\\-", "");
					String url_path = "";
					if(taskEntity.getFiles()!=null){
						for(MultipartFile file : taskEntity.getFiles()){
							String filename = file.getOriginalFilename();
							String path = PropertiesUtils.getStringByKey("MIRROR_SPACE_UPLOAD_PATH")+File.separator+nowDay;
							url_path += PropertiesUtils.getStringByKey("MIRROR_SPACE_URL") +File.separator+nowDay+File.separator + filename+";";
							Base64Util.uploadFile(file, path);
						}
					}
					userTaskObj.setFinishTaskPic(url_path);
					userTaskObj.setFinishTime(new Date());
					userTaskObj.setStatus("2");//已完成
					tAppuserTaskService.update(userTaskObj);
					flag = true;
				}else{
					userTaskObj.setStatus("4");//任务已经结束
					tAppuserTaskService.update(userTaskObj);
					msg = "该游戏任务已经结束";
				}
			}else{
				msg = "您还未领取改游戏任务";
			}
		}else{
			msg = "该游戏任务不存在";
		}
		if(flag){
			return R.ok();
		}else{
			return R.error(msg);
		}
	}
}
