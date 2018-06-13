package com.yunpan.service.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunpan.base.tool.HttpClientUtil;
import com.yunpan.base.tool.MD5Util;
import com.yunpan.base.tool.PaymentUtils;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.bean.PaymentResult;
import com.yunpan.service.service.PaymentService;

@Service
public class PaymentService51Impl implements PaymentService {
	
	 private static final Logger logger = LoggerFactory.getLogger(MerchantRechargeServiceImpl.class);
    
    @Value(value = "${publicKey}")
    private  String publicKey;
    
    @Value(value = "${merchartId}")
    private  String merchartId;
    
    @Value(value = "${postUrl}")
    private String postUrl;
    
    @Value(value = "${queryUrl}")
    private String queryUrl;
    
    private  String getSign(Map<String,String> map1){
    	  Map<String,String> map=new HashMap<String,String>();
          map.put("merchant_id", merchartId);
          map.put("nonce_str",map1.get("nonce_str"));
          map.put("total_fee",map1.get("total_fee"));        
          map.put("m_out_trade_no", map1.get("m_out_trade_no")); 
          Map<String,String> contentData= PaymentUtils.filterBlank(map);
          String data=PaymentUtils.coverMap2String(contentData);
          String stringSignTemp=data+"&key="+publicKey;        
          System.out.println("签名数据="+stringSignTemp);
          String sign=MD5Util.MD5(stringSignTemp).toUpperCase();
          System.out.println("签名="+sign);       
          return sign;
    }

	@Override
	public String webPay(int amount, String orderId,String notifyUrl) {
	    Map<String,String> dataMap=new HashMap<String,String>();
        dataMap.put("merchant_id", merchartId);
        dataMap.put("nonce_str",RandomStringUtils.randomAscii(10));
        dataMap.put("total_fee",String.valueOf(amount));        
        dataMap.put("m_out_trade_no", orderId);     
        dataMap.put("return_url", notifyUrl); 
        dataMap.put("sign", getSign(dataMap));
        String html=PaymentUtils.createAutoFormHtml(postUrl, dataMap, "UTF-8");    
        return html;
	}
	
	@Override
	public Map<String, String> webPayMap(int amount, String orderId,String notifyUrl) {
	    Map<String,String> dataMap=new HashMap<String,String>();
        dataMap.put("merchant_id", merchartId);
        dataMap.put("nonce_str",RandomStringUtils.randomAlphabetic(10));
        dataMap.put("total_fee",String.valueOf(amount));        
        dataMap.put("m_out_trade_no", orderId);     
        dataMap.put("return_url", notifyUrl); 
        dataMap.put("sign", getSign(dataMap));
        dataMap.put("post_url", postUrl);
		return dataMap;
	}
	
	

	@Override
	public PaymentResult queryOrder(String orderId) {		
		PaymentResult paymentResult=new PaymentResult();
	    Map<String,String> dataMap=new HashMap<String,String>();
        dataMap.put("merchant_id", merchartId);
        dataMap.put("nonce_str",RandomStringUtils.randomAlphabetic(10));       
        dataMap.put("m_out_trade_no", orderId);         
        String sign= getSign(dataMap);  
        dataMap.put("sign", sign);    
        String html="";		
        try {
			html = HttpClientUtil.getInstance().sendHttpPost(queryUrl, dataMap);
			logger.info("渠道返回参数={}",html);
			if(StringUtils.isBlank(html)){
	        	  return paymentResult; 
	        }
		  JSONObject object= null;
          object = JSON.parseObject(html);
          if("0000".equals(object.get("responseCode"))){
        	  JSONObject dataObject = JSONObject.parseObject(object.get("data").toString()); 
        	  paymentResult.setPayAmount(dataObject.getIntValue("amount"));
        	  paymentResult.setNeedPayAmount(dataObject.getIntValue("need_pay_amount"));
        	  paymentResult.setOrderNo( (String) dataObject.get("m_out_trade_no"));
        	  String status= (String) dataObject.get("status");
        	  if("success".equals(status)){
        		  paymentResult.setPaymentStatus(AppCommon.PAY_STATUS_SUCCESS);
        	  }        	
          }
		} catch (Exception e) {
			 logger.error("查询订单异常",e); 			 
		}       
      return paymentResult; 
	}

	
	
	

}
