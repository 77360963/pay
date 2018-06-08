package com.yunpan.data.entity;

import java.util.Date;

public class MerchantEntity {
    /**
     * <pre>
     * 客户ID
     * 表字段 : t_merchant.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 
     * 表字段 : t_merchant.user_id
     * </pre>
     */
    private Long userId;

    /**
     * <pre>
     * 商户名称
     * 表字段 : t_merchant.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 商户地址
     * 表字段 : t_merchant.address
     * </pre>
     */
    private String address;

    /**
     * <pre>
     * 联系人
     * 表字段 : t_merchant.contacts
     * </pre>
     */
    private String contacts;

    /**
     * <pre>
     * 联系电话
     * 表字段 : t_merchant.mobile
     * </pre>
     */
    private String mobile;

    /**
     * <pre>
     * 商户图标
     * 表字段 : t_merchant.image
     * </pre>
     */
    private String image;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_merchant.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_merchant.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：客户ID
     * 表字段：t_merchant.id
     * </pre>
     *
     * @return t_merchant.id：客户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：客户ID
     * 表字段：t_merchant.id
     * </pre>
     *
     * @param id
     *            t_merchant.id：客户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：t_merchant.user_id
     * </pre>
     *
     * @return t_merchant.user_id：
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：t_merchant.user_id
     * </pre>
     *
     * @param userId
     *            t_merchant.user_id：
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * <pre>
     * 获取：商户名称
     * 表字段：t_merchant.name
     * </pre>
     *
     * @return t_merchant.name：商户名称
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 设置：商户名称
     * 表字段：t_merchant.name
     * </pre>
     *
     * @param name
     *            t_merchant.name：商户名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 获取：商户地址
     * 表字段：t_merchant.address
     * </pre>
     *
     * @return t_merchant.address：商户地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * <pre>
     * 设置：商户地址
     * 表字段：t_merchant.address
     * </pre>
     *
     * @param address
     *            t_merchant.address：商户地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * <pre>
     * 获取：联系人
     * 表字段：t_merchant.contacts
     * </pre>
     *
     * @return t_merchant.contacts：联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * <pre>
     * 设置：联系人
     * 表字段：t_merchant.contacts
     * </pre>
     *
     * @param contacts
     *            t_merchant.contacts：联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * <pre>
     * 获取：联系电话
     * 表字段：t_merchant.mobile
     * </pre>
     *
     * @return t_merchant.mobile：联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * <pre>
     * 设置：联系电话
     * 表字段：t_merchant.mobile
     * </pre>
     *
     * @param mobile
     *            t_merchant.mobile：联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * <pre>
     * 获取：商户图标
     * 表字段：t_merchant.image
     * </pre>
     *
     * @return t_merchant.image：商户图标
     */
    public String getImage() {
        return image;
    }

    /**
     * <pre>
     * 设置：商户图标
     * 表字段：t_merchant.image
     * </pre>
     *
     * @param image
     *            t_merchant.image：商户图标
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_merchant.created_time
     * </pre>
     *
     * @return t_merchant.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_merchant.created_time
     * </pre>
     *
     * @param createdTime
     *            t_merchant.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_merchant.updated_time
     * </pre>
     *
     * @return t_merchant.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_merchant.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_merchant.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_merchant.status
     * </pre>
     *
     * @return t_merchant.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_merchant.status
     * </pre>
     *
     * @param status
     *            t_merchant.status：正常：1；删除：0
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
        MerchantEntity other = (MerchantEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getContacts() == null ? other.getContacts() == null : this.getContacts().equals(other.getContacts()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getContacts() == null) ? 0 : getContacts().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", contacts=").append(contacts);
        sb.append(", mobile=").append(mobile);
        sb.append(", image=").append(image);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}