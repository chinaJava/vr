package io.renren.api.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserFavoritesEntity;
import io.renren.entity.TGameInfoEntity;
import io.renren.entity.THomeImageEntity;
import io.renren.entity.TVideoInfoEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserFavoritesService;
import io.renren.service.TGameCommentService;
import io.renren.service.TGameInfoService;
import io.renren.service.TGameTypeService;
import io.renren.service.THomeImageService;
import io.renren.service.TVideoInfoService;
import io.renren.service.TokenService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
@RestController
@RequestMapping("/api/tgameinfo")
@Api(value="/api/tgameinfo",description="游戏信息")
public class TGameInfoApiController {
	@Autowired
	private TGameInfoService tGameInfoService;
	@Autowired
	private TGameTypeService gameTypeService;
	@Autowired
	private TAppuserFavoritesService tAppuserFavoritesService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private THomeImageService tHomeImageService;
	@Autowired
	private TVideoInfoService tVideoInfoService;
	@Autowired
	private TGameCommentService tGameCommentService;

	/**
	 * 列表
	 */
	@IgnoreAuth
	@GetMapping("/list")
	@ApiOperation(value="根据游戏总分类，查询游戏信息；排序字段=sidx:DOWNLOADING_ANDROID_NUM=安卓端下载量；如果不传，默认根据ID排序")
	public R list(@RequestParam(defaultValue="1")Integer page,
				  @RequestParam(defaultValue="10")Integer limit,
				  @RequestParam(defaultValue="", required=false)String sidx,
				  @RequestParam(defaultValue="",required=false)String order,
				  @RequestParam(required=false) Integer typeId){
		//查询列表数据
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("page", page);
		params.put("limit", limit);
		params.put("sidx", sidx);
		params.put("order", order);
		params.put("status", "1");
		params.put("typeid", typeId);
        Query query = new Query(params);
		List<TGameInfoEntity> gameInfos= tGameInfoService.queryList(query);
		Map<String, Object> gameComap = new HashMap<>();
		for (TGameInfoEntity tGameInfoEntity : gameInfos) {
			gameComap.put("gameId", tGameInfoEntity.getId());
			tGameInfoEntity.setCommentTotal(tGameCommentService.queryTotal(gameComap));
		}
        return R.ok().put("gameInfos", gameInfos);
	}
	
	
	@IgnoreAuth
	@PostMapping("/getHomePageGameList")
	@ApiOperation(value="首页游戏信息列表page:页码,limit条数"
			+ "isHotGame=1热门游戏;isBoutique=1精品游戏(其他游戏=0);ischarge=1收费(免费=0);isNewGame=1新品游戏;必填参数deviceType=IOS或Android 默认安卓"
			 )
	public R getHomePageGameList(@RequestBody Map<String,Object> map){
		//如果分页信息为空时，设置一个默认值
		if(map.get("page")==null || "".equals(map.get("page"))){
			map.put("page", "1");
		}
		if(map.get("limit")==null || "".equals(map.get("limit"))){
			map.put("limit", "10");
		}
		//如果typeIde为空时，默认查询首页的排行榜下的游戏
		if(map.get("typeId")==null || "".equals(map.get("typeId"))){
			if("IOS".equals(map.get("deviceType"))){
				map.put("sidx", "DOWNLOADING_IOS_NUM");
				//IOS游戏
				map.put("gameChannel", "2");
			}else{
				map.put("sidx", "DOWNLOADING_ANDROID_NUM");
				//安卓游戏
				map.put("gameChannel", "1");
			}
			map.put("order", "desc");
		}
		if(map.get("sidx")==null || "".equals(map.get("sidx"))){
			map.put("sidx", "");
		}
		if(map.get("order")==null || "".equals(map.get("order"))){
			map.put("order", "");
		}
		map.put("status", "1");
		Query query = new Query(map);
		List<TGameInfoEntity> gameInfos= tGameInfoService.queryListByType(query);
		Map<String, Object> imgMap = Maps.newHashMap();
		imgMap.put("status", "1");
		imgMap.put("typeId", "0");
		List<THomeImageEntity> tHomeImageList = tHomeImageService.queryList(imgMap);
		map.remove("sidx");
		List<TVideoInfoEntity> tVideoInfoList = tVideoInfoService.queryList(map);
		int total = tVideoInfoService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tVideoInfoList, total, query.getLimit(), query.getPage());
		query.put("typeid", "31");
		query.put("limit", map.get("applimit"));
		List<TGameInfoEntity> gameApp= tGameInfoService.queryList(query);
		map.clear();
		map.put("gameInfos",gameInfos);
		map.put("tHomeImageList",tHomeImageList);
		map.put("tVideoInfoList",pageUtil);
		map.put("gameApp",gameApp);
		
