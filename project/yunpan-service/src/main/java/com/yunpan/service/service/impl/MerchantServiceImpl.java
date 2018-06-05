package com.yunpan.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRateDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.service.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantAccountDao merchantAccountDao;
	
	@Autowired
	private MerchantRateDao merchantRateDao;

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

}
