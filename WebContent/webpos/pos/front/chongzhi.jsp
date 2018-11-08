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
  		<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
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
	    	   <li class="on">100</li>
	    	   <li>300</li>
	    	   <li>500</li>
	    	   <li>1000</li>
	    	   <li>2000</li>
	    	   <li>3000</li>
	    	   <li>5000</li>
	    	   <li><input type="text" value="其他金额"/></li>
	    	</ul>
	    	</div>
	    	<div class="zf">
	    		<h3>支付方式:</h3>
	    		<ul class="zf_list">
	    			<li  class="on" data-name="weixin"><i class="icons" style="color:limegreen">&#xe62f;</i>微信支付</li>
	    			<li data-name="alipay"><i class="icons"  style="color:deepskyblue">&#xe646;</i>支付宝</li>
	    			<li data-name="alipay"><i class="icons"  style="color:deepskyblue">&#xe646;</i>银行卡</li>
	    			<li data-name="bank"><i class="icons"  style="color:orangered">&#xe61e;</i>客服支付</li>
					
	    		</ul>
	    	</div>
	    	<a href="javascript:;" class="ljzf">立即支付</a>
	    </div>

    </div>
    
</body>

 <script>
 	$(function(){
      $(document).on("click",".btns a",function(){
 	     $(".zz").remove();
 	  })
      $(".cz_list li").click(function(){
      	 $(this).addClass("on").siblings().removeClass();
      }).not(".cz_list li:last").click(function(){$(".cz_list li:last input").val("其他金额").css("color","#666")})
      $(".cz_list li input").focus(function(e){
      	 $(this).css("color","white").val("");
      	 e.stopPropagation()
      }).blur(function(){
      	 var reg=/^\d+$/;
      	 if(reg.test($(this).val())==false){
      	 	 zz({"info":"您所输入的信息有误","txt1":"确定","txt2":"关闭"});
      	 	 $(this).val("其他金额");
      	 	 $(window).on("click",".btns a",function(){$(".zz").remove()})
      	 }else{
      	 	 $(this).hide().parent().html($(this).val())
      	 }
      })
      var that=null;
      $(".zf_list li").click(function(){
      	 $(this).addClass("on").siblings().removeClass(); 
      })
      
      $(".ljzf").click(function(){
      	gdata('cz',{"price":$(".cz_list .on").text()*1,"pay_type":$(".zf_list .on").attr("data-name")},function(data){
			if(data.retInt==1){
				var url = '/tourl.php?type=zf&id=_id_';
				url = url.replace('_id_',data.retRes.orders_id);
				window.location.href = url;
			}else{
				zz({"info":data.retErr,"txt1":"确定","txt2":"关闭"});
				$(window).on("click",".btns a",function(){$(".zz").remove()})
			}
		})
      })
 	})
 </script>
</html>