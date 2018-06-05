package com.yunpan.base.tool;

import java.io.Serializable;

/**
 * 请求字典数据
 * 
 * @author Administrator
 * 
 */
public class ParameterReq implements Serializable {

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
