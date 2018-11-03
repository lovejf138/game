<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>提现记录</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=9">

<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
</head>
<body style="">
<div class="nav">
	<a class="weizhi" href="gerenzhongxin.do"> <img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/zuo.png" alt=""></a>
	<div class="zhongzi">
		提现记录(近30条)
	</div>
   <!--   <a class="shoucang" href="tixian.html">我要提现</a>-->
</div>
<div class="hj-z">
	<ul>
	
		<c:forEach items="${objs }" var="withdraw">
		<li style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
		
		
			<div class="hj-z1">
				<p><fmt:formatDate value="${withdraw.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<c:if test="${withdraw.status eq 'request'}">
				<span class="yanse1" style="color: white;">
				待处理
				</span>
				</c:if>
				<c:if test="${withdraw.status eq 'success'}">
				<span class="yanse2" style="color: white;">
				 已处理
				 </span>
				</c:if>
				</span></p>
			</div>
			<div class="hj-z2">
				
				<div class="hj-z4" style="width: 100%;">
					
					<p><span>${withdraw.amount}</span>ETH</p>
					<p>实际到账：<span>${withdraw.fianl_amount}</span>ETH</p>
				</div>
			</div>
		</li>
		</c:forEach>
		
	</ul>
</div>
</body>
</html>