package com.yunpan.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRechargeDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRechargeEntity;
import com.yunpan.service.bean.AppCommon;
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
	private MerchantRechargeDao merchantRechargeDao;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public long merchantRechargeAddOrder(MerchantRechargeEntity merchantRechargeEntity) throws MerchantException{		
		MerchantEntity merchantEntity=merchantDao.selectByPrimaryKey(merchantRechargeEntity.getMerchantId());
		if(null==merchantEntity){
			logger.info("未找到相关商户信息，商户id={}",merchantRechargeEntity.getMerchantId());
			throw new MerchantException("", "未找到相关商户信息");
		}	
	    try {	    	
			merchantRechargeDao.insertSelective(merchantRechargeEntity);			
		} catch (Exception e) {
			logger.info("商户充值下单失败",e);
			throw new MerchantException("", "商户充值下单失败");
		}
	    return merchantRechargeEntity.getId(); 
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean merchantRechargePaySuccess(String orderId) throws MerchantException{
		boolean paymentStatus=false;
		MerchantRechargeEntity merchantRechargeEntity=merchantRechargeDao.selectByPrimaryKey(Long.valueOf(orderId));		
		if(null==merchantRechargeEntity){
			logger.info("充值订单不存在,充值订单号={}",orderId);
			throw new MerchantException("", "充值订单不存在");
		}
		MerchantAccountEntity merchantAccountEntity=merchantAccountDao.selectByMerchantId(merchantRechargeEntity.getMerchantId());
		if(null==merchantAccountEntity){
			logger.info("未找到相关商户资金账户,商户id={}",merchantRechargeEntity.getMerchantId());
			throw new MerchantException("", "未找到相关商户资金账户");
		}
		try {
			PaymentResult paymentResult=paymentService.queryOrder(String.valueOf(orderId));
			if(null!=paymentResult&& AppCommon.PAY_STATUS_SUCCESS==paymentResult.getPaymentStatus()){
				MerchantRechargeEntity updateMerchantRechargeEntity=new MerchantRechargeEntity();
				updateMerchantRechargeEntity.setId(merchantRechargeEntity.getId());
				updateMerchantRechargeEntity.setNeedPayAmount(paymentResult.getNeedPayAmount());
				updateMerchantRechargeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
				int updateCount=merchantRechargeDao.updateMerchantRechargeStatus(updateMerchantRechargeEntity);
				if(updateCount>0){
					int updateAccount=merchantAccountDao.merchantRecharge(merchantRechargeEntity.getMerchantId(), paymentResult.getNeedPayAmount());
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
	public List<MerchantRechargeEntity> queryMerchantRechargeByMerchantId(long merchantId) {		
		List<MerchantRechargeEntity> list=merchantRechargeDao.queryMerchantRechargeByMerchantId(merchantId);		
		return list;
	}

}
