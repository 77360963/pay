package com.yunpan.data.entity;

import java.util.Date;

public class MerchantRechargeEntity {
    /**
     * <pre>
     * 客户ID
     * 表字段 : t_merchant_recharge.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 客户id
     * 表字段 : t_merchant_recharge.merchant_id
     * </pre>
     */
    private Long merchantId;

    /**
     * <pre>
     * 交易金额
     * 表字段 : t_merchant_recharge.pay_amount
     * </pre>
     */
    private Integer payAmount;

    /**
     * <pre>
     * 实际结算金额
     * 表字段 : t_merchant_recharge.need_pay_amount
     * </pre>
     */
    private Integer needPayAmount;

    /**
     * <pre>
     * 支付状态
     * 表字段 : t_merchant_recharge.pay_status
     * </pre>
     */
    private Integer payStatus;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant_recharge.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_merchant_recharge.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_merchant_recharge.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：客户ID
     * 表字段：t_merchant_recharge.id
     * </pre>
     *
     * @return t_merchant_recharge.id：客户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：客户ID
     * 表字段：t_merchant_recharge.id
     * </pre>
     *
     * @param id
     *            t_merchant_recharge.id：客户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：客户id
     * 表字段：t_merchant_recharge.merchant_id
     * </pre>
     *
     * @return t_merchant_recharge.merchant_id：客户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * <pre>
     * 设置：客户id
     * 表字段：t_merchant_recharge.merchant_id
     * </pre>
     *
     * @param merchantId
     *            t_merchant_recharge.merchant_id：客户id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * <pre>
     * 获取：交易金额
     * 表字段：t_merchant_recharge.pay_amount
     * </pre>
     *
     * @return t_merchant_recharge.pay_amount：交易金额
     */
    public Integer getPayAmount() {
        return payAmount;
    }

    /**
     * <pre>
     * 设置：交易金额
     * 表字段：t_merchant_recharge.pay_amount
     * </pre>
     *
     * @param payAmount
     *            t_merchant_recharge.pay_amount：交易金额
     */
    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * <pre>
     * 获取：实际结算金额
     * 表字段：t_merchant_recharge.need_pay_amount
     * </pre>
     *
     * @return t_merchant_recharge.need_pay_amount：实际结算金额
     */
    public Integer getNeedPayAmount() {
        return needPayAmount;
    }

    /**
     * <pre>
     * 设置：实际结算金额
     * 表字段：t_merchant_recharge.need_pay_amount
     * </pre>
     *
     * @param needPayAmount
     *            t_merchant_recharge.need_pay_amount：实际结算金额
     */
    public void setNeedPayAmount(Integer needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    /**
     * <pre>
     * 获取：支付状态
     * 表字段：t_merchant_recharge.pay_status
     * </pre>
     *
     * @return t_merchant_recharge.pay_status：支付状态
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * <pre>
     * 设置：支付状态
     * 表字段：t_merchant_recharge.pay_status
     * </pre>
     *
     * @param payStatus
     *            t_merchant_recharge.pay_status：支付状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_merchant_recharge.created_time
     * </pre>
     *
     * @return t_merchant_recharge.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_merchant_recharge.created_time
     * </pre>
     *
     * @param createdTime
     *            t_merchant_recharge.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_merchant_recharge.updated_time
     * </pre>
     *
     * @return t_merchant_recharge.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_merchant_recharge.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_merchant_recharge.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_merchant_recharge.status
     * </pre>
     *
     * @return t_merchant_recharge.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_merchant_recharge.status
     * </pre>
     *
     * @param status
     *            t_merchant_recharge.status：正常：1；删除：0
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
        MerchantRechargeEntity other = (MerchantRechargeEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getNeedPayAmount() == null ? other.getNeedPayAmount() == null : this.getNeedPayAmount().equals(other.getNeedPayAmount()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
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
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getNeedPayAmount() == null) ? 0 : getNeedPayAmount().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
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
        sb.append(", merchantId=").append(merchantId);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", needPayAmount=").append(needPayAmount);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}