package com.blog.framework.web.annotation;

import com.blog.framework.common.ResultData;
import com.blog.framework.common.constants.BaseConstants;
import com.blog.framework.common.enums.ResultDataEnum;
import com.blog.framework.common.exception.LoginException;
import com.blog.framework.service.TokenService;
import com.blog.framework.vo.sys.user.SysUserLoginVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-05-21
 **/
@Slf4j
@Order(1)
@Aspect
@Component
public class PermissionsAop {

    @Autowired
    private TokenService tokenService;


    @Pointcut("@annotation(com.blog.framework.web.annotation.Permissions)")
    public void point() {
    }


    @Around("point()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //首先检验是否登录
        if (checkLogin()) {
            return ResultData.createErrorResult(ResultDataEnum.UN_LOGIN.getCode(), ResultDataEnum.UN_LOGIN.getMsg());
        }
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        //获取方法上 @Permissions 注解
        Permissions permissions = targetMethod.getAnnotation(Permissions.class);
        //判断该账户有没有该方法的操作权限
        if (checkPermissions(permissions.permissions())) {
            return pjp.proceed();
        }
        ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
        String methodRemark = apiOperation == null ? ResultDataEnum.AUTH_FAIL.getMsg() : apiOperation.value();
        return ResultData.createErrorResult(ResultDataEnum.AUTH_FAIL.getCode(), methodRemark);
    }

    /**
     * 检验账号是否登录
     *
     * @return Boolean
     */
    private Boolean checkLogin() {
        long s = System.currentTimeMillis();
        SysUserLoginVo sysUserInfo = tokenService.getSysUserInfo();
        Boolean flag = sysUserInfo == null;
        log.info("---------checkLogin [{}] 验证耗时{}ms----------", flag, (System.currentTimeMillis() - s));
        return flag;
    }

    /**
     * 检验账号是拥有该方法的操作权限
     *
     * @return Boolean
     */
    private Boolean checkPermissions(String perms) {
        long s = System.currentTimeMillis();
        SysUserLoginVo sysUserInfo = tokenService.getSysUserInfo();
        List<String> permsList = sysUserInfo.getPermsList();
        if (CollectionUtils.isEmpty(permsList)) {
            return false;
        }
        boolean flag = permsList.contains(perms);
        log.info("---------checkPermissions [{}] 验证耗时{}ms----------", flag, (System.currentTimeMillis() - s));
        return flag;
    }

}
