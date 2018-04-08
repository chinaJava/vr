package io.renren.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.renren.alipay.util.UtilDate;
import io.renren.dao.TUserRepaymentDao;
import io.renren.entity.TAppuserDetailEntity;
import io.renren.entity.TMemberInfoEntity;
import io.renren.entity.TUserRepaymentEntity;
import io.renren.service.TAppuserDetailService;
import io.renren.service.TMemberInfoService;
import io.renren.service.TUserRepaymentService;
import io.renren.service.WXService;
import io.renren.service.ZFBService;
import io.renren.utils.DateUtils;
import io.renren.utils.R;



@Service("tUserRepaymentService")
public class TUserRepaymentServiceImpl  implements TUserRepaymentService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TUserRepaymentServiceImpl.class);
	
	@Autowired
	private TUserRepaymentDao tUserRepaymentDao;
	@Autowired
	private ZFBService  zfbService;
	@Autowired
	private WXService wxService;
	@Autowired
	private TMemberInfoService memberInfoService;
	@Autowired
	private TAppuserDetailService  userDetailService;
	
	
	@Override
	public TUserRepaymentEntity queryObject(Long id){
		return tUserRepaymentDao.queryObject(id);
	}
	
	@Override
	public List<TUserRepaymentEntity> queryList(Map<String, Object> map){
		return tUserRepaymentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserRepaymentDao.queryTotal(map);
	}
	
	@Override
	public void save(TUserRepaymentEntity tUserRepayment){
		tUserRepaymentDao.save(tUserRepayment);
	}
	
	@Override
	public void update(TUserRepaymentEntity tUserRepayment){
		tUserRepaymentDao.update(tUserRepayment);
	}
	
	@Override
	public void delete(Long id){
		tUserRepaymentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tUserRepaymentDao.deleteBatch(ids);
	}

	@Override
	public R userPayment(Map<String, Object> map) {
		try {
			String payMethod=map.get("payMethod").toString();
			Integer memberId=Integer.parseInt(map.get("memberId").toString()) ;// 会员级别id
			Integer openTime=Integer.parseInt(map.get("openTime").toString()) ;// 1表示一个季度；2表示二个季度，以此类推
			TMemberInfoEntity memberInfo= memberInfoService.queryObject(memberId);// 会员信息
			Integer payMoney=memberInfo.getChargeQuarter() * openTime;// 最终支付金额,单位：元
			
			R result=null;
			TAppuserDetailEntity userDetail= userDetailService.queryObjectByAppuserId(map.get("userId").toString());
			// 与当前时间比较
			if(userDetail!=null && userDetail.getMemberEndTime()!=null){
				int dateCompareResult=DateUtils.compare_date(
						DateUtils.format(userDetail.getMemberEndTime(), DateUtils.DATE_PATTERN2), // 用户会员到期时间
						DateUtils.format(new Date(), DateUtils.DATE_PATTERN2),//申请时间
						DateUtils.DATE_PATTERN2);// 格式化类型
				if(dateCompareResult >0 &&   memberId <userDetail.getMemberLevelId()){
					return R.error("您的将要开通会员等级不能小于当前会员等级");
				}
			}
			map.put("paymentPric", 0.01);//目前写死
			map.put("appNo","UGB"+ System.currentTimeMillis()/1000+"");
			LOGGER.info("用户掉起预支付请求参数==="+map);
			if(payMethod.equalsIgnoreCase("zfb")){// 支付宝支付
				map.put("notifyUrl", "http://mirrorspac.huimanyule.com/mirrorspace/api/tuserrepayment/syncInfo");
				 result= zfbService.userPayment(map);
			}else if(payMethod.equalsIgnoreCase("wx")){ // 微信支付
				map.put("notifyUrl", "http://mirrorspac.huimanyule.com/mirrorspace/api/tuserrepayment/callback");
				result=wxService.createTransactionNumber(map);
			}
			if(result.get("code").toString().equals("200")){
				saveTransactionRecord(map,payMoney);// 保存交易信息
			}
			return result;
		} catch (NumberFormatException e) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("预支付调用失败 {}=",e);
			}
			e.printStackTrace();
		}
			return R.error("预支付调用失败");
	}

	// 预支付成功，保存交易信息
	private void saveTransactionRecord(Map<String,Object> map,Integer amountMoney){
		TUserRepaymentEntity UserRepayment=new TUserRepaymentEntity();
		UserRepayment.setUserId(map.get("userId").toString());
		UserRepayment.setStatus("D");
		UserRepayment.setPaymentNo(map.get("appNo").toString());
		UserRepayment.setPaymentTime(new Date());
		UserRepayment.setMermberLevelId(Integer.parseInt(map.get("memberId").toString()) );
		UserRepayment.setMermberTime(map.get("openTime").toString());
		UserRepayment.setAmountMoney(amountMoney);
		UserRepayment.setPaymentMethod(map.get("payMethod").toString());
		tUserRepaymentDao.save(UserRepayment);
	}

	@Override
	public R callback(String appNo) {

		try {
			TUserRepaymentEntity userRepayment= tUserRepaymentDao.queryRepaymentByAppNoAndStatus(appNo,"D");
			if(userRepayment==null){
				return R.error();
			}
			Integer updateResult=tUserRepaymentDao.updateSelecByAppNoAndStatus(appNo,"Y");
			if(updateResult>0){
				TAppuserDetailEntity userDetailEntity=userDetailService.queryObjectByAppuserId(userRepayment.getUserId());
				//当前会员等级
				Integer alreadMemberLevelId = userDetailEntity.getMemberLevelId();
				//会员要开通的会员等级
				Integer openMemberLevelId = userRepayment.getMermberLevelId();
				//会员结束时间
				Date alreadMemberEndTime = userDetailEntity.getMemberEndTime();
				// 用户当前会员等级Id等于要开通的等级Id
				if(alreadMemberEndTime!=null && openMemberLevelId == alreadMemberLevelId && alreadMemberEndTime.getTime()>new Date().getTime()){
					//会员延长后的结束时间
					userDetailEntity.setMemberEndTime(UtilDate.addTime(alreadMemberEndTime, Integer.valueOf(userRepayment.getMermberTime())*3));
				}else {
					//用户没开通过会员或者用户升级会员都直接覆盖
					Date newMemberEndTime=UtilDate.addTime(new Date(), Integer.valueOf(userRepayment.getMermberTime())*3);
					userDetailEntity.setMemberStartTime(new Date());
					userDetailEntity.setMemberEndTime(newMemberEndTime);
					userDetailEntity.setMemberLevelId( userRepayment.getMermberLevelId());
				}
				userDetailService.update(userDetailEntity);
				return R.ok();
			}
		} catch (NumberFormatException e) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("支付失败 {}=",e);
			}
			e.printStackTrace();
		}
			return R.error();
	}
	
	
	/*@Override
	public R callback(String appNo) {
		try {
			TUserRepaymentEntity userRepayment= tUserRepaymentDao.queryRepaymentByAppNoAndStatus(appNo,"D");
			if(userRepayment==null){
				return R.ok();
			}
			Integer updateResult=tUserRepaymentDao.updateSelecByAppNoAndStatus(appNo,"Y");
			if(updateResult>0){
				TAppuserDetailEntity userDetailEntity=userDetailService.queryObjectByAppuserId(userRepayment.getUserId());
				// 用户已开通的
				Integer alreadMemberLeveId=userDetailEntity.getMemberLevelId();
				Date alreadMemberEndTime=userDetailEntity.getMemberEndTime();
				//将要开通的
				Integer memberLeveId=userRepayment.getMermberLevelId();
				Integer memberTime=Integer.parseInt(userRepayment.getMermberTime());
				Integer openTime=memberTime*3;
				
				//会员等级没有开通,直接设置开始时间和结束时间
				if(userDetailEntity.getMemberStartTime()==null || alreadMemberLeveId<memberLeveId || alreadMemberEndTime.getTime()<new Date().getTime()){
					userDetailEntity.setMemberLevelId(memberLeveId);
					userDetailEntity.setMemberStartTime(new Date());
					userDetailEntity.setMemberEndTime(DateUtils.addMemberTime(DateUtils.format(userDetailEntity.getMemberStartTime()), openTime, DateUtils.DATE_PATTERN));
				}
				//会员等级相同, 会员之前有开通,,当前会员没有到期,时间直接延长
				if(alreadMemberEndTime.getTime()>=new Date().getTime() && alreadMemberLeveId==memberLeveId){
					userDetailEntity.setMemberEndTime(DateUtils.addMemberTime(DateUtils.format(userDetailEntity.getMemberEndTime()), openTime, DateUtils.DATE_PATTERN));
				}
				userDetailService.update(userDetailEntity);
				return R.ok(); 
			}
				
		} catch (Exception e) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.info("支付回掉失败 {}=",e);
			}
			e.printStackTrace();
		}
			return R.error();
	}*/
	
	
}
