package com.yunpan.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.service.service.MerchantAccountService;

@Service
public class MerchantAccountServiceImpl implements MerchantAccountService {
	
	@Autowired
	private MerchantAccountDao merchantAccountDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean withdrawByMerchantId(long merchantId, int amount) {
		int updateCount= merchantAccountDao.merchantWithdraw(merchantId, amount);
		if(updateCount>0){
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean confirmWithdrawByTransId(long transId) {
		int updateCount= merchantAccountDao.merchantWithdrawConfirm(16L, 10);
		if(updateCount>0){
			return true;
		}
		return false;
	}

}
