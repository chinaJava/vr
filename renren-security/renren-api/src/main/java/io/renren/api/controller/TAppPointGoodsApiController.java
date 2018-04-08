package io.renren.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TAppuserAddressEntity;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TAppuserPointgoodsEntity;
import io.renren.entity.TPointGoodsEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserAddressService;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TAppuserPointgoodsService;
import io.renren.service.TPointGoodsService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-11-30 16:45:30
 */
@RestController
@RequestMapping("/api/tpointgoods")
@Api(value="/api/tpointgoods",description="积分兑换商品相关接口")
public class TAppPointGoodsApiController {
	@Autowired
	private TPointGoodsService tPointGoodsService;
	@Autowired
    private TokenService tokenService;
	@Autowired
	private TAppuserPointgoodsService tAppuserPointgoodsService;
	@Autowired
	private TAppuserDetailService tAppuserDetailService;
	
	
	@PostMapping("/list")
	@ApiOperation(value="查询所有兑换商品列表,map可传入分页信息page=1,limit=10也可不传，不传是查询全部")
	public R list(@RequestHeader("token")String token,@RequestBody(required=false) Map<String,Object> map){
		//如果分页信息为空时，设置一个默认值
		if(map.get("page")==null || "".equals(map.get("page"))){
			map.put("page", "1");
		}
		if(map.get("limit")==null || "".equals(map.get("limit"))){
			map.put("limit", "10");
		}
		if(map.get("sidx")==null || "".equals(map.get("sidx"))){
			map.put("sidx", "");
		}
		if(map.get("order")==null || "".equals(map.get("order"))){
			map.put("order", "");
		}
		//查询上架的兑换商品
		map.put("status", Constant.Status.NORMAL.getValue());
		//查询列表数据
        Query query = new Query(map);
		List<TPointGoodsEntity> tPointGoodsList = tPointGoodsService.queryList(query);
		return R.ok().put("pointGoodsList", tPointGoodsList);
	}
	
	
	@PostMapping("/exchange")
	@ApiOperation(value="兑换商品接口,map传入goodsId商品的id")
	@Transactional
	public R exchange(@RequestHeader("token")String token,@RequestBody Map<String,Object> map){
		//从header中获取token
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		int goodsId = (Integer.valueOf(map.get("goodsId").toString()));
		String appuserId = tokenEntity.getUserId();
		TPointGoodsEntity goodsObj  = tPointGoodsService.queryObject(goodsId);
		String msg = "";
		boolean flag = false;
		if(goodsObj!=null && goodsObj.getRemainNum()>0 && "1".equals(goodsObj.getStatus())){
			Map<String,Object> map_search =  new HashMap<String,Object>();
			map_search.put("goodsId", goodsId);
			map_search.put("appuserId", appuserId);
			TAppuserPointgoodsEntity userPointGoods = tAppuserPointgoodsService.queryByAppuserIdAndGoodsId(map_search);
			if(userPointGoods!=null){
				msg = "您已经兑换过该商品";
			}else{
				TAppuserDetailEntity userDetailObj = tAppuserDetailService.queryObjectByAppuserId(appuserId);
				if(userDetailObj.getPoint()!=null && userDetailObj.getPoint()>goodsObj.getExchangePoints()){
					/*********修改用户积分******************/
					userDetailObj.setPoint(userDetailObj.getPoint()-goodsObj.getExchangePoints());
					tAppuserDetailService.update(userDetailObj);
					/*********修改兑换商品库存******************/
					goodsObj.setRemainNum(goodsObj.getRemainNum()-1);
					tPointGoodsService.update(goodsObj);
					/*********保存兑换商品记录******************/
					TAppuserPointgoodsEntity appuserPointGoods = new TAppuserPointgoodsEntity();
					appuserPointGoods.setAppuserId(appuserId);
					appuserPointGoods.setGoodsId(goodsId);
					appuserPointGoods.setStatus(Constant.Status.NORMAL.getValue());
					appuserPointGoods.setCreatetime(new Date());
					tAppuserPointgoodsService.save(appuserPointGoods);
					flag = true;
				}else{
					msg = "您积分不足，无法兑换";
				}
			}
		}else{
			msg = "商品库存不足或者商品已经下架，无法兑换";
		}
		if(flag){
			return R.ok();
		}else{
			return R.error(msg);
		}
	}
	
	
	@PostMapping("/search")
	@ApiOperation(value="查询兑换商品成功记录")
	public R search(@RequestHeader("token")String token){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		List<TAppuserPointgoodsEntity> pointGoodsList = tAppuserPointgoodsService.queryByAppuserId(tokenEntity.getUserId());
		return R.ok().put("appuserPointGoodsList", pointGoodsList);
	}
	
	@PostMapping("/delete")
	@ApiOperation(value="删除兑换商品成功记录")
	public R delete(@RequestHeader("token")String token){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		//删除兑换商品成功记录修改status=3
		tAppuserPointgoodsService.updateByAppuserId(tokenEntity.getUserId());
		// List<TAppuserPointgoodsEntity> pointGoodsList = tAppuserPointgoodsService.queryByAppuserId(tokenEntity.getUserId());
		return R.ok();
		
	}
	
}
