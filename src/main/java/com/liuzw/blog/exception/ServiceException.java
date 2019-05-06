package com.liuzw.blog.exception;

import java.io.Serializable;

/**
 * 自定义Service异常
 *
 * @author liuzw
 **/
public class ServiceException extends RuntimeException implements Serializable {

    /**
     * 错误代码
     */
    private Integer code;

    private static final long serialVersionUID = -219863228322009672L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
