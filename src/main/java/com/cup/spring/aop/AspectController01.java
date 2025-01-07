package com.cup.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class AspectController01 {


	@Pointcut("within(com.cup.spring.controller..*)")
	public void requestMethods() {
		// 지정된 패키지 또는 클래스
	}
	
    @Around("requestMethods()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("==> {} with args: {}", methodName, Arrays.toString(args));
        try {
        	// 지정된 클래스의 메서드 실행
            Object result = joinPoint.proceed();
            log.info("=== {} result: {}", methodName, result);
            return result;
        }
        finally {
            log.info("<== {}", methodName);
        }
    }	
}

