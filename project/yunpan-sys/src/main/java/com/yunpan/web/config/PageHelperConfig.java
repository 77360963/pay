package com.yunpan.web.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;

@Component
public class PageHelperConfig {
  
  /*  @Bean
   public PageHelper getPageHelper(){
    PageHelper pageHelper=new PageHelper();
    Properties properties=new Properties();
    properties.setProperty("helperDialect","mysql");
    properties.setProperty("reasonable","false");
    properties.setProperty("supportMethodsArguments","true");
    properties.setProperty("params","count=countSql");
    pageHelper.setProperties(properties);
    return pageHelper;
    }*/
}
