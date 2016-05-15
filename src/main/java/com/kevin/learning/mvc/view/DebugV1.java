package com.kevin.learning.mvc.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.support.ServletContextScope;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpRequestParametersHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelIterator;

public class DebugV1 {

	static final String nodeName = "name";
	
	
	public static void main(String[] args) throws Exception {
		
		ObjectWrapper wrapper = ObjectWrapper.DEFAULT_WRAPPER;
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		HttpRequestHashModel reqAttr = new HttpRequestHashModel(request,wrapper);
		
		User user = new User(20,"kkk");
		List list  =  new ArrayList();
		
		list.add(new User(50,"bbbb"));
		list.add(1000);
		
		user.setObj( list );
		
		request.setAttribute("user", user);
		request.setAttribute("address", "广东省深圳市看看地方领导");
		
		Map followMap = new HashMap();
		followMap.put("date", new Date());
		followMap.put("userId", "u0--134");
		followMap.put("userName", "经纪人A");
		
		request.setAttribute("followMap", followMap);
		
		JSONObject requestJSON = handleHttpRequestHashModel(reqAttr);
		String result = JSON.toJSONString( requestJSON  );
		System.out.println(result);
	}
	
	//初始化request attribute
	static void initHttpRequestHashModel(HttpServletRequest request){
		User user = new User(20,"kkk");
		List list  =  new ArrayList();
		
		list.add(new User(50,"bbbb"));
		list.add(1000);
		user.setObj( list );
		
		request.setAttribute("user", user);
		request.setAttribute("address", "广东省深圳市看看地方领导");
		Map followMap = new HashMap();
		followMap.put("date", new Date());
		followMap.put("userId", "u0--134");
		followMap.put("userName", "经纪人A");
		
		request.setAttribute("followMap", followMap);
		
	}
	
	//初始化request parameter
	static HttpServletRequest initHttpRequestParametersHashModel(HttpServletRequest request){
		MockHttpServletRequest mockReq = new MockHttpServletRequest();
		mockReq.setParameter("phone", "18673166174");
		mockReq.setParameter("username", "施凯文");
		mockReq.setParameter("type", "偶看");
		return mockReq;
	}
	
	
	
	// request parameter
	public static JSONObject handleHttpRequestParametersHashModel(Object obj) throws Exception{
		
		JSONObject json = new JSONObject();
		
		HttpRequestParametersHashModel reqParam = (HttpRequestParametersHashModel)obj;
		json.put("name", "HttpRequestParametersHashModel");
		JSONArray jArr = new JSONArray();
		
		json.put("children",  jArr);
		
		TemplateCollectionModel keys = (TemplateCollectionModel)reqParam.keys();
		TemplateModelIterator  iter = keys.iterator();
		
		for(;iter.hasNext();){
			
			JSONObject pJson = new JSONObject();
			String pName = iter.next().toString();
			TemplateModel tmVal = reqParam.get( pName );
			
			pJson.put( "name", pName + "=" +tmVal);
			
			jArr.add( pJson );
			
		}
		
		return json;
	}
	
