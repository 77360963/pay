package com.yunpan.service.service;

import java.util.List;

import com.yunpan.service.bean.MerchantTradeEntityBean;

public interface MerchantSettlementService {
	
	/**
	 * 查询商户充值记录
	 * @param merchantId
	 * @return
	 */
	public List<MerchantTradeEntityBean> queryMerchantTrade(MerchantTradeEntityBean merchantTradeEntityBean);

}
