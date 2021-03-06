package com.kevin.learning.spring.transaction;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanSelfAwareBeanPostProcessor implements BeanPostProcessor{

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanSelfAware){
			BeanSelfAware service = (BeanSelfAware)bean;
			service.setSelf(bean);
		}
		return bean;
	}

}
