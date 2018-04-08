package io.renren.app.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserMemberEntity;
import io.renren.service.TAppuserMemberService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-14 10:23:23
 */
@RestController
@RequestMapping("tappusermember")
public class TAppuserMemberController {
	@Autowired
	private TAppuserMemberService tAppuserMemberService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tappusermember:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TAppuserMemberEntity> tAppuserMemberList = tAppuserMemberService.queryList(query);
		int total = tAppuserMemberService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tAppuserMemberList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tappusermember:info")
	public R info(@PathVariable("id") Integer id){
		TAppuserMemberEntity tAppuserMember = tAppuserMemberService.queryObject(id);
		
		return R.ok().put("tAppuserMember", tAppuserMember);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tappusermember:save")
	public R save(@RequestBody TAppuserMemberEntity tAppuserMember){
		tAppuserMemberService.save(tAppuserMember);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tappusermember:update")
	public R update(@RequestBody TAppuserMemberEntity tAppuserMember){
		tAppuserMemberService.update(tAppuserMember);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tappusermember:delete")
	public R delete(@RequestBody Integer[] ids){
		tAppuserMemberService.deleteBatch(ids);
		return R.ok();
	}
	
}
