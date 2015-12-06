package com.kevin.learning.spring.ioc;

import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.kevin.learning.spring.aop.AopInterceptTest;
import com.kevin.learning.spring.ioc.leftcycle.T1;

@javax.xml.bind.annotation.XmlRootElement
public class InterfaceAndClassLearn {

	
	BeanFactory b1;
	
	static URL u = ClassLoader.getSystemResource("log4j.properties");
	static{
		PropertyConfigurator.configure(u);
	}
	
	static Logger logger = LoggerFactory.getLogger(AopInterceptTest.class);
	
	@Test
	public void t1() throws URISyntaxException{
		
		ResourceLoader r = new DefaultResourceLoader(); 
		Resource resource = r.getResource("classpath:tt.xml");
		try{
			
			URL url = Thread.currentThread().getContextClassLoader().getResource("springioc/spring-ioc-lifecycle.xml");
			Resource res = new FileSystemResource( url.getPath() );
			BeanFactory factory = new XmlBeanFactory(res);
			T1 t1 = factory.getBean(com.kevin.learning.spring.ioc.leftcycle.T1.class);
			System.out.println(t1);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
