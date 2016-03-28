package com.kevin.learning.spring.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerMapping;

/**
 * springMVC pattern使用 http://blog.csdn.net/kobejayandy/article/details/12690041
 * http://java2novice.com/spring-mvc/url-mapping-regex/
 * @author root
 *
 *
 * 参数解析源码分析：http://www.chawenti.com/articles/23718.html
 */

public class RequestParamTest {

	@RequestMapping("")
	public void getAllVariable(HttpServletRequest req){
		org.springframework.util.AntPathMatcher ma;
		
		HandlerMethodArgumentResolver argumentResovler ;
		HandlerMethodReturnValueHandler returnHandler;
		
		req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	}
}
