package com.bankingappfinal.aspect;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
@Service
public class LoggingAspect {


  @Before("execution(* com.bankingappfinal.resource..*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("logBefore running .....");
    log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

  }

  @After("execution(* com.bankingappfinal.resource..*.*(..))")
  public void logAfter(JoinPoint joinPoint) {

    log.info("logAfter running .....");
    log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
  }

}
