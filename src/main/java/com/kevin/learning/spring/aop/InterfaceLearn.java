package com.kevin.learning.spring.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.Advisor;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

public class InterfaceLearn {

	/**
	 * spring 中没有明确的 Aspect概念，而是用Advisor代替，advisor = advice(what to do )+ Pointcut(where to do )来表示的
	 * 
	 */
	
	ProceedingJoinPoint l;
	
	Pointcut pointcut;
	NameMatchMethodPointcut namePointcut;
	ComposablePointcut c;
	
	Advice ad;
	BeforeAdvice bd;
	ThrowsAdvice td;
	MethodBeforeAdvice m;
	
	//spring的around advice 是通过 AOP的MethodInterceptor实现的
	MethodInterceptor mInter;
	//这个类将当前正在执行的MethodInterceptor保存在ThreadLocal中
	ExposeInvocationInterceptor expose;
	
	AspectJAfterThrowingAdvice asjectJT;
	ThrowsAdviceInterceptor tai;
	
	//可以找到local变量名称
	LocalVariableTableParameterNameDiscoverer localDis;
	
	Advisor advisor;
	PointcutAdvisor pointcutAdvisor;
	IntroductionAdvisor introAdvisor; //不会怎么用
	DefaultPointcutAdvisor defaultPt;

	//代理对象
//	org.springframework.aop.framework.JdkDynamicAopProxy jdkPorxy; 不可见
	ProxyConfig proxyConfig;
	ProxyFactory factory;
	
	
	
	//BeanPostProcessor 
	InstantiationAwareBeanPostProcessor bp;
}
