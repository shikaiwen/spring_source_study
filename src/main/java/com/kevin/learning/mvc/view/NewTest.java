package com.kevin.learning.mvc.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class NewTest {

	
	
	
	public static void main(String[] args) throws Exception {
		
		
		User user = new User(20,"kkk");
		List list  =  new ArrayList();
		list.add(0543);
		list.add(6, 8888);
		
		list.add(new User(50,"bbbb"));
		
		user.setObj( list );
		
//		Map m = new HashMap();
//		m.put("user", user);
//		m.put("local", "varia");
		
//		User uu = null;
//		System.out.println(uu.getClass());
		
		JSON j = handleRecursive(user,null);
		j.toJSONString();
		System.out.println(j);
		
//		List<String> nums = new ArrayList<String>();
//		
//		user.setObj(nums);
//		Class uClass = user.getClass();
//		Field [] fields = uClass.getDeclaredFields();
//		
//		
//		JSONObject rootJson = new JSONObject();
//		
//		for(int i =0; i < fields.length;i++){
//			Field f = fields[i];
//			f.setAccessible(true);
//			Object fieldVal = f.get(user);
//			
//			//显示格式 fieldName = fieldClassSimpleName
//			String name = f.getName();
//			String fValClassSimpleName = fieldVal.getClass().getSimpleName();
//		}
		
		
	}
	
	static JSON handleRecursive(Object obj,JSON root) throws Exception{
		
		if(root == null ) root = new JSONObject();
		
		
		
		
		if(obj instanceof String || obj instanceof Integer){
			
			JSONObject jObj = new JSONObject();
			String cName = obj.getClass().getSimpleName();
			
			if(root instanceof JSONObject){
				((JSONObject)root).put( "name" , cName + "=" + obj );
			}else if(root instanceof JSONArray){
				
				((JSONArray) root).add( jObj );
				
			}
			

		}else if(obj == null){
			
//			JSONObject jObj = new JSONObject(); 
//			
//			if(root instanceof JSONObject){
//				
//				((JSONObject)root).put( "name" , cName + "=" + obj );
//			}else if(root instanceof JSONArray){
//				
//				((JSONArray) root).add( jObj );
//				
//			}
			
		}else{
			
			
			Class objClass = obj.getClass();
			Field [] fields = objClass.getDeclaredFields();
			
			//是复杂对象，将simplename 放到 root中
			
			if(root instanceof JSONObject){
				((JSONObject) root ).put("name",  objClass.getSimpleName() );
			}else{
				
				JSONObject jo = new JSONObject();
				jo.put( "name" ,   obj.getClass().getSimpleName()  );
				((JSONArray)root).add(jo);
				
			}
			
			JSONArray jArr = new JSONArray();
			((JSONObject) root ).put("children", jArr  );
			
			for(int i =0; i < fields.length;i++){
				
				Field f = fields[i];
				f.setAccessible(true);
				String fName = f.getName();	
				Object fieldVal = f.get(obj);
				
				if(fieldVal == null){
					JSONObject nullObj = new JSONObject();
					nullObj.put("name",   fName + "=null");
					jArr.add( nullObj );
					continue;
				}
				
				if(fieldVal instanceof String || fieldVal instanceof Integer){
					
					JSONObject jObj = new JSONObject();
					String cName = fieldVal.getClass().getSimpleName();
					
					JSONObject jsonObj = new JSONObject();
					jsonObj.put( "name" , fName + "=" +  fieldVal);
					jArr.add( jsonObj  );
					
					continue;
				}
				
				if(fieldVal instanceof List){
					
					String sName = fName.getClass().getSimpleName();
					
					JSONObject listJsonObj = new JSONObject();
					listJsonObj.put( "name", fName+"="+sName  ) ;
					
					JSONArray listChildrenArr = new JSONArray();
					listJsonObj.put("children", listChildrenArr);
					
					Field sF = obj.getClass().getDeclaredField("size");
					sF.get(obj);
					
					List list = (List)fieldVal;
					
					
					
					for(int listi=0;listi<list.size();listi++){
						Object objs = list.get(listi);
						
						
						if(isSimpleType(objs)){
							
							JSONObject jObj = new JSONObject();
							
							if( isNull(objs)  ){
								jObj.put( "name",  "["+listi+"]=null" );
								
								listChildrenArr.add(jObj);
							}
							
							if(isSimpleType(objs)){
								
								jObj.put("name",  fieldVal + "<"+ objs.getClass().getSimpleName() + ">");
								
								listChildrenArr.add(jObj);
								
							}
							
							
						}
						
					}
					
					
					
				}
				
				handleRecursive(fieldVal,jArr);
				
			}
		}

		return root;
	}
	
	static boolean isNull(Object obj)  {
		return obj == null;
	}
		
	
	static boolean isSimpleType(Object obj){
		if(obj instanceof String || obj instanceof Integer || obj instanceof Long  || obj instanceof Float 
				|| obj instanceof Double ){
			return true;
		}
		return false;
	}
}



class User{
	
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