		return R.ok().put("maps", map);
	}
	
	@IgnoreAuth
	@GetMapping("/info/{id}")
	@ApiOperation(value="根据游戏ID查询游戏详情")
	public R info(@PathVariable("id") Long id){
		TGameInfoEntity tGameInfo = tGameInfoService.queryObject(id);
		return R.ok().put("tGameInfo", tGameInfo);
	}
	

	@PostMapping("/recommend")
	@ApiOperation(value="游戏推荐")
	public R recommend(@RequestHeader("token")String token,@RequestParam("gameId")long gameId){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		if(tAppuserFavoritesService.queryNumByGameIdAndAppuserId(tokenEntity.getUserId(), gameId)>0){
			return R.ok().put("type","2");
		}else{
			TAppuserFavoritesEntity tAppuserFavoritesEntity = new TAppuserFavoritesEntity();
			tAppuserFavoritesEntity.setAppuserId(tokenEntity.getUserId());
			tAppuserFavoritesEntity.setGameId(gameId);
			tAppuserFavoritesEntity.setCreatetime(new Date());
			tAppuserFavoritesEntity.setType("2");
			tAppuserFavoritesService.save(tAppuserFavoritesEntity);
			return R.ok().put("type","2");
		}
	} 
	
	
	@IgnoreAuth
	@GetMapping("/recommendPage")
	@ApiOperation(value="游戏推荐页面")
	public R recommendPage(){
		
		return R.ok().put("recommendPage", tAppuserFavoritesService.queryRecommend());
	}
	
	
	@PostMapping("/collectPage")
	@ApiOperation(value="查询收藏")
	public R collectPage(@RequestHeader("token")String token,@RequestParam("gameId")long gameId){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TAppuserFavoritesEntity tAppuserFavoritesEntity = tAppuserFavoritesService.queryObjectByAppuserIdAndGameId(tokenEntity.getUserId(),gameId);
		if(tAppuserFavoritesEntity==null){
			return R.ok().put("msg","没有收藏");
		}else{
			if(tAppuserFavoritesEntity.getType().equals("1")){
				return R.ok().put("msg","有收藏");
			}else{
				return R.ok().put("msg","没有收藏");	
			}
				
		}
	}
	
	@PostMapping("/collect")
	@ApiOperation(value="根据游戏ID点击收藏")
	public R collect(@RequestHeader("token")String token,@RequestParam("gameId")long gameId){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TAppuserFavoritesEntity tAppuserFavoritesEntity = tAppuserFavoritesService.queryObjectByAppuserIdAndGameId(tokenEntity.getUserId(),gameId);
		if(tAppuserFavoritesEntity!=null){
			if(tAppuserFavoritesEntity.getType().equals("1")){
				tAppuserFavoritesEntity.setCreatetime(new Date());
				tAppuserFavoritesEntity.setGameId(gameId);
				tAppuserFavoritesEntity.setType("0");
				tAppuserFavoritesService.update(tAppuserFavoritesEntity);
				return R.ok().put("type","0");
			}else{
				tAppuserFavoritesEntity.setCreatetime(new Date());
				tAppuserFavoritesEntity.setGameId(gameId);
				tAppuserFavoritesEntity.setType("1");
				tAppuserFavoritesService.update(tAppuserFavoritesEntity);
				return R.ok().put("type","1");
			}
			
		}else{
			TAppuserFavoritesEntity tappuserFavoritesEntity = new TAppuserFavoritesEntity();
			tappuserFavoritesEntity.setAppuserId(tokenEntity.getUserId());
			tappuserFavoritesEntity.setGameId(gameId);
			tappuserFavoritesEntity.setCreatetime(new Date());
			tappuserFavoritesEntity.setType("1");
			tAppuserFavoritesService.save(tappuserFavoritesEntity);
			return R.ok().put("type","1");
		}
		
	}
	@IgnoreAuth
	@PostMapping("/queryAllGame")
	@ApiOperation(value="查询所有游戏,必填参数deviceType=IOS或Android")
	public R queryAllGame(@RequestBody(required=false) Map<String,Object> map){
		if("IOS".equals(map.get("deviceType"))){
			//IOS游戏
			map.put("gameChannel", "2");
		}else{
			//安卓游戏
			map.put("gameChannel", "1");
		}
		return R.ok().put("allGame",tGameInfoService.queryAllGame());
	}
	
	@IgnoreAuth
	@PostMapping("/queryARVRGame")
	@ApiOperation(value="查询ARVR游戏,必填参数deviceType=IOS或Android")
	public R queryARorVRGame(@RequestBody(required=false) Map<String,Object> map){
		//如果分页信息为空时，设置一个默认值
		if(map.get("page")==null || "".equals(map.get("page"))){
			map.put("page", "1");
		}
		if(map.get("limit")==null || "".equals(map.get("limit"))){
			map.put("limit", "999");
		}
		if("IOS".equals(map.get("deviceType"))){
			//IOS游戏
			map.put("gameChannel", "2");
		}else{
			//安卓游戏
			map.put("gameChannel", "1");
		}
		map.put("sidx", "");
		map.put("order", "");
		Query query = new Query(map);
		return R.ok().put("allARVRGame",tGameInfoService.queryARorVRGame(query));
	}
	
	
}
