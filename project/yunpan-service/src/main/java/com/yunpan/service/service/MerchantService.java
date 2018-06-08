package com.yunpan.service.service;

import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;

public interface MerchantService {
	
	/**
	 * 增加商户
	 * @param merchantEntity
	 * @return
	 */
	public boolean addMerchant(MerchantEntity merchantEntity);
	
	/**
	 * 根据Id查询商户信息
	 * @param merchantId
	 * @return
	 */
	public MerchantEntity queryMerchantInfoById(long merchantId);
	
	/**
	 * 根据Id查询商户账户信息
	 * @param merchantId
	 * @return
	 */
	public MerchantAccountEntity queryMerchantAccountByMerchantId(long merchantId);
	
	
	public MerchantEntity merchantLogin(String loginName,String password);
	

}
