<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>推广收益记录</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">

<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
</head>
<body>
<div class="nav">
	<a class="weizhi" href="gerenzhongxin.do"> <img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/zuo.png" alt=""></a>
	<div class="zhongzi">
		推广收益记录(近30条)
	</div>
   <!--   <a class="shoucang" href="tixian.html">我要提现</a>-->
</div>
<div class="tx-jl" style="margin-top: 1rem;">
	<ul>
	
		<c:forEach items="${objs }" var="withdraw">
		<li style="height: 100px;line-height: 100px;">
			
			<p><fmt:formatDate value="${withdraw.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/><span>&nbsp&nbsp¥${withdraw.oneperofactual}
			</span>
			<span style="color:#000">${withdraw.goodsname}
			</span>
			</p>
		</li>
		</c:forEach>
		
	</ul>
</div>
</body>
</html>