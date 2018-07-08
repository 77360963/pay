package com.yunpan.base.mail.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.yunpan.base.mail.EmailException;
import com.yunpan.base.mail.IEMailSender;
import com.yunpan.base.tool.DateTool;

@Component
public class EMailSender implements IEMailSender {
    protected  final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = false)
	private JavaMailSender mailSender; // 自动注入的Bean
	
	 //spring提供的发送消息模板
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

	
	private String mailFrom="77360963@qq.com";
	
	 @Autowired
	private BaiduText2audio baiduText2audio;
	

	private void check() {
		if (mailSender == null) {
			throw new EmailException("===>email未配置");
		}
	}

	@Override
	public void sendSimpleEmail(String toAddress, String title, String content) {
		check();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(toAddress); // 自己给自己发送邮件
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
		logger.info("========>邮件发送成功:{},{},{}",toAddress,title,content);		
	}	

	@Override
	public void sendSimpleEmail(final String mailType,final  Map<String,String> context) {
		new Thread(){
			public void run() {
				try {			
					HashMap<String,String> map=this.getMailContext(mailType, context);
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom(mailFrom);
					message.setTo(mailFrom); // 自己给自己发送邮件
					message.setSubject(map.get("title"));
					message.setText(map.get("context"));
					mailSender.send(message);
					logger.info("========>邮件发送成功:{},{}",map.get("title"),map.get("context"));
				} catch (MailException e) {
					logger.error("邮件发送失败,类型:{}"+mailType,e);
				}		
			}

			private HashMap<String, String> getMailContext(String mailType,	Map<String, String> context) {
				StringBuffer sb=new StringBuffer();
				HashMap<String,String> map=new HashMap<String,String>();
				if("register".equals(mailType)){
					sb.append("商户名称:").append(context.get("merchantName")).append("\r\n");
					sb.append("联系人:").append(context.get("contacts")).append("\r\n");
					sb.append("联系电话:").append(context.get("mobile")).append("\r\n");
					sb.append("收款方式:").append(context.get("paymentMethod")).append("\r\n");
					sb.append("注册时间:").append(DateTool.formatFullDate(new Date())).append("\r\n");
					map.put("title", "商户注册【"+context.get("merchantName")+"】");
					map.put("context", sb.toString());
				}else if("withdraw".equals(mailType)){
                    sb.append("商户名称:").append(context.get("merchantName")).append("\r\n");
                    sb.append("联系人:").append(context.get("contacts")).append("\r\n");
                    sb.append("联系电话:").append(context.get("mobile")).append("\r\n");
                    sb.append("收款方式:").append(context.get("paymentMethod")).append("\r\n");
                    sb.append("提现时间:").append(DateTool.formatFullDate(new Date())).append("\r\n");
                    sb.append("提现金额:").append(context.get("payAmount")).append("\r\n");
                    map.put("title", "商户提现【"+context.get("merchantName")+"】");
                    map.put("context", sb.toString());
                }else if("recharge".equals(mailType)){
                    sb.append("商户名称:").append(context.get("merchantName")).append("\r\n");
                    sb.append("联系人:").append(context.get("contacts")).append("\r\n");
                    sb.append("联系电话:").append(context.get("mobile")).append("\r\n");
                    sb.append("收款方式:").append(context.get("paymentMethod")).append("\r\n");
                    sb.append("充值时间:").append(DateTool.formatFullDate(new Date())).append("\r\n");
                    sb.append("充值金额:").append(context.get("payAmount")).append("\r\n");
                    map.put("title", "商户充值【"+context.get("merchantName")+"】");
                    map.put("context", sb.toString());
                    //发送语音消息给指定用户
                    String Text2audioPath=baiduText2audio.getText2audio(context.get("fromSource"), context.get("payAmount"));
                    logger.info("userId={},语音内容={}",context.get("userId"),Text2audioPath);
                    messagingTemplate.convertAndSendToUser(context.get("userId"), "/queue/message", Text2audioPath);
                }else if("signin".equals(mailType)){
                    sb.append("商户名称:").append(context.get("merchantName")).append("\r\n");
                    sb.append("联系人:").append(context.get("contacts")).append("\r\n");
                    sb.append("联系电话:").append(context.get("mobile")).append("\r\n");
                    sb.append("收款方式:").append(context.get("paymentMethod")).append("\r\n");
                    sb.append("签到时间:").append(DateTool.formatFullDate(new Date())).append("\r\n");
                    sb.append("签到金额:").append(context.get("payAmount")).append("\r\n");
                    map.put("title", "商户签到【"+context.get("merchantName")+"】");
                    map.put("context", sb.toString());
                }    		
				return map;
			};
		}.start();		
		
	}	

}
