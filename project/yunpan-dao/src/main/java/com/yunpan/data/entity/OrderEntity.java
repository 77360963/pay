package com.yunpan.data.entity;

import java.util.Date;

public class OrderEntity {
    /**
     * <pre>
     * 订单Id
     * 表字段 : t_order.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 客户id
     * 表字段 : t_order.customer_id
     * </pre>
     */
    private Long customerId;

    /**
     * <pre>
     * 订单总金额
     * 表字段 : t_order.total_amount
     * </pre>
     */
    private Integer totalAmount;

    /**
     * <pre>
     * 实际支付金额
     * 表字段 : t_order.need_pay_amount
     * </pre>
     */
    private Integer needPayAmount;

    /**
     * <pre>
     * 订单状态
     * 表字段 : t_order.order_status
     * </pre>
     */
    private Integer orderStatus;

    /**
     * <pre>
     * 支付渠道
     * 表字段 : t_order.channel
     * </pre>
     */
    private String channel;

    /**
     * <pre>
     * 外部订单号
     * 表字段 : t_order.out_trade_no
     * </pre>
     */
    private String outTradeNo;

    /**
     * <pre>
     * 支付完成时间
     * 表字段 : t_order.pay_time
     * </pre>
     */
    private Date payTime;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_order.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_order.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_order.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：订单Id
     * 表字段：t_order.id
     * </pre>
     *
     * @return t_order.id：订单Id
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：订单Id
     * 表字段：t_order.id
     * </pre>
     *
     * @param id
     *            t_order.id：订单Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：客户id
     * 表字段：t_order.customer_id
     * </pre>
     *
     * @return t_order.customer_id：客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <pre>
     * 设置：客户id
     * 表字段：t_order.customer_id
     * </pre>
     *
     * @param customerId
     *            t_order.customer_id：客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * <pre>
     * 获取：订单总金额
     * 表字段：t_order.total_amount
     * </pre>
     *
     * @return t_order.total_amount：订单总金额
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * <pre>
     * 设置：订单总金额
     * 表字段：t_order.total_amount
     * </pre>
     *
     * @param totalAmount
     *            t_order.total_amount：订单总金额
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * <pre>
     * 获取：实际支付金额
     * 表字段：t_order.need_pay_amount
     * </pre>
     *
     * @return t_order.need_pay_amount：实际支付金额
     */
    public Integer getNeedPayAmount() {
        return needPayAmount;
    }

    /**
     * <pre>
     * 设置：实际支付金额
     * 表字段：t_order.need_pay_amount
     * </pre>
     *
     * @param needPayAmount
     *            t_order.need_pay_amount：实际支付金额
     */
    public void setNeedPayAmount(Integer needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    /**
     * <pre>
     * 获取：订单状态
     * 表字段：t_order.order_status
     * </pre>
     *
     * @return t_order.order_status：订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * <pre>
     * 设置：订单状态
     * 表字段：t_order.order_status
     * </pre>
     *
     * @param orderStatus
     *            t_order.order_status：订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * <pre>
     * 获取：支付渠道
     * 表字段：t_order.channel
     * </pre>
     *
     * @return t_order.channel：支付渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * <pre>
     * 设置：支付渠道
     * 表字段：t_order.channel
     * </pre>
     *
     * @param channel
     *            t_order.channel：支付渠道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * <pre>
     * 获取：外部订单号
     * 表字段：t_order.out_trade_no
     * </pre>
     *
     * @return t_order.out_trade_no：外部订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * <pre>
     * 设置：外部订单号
     * 表字段：t_order.out_trade_no
     * </pre>
     *
     * @param outTradeNo
     *            t_order.out_trade_no：外部订单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * <pre>
     * 获取：支付完成时间
     * 表字段：t_order.pay_time
     * </pre>
     *
     * @return t_order.pay_time：支付完成时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * <pre>
     * 设置：支付完成时间
     * 表字段：t_order.pay_time
     * </pre>
     *
     * @param payTime
     *            t_order.pay_time：支付完成时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_order.created_time
     * </pre>
     *
     * @return t_order.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_order.created_time
     * </pre>
     *
     * @param createdTime
     *            t_order.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_order.updated_time
     * </pre>
     *
     * @return t_order.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_order.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_order.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_order.status
     * </pre>
     *
     * @return t_order.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_order.status
     * </pre>
     *
     * @param status
     *            t_order.status：正常：1；删除：0
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
        OrderEntity other = (OrderEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getNeedPayAmount() == null ? other.getNeedPayAmount() == null : this.getNeedPayAmount().equals(other.getNeedPayAmount()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
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
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getNeedPayAmount() == null) ? 0 : getNeedPayAmount().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
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
        sb.append(", customerId=").append(customerId);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", needPayAmount=").append(needPayAmount);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", channel=").append(channel);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", payTime=").append(payTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}