<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>个人中心</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsbase.css?t=4" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsstyle.css?t=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/assets/css/amazeui.min.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/assets/css/app.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">
<style>
.input_class{
background-color: #EBEBEB;border-radius: 0.438rem;color: #4F4F4F;
     font-size: 15px;
    padding: 5px;
    outline: none;
    border: 0.063rem solid transparent;
    width: 100%;
    max-width: 52%;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
.sumbit_login{
    font-size: 15px;
    padding: 10px;
	float: right;
    font-weight: 600;
    line-height: normal;
    line-height: 1;
    text-align: center;
    color: #FFFFFF;
    background-color: #21a769;
    border-radius: 1.563rem;
    text-transform: uppercase;
    outline: none;
    border: none;
 
    cursor: pointer;
    
    width: 100%;
    max-width: 36%;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
</style>


</head>
<body>
<div class="gr-sh">
	<div class="gr-sh-zuo">
		<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/touxiang2.jpeg">
	</div>
	<div class="gr-sh-you">
		<div class="gr-sh-you1">
			
			<p>${user.id_short}</p>
			
			<p style="float:right"><a href="personal.do" style="display: block; width: 100px;text-align: center;height: 32px;line-height: 32px;border: 1px solid #c52600;color: #c52600;border-radius: 45px;">个人信息</a></p>
		</div>
		
	</div>
</div>
<input id="error_time" value="${localtime}" style="display:none"/>
<input id="balance" value="${user.balance}" style="display:none" type="text">
<button id="div_btn_gift" style="display:none"  data-am-modal="{target: '#doc-modal-spread', closeViaDimmer: 0, width: 380, height: 550}"></button>

<div class="gr-zh">
	<div class="gr-zh-zuo">
		<p><span>¥${user.balance}</span></p>
		<!-- <p>ETH</p> -->
	</div>

	<div class="gr-zh-you">
		<ul>
			
			<li onclick="javascript:window.location.href='chongzhi.do?phone=${user.phone}'">
				<a>
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/k4.png">
					<p>充值</p>
				</a>
			</li>
			<li onclick="javascript:window.location.href='tixian.do'">
				<a >
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/k3.png">
					<p>提现</p>
				</a>
			</li>
		</ul>
	</div>
</div>
<div class="fgx"></div>
<div class="gr-xia">
	<ul>
		<li>
			<a href="orders.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge1.png">
				<p>我的订单</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		<li>
			<a href="tixianmingxi.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge2.png">
				<p>提现记录</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		<li>
			<a href="chongzhimingxi.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge3.png">
				<p>充值和奖励记录</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		
		<li>
			<a href="childs.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/child.png">
				<p>推广用户列表</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		
		<%-- <li>
			<a href="childcanyu2.do">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/childpro.png">
				<p>推广用户收益列表</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li> --%>
		
		<li id="div_gift">
			<a>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge4.png">
				<p>邀请好友得随机红包</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		<li>
			<a style="text-align: center;font-size: 20px;">
				客服微信:<font style="color: #cc9090;">17159332040</font>
			</a>
		</li> 
		<li>
			<a style="text-align: center;font-size: 20px;">
				客服QQ:<font style="color: #cc9090;">1206480043</font>
			</a>
		</li>
		
	</ul>
</div>

<div class="kong"></div>
 <div class="footer" style="background: #000000;">
	      	<ul>
				<li <c:if test="${beishu ==2}"> class="active" </c:if>><a href="goods.do?beishu=2"><p><i class="icons">&#xe66b;</i></p><h4>特价1</h4></a></li>
				<li <c:if test="${beishu ==6}"> class="active" </c:if>><a href="goods.do?beishu=6"><p><i class="icons">&#xe66b;</i></p><h4>特价2</h4></a></li>
				<li <c:if test="${beishu ==250}"> class="active" </c:if>><a href="goods.do?beishu=250"><p><i class="icons">&#xe66b;</i></p><h4>特价3</h4></a></li>
				<li class="active"><a  href="gerenzhongxin.do"><p><i class="icons">&#xe607;</i></p><h4>我的</h4></a></li>
			</ul>
 </div>


<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-spread" style="top:auto;">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="line-height: 20px;font-size: 20px;">可长按保存图片发送给好友<!-- 分享给好友，永享好友购买金额的1%收益 -->
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    
    <img id="bg_img" src="<%=request.getContextPath()%>/webpos/pos/front/img/share-bg.jpeg" style="display:none;max-width:100%"/>
    
    <div class="am-modal-bd" style="display:none;margin-top: 10px;" id="spread_inpiut">
      <a id="spread-text">http://out-sale.com/goods.do?parent=${user.id_md5}</a>
    </div>
    <div id="qrcode-spread" style="display:none;text-align: center;margin: auto;">
    </div>
   
     <canvas id="myCanvas" style="display:none" width="" height=""></canvas>

	<img id="canimg" src=""/>
  </div>
</div>

</body>

<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/m/geren_123.js?t=4"></script> --%>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?d08321caa4ded9e8f406743dac85e273";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

$("#qrcode-spread").qrcode({ 
    render: "canvas", //table方式 
    width: 100, //宽度 
    height:100, //高度 
    text: $("#spread-text").html() //任意内容 
});


function convertCanvasToImage(canvas) {
	var image = new Image();
	image.src = canvas.toDataURL("image/png");
	return image;
}


$("#div_gift").click(function(event){

	var qrcod = document.getElementById("qrcode-spread").lastChild;
	var bgimg = new Image();
	bgimg.src=$("#bg_img").attr("src");
	bgimg.width=300;
	bgimg.height=500;
	
	var bgcanvas = document.getElementById("myCanvas");
	bgcanvas.width = bgimg.width;
	bgcanvas.height = bgimg.height;
	
	var ctx = bgcanvas.getContext("2d");
	bgimg.crossOrigin="*";
	
	bgimg.onload = function(){
		ctx.drawImage(bgimg,0,0,bgimg.width,bgimg.height);
		ctx.drawImage(qrcod,0,bgimg.height-100,qrcod.width,qrcod.height);
		var srcImg = bgcanvas.toDataURL('image/png');
		document.getElementById("canimg").setAttribute('src',srcImg);
		$("#div_btn_gift").click();
	}
	
	
});
</script>
</html>