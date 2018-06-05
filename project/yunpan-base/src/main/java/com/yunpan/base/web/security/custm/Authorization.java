package com.yunpan.base.web.security.custm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.yunpan.base.web.security.custm.exception.AuthException;
import com.yunpan.base.web.security.custm.filter.AuthFilter;
import com.yunpan.base.web.security.custm.mgr.AuthMgr;

public class Authorization {
	private String loginName;
	private String password;
	private Set<String> authRoles;
	private Set<String> authRes;
	private Object user;
	private Map<String, Object> param = new HashMap<String, Object>();
	private transient HttpServletRequest request;
	// private HttpSession session;
	private AuthMgr authMgr;

	public void hasPermission(String servletPath) throws AuthException {
		authMgr.webSecurityAuth(this, servletPath);
	}

	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	public String getRemoteHost() {
		return request.getRemoteHost();
	}
	
	public String getRequestUrl() {
		return request.getServletPath();
	}
	
	private boolean hasAuth = false;

	public boolean hasAuth() {
		return null != request.getSession().getAttribute(
				AuthFilter.AUTHORIZATION)
				&& hasAuth;
	}

	public Authorization() {
		super();
	}

	public Authorization(AuthMgr authMgr) {
		super();
		this.authMgr = authMgr;
	}

	public void holdRequest(HttpServletRequest request) {
		this.request = request;
	}

	//获取认证信息
	public static Authorization getAuthorization(HttpServletRequest request,String loginName) {
		Authorization auth  = new Authorization();
		auth.setLoginName(loginName);
		auth.holdRequest(request);
		return auth;
	}
	
	
	public static Authorization getAuthorization(HttpServletRequest request,
			AuthMgr authMgr) {
		Authorization auth = (Authorization) request.getSession().getAttribute(
				AuthFilter.AUTHORIZATION);
		if (auth == null) {
			auth = new Authorization(authMgr);
			request.getSession().setAttribute(AuthFilter.AUTHORIZATION, auth);
		}
		auth.holdRequest(request);
		return auth;
	}

	public void login(String username, String password) throws AuthException {
		try {
			Authorization _auth = authMgr.auth(username, password,
					request);
			// 设置登录结果 username, roleStr, resStr, cu)
			this.setAuthRes(_auth.getAuthRes());
			this.setAuthRoles(_auth.getAuthRoles());
			this.setLoginName(_auth.getLoginName());
			this.setParam(_auth.getParam());
			this.setPassword(_auth.getPassword());
			this.setUser(_auth.getUser());
			authMgr.refreshRes(_auth);
			hasAuth = true;
			//清除session
			sessionInvalidate();
			request.getSession().setAttribute(AuthFilter.AUTHORIZATION, this);
		} catch (AuthException e) {
			hasAuth =false;
			throw e;
		}
	}

	public Authorization(String loginName, Set<String> authRoles,
			Set<String> authRes, Object user) {
		super();
		this.loginName = loginName;
		this.authRoles = authRoles;
		this.authRes = authRes;
		this.user = user;
	}

	public boolean checkPermission(String url) {
		return false;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public void addParam(String key, Object val) {
		param.put(key, val);
	}

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

	public Set<String> getAuthRoles() {
		return authRoles;
	}

	public void setAuthRoles(Set<String> authRoles) {
		this.authRoles = authRoles;
	}

	public Set<String> getAuthRes() {
		return authRes;
	}

	public void setAuthRes(Set<String> authRes) {
		this.authRes = authRes;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public void sessionInvalidate() {
		request.getSession().invalidate();
	}

}
