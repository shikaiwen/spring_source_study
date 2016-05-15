package com.kevin.learning.mvc.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * 接收外部服务器发送的请求
 * @author root
 *
 */
public class RequestHandlerServlet extends HttpServlet{

	ThreadLocal<HttpServletRequest> requestLocalHolder = new ThreadLocal<HttpServletRequest>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		requestLocalHolder.set(req);
		
		String method = req.getMethod();
		Map<String,String[]> paramMap = req.getParameterMap();
		
		Map<String,Object> respMap = new HashMap<String,Object>();
		
		String reqJsonStr = JSON.toJSONString( paramMap );
		
		Map <String,SocketDataHolder> connectedBounds = HelloWebSocket.connectedSockets;
		
		respMap.put("method", method);
		respMap.put("paramContent", paramMap);
		
		String respContent = JSON.toJSONString( respMap );
		
		
		sendTextToAllClient(respContent);
	}
	
	public void sendTextToAllClient(String content) throws IOException{
		
		Map <String,SocketDataHolder> connectedBounds = HelloWebSocket.connectedSockets;
		
		for(String key : connectedBounds.keySet()){
			
			SocketDataHolder holder = connectedBounds.get(key);
			
			HelloMessageInbound helloBound = (HelloMessageInbound)holder.getInbound();
			helloBound.sendText(content);
			
		
		}
	}
	
	
	public static void main(String[] args) {
		String url = "http://localhost:9090/springlearning/notify?username=thisisdog&nation=USB";
		new RequestHandlerServlet().requestUrl(url);
	}
	
	public void requestUrl(String url){
		
		HttpServletRequest request = requestLocalHolder.get();
		boolean isPost = "POST".equals("POST");
		
		HttpURLConnection httpURLConnection;
		try {
			httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
			   httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");  
		        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
		        httpURLConnection.setDoInput(true);    //true表示允许获得输入流,读取服务器响应的数据,该属性默认值为true    
		        httpURLConnection.setDoOutput(true);   //true表示允许获得输出流,向远程服务器发送数据,该属性默认值为false    
		        httpURLConnection.setUseCaches(false); //禁止缓存    
		        httpURLConnection.setReadTimeout(30000);    //30秒读取超时    
		        httpURLConnection.setConnectTimeout(30000); //30秒连接超时    
		        httpURLConnection.setRequestMethod(isPost ? "POST" :"GET");    
		          
		        if(isPost){
		        	String postData = getPostData(request);
		        	httpURLConnection.setRequestProperty("Content-Length", postData.length() + "");  
			        OutputStream writer = httpURLConnection.getOutputStream();  
			        writer.write(postData.getBytes("UTF-8"));  
			        writer.flush();  
		        }
		          

		          
		        InputStream in = httpURLConnection.getInputStream();    
		        ByteArrayOutputStream buffer = new ByteArrayOutputStream();    
		        byte[] buff = new byte[1024];    
		        int len = -1;    
		        while((len= in.read(buff)) != -1){    
		            buffer.write(buff, 0, len);    
		        }    
		        
		        String result  = buffer.toString("utf-8"); 
		        
		        //将收到的数据
		        
		        System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	      
		  
	}
	
	public String getPostData(HttpServletRequest request){
		Map<String,String[]> map = request.getParameterMap();
		Map<String,Object> respMap = new LinkedHashMap<String,Object>();
		for(String key : map.keySet()){
			
			String [] pArr = map.get(key);
			if(pArr.length == 0 || pArr.length == 1){
				respMap.put(key, pArr.length == 0 ? "" : pArr[0]);
			}else{
				respMap.put(key, pArr);
			}
		}

		return JSON.toJSONString(respMap);
	}
}
