package com.yunpan.service.test;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunpan.service.service.MerchantService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class MailTest {    
    @Autowired
    private JavaMailSender javaMailSender;      
    @Autowired
    private MerchantService merchantService;
    
    private CountDownLatch latch = new CountDownLatch(1);
    
    public void send(final String name){
        System.out.println("进入方法");
        new Thread(){
            public void run() {
                System.out.println("11111");
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("meet99@qq.com");
                message.setTo("77360963@qq.com");
                message.setSubject(name);
                message.setText("测试内容部份");
                javaMailSender.send(message);
                System.out.println("发送完成");
                latch.countDown();
            };
        }.start();
    }    
    @Test
    public void test1() {
        send("xx");
        try {
            latch.await(); // 主线程等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
  
}
