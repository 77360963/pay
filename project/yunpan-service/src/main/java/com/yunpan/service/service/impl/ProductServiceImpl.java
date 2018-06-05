package com.yunpan.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpan.data.dao.ProductDao;
import com.yunpan.data.entity.ProductEntity;
import com.yunpan.service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public int addProduct(ProductEntity productEntity) {
		return productDao.insertSelective(productEntity);
	}

}
