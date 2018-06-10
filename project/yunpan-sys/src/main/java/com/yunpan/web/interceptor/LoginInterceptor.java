package com.yunpan.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunpan.base.annotation.IfNeedLogin;
import com.yunpan.base.web.util.Result;
import com.yunpan.service.bean.AppCommon;

@Component
public class LoginInterceptor  extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {  
            return true;  
        }  
        final HandlerMethod handlerMethod = (HandlerMethod) handler; 
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
       if (clazz.isAnnotationPresent(IfNeedLogin.class) || method.isAnnotationPresent(IfNeedLogin.class)) {  
           if(request.getSession().getAttribute(AppCommon.SESSION_KEY) == null){        	   
        	   String requestType = request.getHeader("X-Requested-With");
        	 //判断是否是ajax请求
        	   if(requestType!=null && "XMLHttpRequest".equals(requestType)){
        		   // ajax请求
                   response.setHeader("SESSIONSTATUS", "TIMEOUT");
                   response.setHeader("CONTEXTPATH",request.getContextPath()+"/login");
                   response.setStatus(HttpServletResponse.SC_FORBIDDEN);//403 禁止
                   return false;
        	   }else{
        		   response.sendRedirect(request.getContextPath()+"/login");
        	   }        	   
               
           }else{  
               return true;  
           }  
        }
       return true;  
    }

    
    
}
