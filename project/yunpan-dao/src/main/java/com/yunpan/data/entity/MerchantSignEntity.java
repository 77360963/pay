package com.yunpan.data.entity;

import java.util.Date;

public class MerchantSignEntity {
    /**
     * <pre>
     * 商户签约ID
     * 表字段 : t_merchant_sign.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * userId
     * 表字段 : t_merchant_sign.user_id
     * </pre>
     */
    private Long userId;

    /**
     * <pre>
     * 私钥
     * 表字段 : t_merchant_sign.private_key
     * </pre>
     */
    private String privateKey;

    /**
     * <pre>
     * 公钥
     * 表字段 : t_merchant_sign.public_key
     * </pre>
     */
    private String publicKey;

    /**
     * <pre>
     * 商户通知地址
     * 表字段 : t_merchant_sign.notify_url
     * </pre>
     */
    private String notifyUrl;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant_sign.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_merchant_sign.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_merchant_sign.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：商户签约ID
     * 表字段：t_merchant_sign.id
     * </pre>
     *
     * @return t_merchant_sign.id：商户签约ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：商户签约ID
     * 表字段：t_merchant_sign.id
     * </pre>
     *
     * @param id
     *            t_merchant_sign.id：商户签约ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：userId
     * 表字段：t_merchant_sign.user_id
     * </pre>
     *
     * @return t_merchant_sign.user_id：userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * <pre>
     * 设置：userId
     * 表字段：t_merchant_sign.user_id
     * </pre>
     *
     * @param userId
     *            t_merchant_sign.user_id：userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * <pre>
     * 获取：私钥
     * 表字段：t_merchant_sign.private_key
     * </pre>
     *
     * @return t_merchant_sign.private_key：私钥
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * <pre>
     * 设置：私钥
     * 表字段：t_merchant_sign.private_key
     * </pre>
     *
     * @param privateKey
     *            t_merchant_sign.private_key：私钥
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    /**
     * <pre>
     * 获取：公钥
     * 表字段：t_merchant_sign.public_key
     * </pre>
     *
     * @return t_merchant_sign.public_key：公钥
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * <pre>
     * 设置：公钥
     * 表字段：t_merchant_sign.public_key
     * </pre>
     *
     * @param publicKey
     *            t_merchant_sign.public_key：公钥
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    /**
     * <pre>
     * 获取：商户通知地址
     * 表字段：t_merchant_sign.notify_url
     * </pre>
     *
     * @return t_merchant_sign.notify_url：商户通知地址
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * <pre>
     * 设置：商户通知地址
     * 表字段：t_merchant_sign.notify_url
     * </pre>
     *
     * @param notifyUrl
     *            t_merchant_sign.notify_url：商户通知地址
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_merchant_sign.created_time
     * </pre>
     *
     * @return t_merchant_sign.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_merchant_sign.created_time
     * </pre>
     *
     * @param createdTime
     *            t_merchant_sign.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_merchant_sign.updated_time
     * </pre>
     *
     * @return t_merchant_sign.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_merchant_sign.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_merchant_sign.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_merchant_sign.status
     * </pre>
     *
     * @return t_merchant_sign.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_merchant_sign.status
     * </pre>
     *
     * @param status
     *            t_merchant_sign.status：正常：1；删除：0
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
        MerchantSignEntity other = (MerchantSignEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPrivateKey() == null ? other.getPrivateKey() == null : this.getPrivateKey().equals(other.getPrivateKey()))
            && (this.getPublicKey() == null ? other.getPublicKey() == null : this.getPublicKey().equals(other.getPublicKey()))
            && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
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
        result = prime * result + ((getPrivateKey() == null) ? 0 : getPrivateKey().hashCode());
        result = prime * result + ((getPublicKey() == null) ? 0 : getPublicKey().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
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
        sb.append(", privateKey=").append(privateKey);
        sb.append(", publicKey=").append(publicKey);
        sb.append(", notifyUrl=").append(notifyUrl);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}