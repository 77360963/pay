package com.yunpan.service.bean;

import java.util.Date;

public class MerchantAccountEntityBean {
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
    private String shareAmt;

    /**
     * <pre>
     * 可用金额
     * 表字段 : t_merchant_account.avl_amt
     * </pre>
     */
    private String avlAmt;

    /**
     * <pre>
     * 冻结金额
     * 表字段 : t_merchant_account.fre_amt
     * </pre>
     */
    private String freAmt;

    /**
     * <pre>
     * 创建时间
     * 表字段 : t_merchant_account.created_time
     * </pre>
     */
    private String createdTime;

    /**
     * <pre>
     * 更新时间
     * 表字段 : t_merchant_account.updated_time
     * </pre>
     */
    private String updatedTime;

    /**
     * <pre>
     * 正常：1；删除：0
     * 表字段 : t_merchant_account.status
     * </pre>
     */
    private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(String shareAmt) {
		this.shareAmt = shareAmt;
	}

	public String getAvlAmt() {
		return avlAmt;
	}

	public void setAvlAmt(String avlAmt) {
		this.avlAmt = avlAmt;
	}

	public String getFreAmt() {
		return freAmt;
	}

	public void setFreAmt(String freAmt) {
		this.freAmt = freAmt;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

   
}