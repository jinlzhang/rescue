package com.cjd.rescue.dao.intercepter;

import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Aspect
@Component
public class JDBCExceptionAspect {

    Log log = LogFactory.getLog(JDBCExceptionAspect.class);

    @Pointcut("@annotation(com.cjd.rescue.dao.anno.JDBCException)")//指向自定义注解路径
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;

        try {
            //执行方法
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("db exception:"+throwable.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnT.result(Err.DB_ERR);
        }
        return result;

    }





}