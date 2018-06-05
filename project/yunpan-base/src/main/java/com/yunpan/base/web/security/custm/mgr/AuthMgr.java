package com.yunpan.base.web.security.custm.mgr;


import javax.servlet.http.HttpServletRequest;

import com.yunpan.base.web.security.custm.Authorization;
import com.yunpan.base.web.security.custm.exception.AuthException;

import org.springframework.stereotype.Component;

@Component
public interface AuthMgr {
	
	public void init();

    boolean isIgnorPath(String servletPath);

    Authorization auth(String username, String password, HttpServletRequest request)
                                                                                   throws AuthException;

    public void refreshRes(Authorization cu);

    public void webSecurityAuth(Authorization auth, String serverletPath) throws AuthException;

	public String getLoginUrl();

	public String getLoginPage();

	public String getLoginOutUrl();

	public String getAuthDenyUrl();

}
