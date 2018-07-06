package com.yunpan.base.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	/**
     * 添加cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {        
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			cookie.setPath("/");	       
	        cookie.setMaxAge(30*24*3600);	       
	        response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			
		}
       
    }

    /**
     * 删除cookie
     * 
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * 获取cookie值
     * 
     * @param request
     * @return
     */
    public static String getUid(HttpServletRequest request,String cookieName) {
        try {
			Cookie cookies[] = request.getCookies();
			for (Cookie cookie : cookies) {
			    if (cookie.getName().equals(cookieName)) {
			        return URLDecoder.decode(cookie.getValue(),"UTF-8");
			    }
			}
		} catch (UnsupportedEncodingException e) {
			
		}
        return null;
    }

}
