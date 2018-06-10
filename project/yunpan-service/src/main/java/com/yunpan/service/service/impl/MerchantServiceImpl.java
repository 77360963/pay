package com.yunpan.service.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.base.tool.MoneyUtil;
import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRateDao;
import com.yunpan.data.dao.UniUserDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.UniUserEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.bean.MerchantAccountEntityBean;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantService;
import com.yunpan.service.service.bean.MerchantRegisterBean;

@Service
public class MerchantServiceImpl implements MerchantService {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantAccountDao merchantAccountDao;
	
	@Autowired
	private MerchantRateDao merchantRateDao;
	
	@Autowired
	private UniUserDao UniUserDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean addMerchant(MerchantRegisterBean merchantRegisterBean) {
		
		if(StringUtils.isBlank(merchantRegisterBean.getLoginName())||StringUtils.isBlank(merchantRegisterBean.getPassword())){
			throw new MerchantException("", "注册用户名或密码不能为空");
		}
		if(StringUtils.isBlank(merchantRegisterBean.getContacts())||StringUtils.isBlank(merchantRegisterBean.getPaymentMethod())){
			throw new MerchantException("", "真实姓名或收款支付宝账户不能为空");
		}
		
		UniUserEntity queryUniUserEntity=UniUserDao.selectByLoginName(merchantRegisterBean.getLoginName());
		if(null!=queryUniUserEntity){
			throw new MerchantException("", "该用户已存在");
		}		
		
		//统一用户表
		UniUserEntity uniUserEntity=new UniUserEntity();
		uniUserEntity.setLoginName(merchantRegisterBean.getLoginName());
		uniUserEntity.setPassword(merchantRegisterBean.getPassword());
		uniUserEntity.setUserState(AppCommon.USER_STATUS_OPEN);
		uniUserEntity.setRole(AppCommon.USER_TYPE_MERCHANT);
		uniUserEntity.setPlatform(AppCommon.PLATFORM);
		UniUserDao.insertSelective(uniUserEntity);
		
		 //商户信息表
		MerchantEntity merchantEntity=new MerchantEntity();
		merchantEntity.setUserId(uniUserEntity.getId());
		merchantEntity.setName(merchantRegisterBean.getName());
		merchantEntity.setAddress(merchantRegisterBean.getAddress());
		merchantEntity.setContacts(merchantRegisterBean.getContacts());
		merchantEntity.setMobile(merchantRegisterBean.getMobile());
		merchantEntity.setImage(merchantRegisterBean.getImage());
		merchantEntity.setPaymentMethod(merchantRegisterBean.getPaymentMethod());
		merchantEntity.setPaymentMinamt(AppCommon.PAYMENTMINAMT);
		 merchantDao.insertSelective(merchantEntity);
		 
		//商户账户表
		 MerchantAccountEntity merchantAccountEntity=new MerchantAccountEntity();
		 merchantAccountEntity.setUserId(uniUserEntity.getId());
		 merchantAccountDao.insertSelective(merchantAccountEntity);
		//商户费率表
		 MerchantRateEntity merchantRateEntity=new MerchantRateEntity();
		 merchantRateEntity.setUserId(uniUserEntity.getId());
		 merchantRateEntity.setRate(AppCommon.PLATFORM_RATE);
		 merchantRateDao.insertSelective(merchantRateEntity);
		 return true;
	}

	@Override
	public MerchantEntity queryMerchantInfoByUserId(long userId) {	
		return merchantDao.selectMerchantEntityByUserId(userId);
	}

	@Override
	public MerchantAccountEntityBean queryMerchantAccountByUserId(long userId) {
		MerchantAccountEntity merchantAccountEntity=merchantAccountDao.selectByUserId(userId);
		MerchantAccountEntityBean merchantAccountEntityBean=new MerchantAccountEntityBean();
		merchantAccountEntityBean.setId(merchantAccountEntity.getId());
		merchantAccountEntityBean.setShareAmt(MoneyUtil.parseFromFenAmountToRMB(merchantAccountEntity.getShareAmt().toString()));
		merchantAccountEntityBean.setAvlAmt(MoneyUtil.parseFromFenAmountToRMB(merchantAccountEntity.getAvlAmt().toString()));
		merchantAccountEntityBean.setFreAmt(MoneyUtil.parseFromFenAmountToRMB(merchantAccountEntity.getFreAmt().toString()));
		return merchantAccountEntityBean;
	}

	@Override
	public MerchantEntity merchantLogin(String loginName, String password) {
		UniUserEntity uniUserEntity=UniUserDao.selectByLoginPassword(loginName, password);
		if(null==uniUserEntity){
			logger.info("登录名或密码不正确,loginName={}",loginName);
	        throw new MerchantException("", "登录名或密码不正确");
		}
		if(uniUserEntity.getUserState().equals(AppCommon.USER_STATUS_STOP)){
			logger.info("此账户已被停用,loginName={}",loginName);
	        throw new MerchantException("", "此账户已被停用");
		}
		
		return merchantDao.selectMerchantEntityByUserId(uniUserEntity.getId());
		
	}

}