	/**
	 * 这里session可能为空，访问了controller的请求为什么session也能为空，测试发现只有显示的往session中放值
	 * session才不为空，session创建机制
	 */
	// session attribute
	public static JSONObject handleHttpSessionHashModel(Object obj) throws Exception{
		
		JSONObject json = new JSONObject();
		HttpSessionHashModel sessionModel = (HttpSessionHashModel)obj;
		
		Field sf = sessionModel.getClass().getDeclaredField("session");
		sf.setAccessible(true);
		HttpSession session = (HttpSession)sf.get(sessionModel);
		
		if(session == null){
			json.put("name",  "HttpSessionHashModel = null");
			return json;
		}
		
		Enumeration<String> attrEnum = session.getAttributeNames();
		
		JSONObject sessionAttrAllJSON = new JSONObject();
		sessionAttrAllJSON.put( nodeName ,  "HttpSessionHashModel" );
		
		JSONArray sessionAttrChildren  = new JSONArray();
		sessionAttrAllJSON.put("children", sessionAttrChildren );

		while(attrEnum.hasMoreElements()){
			
			String attr = attrEnum.nextElement();
			Object attrVal = session.getAttribute(attr);
			
//			if("springMacroRequestContext".equals(attr)) continue;
//			if("request".equals(attr)) continue;
//			if( attr.startsWith( "org.springframework.web" ) ) continue;
			
			JSONObject attrJSON = new JSONObject();
			if(attrVal == null) {
				
				attrJSON.put(nodeName, attr+"="+null);
				
			}else if(isSimpleType(attrVal)){
				
				attrJSON.put(nodeName, attr + "=" + attrVal +  "   < " + attrVal.getClass().getSimpleName() +">");
				
			}else{
				attrJSON = handleRecursive( attrVal )   ;
				//复杂对象node名称从这里设置
				attrJSON.put(nodeName, attr);
			}
			
			sessionAttrChildren.add(  attrJSON  );
		}
		return sessionAttrAllJSON;
	}
	
	
	public static JSONObject handleServletContextHashModel(Object obj) throws Exception{
		
		ServletContextHashModel contextModel = (ServletContextHashModel)obj;
		
		Field f = contextModel.getClass().getDeclaredField("servletctx");
		f.setAccessible(true);
		ServletContext context = (ServletContext)f.get(contextModel);
		context.setAttribute("pageSize", 1000);
		context.setAttribute("pageNo", 500);
		
		String str =
					"org.springframework.web.context.support.ServletContextScope,"
//						+"org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet,"
						+"org.springframework.web,"
						+"org.apache.tomcat.InstanceManager,"
						+"org.apache.catalina.jsp_classpath,"
						+"javax.websocket.server.ServerContainer,"
						+"org.apache.tomcat.util.scan.MergedWebXml,"
						+"javax.servlet.context.tempdir,"
						+"org.apache.catalina.resources,"
						+"org.apache.tomcat.JarScanner,"
						+"org.apache.jasper.compiler.TldLocationsCache,"
						+"org.apache";
		
		Enumeration<String> attrEnum = context.getAttributeNames();
		JSONObject sessionAttrAllJSON = new JSONObject();
		sessionAttrAllJSON.put( nodeName ,  "ServletContextHashModel" );
		
		JSONArray contextAttrChildren  = new JSONArray();
		sessionAttrAllJSON.put("children", contextAttrChildren );

		while(attrEnum.hasMoreElements()){
			
			String attr = attrEnum.nextElement();
			Object attrVal = context.getAttribute(attr);
			
			String [] resultStr = str.split(",");
			boolean innerAttr = false;
			for(int i =0;i<resultStr.length;i++){
				String cur = resultStr[i];
				if(cur.equals(attr) || attr.startsWith(cur)){
					innerAttr = true;
					break;
				}
			}
			
			if(innerAttr) continue;
			
			JSONObject attrJSON = new JSONObject();
			System.out.println(attr );
//			if(true)continue;
			if(attrVal == null) {
				attrJSON.put(nodeName, attr+"="+null);
			}else if(isSimpleType(attrVal)){
				attrJSON.put(nodeName, attr + "=" + attrVal +  "   < " + attrVal.getClass().getSimpleName() +">");
			}else{
				attrJSON = handleRecursive( attrVal )   ;
				//复杂对象node名称从这里设置
				attrJSON.put(nodeName, attr);
			}
			
			contextAttrChildren.add(  attrJSON  );
		}
		return sessionAttrAllJSON;
	}
	
