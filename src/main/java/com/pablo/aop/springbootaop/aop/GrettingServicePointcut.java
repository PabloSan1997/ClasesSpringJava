package com.pablo.aop.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GrettingServicePointcut {
    
    @Pointcut("execution(* com.pablo.aop.springbootaop.service.*.*(..))")
    public void greetingLoggerPointCut(){
    }


}
