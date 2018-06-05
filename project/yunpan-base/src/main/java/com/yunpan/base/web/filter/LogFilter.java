package com.yunpan.base.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("请求路径===========================================>"
				+ ((HttpServletRequest) request).getServletPath()+"请求地址:"+request.getRemoteAddr()+"|"+request.getRemoteHost());
		long t1 = System.currentTimeMillis();
		chain.doFilter(request, response);
		long t2 = System.currentTimeMillis();
		logger.info("请求路径end("+((HttpServletRequest) request).getServletPath()+")end===========================================>耗时["
				+ (t2 - t1) + "]");
	}

	@Override
	public void destroy() {
	}

}
