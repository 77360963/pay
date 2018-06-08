package com.yunpan.service.bean;

public class AppCommon {
    
    /**
     * 充值
     */
    public static String TRANS_TYPE_I="I";
    
    /**
     * 提现
     */
    public static String TRANS_TYPE_O="O";
	
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

}
