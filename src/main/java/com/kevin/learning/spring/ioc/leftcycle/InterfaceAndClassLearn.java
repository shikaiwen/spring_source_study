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

	BeanFactory bf;//һ���ݽӿ� 1��
	
	ListableBeanFactory lbf; //�����ݽӿ�3��
	AutowireCapableBeanFactory acbf;
	HierarchicalBeanFactory hbf;
	
	ConfigurableBeanFactory cbf;	//�����ݽӿ� 1��,ʵ����SingltonBeanRegistry
	
	ConfigurableListableBeanFactory clbf; //�Ľ��ݽӿ� 1��
	
	AbstractBeanFactory abfc; //ʵ���������ӿ� ConfigurableBeanFactory
	
	AbstractAutowireCapableBeanFactory aacbf;
	
	DefaultListableBeanFactory dlbf; //ʵ�� ConfigurableListableBeanFactory,���� BeanDefinitionRegistry
	BeanDefinitionRegistry bdr;
	
	XmlBeanFactory xbf;//�̳� DefaultListableBeanFactory;
	
	
	//ʵ���ಿ��
	SimpleAliasRegistry sar; //ʵ���� AliasRegistry interface
	DefaultSingletonBeanRegistry dsbr; //�������� SignletonBeanRegistry
	
	FactoryBean fb;
	FactoryBeanRegistrySupport fbrs; //�Թ���Bean��FactoryBean��֧�֣�
	AbstractBeanFactory abf;
	
}
