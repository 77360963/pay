package com.yunpan.service.service;

import java.util.Map;

import com.yunpan.service.bean.PaymentResult;

public interface PaymentService {
	
	/**
	 * 网页下单
	 * @param amount
	 * @param orderId
	 * @return
	 */
	public String webPay(int amount,String orderId,String notifyUrl);
	
	
	/**
	 * 网页下单
	 * @param amount
	 * @param orderId
	 * @return
	 */
	public Map<String,String> webPayMap(int amount,String orderId,String notifyUrl);
	
	/**
	 * 订单查询
	 * @param orderId
	 * @return
	 */
	public PaymentResult queryOrder(String orderId);

}
