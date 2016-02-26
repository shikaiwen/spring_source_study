package com.kevin.learning.spring.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class InterfaceLearn {

	/**
	 * spring ��û����ȷ�� Aspect���������Advisor���棬advisor = advice(what to do )+ Pointcut(where to do )����ʾ��
	 * 
	 */
	
	Pointcut pointcut;
	NameMatchMethodPointcut namePointcut;
	ComposablePointcut c;
	
	Advice ad;
	BeforeAdvice bd;
	ThrowsAdvice td;
	MethodBeforeAdvice m;
	
	//spring��around advice ��ͨ�� AOP��MethodInterceptorʵ�ֵ�
	MethodInterceptor mInter;
	
	Advisor advisor;
	PointcutAdvisor pointcutAdvisor;
	IntroductionAdvisor introAdvisor; //������ô��
	DefaultPointcutAdvisor defaultPt;

	//��������
//	org.springframework.aop.framework.JdkDynamicAopProxy jdkPorxy; ���ɼ�
	ProxyConfig proxyConfig;
	ProxyFactory factory;
	
	//BeanPostProcessor 
	InstantiationAwareBeanPostProcessor bp;
}