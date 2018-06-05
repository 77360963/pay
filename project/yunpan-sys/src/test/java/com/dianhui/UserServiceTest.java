/*
package com.pingan.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hiiso.biz.service.AuthService;
import com.hiiso.data.domain.Operator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceTest.class)
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.hiiso")
public class UserServiceTest {
    @Autowired
    private AuthService userRoleService;
    @Autowired
    private Environment       env;

    @Test
    public void pageSelect() throws Exception {
        try {
            Operator param = new Operator();
           // List<User> uList = userRoleService.queryPagedUser(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/
