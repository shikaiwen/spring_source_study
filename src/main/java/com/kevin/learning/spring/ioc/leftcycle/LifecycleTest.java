package com.kevin.learning.spring.ioc.leftcycle;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
	
	@Test@Ignore
	public void test1(){
		
//		reader.registerBeanDefinitions(doc, resource)
//		DefaultAdvisorAutoProxyCreator d;
//		ResourceLoader loader = new DefaultResourceLoader();
//		Resource res = loader.getResource("spring-ioc-lifecycle.xml");
		XmlBeanFactory f;
		ApplicationContext context =  new ClassPathXmlApplicationContext("springioc/spring-ioc-lifecycle.xml");
		T1 t = (T1)context.getBean("t1");
		logger.debug(t.toString());
	}
	
	@Test
	public void test2(){
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		int count = reader.loadBeanDefinitions(new ClassPathResource("springioc/spring-ioc-lifecycle.xml"));
		T1 t1 = factory.getBean(com.kevin.learning.spring.ioc.leftcycle.T1.class);
		logger.debug("beanDefinition count : " +count);
		System.out.println(t1);
	}
	
}
