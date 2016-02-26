package com.kevin.learning.spring.transaction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.ResourceHolder;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;
import org.springframework.transaction.support.TransactionTemplate;

public class InterfaceAndClass {

	PlatformTransactionManager  ptm;
	TransactionDefinition td;
	TransactionStatus ts;
	TransactionAttribute ta;
	
	DefaultTransactionDefinition dtd;
	DefaultTransactionAttribute dta;
	DefaultTransactionStatus dts;
	
	
	RuleBasedTransactionAttribute rta;
	
	InitializingBean ib;
	
	TransactionCallback tcb;
	TransactionTemplate tt;
	
	TransactionSynchronization tsn;
	TransactionSynchronizationManager tsm;
	TransactionSynchronizationUtils tsu;
	
	AbstractPlatformTransactionManager aptm;
	
	ResourceHolder rh;
	ConnectionHolder ch;
	DataSourceUtils dsu;
}
