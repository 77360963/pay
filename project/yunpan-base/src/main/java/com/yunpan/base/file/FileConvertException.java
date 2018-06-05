package com.yunpan.base.file;

public class FileConvertException extends RuntimeException{

    public FileConvertException() {
        super();
    }

    public FileConvertException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FileConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileConvertException(String message) {
        super(message);
    }

    public FileConvertException(Throwable cause) {
        super(cause);
    }

}
