package com.yunpan.data.entity;

import java.util.Date;

public class ChannelTradeEntity {
    /**
     * <pre>
     * 客户ID
     * 表字段 : t_channel_trade.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 客户id
     * 表字段 : t_channel_trade.user_id
     * </pre>
     */
    private Long userId;

    /**
     * <pre>
     * 商户交易表ID
     * 表字段 : t_channel_trade.merchant_trade_id
     * </pre>
     */
    private Long merchantTradeId;

    /**
     * <pre>
     * 请求支付流水号
     * 表字段 : t_channel_trade.request_trade_No
     * </pre>
     */
    private String requestTradeNo;

    /**
     * <pre>
     * 交易金额
     * 表字段 : t_channel_trade.pay_amount
     * </pre>
     */
    private Integer payAmount;

    /**
     * <pre>
     * 实际结算金额
     * 表字段 : t_channel_trade.need_pay_amount
     * </pre>
     */
    private Integer needPayAmount;

    /**
     * <pre>
     * 支付状态
     * 表字段 : t_channel_trade.pay_status
     * </pre>
     */
    private Integer payStatus;

    /**
     * <pre>
     * 渠道支付流水号
     * 表字段 : t_channel_trade.out_trade_No
     * </pre>
     */
    private String outTradeNo;

    /**
     * <pre>
     * 渠道支付完成时间
     * 表字段 : t_channel_trade.out_trade_pay_time
     * </pre>
     */
    private String outTradePayTime;

    /**
     * <pre>
     * 支付渠道名称
     * 表字段 : t_channel_trade.out_channel_no
     * </pre>
     */
    private String outChannelNo;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_channel_trade.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_channel_trade.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_channel_trade.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：客户ID
     * 表字段：t_channel_trade.id
     * </pre>
     *
     * @return t_channel_trade.id：客户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：客户ID
     * 表字段：t_channel_trade.id
     * </pre>
     *
     * @param id
     *            t_channel_trade.id：客户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：客户id
     * 表字段：t_channel_trade.user_id
     * </pre>
     *
     * @return t_channel_trade.user_id：客户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * <pre>
     * 设置：客户id
     * 表字段：t_channel_trade.user_id
     * </pre>
     *
     * @param userId
     *            t_channel_trade.user_id：客户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * <pre>
     * 获取：商户交易表ID
     * 表字段：t_channel_trade.merchant_trade_id
     * </pre>
     *
     * @return t_channel_trade.merchant_trade_id：商户交易表ID
     */
    public Long getMerchantTradeId() {
        return merchantTradeId;
    }

    /**
     * <pre>
     * 设置：商户交易表ID
     * 表字段：t_channel_trade.merchant_trade_id
     * </pre>
     *
     * @param merchantTradeId
     *            t_channel_trade.merchant_trade_id：商户交易表ID
     */
    public void setMerchantTradeId(Long merchantTradeId) {
        this.merchantTradeId = merchantTradeId;
    }

    /**
     * <pre>
     * 获取：请求支付流水号
     * 表字段：t_channel_trade.request_trade_No
     * </pre>
     *
     * @return t_channel_trade.request_trade_No：请求支付流水号
     */
    public String getRequestTradeNo() {
        return requestTradeNo;
    }

    /**
     * <pre>
     * 设置：请求支付流水号
     * 表字段：t_channel_trade.request_trade_No
     * </pre>
     *
     * @param requestTradeNo
     *            t_channel_trade.request_trade_No：请求支付流水号
     */
    public void setRequestTradeNo(String requestTradeNo) {
        this.requestTradeNo = requestTradeNo == null ? null : requestTradeNo.trim();
    }

    /**
     * <pre>
     * 获取：交易金额
     * 表字段：t_channel_trade.pay_amount
     * </pre>
     *
     * @return t_channel_trade.pay_amount：交易金额
     */
    public Integer getPayAmount() {
        return payAmount;
    }

    /**
     * <pre>
     * 设置：交易金额
     * 表字段：t_channel_trade.pay_amount
     * </pre>
     *
     * @param payAmount
     *            t_channel_trade.pay_amount：交易金额
     */
    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * <pre>
     * 获取：实际结算金额
     * 表字段：t_channel_trade.need_pay_amount
     * </pre>
     *
     * @return t_channel_trade.need_pay_amount：实际结算金额
     */
    public Integer getNeedPayAmount() {
        return needPayAmount;
    }

