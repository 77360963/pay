package com.yunpan.base.web.security.custm.exception;

public class AuthException extends Exception {

    public AuthException() {
        super();
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

}
