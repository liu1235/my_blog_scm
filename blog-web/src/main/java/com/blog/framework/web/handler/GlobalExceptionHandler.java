package com.blog.framework.web.handler;

import com.blog.framework.common.ResultData;
import com.blog.framework.common.enums.ResultDataEnum;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author liuzw
 * @date 2018/8/28 11:58
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResultData<String> defaultErrorHandler(final Exception ex) {
        log.error("---------> defaultErrorHandler ", ex);
        return ResultData.createErrorResult(ResultDataEnum.ERROR.getCode(), ResultDataEnum.ERROR.getMsg());
    }


    @ExceptionHandler({ServiceException.class})
    public ResultData<String> handleServiceException(final ServiceException ex) {
        log.error("---------> handleServiceException ", ex);
        return ResultData.createErrorResult(getCode(ex.getCode()), ex.getMessage());
    }

    @ExceptionHandler({LoginException.class})
    public ResultData<String> handleLoginException(final LoginException ex) {
        log.error("---------> handleLoginException", ex);
        return ResultData.createErrorResult(ResultDataEnum.UN_LOGIN.getCode(), ResultDataEnum.UN_LOGIN.getMsg());
    }

    @ExceptionHandler({MailSendException.class})
    public ResultData<String> handleMailSendException(final MailSendException ex) {
        log.error("---------> handleServiceException ", ex);
        return ResultData.createErrorResult(ResultDataEnum.MAIL_ERROR.getCode(), ResultDataEnum.MAIL_ERROR.getMsg());
    }


    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class,
            IllegalStateException.class})
    public ResultData<String> handle500s(final RuntimeException ex) {
        log.error("---------> handle500s ", ex);
        return ResultData.createErrorResult(ResultDataEnum.ERROR.getCode(), ResultDataEnum.ERROR.getMsg());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultData<String> methodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        log.error("---------> methodArgumentNotValidException ", ex);
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : allErrors) {
            stringBuilder.append(error.getDefaultMessage()).append(";");
        }
        return ResultData.createErrorResult(ResultDataEnum.PARAM_NOT_NULL.getCode(), stringBuilder.toString());
    }


    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResultData<String> handleBadRequest(final ConstraintViolationException ex) {
        log.error("---------> 422 Status Code", ex);
        log.debug("---------> Violation exception: {}", ex.getLocalizedMessage());
        return ResultData.createErrorResult(ResultDataEnum.PARAM_NOT_NULL.getCode(), ResultDataEnum.PARAM_NOT_NULL.getMsg());
    }


    /**
     * 获取code
     *
     * @param code 错误码
     * @return Integer
     */
    private Integer getCode(Integer code) {
        return code == null ? ResultDataEnum.ERROR.getCode() : code;
    }

}
