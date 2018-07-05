package com.yunpan.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunpan.base.mail.IEMailSender;
import com.yunpan.base.tool.DateTool;
import com.yunpan.base.tool.MoneyUtil;
import com.yunpan.data.dao.ChannelTradeDao;
import com.yunpan.data.dao.MerchantAccountDao;
import com.yunpan.data.dao.MerchantDao;
import com.yunpan.data.dao.MerchantRateDao;
import com.yunpan.data.dao.MerchantTradeDao;
import com.yunpan.data.entity.ChannelTradeEntity;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.bean.PaymentResult;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.PaymentService;

@Service
public class MerchantRechargeServiceImpl implements MerchantRechargeService {
	
    private static final Logger logger = LoggerFactory.getLogger(MerchantRechargeServiceImpl.class);
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantAccountDao merchantAccountDao;
	
	@Autowired
	private MerchantTradeDao merchantTradeDao;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantRateDao merchantRateDao;
	
	@Autowired
	private ChannelTradeDao channelTradeDao;
	
	@Autowired
    private IEMailSender mailSender;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String merchantRechargeAddOrder(MerchantTradeEntity merchantTradeEntity) throws MerchantException{	
	    logger.info("用户充值下单 userId={},金额={}",merchantTradeEntity.getUserId(),merchantTradeEntity.getPayAmount());
	    if(merchantTradeEntity.getPayAmount()==0){
	    	throw new MerchantException("", "请输入正确的金额");
	    }
	    merchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_INIT);
	    merchantTradeEntity.setTransType(AppCommon.TRANS_TYPE_I);
	    MerchantEntity merchantEntity=merchantDao.selectMerchantEntityByUserId(merchantTradeEntity.getUserId());
		if(null==merchantEntity){
			logger.info("未找到相关商户信息，商户id={}",merchantTradeEntity.getUserId());
			throw new MerchantException("", "未找到相关商户信息");
		}
		MerchantRateEntity merchantRateEntity=merchantRateDao.selectByUserId(merchantTradeEntity.getUserId());
		if(null==merchantRateEntity){
			logger.info("未找到相关商户费率信息，商户id={}",merchantTradeEntity.getUserId());
			throw new MerchantException("", "未找到相关商户信息");
		}
		//验证第三方订单号是否重复
		if(StringUtils.isNotBlank(merchantTradeEntity.getThreadOrderNo())){
		    MerchantTradeEntity queryMerchantTradeEntity=merchantTradeDao.queryTradeByUserIdandThreadOrder(merchantTradeEntity.getUserId(), merchantTradeEntity.getThreadOrderNo());
		    if(null!=queryMerchantTradeEntity){
		        if(queryMerchantTradeEntity.getPayAmount().equals(merchantTradeEntity.getPayAmount())&&AppCommon.PAY_STATUS_INIT==queryMerchantTradeEntity.getPayStatus()){
		            ChannelTradeEntity queryChannelTradeEntity=channelTradeDao.selectByMerchantTradeId(queryMerchantTradeEntity.getId());
		            if(null!=queryChannelTradeEntity){
		                return queryChannelTradeEntity.getRequestTradeNo();
		            }
		        }else{
		            logger.info("第三方订单号重复,userId={},ThreadOrderNo={}",merchantTradeEntity.getUserId(),merchantTradeEntity.getThreadOrderNo());
		            throw new MerchantException("", "订单号重复");
		        }
		    }
		}
		
