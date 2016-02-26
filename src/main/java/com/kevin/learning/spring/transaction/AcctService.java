package com.kevin.learning.spring.transaction;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author 
 */
public class AcctService implements BeanSelfAware{

	AcctService self;
	
	@Resource
	TransactionTemplate transactionTemplate;
	
	@Resource
	JdbcTemplate jdbcTemplate;
	public int updateBlance( Integer account){
		final Integer finalAccount = account;
		transactionTemplate.execute(new TransactionCallback<Integer>(){
			public Integer doInTransaction(TransactionStatus status) {
				int res = jdbcTemplate.update("update t_acct set balance = balance + " + finalAccount);
//				status.setRollbackOnly();
				if(1 == 1) throw new RuntimeException("3434");
				return res;
			}
		});
		
		return 0;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int update2(Integer amount){
		int res = jdbcTemplate.update("update t_acct set balance = balance + " + amount);
		
		self.innerUpdate(amount);
		return res;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int innerUpdate(Integer amount){
		
		int res = jdbcTemplate.update("update t_acct set balance = balance + " + amount);
		return res;
	}
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setSelf(Object obj) {
		if(obj instanceof AcctService){
			this.self = (AcctService)obj;
		}
	}


	
	
}
