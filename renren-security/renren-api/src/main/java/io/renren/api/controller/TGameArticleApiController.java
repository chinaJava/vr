package io.renren.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TGameArticleEntity;
import io.renren.service.TGameArticleService;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/** 
 * 类名称: TGameArticleApiController 获取游戏相关文章接口
 * 描述: TODO
 * @create 2017年7月29日 下午1:58:35 
 * @author llh  
 * @version 1.0.0 
 */
@RestController
@RequestMapping("/api/tgamearticle")
@Api(value="/api/tgamearticle",description="获取游戏相关文章")
public class TGameArticleApiController {
	@Autowired
	private TGameArticleService tGameArticleService;
	
	
	@IgnoreAuth
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ApiOperation(value = "根据游戏ID或者文章类型ID查询文章内容")
	public R getArticleListByGameID(@RequestParam("gameId") Long gameId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameid", gameId);
		map.put("noTypeid", 4);
		map.put("page", 1);
		map.put("limit", 3);
		map.put("sidx", "");
		map.put("order","");
		List<TGameArticleEntity> tGameArticleList = tGameArticleService.queryListByGame(map);
		return R.ok().put("gameArticleList", tGameArticleList);
	}
	
	@IgnoreAuth
	@RequestMapping(value = "/queryAnnouncement",method = RequestMethod.GET)
	@ApiOperation(value = "查询游戏公告")
	public R queryAnnouncement(@RequestParam("gameId") Long gameId,@RequestParam("typeId") Integer typeId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameid", gameId);
		map.put("typeid", typeId);
		map.put("page", 1);
		map.put("limit", 3);
		map.put("sidx", "");
		map.put("order","");
		List<TGameArticleEntity> tGameArticleList = tGameArticleService.queryListByGame(map);
		return R.ok().put("gameArticleList", tGameArticleList);
	}
	
	@IgnoreAuth
	@GetMapping("queryGameArticleById")
	@ApiOperation(value="根据id查询文章信息")
	public R queryTGameArticleById(@RequestParam("id") Long id){
		TGameArticleEntity gameArticle= tGameArticleService.queryObject(id);
		return R.ok().put("gameArticle", gameArticle);
	}
}