	    try { 	    	
	    	//增加商户交易流水	       
	    	merchantTradeDao.insertSelective(merchantTradeEntity);	
	        
	        //增加渠道流水
	        ChannelTradeEntity channelTradeEntity=new ChannelTradeEntity();
	        channelTradeEntity.setUserId(merchantTradeEntity.getUserId());
	        channelTradeEntity.setMerchantTradeId(merchantTradeEntity.getId());
	        channelTradeEntity.setPayStatus(AppCommon.PAY_STATUS_INIT);
	        //请求支付的外部流水号
	    	String requestTradeNo=DateTool.getCurrentDateStr2()+merchantTradeEntity.getId();
	    	merchantTradeEntity.setOutTradeNo(requestTradeNo);
	        channelTradeEntity.setRequestTradeNo(requestTradeNo);
	        channelTradeEntity.setPayAmount(merchantTradeEntity.getPayAmount());
	        channelTradeDao.insertSelective(channelTradeEntity);
	        logger.info("用户充值下单 userId={},金额={},充值请求流水号={}",merchantTradeEntity.getUserId(),merchantTradeEntity.getPayAmount(),requestTradeNo);
	        return channelTradeEntity.getRequestTradeNo(); 
		} catch (Exception e) {
			logger.info("商户充值下单失败",e);
			throw new MerchantException("", "商户充值下单失败");
		}
	    
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean merchantRechargePaySuccess(String  requestTradeNo) throws MerchantException{
	    logger.info("根据支付请求流水号查询支付状态,requestTradeNo={}",requestTradeNo);
		boolean paymentStatus=false;		
		ChannelTradeEntity channelTradeEntity=channelTradeDao.selectByRequestTradeNo(requestTradeNo);
		if(null==channelTradeEntity){
			logger.info("渠道充值订单不存在,充值请求订单号={}",requestTradeNo);
			throw new MerchantException("", "渠道充值订单不存在");
		}
		Long tradeOrderId=channelTradeEntity.getMerchantTradeId();
		MerchantTradeEntity merchantTradeEntity=merchantTradeDao.selectByPrimaryKey(tradeOrderId);		
		if(null==merchantTradeEntity){
			logger.info("充值订单不存在,充值订单号={}",tradeOrderId);
			throw new MerchantException("", "充值订单不存在");
		}
		if(merchantTradeEntity.getPayStatus().equals(AppCommon.PAY_STATUS_SUCCESS)){
			paymentStatus=true;
			return paymentStatus;
		}
		
		MerchantEntity merchantEntity=merchantDao.selectMerchantEntityByUserId(merchantTradeEntity.getUserId());
        if(null==merchantEntity){
            logger.info("未找到相关商户信息，商户id={}",merchantTradeEntity.getUserId());
            throw new MerchantException("", "未找到相关商户信息");
        }
		
		MerchantAccountEntity merchantAccountEntity=merchantAccountDao.selectByUserId(merchantTradeEntity.getUserId());
		if(null==merchantAccountEntity){
			logger.info("未找到相关商户资金账户,商户id={}",merchantTradeEntity.getUserId());
			throw new MerchantException("", "未找到相关商户资金账户");
		}
		
		MerchantRateEntity merchantRateEntity=merchantRateDao.selectByUserId(merchantTradeEntity.getUserId());
		if(null==merchantRateEntity){
			logger.info("未找到相关商户费率账户,商户id={}",merchantTradeEntity.getUserId());
			throw new MerchantException("", "未找到相关商户费率账户");
		}
		
		
		try {
			PaymentResult paymentResult=paymentService.queryOrder(requestTradeNo);
			if(null!=paymentResult&& AppCommon.PAY_STATUS_SUCCESS==paymentResult.getPaymentStatus()){			    
				//交易分润
			    if(null!=merchantEntity.getParentUserId()){
			        MerchantRateEntity parentMerchantRateEntity=merchantRateDao.selectByUserId(merchantEntity.getParentUserId());
			        //交易的费率大于上级商户的费率时，才产生分润
			        if(merchantRateEntity.getRate().compareTo(parentMerchantRateEntity.getRate())>0){
			        	BigDecimal platform_parent_rate=merchantRateEntity.getRate().subtract(parentMerchantRateEntity.getRate());
		                BigDecimal platform_parent_needPayAmount=new BigDecimal(paymentResult.getPayAmount()).multiply(platform_parent_rate).setScale(0, BigDecimal.ROUND_DOWN);
		                //分润大于0时，产生分润记录
		                if(platform_parent_needPayAmount.compareTo(BigDecimal.ZERO)>0){
		                	MerchantTradeEntity parentMerchantTradeEntity=new MerchantTradeEntity();
		 	                parentMerchantTradeEntity.setPayAmount(platform_parent_needPayAmount.intValue());
		 	                parentMerchantTradeEntity.setNeedPayAmount(platform_parent_needPayAmount.intValue());
		 	                parentMerchantTradeEntity.setConfirmPayAmount(platform_parent_needPayAmount.intValue());
		 	                parentMerchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
		 	                parentMerchantTradeEntity.setTransType(AppCommon.TRANS_TYPE_F);
		 	                parentMerchantTradeEntity.setUserId(parentMerchantRateEntity.getUserId());
		 	                parentMerchantTradeEntity.setFromSource(merchantTradeEntity.getFromSource());
		 	                int insertParentCount=merchantTradeDao.insertSelective(parentMerchantTradeEntity);
		 	                if(insertParentCount>0){
		 	                    int updateParentAccount=merchantAccountDao.merchantRecharge(parentMerchantRateEntity.getUserId(), platform_parent_needPayAmount.intValue());
		 	                }
		                }	               
			        }		        
	               
			    }			   
			    
			    //平台收款金额
				BigDecimal platform_rate=new BigDecimal(1).subtract(merchantRateEntity.getRate());
				BigDecimal platform_needPayAmount=new BigDecimal(paymentResult.getPayAmount()).multiply(platform_rate).setScale(0, BigDecimal.ROUND_DOWN);
				
				ChannelTradeEntity updateChannelTradeEntity=new ChannelTradeEntity();
				updateChannelTradeEntity.setId(channelTradeEntity.getId());
				updateChannelTradeEntity.setNeedPayAmount(paymentResult.getNeedPayAmount());
				updateChannelTradeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
				updateChannelTradeEntity.setOutTradeNo(paymentResult.getOrderNo());
				channelTradeDao.updateByPrimaryKeySelective(updateChannelTradeEntity);
				
				MerchantTradeEntity updateMerchantTradeEntity=new MerchantTradeEntity();
				updateMerchantTradeEntity.setId(merchantTradeEntity.getId());
				updateMerchantTradeEntity.setNeedPayAmount(platform_needPayAmount.intValue());
				updateMerchantTradeEntity.setConfirmPayAmount(paymentResult.getNeedPayAmount());
				updateMerchantTradeEntity.setConfirmPayTime(paymentResult.getPaymentTime());
				updateMerchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_SUCCESS);
				updateMerchantTradeEntity.setOutTradeNo(paymentResult.getOrderNo());
				updateMerchantTradeEntity.setOutChannelNo(AppCommon.CHANNEL_NO);
				int updateCount=merchantTradeDao.updateMerchantTradeStatus(updateMerchantTradeEntity);
				if(updateCount>0){
					int updateAccount=merchantAccountDao.merchantRecharge(merchantTradeEntity.getUserId(), platform_needPayAmount.intValue());
					if(updateAccount!=1){
						throw new MerchantException("", "商户充值失败,请联系管理员");
					}else{
						paymentStatus=true;
						//发送邮件          
			             HashMap<String,String> map=new HashMap<String,String>();			            
			             map.put("userId", merchantEntity.getUserId().toString());			             
			             map.put("merchantName", merchantEntity.getName());
			             map.put("contacts", merchantEntity.getContacts());
			             map.put("mobile", merchantEntity.getMobile());
			             map.put("paymentMethod", merchantEntity.getPaymentMethod());
			             map.put("payAmount", MoneyUtil.parseFromFenAmountToRMB(String.valueOf(merchantTradeEntity.getPayAmount())));
			             map.put("fromSource", merchantTradeEntity.getFromSource());
			             mailSender.sendSimpleEmail(AppCommon.MAIL_RECHARGE,map);
					}
				}else{
					throw new MerchantException("", "商户充值成功,请检查订单状态");
				}
			}else{
				throw new MerchantException("", "渠道未找到相关订单");
			}
		} catch (MerchantException e) {
			logger.info("商户充值失败",e);
			throw new MerchantException("", e.getMessage());
		}catch (Exception e) {
			logger.info("商户充值失败",e);
			throw new MerchantException("", "商户充值失败");
		}			
		return paymentStatus;
	}
	

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean merchantRechargePaySuccess(long merchantTradeId)	throws MerchantException {
		ChannelTradeEntity channelTradeEntity=channelTradeDao.selectByMerchantTradeId(merchantTradeId);
		if(null==channelTradeEntity){
			logger.info("渠道充值订单不存在,充值流水号Id={}",merchantTradeId);
			throw new MerchantException("", "渠道充值订单不存在");
		}
		
		return this.merchantRechargePaySuccess(channelTradeEntity.getRequestTradeNo());
	}
	
	

	@Override
	public List<MerchantTradeEntityBean> queryMerchantTradeByUserId(long userId,String transType) {
	    MerchantTradeEntity query=new MerchantTradeEntity();
	    query.setUserId(userId);
	    query.setTransType(transType);
		List<MerchantTradeEntity> list=merchantTradeDao.queryTrade(query);
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
			merchantTradeEntityBean.setCreatedTime(DateTool.formatFullDate(entity.getCreatedTime()));
			merchantTradeEntityBean.setFromSource(entity.getFromSource());
			listBean.add(merchantTradeEntityBean);
		}
		return listBean;
	}

    @Override
    public MerchantTradeEntity queryTradeByUserIdandThreadOrderNo(long userId, String threadOrderNo) {
         MerchantTradeEntity queryMerchantTradeEntity=merchantTradeDao.queryTradeByUserIdandThreadOrder(userId, threadOrderNo);
         return queryMerchantTradeEntity;
    }

	

}
