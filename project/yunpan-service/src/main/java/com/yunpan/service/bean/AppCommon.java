package com.yunpan.service.bean;

import java.math.BigDecimal;

public class AppCommon {
    
    /**
     * 充值
     */
    public static String TRANS_TYPE_I="I";
    
    /**
     * 提现
     */
    public static String TRANS_TYPE_O="O";
    
    /**
     * 签到收益
     */
    public static String TRANS_TYPE_P="P";
    
    /**
     * 交易分润
     */
    public static String TRANS_TYPE_F="F";
    
	
	/*
	 * 支付状态（初始化）
	 */
	public static int  PAY_STATUS_INIT=0;
	
	/*
	 * 支付状态（支付成功）
	 */
	public static int  PAY_STATUS_SUCCESS=1;
	
	
	/*
	 * 支付状态（支付成功）
	 */
	public static int  USER_STATUS_OPEN=1;
	
	/*
	 * 支付状态（支付成功）
	 */
	public static int  USER_STATUS_STOP=0;
	
	
	public static String SESSION_KEY="merchantEntityKey";
	
	public static String SESSION_KEY_ROLE="merchantEntityKeyRole";
	
	
	public static int  USER_TYPE_MERCHANT=1; 
	
	public static int  USER_TYPE_CUSTOMER=2; 
	
	/**
	 * 来源平台
	 */
	public static int PLATFORM=1;
	
	/**
	 * 默认费率
	 */
	public static BigDecimal PLATFORM_RATE=new BigDecimal("0.006");
	
	/**
     * 最小设置费率
     */
    public static BigDecimal PLATFORM_RATE_MIN=new BigDecimal("0.004");
    
	/**
	 * 提现最小金额
	 */
	public static int PAYMENTMINAMT=1000;
	
	public static String CHANNEL_NO="df";
	
	/**
	 * 签到最大金额
	 */
	public static int SIGNIN_MAX_AMOUNT=6;
	
	/**
	 * 发送短信(注册)
	 */
	public static String MAIL_REGISTER="register";
	
	/**
     * 发送短信(提现)
     */
    public static String MAIL_WITHDRAW="withdraw";
    
    /**
     * 发送短信(充值)
     */
    public static String MAIL_RECHARGE="recharge";
	
}
