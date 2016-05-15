<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>WebSocket demo</title>
    <%
	String path = request.getContextPath();
%>
<style>
body {padding: 40px;}
#outputPanel {margin: 10px;padding:10px;background: #eee;border: 1px solid #000;/* min-height: 300px; */}
</style>

<link href="<%=path%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=path%>/jquery.js"></script>
<script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<input type="text" id="messagebox" size="60" />
<input type="button" id="buttonSend" value="Send Message" />
<input type="button" id="buttonConnect" value="Connect to server" />
<input type="button" id="buttonClose" value="Disconnect" />

<br>
<% out.println("Session ID = " + session.getId()); %>
<div id="outputPanel"></div>
<h2>收到的请求</h2>
<table id="dataTable" width="80%" class="table table-bordered">

	<tr id="headTr">
	<td width="10%">请求方式</td>
	<td colspan="2">收到的数据:</td>
	</tr>
	<tr>
	<td width="10%">GET</td>
	<td>{username="kevin",age=44}</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	var infoPanel = document.getElementById('outputPanel'); // 输出结果面板
	var textBox = document.getElementById('messagebox');	// 消息输入框
	var sendButton = document.getElementById('buttonSend');	// 发送消息按钮
	var connButton = document.getElementById('buttonConnect');// 建立连接按钮
	var discButton = document.getElementById('buttonClose');// 断开连接按钮
	// 控制台输出对象
	var console = {log : function(text) {infoPanel.innerHTML += text + "<br>";}};
	// WebSocket演示对象
	var demo = {
		socket : null, 	// WebSocket连接对象
		host : '',		// WebSocket连接 url
		connect : function() { 	// 连接服务器
			window.WebSocket = window.WebSocket || window.MozWebSocket;
			if (!window.WebSocket) {	// 检测浏览器支持
				console.log('Error: WebSocket is not supported .');
				return;
			}
			this.socket = new WebSocket(this.host); // 创建连接并注册响应函数
			this.socket.onopen = function(){console.log("websocket is opened .");};
			
			//收到服务器发送的数据
			this.socket.onmessage = function(message) {
				console.log(message.data);
				
				handleReceivedData(message.data);
				
				};
			this.socket.onclose = function(){
				console.log("websocket is closed .");
				demo.socket = null; // 清理
			};
		},
		send : function(message) {	// 发送消息方法
			if (this.socket) {
				this.socket.send(message);
				return true;
			}
			console.log('please connect to the server first !!!');
			return false;
		}
	};
	// 初始化WebSocket连接 url
	demo.host=(window.location.protocol == 'http:') ? 'ws://' : 'wss://' ;
	demo.host += window.location.host + '/springlearning/websocket/say';
	// 初始化按钮点击事件函数
	sendButton.onclick = function() {
		var message = textBox.value;
		if (!message) return;
		if (!demo.send(message)) return;
		textBox.value = '';
	};
	connButton.onclick = function() {
		if (!demo.socket) 	demo.connect();
		else console.log('websocket already exists .');
	};
	discButton.onclick = function() {
		if (demo.socket) demo.socket.close();
		else  console.log('websocket is not found .');
	};
	
	
function handleReceivedData(data){
	var tr = $("#headTr").clone();
	var json = $.parseJSON(data);
	var method = json.method;
	var respStr = JSON.stringify(json.paramContent);
	$(tr).find("td").eq(0).html(method);
	$(tr).find("td").eq(1).html(respStr);
	
	$(tr).appendTo($("#dataTable"));
}	
	
</script>



</html>