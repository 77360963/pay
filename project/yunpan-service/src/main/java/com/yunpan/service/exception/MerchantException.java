package com.yunpan.service.exception;

public class MerchantException extends BaseException {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MerchantException(String errorCode, String errorMsg, Throwable caused) {
		super(errorCode, errorMsg, caused);
	}

	public MerchantException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public MerchantException(String errorCode, Throwable caused) {
		super(errorCode, caused);
	}
	
	
	
	

}
