package com.erez.thymeleaf.vehiclemodelsshope.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLogingAspect {
	
	private Logger myLogger = LoggerFactory.getLogger(getClass().getName());

 @Pointcut ("execution(* com.erez.thymeleaf.vehiclemodelsshope.controller.*.*(..))")
 private void forControllerPackage(){}
 
 @Pointcut ("execution(* com.erez.thymeleaf.vehiclemodelsshope.service.*.*(..))")
 private void forServicePackage(){}

 @Pointcut ("execution(* com.erez.thymeleaf.vehiclemodelsshope.dao.*.*(..))")
 private void forDaoPackage(){}

 @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
 private void forAppFlow() {}

@Before("forAppFlow()")
public void before(JoinPoint theJoinPoint) {
	
	String theMethod = theJoinPoint.getSignature().toShortString();
	myLogger.info("======> in @Before calling method :" + theMethod);
	
	Object[] args = theJoinPoint.getArgs();
	for(Object tempArg : args) {
		myLogger.info("==========> argument:" + tempArg);
	}
}
@AfterReturning(pointcut= "forAppFlow()",returning="theResult")
public void afterReturning(JoinPoint theJoinPoint , Object theResult) {
	String theMethod = theJoinPoint.getSignature().toShortString();
	myLogger.info("======> in @AftereReturning calling method :" + theMethod);
	
	myLogger.info("======> result  :" + theResult);
	
}


}
