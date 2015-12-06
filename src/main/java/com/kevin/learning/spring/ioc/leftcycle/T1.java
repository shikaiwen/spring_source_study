package com.kevin.learning.spring.ioc.leftcycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class T1  implements HelloInterface{

	Logger logger = LoggerFactory.getLogger(T1.class);
	
	public String say() {
		logger.debug("say invoked ");
		return null;
	}

}
