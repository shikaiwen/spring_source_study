package com.kevin.learning.spring.aop.entities;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AopAfterReturningAdvice implements AfterReturningAdvice{
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("after return");
	}
}