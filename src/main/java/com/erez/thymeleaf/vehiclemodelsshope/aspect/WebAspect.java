package com.erez.thymeleaf.vehiclemodelsshope.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class WebAspect {
	
	private Logger myLogger = LoggerFactory.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.erez.thymeleaf.crmthymeleaf.controller.*.*(..))")
    public void webAspect() {
    }

	
	@Before("webAspect()")
    public void doBefore(JoinPoint theJoinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String beanName = theJoinPoint.getSignature().getDeclaringTypeName();
        String methodName = theJoinPoint.getSignature().getName();
        String uri = request.getRequestURI();
        String remoteAddr = getIpAddr(request);
        String sessionId = request.getSession().getId();
        String user = (String) request.getSession().getAttribute("user");
        String method = request.getMethod();
        myLogger.info(method);
        myLogger.info(user);
        myLogger.info(remoteAddr);
        myLogger.info(sessionId);
        myLogger.info(uri);
        myLogger.info(methodName);
        myLogger.info(beanName);
        
        String theMethod = theJoinPoint.getSignature().toShortString();
    	myLogger.info("======> in @Before calling method :" + theMethod);
    	
    	Object[] args = theJoinPoint.getArgs();
    	for(Object tempArg : args) {
    		myLogger.info("==========> argument:" + tempArg);
    	}
        
	}

	@AfterReturning(returning = "theResult", pointcut = "webAspect()")
    public void doAfterReturning(JoinPoint theJoinPoint ,Object theResult) {
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("======> in @AftereReturning calling method :" + theMethod);
		
		myLogger.info("======> result  :" + theResult);;
    }

	@Around("webAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoin) throws Throwable {
         Object o = null;
        System.out.println("before---------------------");
        o = proceedingJoinPoin.proceed();
        System.out.println("after---------------------");
        return o;
        
       
    }

	 private String getIpAddr(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for");
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        return ip;
	    }
	
	
}
