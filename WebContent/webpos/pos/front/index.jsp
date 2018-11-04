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
		
		<div class="gg-z-f" style="margin-top: 2px;width: 60px;font-size: 30px;float: right;text-align: center;height: 30px;line-height: 30px;">
			
			<a href="http://caipiao.163.com/award/gd11xuan5/" style="color: red;font-size: 20px;float: right;width: 60px;height: 30px;border-radius: 100px; border: 1px solid #da0707;">公开</a>
		</div>
		
		<div class="gd-g" style="width:auto">
			<marquee direction="left" scrollamount="3" onmouseover="this.stop()"
				onmouseout="this.start()">
				<a href="">每天09:00～23:00每十分钟一个收获季。基于公平、公正、不可更改的实时数据。区块链思维设计，去中心化，人为不可控！</a>
			</marquee>
		</div>
		
	</div>
	
   

<!-- .cd-tour-wrapper -->

<!-- <div class="cd-app-screen"></div> -->

        <!-- 二维码弹窗 -->
		<div id="qrCode-jq"  style="display:none;height: 100%; padding: 15px;">
			
		</div>
		
<div class="cd-cover-layer"></div>

	
	<div class="fenge"></div>
	<div class="shou-shp" style="margin-bottom: 1.2rem;">
			<!-- <button style="top: 300px;background: hsl(13, 46%, 49%);" id="room_button" onclick="window.location.href='testroom.do'">示范大厅</button>
			
			<button id="room_button" onclick="window.location.href='room.do'">播种子，收金子</button> -->
			
			<button id="room_button" onclick="window.location.href='oneroom.do'">猜中翻倍</button>

			<%-- <canvas id="room_myCanvas" width="100%" height="100%"></canvas> --%>
	
	</div>

	<div class="daohang">
		<ul>
			<li><a href="index.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s1.png">
					<p style="color: #e01222;">首页</p>
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
</body>
</html>