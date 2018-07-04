window.socket=null;
window.stompClient=null;
window.webClientUserId=null;

$(function(){
	 //session取值
	//var aa=[[${session.merchantEntityKey.userId}]];		
	 alert(webClientUserId);
	// 建立连接对象（还未发起连接）
		socket = new SockJS("http://192.168.111.18:8082/webSocketServer");

	    // 获取 STOMP 子协议的客户端对象
		window.stompClient = Stomp.over(socket);

	    // 向服务器发起websocket连接并发送CONNECT帧
	    stompClient.connect(
	        {
	            name: webClientUserId // 携带客户端信息
	        },
	        function connectCallback(frame) {
	            // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
	            //alert("连接成功");    
	            subscribe();
	        },
	        function errorCallBack(error) {
	            // 连接失败时（服务器响应 ERROR 帧）的回调方法
	            //alert("连接失败");
	        }
	    );
	}
});

//订阅消息
function subscribe() { 
     stompClient.subscribe('/user/queue/message', function (response) {
    	alert(response); 
    });
}