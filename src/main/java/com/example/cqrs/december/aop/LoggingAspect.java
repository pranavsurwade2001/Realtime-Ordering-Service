package com.example.cqrs.december.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.cqrs.december.controller..*(..)) || execution(* com.example.cqrs.december.service..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        log.info("START: {}", method);

        long start = System.currentTimeMillis();
        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            log.error("ERROR in {} : {}", method, ex.getMessage());
            throw ex;
        }

        long time = System.currentTimeMillis() - start;
        log.info("END: {} ({} ms)", method, time);

        return result;
    }
}

