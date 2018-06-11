package com.yunpan.service.service;

import java.util.List;

import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.exception.MerchantException;

public interface MerchantRechargeService {
	
	/**
	 * 商户充值下单
	 * @param merchantRechargeEntity
	 * @return 充值请求流水号
	 */
	public String merchantRechargeAddOrder(MerchantTradeEntity merchantTradeEntity) throws MerchantException;
	
	/**
	 * 根据商户请求支付流水号更新
	 * @param orderId
	 * @return
	 */
	public boolean merchantRechargePaySuccess(String  requestTradeNo) throws MerchantException;
	
	
	/**
	 * 根据商户充值流水Id更新
	 * @param orderId
	 * @return
	 */
	public boolean merchantRechargePaySuccess(long  merchantTradeId) throws MerchantException;
	
	
	/**
	 * 查询指定商户充值记录
	 * @param merchantId
	 * @return
	 */
	public List<MerchantTradeEntityBean> queryMerchantTradeByUserId(long merchantId);

}
