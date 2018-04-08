package io.renren.api.controller;

import java.util.ArrayList;
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

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TGameStoreEntity;
import io.renren.entity.TStoreOrdersEntity;
import io.renren.entity.TStoreProducttypeEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TGameStoreService;
import io.renren.service.TStoreOrdersService;
import io.renren.service.TStoreProducttypeService;
import io.renren.service.TokenService;
import io.renren.utils.Constant;
import io.renren.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author jiayou
 * @email info@jiayouad.com
 * @date 2017-10-25 17:28:03
 */
@RestController
@RequestMapping("/api/tgamestore")
@Api(value="/api/tgamestore",description="游戏商店")
public class TAppGameStoreController {
	@Autowired
	private TGameStoreService tGameStoreService;
	@Autowired
	private TStoreOrdersService tStoreOrdersService;
	@Autowired
	private  TAppuserDetailService tAppUserDetailService;
	@Autowired
    private TokenService tokenService;
	@Autowired
	private TStoreProducttypeService tStoreProducttypeService;
	
	/**
	 * 列表
	 */
	@IgnoreAuth
	@PostMapping("/list")
	@ApiOperation(value="查询所有商品列表")
	public R getAllStoreList(){
		//查询所有商品类型
		List<TStoreProducttypeEntity> tStoreProducttypeList = tStoreProducttypeService.queryAllObject();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(TStoreProducttypeEntity typeObj : tStoreProducttypeList){
			Map<String, Object> qmap = new HashMap<String, Object>();
			qmap.put("status", "1");
			qmap.put("productType", typeObj.getId());
			List<TGameStoreEntity> tGameStoreList = tGameStoreService.queryList(qmap);
			if(tGameStoreList.size()>0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("typeName", typeObj.getName());
				map.put("storeList", tGameStoreList);
				list.add(map);
			}
		}
		return R.ok().put("tGameStoreList", list);
	}
	
	@IgnoreAuth
	@GetMapping("/detail/{productId}")
	@ApiOperation(value="根据商品ID查询商品详情")
	public R getDetail(@PathVariable("productId") Long productId){
		TGameStoreEntity storeObj = tGameStoreService.queryObject(productId);
		return R.ok().put("tGameStore", storeObj);
	}
	
	
	@PostMapping("/createOrder")
	@ApiOperation(value="金币兑换商品，下单接口(需要传入productId:商品ID、addressId:收货地址、productAmount:兑换数量)")
	public R createOrder(@RequestHeader("token") String token,@RequestBody TStoreOrdersEntity tStoreOrders){
		String msg = "";
		boolean flag = false;
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		tStoreOrders.setAppuserId(tokenEntity.getUserId());
		tStoreOrders.setCreatetime(new Date());
		//下单时订单状态为0：未发货
		tStoreOrders.setStatus(Constant.Status.DISABLE.getValue());
		Long productId = tStoreOrders.getProductId();
		TGameStoreEntity storeObj = tGameStoreService.queryObject(productId);
		TAppuserDetailEntity appuserDetailObj = tAppUserDetailService.queryObjectByAppuserId(tokenEntity.getUserId());
		if(storeObj!=null){
			if(appuserDetailObj!=null && appuserDetailObj.getGameGold()>storeObj.getGameGold()){
				//已经上架的商品并且库存大于0才能进行兑换
				if(storeObj.getStatus().equals(Constant.Status.NORMAL.getValue())){
					if(storeObj.getInventory()>0 && storeObj.getInventory() > tStoreOrders.getProductAmount()){
						tGameStoreService.createOrder(tStoreOrders);
						flag = true;
					}else{
						msg = "商品库存不足，请选择其他商品。";
					}
				}else{
					msg = "改商品已经下架，请选择其他商品。";
				}
			}else{
				msg = "您的金币不足兑换该商品，请重新选择。";
			}
		}else{
			msg = "您选择的商品不存在，请重新选择。";
		}
		
		if(flag){
			return R.ok();
		}else{
			return R.error(msg);
		}
	}
	
	@PostMapping("/confirm")
	@ApiOperation(value="确认收货接口，传入订单ID")
	public R confirm(@RequestHeader("token") String token,@RequestBody Long productId){
		TokenEntity tokenEntity = tokenService.queryByToken(token);
		TStoreOrdersEntity orderObj = tStoreOrdersService.queryObject(productId);
		if(orderObj!=null){
			if(tokenEntity.getUserId().equals(orderObj.getAppuserId())){
				orderObj.setStatus("2");//更新为确认收货状态
				tStoreOrdersService.update(orderObj);
				return R.ok();
			}
		}
		return R.error("操作失败，请稍后再试。");
	}
	
}
