package com.yunpan.web.controller;



import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpan.base.tool.DeviceUtils;
import com.yunpan.base.web.util.Result;
import com.yunpan.data.entity.MerchantAccountEntity;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantAccountService;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.MerchantService;
import com.yunpan.service.service.PaymentService;

@Controller
public class MerchantController {
	
	 private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantRechargeService merchantRechargeService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;
	
	/**
	 * 商户充值
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value ="/merchantRecharge")
	@ResponseBody
	public Map merchantRecharge(HttpServletRequest request,HttpServletResponse response) throws IOException{		
		if(!DeviceUtils.isMobileDevice(request)||!DeviceUtils.isWeChat(request)){
			 return Result.failed("failed","下单失败");
		}
		Long merchantId=Long.valueOf(request.getParameter("merchantId"));		
		int payAmount=new BigDecimal(request.getParameter("payAmount")).multiply(new BigDecimal(100)).intValue();
		MerchantTradeEntity merchantTradeEntity=new MerchantTradeEntity();
		merchantTradeEntity.setMerchantId(merchantId);
		merchantTradeEntity.setPayAmount(payAmount);
		merchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_INIT);
		Long rechargeId;
		try {
			rechargeId = merchantRechargeService.merchantRechargeAddOrder(merchantTradeEntity);
			Map map=paymentService.webPayMap(payAmount, String.valueOf(rechargeId));
			return Result.success(map);
		} catch (MerchantException e) {				
			 return Result.failed("failed","下单失败");
		}
	}
	
	/**
	 * 商户充值回调
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value ="/merchantRechargeNotify")
	public String queryMerchantRecharge(HttpServletRequest request,final ModelMap model) throws IOException{
		 String orderId=request.getParameter("orderId");
		 boolean paymentStatus=false;
		 try {
			 paymentStatus=merchantRechargeService.merchantRechargePaySuccess(orderId);
		} catch (Exception e) {
			logger.error("查询出错",e.getMessage());
			model.addAttribute("paymentMessage", e.getMessage());
		}
		model.addAttribute("paymentStatus", paymentStatus);
		return "/paymentResult";
	}
	
	/**
	 * 查询商户充值记录
	 * @param request
	 */
	@RequestMapping(value ="/queryMerchantRechargeList")
	public String queryMerchantRechargeList(HttpServletRequest request,final ModelMap model){
		 String merchantId=request.getParameter("merchantId");		
		 List<MerchantTradeEntity> merchantRechargeList=merchantRechargeService.queryMerchantRechargeByMerchantId(Long.valueOf(merchantId));
		 model.addAttribute("merchantRechargeList", merchantRechargeList);
		 return "/merchantRechargeList";		
	}
	
	/**
	 * 增加商户
	 * @param request
	 */
	@RequestMapping(value ="/addMerchant")
	public void addMerchant(HttpServletRequest request){
		String merchantName=request.getParameter("merchantName");
		String mobile=request.getParameter("mobile");		
		MerchantEntity merchantEntity=new MerchantEntity();
		merchantEntity.setName(merchantName);
		merchantEntity.setMobile(mobile);		
		merchantService.addMerchant(merchantEntity);
	}
	
	/**
	 * 查询指定商户信息
	 * @param request
	 */
	@RequestMapping(value ="/queryMerchantInfoById")	
	public String queryMerchantById(HttpServletRequest request,final ModelMap model){
		String merchantId=request.getParameter("merchantId");
		MerchantEntity merchantEntity=merchantService.queryMerchantInfoById(Long.valueOf(merchantId));		
		model.addAttribute("merchantEntity", merchantEntity);
		return "/user";
	
	}
	
	/**
	 * 查询指定商户账户信息
	 * @param request
	 */
	@RequestMapping(value ="/queryMerchantAccountByMerchantId")	
	public String queryMerchantAccountByMerchantId(HttpServletRequest request,final ModelMap model){
		String merchantId=request.getParameter("merchantId");
		MerchantEntity merchantEntity=merchantService.queryMerchantInfoById(Long.valueOf(merchantId));		
		MerchantAccountEntity merchantAccountEntity=merchantService.queryMerchantAccountByMerchantId(Long.valueOf(merchantId));	
		model.addAttribute("merchantEntity", merchantEntity);
		model.addAttribute("merchantAccountEntity", merchantAccountEntity);
		return "/merchantAccount";	
	}
	
	@RequestMapping(value ="/merchantWithdraw")
	@ResponseBody
    public Map merchantWithdraw(HttpServletRequest request,final ModelMap model){      
        try {
            String merchantId=request.getParameter("merchantId");
            String amount=request.getParameter("amount");
            int amt=new BigDecimal(amount).multiply(new BigDecimal(100)).intValue();
            boolean result=merchantAccountService.withdrawByMerchantId(Long.valueOf(merchantId), amt);
            return Result.success(result);
        } catch (Exception e) {
         
        }
        return Result.failed("failed","下单失败");
    }
	
	@RequestMapping(value ="/confirmWithdraw")
	@ResponseBody
    public Map confirmWithdraw(HttpServletRequest request,final ModelMap model){
        try {
            String transId=request.getParameter("transId");       
            boolean result=merchantAccountService.confirmWithdrawByTransId(Long.valueOf(transId));
            return Result.success(result);
        } catch (Exception e) {
            
        }  
        return Result.failed("failed","下单失败");
    }
	
	

}