	// request attribute 
	public static JSONObject handleHttpRequestHashModel(Object obj) throws Exception{
		
		HttpRequestHashModel reqAttr = (HttpRequestHashModel)obj;
		
		HttpServletRequest request = reqAttr.getRequest();
		
//		================ init data 
		initHttpRequestHashModel(request);
//		================ init data 		
		
		Enumeration<String> attrEnum = request.getAttributeNames();
		
		JSONObject reqAttrAllJSON = new JSONObject();
		reqAttrAllJSON.put( nodeName ,  "HttpRequestHashModel" );
		
		JSONArray reqAttrChildren  = new JSONArray();
		reqAttrAllJSON.put("children", reqAttrChildren );
	
		while(attrEnum.hasMoreElements()){
			
			String attr = attrEnum.nextElement();
			Object attrVal = request.getAttribute(attr);
			
			if("springMacroRequestContext".equals(attr)) continue;
			if("request".equals(attr)) continue;
			if( attr.startsWith( "org.springframework.web" ) ) continue;
			
			JSONObject attrJSON = new JSONObject();
			if(attrVal == null) {
				
				attrJSON.put(nodeName, attr+"="+null);
				
			}else if(isSimpleType(attrVal)){
				
				attrJSON.put(nodeName, attr + "=" + attrVal +  "   < " + attrVal.getClass().getSimpleName() +">");
				
			}else{
				attrJSON = handleRecursive( attrVal )   ;
				//复杂对象node名称从这里设置
				attrJSON.put(nodeName, attr + " <"+attrVal.getClass().getSimpleName()+">");
			}
			
			reqAttrChildren.add(  attrJSON  );
		}
		return reqAttrAllJSON;
	}
	
	
	public static JSONObject handleRecursive(Object obj) throws Exception{
		
		if( obj instanceof List){
			
			return handleTypeList(obj);
			
		} else if(obj instanceof Map){
			
			return handleTypeMap(obj);
		}
		else{
			
			JSONObject json = new JSONObject();
			
			json.put(nodeName,  obj.getClass().getSimpleName() );
			
			JSONArray complexChildren = new JSONArray();
			
			json.put("children", complexChildren);
			
			Field [] fields = obj.getClass().getDeclaredFields();
			for(int i =0; i < fields.length;i++ ){
				
				Field f = fields[i];
				f.setAccessible(true);
				
				String fName = f.getName();
				Object fVal = f.get(obj);
				
				JSONObject cJSON = new JSONObject();
				if(isNull(fVal)){
					cJSON.put(nodeName,  fName +"=null" );
					
				}else if(isSimpleType(fVal)){
					
					cJSON.put(nodeName, fName + "=" + fVal + "<"+fVal.getClass().getSimpleName()+">" );
					
				}else{
					cJSON = handleRecursive(fVal);
					cJSON.put( nodeName , fName + " <" + fVal.getClass().getSimpleName() +'>');
				}
				complexChildren.add(cJSON);
			}
			return json;
		}
	}
	
	
	static JSONObject handleTypeMap(Object obj) throws Exception{
		
		Map map = (Map)obj;
		
		JSONObject json = new JSONObject();
		json.put("name", obj.getClass().getSimpleName());
		
		JSONArray jArr = new JSONArray();
		
		json.put("children",  jArr );
		
		Set keySet = map.keySet();
		for(Object key : keySet){
			
			Object mapVal = map.get(key);
			
			JSONObject mapJSON = new JSONObject();
			
			if(isNull(mapVal)){
				mapJSON.put(nodeName,  key  + " = null" );
			
			}else if(isSimpleType(mapVal)){
				
				mapJSON.put(nodeName,  key  + " = " + mapVal + " <"+mapVal.getClass().getSimpleName()+">" );
				
			}else{
				
				//复杂对象
				mapJSON = handleRecursive(mapVal);
				mapJSON.put(nodeName,   key  + " = " + mapVal + " <"+mapVal.getClass().getSimpleName()+">" );
				
			}
			jArr.add(mapJSON);
		}
		
		return json;
	}
	
	
	static JSONObject handleTypeList(Object obj) throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("name", obj.getClass().getSimpleName());
		
		JSONArray jArr = new JSONArray();
		
		json.put("children",  jArr );
		
		List list = (List)obj;
		
		for(int i = 0;i < list.size();i++){
			
			JSONObject listJSON = new JSONObject();
			
			Object listVal = list.get(i);
			
			if(isSimpleType(listVal)){
				
				listJSON.put(nodeName,  "["+ i +"] = " + listVal + "<"+listVal.getClass().getSimpleName() + ">");
				
			}else{
				
				//复杂对象
				listJSON = handleRecursive(listVal);
				listJSON.put(nodeName,   "["+ i +"] = " +listVal + "<"+listVal.getClass().getSimpleName() + ">" );
				
			}
			
			jArr.add(listJSON);
			
		}
		
		return json;
		
	}
	
	
	
	static JSONObject handleNull(Object obj){
		JSONObject json = new JSONObject();
		
		json.put( DebugV1.nodeName, "null");
		
		return json;
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
