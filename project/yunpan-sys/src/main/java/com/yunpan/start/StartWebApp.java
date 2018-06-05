package com.yunpan.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.yunpan")
@MapperScan("com.yunpan.data.dao")
//@SpringBootApplication
public class StartWebApp {
    public static void main(String[] args) {
        SpringApplication.run(StartWebApp.class);
       // SpringApplication app = new SpringApplication(StartWebApp.class);
       // app.run(args);
    }
}
