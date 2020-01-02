package com.blog.framework.common;

import com.blog.framework.enums.ResultDataEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 返回类型
 *
 * @author liuzw
 **/
@Data
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 3866468576998485238L;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String message;

    /**
     * 返回结果集
     */
    @ApiModelProperty(value = "返回结果集")
    private T data;


    /**
     * 创建新增返回结果
     *
     * @param flag boolean
     * @return ResultData
     */
    public static <E> ResultData<E> createInsertResult(Boolean flag) {
        if (flag) {
            return createSuccessResult(ResultDataEnum.INSERT_SUCCESS.getMsg());
        } else {
            return createErrorResult(ResultDataEnum.INSERT_ERROR.getMsg());
        }
    }


    /**
     * 创建新增返回结果
     *
     * @param flag    boolean
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createInsertResult(Boolean flag, String message) {
        if (flag) {
            return createSuccessResult(message);
        } else {
            return createErrorResult(message);
        }
    }


    /**
     * 创建删除返回结果
     *
     * @param flag boolean
     * @return ResultData
     */
    public static <E> ResultData<E> createDeleteResult(Boolean flag) {
        if (flag) {
            return createSuccessResult(ResultDataEnum.DELETE_SUCCESS.getMsg());
        } else {
            return createErrorResult(ResultDataEnum.DELETE_ERROR.getMsg());
        }
    }

    /**
     * 创建删除返回结果
     *
     * @param flag    boolean
     * @param message 错误信息
     * @return ResultData
     */
    public static ResultData createDeleteResult(Boolean flag, String message) {
        if (flag) {
            return createSuccessResult(message);
        } else {
            return createErrorResult(message);
        }
    }


    /**
     * 创建修改成功返回结果
     *
     * @param flag boolean
     * @return ResultData
     */
    public static <E> ResultData<E> createUpdateResult(Boolean flag) {
        if (flag) {
            return createSuccessResult(ResultDataEnum.UPDATE_SUCCESS.getMsg());
        } else {
            return createErrorResult(ResultDataEnum.UPDATE_ERROR.getMsg());
        }
    }


    /**
     * 创建修改返回结果
     *
     * @param flag    boolean
     * @param message 错误信息
     * @return ResultData
     */
    public static <E> ResultData<E> createUpdateResult(Boolean flag, String message) {
        if (flag) {
            return createSuccessResult(message);
        } else {
            return createErrorResult(message);
        }
    }


    /**
     * 返回查询成功信息
     *
     * @param data 返回结果集
     * @return ResultData
     */
    public static <E> ResultData<E> createSelectResult(E data) {
        return new ResultData<>(ResultDataEnum.SUCCESS.getCode(), ResultDataEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 返回成功信息
     *
     * @param data    返回结果集
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createSelectResult(E data, String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataEnum.SUCCESS.getMsg();
        }
        return new ResultData<>(ResultDataEnum.SUCCESS.getCode(), message, data);
    }


    /**
     * 返回错误信息
     *
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createErrorResult(String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataEnum.ERROR.getMsg();
        }
        return createErrorResult(ResultDataEnum.ERROR.getCode(), message);
    }

    /**
     * 返回错误信息
     *
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createErrorResult(Integer code, String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataEnum.ERROR.getMsg();
        }
        if (code == null) {
            code = ResultDataEnum.ERROR.getCode();
        }
        return new ResultData<>(code, message, null);
    }


    /**
     * 返回成功信息
     *
     * @return ResultData
     */
    public static <E> ResultData<E> createSuccessResult() {
        return new ResultData<>(ResultDataEnum.SUCCESS.getCode(), ResultDataEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 返回成功信息
     *
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createSuccessResult(String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataEnum.SUCCESS.getMsg();
        }
        return new ResultData<>(ResultDataEnum.SUCCESS.getCode(), message, null);
    }


    private ResultData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
