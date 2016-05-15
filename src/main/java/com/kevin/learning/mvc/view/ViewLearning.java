package com.kevin.learning.mvc.view;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import freemarker.template.TemplateModel;

/**
 * 值得研究的问题：一个类编译时包含了其他第三方的类，但是发布到tomcat中是没有包含这个第三方jar包，启动时
 * WebApp的classLoader初始化我们的主类时可以正常初始化，但也报出了错误说第三方类未加载到，但是程序没有结束
 * 经测试，如果将第三方类声明为成员变量就不行了
 * 结论猜想：声明为成员则是告诉虚拟机一定要成功加载，否则虚拟机结束
 * 
 * 
 * @author root
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Controller
public class ViewLearning {

	
	View view;
	DispatcherServlet dds;
	
//	RunWith runwi;
	
	public ViewLearning() {
		System.out.println("dfdf");
		TemplateModel tml;
	}
	
	@RequestMapping("/kdebug")
	public ModelAndView index(HttpServletRequest request){
		request.getSession().setAttribute("s1", "asdf");
		request.getSession().setAttribute("s2", 254);
		request.getSession().setAttribute("s3",   new User(30,"打开的"));
		return new ModelAndView("index2");
	}
	
}
