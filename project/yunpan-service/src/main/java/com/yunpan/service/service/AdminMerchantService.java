package com.yunpan.service.service;

import java.math.BigDecimal;
import java.util.List;

import com.yunpan.service.service.bean.MerchantInfoBean;

public interface AdminMerchantService {
	
    /**
     * 查询所有商户信息
     * @return
     */
	List<MerchantInfoBean> queryMerchantInfo();
	
	/**
	 * 根据userId更改商户费率
	 * @param userId
	 * @return
	 */
	boolean modfiyMerchantRate(long userId,BigDecimal rate);

}
