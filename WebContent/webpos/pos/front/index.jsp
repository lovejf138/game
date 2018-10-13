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
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/assets/css/amazeui.min.css">

 <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/assets/css/app.css">
<style>
body, button, input, select, textarea {
    -webkit-font-smoothing: auto;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_lizi_style.css?t=8">
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
		
		<div class="gg-z-f" style="width: 60px;font-size: 30px;float: right;">
			
			<a href="http://caipiao.163.com/award/gd11xuan5/" style="color: red;font-size: 22px;float: right;">公开</a>
		</div>
		
		<div class="gd-g" style="width:auto">
			<marquee direction="left" scrollamount="3" onmouseover="this.stop()"
				onmouseout="this.start()">
				<a href="">播种子收金子竞技游戏，每天09:00～23:00每十分钟一个收获季。基于公平、公正、不可更改的实时数据。区块链思维设计，去中心化，人为不可控！</a>
			</marquee>
		</div>
		
	</div>
	<div class="gg-zhong"
		style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
		<p>第${award.name}季</p>
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
				<p id="p_time_show">离${nextname}季结束：</p>
			</div>
			<div class="time-item">
				<strong id="hour_show">0时</strong> <strong id="minute_show">0分</strong>
				<strong id="second_show">0秒</strong>
			</div>
		</div>
	</div>

   <div class="shou-zhong" style="height: 1rem;">
		  <a  id="how_to_play" style="padding-top: 25px; color: #868686; float: left; font-size: 15px;">怎么玩？</a>
		  <a  id="how_to_create"  style="padding-top: 25px; color: #868686; float: right; font-size: 15px;">号码怎么产生？</a>
   </div>
   
   <ul class="cd-tour-wrapper">
	<li class="cd-single-step">
		<span>前五位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">前五位号码</h2>
			<p style="font-size:15px">根据期号从<a href="http://caipiao.163.com/award/gd11xuan5/" style="color:red">http://caipiao.163.com/award/gd11xuan5/</a>获取，以18092564期为例，前五个号码为：</p>
			
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				</ul>
			</div>
			
		</div>
	</li> <!-- .cd-single-step -->

	<li class="cd-single-step">
		<span>第六位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">第六位号码</h2>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				</ul>
			</div>
			<p style="font-size:15px">取第一位号码 6➕1 => 7(已出现过) ➕1 => 8 (未出现)，故第六位号码为8</p>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				</ul>
			</div>
		</div>
	</li> <!-- .cd-single-step -->
	
	<li class="cd-single-step">
		<span>第七位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">第七位号码</h2>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				</ul>
			</div>
			<p style="font-size:15px">取第二位号码 1➕1 => 2(未出现)，故第七位号码为2</p>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				</ul>
			</div>
		</div>
	</li>
	
	<li class="cd-single-step">
		<span>第八位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">第八位号码</h2>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				</ul>
			</div>
			<p style="font-size:15px">取第三位号码 9➕1 => 10(未出现)，故第八位号码为10</p>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				</ul>
			</div>
		</div>
	</li>
	
	<li class="cd-single-step">
		<span>第九位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">第九位号码</h2>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				</ul>
			</div>
			<p style="font-size:15px">取第四位号码 4➕1 => 5(未出现)，故第九位号码为5</p>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">5</p>
				</li>
				</ul>
			</div>
		</div>
	</li>
	
	<li class="cd-single-step">
		<span>第十位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">第十位号码</h2>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">5</p>
				</li>
				</ul>
			</div>
			<p style="font-size:15px">取第五位号码 7➕1 => 8(已出现)➕1 => 9(已出现)➕1 => 10(已出现)➕1 => 11(未出现)，故第十位号码为11</p>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">5</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">11</p>
				</li>
				</ul>
			</div>
		</div>
	</li>
	
	<li class="cd-single-step">
		<span>第十一位号码</span>

		<div class="cd-more-info bottom">
			<h2 style="font-size:20px">第十一位号码</h2>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">5</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">11</p>
				</li>
				</ul>
			</div>
			<p style="font-size:15px">1～11在前面剩余号码3未出现，故第十一位取号码3，最终十一个号码为：</p>
			<div class="gg-shu">
				<ul>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">6</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">1</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">9</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">4</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="red-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">7</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">8</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">2</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">10</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">5</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">11</p>
				</li>
				<li style="margin-left: 5px;">
					<p class="blue-ball" style="font-size: 18px;color: #fff;margin-top: 0px;text-shadow: 2px 2px 5px orange;">3</p>
				</li>
				</ul>
			</div>
		</div>
	</li>

</ul> <!-- .cd-tour-wrapper -->

<!-- <div class="cd-app-screen"></div> -->

<div class="cd-cover-layer"></div>

	
	<div class="fenge"></div>
	<div class="shou-shp" style="margin-bottom: 1.2rem;">
			<button id="room_button" onclick="window.location.href='room.do'">参与竞技</button>

			<canvas id="room_myCanvas" width="100%" height="100%"></canvas>
			
			<%--<ul id="creditLoan" class="credit-loan-list credit clearfix" style="list-style: none;">
			
						<li  onclick="javascript:document.getElementById('aroom_${room.id}').click();"
						>
						<a  id="aroom_${room.id}" style="display:none" href="room.do"></a>
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
			
					</ul> --%>

		
	</div>

	<div class="daohang">
		<ul>
			<li><a href="index.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s1.png">
					<p style="color: #e01222;">首页</p>
			</a></li>
			<li><a href="kj.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s4.png">
					<p>历史</p>
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
		src="<%=request.getContextPath()%>/webpos/pos/js/modernizr.js"></script>
    <script src="<%=request.getContextPath()%>/webpos/pos/assets/js/amazeui.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/prefixfree.min.js"></script>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_lizi.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_123.js"></script>
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
			$("#p_time_show").html("待" + $("#nextname").val() + "季收获：");
		} else {
			$("#p_time_show").html("离" + $("#nextname").val() + "季结束：");
		}
		/* var intDiff = parseInt(120);//倒计时总秒数量 */
		function timer(intDiff) {
			window.setInterval(function() {
				if(intDiff==0){
					$("#p_time_show").html("待" + $("#nextname").val() + "季收获：");
					wait=true;
				}
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
	
<script>


</script>
</body>
</html>