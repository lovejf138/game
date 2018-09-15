<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>排行</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">

</head>
<body>
<div id="wrap">
	<div id="tit">
		<span class="select">参与榜</span>
		<span>奖金榜</span>
	</div>
	<div id="login">
		<div class="login show">
			<div class="ph-sh">
				<div class="ph-sh1">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/tou.jpeg">
					<p>${play1.short6}</p>
					<p><span>${play1.play_sum}</span>ETH</p>
					<div class="ph-dy">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/p1.png">
					</div>
				</div>
				<div class="ph-sh1">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/tou.jpeg">
					<p>${play2.short6}</p>
					<p><span>${play2.play_sum}</span>ETH</p>
					<div class="ph-dy">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/p2.png">
					</div>
				</div>
				<div class="ph-sh1 ph-sh2">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/tou.jpeg">
					<p>${play3.short6}</p>
					<p><span>${play3.play_sum}</span>ETH</p>
					<div class="ph-dy">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/p3.png">
					</div>
				</div>
			</div>
			<div class="biao">
				<ul>
				
					<c:forEach items="${plays }" var="play">
					<li>
						<p>${play.id_short}</p>
						<p style="float: right;">已参与<span>${play.play_sum}</span>ETH</p>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="login">
			<div class="ph-sh">
			
				<div class="ph-sh1">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/tou.jpeg">
					<p>${award1.short6}</p>
					<p><span>${award1.award_sum}</span>ETH</p>
					<div class="ph-dy">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/p1.png">
					</div>
				</div>
				<div class="ph-sh1">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/tou.jpeg">
					<p>${award2.short6}</p>
					<p><span>${award2.award_sum}</span>ETH</p>
					<div class="ph-dy">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/p2.png">
					</div>
				</div>
				<div class="ph-sh1 ph-sh2">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/tou.jpeg">
					<p>${award3.short6}</p>
					<p><span>${award3.award_sum}</span>ETH</p>
					<div class="ph-dy">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/p3.png">
					</div>
				</div>
				
			</div>
			<div class="biao">
				<ul>
				   <c:forEach items="${awards }" var="award">
					<li>
						<p>${award.id_short}</p>
						<p style="float: right;">已获得<span>${award.award_sum}</span>ETH</p>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
	</div>
</div>



<div class="daohang">
	<ul>
		<li>
			<a href="index.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s2.png">
				<p>首页</p>
			</a>
		</li>
		<li>
			<a href="kj.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s4.png">
				<p>开奖</p>
			</a>
		</li>
		<li>
			<a href="paihang.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s5.png">
				<p style="color: #e01222;">排行</p>
			</a>
		</li>
		<li>
			<a href="gerenzhongxin.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s8.png">
				<p class="yanse">我的</p>
			</a>
		</li>
	</ul>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>

<script>
	$('#tit span').click(function() {
		var i = $(this).index();//下标第一种写法
		$(this).addClass('select').siblings().removeClass('select');
		$('.login').eq(i).show().siblings().hide();
	});
</script>

</body>
</html>