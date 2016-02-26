package com.kevin.learning.spring.aop.aspectj;

public class GoToWork {

	
	public String doWork(){
		System.err.println("i am doing work ");
		return "";
	}
	
	public String toToWorkAndReturn(Integer busCost){
		System.out.println("i am on bus  " );
		return "";
	}
}
