package com.kevin.learning.spring.aop.entities;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AopAroundAdvice implements MethodInterceptor{
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("invoke");
		Object result = invocation.proceed();
		System.out.println("after invoke ");
		return result;
	}
}