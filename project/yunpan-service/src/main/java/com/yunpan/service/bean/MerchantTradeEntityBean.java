package com.yunpan.service.bean;


public class MerchantTradeEntityBean {
    /**
     * <pre>
     * 客户ID
     * 表字段 : t_merchant_trade.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 客户id
     * 表字段 : t_merchant_trade.merchant_id
     * </pre>
     */
    private Long userId;

    /**
     * <pre>
     * 交易金额
     * 表字段 : t_merchant_trade.pay_amount
     * </pre>
     */
    private String payAmount;

    /**
     * <pre>
     * 实际结算金额
     * 表字段 : t_merchant_trade.need_pay_amount
     * </pre>
     */
    private String needPayAmount;

    /**
     * <pre>
     * 确认结算金额
     * 表字段 : t_merchant_trade.confirm_pay_amount
     * </pre>
     */
    private String confirmPayAmount;

    /**
     * <pre>
     * 交易类型
     * 表字段 : t_merchant_trade.trans_type
     * </pre>
     */
    private String transType;

    /**
     * <pre>
     * 支付状态
     * 表字段 : t_merchant_trade.pay_status
     * </pre>
     */
    private Integer payStatus;

    /**
     * <pre>
     * 确认时间
     * 表字段 : t_merchant_trade.confirm_pay_time
     * </pre>
     */
    private String confirmPayTime;
    
    /**
     * <pre>
     * 支付渠道名称
     * 表字段 : t_merchant_trade.out_channel_no
     * </pre>
     */
    private String outChannelNo;

    /**
     * <pre>
     * 渠道支付流水号
     * 表字段 : t_merchant_trade.out_trade_No
     * </pre>
     */
    private String outTradeNo;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant_trade.created_time
     * </pre>
     */
    private String createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getNeedPayAmount() {
		return needPayAmount;
	}

	public void setNeedPayAmount(String needPayAmount) {
		this.needPayAmount = needPayAmount;
	}

	public String getConfirmPayAmount() {
		return confirmPayAmount;
	}

	public void setConfirmPayAmount(String confirmPayAmount) {
		this.confirmPayAmount = confirmPayAmount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getConfirmPayTime() {
		return confirmPayTime;
	}

	public void setConfirmPayTime(String confirmPayTime) {
		this.confirmPayTime = confirmPayTime;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getOutChannelNo() {
		return outChannelNo;
	}

	public void setOutChannelNo(String outChannelNo) {
		this.outChannelNo = outChannelNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
    
    

     
}