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
		<title>购买</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/bootstrap-grid.min.css?t=1">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=5">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_base1.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_home1.css?t=2">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room_reset.css">
		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/room.css?t=4">

		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/room_style.css?t=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsbase.css" />
  		<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsstyle.css" />
		
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
					<p class="qi_name_class">${aw.name}期</p>
						<div class="kj-p5 qi_name_div">
						<p class="red-ball2 qi_ball" >${aw.no1}</p>
						<p class="red-ball2 qi_ball" >${aw.no2}</p>
						<p class="red-ball2 qi_ball" >${aw.no3}</p>
						<p class="red-ball2 qi_ball" >${aw.no4}</p>
						<p class="red-ball2 qi_ball" >${aw.no5}</p>
						<p class="blue-ball2 qi_ball" >
						  <c:if test="${aw.result1 ==1}">大</c:if>
						  <c:if test="${aw.result1 ==0}">小</c:if>	
						</p>
						
					</div>
					</c:forEach>
				</div>
				<div class="navContent">
					<div class="navList c" onClick="window.location.href='goods.do'">
						
						<i class="navIco-fanhui navico"></i>
						<span class="navText">
							返回
						</span>
					</div>
					
					<div class="navList" onClick="window.location.href='orders.do'">
						<i class="navIco-shang navico"></i>
						<span class="navText">
							我的订单
						</span>
					</div>
					
				</div>					
				<div class="pothook">
				</div>
			</nav>		
		
	
		<div class="in-body" style="margin-top: 240px;">

	<!-- content begin -->
	<section id="content" >
		<div class="in-content" style="padding-bottom: 20px;">
			<div class="in-content-line"></div>
			<div class="in-content-box">
			
				<div class="in-content-title" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					  	
						<div style="text-align:center;margin-top:3px">
						余额：
						<font style="color:#ff7400;font-size: 20px;" id="my_balance">￥${user.balance}</font>
						</div>
						<a id="name_wait1">离${nextname}期结束：</a><a id="name_wait2" style="color:#ff7400">0</a>秒
					
					<div class="in-line-left" style="top: 60px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
					<div class="in-line-right" style="top: 60px;">
						<img src="<%=request.getContextPath()%>/webpos/pos/front/img/room/icon-line.png" alt="">
					</div>
				</div>
				
				<div class="in-content-title" >
					<div style="text-align:center;padding-top: 10px;">
						<a style="font-weight: bold;font-size:16px;color: #6d542a;line-height: 30px;">规则：根据粤11选5每期开奖结果,第一位号码数字大于第二位号码，则为大，第一位号码小于第二位号码，则为小
						</a>
					</div>
					
						
				</div>
					
				</div>
				
			</div>
		</div>
		</section>
	<!-- content end -->

<div class="zzc"></div>
<div class="sxlist xqfa" style="height: 240px;">
	<div class="maintitle">
	<input style="display:none" value="${goods.id}" id="goodsid"/>
		<img src="<%=request.getContextPath()%>/upload/${goods.img}"/>
		<h5>${goods.name}</h5>
		<p>原价:${goods.price}元</p>
		<p style="color: #1f1313;">抢购价:${goods.nowprice}元</p>
		
		<i class="icons">&#xe611;</i>
	</div>
	<div class="maininfo">
		<h4>请选择抢购方案(第${nextname}期)</h4>
		<ul>
			<li class="active" value="1">抢大</li>
			<li value="0">抢小</li>
		</ul>
		
	</div>
	<div class="hjje"><p class="gxzf">您共需支付:<span class="red">￥<b>${goods.nowprice}</b>元</span></p><p>账户余额:<span class="red"><b id="my_balance2">¥${user.balance}</b></span></p></div>
    <a href="javascript:;" class="ok">确定</a>
</div>


	</div>
	
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
				
				<div class="in-content-links" style="display:block;text-align: center;font-weight: bold;padding: 14px 0;font-size: 16px;">
					<div class="gg-shu" style="text-align:center;width: 80%;margin: 0 auto;overflow: hidden;margin-top: .2rem;">
						
					<div style="line-height: 30px; height: 30px;">
						<input id="btn_next" value="中奖结果" onClick="window.location.href='orders.do'" type="button" class="input-login" style="float: right;width:80px;background-color: #ff5292;font-size:15px;margin: auto; max-width: 80px;"/>
               	
						<input id="btn_detail" value="继续抢购" onClick="window.location.href='goodsbuypage.do?goodsid=${goods.id}'" type="button" class="input-login" style="float: left;width:60px;font-size:15px"/>
          			</div>
               		
					</div>
					
				</div>
				
			</div>
		</div>
		</section>
		</div>
	
	    <audio src="<%=request.getContextPath()%>/webpos/pos/front/gold.mp3" id="gold_audio" style="display:none"></audio>

<div class="footer">
	<div class="foot_l"><p>${goods.name}</p></div>
	<div class="foot_r"><a href="javascript:;" class="ljgm">￥${goods.nowprice}抢购</a></div>
</div>

		<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/zepto.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
		<script src='<%=request.getContextPath()%>/webpos/pos/front/js/goods2_123.js?t=4'></script>
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