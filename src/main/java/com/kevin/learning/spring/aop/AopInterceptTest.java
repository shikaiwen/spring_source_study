package com.kevin.learning.spring.aop;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kevin.learning.spring.aop.entities.AopAfterReturningAdvice;
import com.kevin.learning.spring.aop.entities.AopAroundAdvice;
import com.kevin.learning.spring.aop.entities.AopMethodBeforeAdvice;
import com.kevin.learning.spring.aop.entities.FooService;
import com.kevin.learning.spring.aop.entities.FooServiceImpl;


public class AopInterceptTest {
	
	static URL u = ClassLoader.getSystemResource("log4j.properties");
	static{
		PropertyConfigurator.configure(u);
	}
	
	static Logger logger = LoggerFactory.getLogger(AopInterceptTest.class);
	
	@Test@Ignore
	public void test1(){

		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("action");
		
		NameMatchMethodPointcut pointcut2 = new NameMatchMethodPointcut();
		pointcut2.setMappedName("action4");
		
		AopMethodBeforeAdvice before = new AopMethodBeforeAdvice();
		AopAfterReturningAdvice after = new AopAfterReturningAdvice();
		AopAroundAdvice around = new AopAroundAdvice();
		
		DefaultPointcutAdvisor beforeAdvisor = new DefaultPointcutAdvisor(pointcut,before),
				afterAdvisor = new DefaultPointcutAdvisor(pointcut,after),
				aroundAdvisor = new DefaultPointcutAdvisor(pointcut2,around);
		
		FooService fooService = new FooServiceImpl();
		ProxyFactory factory = new ProxyFactory(fooService);
		
		//可以指定advisor的order值来指定顺序
		factory.addAdvisors(new DefaultPointcutAdvisor[]{afterAdvisor,aroundAdvisor,beforeAdvisor});
		FooService service = (FooService)factory.getProxy();
		service.action();
	}
	
	//手动的方式生成代理
	@Test@Ignore
	public void testSpringBasicAOP(){
//		BasicConfigurator.configure();
		try{
			ApplicationContext context =  new ClassPathXmlApplicationContext("springaop/spring-aop-basic.xml");
//			FooService service = (FooService)context.getBean("fooService");
//			service.action();
			
			FooService service = (FooService)context.getBean("fooProxy");
			service.action();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//自动代理
	@Test
	public void autoProxyTest(){
		try{
			ApplicationContext context =  new ClassPathXmlApplicationContext("springaop/spring-aop-auto-proxy.xml");
			FooService service = (FooService)context.getBean("fooService");
			service.action();
			service.say();
			DefaultAdvisorAutoProxyCreator def;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
