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
	<title>惠来社区</title>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/login_default.css">
	<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
	<!--必要样式-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/front/css/login_styles.css?t=2">
	
</head>
<body>
	<input id="error_time" value="${localtime}" style="display:none"/>
	<div class='login'>
	  <div class='login_title'>
	    <span>惠来社区--注册</br></span>
	  </div>
	  <div class='login_fields'>
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/phone.png'>
	      </div>
	      
                 <input style="display:none" name="remark" id="remark" value="">
                 <input style="display:none" name="geetest_challenge" id="geetest_challenge" value="">
                 <input style="display:none" name="geetest_validate" id="geetest_validate" value="">
                 <input style="display:none" name="geetest_seccode" id="geetest_seccode" value="">
	      <input placeholder='请输入手机号' id="address" type='text'>
	        <div class='validation'>
	          <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/tick.png'>
	        </div>
	      </input>
	    </div>
	    
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/verify.png'>
	      </div>
	         
	      <input placeholder='请输入验证码' id="verify" type='text' >
	      
	     
	        <div id="div_verify" style="position: absolute; z-index: 1;right: 10px; top: 6px;opacity: 1;">
	            <input type='button' id="btn_sendverify"  style="border-radius: 50px;background: transparent;padding: 2px 5px;border: 2px solid #2fa0ec;color: #2fa0ec;text-transform: uppercase;font-size: 11px;" value='发送验证码'>
	        </div>
	      </input>
	    </div>
	    
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/lock_icon_copy.png'>
	      </div>
	      <input placeholder='输入密码' id="pass" type='password'>
	      <div class='validation'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/tick.png'>
	      </div>   
	    </div>
	    
	    <div id="login_fields__repassword" class='login_fields__password' >
	      <div class='icon'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/lock_icon_copy.png'>
	      </div>
	      <input placeholder='请确认密码' id="repass" type='password'>
	      <div class='validation'>
	        <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/tick.png'>
	      </div>   
	    </div>
	    
	    <a id="error_msg" style="    margin-left: 36px;color: red;"></a>
	    <div class='login_fields__submit' style="    top: 15px;width: 40%;">
	      <input type='submit' id="btn_login" value='立即注册'>
	    </div>
	  </div>
	  <div class='success'>
	    <h2>登录成功</h2>
	    <p>正在跳转到首页...</p>
	  </div>
	  <div class='disclaimer'>
	    <p style="font-size: 18px;">已注册,<a href='login.do'>立即登陆</a></p>
	  </div>
	</div>
	<div class='authent'>
	  <img src='<%=request.getContextPath()%>/webpos/pos/front/img/login/puff.svg'>
	  <p>正在请求...</p>
	</div>
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/webpos/pos/js/gt.js"></script>
	<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/cryptographic.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/zepto.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/m/reg_123.js?t=2"></script>
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