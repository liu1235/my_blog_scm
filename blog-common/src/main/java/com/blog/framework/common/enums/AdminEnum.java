package com.blog.framework.common.enums;

/**
 * 菜单类型枚举
 *
 * @author liuzw
 **/
public enum AdminEnum {

    /**
     * 是
     */
    YES(1, "是"),

    /**
     * 禁用
     */
    NO(0, "否"),

    ;


    /**
     * 状态值
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;

    AdminEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
