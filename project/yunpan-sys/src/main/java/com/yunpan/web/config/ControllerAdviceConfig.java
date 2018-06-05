package com.yunpan.web.config;

import com.yunpan.base.web.util.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        logger.error(ex.getMessage(),ex);
        Map map = new HashMap();
        return Result.failed("failed",ex.getMessage());
    }

}
