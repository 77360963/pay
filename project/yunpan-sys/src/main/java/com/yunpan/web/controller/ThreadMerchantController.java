package com.yunpan.web.controller;



import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpan.base.tool.DeviceUtils;
import com.yunpan.base.web.util.Result;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.PaymentService;

@Controller
public class ThreadMerchantController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ThreadMerchantController.class);	

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantRechargeService merchantRechargeService;
	
	/**
	 * 第三方商户充值
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value ="/webpay")
	@ResponseBody
	public void merchantRecharge(HttpServletRequest request,HttpServletResponse response) throws Exception{		    
	  //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        response.setHeader("content-type", "text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();  
	    String merchantId=request.getParameter("merchantId");		
		String payAmount=request.getParameter("payAmount");
		String threadOrderNo=request.getParameter("orderNo");
		String returnUrl=request.getParameter("returnUrl");	
		String html="下单出错";    
		if(StringUtils.isBlank(merchantId)||StringUtils.isBlank(payAmount)||StringUtils.isBlank(threadOrderNo)){
		    out.println(html+="缺少参数!");
		    return;
		}
		 if(!DeviceUtils.isMobileDevice(request)){
		     out.println(html+="请使用手机下单!");
	         return;
		 }
		     
	    try { 
	            MerchantTradeEntity merchantTradeEntity=new MerchantTradeEntity();
	            merchantTradeEntity.setUserId(Long.valueOf(merchantId));
	            merchantTradeEntity.setPayAmount(Integer.valueOf(payAmount));            
	            merchantTradeEntity.setFromSource(DeviceUtils.getBrowser(request));
	            merchantTradeEntity.setThreadOrderNo(threadOrderNo);            
	            String rechargeRequestNo = merchantRechargeService.merchantRechargeAddOrder(merchantTradeEntity);          
	            html=paymentService.webPay(Integer.valueOf(payAmount), rechargeRequestNo, returnUrl); 
        } catch (MerchantException e) {
            html=e.getMessage();
            logger.info("下单出错",e);
        } catch (Exception e) {
            logger.info("下单出错",e);
        }	      
	    out.println(html);
	}
	
	/**
	 * 第三方商户充值查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
    @RequestMapping(value ="/querywebpay")
    @ResponseBody
    public Map userSigninProcess(HttpServletRequest request,HttpServletResponse response,final ModelMap model){      
        try {  
            Map<String,Object> map=new HashMap<String,Object>();
            String merchantId=request.getParameter("merchantId");
            String threadOrderNo=request.getParameter("orderNo");
            MerchantTradeEntity merchantTradeEntity=merchantRechargeService.queryTradeByUserIdandThreadOrderNo(Long.valueOf(merchantId), threadOrderNo);
            if(null!=merchantTradeEntity){
                map.put("amount", merchantTradeEntity.getPayAmount());
                map.put("payStatus", merchantTradeEntity.getPayStatus());
                map.put("paymentOrder", merchantTradeEntity.getId());
                map.put("paymentTime", merchantTradeEntity.getConfirmPayTime());
                return Result.success(map);
            } else{
                return Result.failed("failed","未找到相关订单");
            }
        } catch (Exception e) {
             return Result.failed("failed",e.getMessage());
        }           
    }

}
