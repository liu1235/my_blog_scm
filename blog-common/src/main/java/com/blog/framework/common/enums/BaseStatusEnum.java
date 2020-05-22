package com.blog.framework.common.enums;

/**
 * 菜单类型枚举
 *
 * @author liuzw
 **/
public enum BaseStatusEnum {

    /**
     * 启用
     */
    ENABLE(1, "启用"),

    /**
     * 禁用
     */
    DISABLE(0, "禁用"),

    /**
     * 未知
     */
    OTHER(-1, "未知")

    ;


    public static BaseStatusEnum getStatusEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (BaseStatusEnum statusEnum : BaseStatusEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return OTHER;
    }


    /**
     * 状态值
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;

    BaseStatusEnum(Integer code, String msg) {
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
