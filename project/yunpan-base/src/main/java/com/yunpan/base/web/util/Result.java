package com.yunpan.base.web.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Result {

    public static final String RESPONS_ECODE       = "responseCode";
    public static final String RESPONS_EMSG        = "responseMsg";

    public static final String DATA_LIST           = "dataList";
    public static final String DATA                = "data";
    public static final String DATA_COUNT          = "dataCount";
    public static final String VIEW_NAME           = "viewName";

    public static final String SUCCESS_CODE        = "success";
    public static final String FAILED_DEFAULT_CODE = "failed";
    
    public static final String AUTH_LOGIN_FAILED   = "auth_login_failed";
    public static final String AUTH_ACCESS_DENY    = "auth_access_deny";

    public static Map<String, Object> success(Object result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(RESPONS_ECODE, SUCCESS_CODE);
        map.put(DATA, result);
        return map;
    }

    public static Map<String, Object> success() {
        return success(null);
    }

    public static String successJson(Object result) {
        return JSON.toJSONString(success(result));
    }

    public static String successJson() {
        return JSON.toJSONString(success());
    }

    public static Map<String, Object> failed(String code, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(RESPONS_ECODE, code);
        map.put(RESPONS_EMSG, message);
        return map;
    }

    public static Map<String, Object> authFailed() {
        return failed(AUTH_LOGIN_FAILED, "认证失败请重新登录");
    }
    
    public static Map<String, Object> authFailed(String msg) {
        return failed(AUTH_LOGIN_FAILED, msg);
    }

    public static Map<String, Object> authDeny() {
        return failed(AUTH_ACCESS_DENY, "无权限访问");
    }

    public static Map<String, Object> failed(String message) {
        return failed(FAILED_DEFAULT_CODE, message);
    }

    public static String failedJson(String code, String message) {
        return JSON.toJSONString(failed(code, message));

    }

}
