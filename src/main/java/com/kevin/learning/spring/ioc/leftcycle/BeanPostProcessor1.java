package com.kevin.learning.spring.ioc.leftcycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessor1 implements BeanPostProcessor{

	static Logger logger = LoggerFactory.getLogger(BeanPostProcessor1.class); 
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.debug("invoked ");
		return bean;
	}
	
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.debug("invoked ");
		return bean;
	}
}
