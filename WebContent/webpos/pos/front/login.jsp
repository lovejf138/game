<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>天注定－登录</title>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/login_default.css">
	<!--必要样式-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/login_styles.css">
	
</head>
<body>

	<div class='login'>
	  <div class='login_title'>
	    <span>天注定</span>
	  </div>
	  <div class='login_fields'>
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/user_icon_copy.png'>
	      </div>
	      <input placeholder='请输入以太坊地址' type='text'>
	        <div class='validation'>
	          <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/tick.png'>
	        </div>
	      </input>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/lock_icon_copy.png'>
	      </div>
	      <input placeholder='密码' type='password'>
	      <div class='validation'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/tick.png'>
	      </div>
	    </div>
	    <div class='login_fields__submit'>
	      <input type='submit' id="btn_login" value='登录'>
	    </div>
	  </div>
	  <div class='success'>
	    <h2>登录成功</h2>
	    <p>正在跳转到首页...</p>
	  </div>
	  <div class='disclaimer'>
	    <p>无需注册，以太坊地址直接登录。首次登录，输入的密码为您下一次登录密码，忘记密码请联系客服QQ:1092888743</p>
	  </div>
	</div>
	<div class='authent'>
	  <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/puff.svg'>
	  <p>认证中...</p>
	</div>
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/index_123.js"></script>

</body>
</html>