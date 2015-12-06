package com.kevin.learning.spring.ioc.leftcycle;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kevin.learning.spring.aop.AopInterceptTest;

/**
 * 
 * ApplicationContextAware 其实是通过ApplicationContextAwareProcessor这个BeanPostProcessor来进行设置的，参考源码
 * 
 */

public class LifecycleTest {
//	ApplicationContextAwareProcessor a;
	static URL u = ClassLoader.getSystemResource("log4j.properties");
	static{
		PropertyConfigurator.configure(u);
	}
	static Logger logger = LoggerFactory.getLogger(AopInterceptTest.class);
	
	@Test
	public void test1(){
		
		DefaultAdvisorAutoProxyCreator d;
		
		ApplicationContext context =  new ClassPathXmlApplicationContext("springioc/spring-ioc-lifecycle.xml");
		T1 t = (T1)context.getBean("t1");
		logger.debug(t.toString());
	}
}
