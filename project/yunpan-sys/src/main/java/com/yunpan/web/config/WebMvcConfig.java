package com.yunpan.web.config;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yunpan.web.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
	
	
	
/*	 @Bean
	    public Converter<String, Date> addString2DateConvert() {
	        return new Converter<String, Date>() {
	            @Override
	            public Date convert(String source) {
	            	if(StringUtils.isEmpty(source)){
	            		return null;
	            	}else{
	            		Date d=null;
						try {
							d = DateUtils.parseDate(source, "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
						} catch (ParseException e) {
							e.printStackTrace();
						}
	            		return d;
	            	}
	            }
	        };
		 }*/
	/*
	 * @Override public void addViewControllers(ViewControllerRegistry registry)
	 * { // registry.addViewController("/").setViewName("login");
	 * super.addViewControllers(registry); }
	 */

     @Override
     public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*"); 
	 }


	// 增加alimessageconvert
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		fastConverter.setDefaultCharset(Charset.forName("utf-8"));
		/*
		 * FastJsonConfig fastJsonConfig = new FastJsonConfig();
		 * fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		 */
		// fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}

}
