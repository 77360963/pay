package com.yunpan.web.controller;



import java.io.IOException;
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
import com.yunpan.base.tool.MD5Util;
import com.yunpan.base.tool.PaymentUtils;
import com.yunpan.base.web.util.Result;
import com.yunpan.data.entity.ChannelTradeEntity;
import com.yunpan.data.entity.MerchantSignEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.MerchantService;
import com.yunpan.service.service.PaymentService;

@Controller
public class ThreadMerchantController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ThreadMerchantController.class);	

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantRechargeService merchantRechargeService;
	
	@Autowired
	private MerchantService merchantService;
	
	
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
		String nonce_str=request.getParameter("nonce_str");
		String requestSign=request.getParameter("sign"); 
		String html="下单出错";    
		if(StringUtils.isBlank(merchantId)||StringUtils.isBlank(payAmount)||StringUtils.isBlank(threadOrderNo)){
		    out.println(html+="缺少参数!");
		    return;
		}
		/* if(!DeviceUtils.isMobileDevice(request)){
		     out.println(html+="请使用手机下单!");
	         return;
		 }*/
		 
	    try { 
    	        MerchantSignEntity merchantSignEntity= merchantService.queryMerchantSignByUserId(Long.valueOf(merchantId));
    	         if(null==merchantSignEntity){
    	             out.println(html+="商户不存在!");
    	             return;
    	         }    	         
    	         HashMap map=new HashMap();
    	         map.put("merchantId", merchantId);
    	         map.put("payAmount", payAmount);
    	         map.put("orderNo", threadOrderNo);
    	         map.put("nonce_str", nonce_str);      
    	         Map<String,String> contentData= PaymentUtils.filterBlank(map);
    	         String data=PaymentUtils.coverMap2String(contentData);
    	         String stringSignTemp=data+"&key="+merchantSignEntity.getPublicKey();        
    	         System.out.println("签名数据="+stringSignTemp);
    	         String sign=MD5Util.MD5(stringSignTemp).toUpperCase();
    	         System.out.println("签名="+sign);     
    	         if(!requestSign.toUpperCase().equals(sign)){
    	             out.println(html+="非法请求!");
    	             return;
    	         } 	        
	            MerchantTradeEntity merchantTradeEntity=new MerchantTradeEntity();
	            merchantTradeEntity.setUserId(Long.valueOf(merchantId));
	            merchantTradeEntity.setPayAmount(Integer.valueOf(payAmount));            
	            merchantTradeEntity.setFromSource(DeviceUtils.getBrowser(request));
	            merchantTradeEntity.setThreadOrderNo(threadOrderNo);            
	            String rechargeRequestNo = merchantRechargeService.merchantRechargeAddOrder(merchantTradeEntity);  
	            String path = request.getContextPath();
	            String notifyUrl = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/ThreadMerchantRechargeNotify?requestTradeNo="+rechargeRequestNo;
	            html=paymentService.webPay(Integer.valueOf(payAmount), rechargeRequestNo, notifyUrl); 
        } catch (MerchantException e) {
            html=e.getMessage();
            logger.info("下单出错",e);
        } catch (Exception e) {
            logger.info("下单出错",e);
        }	      
	    out.println(html);
	}
	
	/**
     * 第三方商户充值回调
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value ="/ThreadMerchantRechargeNotify")
    public String ThreadMerchantRechargeNotify(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws IOException{
         String requestTradeNo=request.getParameter("requestTradeNo");
         boolean paymentStatus=false;
         try {
             //根据渠道请求流水号查询
             ChannelTradeEntity channelTradeEntity=merchantRechargeService.queryChannelTradeByRequestTradeNo(requestTradeNo);
             if(null!=channelTradeEntity){
                 //处理充值
                 paymentStatus=merchantRechargeService.merchantRechargePaySuccess(requestTradeNo);
                 if(paymentStatus){
                     //查询第三方流水号
                     MerchantTradeEntity merchantTradeEntity= merchantRechargeService.queryTradeById(channelTradeEntity.getMerchantTradeId());
                     MerchantSignEntity merchantSignEntity= merchantService.queryMerchantSignByUserId(channelTradeEntity.getUserId());
                     response.sendRedirect(merchantSignEntity.getNotifyUrl()+merchantTradeEntity.getThreadOrderNo());
                 }
             }            
        } catch (Exception e) {
            logger.error("查询出错",e.getMessage());
            model.addAttribute("paymentMessage", e.getMessage());
        }       
        return "/paymentResult";
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
            String merchantId=request.getParameter("merchantId");
            String threadOrderNo=request.getParameter("orderNo");
            String nonce_str=request.getParameter("nonce_str");
            String requestSign=request.getParameter("sign"); 
            MerchantSignEntity merchantSignEntity= merchantService.queryMerchantSignByUserId(Long.valueOf(merchantId));
            if(null==merchantSignEntity){
                return Result.failed("failed","未找到相关商户");
            }            
            HashMap map=new HashMap();
            map.put("merchantId", merchantId);          
            map.put("orderNo", threadOrderNo);  
            map.put("nonce_str", nonce_str);  
            Map<String,String> contentData= PaymentUtils.filterBlank(map);
            String data=PaymentUtils.coverMap2String(contentData);
            String stringSignTemp=data+"&key="+merchantSignEntity.getPublicKey();        
            System.out.println("签名数据="+stringSignTemp);
            String sign=MD5Util.MD5(stringSignTemp).toUpperCase();
            System.out.println("签名="+sign);     
            if(!requestSign.toUpperCase().equals(sign)){
                return Result.failed("failed","非法请求");
            }
            
            MerchantTradeEntity merchantTradeEntity=merchantRechargeService.queryTradeByUserIdandThreadOrderNo(Long.valueOf(merchantId), threadOrderNo);
            Map<String,Object> resultMap=new HashMap<String,Object>();
            if(null!=merchantTradeEntity){
                resultMap.put("amount", merchantTradeEntity.getPayAmount());
                resultMap.put("needPayAmount", merchantTradeEntity.getNeedPayAmount());
                resultMap.put("payStatus", merchantTradeEntity.getPayStatus());
                resultMap.put("paymentOrder", merchantTradeEntity.getId());
                resultMap.put("paymentTime", merchantTradeEntity.getConfirmPayTime());
                return Result.success(resultMap);
            } else{
                return Result.failed("failed","未找到相关订单");
            }
        } catch (Exception e) {
             return Result.failed("failed",e.getMessage());
        }           
    }

}
