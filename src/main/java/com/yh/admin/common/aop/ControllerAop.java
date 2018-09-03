package com.yh.admin.common.aop;

import com.yh.admin.common.beans.ResultBean;
import com.yh.admin.common.exceptions.CheckException;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Log4j2
@Aspect
//@Order(10)
@Component
public class ControllerAop {

    @Pointcut("execution(public com.yh.admin.common.beans.ResultBean *(..))")
    public void controllerMethod(){
    }

    @Around("controllerMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint proceedingJoinPoint){

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));

        long startTime = System.currentTimeMillis();
        ResultBean<?> resultBean;
        try {
            resultBean = (ResultBean<?>) proceedingJoinPoint.proceed();
            log.info(proceedingJoinPoint.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable throwable) {
            resultBean = handlerException(proceedingJoinPoint, throwable);
        }
        return resultBean;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint proceedingJoinPoint, Throwable throwable){
        ResultBean<?> resultBean = new ResultBean<>();
        if(throwable instanceof CheckException){
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg(throwable.getMessage());
        }else{
            log.error(proceedingJoinPoint.getSignature() + " error " + throwable);
        }
        return resultBean;
    }
}
