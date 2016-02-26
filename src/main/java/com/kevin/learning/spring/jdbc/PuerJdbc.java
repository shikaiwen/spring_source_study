package com.kevin.learning.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PuerJdbc {

	Connection conn = null;
	@Before
	public void before() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
	}
	
	@Test@Ignore
	//https://dev.mysql.com/doc/connector-j/en/connector-j-reference-configuration-properties.html
	//关于url
	public  void getConn() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
	}
	
	/**
	 * 
	 * 注意，就算用rollback回滚了savepoint，但是一执行commit还是会提交
	 */
	@Test
	public void t2() throws Exception{
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		Savepoint sp1 = conn.setSavepoint();
		int record = stmt.executeUpdate("update t_acct set balance = balance + 1 where id = 1");
		conn.rollback(sp1);
		stmt.executeUpdate("update t_acct set frozen_amount = frozen_amount + 1 where id = 1 ");
		
		conn.commit();
		
		conn.setAutoCommit(true);
	}
}
