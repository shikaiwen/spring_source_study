package com.kevin.learning.spring.transaction;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class ProgrammingTransaction {

	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager txManager;
	private TransactionDefinition txDefinition;
	
	ApplicationContext context ;
	
	@Before
	public void before(){
		context = 
				new ClassPathXmlApplicationContext("springtran/spring-transaction.xml");
		txManager = (PlatformTransactionManager)context.getBean("txManager");
		jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
	}
	
	@Test
	public void t1(){
		traceeTransaction(false,false,false);	
	}
	
	public void traceeTransaction(boolean throw1 , boolean throw2,boolean reThrow){
		
		int propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED;
		txDefinition = new DefaultTransactionDefinition(propagationBehavior);
		
		TransactionStatus txStatus = txManager.getTransaction(txDefinition);
		try{
			int count = jdbcTemplate.update("update t_acct set balance = balance + 3");
			if(throw1) throw new Exception(" throw First ");
			updateAcct(throw2, reThrow);
			txManager.commit(txStatus);
		}catch(Exception e){
			txManager.rollback(txStatus);
			throw new RuntimeException(e);
		}
		
	}
	
	public void updateAcct(boolean isThrow , boolean reThrow){
		txDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus txStatus = txManager.getTransaction(txDefinition);
		try{
			int count = jdbcTemplate.update("update t_acct set frozen_amount = frozen_amount + 2");
			if(isThrow) throw new RuntimeException(" throw second ");
			txManager.commit(txStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
