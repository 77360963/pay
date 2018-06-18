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

import com.yunpan.base.annotation.IfNeedLogin;
import com.yunpan.base.web.util.Result;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.service.AdminMerchantService;
import com.yunpan.service.service.MerchantAccountService;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.MerchantSettlementService;
import com.yunpan.service.service.bean.MerchantInfoBean;

@Controller
public class MerchantSettlementController {
	
	 private static final Logger logger = LoggerFactory.getLogger(MerchantSettlementController.class);
	
	@Autowired
	private MerchantSettlementService merchantSettlementService;
	
	@Autowired
	private MerchantAccountService merchantAccountService;
	
	@Autowired
	private MerchantRechargeService merchantRechargeService;
	
	@Autowired
	private AdminMerchantService adminMerchantService;
	
	/**
	 * 商户交易列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@IfNeedLogin
	@RequestMapping(value ="/merchantSettlement")	
	public String merchantRecharge(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws IOException{		
		String userId=request.getParameter("userId");
		MerchantTradeEntityBean queryMerchantTradeEntityBean=new MerchantTradeEntityBean();
		queryMerchantTradeEntityBean.setUserId(Long.valueOf(userId));
		List<MerchantTradeEntityBean> merchantTradeList=merchantSettlementService.queryMerchantTrade(queryMerchantTradeEntityBean);
		model.addAttribute("merchantTradeList", merchantTradeList);
		return "/merchantSettlement";	
	}
	
	@IfNeedLogin
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
	
	@IfNeedLogin
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
	
	/**
	 *查看所有商户详情
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@IfNeedLogin
	@RequestMapping(value ="/queryMerchantInfo")	
	public String queryMerchantInfo(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws IOException{		
		List<MerchantInfoBean> merchantInfoList=adminMerchantService.queryMerchantInfo();
		model.addAttribute("merchantInfoList", merchantInfoList);
		return "/adminMerchantInfo";	
	}
	
	/**
	 * 修改商户费率
	 * @param request
	 * @param model
	 * @return
	 */
	@IfNeedLogin
	@RequestMapping(value ="/modfiyMerchantRate")
    @ResponseBody
    public Map modfiyMerchantRate(HttpServletRequest request,final ModelMap model){
        try {
            String userId=request.getParameter("userId"); 
            String rate=request.getParameter("rate"); 
            boolean result=adminMerchantService.modfiyMerchantRate(Long.valueOf(userId), new BigDecimal(rate));
            return Result.success(result);
        } catch (Exception e) {
            return Result.failed("failed",e.getMessage());
        }          
    }
	

}
