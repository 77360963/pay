var webStockPath=null;
var webClientUserId=null;
var socket=null;
var stompClient=null;

$(function(){
     socket = new SockJS(webStockPath);

    // 获取 STOMP 子协议的客户端对象
     stompClient = Stomp.over(socket);

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
});

//订阅消息
function subscribe() {	 
     stompClient.subscribe('/user/queue/message', function (response) { 
    	 //alert(response);
    	autoPlay(response.body);
    });
}

function autoPlay(redio){
	var myAuto = document.getElementById('myaudio');
	if("1"==redio){
		myAuto.src="images/alipay.mp3";
	}else if("2"==redio){
		myAuto.src="images/weixin.mp3";
	}	
	myAuto.play();
}