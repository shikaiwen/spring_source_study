package com.kevin.learning.spring.aop.entities;

public class FooServiceImpl implements FooService{
	public String action() {
		System.out.println("action");
		return null;
	}
	
	
	public String say() {
		System.out.println("say");
		return null;
	}
}