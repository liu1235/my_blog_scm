package com.blog.framework.common.enums;

/**
 * 返回内容
 *
 * @author liuzw
 */
public enum ResultDataEnum {

    /**
     * 成功
     */
    SUCCESS(0, "操作成功"),

    /**
     * 失败
     */
    ERROR(1, "操作失败"),

    /**
     * 当前用户未登录
     */
    UN_LOGIN(3, "当前用户未登录"),

    /**
     * 新增成功
     */
    INSERT_SUCCESS(0, "新增成功"),

    /**
     * 新增失败
     */
    INSERT_ERROR(1, "新增失败"),

    /**
     * 删除成功
     */
    DELETE_SUCCESS(0, "删除成功"),

    /**
     * 删除失败
     */
    DELETE_ERROR(1, "删除失败"),

    /**
     * 修改成功
     */
    UPDATE_SUCCESS(0, "修改成功"),

    /**
     * 修改失败
     */
    UPDATE_ERROR(1, "修改失败"),

    /**
     * 参数不能为空
     */
    PARAM_NOT_NULL(100, "参数不能为空"),


    /**
     * 账号已存在
     */
    ACCOUNT_EXISTS(1000, "账号已存在"),

    /**
     * 用户名密码错误
     */
    USERNAME_OR_PASSWORD_IS_WRONG(1001, "用户名密码错误"),

    /**
     * 账号未激活
     */
    EMAIL_NOT_ACTIVATED(1002, "账号未激活"),

    /**
     * 账号激活超时
     */
    ACCOUNT_ACTIVATION_TIMEOUT(1003, "账号激活超时"),

    /**
     * 邮箱错误或不存在
     */
    MAIL_ERROR(1004, "邮箱错误或不存在"),


    /**
     * 非法激活
     */
    ILLEGAL_ACTIVATION(1005, "非法激活"),

    ;

    /**
     * 状态值
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;

    ResultDataEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }}
