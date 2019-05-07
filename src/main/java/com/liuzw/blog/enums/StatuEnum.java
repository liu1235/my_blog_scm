package com.liuzw.blog.enums;

/**
 * 通用状态
 *
 * @author liuzw
 * @date 2019-05-07
 **/
public enum StatuEnum {


    /**
     * 有效
     */
    EFFECTIVE(1),

    /**
     * 无效
     */
    INVALID(0),
    ;

    StatuEnum(Integer code) {
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
