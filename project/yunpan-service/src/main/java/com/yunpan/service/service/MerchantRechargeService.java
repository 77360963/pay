package com.yunpan.service.service;

import java.util.List;

import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.exception.MerchantException;

public interface MerchantRechargeService {
	
	/**
	 * 商户充值下单
	 * @param merchantRechargeEntity
	 * @return
	 */
	public long merchantRechargeAddOrder(MerchantTradeEntity merchantTradeEntity) throws MerchantException;
	
	/**
	 * 商户充值成功
	 * @param orderId
	 * @return
	 */
	public boolean merchantRechargePaySuccess(String orderId) throws MerchantException;
	
	
	/**
	 * 查询指定商户充值记录
	 * @param merchantId
	 * @return
	 */
	public List<MerchantTradeEntity> queryMerchantTradeByUserId(long merchantId);

}
