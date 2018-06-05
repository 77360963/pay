package com.yunpan.data.entity;

import java.util.Date;

public class OrderDetailEntity {
    /**
     * <pre>
     * 订单详情Id
     * 表字段 : t_order_detail.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 订单Id
     * 表字段 : t_order_detail.order_id
     * </pre>
     */
    private Long orderId;

    /**
     * <pre>
     * 商户id
     * 表字段 : t_order_detail.merchant_id
     * </pre>
     */
    private Long merchantId;

    /**
     * <pre>
     * 客户id
     * 表字段 : t_order_detail.customer_id
     * </pre>
     */
    private Long customerId;

    /**
     * <pre>
     * 商品id
     * 表字段 : t_order_detail.product_id
     * </pre>
     */
    private Long productId;

    /**
     * <pre>
     * 订单金额
     * 表字段 : t_order_detail.amount
     * </pre>
     */
    private Integer amount;

    /**
     * <pre>
     * 订单数量
     * 表字段 : t_order_detail.buy_count
     * </pre>
     */
    private Integer buyCount;

    /**
     * <pre>
     * 实际支付金额
     * 表字段 : t_order_detail.need_pay_amount
     * </pre>
     */
    private Integer needPayAmount;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_order_detail.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_order_detail.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_order_detail.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：订单详情Id
     * 表字段：t_order_detail.id
     * </pre>
     *
     * @return t_order_detail.id：订单详情Id
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：订单详情Id
     * 表字段：t_order_detail.id
     * </pre>
     *
     * @param id
     *            t_order_detail.id：订单详情Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：订单Id
     * 表字段：t_order_detail.order_id
     * </pre>
     *
     * @return t_order_detail.order_id：订单Id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <pre>
     * 设置：订单Id
     * 表字段：t_order_detail.order_id
     * </pre>
     *
     * @param orderId
     *            t_order_detail.order_id：订单Id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <pre>
     * 获取：商户id
     * 表字段：t_order_detail.merchant_id
     * </pre>
     *
     * @return t_order_detail.merchant_id：商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * <pre>
     * 设置：商户id
     * 表字段：t_order_detail.merchant_id
     * </pre>
     *
     * @param merchantId
     *            t_order_detail.merchant_id：商户id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * <pre>
     * 获取：客户id
     * 表字段：t_order_detail.customer_id
     * </pre>
     *
     * @return t_order_detail.customer_id：客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <pre>
     * 设置：客户id
     * 表字段：t_order_detail.customer_id
     * </pre>
     *
     * @param customerId
     *            t_order_detail.customer_id：客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * <pre>
     * 获取：商品id
     * 表字段：t_order_detail.product_id
     * </pre>
     *
     * @return t_order_detail.product_id：商品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * <pre>
     * 设置：商品id
     * 表字段：t_order_detail.product_id
     * </pre>
     *
     * @param productId
     *            t_order_detail.product_id：商品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * <pre>
     * 获取：订单金额
     * 表字段：t_order_detail.amount
     * </pre>
     *
     * @return t_order_detail.amount：订单金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * <pre>
     * 设置：订单金额
     * 表字段：t_order_detail.amount
     * </pre>
     *
     * @param amount
     *            t_order_detail.amount：订单金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * <pre>
     * 获取：订单数量
     * 表字段：t_order_detail.buy_count
     * </pre>
     *
     * @return t_order_detail.buy_count：订单数量
     */
    public Integer getBuyCount() {
        return buyCount;
    }

    /**
     * <pre>
     * 设置：订单数量
     * 表字段：t_order_detail.buy_count
     * </pre>
     *
     * @param buyCount
     *            t_order_detail.buy_count：订单数量
     */
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    /**
     * <pre>
     * 获取：实际支付金额
     * 表字段：t_order_detail.need_pay_amount
     * </pre>
     *
     * @return t_order_detail.need_pay_amount：实际支付金额
     */
    public Integer getNeedPayAmount() {
        return needPayAmount;
    }

    /**
     * <pre>
     * 设置：实际支付金额
     * 表字段：t_order_detail.need_pay_amount
     * </pre>
     *
     * @param needPayAmount
     *            t_order_detail.need_pay_amount：实际支付金额
     */
    public void setNeedPayAmount(Integer needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_order_detail.created_time
     * </pre>
     *
     * @return t_order_detail.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_order_detail.created_time
     * </pre>
     *
     * @param createdTime
     *            t_order_detail.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_order_detail.updated_time
     * </pre>
     *
     * @return t_order_detail.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_order_detail.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_order_detail.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_order_detail.status
     * </pre>
     *
     * @return t_order_detail.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_order_detail.status
     * </pre>
     *
     * @param status
     *            t_order_detail.status：正常：1；删除：0
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
        OrderDetailEntity other = (OrderDetailEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBuyCount() == null ? other.getBuyCount() == null : this.getBuyCount().equals(other.getBuyCount()))
            && (this.getNeedPayAmount() == null ? other.getNeedPayAmount() == null : this.getNeedPayAmount().equals(other.getNeedPayAmount()))
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
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBuyCount() == null) ? 0 : getBuyCount().hashCode());
        result = prime * result + ((getNeedPayAmount() == null) ? 0 : getNeedPayAmount().hashCode());
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
        sb.append(", orderId=").append(orderId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", customerId=").append(customerId);
        sb.append(", productId=").append(productId);
        sb.append(", amount=").append(amount);
        sb.append(", buyCount=").append(buyCount);
        sb.append(", needPayAmount=").append(needPayAmount);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}