<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsbase.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/goodsstyle.css" />
    <link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
    <title>商品</title>  
</head>

<body style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">
	<div class="wrap">
	   
	   <!--主体区域-->
	   <div class="container" style="padding:0px 0">
	   	<!-- 通知信息 -->
	   	<div class="tzxx" style="margin-top: 0px;">
	   		<i class="icons">&#xea61;</i>
	   		<p><marquee>通知公告信息通知公告信息通知公告信息通知公告信息</marquee></p>
	   	</div>
        
		<!--实物商品-->	
		<div class="ztmc" style="margin-bottom: 60px;margin-top: 0px;">
			
			<ul class="ztmc_list">
			
				<c:forEach items="${objs }" var="goods">
				<li>
					<a><div class="img" style="padding:0px"><img style="max-width: 100%;max-height:100%" src="<%=request.getContextPath()%>/upload/${goods.img}"/></div></a>
					<div class="prolist_info">
						<h4>${goods.name}</h4>
						<p class="grey">原价:￥<span>${goods.price}</span></p>
						<p class="red">抢购价:￥<span>${goods.nowprice}</span></p>
						<div class="add" ><a onclick="gotobuy('${goods.id}')">立即抢购</a></div>
					</div>
				</li>
				</c:forEach>
				</ul>
		</div>
 

		</div>
	
		
		<!--导航栏-->
	    <div class="footer">
	      	<ul>
				<li class="active"><a href="index.html"><p><i class="icons">&#xe66b;</i></p><h4>首页</h4></a></li>
				<li><a href="pm.html"><p><i class="icons">&#xe68f;</i></p><h4>排行</h4></a></li>
				<li><a href="help.html"><p><i class="icons">&#xe608;</i></p><h4>客服</h4></a></li>
				<li><a href="my.html"><p><i class="icons">&#xe607;</i></p><h4>我的</h4></a></li>
			</ul>
       </div>
       
	</div>
	
<!-- <div class="zzc"></div>
<div class="sxlist xqfa">
	<div class="maintitle">
		<img src="img/pic1.png"/>
		<h5>小米笔记本电脑</h5>
		<p class="grey">原价:<span>2000</span>元</p>
		<p class="red">抢购价:<span>600</span>元</p>
		<i class="icons">&#xe611;</i>
	</div>
	<div class="maininfo">
		<h4>请选择抢购方案</h4>
		<ul>
			<li>买单</li>
			<li>买双</li>
		</ul>
		<h4>购买数量</h4>
		<div class="slxz"><input type="button" value="-"/><input type="number" value="1" id="num" /><input type="button" value="+"/></div>
	</div>
	<div class="hjje"><p class="gxzf">您共需支付:<span class="red">￥<b>600</b>元</span></p><p>账户余额:<span class="red"><b>600</b>金币</span></p></div>
    <a href="javascript:;" class="ok">确定</a>
</div> -->

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/modernizr.js"></script>
    <script src="<%=request.getContextPath()%>/webpos/pos/assets/js/amazeui.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/prefixfree.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
		

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?d08321caa4ded9e8f406743dac85e273";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

<script>
var waitdialog=null;
function gotobuy(_goodsid){
	
	waitdialog = new TipBox({type:'load',str:'正在请求。。。',hasBtn:false});
	$.ajax({
		async:true,
		type:'post',
		url:'buygoodstry.do',
		data:{goodsid:""+_goodsid},
		dataType:'json',
		success:function(result,textStatus){
			waitdialog.destroy();
			if(result.result=="success"){
				//跳转到购买页面
				window.location.href='goodsbuypage.do?goodsid='+_goodsid;
			}else if(result.result=="not_login"){
				//跳转到登录页面
				window.location.href='login.do';
			}else if(result.result=="goods_down"){
				//提示商品下架
				new TipBox({type:'error',str:'商品已下架，请刷新页面',hasBtn:true});
			}else if(result.result=="balance_not_enough"){
				//提示余额不足
				new TipBox({type:'error',str:'余额不足',hasBtn:true});
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			waitdialog.destroy();
			new TipBox({type:'error',str:'网络错误',hasBtn:true});
			
		}
	});
}
</script>
</body>
</html>