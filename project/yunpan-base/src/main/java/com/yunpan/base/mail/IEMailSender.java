package com.yunpan.base.mail;

import java.util.Map;

public interface IEMailSender {

	public void sendSimpleEmail(String toAddress,String title,String content);
	
	
	public void sendSimpleEmail(String mailType,Map<String,String> context);
	
}
