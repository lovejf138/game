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
		<title>竞技详情</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=13">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_base1.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_home1.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room_reset.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room.css?t=5">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=1">
		
	</head>
	<body style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
	
<div class="" style="width: 100%;overflow: hidden;position: fixed;top: 0;height: 40px;line-height: 40px;">
	<a class="weizhi" href="${url}" style="display: block;float: left;width: 15%;height: 40px;line-height: 40px;"> 
		<img style="width: 30px;margin-top: .26rem;margin-left: .15rem;" src="<%=request.getContextPath()%>/webpos/pos/front/img/room/return.png" alt=""></a>
	<div class="zhongzi" style="width: 60%;text-align: center;float: left;height: 40px;line-height: 40px;font-size: 20px;color:#fff;font-weight:700">
		竞技详情
	</div>
 
</div>
	
	
<div class="in-body" style="margin-top: 100px;">

	<!-- content begin -->
	<section id="content" >
		<div class="in-content">
			<div class="in-content-line"></div>
			<div class="in-content-box">
				<div class="in-content-title">
					<h2>
						<a id="name_wait1">币池：
						<font style="color:#ff7400;font-size: 20px;">${_sum}</font>ETH</a>
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
						
						期号：<font style="color:#ff7400;font-size: 20px;">${qiname}</font>
						
					</div>
					
					
					<div class="in-line-left">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				<div class="in-content-links" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					<div class="gg-shu" style="text-align:center;width: 80%;margin: 0 auto;overflow: hidden;margin-top: .2rem;">
						
						<!-- 等待开奖-->
				
				<c:if test="${kj !=1}">等待开奖</c:if>
				<c:if test="${kj ==1}">
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
					</c:if>
						
					</div>
					
					
				</div>
				
				 
				<div class="in-content-fellow" style="background: #111;">
				
					<div class="in-fellow-well">
						
						<div class="in-content-bd red">
							<p style="color:#fff;font-size: 15px;">号码 &nbsp&nbsp用户</p>
						</div>
						<div class="in-content-ft">
							<p style="color:#fff;font-size: 15px;">
								参与数量</br>(奖金)
							</p>
						</div>
					</div>
					
					<div style="height: 200px;overflow: auto;">
					<c:forEach items="${objs }" var="detail">
					<div class="in-fellow-well">
						
						<div class="in-content-bd red">
							<p>${detail.number}. &nbsp${detail.shortid} </p>
						</div>
						<div class="in-content-ft">
							<p style="color:#fff;">
								<em style="font-size: 15px;font-weight: 500;">${detail.amount}</em>ETH</br>
								(<em style="color:#ff7400;font-size: 15px;font-weight: 500;">${detail.award}</em>ETH)
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

		
		
		
		<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/zepto.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		
	</body>
</html>