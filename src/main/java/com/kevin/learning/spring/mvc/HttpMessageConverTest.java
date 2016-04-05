package com.kevin.learning.spring.mvc;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.config.AnnotationDrivenBeanDefinitionParser;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * http://www.cnblogs.com/fangjian0423/p/springMVC-xml-json-convert.html
 * http://my.oschina.net/lichhao/blog/172562
 * @author Administrator
 *
 *https://help.github.com/articles/generating-an-ssh-key/
 *http://serverfault.com/questions/194567/how-do-i-tell-git-for-windows-where-to-find-my-private-rsa-key
 *
 */
public class HttpMessageConverTest {

	public static void test1(){
		HttpInputMessage input = null;
		HttpOutputMessage output = null;
		HttpMessageConverter converter;
		StringHttpMessageConverter strCon;


		HandlerMethodArgumentResolver res1;
		HandlerMethodReturnValueHandler res2;
		
		RequestResponseBodyMethodProcessor p;
		
		//解析 <mvc:annotation-driven/>
		AnnotationDrivenBeanDefinitionParser parser;
		
	}
}
