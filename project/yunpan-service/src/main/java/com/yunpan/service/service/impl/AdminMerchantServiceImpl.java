package com.yunpan.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.AdminMerchantService;
import com.yunpan.service.service.bean.MerchantInfoBean;

@Service
public class AdminMerchantServiceImpl implements AdminMerchantService {
	
    private static final Logger logger = LoggerFactory.getLogger(AdminMerchantServiceImpl.class);
    
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

    @Override
    public boolean modfiyMerchantRate(long userId,BigDecimal rate) {
        logger.info("userId={},设置平台费率={}",userId,rate);
        MerchantRateEntity merchantRateEntity=merchantRateDao.selectByUserId(userId);
        if(null==merchantRateEntity){
            throw new MerchantException("", "未找到商户费率信息");
        }
        if(rate.compareTo(AppCommon.PLATFORM_RATE_MIN)<0){
            throw new MerchantException("", "不能低于平台最低费率");
        }
        MerchantRateEntity updateMerchantRateEntity=new MerchantRateEntity();
        updateMerchantRateEntity.setId(merchantRateEntity.getId());
        updateMerchantRateEntity.setRate(rate);
        if(merchantRateDao.updateByPrimaryKeySelective(updateMerchantRateEntity)>0){
            return true;
        }
        return false;
    }

}
