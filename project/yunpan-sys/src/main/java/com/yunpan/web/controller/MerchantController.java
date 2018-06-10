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

import com.google.code.kaptcha.Constants;
import com.yunpan.base.tool.DeviceUtils;
import com.yunpan.base.web.util.Result;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.bean.MerchantAccountEntityBean;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantAccountService;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.MerchantService;
import com.yunpan.service.service.PaymentService;
import com.yunpan.service.service.bean.MerchantRegisterBean;

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
		merchantTradeEntity.setUserId(merchantId);
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
	 * 查询商户交易记录
	 * @param request
	 * @throws Exception 
	 */
	
	@RequestMapping(value ="/queryMerchantTrade")
	public String queryMerchantRechargeList(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		  MerchantEntity merchantEntity=getUserSession(request,response);			
		 List<MerchantTradeEntityBean> merchantRechargeList=merchantRechargeService.queryMerchantTradeByUserId(merchantEntity.getUserId());
		 model.addAttribute("merchantRechargeList", merchantRechargeList);
		 return "/merchantRechargeList";		
	}
	
	
	/**
	 * 商户登录
	 * @param request
	 */
	@RequestMapping(value ="/login")
	public String merchantIndex(HttpServletRequest request){
		return "/merchantLogin";
	}
	
	/**
     * 商户登录
     * @param request
     */
    @RequestMapping(value ="/merchantLogin")
    @ResponseBody
    public Map merchantLogin(HttpServletRequest request){
        String loginName=request.getParameter("name");
        String password=request.getParameter("password");
        String kaptcha=request.getParameter("kaptcha");
        if(!this.verify(request)){
            return Result.failed("验证码错误");
        }
        try {
        	MerchantEntity merchantEntity=merchantService.merchantLogin(loginName, password);
			request.getSession().setAttribute(AppCommon.SESSION_KEY, merchantEntity);
			 return Result.success();
		} catch (Exception e) {
			 return Result.failed(e.getMessage());
		}
       
    }
    
    public static boolean verify(HttpServletRequest request) {
          //从session中取出servlet生成的验证码text值
          String expected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
          //获取用户页面输入的验证码
          String received = request.getParameter("kaptcha");
          return received != null && received.equalsIgnoreCase(expected);
      }

	
	
	/**
	 * 商户注册
	 * @param request
	 */
	@RequestMapping(value ="/register")
	public String merchantRegister(HttpServletRequest request){
		return "/merchantRegister";
	}
	
	
	/**
	 * 增加商户
	 * @param request
	 */
	@RequestMapping(value ="/addMerchantInfo")
	@ResponseBody
	public Map addMerchant(HttpServletRequest request){
		try {
			String loginName=request.getParameter("loginName");
			String password=request.getParameter("password");
			String merchantName=request.getParameter("merchantName");
			String mobile=request.getParameter("mobile");
			String contacts=request.getParameter("contacts");
			String paymentMethod=request.getParameter("paymentMethod");
			MerchantRegisterBean merchantRegisterBean=new MerchantRegisterBean();
			merchantRegisterBean.setLoginName(loginName);
			merchantRegisterBean.setPassword(password);
			merchantRegisterBean.setName(merchantName);
			merchantRegisterBean.setMobile(mobile);	
			merchantRegisterBean.setContacts(contacts);
			merchantRegisterBean.setPaymentMethod(paymentMethod);
			merchantService.addMerchant(merchantRegisterBean);
			return Result.success();
		} catch (Exception e) {
			logger.info("注册商户信息失败",e);
			return Result.failed(e.getMessage());
		}
		
	}
	
	/**
	 * 查询指定商户收款二维码
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value ="/merchantInfoScanQR")	
	public String queryMerchantById(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		Long userId=getUserSession(request,response).getUserId();
		MerchantEntity merchantEntity=merchantService.queryMerchantInfoByUserId(userId);
		String aa=request.getContextPath();
		System.out.println(aa);
		model.addAttribute("merchantEntity", merchantEntity);
		return "/merchantInfoScanQR";	
	}
	
	/**
     * 查询指定商户收款二维码
     * @param request
	 * @throws IOException 
     */
    @RequestMapping(value ="/merchantPayment")   
    public String merchantPayment(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
    	String userId=request.getParameter("merchantId");		
		MerchantEntity merchantEntity=merchantService.queryMerchantInfoByUserId(Long.valueOf(userId)); 
		if(null==merchantEntity){
			throw new MerchantException("", "非法商户");
		}
        model.addAttribute("merchantEntity", merchantEntity);
        return "/payment";   
    }
	
	/**
	 * 查询指定商户账户信息
	 * @param request
	 * @throws IOException 
	 */
	@RequestMapping(value ="/queryMerchantAccount")	
	public String queryMerchantAccountByMerchantId(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{		
		MerchantEntity merchantEntity=getUserSession(request,response);	
		MerchantAccountEntityBean merchantAccountEntityBean=merchantService.queryMerchantAccountByUserId(merchantEntity.getUserId());	
		model.addAttribute("merchantEntity", merchantEntity);
		model.addAttribute("merchantAccountEntity", merchantAccountEntityBean);
		return "/merchantAccount";	
	}
	
	@RequestMapping(value ="/merchantWithdraw")
	@ResponseBody
    public Map merchantWithdraw(HttpServletRequest request,HttpServletResponse response,final ModelMap model){      
        try {
        	MerchantEntity merchantEntity=getUserSession(request,response);	
            String amount=request.getParameter("amount");
            int amt=new BigDecimal(amount).multiply(new BigDecimal(100)).intValue();
            if(amt<merchantEntity.getPaymentMinamt()){
            	 return Result.failed("failed","不能少于平台最小结算金额");
            }
            boolean result=merchantAccountService.withdrawByUserId(merchantEntity.getUserId(), amt);
            return Result.success(result);
        } catch (Exception e) {
        	 return Result.failed("failed",e.getMessage());
        }       
    }
	
	
	
	
	public MerchantEntity getUserSession(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		MerchantEntity merchantEntity=(MerchantEntity)request.getSession().getAttribute(AppCommon.SESSION_KEY);
		if(null==merchantEntity){
			  response.sendRedirect(request.getContextPath()+"/merchantLoginIndex");
		}
		return merchantEntity;
	}
	
	
	

}
