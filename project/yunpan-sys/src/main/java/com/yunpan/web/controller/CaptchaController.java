package com.yunpan.web.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yunpan.base.annotation.IfNeedLogin;
import com.yunpan.base.tool.QRCodeUtil;
import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.service.bean.AppCommon;

@Controller
public class CaptchaController {
    
    @Autowired
    private Producer producer;    
    
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("验证码: " + code);
        rsp.setDateHeader("Expires", 0);
        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        rsp.setHeader("Pragma", "no-cache");
        rsp.setContentType("image/jpeg");
        String capText = producer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage image = producer.createImage(capText);
        ServletOutputStream out = rsp.getOutputStream();
        ImageIO.write(image, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
    
    /**
     * 商户推广二维码
     * @param request
     * @param response
     * @throws Exception
     */
    @IfNeedLogin
    @RequestMapping(value = "/myRecommendScanQR", method = RequestMethod.GET)
    public void myRecommendScanQR(HttpServletRequest request,HttpServletResponse response) throws Exception {
        MerchantEntity merchantEntity=getUserSession(request,response);      
        String path = request.getContextPath();
        String registerUrl = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/register?parentUserId="+merchantEntity.getUserId();
        String text = registerUrl; // 随机生成验证码
        System.out.println("商户推广二维码： " + text);
        int width = 537; // 二维码图片的宽
        int height = 537; // 二维码图片的高
        String format = "png"; // 二维码图片的格式
        QRCodeUtil.generateQRCode(text, width, height, format, response);
    }
    
    /**
     * 商户收款二维码
     * @param request
     * @throws Exception 
     */
    @IfNeedLogin
    @RequestMapping(value ="/merchantInfoScanQRImage", method = RequestMethod.GET)   
    public void queryMerchantById(HttpServletRequest request,HttpServletResponse response,final ModelMap model) throws Exception{
        Long userId=getUserSession(request,response).getUserId();      
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/merchantPayment?merchantId="+userId;
        String text = basePath; // 随机生成验证码
        System.out.println("商户收款二维码： " + text);
        int width = 537; // 二维码图片的宽
        int height = 537; // 二维码图片的高
        String format = "png"; // 二维码图片的格式
        QRCodeUtil.generateQRCode(text, width, height, format, response);
    }
    
    
    public MerchantEntity getUserSession(HttpServletRequest request,HttpServletResponse response) throws Exception{     
        MerchantEntity merchantEntity=(MerchantEntity)request.getSession().getAttribute(AppCommon.SESSION_KEY);
        if(null==merchantEntity){
              response.sendRedirect(request.getContextPath()+"/merchantLoginIndex");
        }
        return merchantEntity;
    }


}
