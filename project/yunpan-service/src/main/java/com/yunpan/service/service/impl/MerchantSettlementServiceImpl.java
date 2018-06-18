package com.yunpan.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpan.base.tool.DateTool;
import com.yunpan.base.tool.MoneyUtil;
import com.yunpan.data.dao.MerchantTradeDao;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.service.MerchantSettlementService;

@Service
public class MerchantSettlementServiceImpl implements MerchantSettlementService {
	
	@Autowired
	private MerchantTradeDao merchantTradeDao;

	@Override
	public List<MerchantTradeEntityBean> queryMerchantTrade(MerchantTradeEntityBean queryMerchantTradeEntityBean) {
		MerchantTradeEntity queryMerchantTradeEntity=new MerchantTradeEntity();
		queryMerchantTradeEntity.setUserId(queryMerchantTradeEntityBean.getUserId());
		List<MerchantTradeEntity> list=merchantTradeDao.queryTrade(queryMerchantTradeEntity);
		List<MerchantTradeEntityBean> listBean=new ArrayList<MerchantTradeEntityBean>();
		MerchantTradeEntityBean merchantTradeEntityBean=null;
		for(MerchantTradeEntity entity:list){
			merchantTradeEntityBean=new MerchantTradeEntityBean();
			merchantTradeEntityBean.setId(entity.getId());
			merchantTradeEntityBean.setPayAmount(MoneyUtil.parseFromFenAmountToRMB(entity.getPayAmount().toString()));
			if(null!=entity.getNeedPayAmount()){
				merchantTradeEntityBean.setNeedPayAmount(MoneyUtil.parseFromFenAmountToRMB(entity.getNeedPayAmount().toString()));
			}
			merchantTradeEntityBean.setTransType(entity.getTransType());
			merchantTradeEntityBean.setPayStatus(entity.getPayStatus());
			merchantTradeEntityBean.setOutChannelNo(entity.getOutChannelNo());
			merchantTradeEntityBean.setOutTradeNo(entity.getOutTradeNo());
			merchantTradeEntityBean.setFromSource(entity.getFromSource());
			merchantTradeEntityBean.setCreatedTime(DateTool.formatFullDate(entity.getCreatedTime()));
			listBean.add(merchantTradeEntityBean);
		}
		return listBean;		
	}

}
