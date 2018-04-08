package io.renren.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TVideoInfoEntity;
import io.renren.service.TVideoInfoService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/tvideoinfo")
@Api(value="/api/tvideoinfo",description="视频接口管理类")
public class TVideoInfoApiController {
	@Autowired
	private TVideoInfoService tVideoInfoService;
	
	/**
	 * 列表
	 */
	@IgnoreAuth
	@PostMapping("/list")
	@ApiOperation(value = "视频查询列表，传入信息(不传参数时默认查询全部记录)：page页数，limit每页记录数")
	public R list(@RequestBody Map<String, Object> params){
		if(params.get("page")==null || "".equals(params.get("page"))){
			params.put("page", "1");
		}
		if(params.get("limit")==null || "".equals(params.get("limit"))){
			params.put("limit", "999");
		}
		params.put("sidx", "");
		params.put("order", "");
		//查询列表数据
        Query query = new Query(params);

		List<TVideoInfoEntity> tVideoInfoList = tVideoInfoService.queryList(query);
		int total = tVideoInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tVideoInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("videoList", pageUtil);
	}
	
	@IgnoreAuth
	@GetMapping("/info/{id}")
	@ApiOperation(value="根据视频ID查询视频详情")
	public R info(@PathVariable("id") Integer id){
		TVideoInfoEntity tVideoInfo = tVideoInfoService.queryObject(id);
		return R.ok().put("tVideoInfo", tVideoInfo);
	}
	
}
