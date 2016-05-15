package com.kevin.learning.mvc.view;

import org.apache.catalina.websocket.StreamInbound;

public class SocketDataHolder {

	
	private StreamInbound inbound;
	
	//本地地址
	private String inUrl ;

	//回调远程地址
	private String outUrl;
	
	public StreamInbound getInbound() {
		return inbound;
	}

	public void setInbound(StreamInbound inbound) {
		this.inbound = inbound;
	}

	public String getInUrl() {
		return inUrl;
	}

	public void setInUrl(String inUrl) {
		this.inUrl = inUrl;
	}

	public String getOutUrl() {
		return outUrl;
	}

	public void setOutUrl(String outUrl) {
		this.outUrl = outUrl;
	}


	
	
	
}
