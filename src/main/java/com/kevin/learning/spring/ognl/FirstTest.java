package com.kevin.learning.spring.ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ognl.Ognl;
import ognl.OgnlException;
//{data={"data2":[{"id":1,"name":"自己"},{"id":2,"name":"一个朋友"},{"id":3,"name":"一个司机"}]}, userList=[{"desc":"自己","name":"kevin","age":1},{"desc":"一个朋友","name":"yuqing","age":2},{"desc":"一个司机","name":"driver","age":3}]}
public class FirstTest {

	@Test
	public void t3() {
		User user = new User("kevin",1,"自己");
		List list = new ArrayList();
		list.add(11);
		try {
			
			Object val = Ognl.getValue("add(Math.random())", list);
			System.out.println(list);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}
	
	@Test@Ignore
	public void t2() throws OgnlException {
		
		String str = getJson();
		JSONObject rowPar = (JSONObject)JSON.parse(str);
		
		Map map = JSON.parseObject(str, Map.class);
		
		Object groupListObj = (Object)Ognl.getValue("data.data2[3]", map);
//		Ognl.getValue(expression, context, root)
		JSONArray groupList = (JSONArray)groupListObj;
//		groupList.getJSONArray(index);
	}
	
//	@Test
	public  String getJson(){
		Map map = new HashMap();	
		List<User> userList = new ArrayList(){{
			add(new User("kevin",1,"自己"));
			add(new User("yuqing",2,"一个朋友"));
			add(new User("driver",3,"一个司机"));
		}};
		
		final List<group> groupList = new ArrayList<group>(){{
			add(new group(1,"自己"));
			add(new group(2,"一个朋友"));
			add(new group(3,"一个司机"));
		}};
		
		map.put("userList", userList);
		
		map.put("data", new HashMap(){{
			put("data2",groupList);
		}});
		
		String resultStr = JSON.toJSONString(map, true);
		System.out.println(resultStr);
		return resultStr;
//		try {
//			Object val = Ognl.getValue("name2", map);
//			System.out.println(val);
//		} catch (OgnlException e) {
//			e.printStackTrace();
//		}
	}


	class User{
		private String name;
		private int age;
		private String desc;
		public User(String name, int age, String desc) {
			this.name = name;
			this.age = age;
			this.desc = desc;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	class group{
		private int id;
		private String name;
		public group(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int add(int i,int j){
			return i+ j;
		}
	}
}
