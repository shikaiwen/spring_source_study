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
	 * 不能同时  (modifier type )和 (return-type)都写成 * , 第一个可以不写就代表其为*，return type可以为* 或类型，如void ,String 
	 * name-pattern和decaring-type-pattern同时存在时，要将彼此用.连接
	 * 
	 * Advice如果有JoinPoint参数，那么一定要放在第一个
	 * 
	 * 可以吧Advice和Pointcut分开写，也可以就在Advice注解中使用pointcut表达式，区别是分开写，可以进行复用，在其他的
	 * 地方也可以引用这个advice
	 */
	@Pointcut("execution(* com.kevin.learning.spring.aop.aspectj..*(..))")
	public void prepareWorkPointcut(){
	}
	
	//表示这个advice要使用上面的pointcut
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
