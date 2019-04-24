package com.cjd.rescue.common.intercepter;

import com.cjd.rescue.common.anno.SysLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect{

    Log log = LogFactory.getLog(LogAspect.class);

    @Pointcut("@annotation(com.cjd.rescue.common.anno.SysLog)")//指向自定义注解路径
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String className=point.getTarget().getClass().getSimpleName();
        String methodName=point.getSignature().getName();

        Class<?> classTarget=point.getTarget().getClass();
        Class<?>[] par=((MethodSignature) point.getSignature()).getParameterTypes();
        Method objMethod=classTarget.getMethod(methodName, par);

        SysLog sysLog=objMethod.getAnnotation(SysLog.class);
        if(sysLog!=null){
           // sysLog.getClass().getDeclaredField("");
            System.out.println("-----------------");
            System.out.println("-----------------获取注解实现类上的注解");
            System.out.println("-----------------");
        }
        long beginTime = System.currentTimeMillis();
        log.info("类"+className+"中"+methodName+"方法执行开始时间："+ beginTime);
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long endTime = System.currentTimeMillis() ;
        log.info("类"+className+"中"+methodName+"方法执行结束时间："+ endTime);
        log.info("类"+className+"中"+methodName+"方法执行耗时："+ (endTime - beginTime) + "毫秒");

        return result;

    }





}