    /**
     * <pre>
     * 设置：实际结算金额
     * 表字段：t_channel_trade.need_pay_amount
     * </pre>
     *
     * @param needPayAmount
     *            t_channel_trade.need_pay_amount：实际结算金额
     */
    public void setNeedPayAmount(Integer needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    /**
     * <pre>
     * 获取：支付状态
     * 表字段：t_channel_trade.pay_status
     * </pre>
     *
     * @return t_channel_trade.pay_status：支付状态
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * <pre>
     * 设置：支付状态
     * 表字段：t_channel_trade.pay_status
     * </pre>
     *
     * @param payStatus
     *            t_channel_trade.pay_status：支付状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * <pre>
     * 获取：渠道支付流水号
     * 表字段：t_channel_trade.out_trade_No
     * </pre>
     *
     * @return t_channel_trade.out_trade_No：渠道支付流水号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * <pre>
     * 设置：渠道支付流水号
     * 表字段：t_channel_trade.out_trade_No
     * </pre>
     *
     * @param outTradeNo
     *            t_channel_trade.out_trade_No：渠道支付流水号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * <pre>
     * 获取：渠道支付完成时间
     * 表字段：t_channel_trade.out_trade_pay_time
     * </pre>
     *
     * @return t_channel_trade.out_trade_pay_time：渠道支付完成时间
     */
    public String getOutTradePayTime() {
        return outTradePayTime;
    }

    /**
     * <pre>
     * 设置：渠道支付完成时间
     * 表字段：t_channel_trade.out_trade_pay_time
     * </pre>
     *
     * @param outTradePayTime
     *            t_channel_trade.out_trade_pay_time：渠道支付完成时间
     */
    public void setOutTradePayTime(String outTradePayTime) {
        this.outTradePayTime = outTradePayTime == null ? null : outTradePayTime.trim();
    }

    /**
     * <pre>
     * 获取：支付渠道名称
     * 表字段：t_channel_trade.out_channel_no
     * </pre>
     *
     * @return t_channel_trade.out_channel_no：支付渠道名称
     */
    public String getOutChannelNo() {
        return outChannelNo;
    }

    /**
     * <pre>
     * 设置：支付渠道名称
     * 表字段：t_channel_trade.out_channel_no
     * </pre>
     *
     * @param outChannelNo
     *            t_channel_trade.out_channel_no：支付渠道名称
     */
    public void setOutChannelNo(String outChannelNo) {
        this.outChannelNo = outChannelNo == null ? null : outChannelNo.trim();
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_channel_trade.created_time
     * </pre>
     *
     * @return t_channel_trade.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_channel_trade.created_time
     * </pre>
     *
     * @param createdTime
     *            t_channel_trade.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_channel_trade.updated_time
     * </pre>
     *
     * @return t_channel_trade.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_channel_trade.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_channel_trade.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_channel_trade.status
     * </pre>
     *
     * @return t_channel_trade.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_channel_trade.status
     * </pre>
     *
     * @param status
     *            t_channel_trade.status：正常：1；删除：0
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChannelTradeEntity other = (ChannelTradeEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMerchantTradeId() == null ? other.getMerchantTradeId() == null : this.getMerchantTradeId().equals(other.getMerchantTradeId()))
            && (this.getRequestTradeNo() == null ? other.getRequestTradeNo() == null : this.getRequestTradeNo().equals(other.getRequestTradeNo()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getNeedPayAmount() == null ? other.getNeedPayAmount() == null : this.getNeedPayAmount().equals(other.getNeedPayAmount()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getOutTradePayTime() == null ? other.getOutTradePayTime() == null : this.getOutTradePayTime().equals(other.getOutTradePayTime()))
            && (this.getOutChannelNo() == null ? other.getOutChannelNo() == null : this.getOutChannelNo().equals(other.getOutChannelNo()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMerchantTradeId() == null) ? 0 : getMerchantTradeId().hashCode());
        result = prime * result + ((getRequestTradeNo() == null) ? 0 : getRequestTradeNo().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getNeedPayAmount() == null) ? 0 : getNeedPayAmount().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getOutTradePayTime() == null) ? 0 : getOutTradePayTime().hashCode());
        result = prime * result + ((getOutChannelNo() == null) ? 0 : getOutChannelNo().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", merchantTradeId=").append(merchantTradeId);
        sb.append(", requestTradeNo=").append(requestTradeNo);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", needPayAmount=").append(needPayAmount);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", outTradePayTime=").append(outTradePayTime);
        sb.append(", outChannelNo=").append(outChannelNo);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}