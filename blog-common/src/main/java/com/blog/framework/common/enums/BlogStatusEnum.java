package com.blog.framework.common.enums;

/**
 * 用户状态
 *
 * @author liuzw
 * @date 2019-04-29
 **/
public enum BlogStatusEnum {

    /**
     * 已发布
     */
    PUBLISHED(1, "已发布"),

    /**
     * 未发布
     */
    UNPUBLISHED(0, "未发布"),
    ;

    BlogStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取描述
     */
    public static String getMsg(Integer code) {
        if (code == null) {
            return "";
        }
        for (BlogStatusEnum classStatusEnum : BlogStatusEnum.values()) {
            if (classStatusEnum.getCode().equals(code)) {
                return classStatusEnum.getMsg();
            }
        }
        return String.valueOf(code);
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
