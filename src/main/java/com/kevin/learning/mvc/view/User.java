package com.kevin.learning.mvc.view;

import java.io.Serializable;

class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	private Object obj;
	
	public User(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int age;
	private String name;
	
}