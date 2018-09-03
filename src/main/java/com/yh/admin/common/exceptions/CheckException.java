package com.yh.admin.common.exceptions;

public class CheckException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CheckException(){}

    public CheckException(String msg){
        super(msg);
    }

    public CheckException(Throwable cause){
        super(cause);
    }

    public CheckException(String msg, Throwable cause) {
        super(msg, cause);
    }

    protected CheckException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
