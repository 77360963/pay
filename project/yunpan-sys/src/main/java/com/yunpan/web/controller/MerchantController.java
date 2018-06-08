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
import com.yunpan.base.annotation.IfNeedLogin;
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
	@IfNeedLogin
	@RequestMapping(value ="/queryMerchantRechargeList")
	public String queryMerchantRechargeList(HttpServletRequest request,final ModelMap model){
		 String merchantId=request.getParameter("merchantId");		
		 List<MerchantTradeEntity> merchantRechargeList=merchantRechargeService.queryMerchantRechargeByMerchantId(Long.valueOf(merchantId));
		 model.addAttribute("merchantRechargeList", merchantRechargeList);
		 return "/merchantRechargeList";		
	}
	
	
	/**
	 * 商户登录
	 * @param request
	 */
	@RequestMapping(value ="/merchantLoginIndex")
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
        String  name=request.getParameter("name");
        String password=request.getParameter("password");
        String kaptcha=request.getParameter("kaptcha");
        if(!this.verify(request)){
            return Result.failed("验证码错误");
        }
        if("1".equals(name)&&"1".equals(password)){
            return Result.success();
        }
        return Result.failed("用户或密码错误");
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
	@RequestMapping(value ="/merchantRegister")
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
			String merchantName=request.getParameter("merchantName");
			String mobile=request.getParameter("mobile");		
			MerchantEntity merchantEntity=new MerchantEntity();
			merchantEntity.setName(merchantName);
			merchantEntity.setMobile(mobile);		
			merchantService.addMerchant(merchantEntity);
			return Result.success();
		} catch (Exception e) {
			logger.info("添加商户信息失败",e);
		}
		return Result.failed("添加商户信息失败");
	}
	
	/**
	 * 查询指定商户收款二维码
	 * @param request
	 */
	@RequestMapping(value ="/merchantInfoScanQR")	
	public String queryMerchantById(HttpServletRequest request,final ModelMap model){
		String merchantId=request.getParameter("merchantId");
		MerchantEntity merchantEntity=merchantService.queryMerchantInfoById(Long.valueOf(merchantId));		
		model.addAttribute("merchantEntity", merchantEntity);
		return "/merchantInfoScanQR";	
	}
	
	/**
     * 查询指定商户收款二维码
     * @param request
     */
    @RequestMapping(value ="/merchantPayment")   
    public String merchantPayment(HttpServletRequest request,final ModelMap model){
        String merchantId=request.getParameter("merchantId");
        MerchantEntity merchantEntity=merchantService.queryMerchantInfoById(Long.valueOf(merchantId));      
        model.addAttribute("merchantEntity", merchantEntity);
        return "/pay";   
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
