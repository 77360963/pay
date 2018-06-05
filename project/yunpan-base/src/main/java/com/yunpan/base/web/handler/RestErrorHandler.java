package com.yunpan.base.web.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yunpan.base.web.util.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
//@RequestMapping("${server.error.path:${error.path:/error}}")
public class RestErrorHandler extends AbstractErrorController {

    public RestErrorHandler(ErrorAttributes errorAttributes,
                            List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    private Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    @RequestMapping("/error")
    public Map<String, Object> errorMsg(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, true);
        logger.error("==========>error:" + body);
        HttpStatus status = getStatus(request);
        return Result.failed("SYS-" + String.valueOf(status.value()), status.getReasonPhrase());
    }

    @RequestMapping("/auth_sucess")
    public Map<String, Object> auth_success(HttpServletRequest request) {
        return Result.success();
    }

    @RequestMapping("/auth_failed")
    public Map<String, Object> auth_failed(HttpServletRequest request) {
        return Result.failed(Result.AUTH_LOGIN_FAILED, "用户登录认证失败");
    }

    @RequestMapping("/auth_deny")
    public Map<String, Object> auth_dency(HttpServletRequest request) {
        return Result.failed(Result.AUTH_LOGIN_FAILED, "用户登录认证失败");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
