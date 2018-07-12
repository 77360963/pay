package com.yunpan.service.service.bean;

public class MerchantRegisterBean {
	
	 private String loginName;
	 
	 private String password;
	 
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
	     * 
	     * 表字段 : t_merchant.payment_method
	     * </pre>
	     */
	    private String paymentMethod;
	    
	    /**
	     * 推荐人
	     */
	    private Long parentUserId;

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getContacts() {
			return contacts;
		}

		public void setContacts(String contacts) {
			this.contacts = contacts;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

        public Long getParentUserId() {
            return parentUserId;
        }

        public void setParentUserId(Long parentUserId) {
            this.parentUserId = parentUserId;
        }

       
        
	    

}
