package com.kevin.learning.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
public class MyAspect {

	/**
	 * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
	 * ����ͬʱ  (modifier type )�� (return-type)��д�� * , ��һ�����Բ�д�ʹ�����Ϊ*��return type����Ϊ* �����ͣ���void ,String 
	 * name-pattern��decaring-type-patternͬʱ����ʱ��Ҫ���˴���.����
	 * 
	 * Advice�����JoinPoint��������ôһ��Ҫ���ڵ�һ��
	 * 
	 * ���԰�Advice��Pointcut�ֿ�д��Ҳ���Ծ���Adviceע����ʹ��pointcut���ʽ�������Ƿֿ�д�����Խ��и��ã���������
	 * �ط�Ҳ�����������advice
	 */
	@Pointcut("execution(* com.kevin.learning.spring.aop.aspectj..*(..))")
	public void prepareWorkPointcut(){
	}
	
	//��ʾ���adviceҪʹ�������pointcut
	@Before("prepareWorkPointcut()")
	public void beforeAdvice(){
		System.out.println("get up from bed ");
	}
	
	@Around("execution(* com.kevin.learning.spring.aop.aspectj..toToWorkAndReturn(*)) && args(cost)")
	public void buBusAround(ProceedingJoinPoint proced,int cost){
		System.out.println("tiket seller receive go to company monney  " + cost);
		try {
			proced.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("tiket seller receive go to home monney  " + cost);
	}
	
}
