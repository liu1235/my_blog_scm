package com.blog.framework.common.exception;

import java.io.Serializable;

/**
 * 自定义 Dao异常
 *
 * @author liuzw
 **/
public class LoginException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 718609797623162390L;

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause,
                          boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}