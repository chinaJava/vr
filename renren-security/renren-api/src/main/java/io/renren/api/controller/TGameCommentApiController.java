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

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TGameCommentEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TGameCommentService;
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
@RequestMapping("/api/tgamecomment")
@Api(value="/api/tgamecomment",description="游戏评论")
public class TGameCommentApiController {
	@Autowired
	private TGameCommentService tGameCommentService;
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 列表
	 */
	@IgnoreAuth
	@GetMapping("/list")
	@ApiOperation(value="根据游戏id查询游戏评论")
	public R list(@RequestParam(defaultValue="1")Integer page,
				 @RequestParam(defaultValue="10")Integer limit,
			     @RequestParam(required=true)Long gameId,
			     @RequestParam(required=false)Long pId,
			     @RequestParam(required=false)Long id){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("page", page);
		params.put("limit", limit);
		params.put("sidx", "");
		params.put("order", "");
		params.put("gameId", gameId);
		params.put("status", "1");
		params.put("pId", pId);
		params.put("id", id);
		//查询列表数据
        Query query = new Query(params);
		List<TGameCommentEntity> tGameCommentList = tGameCommentService.queryList(query);
		int total = tGameCommentService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tGameCommentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@IgnoreAuth
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		TGameCommentEntity tGameComment = tGameCommentService.queryObject(id);
		return R.ok().put("tGameComment", tGameComment);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation(value="保存游戏评论")
	public R save(@RequestHeader String token,@RequestBody TGameCommentEntity tGameComment){
		TokenEntity tokenEntity=tokenService.queryByToken(token);
		tGameComment.setAppuserid(tokenEntity.getUserId());
		tGameComment.setStatus(1);
		tGameComment.setIsRecommend(0);
		tGameComment.setStarLevel(0f);
		tGameComment.setCreatetime(new Date());
		tGameCommentService.save(tGameComment);
		return R.ok();
	}
	
}
