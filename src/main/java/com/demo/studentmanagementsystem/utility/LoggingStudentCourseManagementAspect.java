package com.demo.studentmanagementsystem.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingStudentCourseManagementAspect {
public static final Log LOGGER = LogFactory.getLog(LoggingStudentCourseManagementAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.demo.studentmanagementsystem.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
}
