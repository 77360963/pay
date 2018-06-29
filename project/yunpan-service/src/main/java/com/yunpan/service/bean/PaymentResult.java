package com.yunpan.service.bean;

import java.util.Date;

public class PaymentResult {
	
	/**
	 * 请求订单号
	 */
	private String orderNo;
	
	/**
	 * 支付状态
	 */
	private int paymentStatus;
	
	
	/**
	 * 支付金额
	 */
	private int payAmount;
	
	/**
	 * 实际支付金额
	 */
	private int needPayAmount;
	
	private Date paymentTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public int getNeedPayAmount() {
		return needPayAmount;
	}

	public void setNeedPayAmount(int needPayAmount) {
		this.needPayAmount = needPayAmount;
	}

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
	
	
	
	

}
