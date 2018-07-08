package com.yunpan.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpan.base.mail.impl.BaiduText2audio;
import com.yunpan.base.web.util.Result;

@Controller
public class WebSocketController {
  
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    //spring提供的发送消息模板
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;
    
    @Autowired
    private BaiduText2audio baiduText2audio;

    @RequestMapping(value = "/templateTest")
    @ResponseBody
    public Map templateTest(HttpServletRequest request) {      
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
        logger.info("用户" + i++ + "---" + user);
        }  
       
       String user= request.getParameter("user");
       if(StringUtils.isBlank(user)){
    	   user="222734647";
       }
       //发送消息给指定用户
       String redioPath= baiduText2audio.getText2audio("1","189.03"); 
       messagingTemplate.convertAndSendToUser(user, "/queue/message", redioPath);
       
       Map map=new HashMap();
       map.put("在线人数", userRegistry.getUserCount());
       
       return Result.success(map);
       
       
    }

}
