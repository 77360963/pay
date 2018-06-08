package com.yunpan.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRateDao;
import com.yunpan.data.dao.UniUserDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.UniUserEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantService;

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
	public boolean addMerchant(MerchantEntity merchantEntity) {		
		 //商户信息表
		 merchantDao.insertSelective(merchantEntity);
		//商户账户表
		 MerchantAccountEntity merchantAccountEntity=new MerchantAccountEntity();
		 merchantAccountEntity.setMerchantId(merchantEntity.getId());
		 merchantAccountDao.insertSelective(merchantAccountEntity);
		//商户费率表
		 MerchantRateEntity merchantRateEntity=new MerchantRateEntity();
		 merchantRateEntity.setMerchantId(merchantEntity.getId());
		 merchantRateDao.insertSelective(merchantRateEntity);
		 return true;
	}

	@Override
	public MerchantEntity queryMerchantInfoById(long merchantId) {	
		return merchantDao.selectByPrimaryKey(merchantId);
	}

	@Override
	public MerchantAccountEntity queryMerchantAccountByMerchantId(long merchantId) {
		return merchantAccountDao.selectByMerchantId(merchantId);
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
