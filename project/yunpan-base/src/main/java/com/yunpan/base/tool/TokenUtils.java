package com.yunpan.base.tool;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.yunpan.base.web.util.Result;

public class TokenUtils {

	/*
	 * 单例设计:类的构造函数私有对外提供一个返回实例对象的公共方法
	 */
	private TokenUtils() {
	}

	private static final TokenUtils instance = new TokenUtils();

	/**
	 * 返回类的对象
	 * @return
	 */
	public static TokenUtils getInstance() {
		return instance;
	}

	public String makeToken() {
		return (System.currentTimeMillis()) + "_" + UUID.randomUUID();
	}

	/**
	 * 判断客户端提交token和服务器端token是否一致
	 * @param request
	 * @return
	 */
	public static Map<String, Object> checkDataSubmit(HttpServletRequest request) {
		String client_token = request.getParameter("token");
		if (client_token == null) {
			return Result.failed("请求正在处理中~~~,请勿重复提交！");
		}
		String server_token = (String) request.getSession().getAttribute("token");
		if (server_token == null) {
			return Result.failed("请求正在处理中~~~,请勿重复提交！");
		}
		if (!client_token.equals(server_token)) {
			return Result.failed("请求正在处理中~~~,请勿重复提交！");
		}
		//校验完成后删除session中的token
		request.getSession().removeAttribute("token");
		return Result.success();
	}

	public static void main(String[] args) {
		String token1 = UUID.randomUUID().toString();
		String token = (System.currentTimeMillis()) + "_" + UUID.randomUUID();
		System.out.println(token);
		System.out.println(token1);
	}

}
