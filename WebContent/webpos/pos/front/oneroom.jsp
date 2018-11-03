<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>猜猜猜</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/bootstrap-grid.min.css?t=1">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=5">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_base1.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_home1.css?t=2">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room_reset.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room.css?t=4">

		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_style.css?t=1">
<style>
input::-webkit-input-placeholder {
  /* placeholder颜色  */
        color: #fff;
        /* placeholder字体大小  */
         font-size: 15px;
         font-weight:600;
         /* placeholder位置  */
        text-align: left;
    }
</style>
		
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
					<p class="qi_name_class">${aw.name}季</p>
						<div class="kj-p5 qi_name_div">
						<p class="red-ball2 qi_ball" >${aw.no1}</p>
						<p class="red-ball2 qi_ball" >${aw.no2}</p>
						<p class="red-ball2 qi_ball" >${aw.no3}</p>
						<p class="red-ball2 qi_ball" >${aw.no4}</p>
						<p class="red-ball2 qi_ball" >${aw.no5}</p>
						<p class="red-ball2 qi_ball">${aw.no6}</p>
						<p class="red-ball2 qi_ball">${aw.no7}</p>
						<p class="red-ball2 qi_ball">${aw.no8}</p>
						<p class="red-ball2 qi_ball">${aw.no9}</p>
						<p class="red-ball2 qi_ball">${aw.no10}</p>
						<p class="red-ball2 qi_ball">${aw.no11}</p>
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
					
					<div class="navList" onClick="window.location.href='onecanyu.do'">
						<i class="navIco-shang navico"></i>
						<span class="navText">
							历史
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
					  	
						<div style="text-align:center;margin-top:3px">
						余额：
						<font style="color:#ff7400;font-size: 20px;" id="my_balance">${user.balance}</font>ETH
						</div>
					
					<div class="in-line-left" style="top: 45px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 45px;">
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
				
				<div class="in-content-title">
					<div style="text-align:center;padding-top: 10px;">
						<a style="font-weight: bold;font-size:16px;color: #6d542a;">猜猜什么号码会出现在
						<font style="font-weight:800;font-size:13px;padding-left: 10px;padding-right: 10px;padding-top: 2px;padding-bottom: 2px;background-color: rgb(35, 29, 29); border-radius: 50px; height: 20px; width: 20px; color: #fff; line-height: 20px; text-align: center;">${systemnum}</font>
						之前？</a>
						</div>
					<div style="text-align:center;padding-top: 10px;">
						<a style="font-weight: bold;font-size:16px">猜中就获得一倍收益</a>
						</div>
						 <div style="text-align:center; font-size: 12px; color: #757272;padding-top: 5px;font-weight: bold;">
						(每季只能选一个号码)
						</div>
						
						<div class="in-line-left" style="top: 65px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 65px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				
				<div class="in-content-links" style="background-image: linear-gradient(to bottom,#e4e4e4,#ffffff);display: block;">
				
					<div class="kj-p5 qi_name_div" style="height: 190px; line-height: 190px;">
						<p class="red-ball2 xuan_ball _myamount1_ball"  id="select_ball1">1</p>
						<p class="red-ball2 xuan_ball _myamount2_ball"  id="select_ball2">2</p>
						<p class="red-ball2 xuan_ball _myamount3_ball"  id="select_ball3">3</p>
						<p class="red-ball2 xuan_ball _myamount4_ball"  id="select_ball4" >4</p>
						<p class="red-ball2 xuan_ball _myamount5_ball"  id="select_ball5" >5</p>
						<p class="red-ball2 xuan_ball _myamount6_ball"  id="select_ball6">6</p>
						<p class="red-ball2 xuan_ball _myamount7_ball" id="select_ball7">7</p>
						<p class="red-ball2 xuan_ball _myamount8_ball"  id="select_ball8">8</p>
						<p class="red-ball2 xuan_ball _myamount9_ball" id="select_ball9">9</p>
						<p class="red-ball2 xuan_ball _myamount10_ball"  id="select_ball10">10</p>
						<p class="red-ball2 xuan_ball _myamount11_ball"  id="select_ball11">11</p>
						<p style="line-height: 40px;float: left;margin-top: 15px;margin-left: 15px;color: #5d5959;width: 150px; font-size: 15px; font-weight: 600;" id="select_number">已选号码：1</p>
						
					</div>
						
					<div style="line-height: 30px; height: 30px;">
		
						<input type="text" class="input-address" id="input_amount" maxlength="12" placeholder="输入ETH数量"
						      style="font-size: 15px;font-weight: 800;color: #ff0000 !important;"/>
					
          				<input id="btn_ok" value="确定" type="button" class="input-login" style="width:60px"/>
               		</div>
					
					<div class="in-line-left in-content-left" style="top: 230px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right in-content-right" style="top: 230px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					
				</div>
				
				<div class="in-content-fellow">
						
						<div style="text-align:center;margin-top:3px">
						已选号码：
						<font style="color:#ff7400;font-size: 20px;" id="has_haoma">${has_haoma}</font>
						</div>	
						
					<div style="text-align:center;margin-top:3px">
						数量：
						<font style="color:#ff7400;font-size: 20px;" id="has_number">${has_number}</font>ETH
						</div>	
					
				</div>
				
				
				</div>
				
			</div>
		</div>
		</section>
	<!-- content end -->
	</div>
	
		<div id="sampledata3" class="bringins-content" style="color: white;">
			
		  <section id="content" >
			<div class="in-content">
			<div class="in-content-line"></div>
			<div class="in-content-box">
			
				<div class="in-content-title" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					<div style="text-align:center">
						<font style="color:#ff7400;font-size: 20px;">收金子啦</font>
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
						季节:
						<font style="color:#666;font-size: 20px;" id="kj_qiname"></font>
						</div>
						
						 <div style="text-align:center;margin-top:3px">
						参与数量：
						<font style="font-size: 18px;" id="kj_join_number">0</font>ETH
						</div>
						
						<div style="text-align:center;margin-top:3px">
						获得：
						<font style="color:#ff7400;font-size: 20px;" id="kj_award_number">0</font>ETH
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
					<p class="red-ball" id="kj_no6">6</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no7">7</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no8">8</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no9">9</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no10">10</p>
				</li>
				<li>
					<p class="red-ball" id="kj_no11">11</p>
				</li>
			</ul>
				
						
					<div style="line-height: 30px; height: 30px;">
						<input id="btn_next" value="继续" onClick="window.location.href='testroom.do'" type="button" class="input-login" style="float: right;width:80px;background-color: #ff5292;font-size:15px;margin: auto; max-width: 80px;"/>
               	
						<input id="btn_detail" value="详情" onClick="window.location.href='dtroom.do?type=_now&qiname=${nextname}'" type="button" class="input-login" style="float: left;width:60px;font-size:15px"/>
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
           				<th>播种数量(种子)</th>
           				<th>获得数量(金子)</th>
           			   </tr>
   					 </thead>
   					 <tbody id="tbody">
   					   
					   
   					 </tbody>
					</table>
					</div>
					
					
					</div>
				
			</div>
		</div>
		</section>
		</div>
	
	    <audio src="<%=request.getContextPath()%>/webpos/pos/front/gold.mp3" id="gold_audio" style="display:none"></audio>
	
		<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/zepto.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
		<script src='<%=request.getContextPath()%>/webpos/pos/front/js/oneroom_123.js?t=1'></script>
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