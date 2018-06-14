package com.yunpan.service.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunpan.data.dao.ChannelTradeDao;
import com.yunpan.data.entity.ChannelTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.service.MerchantRechargeService;


/**
 * 待支付订单状态查询
 * @author Administrator
 *
 */
@Component
public class QueryChannelPayTask {
	
	 private static final Logger logger = LoggerFactory.getLogger(QueryChannelPayTask.class);
	 
	 @Autowired
     private MerchantRechargeService merchantRechargeService;
	 
	 @Autowired
	 private ChannelTradeDao channelTradeDao;
	
	 @Scheduled(fixedDelay=1*30*1000)
	 public void entrustImportUser(){ 
        int pageNum = 1;
        while (true) { 
            ChannelTradeEntity queryChannelTradeEntity=new ChannelTradeEntity();
            queryChannelTradeEntity.setPayStatus(AppCommon.PAY_STATUS_INIT);
            PageHelper.startPage(pageNum, 100);
            Page<ChannelTradeEntity> page=channelTradeDao.queryChannelTradeEntityList(queryChannelTradeEntity);
            if (page.isEmpty()) {
                logger.info("查询待支付订单定时任务时，获取数据为0");
                break;
            }else{
                List<ChannelTradeEntity> channelTradeEntityList=page.getResult();
                for(ChannelTradeEntity channelTradeEntity:channelTradeEntityList){
                    try {
                        logger.info("查询待支付订单定时任务时,requestNo={}",channelTradeEntity.getRequestTradeNo());
                        merchantRechargeService.merchantRechargePaySuccess(channelTradeEntity.getRequestTradeNo());
                    } catch (Exception e) {
                        logger.info("查询待支付订单定时任务时,requestNo={}",channelTradeEntity.getRequestTradeNo());
                    }
                }
            }
            pageNum++;
        }
	    
	 }

}
