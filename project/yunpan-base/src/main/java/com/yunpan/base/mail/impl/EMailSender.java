package com.yunpan.base.mail.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.yunpan.base.mail.EmailException;
import com.yunpan.base.mail.IEMailSender;

@Component
public class EMailSender implements IEMailSender {
    protected  final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = false)
	private JavaMailSender mailSender; // 自动注入的Bean

	@Value("${mail.from:test@yintaokeji.com}")
	private String mailFrom;

	private void check() {
		if (mailSender == null) {
			throw new EmailException("===>email未配置");
		}
	}

	@Override
	public void sendSimpleEmail(String[] toAddress, String title, String content) {
		check();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(toAddress); // 自己给自己发送邮件
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
		logger.info("========>邮件发送成功:{},{},{}",toAddress,title,content);
		
	}

}
