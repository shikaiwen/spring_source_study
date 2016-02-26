package com.kevin.learning.spring.ioc.leftcycle;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.support.FactoryBeanRegistrySupport;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.SimpleAliasRegistry;

public class InterfaceAndClassLearn {

	BeanFactory bf;//一阶梯接口 1个
	
	ListableBeanFactory lbf; //二阶梯接口3个
	AutowireCapableBeanFactory acbf;
	HierarchicalBeanFactory hbf;
	
	ConfigurableBeanFactory cbf;	//三阶梯接口 1个,实现了SingltonBeanRegistry
	
	ConfigurableListableBeanFactory clbf; //四阶梯接口 1个
	
	AbstractBeanFactory abfc; //实现了三级接口 ConfigurableBeanFactory
	
	AbstractAutowireCapableBeanFactory aacbf;
	
	DefaultListableBeanFactory dlbf; //实现 ConfigurableListableBeanFactory,还有 BeanDefinitionRegistry
	BeanDefinitionRegistry bdr;
	
	XmlBeanFactory xbf;//继承 DefaultListableBeanFactory;
	
	
	//实现类部分
	SimpleAliasRegistry sar; //实现了 AliasRegistry interface
	DefaultSingletonBeanRegistry dsbr; //是吸纳了 SignletonBeanRegistry
	
	FactoryBean fb;
	FactoryBeanRegistrySupport fbrs; //对工厂Bean（FactoryBean的支持）
	AbstractBeanFactory abf;
	
}
