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
		<title>竞技</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/bootstrap-grid.min.css?t=1">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=5">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_base1.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_home1.css?t=2">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room_reset.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room.css?t=4">

		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_style.css?t=3">
		
		
	</head>
	<body style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
		<div id="wrap">	
			<input id="baseurl" value="<%=request.getContextPath()%>" style="display:none"/>
			<input id="error_time" value="${localtime}" style="display:none"/>
			<input id="roomid" value="${room.id}" style="display:none"/>
			<input id="userid" value="${user.user_id}" style="display:none"/>		
			<input id="nextname" value="${nextname}" style="display: none">
			<input id="nextsecond" value="${nextsecond}" style="display: none">
		
			<nav class="navbox navbox_class2">
				<div class="navContent1">
					<c:forEach items="${awards }" var="aw">
					<p class="qi_name_class">${aw.name}季</p>
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
					<div class="navList" onClick="window.location.href='droom.do?qiname=${nextname}&type=_last'">
						<i class="navIco-shang navico"></i>
						<span class="navText">
							上一季
						</span>
					</div>
					
				</div>					
				<div class="pothook">
				</div>
			</nav>		
		
	
		<div class="in-body" style="margin-top: 240px;">

	<!-- content begin -->
	<section id="content" >
		<div class="in-content" style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);padding-bottom: 20px;">
			<div class="in-content-line"></div>
			<div class="in-content-box">
			
				<div class="in-content-title" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					  <div style="text-align:center">
						在线人数
						<font style="color:#ff7400;font-size: 20px;" id="online_number">0</font>
						</div>
						
						 <div style="text-align:center;margin-top:3px">
						我的余额：
						<font style="font-size: 18px;" id="my_balance">${user.balance}</font>ETH
						</div>
						
						<div style="text-align:center;margin-top:3px">
						币池：
						<font style="color:#ff7400;font-size: 20px;" id="sumamount">${sumamount}</font>ETH
						</div>
					
					
					
					<div class="in-line-left" style="top: 85px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 85px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				<div class="in-content-links" style="background: #96ff96;display: block;">
				
					<div class="kj-p5 qi_name_div" style="height: 190px; line-height: 190px;">
						<p class="red-ball2 xuan_ball" style="background-color: rgb(255, 3, 3);" id="select_ball1">1</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball2">2</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball3">3</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball4" >4</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball5" >5</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball6">6</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball7">7</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball8">8</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball9">9</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball10">10</p>
						<p class="red-ball2 xuan_ball" style="background-color: rgb(29, 12, 12);" id="select_ball11">11</p>
						<p style="line-height: 40px;float: left;margin-top: 15px;margin-left: 15px;color: #f10909;width: 150px; font-size: 15px; font-weight: 900;" id="select_number">已选号码：1</p>
						
					</div>
						
					<div style="line-height: 30px; height: 30px;">
						<input type="text" class="input-address" id="input_amount" maxlength="12" placeholder="输入参与数量"/>
					
          				<input id="btn_ok" value="提交" type="button" class="input-login" style="width:60px"/>
               		</div>
					
					<div class="in-line-left in-content-left" style="top: 230px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right in-content-right" style="top: 230px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					
				</div>
				
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
				
				
				 
				<div class="in-content-fellow" style="background: #111;">
				 <div class="in-fellow-well">
					<table class="am-table">
   					 <thead>
     				   <tr style="color:white">
            			<th>号码</th>
            			<th>人数</th>
           				<th>总数量(ETH)</th>
           				<th>最大数量(ETH)</th>
           				<th>我的(ETH)</th>
           			   </tr>
   					 </thead>
   					 <tbody>
   					    <c:forEach items="${details }" var="sd">
					        <tr style="height: 30px;line-height: 30px;">
					            <td class="td_font" style="font-size:15px;font-weight:800">${sd.number}</td>
					            <td class="td_font" id="_count${sd.number}">${sd.count}</td>
					            <td class="td_font" id="_sumamount${sd.number}">${sd.sumamount}</td>
					            <td class="td_font" id="_maxamount${sd.number}">${sd.maxamount}</td>
					            <td class="td_font" id="_myamount${sd.number}" style="color: #ff7400;">${sd.myamount}</td>
					        </tr>
					     </c:forEach>
					       
   					 </tbody>
					</table>
					</div>
					
					
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
			<!--end </div>send-->
	
		<div id="sampledata3" class="bringins-content" style="color: white;">
			
		  <section id="content" >
			<div class="in-content">
			<div class="in-content-line"></div>
			<div class="in-content-box">
			
				<div class="in-content-title" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					<div style="text-align:center">
						<font style="color:#ff7400;font-size: 20px;">开奖啦</font>
					</div>
					
					<div class="in-line-left">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				
				<div class="in-content-links" style="color:#666;display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					 	 <div style="text-align:center;">
						期号:
						<font style="color:#666;font-size: 20px;" id="kj_qiname"></font>
						</div>
						
						 <div style="text-align:center;margin-top:3px">
						我的竞技数量：
						<font style="font-size: 18px;" id="kj_join_number">0.0</font>ETH
						</div>
						
						<div style="text-align:center;margin-top:3px">
						我的奖金数量：
						<font style="color:#ff7400;font-size: 20px;" id="kj_award_number">0.0</font>ETH
						</div>
					
					
					
					<div class="in-line-left" style="top: 90px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 90px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				
				<div class="in-content-links" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					<div class="gg-shu" style="text-align:center;width: 80%;margin: 0 auto;overflow: hidden;margin-top: .2rem;">
						
			<ul>
				<li>
					<p class="red-ball" id="kj_no1">1</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no2">2</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no3">3</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no4">4</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no5">5</p>
				</li>
				<li>
					<p class="blue-ball" id="kj_no6">6</p>
				</li>
				<li>
					<p class="blue-ball" id="kj_no7">7</p>
				</li>
				<li>
					<p class="blue-ball" id="kj_no8">8</p>
				</li>
				<li>
					<p class="blue-ball" id="kj_no9">9</p>
				</li>
				<li>
					<p class="blue-ball" id="kj_no10">10</p>
				</li>
				<li>
					<p class="blue-ball" id="kj_no11">11</p>
				</li>
			</ul>
				
						
					<div style="line-height: 30px; height: 30px;">
						<input id="btn_next" value="继续竞技" onClick="window.location.href='room.do'" type="button" class="input-login" style="float: right;width:80px;background-color: #ff5292;font-size:15px;margin: auto; max-width: 80px;"/>
               	
						<input id="btn_detail" value="详情" onClick="window.location.href='droom.do?type=_now&qiname=${nextname}'" type="button" class="input-login" style="float: left;width:60px;font-size:15px"/>
          			</div>
               		
					</div>
					<div class="in-line-left" style="top: 135px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 135px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					
				</div>
				
				 
				<div class="in-content-fellow" >
				 <div class="in-fellow-well">
					<table class="am-table">
   					 <thead>
     				   <tr style="color:#666;font-size: 16px;">
            			<th>号码</th>
           				<th>我的数量(ETH)</th>
           				<th>奖金数量(ETH)</th>
           			   </tr>
   					 </thead>
   					 <tbody id="tbody">
   					   
					        <!-- <tr style="height: 30px;line-height: 30px;">
					            <td class="td_font" style="font-size:15px;font-weight:800">1</td>
					            
					            <td class="td_font" >0.00001</td>
					            <td class="td_font" >0.0004</td>
					            
					        </tr> -->
					         
					        
					   
   					 </tbody>
					</table>
					</div>
					
					
					</div>
				
			</div>
		</div>
		</section>
		</div>
	
		<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/zepto.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=2"></script>
		<script src='<%=request.getContextPath()%>/webpos/pos/front/js/room_123.js?t=<%=System.currentTimeMillis()%>'></script>
		
	</body>
</html>