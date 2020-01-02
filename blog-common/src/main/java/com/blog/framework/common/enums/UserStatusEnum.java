package com.blog.framework.common.enums;

/**
 * 用户状态
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public enum UserStatusEnum {

    /**
     * 已激活
     */
    ACTIVATED(1, "已激活"),

    /**
     * 未激活
     */
    INACTIVATED(0, "未激活"),
    ;

    UserStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
