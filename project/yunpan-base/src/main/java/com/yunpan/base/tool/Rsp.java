package com.yunpan.base.tool;

public class Rsp {

	private String errCode;
	private String errMsg;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "Rsp [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}
	
	
}
