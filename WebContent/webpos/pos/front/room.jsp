<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1,minimum-scale=1,maximum-scale=1 " />
		<title></title>
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room_reset.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room.css?t=1">
		
		<style>
			@media all and (max-width:500px){
    			initScreen{
					position: absolute;
					left:20%;
					top:20%;
					width: 60%;
					height:320px;
					z-index: 1000;
					background:#fff;
					color:#000;
					border-radius: 30px;
				}
			}
		</style>
		
	</head>
	<body style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
		<div id="wrap">	
			<input id="baseurl" value="<%=request.getContextPath()%>" style="display:none"/>
			<input id="localtime" value="${localtime}" style="display:none"/>
			<input id="roomid" value="${roomid}" style="display:none"/>
			<input id="userid" value="${userid}" style="display:none"/>		
			<nav class="navbox">
				<div class="navContent">
					
					<div class="navList c-tanmu">
						<i class="navIco-tanmu navico"></i>
						<span class="navText">
							弹幕开关
						</span>
					</div>
					<div class="navList c-fatan">
						<i class="navIco-fatan navico"></i>
						<span class="navText">
							发弹幕
						</span>
					</div>
					
				</div>					
				<div class="pothook">
				</div>
			</nav>		
		
			<div class="send">
				<div class="s_fiter">
					
						<input type="text" class="s_txt" maxlength="12" placeholder="发送弹幕，最多可写12字"/>
						<div class="s_sub"></div>
						
				</div>
			</div>
			<!--end send-->
		</div>
		
		
		<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
		<script src='<%=request.getContextPath()%>/webpos/pos/front/js/room_123.js?t=2'></script>	
	</body>
</html>