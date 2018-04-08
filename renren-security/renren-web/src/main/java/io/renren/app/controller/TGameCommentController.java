package io.renren.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TGameCommentEntity;
import io.renren.service.TGameCommentService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-07-21 10:03:19
 */
@RestController
@RequestMapping("tgamecomment")
public class TGameCommentController {
	@Autowired
	private TGameCommentService tGameCommentService;
//	@Autowired
//	private TGameInfoDao tGameInfoDao;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tgamecomment:list")
	public R list(@RequestParam Map<String, Object> params){
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
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tgamecomment:info")
	public R info(@PathVariable("id") Long id){
		TGameCommentEntity tGameComment = tGameCommentService.queryObject(id);
		return R.ok().put("tGameComment", tGameComment);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("tgamecomment:save")
	public R save(@RequestBody TGameCommentEntity tGameComment){
		//根据游戏名字查询游戏id
//		String gameName=tGameComment.getGameName();
//		TGameInfoEntity tGameInfo=tGameInfoDao.queryObjectName(gameName);
//		if (tGameInfo==null) {
//			throw new RRException("此游戏名不存在");
//		}
//		Long gameid=tGameInfo.getId();
//		tGameComment.setGameid(gameid);
		tGameComment.setCreatetime(new Date());
		tGameComment.setIsRecommend(0);
		tGameComment.setStarLevel(5.0F);
		tGameCommentService.save(tGameComment);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tgamecomment:update")
	public R update(@RequestBody TGameCommentEntity tGameComment){
		TGameCommentEntity commentEntity= tGameCommentService.queryObject(tGameComment.getId());
		if(tGameComment.getStatus()==1 || commentEntity.getStatus()==tGameComment.getStatus()){
			tGameCommentService.update(tGameComment);
		}else{
			tGameCommentService.batchUpdateById(tGameComment);
		}
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tgamecomment:delete")
	public R delete(@RequestBody Long[] ids){
		tGameCommentService.deleteBatch(ids);
		return R.ok();
	}
	
}
