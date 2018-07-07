package com.yunpan.web.controller;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.yunpan.base.web.util.CookieUtil;
import com.yunpan.base.web.util.Result;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.data.entity.MerchantRateEntity;
import com.yunpan.data.entity.MerchantTradeEntity;
import com.yunpan.service.bean.AppCommon;
import com.yunpan.service.bean.MerchantAccountEntityBean;
import com.yunpan.service.bean.MerchantTradeEntityBean;
import com.yunpan.service.exception.MerchantException;
import com.yunpan.service.service.MerchantAccountService;
import com.yunpan.service.service.MerchantRechargeService;
import com.yunpan.service.service.MerchantService;
import com.yunpan.service.service.PaymentService;
import com.yunpan.service.service.UserService;
import com.yunpan.service.service.bean.MerchantInfoBean;
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
	
	@Autowired
	private UserService userService;
	
	/**
	 * 商户充值
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value ="/merchantRecharge")
	@ResponseBody
	public Map merchantRecharge(HttpServletRequest request,HttpServletResponse response) throws IOException{		
		if(!DeviceUtils.isMobileDevice(request)){
			 return Result.failed("failed","下单失败");
		}
		Long merchantId=Long.valueOf(request.getParameter("merchantId"));		
		int payAmount=new BigDecimal(request.getParameter("payAmount")).multiply(new BigDecimal(100)).intValue();
		MerchantTradeEntity merchantTradeEntity=new MerchantTradeEntity();
		merchantTradeEntity.setUserId(merchantId);
		merchantTradeEntity.setPayAmount(payAmount);
		merchantTradeEntity.setPayStatus(AppCommon.PAY_STATUS_INIT);
		merchantTradeEntity.setFromSource(DeviceUtils.getBrowser(request));
		try {
			String rechargeRequestNo = merchantRechargeService.merchantRechargeAddOrder(merchantTradeEntity);
			String path = request.getContextPath();
	        String notifyUrl = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/merchantRechargeNotify?requestTradeNo="+rechargeRequestNo;
			Map map=paymentService.webPayMap(payAmount,rechargeRequestNo,notifyUrl);
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
		 String requestTradeNo=request.getParameter("requestTradeNo");
		 boolean paymentStatus=false;
		 try {
			 paymentStatus=merchantRechargeService.merchantRechargePaySuccess(requestTradeNo);
		} catch (Exception e) {
			logger.error("查询出错",e.getMessage());
			model.addAttribute("paymentMessage", e.getMessage());
		}
		model.addAttribute("paymentStatus", paymentStatus);
		model.addAttribute("requestTradeNo", requestTradeNo);
		return "/paymentResult";
	}
	
	/**
	 * 查询商户充值交易记录
	 * @param request
	 * @throws Exception 
	 */
	@IfNeedLogin
	@RequestMapping(value ="/queryRechargeTrade")
	public String queryRechargeTradeList(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		 MerchantEntity merchantEntity=getUserSession(request,response);	
		 String transType="I";		 
		 List<MerchantTradeEntityBean> merchantRechargeList=merchantRechargeService.queryMerchantTradeByUserId(merchantEntity.getUserId(),transType);
		 model.addAttribute("merchantRechargeList", merchantRechargeList);
		 return "/merchantRechargeList";		
	}
	
	/**
	 * 查询商户提现交易记录
	 * @param request
	 * @throws Exception 
	 */
	@IfNeedLogin
	@RequestMapping(value ="/queryWithdrawTrade")
	public String queryWithdrawTradeList(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		  MerchantEntity merchantEntity=getUserSession(request,response);	
		  String transType="O";
		 List<MerchantTradeEntityBean> merchantRechargeList=merchantRechargeService.queryMerchantTradeByUserId(merchantEntity.getUserId(),transType);
		 model.addAttribute("merchantRechargeList", merchantRechargeList);
		 return "/merchantWithdrawList";		
	}
	
	
	/**
	 * 查询商户返现佣金交易记录
	 * @param request
	 * @throws Exception 
	 */
	@IfNeedLogin
	@RequestMapping(value ="/queryCommissionTrade")
	public String queryCommissionTradeList(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		  MerchantEntity merchantEntity=getUserSession(request,response);	
		  String transType="F";
		 List<MerchantTradeEntityBean> merchantRechargeList=merchantRechargeService.queryMerchantTradeByUserId(merchantEntity.getUserId(),transType);
		 model.addAttribute("merchantRechargeList", merchantRechargeList);
		 return "/merchantCommissionList";		
	}
	
	/**
	 * 查询商户推荐用户记录
	 * @param request
	 * @throws Exception 
	 */
	@IfNeedLogin
	@RequestMapping(value ="/queryRecommendTrade")
	public String queryRecommendTradeList(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		  MerchantEntity merchantEntity=getUserSession(request,response);		 
		  List<MerchantInfoBean> merchantInfoList=userService.queryRecommendTradeList(merchantEntity.getUserId());
		 model.addAttribute("merchantInfoList", merchantInfoList);
		 return "/merchantRecommendList";		
	}
	
	/**
     * 修改推荐用户费率
     * @param request
     * @throws Exception 
     */
    @IfNeedLogin
    @RequestMapping(value ="/modfiyRecommendRate")
    @ResponseBody
    public Map modfiyRecommendRate(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
          MerchantEntity merchantEntity=getUserSession(request,response); 
          String userId=request.getParameter("userId");
          String rate=request.getParameter("rate");
          int updateStatus=userService.modfiyRecommendRate(Long.valueOf(userId), merchantEntity.getUserId(), new BigDecimal(rate));
         if(updateStatus>0){
             return Result.success();
         }
         return Result.failed("修改费率失败");
    }
	
    /**
	 * 商户退出
	 * @param request
     * @throws IOException 
	 */
	@RequestMapping(value ="/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{		
		CookieUtil.removeCookie(response, "loginUserName");
		CookieUtil.removeCookie(response, "loginPassword");         
 		//false代表：不创建session对象，只是从request中获取。
 		HttpSession session = request.getSession(false);
 		if(session!=null){
 			logger.info("用户主动退出");
 			session.removeAttribute(AppCommon.SESSION_KEY);
 			session.removeAttribute(AppCommon.SESSION_KEY_ROLE);
 		} 		
 		response.sendRedirect(request.getContextPath() + "/login");
	}
    
	
	/**
	 * 商户登录
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value ="/login")
	public String merchantLoginIndex(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		 String loginCookieUserName = CookieUtil.getUid(request, "loginUserName");  
         String loginCookiePassword = CookieUtil.getUid(request, "loginPassword");        
     	 try {
				MerchantInfoBean merchantInfoBean=merchantService.merchantLogin(loginCookieUserName, loginCookiePassword);
				request.getSession().setAttribute(AppCommon.SESSION_KEY, merchantInfoBean.getMerchantEntity());
				request.getSession().setAttribute(AppCommon.SESSION_KEY_ROLE, merchantInfoBean.getUniUserEntity().getRole());
				String path = request.getContextPath();
				String webStockPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/webSocketServer";
				request.getSession().setAttribute("webStockPath", webStockPath);
				response.sendRedirect(request.getContextPath() + "/merchantIndex");
		  } catch (Exception e) {					
				logger.info("Cookie登录出错,loginCookieUserName={},loginCookiePassword={}",loginCookieUserName,loginCookiePassword);
		  }		
		 return "/merchantLogin";
	 }
	
	/**
     * 商户登录
     * @param request
     */
    @RequestMapping(value ="/merchantLogin")
    @ResponseBody
    public Map merchantLogin(HttpServletRequest request,HttpServletResponse response){
        String loginName=request.getParameter("name");
        String password=request.getParameter("password");
        String kaptcha=request.getParameter("kaptcha");
        if(!this.verify(request)){
            return Result.failed("验证码错误");
        }
        try {
        	MerchantInfoBean merchantInfoBean=merchantService.merchantLogin(loginName, password);
        	String path = request.getContextPath();
			String webStockPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/webSocketServer";
			request.getSession().setAttribute(AppCommon.SESSION_KEY, merchantInfoBean.getMerchantEntity());
			request.getSession().setAttribute(AppCommon.SESSION_KEY_ROLE, merchantInfoBean.getUniUserEntity().getRole());
			request.getSession().setAttribute("webStockPath", webStockPath);
			CookieUtil.addCookie(response, "loginUserName", loginName);
			CookieUtil.addCookie(response, "loginPassword", password);			
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
	 * 商户首页
	 * @param request
     * @throws Exception 
	 */
    @IfNeedLogin
	@RequestMapping(value ="/merchantIndex")
	public String merchantIndex(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	Long userId=getUserSession(request,response).getUserId();
		return "/merchantIndex";
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
			String loginName=request.getParameter("loginName").trim();
			String password=request.getParameter("password").trim();
			String merchantName=request.getParameter("merchantName").trim();
			String mobile=request.getParameter("mobile").trim();
			String contacts=request.getParameter("contacts").trim();
			String paymentMethod=request.getParameter("paymentMethod").trim();
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
	@IfNeedLogin
	@RequestMapping(value ="/merchantInfoScanQR")	
	public String queryMerchantById(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
		Long userId=getUserSession(request,response).getUserId();
		MerchantEntity merchantEntity=merchantService.queryMerchantInfoByUserId(userId);
		String path = request.getContextPath();
        String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/merchantPayment?merchantId="+userId;
		System.out.println(basePath);
		model.addAttribute("merchantEntity", merchantEntity);
		model.addAttribute("basePath", basePath);
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
    @IfNeedLogin
	@RequestMapping(value ="/queryMerchantAccount")	
	public String queryMerchantAccountByMerchantId(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{		
		MerchantEntity merchantEntity=getUserSession(request,response);	
		MerchantAccountEntityBean merchantAccountEntityBean=merchantService.queryMerchantAccountByUserId(merchantEntity.getUserId());	
		model.addAttribute("merchantEntity", merchantEntity);
		model.addAttribute("merchantAccountEntity", merchantAccountEntityBean);
		return "/merchantAccount";	
	}
	
	@IfNeedLogin
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
	
	@IfNeedLogin
    @RequestMapping(value ="/userSignin")
    @ResponseBody
    public Map userSigninProcess(HttpServletRequest request,HttpServletResponse response,final ModelMap model){      
	    try {
            MerchantEntity merchantEntity=getUserSession(request,response);           
            boolean result=userService.userSignin(merchantEntity.getUserId());
            return Result.success(result);
        } catch (Exception e) {
             return Result.failed("failed",e.getMessage());
        }       
	}
    
	@IfNeedLogin
    @RequestMapping(value ="/userSigninList")
    public String userSigninList(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{      
        MerchantEntity merchantEntity=getUserSession(request,response); 
        String transType=AppCommon.TRANS_TYPE_P;      
       List<MerchantTradeEntityBean> userSigninList=merchantRechargeService.queryMerchantTradeByUserId(merchantEntity.getUserId(),transType);
       model.addAttribute("userSigninList", userSigninList);
       return "/userSigninList";  
    }
	
	
	public MerchantEntity getUserSession(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		MerchantEntity merchantEntity=(MerchantEntity)request.getSession().getAttribute(AppCommon.SESSION_KEY);
		if(null==merchantEntity){
			  response.sendRedirect(request.getContextPath()+"/merchantLoginIndex");
		}
		return merchantEntity;
	}
	
	
	
	@IfNeedLogin
    @RequestMapping(value ="/merchantCenter")
    public String merchantAccount(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{      
       MerchantEntity merchantEntity=getUserSession(request,response); 
       MerchantRateEntity merchantRateEntity=merchantService.queryMerchantRateByUserId(merchantEntity.getUserId());
       model.addAttribute("merchantEntity", merchantEntity);
       model.addAttribute("merchantRateEntity", merchantRateEntity);
       return "/merchantCenter";  
    }

}
