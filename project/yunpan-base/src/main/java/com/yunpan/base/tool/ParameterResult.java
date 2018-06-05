package com.yunpan.base.tool;

import java.util.List;

/**
 * 评论列表输出
 * @author xujinyi
 *
 */
public class ParameterResult{

	public static String SUCCESS = "0";
	public static String TRADE_SUCCESS = "交易成功";
	
	private String errCode = SUCCESS;
	
	private String errMsg = TRADE_SUCCESS;

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
	
	// 评论列表
	private List parameterList;

	public List getParameterList() {
		return parameterList;
	}

	public void setParameterList(List parameterList) {
		this.parameterList = parameterList;
	}

	@Override
	public String toString() {
		return "ParameterResult [errCode=" + errCode + ", errMsg=" + errMsg
				+ ", parameterList=" + parameterList + "]";
	}
	
	
	
	
}
