package com.blog.framework.web.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 打印接口日志
 *
 * @author liuzw
 **/
@Slf4j
@Order(-2)
@Aspect
@Component
public class SysLogAop {


    @Around("@annotation(com.blog.framework.web.annotation.SysLog)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return pjp.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        log.info("==================SysLogAop  START====================");
        log.info("================>(doBefore) URL : " + request.getRequestURL().toString());
        log.info("================>(doBefore) HTTP_METHOD : " + request.getMethod());
        log.info("================>(doBefore) CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("================>(doBefore) ContentType : " + request.getContentType());
        Object[] args = pjp.getArgs();
        if (args != null) {
            List<Object> newArgs = new ArrayList<>(args.length);
            for (Object arg : args) {
                if ((arg == null) || (arg instanceof HttpServletRequest)
                        || (arg instanceof HttpServletResponse)
                        || (arg instanceof MultipartFile)) {
                    continue;
                }
                newArgs.add(arg);
            }
            log.info("================>(doBefore) ARGS:{}", JSON.toJSONString(newArgs));
        }
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        log.info("================>(doAfter) EXECUTE METHOD TIME : {}ms", (System.currentTimeMillis() - startTime));
        log.info("==================SysLogAop  END====================");
        log.info("");
        log.info("");
        return obj;
    }

}
