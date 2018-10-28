<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">消息列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/public.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/region_select.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/date/laydate.dev.js"></script>
<!-- fenye  css 、 js-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/template.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/plugin/amaze-ui/css/amazeui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/plugin/amaze-ui/css/admin.css" />
<script type="text/javascript">
	//表单方式分页
	function gotoPage(n) {
		jQuery("#currentPage").val(n);
		jQuery("#ListForm").submit();
	}
</script>
</head>

<body class="user-account">
	<div class="am-cf admin-main">
		<div class="admin-content" style="margin-top: 5px">

			<div class="am-panel am-panel-default">
				<div class="am-panel-hd">
					<div class="am-cf _div_tilte">消息列表</div>
				</div>

				<div class="am-panel-hd " style="background-color: #fff;">
					<form class="am-form am-form-inline am-cf"
						action="<%=request.getContextPath()%>/messages.do" method="post"
						id="ListForm">
						<input type="hidden" id="currentPage" name="currentPage">

						<div class="am-form-group">
							<label class="am-text-sm _merchant_short"
								style="margin-bottom: 0px; font-size: 1.5rem;">地址：</label>:
						</div>
						<div class="am-form-group">
							<input type="text" value="${jc }" name="jc" id="jc"
								class="am-input-sm" style="width: 180px; height: 30px" />
						</div>

						<div class="am-form-group">
							<label class="am-text-sm _merchant_short"
								style="margin-bottom: 0px; font-size: 1.5rem;">房间号：</label>:
						</div>
						<div class="am-form-group">
							<input type="text" value="${roomid }" name="roomid" id="roomid"
								class="am-input-sm" style="width: 80px; height: 30px" />
						</div>



						<!--  <a class="am-btn  am-btn-sm am-btn-primary am-fr" onclick="sys()">系统设置</a>	-->
						
						<a	class="am-btn  am-btn-sm am-btn-fail am-fr" onclick="_get()">获取往期开奖</a>
						
						<a	class="am-btn  am-btn-sm am-btn-fail am-fr" onclick="_jq()">弄点机器人</a>
							
						<a class="am-btn  am-btn-sm am-btn-success am-fr"
							href="<%=request.getContextPath()%>/users.do">用户列表</a> 
						<a
							class="am-btn  am-btn-sm am-btn-fail am-fr" onclick="_kj()">开奖</a>

						<button class="am-btn  am-btn-sm am-btn-primary am-fr _query">查询</button>
					</form>
				</div>

				<div class="am-panel-bd">
					<p style="color: red" id="p_tongji"></p>
					<table
						class="am-table am-table-bordered am-table-centered am-text-sm">
						<thead style="background-color: #f5f5f5;">
							<tr>
								<th class="_merchant_short">ID</th>

								<th class="_contact">房间</th>
								<th class="_contact">用户</th>
								<th class="_contact">消息</th>

								<th class="_endtime">创建时间</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${objs }" var="user">
								<tr>
									<td>${user.id}</td>

									<td>${user.roomid }</td>

									<td>${user.userid }</td>
									<td>${user.message }</td>

									<td><fmt:formatDate value="${user.ctime }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>


								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="fenye">${gotoPageFormHTML1}</div>

				</div>
			</div>
		</div>

		<!-- 二维码弹窗 -->
		<div id="qrCode-kai" class="hide" style="height: 100%; padding: 15px;">
			<table style="text-align: center; width: 100%; height: 100%">
				<tr height="30%">
					<td colspan="4">期数:<input id="kj_name" style="width: 350px"
						style="text" placeholder="18090834" />
					</td>
				</tr>
				<tr>
					<td colspan="4">号码1:<input id="kj_no1" style="width: 100px" />
					</td>
				</tr>
				<tr>
					<td colspan="4">号码2:<input id="kj_no2" style="width: 100px" />
					</td>
				</tr>
				<tr>
					<td colspan="4">号码3:<input id="kj_no3" style="width: 100px" />
					</td>
				</tr>
				<tr>
					<td colspan="4">号码4:<input id="kj_no4" style="width: 100px" />
					</td>
				</tr>
				<tr>
					<td colspan="4">号码5:<input id="kj_no5" style="width: 100px" />
					</td>
				</tr>
				
	

				<tr>
					<td colspan="2"><a id="button-query1" style="font-size: 1rem"
						class="button-a button-a-primary" onclick="kj_btn()"
						href="javascript:void(0);">确定</a></td>
						
					<td colspan="2"><a id="button-query1" style="font-size: 1rem"
						class="button-a button-a-primary" onclick="kj_send_btn()"
						href="javascript:void(0);">开奖通知</a></td>
						
					<td colspan="2"><a id="button-query2" style="font-size: 1rem"
						class="button-a button-a-primary" onclick="getkj()"
						href="javascript:void(0);">获取开奖</a></td>
				</tr>
			</table>
		</div>
		
		
		<!-- 二维码弹窗 -->
		<div id="qrCode-get" class="hide" style="height: 100%; padding: 15px;">
			<table style="text-align: center; width: 100%; height: 100%">
				<tr height="30%">
					<td colspan="4">日期:<input id="get_time" style="width: 350px"
						style="text" value="2018-10-19" />
					</td>
				</tr>
				<tr>
					<td colspan="4">最大期数:<input id="get_maxqi" style="width: 100px" value="84" />
					</td>
				</tr>
				
				<tr>
					<td colspan="4"><a id="button-query1" style="font-size: 1rem"
						class="button-a button-a-primary" onclick="get_btn()"
						href="javascript:void(0);">确定</a></td>
						
				</tr>
			</table>
		</div>
		
		<!-- 二维码弹窗 -->
		<div id="qrCode-jq" class="hide" style="height: 100%; padding: 15px;">
			<table style="text-align: center; width: 100%; height: 100%">
				<tr height="30%">
					<td colspan="4">期数:<input id="jq_name" style="width: 350px"
						style="text" placeholder="18090834" />
					</td>
				</tr>
				<tr>
					<td colspan="4">机器人数:<input id="jq_number" style="width: 100px" value="1"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">id最小:<input id="jq_minid" style="width: 100px" value="1"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">id最大:<input id="jq_maxid" style="width: 100px" value="3000" />
					</td>
				</tr>
				<tr>
					<td colspan="4">下注最小:<input id="jq_minamount" style="width: 100px" value="10"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">下注最大:<input id="jq_maxamount" style="width: 100px"  value="100"/>
					</td>
				</tr>
				
	

				<tr>
					<td colspan="4"><a id="button-query1" style="font-size: 1rem"
						class="button-a button-a-primary" onclick="jq_btn()"
						href="javascript:void(0);">确定</a></td>

				</tr>
			</table>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/date/laydate.dev.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/main.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/layer/layer.js"></script>
