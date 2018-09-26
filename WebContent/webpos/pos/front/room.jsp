<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>参与</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=5">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_base1.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_home1.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room_reset.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room.css?t=1">

		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=1">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_style.css?t=2">
		
		
	</head>
	<body style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
		<div id="wrap">	
			<input id="baseurl" value="<%=request.getContextPath()%>" style="display:none"/>
			<input id="error_time" value="${localtime}" style="display:none"/>
			<input id="roomid" value="${room.id}" style="display:none"/>
			<input id="userid" value="${userid}" style="display:none"/>		
			<input id="nextname" value="${nextname}" style="display: none">
			<input id="nextsecond" value="${nextsecond}" style="display: none">
		
			<nav class="navbox navbox_class2">
				<div class="navContent1">
					<c:forEach items="${awards }" var="aw">
					<p class="qi_name_class">${aw.name}期</p>
						<div class="kj-p5 qi_name_div">
						<p class="red-ball2 qi_ball" >${aw.no1}</p>
						<p class="red-ball2 qi_ball" >${aw.no2}</p>
						<p class="red-ball2 qi_ball" >${aw.no3}</p>
						<p class="red-ball2 qi_ball" >${aw.no4}</p>
						<p class="red-ball2 qi_ball" >${aw.no5}</p>
						<p class="blue-ball2 qi_ball">${aw.no6}</p>
						<p class="blue-ball2 qi_ball">${aw.no7}</p>
						<p class="blue-ball2 qi_ball">${aw.no8}</p>
						<p class="blue-ball2 qi_ball">${aw.no9}</p>
						<p class="blue-ball2 qi_ball">${aw.no10}</p>
						<p class="blue-ball2 qi_ball">${aw.no11}</p>
					</div>
					</c:forEach>
				</div>
				<div class="navContent">
					<div class="navList c" onClick="window.location.href='index.do'">
						
						<i class="navIco-fanhui navico"></i>
						<span class="navText">
							返回
						</span>
					</div>
					
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
		
	
		<div class="in-body" style="margin-top: 240px;">

	<!-- content begin -->
	<section id="content" >
		<div class="in-content">
			<div class="in-content-line"></div>
			<div class="in-content-box">
				<div class="in-content-title">
					<h2>
						<a id="name_wait1">离${nextname}期结束：</a><a id="name_wait2" style="color:#ff7400">0</a>秒
					</h2>
					<div class="in-line-left">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				<div class="in-content-links" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					<div style="text-align:center">
						房间${room.name}
						&nbsp&nbsp参与进度：
						<font style="color:#ff7400;font-size: 20px;">${room.progress}/11</font>
						</div>
						<div style="text-align:center;margin-top:15px">
						币池：
						<font style="color:#ff7400;font-size: 20px;">${room.amount}</font>ETH</div>
					</a>
					
					
					<div class="in-line-left" style="top: 75px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 75px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				<div class="in-content-links" style="background: #96ff96;">
					<input type="text" class="input-address" id="input_amount" maxlength="12" placeholder="输入参与数量"/>
					
          			 <input id="btn_ok" value="提交" type="button" class="input-login"/>
               
					
					<div class="in-line-left in-content-left" style="top: 40px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right in-content-right" style="top: 40px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					
				</div>
				
				 
				<div class="in-content-fellow" style="background: #111;">
				
					<div class="in-fellow-well">
						
						<div class="in-content-bd red">
							<p style="color:#fff;font-size: 15px;">号码 &nbsp&nbsp用户</p>
						</div>
						<div class="in-content-ft">
							<p style="color:#fff;font-size: 15px;">
								参与数量
							</p>
						</div>
					</div>
					
					<div style="height: 200px;overflow: auto;">
					<c:forEach items="${details }" var="detail">
					<div class="in-fellow-well">
						
						<div class="in-content-bd red">
							<p>${detail.number}. &nbsp${detail.shortid} </p>
						</div>
						<div class="in-content-ft">
							<p style="color:#fff;">
								<em style="color:#ff7400;font-size: 15px;font-weight: 500;">${detail.amount}</em>ETH
							</p>
						</div>
					</div>
					</c:forEach>
					</div>
				</div>
				
			</div>
		</div>
		</section>
	<!-- content end -->
	</div>
		
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/zepto.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script src='<%=request.getContextPath()%>/webpos/pos/front/js/room_123.js?t=5'></script>
		
	</body>
</html>