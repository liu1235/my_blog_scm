package com.blog.framework.common.enums;

/**
 * 菜单类型枚举
 *
 * @author liuzw
 **/
public enum MenuTypeEnum {

    /**
     * 目录
     */
    DIRECTORY(1, "目录"),

    /**
     * 菜单
     */
    MENU(2, "菜单"),

    /**
     * 按钮
     */
    BUTTON(3, "按钮"),

    /**
     * 未知
     */
    OTHER(-1, "未知"),
    ;


    public static MenuTypeEnum getMenuTypeEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()) {
            if (menuTypeEnum.code.equals(code)) {
                return menuTypeEnum;
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

    MenuTypeEnum(Integer code, String msg) {
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
