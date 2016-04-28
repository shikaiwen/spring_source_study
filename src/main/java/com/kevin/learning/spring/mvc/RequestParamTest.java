package com.kevin.learning.spring.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * springMVC pattern使用 http://blog.csdn.net/kobejayandy/article/details/12690041
 * http://java2novice.com/spring-mvc/url-mapping-regex/
 * http://stackoverflow.com/questions/7841770/optional-path-variables-in-spring-mvc-requestmapping-uritemplate
 * @author root
 *
 *
 * 参数解析源码分析：http://www.chawenti.com/articles/23718.html
 */
@Controller
public class RequestParamTest {

	class MyServlet extends HttpServlet{
		
		@Override
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.service(req, res);
		}
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.service(req, resp);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(RequestMethod.DELETE.name());
		RequestParamTest d = new RequestParamTest();
		MyServlet my = d.new MyServlet();
	
		
	}
	
	@RequestMapping("")
	public void getAllVariable(HttpServletRequest req){
		
//		RequestContextHolder.getRequestAttributes()
//		RequestContextUtils.getWebApplicationContext(request)
		
		org.springframework.util.AntPathMatcher ma;
		
		HandlerMethodArgumentResolver argumentResovler ;
		HandlerMethodReturnValueHandler returnHandler;
		
		DispatcherServlet ds;
		
		req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		DefaultAnnotationHandlerMapping danno;
		HttpRequestHandlerAdapter helr;
		AnnotationMethodHandlerAdapter dfdfds;
		
		RequestMappingHandlerMapping re;
		
		HandlerMapping handlerMapping;
		WebApplicationObjectSupport webAppObjectSupport;
		AbstractHandlerMapping abMapping;
		AbstractUrlHandlerMapping abstractUrlHandlerMapping;
		
		AbstractHandlerMethodMapping handlerMethodMapping;
	}
}
