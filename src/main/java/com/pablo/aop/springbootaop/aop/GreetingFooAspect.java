package com.pablo.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.pablo.aop.springbootaop.service.GreetingService.sayHello(..))")
    private void greetingFooAsptectPointcut(){}

    @Before("greetingFooAsptectPointcut()")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes "+method+" Invocado con los parametros "+args);
    }
}
