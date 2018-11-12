<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsbase.css" />
  		<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsstyle.css" />
  		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=2">
  		<style>
  		#weixinTip{
			position:fixed;
			left:0;
			top:0;
			height:winHeight;
			width:100%;
			z-index:1000;
			background-color:rgba(0,0,0,0.8);
			filter:alpha(opacity=80);
		}
		#weixinTip p{
			text-align:center;
			margin-top:10%;
			padding-left:5%;
			padding-right:5%;
		}
  		</style>
</head>
<body>
	<div class="wrap">
		<!--顶部-->
	    <div class="fixed_top">
	    	<div class="top-l"><a href="javascript:window.history.back()"><i class="icons">&#xe600;</i></a></div>
	    	<h1>充值</h1>
	    	
	    </div>

	    <div class="container_inner">
	    	<div class="cz">
	    	<h3>充值金额:</h3>
	    	<ul class="cz_list">
	    	 
	    	   <li class="on">10</li>
	    	   <li>100</li>
	    	   <li>300</li>
	    	   <li>500</li>
	    	   <li>1000</li>
	    	   <li>3000</li>
	    	   <li>5000</li>
	    	   <li><input type="text" value="其他金额"/></li>
	    	</ul>
	    	</div>
	    	<input id="error_time" value="${localtime}" style="display:none"/>
	    	<input id="baseurl" value="<%=request.getContextPath()%>" style="display:none"/>
	    	<div class="zf">
	    		<h3>支付方式:</h3>
	    		<ul class="zf_list">
	    			<!--<li  class="on" data-name="weixin"><i class="icons" style="color:limegreen">&#xe62f;</i>微信支付</li>  -->
	    			<li data-name="alipay"><i class="icons"  style="color:deepskyblue">&#xe646;</i>支付宝</li>
	    			<li class="on" data-name="bank"><i class="icons"  style="color:deepskyblue">&#xe61e;</i>快捷</li>
	    			<!-- <li data-name="bank"><i class="icons"  style="color:orangered">&#xe61e;</i>客服支付</li>-->
					
	    		</ul>
	    	</div>
	    	<p>支付宝支付请不要在微信浏览器中！！若一直充值失败，请微信联系客服：17159332040</p>
	    	<a href="javascript:;" id="go_btn" class="ljzf">立即支付</a>
	    </div>

    </div>
    
</body>
<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/m/cz_123.js?t=1"></script>

 <script>
 

 </script>
</html>