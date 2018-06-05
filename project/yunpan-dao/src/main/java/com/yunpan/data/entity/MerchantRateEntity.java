package com.yunpan.data.entity;

import java.util.Date;

public class MerchantRateEntity {
    /**
     * <pre>
     * 商户费率ID
     * 表字段 : t_merchant_rate.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 商户id
     * 表字段 : t_merchant_rate.merchant_id
     * </pre>
     */
    private Long merchantId;

    /**
     * <pre>
     * 交易费率
     * 表字段 : t_merchant_rate.rate
     * </pre>
     */
    private Long rate;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant_rate.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_merchant_rate.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_merchant_rate.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：商户费率ID
     * 表字段：t_merchant_rate.id
     * </pre>
     *
     * @return t_merchant_rate.id：商户费率ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：商户费率ID
     * 表字段：t_merchant_rate.id
     * </pre>
     *
     * @param id
     *            t_merchant_rate.id：商户费率ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：商户id
     * 表字段：t_merchant_rate.merchant_id
     * </pre>
     *
     * @return t_merchant_rate.merchant_id：商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * <pre>
     * 设置：商户id
     * 表字段：t_merchant_rate.merchant_id
     * </pre>
     *
     * @param merchantId
     *            t_merchant_rate.merchant_id：商户id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * <pre>
     * 获取：交易费率
     * 表字段：t_merchant_rate.rate
     * </pre>
     *
     * @return t_merchant_rate.rate：交易费率
     */
    public Long getRate() {
        return rate;
    }

    /**
     * <pre>
     * 设置：交易费率
     * 表字段：t_merchant_rate.rate
     * </pre>
     *
     * @param rate
     *            t_merchant_rate.rate：交易费率
     */
    public void setRate(Long rate) {
        this.rate = rate;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_merchant_rate.created_time
     * </pre>
     *
     * @return t_merchant_rate.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_merchant_rate.created_time
     * </pre>
     *
     * @param createdTime
     *            t_merchant_rate.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_merchant_rate.updated_time
     * </pre>
     *
     * @return t_merchant_rate.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_merchant_rate.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_merchant_rate.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_merchant_rate.status
     * </pre>
     *
     * @return t_merchant_rate.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_merchant_rate.status
     * </pre>
     *
     * @param status
     *            t_merchant_rate.status：正常：1；删除：0
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
        MerchantRateEntity other = (MerchantRateEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
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
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
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
        sb.append(", rate=").append(rate);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}