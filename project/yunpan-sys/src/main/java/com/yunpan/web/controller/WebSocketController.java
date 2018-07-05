package com.yunpan.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunpan.data.entity.MerchantEntity;
import com.yunpan.service.bean.AppCommon;

@Controller
public class WebSocketController {
  
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    //spring提供的发送消息模板
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;

    @RequestMapping(value = "/templateTest")
    public void templateTest(HttpServletRequest request) {      
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
        logger.info("用户" + i++ + "---" + user);
        }  
       
           //发送消息给指定用户
           messagingTemplate.convertAndSendToUser("222734647", "/queue/message", "幽幽幽幽幽幽幽幽");
     
       
       
    }

}
