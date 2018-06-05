package com.yunpan.base.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.yunpan.base.web.filter.LogFilter;
import com.yunpan.base.web.security.custm.filter.AuthFilter;

@Configuration
//@ConditionalOnExpression("${web.auth.enable}")
//@ConditionalOnProperty(name="webauthenable", havingValue = "a")
public class FilterRegister {
    @Value("${web.auth.enable:false}")
    private Boolean webAuthEnable;

    @Bean
    public FilterRegistrationBean registerLogFilter() {
            FilterRegistrationBean registration =  new FilterRegistrationBean(new LogFilter());
            registration.addUrlPatterns("/*");
            registration.setOrder(-100);
            return registration;
    }
    
    
    @Bean
    @ConditionalOnProperty(name="cust.web.auth.enable", havingValue = "true")
    public FilterRegistrationBean createAuthFilter() {
            FilterRegistrationBean registration =  new FilterRegistrationBean(new AuthFilter());
            registration.addUrlPatterns("/*");
            return registration;
    }
    
   @Bean
   @ConditionalOnProperty(name="shiro.web.auth.enable", havingValue = "true")
    public FilterRegistrationBean createShiroWebAuthEnable() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new DelegatingFilterProxy());
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.addUrlPatterns("/*");
        registration.setOrder(Integer.MIN_VALUE-1);
        registration.setName("shiroFilter");
        return registration;
    }

}
