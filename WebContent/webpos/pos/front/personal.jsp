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
    <link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3"/>
     
    <title>个人信息</title>
<style>
	
</style>    
</head>
<body>
	<div class="wrap">
		<!--标题栏-->
		<div class="fixed_top">
			<div class="top-l"><a href="javascript:window.history.back()">返回</a></div>
			<h1>个人信息</h1>
		</div>

		<div class="container">
		<form class="login-form"  style="margin-top:34px">
			<ul>
				<li>
					<label>姓名:</label>
					<div class="username"><input type="text" value="${info.name}" id="name" placeholder="如张三"/></div>
				</li>
				<li>
					<label>手机号:&nbsp&nbsp${user.phone}</label>
				
				</li>
				<li>
					<label>收货地址:</label>
					<div class="username"><input type="text" value="${info.address}"  id="address" placeholder="如上海市**区**街道**小区"/></div>
				</li>
				<li>
					<label>银行卡开户名:</label>
					<div class="username"><input type="text" value="${info.cardname}" id="cardname" placeholder="如张三"/></div>
				</li>
				<li>
					<label>银行卡账号:</label>
					<div class="password"><input type="text" value="${info.cardno}"  id="cardno" placeholder="如62170019000000000000"/></div>
				</li>
				<li>
					<label>银行卡支行:</label>
					<div class="password"><input type="text" value="${info.cardbank}"  id="cardbank" placeholder="如建设银行上海支行"/></div>
				</li>
				
			</ul>
			<div class="log">
				<a class="btn-login" onclick="save();">保存</a>
			</div>
		</form>
		</div>
	</div>
</body>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
<script type="text/javascript">
var waitdialog=null;
function save(){
	
	waitdialog = new TipBox({type:'load',str:'正在请求。。。',hasBtn:false});
	$.ajax({
		async:true,
		type:'post',
		url:'savepersonal.do',
		data:{name:""+$("#name").val(),address:""+$("#address").val(),cardname:""+$("#cardname").val(),cardno:""+$("#cardno").val(),cardbank:""+$("#cardbank").val()},
		dataType:'json',
		success:function(result,textStatus){
			waitdialog.destroy();
			if(result.result=="success"){
				new TipBox({type:'success',str:'保存成功',hasBtn:true});
			 
			}else{
				
				new TipBox({type:'error',str:'保存失败,'+result.desc,hasBtn:true});
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			waitdialog.destroy();
			new TipBox({type:'error',str:'网络错误',hasBtn:true});
			
		}
	});
}
</script>
</html>