package com.blog.framework.common.enums;

/**
 * 通用状态
 *
 * @author liuzw
 **/
public enum StatusEnum {


    /**
     * 有效
     */
    EFFECTIVE(1),

    /**
     * 无效
     */
    INVALID(0),
    ;

    StatusEnum(Integer code) {
        this.code = code;
    }

    /**
     * 状态码
     */
    private Integer code;

    public Integer getCode() {
        return code;
    }


}
