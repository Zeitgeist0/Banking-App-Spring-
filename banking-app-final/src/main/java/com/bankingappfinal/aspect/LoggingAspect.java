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



  /**
   * Run before the method execution.
   * @param joinPoint
   * com.test.model..*.*(..)
   */
  @Before("execution(* com.bankingappfinal.resource..*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("logBefore running .....");
    log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

  }

  /**
   * Run after the method returned a result.
   * @param joinPoint
   */
  @After("execution(* com.bankingappfinal.resource..*.*(..))")
  public void logAfter(JoinPoint joinPoint) {

    log.info("logAfter running .....");
    log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
  }

  /**
   * Run after the method returned a result, intercept the returned result as well.
   * @param joinPoint
   * @param result
   */
  @AfterReturning(pointcut = "execution(* com.bankingappfinal.resource..*.*(..))", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    log.info("logAfterReturning running .....");
    log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

  }

  /**
   * Run around the method execution.
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("execution(* com.bankingappfinal.resource..*.*(..))")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("logAround running .....");
//		if (log.isDebugEnabled()) {
    log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//		}
    try {
      Object result = joinPoint.proceed();
//			if (log.isDebugEnabled()) {
      log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(), result);
//			}
      return result;
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
      throw e;
    }

  }

  /**
   * Advice that logs methods throwing exceptions.
   *
   * @param joinPoint join point for advice
   * @param error         exception
   */

  @AfterThrowing(pointcut = "execution(* com.bankingappfinal.resource..*.*(..)))", throwing = "error")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
    log.info("logAfterThrowing running .....");
    log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), error.getCause() != null ? error.getCause() : "NULL");
  }
}
