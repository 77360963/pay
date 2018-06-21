package com.yunpan.data.entity;

import java.util.Date;

public class UserSigninEntity {
    /**
     * <pre>
     * 签到ID
     * 表字段 : t_user_signin.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 用户id
     * 表字段 : t_user_signin.user_id
     * </pre>
     */
    private Long userId;

    /**
     * <pre>
     * 签到日期
     * 表字段 : t_user_signin.signin_date
     * </pre>
     */
    private String signinDate;

    /**
     * <pre>
     * 签到金额
     * 表字段 : t_user_signin.signin_acount
     * </pre>
     */
    private Integer signinAcount;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_user_signin.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_user_signin.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_user_signin.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：签到ID
     * 表字段：t_user_signin.id
     * </pre>
     *
     * @return t_user_signin.id：签到ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：签到ID
     * 表字段：t_user_signin.id
     * </pre>
     *
     * @param id
     *            t_user_signin.id：签到ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：用户id
     * 表字段：t_user_signin.user_id
     * </pre>
     *
     * @return t_user_signin.user_id：用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * <pre>
     * 设置：用户id
     * 表字段：t_user_signin.user_id
     * </pre>
     *
     * @param userId
     *            t_user_signin.user_id：用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * <pre>
     * 获取：签到日期
     * 表字段：t_user_signin.signin_date
     * </pre>
     *
     * @return t_user_signin.signin_date：签到日期
     */
    public String getSigninDate() {
        return signinDate;
    }

    /**
     * <pre>
     * 设置：签到日期
     * 表字段：t_user_signin.signin_date
     * </pre>
     *
     * @param signinDate
     *            t_user_signin.signin_date：签到日期
     */
    public void setSigninDate(String signinDate) {
        this.signinDate = signinDate == null ? null : signinDate.trim();
    }

    /**
     * <pre>
     * 获取：签到金额
     * 表字段：t_user_signin.signin_acount
     * </pre>
     *
     * @return t_user_signin.signin_acount：签到金额
     */
    public Integer getSigninAcount() {
        return signinAcount;
    }

    /**
     * <pre>
     * 设置：签到金额
     * 表字段：t_user_signin.signin_acount
     * </pre>
     *
     * @param signinAcount
     *            t_user_signin.signin_acount：签到金额
     */
    public void setSigninAcount(Integer signinAcount) {
        this.signinAcount = signinAcount;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_user_signin.created_time
     * </pre>
     *
     * @return t_user_signin.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_user_signin.created_time
     * </pre>
     *
     * @param createdTime
     *            t_user_signin.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_user_signin.updated_time
     * </pre>
     *
     * @return t_user_signin.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_user_signin.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_user_signin.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_user_signin.status
     * </pre>
     *
     * @return t_user_signin.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_user_signin.status
     * </pre>
     *
     * @param status
     *            t_user_signin.status：正常：1；删除：0
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
        UserSigninEntity other = (UserSigninEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSigninDate() == null ? other.getSigninDate() == null : this.getSigninDate().equals(other.getSigninDate()))
            && (this.getSigninAcount() == null ? other.getSigninAcount() == null : this.getSigninAcount().equals(other.getSigninAcount()))
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
        result = prime * result + ((getSigninDate() == null) ? 0 : getSigninDate().hashCode());
        result = prime * result + ((getSigninAcount() == null) ? 0 : getSigninAcount().hashCode());
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
        sb.append(", signinDate=").append(signinDate);
        sb.append(", signinAcount=").append(signinAcount);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}