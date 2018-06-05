package com.yunpan.base.mail;

public interface IEMailSender {

	public void sendSimpleEmail(String[] toAddress,String title,String content);
	
}
