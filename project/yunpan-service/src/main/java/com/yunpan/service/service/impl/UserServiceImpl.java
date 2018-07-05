package com.yunpan.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.base.mail.IEMailSender;
import com.yunpan.base.tool.DateTool;
import com.yunpan.base.tool.MoneyUtil;
import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRateDao;
import com.yunpan.data.dao.MerchantTradeDao;
import com.yunpan.data.dao.UserSigninDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.data.entity.UserSigninEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.UserService;
import com.yunpan.service.service.bean.MerchantInfoBean;

@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserSigninDao userSigninDao;
    
    @Autowired
    private MerchantTradeDao merchantTradeDao;
    
    @Autowired
    private MerchantAccountDao merchantAccountDao;
    
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantRateDao merchantRateDao;
	
	@Autowired
    private IEMailSender mailSender;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean userSignin(long userId) {
        MerchantAccountEntity queryMerchantAccountEntity=merchantAccountDao.selectByUserId(userId);
        if(null==queryMerchantAccountEntity){
            logger.info("未找到相关商户资金账户,userId={}",userId);
            throw new MerchantException("", "未找到相关商户资金账户");
        }  
        
        MerchantEntity merchantEntity=merchantDao.selectMerchantEntityByUserId(userId);
        if(null==merchantEntity){
            logger.info("未找到相关商户信息，商户id={}",userId);
            throw new MerchantException("", "未找到相关商户信息");
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
                  //发送邮件          
		             HashMap<String,String> map=new HashMap<String,String>();
		             map.put("merchantName", merchantEntity.getName());
		             map.put("contacts", merchantEntity.getContacts());
		             map.put("mobile", merchantEntity.getMobile());
		             map.put("paymentMethod", merchantEntity.getPaymentMethod());
		             map.put("payAmount", MoneyUtil.parseFromFenAmountToRMB(String.valueOf(merchantTradeEntity.getPayAmount())));
		             mailSender.sendSimpleEmail(AppCommon.MAIL_SIGNIN,map);
                    return true;
                }
            }
        } catch (Exception e) {
            logger.info("userId={}签到失败",userId,e);
            throw new MerchantException("", "用户签到失败");
        }
       return false;      
    }

	@Override
	public List<MerchantInfoBean> queryRecommendTradeList(long userId) {
		List<MerchantInfoBean> list=new ArrayList<MerchantInfoBean>();
		List<MerchantEntity> merchantEntityList=merchantDao.queryMerchantByParentUserId(userId);		
		MerchantInfoBean merchantInfoBean=null;
		for(MerchantEntity merchantEntity:merchantEntityList){
			MerchantRateEntity merchantRateEntity=merchantRateDao.selectByUserId(merchantEntity.getUserId());
			merchantInfoBean=new MerchantInfoBean();
			merchantInfoBean.setMerchantEntity(merchantEntity);
			merchantInfoBean.setMerchantRateEntity(merchantRateEntity);
			list.add(merchantInfoBean);
		}
		return list;
	}

    @Override
    public int modfiyRecommendRate(long userId, long parentUserId, BigDecimal rate) {
        MerchantEntity merchantEntity=merchantDao.queryMerchantByUser(userId, parentUserId);
        if(null==merchantEntity){
            logger.info("未找到相关商户信息，商户userId={},parentUserId={}",userId,parentUserId);
            throw new MerchantException("", "未找到相关商户信息");
        }
        MerchantRateEntity merchantRateEntity=merchantRateDao.selectByUserId(userId);
        if(null==merchantRateEntity){
            logger.info("未找到相关商户费率信息，商户userId={},",userId);
            throw new MerchantException("", "未找到相关商户费率信息");
        }
        MerchantRateEntity parentMerchantRateEntity=merchantRateDao.selectByUserId(parentUserId);
        if(null==parentMerchantRateEntity){
            logger.info("未找到相关代理商户费率信息，parentUserId={},",parentUserId);
            throw new MerchantException("", "未找到相关代理商户费率信息");
        }        
        if(rate.compareTo(parentMerchantRateEntity.getRate())<0){
            throw new MerchantException("", "不能低于平台代理商的费率");
        }        
        if(rate.compareTo(AppCommon.PLATFORM_RATE_MIN)<0){
            throw new MerchantException("", "不能低于平台最低费率");
        }        
        MerchantRateEntity updateMerchantRateEntity=new MerchantRateEntity();
        updateMerchantRateEntity.setId(merchantRateEntity.getId());
        updateMerchantRateEntity.setRate(rate);
        return merchantRateDao.updateByPrimaryKeySelective(updateMerchantRateEntity);
    }

}
