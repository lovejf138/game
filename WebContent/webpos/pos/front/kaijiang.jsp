<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>历史</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=1">

</head>
<body>
<input id="nextname" value="${nextname}" style="display:none">
<input id="nextsecond" value="${nextsecond}" style="display:none">
<div class="kj-zh" style="margin-top: 0rem;padding:0rem">
	<div class="kj-zh-nei">
		<div class="kj-zh-nei1">
			<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/k6.png">
			<p id="p_time_show">离${nextname}季结束：</p>
		</div>
		<div class="time-item">
			<strong id="hour_show">0时</strong>
			<strong id="minute_show">0分</strong>
			<strong id="second_show">0秒</strong>
		</div>
	</div>
</div>
	
<div class="kj-xia">
	<div class="kj-xia-b" style="color: #777;font-size: 16px;">
		<div class="kj-p1" style="width:90px;float:left">季节</div>
		<div class="kj-p3" style="float: right;width:140px">时间</div>
		<div class="kj-p2" style="margin-left: 90px;margin-right:140px">竞技号码</div>
		
	</div>
	<ul>
		<c:forEach items="${awards }" var="aw">
		<li style="height:90px;line-height:90px">
			<div class="kj-p4" style="width:90px;color: #bfbfbf;float:left;font-size: 15px;">${aw.name}</div>
			<div class="kj-p6" style="width:140px;color: #bfbfbf;font-size:15px;float: right;">
			<fmt:formatDate value="${aw.ctime}" pattern="yyyy-MM-dd HH:mm"/>
			</div>
			<div class="kj-p5" style="width: 100%;padding-top: 10px;text-align:left;height:90px;line-height:90px;margin-left: 90px; margin-right: 140px;">
				<p class="red-ball2 red_back_color1" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no1}</p>
				<p class="red-ball2 red_back_color2" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no2}</p>
				<p class="red-ball2 red_back_color3" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no3}</p>
				<p class="red-ball2 red_back_color4" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no4}</p>
				<p class="red-ball2 red_back_color5" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no5}</p>
				<p class="red-ball2 red_back_color6" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no6}</p>
				<p class="red-ball2 red_back_color7" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no7}</p>
				<p class="red-ball2 red_back_color8" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no8}</p>
				<p class="red-ball2 red_back_color9" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no9}</p>
				<p class="red-ball2 red_back_color10" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no10}</p>
				<p class="red-ball2 red_back_color11" style="font-size:13px;margin-top: 5px;margin-left: 2px;">${aw.no11}</p>
				<%-- <span>${aw.no1},${aw.no2},${aw.no3},${aw.no4},${aw.no5}</span>
				<span>${aw.no6},${aw.no7},${aw.no8},${aw.no9},${aw.no10},${aw.no11}</span> --%>
			</div>
			
		</li>
		</c:forEach>
	</ul>
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
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s3.png">
				<p style="color: #e01222;">历史</p>
			</a>
		</li>
		<li>
			<a href="paihang.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s6.png">
				<p>排行</p>
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
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?d08321caa4ded9e8f406743dac85e273";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script type="text/javascript">

var intDiff = parseInt($("#nextsecond").val());

var wait=false;//等待开奖
if(intDiff<0){
	intDiff = intDiff*-1;
	wait=true;
	$("#p_time_show").html("待"+$("#nextname").val()+"季收获：");
}else{
	$("#p_time_show").html("离"+$("#nextname").val()+"季结束：");
}

function timer(intDiff){
	window.setInterval(function(){
		
		if(intDiff==0){
			$("#p_time_show").html("待" + $("#nextname").val() + "季收获：");
			wait=true;
		}
		
	var day=0,
		hour=0,
		minute=0,
		second=0;//时间默认值
	if(intDiff > 0){
		day = Math.floor(intDiff / (60 * 60 * 24));
		hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
	}
	if (minute <= 9) minute = '0' + minute;
	if (second <= 9) second = '0' + second;
	$('#day_show').html(day+"天");
	$('#hour_show').html('<s id="h"></s>'+hour+'时');
	$('#minute_show').html('<s></s>'+minute+'分');
	$('#second_show').html('<s></s>'+second+'秒');
	$('#day_show5').html(day+"天");
	$('#hour_show5').html('<s id="h"></s>'+hour+'时');
	$('#minute_show5').html('<s></s>'+minute+'分');
	$('#second_show5').html('<s></s>'+second+'秒');
	if(wait){
		intDiff++;
	}else{
		intDiff--;
	}
	}, 1000);
} 

$(function(){
	timer(intDiff);
});	
</script>


</body>
</html>