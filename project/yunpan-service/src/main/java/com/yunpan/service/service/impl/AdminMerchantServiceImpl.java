package com.yunpan.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRateDao;
import com.yunpan.data.dao.UniUserDao;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.UniUserEntity;
import com.yunpan.service.service.AdminMerchantService;
import com.yunpan.service.service.bean.MerchantInfoBean;

@Service
public class AdminMerchantServiceImpl implements AdminMerchantService {
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantAccountDao merchantAccountDao;
	
	@Autowired
	private MerchantRateDao merchantRateDao;
	
	@Autowired
	private UniUserDao UniUserDao;

	@Override
	public List<MerchantInfoBean> queryMerchantInfo() {
		List<MerchantInfoBean> list=new ArrayList<MerchantInfoBean>();
		List<MerchantEntity> merchantEntityList=merchantDao.queryMerchant();
		MerchantInfoBean merchantInfoBean=null;
		for(MerchantEntity merchantEntity:merchantEntityList){
			merchantInfoBean=new MerchantInfoBean();
			MerchantAccountEntity merchantAccountEntity=merchantAccountDao.selectByUserId(merchantEntity.getUserId());
			MerchantRateEntity merchantRateEntity=merchantRateDao.selectByUserId(merchantEntity.getUserId());
			UniUserEntity uniUserEntity=UniUserDao.selectByPrimaryKey(merchantEntity.getUserId());
			merchantInfoBean.setMerchantEntity(merchantEntity);
			merchantInfoBean.setMerchantRateEntity(merchantRateEntity);
			merchantInfoBean.setUniUserEntity(uniUserEntity);
			merchantInfoBean.setMerchantAccountEntity(merchantAccountEntity);
			list.add(merchantInfoBean);
		}		
		return list;
	}

}
