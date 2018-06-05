package com.yunpan.base.web.security.custm.mgr.impl;

import java.util.HashSet;
import java.util.Set;

import com.yunpan.base.web.security.custm.Authorization;
import com.yunpan.base.web.security.custm.exception.AuthException;
import com.yunpan.base.web.security.custm.mgr.AuthMgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

public abstract class AbsAuthMgr implements AuthMgr {
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Set<String> defaultIgnorPath = new HashSet<String>();
    {
        defaultIgnorPath.add("/**/*.css");
        defaultIgnorPath.add("/**/*.js");
        defaultIgnorPath.add("/**/*.png");
        defaultIgnorPath.add("/**/*.jpg");
        defaultIgnorPath.add("/**/*.woff");
        defaultIgnorPath.add("/**/favicon.ico");
        defaultIgnorPath.add("/auth/**");
        defaultIgnorPath.add("/**/resetPwd.html");
        defaultIgnorPath.add("/log/**");
        defaultIgnorPath.add("/**/fileLoadZip.html");
    }

    @Override
    public void init() {
    	 Set<String> ingorePathSets = getIgnorPath();
    	 defaultIgnorPath.addAll(ingorePathSets);
    	 logger.info("======>不过滤路径"+defaultIgnorPath);
    }

    @Override
    public final boolean isIgnorPath(String servletPath) {
        for (String str : defaultIgnorPath) {
            if (antMather(str, servletPath)) {
                return true;
            }
        }
        return false;
    }

    protected Set<String> getIgnorPath() {
        return new HashSet<String>();
    }

    public void webSecurityAuth(Authorization auth, String serverletPath) throws AuthException {
    	System.out.println(auth.getAuthRes()+"---");
        for (String res : auth.getAuthRes()) {
            if (antMather(res, serverletPath)) {
                return;
            }
        }
        throw new AuthException("无权限访问[" + serverletPath + "]");
    }

    private boolean antMather(String path, String servletPath) {
        return new AntPathMatcher().match(path, servletPath);
    }

    public static void main(String[] args) {
        System.out.println(new AntPathMatcher().match("/a.html*", "/a.html?a=1"));
        System.out.println(new AntPathMatcher().match("/**/*.js", "/a/b/c.js"));
        System.out.println(new AntPathMatcher().match("/**/*.png*", "/pos/img/logo.png?a=1"));
        System.out.println(new AntPathMatcher().match("/auth/*", "/auth/getUserRoleResInfo"));
        System.out.println(new AntPathMatcher().match("/**/*.map", "/css/bootstrap.min.css.map"));
    }
}
