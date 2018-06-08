package com.yunpan.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunpan.base.annotation.IfNeedLogin;

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
           if(request.getAttribute("xx") == null){                
               response.sendRedirect(request.getContextPath()+"/merchantLoginIndex");
           }else{  
               return true;  
           }  
        }
       return true;  
    }

    
    
}
