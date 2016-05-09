package com.kevin.learning.mvc.view;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.support.RequestContext;

import com.kevin.learning.spring.ioc.UserInfo;

import freemarker.core.Environment;
import freemarker.core.Environment.Namespace;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpRequestParametersHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelIterator;

public class FreemarkerDebugLabel implements TemplateDirectiveModel {

	
	public static void main(String[] args) throws Exception, SecurityException {
		
//		Object obj = new Object();
//		System.out.println( obj instanceof Integer );
//		if(true)return;
		
		UserInfo uInfo = new UserInfo();
		Class c = uInfo.getClass();
		Field f = c.getDeclaredField("name");
		f.setAccessible(true);
//		Field f = c.getField("name");
		Object result = f.get( uInfo );
		
		
		
		Field [] fields = uInfo.getClass().getDeclaredFields();
		
//		System.out.print(uInfo);
		inspectObject( uInfo,  1 );
		
//		System.out.println(uInfo);
	}
	
	
	public static void inspectObject(Object rootObj,int tabCount) throws Exception, IllegalAccessException{

		
		
		Class c = rootObj.getClass();
		Field [] fields = c.getDeclaredFields();
		if(fields == null || fields.length==0) return;
		
		for(int i = 0;i < fields.length ;i++){
			
			Field f = fields[i];
			f.setAccessible(true);
			
			String fName = f.getName();
			Object fVal = f.get(rootObj);
			
//			System.out.println( fVal instanceof Integer );
			
			if(fVal instanceof String ||
					fVal instanceof Integer || 
					fVal instanceof Long || 
					fVal instanceof Double){
				
				
				System.out.println( getNTab(tabCount) + fName + "=" + fVal);
				
				
			}else{
				
				int curT = tabCount + 1;
				inspectObject(fVal, curT);
				
				
			}
			
		}
		
		
	}
	
	
	public static String getNTab(int count){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<count;i++){
			sb.append("\t");
		}
		return sb.toString();
	}
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {

		Namespace sp = env.getCurrentNamespace();
		TemplateCollectionModel spKeys = sp.keys();
		Map spMap = sp.toMap();
		TemplateCollectionModel spVal = sp.values();
		
		
		ObjectWrapper wrapper = env.getObjectWrapper();
		
		env.getArithmeticEngine();
		Set set = env.getKnownVariableNames();
		
		TemplateHashModel rootModel = env.getDataModel();
		
		Set sharedVars = env.getConfiguration().getSharedVariableNames();
		
		Object requestParametersModel = rootModel.get("RequestParameters");
		
		if(requestParametersModel instanceof HttpRequestParametersHashModel){
			
			HttpRequestParametersHashModel reqHashModel = (HttpRequestParametersHashModel) requestParametersModel;
			
			TemplateCollectionModel collectionModel = reqHashModel.keys();
			
			System.out.println("HttpRequestParametersHashModel参数:");
			for(TemplateModelIterator  keyIter= collectionModel.iterator();keyIter.hasNext();){
				
				TemplateModel paramName = keyIter.next();
				
				System.out.println( "\t" + paramName.toString() 
				+ ":" + reqHashModel.get(paramName.toString()));
				
			}
			
		}
		
		Object requestAttributeObject = rootModel.get("Request");
		
		
		
		if( requestAttributeObject instanceof HttpRequestHashModel ){
			
			
			System.out.println("HttpRequestHashModel参数:");
			HttpRequestHashModel reqAttrs = (HttpRequestHashModel)requestAttributeObject;
			
//			这个对象可以获取HttpServletRequest
			TemplateCollectionModel collectModel = reqAttrs.keys();
			
			for(TemplateModelIterator iter=collectModel.iterator();iter.hasNext();){
				
				TemplateModel modelKey = iter.next();
				
				TemplateModel modelVal = reqAttrs.get( modelKey.toString() );

				
				
			
				
				
				
				
				
				
				System.out.println("\t"+modelKey  + ":" + modelVal);
				
			}
			
		}
		
		
		
		
		
		
		
		for(Iterator iter=set.iterator();iter.hasNext();){
			Object key = iter.next();

			TemplateModel tModel = rootModel.get(key.toString());
			
			if(sharedVars.contains(key )) continue;
			
			
			
			if("springMacroRequestContext".equals(key) && tModel instanceof RequestContext){
				RequestContext reqParams = (RequestContext)tModel;
			}
			

			
			if("JspTaglibs".equals(key)){
				TaglibFactory reqParams = (TaglibFactory)tModel;
			}
			
			if("Session".equals(key)){
				HttpSessionHashModel reqParams = (HttpSessionHashModel)tModel;
			}
			
			if("Application".equals(key)){
				ServletContextHashModel reqParams = (ServletContextHashModel)tModel;
			}
			
			
			
//			System.out.println( key + ":" +  tModel.toString());
			
		}
		
//		env.getConfiguration().getSharedVariableNames() ClassUtil.getFTLTypeDescription(TemplateModel)
		
	}

	//Environment#getObjectWrapper()
	//TemplateDirectiveModel
	



}
