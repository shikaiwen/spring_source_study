package com.kevin.learning.mvc.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Map;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;

@SuppressWarnings("deprecation")
public class HelloMessageInbound extends StreamInbound{

    private String WS_NAME;  
    private final String FORMAT = "%s : %s";  
	private final String PREFIX = "ws_";
	private String sessionId = "";  
	
	public HelloMessageInbound(int id, String sessionId) {
		WS_NAME = PREFIX + id;
		this.sessionId = sessionId;
	}
	

	@Override
	protected void onOpen(WsOutbound outbound) {
		super.onOpen(outbound);
		   try {  
	            send("hello, my name is " + WS_NAME);  
	            send("session id = " + this.sessionId);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}
	
	@Override
	protected void onClose(int status) {
		super.onClose(status);
		Map<String,SocketDataHolder> connectedSockets = HelloWebSocket.connectedSockets;
		if(connectedSockets.containsKey(this.sessionId)){
			connectedSockets.remove(sessionId);
		}
	}
	
	
	@Override
	protected void onTextData(Reader reader) throws IOException {
		 char[] chArr = new char[1024];  
	        int len = reader.read(chArr);  
	        send(String.copyValueOf(chArr, 0, len)); 
	}

	 private void send(String message) throws IOException {  
	        message = String.format(FORMAT, WS_NAME, message);  
	        System.out.println(message);  
	        getWsOutbound().writeTextMessage(CharBuffer.wrap(message));  
	  }  
	
	 public void sendText(String message) throws IOException{
		 this.getWsOutbound().writeTextMessage( CharBuffer.wrap(message) );
	 }
	 
		@Override
		protected void onBinaryData(InputStream arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}


		public String getSessionId() {
			return sessionId;
		}
		
		
		
		
}
