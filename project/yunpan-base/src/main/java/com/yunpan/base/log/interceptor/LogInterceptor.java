package com.yunpan.base.log.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Around("@within(com.yunpan.base.log.Log) || @annotation(com.yunpan.base.log.Log)")
    public Object Interceptor(ProceedingJoinPoint pjp)
            throws ClassNotFoundException {


        Object result = null;
        try {
            // 一切正常的情况下，继续执行被拦截的方法
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);

            return null;
        }
        return result;
    }
}
