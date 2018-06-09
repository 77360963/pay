package com.yunpan.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantTradeDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantAccountService;

@Service
public class MerchantAccountServiceImpl implements MerchantAccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(MerchantAccountServiceImpl.class);
	
    @Autowired
    private MerchantDao merchantDao;
    
	@Autowired
	private MerchantAccountDao merchantAccountDao;
	
	@Autowired
	private MerchantTradeDao merchantTradeDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean withdrawByUserId(long userId, int amount) {
	    MerchantEntity merchantEntity=merchantDao.selectMerchantEntityByUserId(userId);
        if(null==merchantEntity){
            logger.info("未找到相关商户信息，userId={}",userId);
            throw new MerchantException("", "未找到相关商户信息");
        }
        MerchantAccountEntity merchantAccountEntity=merchantAccountDao.selectByUserId(userId);
        if(null==merchantAccountEntity){
            logger.info("未找到相关商户资金账户,userId={}",userId);
            throw new MerchantException("", "未找到相关商户资金账户");
        }
        if(amount>merchantAccountEntity.getAvlAmt()){
            logger.info("账户余额不足,userId={},余额={},取现={}",userId,merchantAccountEntity.getAvlAmt(),amount);
            throw new MerchantException("", "账户余额不足");
        }
	    MerchantTradeEntity merchantTradeEntity=new MerchantTradeEntity();
	    merchantTradeEntity.setUserId(userId);
	    merchantTradeEntity.setPayAmount(amount);
	    merchantTradeEntity.setNeedPayAmount(amount); 
	    merchantTradeEntity.setConfirmPayAmount(amount);	
	    merchantTradeEntity.setTransType(AppCommon.TRANS_TYPE_O);
	    merchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_INIT);
	    merchantTradeDao.insertSelective(merchantTradeEntity);
		int updateCount= merchantAccountDao.merchantWithdraw(userId, amount);
		if(updateCount!=1){
		    throw new MerchantException("", "更新相关份额出错");
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean confirmWithdrawByTransId(long transId) {
	    MerchantTradeEntity queryMerchantTradeEntity=merchantTradeDao.selectByPrimaryKey(transId);
	    if(null==queryMerchantTradeEntity){
	        throw new MerchantException("", "未找到相关订单");
	    }
	    MerchantTradeEntity updateMerchantTradeEntity=new MerchantTradeEntity();
	    updateMerchantTradeEntity.setId(queryMerchantTradeEntity.getId());
	    updateMerchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
	    int updateCount=merchantTradeDao.updateByPrimaryKeySelective(updateMerchantTradeEntity);
	    if(updateCount!=1){
	        throw new MerchantException("", "更新相关订单出错");
	    }
		int updateCount1= merchantAccountDao.merchantWithdrawConfirm(queryMerchantTradeEntity.getUserId(), queryMerchantTradeEntity.getPayAmount());
		if(updateCount1!=1){
		    throw new MerchantException("", "更新相关份额出错");
		}
		return true;
	}

}
