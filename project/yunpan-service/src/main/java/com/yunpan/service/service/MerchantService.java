package com.yunpan.service.service;

import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.service.bean.MerchantAccountEntityBean;
import com.yunpan.service.service.bean.MerchantInfoBean;
import com.yunpan.service.service.bean.MerchantRegisterBean;

public interface MerchantService {
	
	/**
	 * 增加商户
	 * @param merchantEntity
	 * @return
	 */
	public boolean addMerchant(MerchantRegisterBean merchantRegisterBean);
	
	/**
	 * 根据Id查询商户信息
	 * @param merchantId
	 * @return
	 */
	public MerchantEntity queryMerchantInfoByUserId(long userId);
	
	/**
	 * 根据Id查询商户账户信息
	 * @param merchantId
	 * @return
	 */
	public MerchantAccountEntityBean queryMerchantAccountByUserId(long userId);
	
	/**
	 * 用户登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public MerchantInfoBean merchantLogin(String loginName,String password);
	
	
	/**
	 * 查询用户费率
	 * @param userId
	 * @return
	 */
	public MerchantRateEntity queryMerchantRateByUserId(long userId);

}
