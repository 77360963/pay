package com.yunpan.web.controller;



import java.io.IOException;
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

import com.yunpan.base.web.util.Result;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.service.MerchantAccountService;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.MerchantSettlementService;

@Controller
public class MerchantSettlementController {
	
	 private static final Logger logger = LoggerFactory.getLogger(MerchantSettlementController.class);
	
	@Autowired
	private MerchantSettlementService merchantSettlementService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private MerchantRechargeService merchantRechargeService;
	
	/**
	 * 商户交易列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value ="/merchantSettlement")	
	public String merchantRecharge(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws IOException{		
		MerchantTradeEntityBean queryMerchantTradeEntityBean=new MerchantTradeEntityBean();
		List<MerchantTradeEntityBean> merchantTradeList=merchantSettlementService.queryMerchantTrade(queryMerchantTradeEntityBean);
		model.addAttribute("merchantTradeList", merchantTradeList);
		return "/merchantSettlement";	
	}
	
	
	@RequestMapping(value ="/confirmWithdraw")
	@ResponseBody
    public Map confirmWithdraw(HttpServletRequest request,final ModelMap model){
        try {
            String transId=request.getParameter("transId");       
            boolean result=merchantAccountService.confirmWithdrawByTransId(Long.valueOf(transId));
            return Result.success(result);
        } catch (Exception e) {
        	 return Result.failed("failed",e.getMessage());
        }        
    }
	
	@RequestMapping(value ="/orderRefresh")
	@ResponseBody
    public Map queryOrder(HttpServletRequest request,final ModelMap model){
        try {
            String merchantTrandId=request.getParameter("transId");            
            boolean result=merchantRechargeService.merchantRechargePaySuccess(Long.valueOf(merchantTrandId));
            return Result.success(result);
        } catch (Exception e) {
        	return Result.failed("failed",e.getMessage());
        }  
        
    }
	

}