<script type="text/javascript">

var kj = false;
var jq = false;

	function _kj() {

		$("#kj_name").val("");
		$("#kj_no1").val("");
		$("#kj_no2").val("");
		$("#kj_no3").val("");
		$("#kj_no4").val("");
		$("#kj_no5").val("");
		layer.open({
			title : "开奖",
			type : 1, //page层
			area : [ '400px', '400px' ],
			shade : 0.6, //遮罩透明度
			moveType : 1, //拖拽风格，0是默认，1是传统拖动
			shift : 1, //0-6的动画形式，-1不开启
			content : $('#qrCode-kai')
		});

	}
	
	function _get() {

		layer.open({
			title : "获取往期号码（慎重）",
			type : 1, //page层
			area : [ '400px', '400px' ],
			shade : 0.6, //遮罩透明度
			moveType : 1, //拖拽风格，0是默认，1是传统拖动
			shift : 1, //0-6的动画形式，-1不开启
			content : $('#qrCode-get')
		});

	}
	
	function _jq() {

		layer.open({
			title : "搞点机器人（都是钱呀！）",
			type : 1, //page层
			area : [ '400px', '400px' ],
			shade : 0.6, //遮罩透明度
			moveType : 1, //拖拽风格，0是默认，1是传统拖动
			shift : 1, //0-6的动画形式，-1不开启
			content : $('#qrCode-jq')
		});

	}
	
	function jq_btn() {
	  if(!jq){
			 
		  var number= $("#jq_number").val();
		  var _num=0;
		  try{
			  _num = parseInt(number);
		  }catch(e){_num=0;}
		  
		  if(_num<=0||_num>100){
			  alert("人数有误，最大100");
		  }
		  
		  jq = true;
		  
		  var con = true;
		  for(var i=0;i<_num;i++){
			 // alert(i);
			 if(con){
			  $.ajax({
					url : 'macjoin.do',
					data : "jq_name=" + $("#jq_name").val() + "&jq_minid="
							+ $("#jq_minid").val()+ "&jq_maxid="
							+ $("#jq_maxid").val()+ "&jq_minamount="
							+ $("#jq_minamount").val()+ "&jq_maxamount="
							+ $("#jq_maxamount").val(),
					type : 'post',
					dataType : 'json',
					traditional : true,
					success : function(data) {
						if (data == "SUCCESS") {
							
						} else {
							jq = false;
							con = false;
							alert(''+data);
						}
					}
				});
			 }
		  }
		  
		  alert("操作成功");
			
	  }
	}
	
	
	function kj_send_btn() {
		 
		var text=$("#kj_name").val("");
		var x = "1@1"+"!#@#Qsaswe@#./1!"+"@"+text;
	    var _sign = hex_md5(x);
	    text = "1&&__"+"1&&__4&&__"+text+"&&__"+_sign;
	    
		send(text);
	}
	
	
	function get_btn() {
		  if(!kj){
			  kj = true;
			$.ajax({
				url : 'getkaijiang.do',
				data : "time=" + $("#get_time").val() + "&maxqi="
						+ $("#get_maxqi").val(),
				type : 'post',
				dataType : 'json',
				traditional : true,
				success : function(data) {
					if (data == "SUCCESS") {
						alert('操作成功!');
					} else {
						kj = false;
						alert(''+data);
					}
				}
			});
			}
		}
	
	function getkj(){
		$.ajax({
			url : 'http://e.apiplus.net/newly.do?token=td8bb600ad2eb3d7ak&code=gd11x5&rows=1&format=json',
			data : null,
			type : 'post',
			dataType : 'jsonp',
			traditional : true,
			success : function(data) {
				 var description = "";
				 var datas = data.data;
				 var expect = datas[0].expect;
				 var opencode = datas[0].opencode;
				//alert(expect+","+opencode);
				$("#kj_name").val(expect.substring(2,expect.length));
				var codes = opencode.split(",");
				$("#kj_no1").val(""+codes[0]);
				$("#kj_no2").val(""+codes[1]);
				$("#kj_no3").val(""+codes[2]);
				$("#kj_no4").val(""+codes[3]);
				$("#kj_no5").val(""+codes[4]);
				
				
			}
		});
	}
	
	function kj_btn() {
	  if(!kj){
		  kj = true;
		$.ajax({
			url : 'kaijiang.do',
			data : "name=" + $("#kj_name").val() + "&no1="
					+ $("#kj_no1").val() + "&no2="
					+ $("#kj_no2").val()+ "&no3="
					+ $("#kj_no3").val()+ "&no4="
					+ $("#kj_no4").val()+ "&no5="
					+ $("#kj_no5").val(),
			type : 'post',
			dataType : 'json',
			traditional : true,
			success : function(data) {
				if (data == "SUCCESS") {
					alert('操作成功，请点击通知开奖!');
				} else {
					kj = false;
					alert(''+data);
				}
			}
		});
		}
	}
	
	
	//每20秒去获取开奖信息
	setInterval(function(){ 
		getkj();//获取开奖号码
		
		$.ajax({
			url : 'kaijiang.do',
			data : "name=" + $("#kj_name").val() + "&no1="
					+ $("#kj_no1").val() + "&no2="
					+ $("#kj_no2").val()+ "&no3="
					+ $("#kj_no3").val()+ "&no4="
					+ $("#kj_no4").val()+ "&no5="
					+ $("#kj_no5").val(),
			type : 'post',
			dataType : 'json',
			traditional : true,
			success : function(data) {
				if (data == "SUCCESS") {
					kj_send_btn();
				} else {
				}
			}
		});
	},20000);
	
 /* 	var websocket=null;
	var _top=80;
	var index=0;

	var host=window.location.host;
	//判断当前浏览器是否支持WebSocket
	if('WebSocket' in window){
		
		websocket=new WebSocket("ws://eth-game.club/websocket");
	}
	else{
		alert("当前浏览器不支持发送弹幕!");
	}


	//连接发生错误的回调方法
	websocket.onerror = function(){
	   // setMessageInnerHTML("error");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(){
	   // setMessageInnerHTML("open");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event){
		
	}

	//连接关闭的回调方法
	websocket.onclose = function(){
	  //  setMessageInnerHTML("close");
	}


	window.onbeforeunload = function(){
	    websocket.close();
	}  */

	var websocket=null;
	var _top=80;
	var index=0;

	var lockReconnect = false;  //避免ws重复连接
	createWebSocket();   //连接ws

	var host=window.location.host;
	function createWebSocket(){
		//判断当前浏览器是否支持WebSocket
		if('WebSocket' in window){
			//websocket=new WebSocket("ws://localhost:8080/_game/websocket");/*("ws://"+host+"/Danmu/websocket");*/
			websocket=new WebSocket("ws://eth-game.club/websocket");
			initEventHandle();
		}
		else{
			alert("当前浏览器不支持发送弹幕!");
		}
	}


	function initEventHandle() {
		websocket.onclose = function () {
	        console.log("llws连接关闭!" + new Date().toUTCString());
	        reconnect();
	    };
	    websocket.onerror = function () {
	        console.log("llws连接错误!");
	        reconnect();
	    };
	    websocket.onopen = function () {
	        heartCheck.reset().start();      //心跳检测重置
	        console.log("llws连接成功!" + new Date().toUTCString());
	       
	    };
	    websocket.onmessage = function (event) {    //如果获取到消息，心跳检测重置
	        heartCheck.reset().start();      //拿到任何消息都说明当前连接是正常的
	    };
	}

	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function () {
		websocket.close();
	}

	function reconnect(url) {
	    if (lockReconnect) return;
	    lockReconnect = true;
	    setTimeout(function () {     //没连接上会一直重连，设置延迟避免请求过多
	        createWebSocket(url);
	        lockReconnect = false;
	    }, 2000);
	}

	//心跳检测
	var heartCheck = {
	    //timeout: 540000,        //9分钟发一次心跳
	    //timeout: 3600,        //1分钟发一次心跳
	    timeout: 10800,        //3分钟发一次心跳
	    timeoutObj: null,
	    serverTimeoutObj: null,
	    reset: function () {
	        clearTimeout(this.timeoutObj);
	        clearTimeout(this.serverTimeoutObj);
	        return this;
	    },
	    start: function () {
	        var self = this;
	        this.timeoutObj = setTimeout(function () {
	            //这里发送一个心跳，后端收到后，返回一个心跳消息，
	            //onmessage拿到返回的心跳就说明连接正常
	        	websocket.send("ping");
	            console.log("ping!")
	            self.serverTimeoutObj = setTimeout(function () {
	                //如果超过一定时间还没重置，说明后端主动断开了
	                //如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
	            	websocket.close();     
	            },self.timeout)
	        },this.timeout)
	    }
	}
	
	//发送消息
	function send(text){
	    websocket.send(text);
	}
</script>
</html>

