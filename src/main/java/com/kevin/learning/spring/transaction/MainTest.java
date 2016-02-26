package com.kevin.learning.spring.transaction;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	ApplicationContext context ;
	@Before
	public void before(){
		context = 
				new ClassPathXmlApplicationContext("springtran/spring-transaction.xml");
	}
	
	//采用编程的方式实现，好处是能够明确事务边界
	@Test@Ignore
	public void test1(){
		try{
			//奇怪的问题，不能通过注解注入
			context = 
					new ClassPathXmlApplicationContext("springtran/spring-transaction.xml");
//			Object ds = context.getBean("dataSource");
//			Object jdbcTemplate = context.getBean("jdbcTemplate");
//			Object transactionTemplate = context.getBean("transactionTemplate");
//			Object txManager = context.getBean("txManager");
			
			AcctService service = context.getBean(AcctService.class);
			service.updateBlance(1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void byTransactionProxy(){
//		AcctService service = (AcctService)context.getBean("acctServiceProxy");
		try{
//			Object obj = context.getBean("acctService");
//			Object obj = context.getBean("acctService");
			AcctService service = (AcctService)context.getBean("acctService");
			service.update2(2);
//			if(obj instanceof AcctService){
//				System.out.println("type get ");
//			}
//			System.out.println(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
