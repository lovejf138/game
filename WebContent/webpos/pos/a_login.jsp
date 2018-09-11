<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/public.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1">
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/cryptographic.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<style type="text/css">
	</style>
<script>

function login() {
	var storenumber = document.getElementById("name").value;
    var storeReg=/^-?\d{10}$/;//商户id正则表达
    var menReg=/^-?\d{3,10}$/;//门店id正则表达
	 if(document.getElementById("name").value==""){
	        //这里写你要提示的操作
	         alert("请输入商户ID!");
	        return false;
	 }
   
	     if(document.getElementById("pass").value==""){
			    //这里写你要提示的操作
			     alert("请输入密码!");
			        return false;
		 }
	   
	    //加密
	 	 var shaObj = new amallEncryption($("#pass").val());
	 	 var str = shaObj.getHash();
	 	 //alert(str);
	 	 $("#pass").val(str);
	 	 jQuery("#theForm").submit();
	 	 return true;
		}
		var EnterSubmit = function(evt){
		evt = window.event || evt;
	 	if (evt.keyCode == 13){
	 	   login();
 		}
	}
	window.document.onkeydown=EnterSubmit;
	jQuery(document).ready(function(){
     if(top.location!=this.location)top.location=this.location;//跳出框架在主窗口登录
	jQuery('#name').focus();	
	});
	
	window.onload = function() {
		var mmxgcg = document.getElementById("mmxgcg").value;
		var zt = document.getElementById("zt").value;
		var shid = document.getElementById("shid").value;
		
		if (mmxgcg != "") {
			alert(mmxgcg);
		}
		if (zt != "") {
			alert(zt);
		}
		if (shid != "") {
			alert(shid);
		}
	};
</script>
</head>
<body class="login">
	
	<div class="login-c">
		<form action="<%=request.getContextPath()%>/do_xqsdf.do" id="theForm" name="theForm" method="post" class="login-form">
			<h2>登录</h2>
			<dl>
				<dt>商户ID:</dt>
				<dd><input type="text" name="name" id="name"  value="${name}"/>
				
				<c:if test="${shid !=null }">
				<b style="color: red; text-align: center;">${shid}</b>
				</c:if>
				</dd>
			</dl>
			<dl>
				<dt>密码:</dt>
				<dd><input type="password" name="pass" id="pass"/></dd>
			</dl>
			
			<dl>
				<dt></dt>
				<dd><button  style="width:100px" onclick="login();">登陆</button></dd>
			</dl>
				
		</form>
	</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/date/laydate.dev.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/main.js"></script>
</html>