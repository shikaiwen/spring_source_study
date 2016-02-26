package com.kevin.learning.spring.aop.aspectj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	@Test
	public void test1(){
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("springaop/spring-aop-aspectj-1.xml");
			GoToWork goToWork = context.getBean(GoToWork.class);
			goToWork.doWork();
			goToWork.toToWorkAndReturn(5);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
