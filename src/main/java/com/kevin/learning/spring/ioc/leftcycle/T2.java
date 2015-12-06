package com.kevin.learning.spring.ioc.leftcycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class T2 {

	static Logger logger = LoggerFactory.getLogger(T2.class);
	
	public void init(){
		logger.debug("init");
	}
}
