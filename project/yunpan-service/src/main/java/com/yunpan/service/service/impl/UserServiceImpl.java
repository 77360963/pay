package com.yunpan.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.base.tool.DateTool;
import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantTradeDao;
import com.yunpan.data.dao.UserSigninDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.data.entity.UserSigninEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserSigninDao userSigninDao;
    
    @Autowired
    private MerchantTradeDao merchantTradeDao;
    
    @Autowired
    private MerchantAccountDao merchantAccountDao;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean userSignin(long userId) {
        MerchantAccountEntity queryMerchantAccountEntity=merchantAccountDao.selectByUserId(userId);
        if(null==queryMerchantAccountEntity){
            logger.info("未找到相关商户资金账户,userId={}",userId);
            throw new MerchantException("", "未找到相关商户资金账户");
        }  
        String signinDate=DateTool.getCurrentDateStr();
        UserSigninEntity queryUserSigninEntity=userSigninDao.selectByUserIdandDate(userId, signinDate);
        if(null!=queryUserSigninEntity){
            logger.info("用户当日已签到,userId={},signinDate={}",userId,signinDate);
            throw new MerchantException("", "用户当日已签到");
        }  
        try {
            int  signinAmount = (int)(Math.random()*AppCommon.SIGNIN_MAX_AMOUNT)+1;
            UserSigninEntity userSigninEntity=new UserSigninEntity();
            userSigninEntity.setUserId(userId);
            userSigninEntity.setSigninAcount(signinAmount);
            userSigninEntity.setSigninDate(signinDate);
            if(userSigninDao.insertSelective(userSigninEntity)>0){
                //增加交易流水
                MerchantTradeEntity merchantTradeEntity=new MerchantTradeEntity();
                merchantTradeEntity.setUserId(userId);
                merchantTradeEntity.setPayAmount(signinAmount);
                merchantTradeEntity.setNeedPayAmount(signinAmount);
                merchantTradeEntity.setConfirmPayAmount(signinAmount);
                merchantTradeEntity.setTransType(AppCommon.TRANS_TYPE_P);
                merchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
                int count=merchantTradeDao.insertSelective(merchantTradeEntity);
                if(count>0){
                    //增加用户份额            
                    int updateAccount=merchantAccountDao.merchantRecharge(userId, signinAmount);
                    if(updateAccount!=1){
                        throw new MerchantException("", "用户签到失败");
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            logger.info("userId={}签到失败",userId,e);
            throw new MerchantException("", "用户签到失败");
        }
       return false;      
    }

}
