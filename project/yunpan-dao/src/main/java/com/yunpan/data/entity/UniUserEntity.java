package com.yunpan.data.entity;

import java.util.Date;

public class UniUserEntity {
    /**
     * <pre>
     * id
     * 表字段 : t_uniuser.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 登录名
     * 表字段 : t_uniuser.login_name
     * </pre>
     */
    private String loginName;

    /**
     * <pre>
     * 密码，加密
     * 表字段 : t_uniuser.password
     * </pre>
     */
    private String password;

    /**
     * <pre>
     * 用户状态1=启用，0=禁用
     * 表字段 : t_uniuser.user_state
     * </pre>
     */
    private Integer userState;

    /**
     * <pre>
     * 角色 1=商户，2=个人
     * 表字段 : t_uniuser.role
     * </pre>
     */
    private Integer role;

    /**
     * <pre>
     * 所属平台
     * 表字段 : t_uniuser.platform
     * </pre>
     */
    private Integer platform;

    /**
     * <pre>
     * 上次登录时间
     * 表字段 : t_uniuser.last_login_time
     * </pre>
     */
    private Date lastLoginTime;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_uniuser.created_time
     * </pre>
     */
    private Date createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_uniuser.updated_time
     * </pre>
     */
    private Date updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_uniuser.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 获取：id
     * 表字段：t_uniuser.id
     * </pre>
     *
     * @return t_uniuser.id：id
     */
    public Long getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：id
     * 表字段：t_uniuser.id
     * </pre>
     *
     * @param id
     *            t_uniuser.id：id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：登录名
     * 表字段：t_uniuser.login_name
     * </pre>
     *
     * @return t_uniuser.login_name：登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * <pre>
     * 设置：登录名
     * 表字段：t_uniuser.login_name
     * </pre>
     *
     * @param loginName
     *            t_uniuser.login_name：登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * <pre>
     * 获取：密码，加密
     * 表字段：t_uniuser.password
     * </pre>
     *
     * @return t_uniuser.password：密码，加密
     */
    public String getPassword() {
        return password;
    }

    /**
     * <pre>
     * 设置：密码，加密
     * 表字段：t_uniuser.password
     * </pre>
     *
     * @param password
     *            t_uniuser.password：密码，加密
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * <pre>
     * 获取：用户状态1=启用，0=禁用
     * 表字段：t_uniuser.user_state
     * </pre>
     *
     * @return t_uniuser.user_state：用户状态1=启用，0=禁用
     */
    public Integer getUserState() {
        return userState;
    }

    /**
     * <pre>
     * 设置：用户状态1=启用，0=禁用
     * 表字段：t_uniuser.user_state
     * </pre>
     *
     * @param userState
     *            t_uniuser.user_state：用户状态1=启用，0=禁用
     */
    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    /**
     * <pre>
     * 获取：角色 1=商户，2=个人
     * 表字段：t_uniuser.role
     * </pre>
     *
     * @return t_uniuser.role：角色 1=商户，2=个人
     */
    public Integer getRole() {
        return role;
    }

    /**
     * <pre>
     * 设置：角色 1=商户，2=个人
     * 表字段：t_uniuser.role
     * </pre>
     *
     * @param role
     *            t_uniuser.role：角色 1=商户，2=个人
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * <pre>
     * 获取：所属平台
     * 表字段：t_uniuser.platform
     * </pre>
     *
     * @return t_uniuser.platform：所属平台
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * <pre>
     * 设置：所属平台
     * 表字段：t_uniuser.platform
     * </pre>
     *
     * @param platform
     *            t_uniuser.platform：所属平台
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * <pre>
     * 获取：上次登录时间
     * 表字段：t_uniuser.last_login_time
     * </pre>
     *
     * @return t_uniuser.last_login_time：上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * <pre>
     * 设置：上次登录时间
     * 表字段：t_uniuser.last_login_time
     * </pre>
     *
     * @param lastLoginTime
     *            t_uniuser.last_login_time：上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：t_uniuser.created_time
     * </pre>
     *
     * @return t_uniuser.created_time：创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：t_uniuser.created_time
     * </pre>
     *
     * @param createdTime
     *            t_uniuser.created_time：创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * <pre>
     * 获取：更新时间
     * 表字段：t_uniuser.updated_time
     * </pre>
     *
     * @return t_uniuser.updated_time：更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * <pre>
     * 设置：更新时间
     * 表字段：t_uniuser.updated_time
     * </pre>
     *
     * @param updatedTime
     *            t_uniuser.updated_time：更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * <pre>
     * 获取：正常：1；删除：0
     * 表字段：t_uniuser.status
     * </pre>
     *
     * @return t_uniuser.status：正常：1；删除：0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：正常：1；删除：0
     * 表字段：t_uniuser.status
     * </pre>
     *
     * @param status
     *            t_uniuser.status：正常：1；删除：0
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
        UniUserEntity other = (UniUserEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getUserState() == null ? other.getUserState() == null : this.getUserState().equals(other.getUserState()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
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
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getUserState() == null) ? 0 : getUserState().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
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
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", userState=").append(userState);
        sb.append(", role=").append(role);
        sb.append(", platform=").append(platform);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}