package com.kevin.learning.mvc.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 值得研究的问题：一个类编译时包含了其他第三方的类，但是发布到tomcat中是没有包含这个第三方jar包，启动时
 * WebApp的classLoader初始化我们的主类时可以正常初始化，但也报出了错误说第三方类未加载到，但是程序没有结束
 * 
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

	//Freemarker的ExceptionHandler 在Configuration中有说明
	
	
	static class User{
		
		private String nk = "dfa";
		private int age = 100;
		
	}
	
	View view;
	DispatcherServlet dds;
	
//	RunWith runwi;
	
	
	public static void main(String[] args) throws TemplateModelException {
		BeansWrapper bw = new BeansWrapper();
		Map m = new HashMap();
		m.put("username", "kevin");
		TemplateModel tm = bw.wrap(m);
		
		
	}
	
	@RequestMapping("/viewtest")
	public ModelAndView viewTest(){
		ModelAndView mav = new ModelAndView("testview.html");
		mav.addObject("username", "kevin");
		mav.addObject("proLan", "java");
		
		User u  = new User();
		mav.addObject("user", u);
		
		HashSet hs = new HashSet();
		
		return mav;
	}
	
	
	public ViewLearning() {
		System.out.println("dfdf");
	}
	
	
}
