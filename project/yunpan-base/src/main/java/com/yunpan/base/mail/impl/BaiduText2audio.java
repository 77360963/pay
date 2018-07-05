package com.yunpan.base.mail.impl;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunpan.base.tool.HttpClientUtil;

@Component
public class BaiduText2audio {
	
	private static String client_id="i1Go7bAvVv3DbyXqij7L8VyZ";
	
	private static String client_secret="6yceSsundRYZk5UFGk5NE1cg0GKzW7WK";
	
	private static String getTokenPath="https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id={0}&client_secret={1}";
	
	private static String getText2audio="http://tsn.baidu.com/text2audio?lan=zh&ctp=1&cuid=10624821&tok={0}&tex={1}&vol=9&per=0&spd=5&pit=5";
	
	
	public  String getToken(){
		
		String getHttpPath=MessageFormat.format(getTokenPath, client_id,client_secret);
		
		String html = HttpClientUtil.getInstance().sendHttpGet(getHttpPath);
		
		JSONObject object= JSON.parseObject(html);        
		
		return (String) object.get("access_token");
	}
	
   public  String getText2audio(String formSource,String amount){
	   
	    String access_token=getToken();
	    
	    String textString="";
	    
	    if("1".equals(formSource)){
	    	textString="支付宝到账"+amount+"元";
	    }else if("2".equals(formSource)){
	    	textString="微信到账"+amount+"元";
	    }
		
		String Text2audio=MessageFormat.format(getText2audio, access_token,textString);			  
		
		return Text2audio;
	}
	
	
	
	public static void main(String[] args) {
		//System.out.println(getText2audio("1","20"));
	}
	

}
