package com.yunpan.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.data.dao.OrderDao;
import com.yunpan.data.dao.OrderDetailDao;
import com.yunpan.data.dao.ProductDao;
import com.yunpan.data.entity.OrderEntity;
import com.yunpan.data.entity.ProductEntity;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;

	@Override
	@Transactional
	public int createOrder(List list) throws MerchantException{
		ProductEntity productEntity=productDao.selectByPrimaryKey(1L);
		if(null==productEntity){
			throw new MerchantException("0001", "商品不存在");
		}		
		
		
		return 0;
	}

}
