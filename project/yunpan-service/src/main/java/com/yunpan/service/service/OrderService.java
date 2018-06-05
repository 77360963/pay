package com.yunpan.service.service;

import java.util.List;

import com.yunpan.service.exception.MerchantException;

public interface OrderService {
	
	public int createOrder(List list) throws MerchantException;

}
