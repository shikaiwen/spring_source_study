package com.kevin.learning.mvc.view;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@SuppressWarnings("deprecation")
public class HelloWebSocket extends WebSocketServlet{

	private static final long serialVersionUID = 2201155384170564229L;

	private final AtomicInteger connectionIds = new AtomicInteger(0);
	
	//key：sessionId
	public static Map<String,SocketDataHolder> connectedSockets = new HashMap<String,SocketDataHolder>();
	
	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		StreamInbound streamInbound = new HelloMessageInbound(
				connectionIds.getAndIncrement(), 
				sessionId
                );
		SocketDataHolder holder = new SocketDataHolder();
				holder.setInbound(streamInbound);
				
		connectedSockets.put(sessionId, holder);
		return streamInbound;
	}

	
	
	
}
