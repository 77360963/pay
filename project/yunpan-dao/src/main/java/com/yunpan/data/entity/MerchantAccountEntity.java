package com.yunpan.data.entity;

import java.util.Date;

public class MerchantAccountEntity {
    /**
     * <pre>
     * 商户账户ID
     * 表字段 : t_merchant_account.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 客户id
     * 表字段 : t_merchant_account.merchant_id
     * </pre>
     */
    private Long userId;

    /**
     * <pre>
     * 总金额
     * 表字段 : t_merchant_account.share_amt
     * </pre>
     */
    private Integer shareAmt;

    /**
     * <pre>
     * 可用金额
     * 表字段 : t_merchant_account.avl_amt
     * </pre>
     */
    private Integer avlAmt;

    /**
     * <pre>
     * 冻结金额
     * 表字段 : t_merchant_account.fre_amt
     * </pre>
     */
    private Integer freAmt;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant_account.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_merchant_account.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_merchant_account.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：商户账户ID
     * 表字段：t_merchant_account.id
     * </pre>
     *
     * @return t_merchant_account.id：商户账户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：商户账户ID
     * 表字段：t_merchant_account.id
     * </pre>
     *
     * @param id
     *            t_merchant_account.id：商户账户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

   

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
     * <pre>
     * 获取：总金额
     * 表字段：t_merchant_account.share_amt
     * </pre>
     *
     * @return t_merchant_account.share_amt：总金额
     */
    public Integer getShareAmt() {
        return shareAmt;
    }

    /**
     * <pre>
     * 设置：总金额
     * 表字段：t_merchant_account.share_amt
     * </pre>
     *
     * @param shareAmt
     *            t_merchant_account.share_amt：总金额
     */
    public void setShareAmt(Integer shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * <pre>
     * 获取：可用金额
     * 表字段：t_merchant_account.avl_amt
     * </pre>
     *
     * @return t_merchant_account.avl_amt：可用金额
     */
    public Integer getAvlAmt() {
        return avlAmt;
    }

    /**
     * <pre>
     * 设置：可用金额
     * 表字段：t_merchant_account.avl_amt
     * </pre>
     *
     * @param avlAmt
     *            t_merchant_account.avl_amt：可用金额
     */
    public void setAvlAmt(Integer avlAmt) {
        this.avlAmt = avlAmt;
    }

    /**
     * <pre>
     * 获取：冻结金额
     * 表字段：t_merchant_account.fre_amt
     * </pre>
     *
     * @return t_merchant_account.fre_amt：冻结金额
     */
    public Integer getFreAmt() {
        return freAmt;
    }

    /**
     * <pre>
     * 设置：冻结金额
     * 表字段：t_merchant_account.fre_amt
     * </pre>
     *
     * @param freAmt
     *            t_merchant_account.fre_amt：冻结金额
     */
    public void setFreAmt(Integer freAmt) {
        this.freAmt = freAmt;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_merchant_account.created_time
     * </pre>
     *
     * @return t_merchant_account.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_merchant_account.created_time
     * </pre>
     *
     * @param createdTime
     *            t_merchant_account.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_merchant_account.updated_time
     * </pre>
     *
     * @return t_merchant_account.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_merchant_account.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_merchant_account.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_merchant_account.status
     * </pre>
     *
     * @return t_merchant_account.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_merchant_account.status
     * </pre>
     *
     * @param status
     *            t_merchant_account.status：正常：1；删除：0
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
        MerchantAccountEntity other = (MerchantAccountEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getShareAmt() == null ? other.getShareAmt() == null : this.getShareAmt().equals(other.getShareAmt()))
            && (this.getAvlAmt() == null ? other.getAvlAmt() == null : this.getAvlAmt().equals(other.getAvlAmt()))
            && (this.getFreAmt() == null ? other.getFreAmt() == null : this.getFreAmt().equals(other.getFreAmt()))
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
        result = prime * result + ((getShareAmt() == null) ? 0 : getShareAmt().hashCode());
        result = prime * result + ((getAvlAmt() == null) ? 0 : getAvlAmt().hashCode());
        result = prime * result + ((getFreAmt() == null) ? 0 : getFreAmt().hashCode());
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
        sb.append(", merchantId=").append(userId);
        sb.append(", shareAmt=").append(shareAmt);
        sb.append(", avlAmt=").append(avlAmt);
        sb.append(", freAmt=").append(freAmt);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}