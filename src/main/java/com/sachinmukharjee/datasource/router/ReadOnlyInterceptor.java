package com.sachinmukharjee.datasource.router;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Order(0)
@Component
public class ReadOnlyInterceptor {

  @Around("@annotation(transactional)")
  public Object interceptReadOnlyTransaction(
      ProceedingJoinPoint joinPoint, Transactional transactional) throws Throwable {
    log.info("Before Transactional Method: " + joinPoint.getSignature().getName());
    try {
      if (transactional.readOnly()) {
        RoutingDataSource.setReadOnlyReplica();
      }
      return joinPoint.proceed();
    } finally {
      RoutingDataSource.clearContext();
    }
  }
}
