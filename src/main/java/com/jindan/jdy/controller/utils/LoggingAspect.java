package com.jindan.jdy.controller.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Pointcut("execution(* com.jindan.jdy.controller.stock.StarchOrganizationPutController.*(..))")
    public void declareJointPointExpression() {

    }

    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("这是切面开始打印出来的--->The method " + methodName + " begins with " + Arrays.asList(args));
    }


    @After("declareJointPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("这是切面结束打印出来的--->The method " + methodName + " ends");
    }


    @AfterReturning(value = "declareJointPointExpression()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends with " + result);
    }


    @AfterThrowing(value = "declareJointPointExpression()",
            throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs excetion:" + e);
    }

}
