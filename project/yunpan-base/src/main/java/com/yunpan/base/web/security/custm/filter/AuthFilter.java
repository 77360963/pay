package com.yunpan.base.web.security.custm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.yunpan.base.web.security.custm.Authorization;
import com.yunpan.base.web.security.custm.holder.AuthHolder;
import com.yunpan.base.web.security.custm.mgr.AuthMgr;
import com.yunpan.base.web.util.Result;

public class AuthFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	private Object monitorObj = new Object();
	private volatile AuthMgr authMgr;
	// private ServletContext sc;
	// private static final String TARGET_BEAN_NAME = "authMgr";

	private static final String LOGIN_URL = "/login";
	private static final String LOGIN_PAGE = "/login.html";
	private static final String LOGINOUT_URL = "/loginout";
	private static final String AUTH_DENY_PAGE = "/authDeny.html";

	public static final String AUTHORIZATION = "_authorization";
	private static final String LOGIN_SUCCESS_PAGE = "loginsuccesspage";

	@Override
	public void initFilterBean() throws ServletException {
		initAuthMgr();
		if (authMgr == null) {
			logger.warn("==========>authFilter未获取到authMgr");
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (authMgr == null) {
			initAuthMgr();
		}
		String servletPath = request.getServletPath();

		Authorization authorization = Authorization.getAuthorization(request,authMgr);
		
		AuthHolder.setAuthUser(authorization);

		// 不拦截
		if (authMgr.isIgnorPath(servletPath)) {
			logger.info("==========>过滤[" + servletPath + "]");
			chain.doFilter(request, response);
			return;
		}

		try {

			logger.info("==========>请求路径:[" + servletPath + "]");
			if (servletPath.equals(StringUtils.defaultIfBlank(
					authMgr.getLoginOutUrl(), LOGINOUT_URL))) {
				// request.getSession().invalidate();
				authorization.sessionInvalidate();
				logindAction(request, response);
				return;
			}

			// session是否有校验对象
			if (authorization.hasAuth()) {
				// 如果是登录url
				if (isLoginUrl(servletPath)) {
					authLoginUser(authorization, request, response);
				} else {
					// 登录成功需校验
					try {
						// authMgr.webSecurityAuth(authorization, servletPath);
						authorization.hasPermission(servletPath);
						chain.doFilter(request, response);
					} catch (Exception e) {
						logger.error(
								"==========>" + authorization.getLoginName()
										+ "无权限访问[" + servletPath + "]", e);
						denyAction(request, response);
					}
				}
			} else {
				// 认证
				if (isLoginUrl(servletPath)) {
					authLoginUser(authorization, request, response);

					return;
				} else {
					failedAction(request, response);
					return;
				}
			}
		} finally {
			AuthHolder.clearContext();
		}
	}

	private void authLoginUser(Authorization authorization,
			HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			authorization.login(username, password);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			request.setAttribute("_message_auth", e.getMessage());
			failedAction(request, response);
			return;
		}
		logger.info("==========>[" + authorization.getLoginName() + "]认证通过["
				+ authorization.getAuthRoles() + "]["
				+ authorization.getAuthRoles() + "]");
		successAction(request, response);
	}

	@Override
	public void destroy() {
	}

	private boolean isAjax(HttpServletRequest request) {
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			return true;
		} else {
			return false;
		}
	}

	private void denyAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			// 分ajax请求,与页面跳转
			if (isAjax(request)) {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(
						JSON.toJSONString(Result.authDeny()));
			} else {
				response.sendRedirect(StringUtils.defaultIfBlank(
						authMgr.getAuthDenyUrl(), AUTH_DENY_PAGE));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	
	private void logindAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (isAjax(request)) {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(
						JSON.toJSONString(Result.successJson()));
			} else {
				response.sendRedirect(StringUtils.defaultIfBlank(
						authMgr.getLoginPage(), LOGIN_PAGE));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private void failedAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String merrorMsg = (String)request.getAttribute("_message_auth");
			if (isAjax(request)) {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(
						JSON.toJSONString(StringUtils.isEmpty(merrorMsg)? Result.authFailed(): Result.authFailed(merrorMsg)));
			} else {
				response.sendRedirect(StringUtils.defaultIfBlank(
						authMgr.getLoginPage(), LOGIN_PAGE));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private void successAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (isAjax(request)) {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(Result.successJson());
			} else {
				response.sendRedirect(StringUtils.defaultIfBlank(
						authMgr.getLoginPage(), LOGIN_SUCCESS_PAGE));
			}

		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private boolean isLoginUrl(String servletPath) {
		return servletPath.equals(StringUtils.defaultIfBlank(
				authMgr.getLoginUrl(), LOGIN_URL));
	}

	private void initAuthMgr() {
		synchronized (this.monitorObj) {
			if (this.authMgr == null) {
				WebApplicationContext wac = WebApplicationContextUtils
						.findWebApplicationContext(super.getServletContext());
				if (wac != null) {
					this.authMgr = (AuthMgr) wac.getBean(AuthMgr.class);
					authMgr.init();
				}
			} else {

			}
		}
	}

}
