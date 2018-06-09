package com.yunpan.service.service;

public interface MerchantAccountService {
	

	/**
	 * 商户取现
	 * @param merchantId
	 * @param amount
	 * @return
	 */
	public boolean  withdrawByUserId(long userId,int amount);
	
	
	/**
	 * 确认打款
	 * @param transId
	 * @return
	 */
	public boolean  confirmWithdrawByTransId(long transId);

}
