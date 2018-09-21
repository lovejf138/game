<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=1">

</head>
<body>

	<input id="nextname" value="${nextname}" style="display: none">
	<input id="nextsecond" value="${nextsecond}" style="display: none">
	<div class="gg-z">
		<div class="gg-z-f">
			<img
				src="<%=request.getContextPath()%>/webpos/pos/front/img/index/gonggao.png">
		</div>
		<div class="gd-g">
			<marquee direction="left" scrollamount="3" onmouseover="this.stop()"
				onmouseout="this.start()">
				<a href="">粤11选5，每天09:00～23:00期间10分钟开奖一次，可前往网易官网"http://caipiao.163.com/award/gd11xuan5"对照数据，最公平、公正、公开的以太坊赚钱平台！</a>
			</marquee>
		</div>
	</div>
	<div class="gg-zhong"
		style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
		<p>第${award.name}期</p>
		<div class="gg-shu">
			<ul>
				<li>
					<p class="red-ball">${award.no1}</p>
				</li>
				<li>
					<p class="red-ball">${award.no2}</p>
				</li>
				<li>
					<p class="red-ball">${award.no3}</p>
				</li>
				<li>
					<p class="red-ball">${award.no4}</p>
				</li>
				<li>
					<p class="red-ball">${award.no5}</p>
				</li>
				<li>
					<p class="blue-ball">${award.no6}</p>
				</li>
				<li>
					<p class="blue-ball">${award.no7}</p>
				</li>
				<li>
					<p class="blue-ball">${award.no8}</p>
				</li>
				<li>
					<p class="blue-ball">${award.no9}</p>
				</li>
				<li>
					<p class="blue-ball">${award.no10}</p>
				</li>
				<li>
					<p class="blue-ball">${award.no11}</p>
				</li>
			</ul>
		</div>
	</div>
	<div class="kj-zh"
		style="margin-top: 0px; background-color: #fff; padding: 0px;">
		<div class="kj-zh-nei">
			<div class="kj-zh-nei1">
				<img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/k6.png">
				<p id="p_time_show">离${nextname}期结束：</p>
			</div>
			<div class="time-item">
				<strong id="hour_show">0时</strong> <strong id="minute_show">0分</strong>
				<strong id="second_show">0秒</strong>
			</div>
		</div>
	</div>

	<div class="shou-zhong" style="height:60px">
		<a href="guize.do" style="padding-top: 25px; color: #868686; float: left; font-size: 15px;">开奖及玩法说明</a>
		
			<svg viewBox="0 0 0 0" style="position: absolute; z-index: -1; opacity: 0;">
			  <defs>
			    <linearGradient id="boxGradient" gradientUnits="userSpaceOnUse" x1="0" y1="0" x2="25" y2="25">
			      <stop offset="0%"   stop-color="#27FDC7"/>
			      <stop offset="100%" stop-color="#0FC0F5"/>
			    </linearGradient>
			
			    <linearGradient id="lineGradient">
			      <stop offset="0%"    stop-color="#0FC0F5"/>
			      <stop offset="100%"  stop-color="#27FDC7"/>
			    </linearGradient>
			
			    <path id="todo__line" stroke="url(#lineGradient)" d="M21 12.3h168v0.1z"></path>
			    <path id="todo__box" stroke="url(#boxGradient)" d="M21 12.7v5c0 1.3-1 2.3-2.3 2.3H8.3C7 20 6 19 6 17.7V7.3C6 6 7 5 8.3 5h10.4C20 5 21 6 21 7.3v5.4"></path>
			    <path id="todo__check" stroke="url(#boxGradient)" d="M10 13l2 2 5-5"></path>
			    <circle id="todo__circle" cx="13.5" cy="12.5" r="10"></circle>
			  </defs>
			</svg>
			
			
			<div class="todo-list" onClick="window.location.href='index.do?hide_room=yes'" 
				style="width: 220px;height:100px;float:right">
			  <label class="todo">
			    <input class="todo__state" type="checkbox" 
			    	<c:if test="${if_hide_room =='_true'}">
			    	checked="checked"
			    	</c:if>
			 	  /> 
			
			    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 200 25" class="todo__icon">
			      <use xlink:href="#todo__line" class="todo__line"></use>
			      <use xlink:href="#todo__box" class="todo__box"></use>
			      <use xlink:href="#todo__check" class="todo__check"></use>
			      <use xlink:href="#todo__circle" class="todo__circle"></use>
			    </svg>
			
			    <div class="todo__text" id="hide_room">隐藏不可参与房间</div>
			
			  </label>
			
			</div>
	</div>

	<div class="fenge"></div>
	<div class="shou-shp" style="margin-bottom: 1.2rem;">
	
			<ul id="creditLoan" class="credit-loan-list credit clearfix" style="list-style: none;">
			<c:set var="_number" value="1"/>
			<c:forEach items="${rooms }" var="room">
						<li  onclick="javascript:document.getElementById('aroom_${room.id}').click();"
						<c:if test="${_number%2!=0}">
						style="margin-right: 0.44rem;margin-bottom: 20px;height:150px;background:#fdfdfd"
						</c:if>
						<c:if test="${_number%2==0}">
						style="margin-bottom: 20px;height:150px;background:#fdfdfd"
						</c:if>
						>
						<c:set var="_number" value="${_number+1}"/>
						<a  id="aroom_${room.id}" style="display:none" href="room.do?roomid=${room.id}"></a>
							<p class="p1" style="margin:0px">房间${room.name}</p>
							<!-- <img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/room.png" style="width: 30%;height: 40%;"/> -->
							<div >
								<p style="margin:0px;height: 20px;line-height: 20px;font-size:12px;    margin-top: 10px;">
									<span style="color: #999999;font-size:20px">进度：</span>
									<span class="monthly" style="color:#ff7400;font-size:20px">${room.progress}/11</span>
								</p>
								
								<p style="margin:0px;height: 20px;line-height: 20px;font-size:12px;    margin-top: 10px;">
									<span style="color: #999999;font-size:20px">币池：</span>
									<span class="monthly" style="color:#ff7400;font-size:20px">${room.amount}ETH</span>
								</p>
								
							</div>
						</li>
			</c:forEach>
					</ul>
					
		

			<!--<ul><c:forEach items="${rooms }" var="room">
				<li style="background-color: #fff;">
				<p class="p1" style="background: #d9593d;text-align: center;line-height: 1.1rem;color: #fff;font-size: 0.44rem;">房间${room.name}</p>
				
						<div class="shou-shp1">

							
							<p>进度:${room.progress}/11</p>
							<p>
							 币池：${room.amount}ETH
							</p>
							
							<a class="progress-title" style="position: relative;" href="room.do?roomid=${room.id}">
												 房间${room.name}<br> 
												进度:${room.progress}/11<br>
												币池：${room.amount}ETH
												</a>
												
						</div>
				</li>
			</c:forEach></ul>-->
		
	</div>

	<div class="daohang">
		<ul>
			<li><a href="index.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s1.png">
					<p style="color: #e01222;">首页</p>
			</a></li>
			<li><a href="kj.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s4.png">
					<p>开奖</p>
			</a></li>
			<li><a href="paihang.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s6.png">
					<p>排行</p>
			</a></li>
			<li><a href="gerenzhongxin.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s8.png">
					<p class="yanse">我的</p>
			</a></li>
		</ul>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
	<script type="text/javascript">
	
		$('#tit2 span').click(function() {
			var i = $(this).index();//下标第一种写法
			$(this).addClass('select2').siblings().removeClass('select2');
			$('.login2').eq(i).show().siblings().hide();
		});
		var intDiff = parseInt($("#nextsecond").val());
		var wait = false;//等待开奖
		if (intDiff < 0) {
			intDiff = intDiff * -1;
			wait = true;
			$("#p_time_show").html("待" + $("#nextname").val() + "期开奖：");
		} else {
			$("#p_time_show").html("离" + $("#nextname").val() + "期结束：");
		}
		/* var intDiff = parseInt(120);//倒计时总秒数量 */
		function timer(intDiff) {
			window.setInterval(function() {
				var day = 0, hour = 0, minute = 0, second = 0;//时间默认值
				if (intDiff > 0) {
					day = Math.floor(intDiff / (60 * 60 * 24));
					hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
					minute = Math.floor(intDiff / 60) - (day * 24 * 60)
							- (hour * 60);
					second = Math.floor(intDiff) - (day * 24 * 60 * 60)
							- (hour * 60 * 60) - (minute * 60);
				}
				if (minute <= 9)
					minute = '0' + minute;
				if (second <= 9)
					second = '0' + second;
				$('#day_show').html(day + "天");
				$('#hour_show').html('<s id="h"></s>' + hour + '时');
				$('#minute_show').html('<s></s>' + minute + '分');
				$('#second_show').html('<s></s>' + second + '秒');
				$('#day_show5').html(day + "天");
				$('#hour_show5').html('<s id="h"></s>' + hour + '时');
				$('#minute_show5').html('<s></s>' + minute + '分');
				$('#second_show5').html('<s></s>' + second + '秒');
				if (wait) {
					intDiff++;
				} else {
					intDiff--;
				}

			}, 1000);
		}
		$(function() {
			timer(intDiff);
		});
	</script>
</body>
</html>