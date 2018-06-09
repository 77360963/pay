package com.yunpan.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.base.tool.DateTool;
import com.yunpan.base.tool.MoneyUtil;
import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantTradeDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.bean.PaymentResult;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.PaymentService;

@Service
public class MerchantRechargeServiceImpl implements MerchantRechargeService {
	
    private static final Logger logger = LoggerFactory.getLogger(MerchantRechargeServiceImpl.class);
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantAccountDao merchantAccountDao;
	
	@Autowired
	private MerchantTradeDao merchantTradeDao;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public long merchantRechargeAddOrder(MerchantTradeEntity merchantTradeEntity) throws MerchantException{		
	    merchantTradeEntity.setTransType(AppCommon.TRANS_TYPE_I);
	    MerchantEntity merchantEntity=merchantDao.selectMerchantEntityByUserId(merchantTradeEntity.getUserId());
		if(null==merchantEntity){
			logger.info("未找到相关商户信息，商户id={}",merchantTradeEntity.getUserId());
			throw new MerchantException("", "未找到相关商户信息");
		}	
	    try {	    	
	        merchantTradeDao.insertSelective(merchantTradeEntity);			
		} catch (Exception e) {
			logger.info("商户充值下单失败",e);
			throw new MerchantException("", "商户充值下单失败");
		}
	    return merchantTradeEntity.getId(); 
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean merchantRechargePaySuccess(String orderId) throws MerchantException{
		boolean paymentStatus=false;
		MerchantTradeEntity merchantTradeEntity=merchantTradeDao.selectByPrimaryKey(Long.valueOf(orderId));		
		if(null==merchantTradeEntity){
			logger.info("充值订单不存在,充值订单号={}",orderId);
			throw new MerchantException("", "充值订单不存在");
		}
		MerchantAccountEntity merchantAccountEntity=merchantAccountDao.selectByUserId(merchantTradeEntity.getUserId());
		if(null==merchantAccountEntity){
			logger.info("未找到相关商户资金账户,商户id={}",merchantTradeEntity.getUserId());
			throw new MerchantException("", "未找到相关商户资金账户");
		}
		try {
			PaymentResult paymentResult=paymentService.queryOrder(String.valueOf(orderId));
			if(null!=paymentResult&& AppCommon.PAY_STATUS_SUCCESS==paymentResult.getPaymentStatus()){
				MerchantTradeEntity updateMerchantTradeEntity=new MerchantTradeEntity();
				updateMerchantTradeEntity.setId(merchantTradeEntity.getId());
				updateMerchantTradeEntity.setNeedPayAmount(paymentResult.getNeedPayAmount());
				updateMerchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
				int updateCount=merchantTradeDao.updateMerchantTradeStatus(updateMerchantTradeEntity);
				if(updateCount>0){
					int updateAccount=merchantAccountDao.merchantRecharge(merchantTradeEntity.getUserId(), paymentResult.getNeedPayAmount());
					if(updateAccount!=1){
						throw new MerchantException("", "商户充值失败,请联系管理员");
					}else{
						paymentStatus=true;
					}
				}else{
					throw new MerchantException("", "商户充值成功,请检查订单状态");
				}
			}
		} catch (MerchantException e) {
			logger.info("商户充值失败",e);
			throw new MerchantException("", e.getMessage());
		}catch (Exception e) {
			logger.info("商户充值失败",e);
			throw new MerchantException("", "商户充值失败");
		}			
		return paymentStatus;
	}

	@Override
	public List<MerchantTradeEntityBean> queryMerchantTradeByUserId(long userId) {		
		List<MerchantTradeEntity> list=merchantTradeDao.queryTradeByUserId(userId);
		List<MerchantTradeEntityBean> listBean=new ArrayList<MerchantTradeEntityBean>();
		MerchantTradeEntityBean merchantTradeEntityBean=null;
		for(MerchantTradeEntity entity:list){
			merchantTradeEntityBean=new MerchantTradeEntityBean();
			merchantTradeEntityBean.setId(entity.getId());
			merchantTradeEntityBean.setPayAmount(MoneyUtil.parseFromFenAmountToRMB(entity.getPayAmount().toString()));
			if(null!=entity.getNeedPayAmount()){
				merchantTradeEntityBean.setNeedPayAmount(MoneyUtil.parseFromFenAmountToRMB(entity.getNeedPayAmount().toString()));
			}
			
			merchantTradeEntityBean.setPayStatus(entity.getPayStatus());
			merchantTradeEntityBean.setCreatedTime(DateTool.formatFullDate(entity.getCreatedTime()));
			listBean.add(merchantTradeEntityBean);
		}
		return listBean;
	}

}
