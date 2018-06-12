package com.yunpan.service.service.bean;

import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.UniUserEntity;

public class MerchantInfoBean {
	
	private MerchantEntity merchantEntity;
	
	private UniUserEntity uniUserEntity;
	
	private MerchantRateEntity merchantRateEntity;
	
	private MerchantAccountEntity merchantAccountEntity;

	public MerchantEntity getMerchantEntity() {
		return merchantEntity;
	}

	public void setMerchantEntity(MerchantEntity merchantEntity) {
		this.merchantEntity = merchantEntity;
	}

	public UniUserEntity getUniUserEntity() {
		return uniUserEntity;
	}

	public void setUniUserEntity(UniUserEntity uniUserEntity) {
		this.uniUserEntity = uniUserEntity;
	}

	public MerchantRateEntity getMerchantRateEntity() {
		return merchantRateEntity;
	}

	public void setMerchantRateEntity(MerchantRateEntity merchantRateEntity) {
		this.merchantRateEntity = merchantRateEntity;
	}

	public MerchantAccountEntity getMerchantAccountEntity() {
		return merchantAccountEntity;
	}

	public void setMerchantAccountEntity(MerchantAccountEntity merchantAccountEntity) {
		this.merchantAccountEntity = merchantAccountEntity;
	}
	
	

}
