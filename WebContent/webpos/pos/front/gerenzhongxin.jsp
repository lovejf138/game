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
		</div>
		
	</div>
</div>
<input id="error_time" value="${localtime}" style="display:none"/>
<input id="balance" value="${user.balance}" style="display:none" type="text">
<div class="gr-zh">
	<div class="gr-zh-zuo">
		<p><span>${user.balance}</span></p>
		<p>ETH</p>
	</div>

	<div class="gr-zh-you">
		<ul>
			
			<li>
				<a data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 250}">
					<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/k4.png">
					<p>充值</p>
				</a>
			</li>
			<li>
				<a data-am-modal="{target: '#doc-modal-2', closeViaDimmer: 0, width: 400, height: 200}">
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
			<a href="wodedingdan.html">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge1.png">
				<p>我的参与记录</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		<li>
			<a href="tixianmingxi.html">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge2.png">
				<p>提现记录</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		<li>
			<a href="duihuanjilu.html">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge3.png">
				<p>充值记录</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		
		<li>
			<a href="duihuanjilu.html">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/ge4.png">
				<p>邀请好友永享收益</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		
		<li>
			<a href="duihuanjilu.html">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/question.png">
				<p>中奖及玩法说明</p>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/you.png">
			</a>
		</li>
		<li>
			<a>
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/kefu.png">
				<p>客服QQ：1092888743</p>
			</a>
		</li>
		
		<li>
			<a style="text-align: center;font-size: 20px;">
				官方邮箱<font style="color: #cc9090;">eth.club@mail.com</font>
			</a>
		</li>
	</ul>
</div>

<div class="kong"></div>
<div class="daohang">
		<ul>
			<li><a href="index.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s2.png">
					<p>首页</p>
			</a></li>
			<li><a href="kj.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s4.png">
					<p>开奖</p>
			</a></li>
			<li><a href="paihang.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s6.png">
					<p>排行</p>
			</a></li>
			<li><a href="gerenzhongxin.do"> <img
					src="<%=request.getContextPath()%>/webpos/pos/front/img/index/s7.png">
					<p style="color: #e01222;">我的</p>
			</a></li>
		</ul>
</div>


<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1" style="top:auto;">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="line-height: 20px;font-size: 25px;">充值
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd" style="line-height: 20px;font-size:15px">
      请将以太坊转入以下地址完成充值
   
     <p><a style="user-select:auto; -webkit-user-select:auto; -moz-user-select:auto; -ms-user-select:auto; user-select:auto;">0x9EEa8Bd7317c0233fCd4E1846368f6eBfC9F06B7</a>
     </p>
     <img style="width:150px" src="<%=request.getContextPath()%>/webpos/pos/img/imtoken1.png"></img>
     
    </div>
  </div>
</div>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2" style="top:auto;">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="line-height:20px;font-size: 20px;">提现
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    
    <div class="am-modal-bd" style="margin-top: 10px;">
      <input class="input_class" name="eth_withdraw" id="eth_withdraw" placeholder="请输入提现数量" type="number"/>   
      <input id="btn_withdraw" value="提交" type="button" class="sumbit_login"/>
    
      <p id="withdraw_tip" style="margin-top:10px;float: left;font-size: 15px;font-align:left;color:red"></p>
     
      <p style="margin-top:10px;float: left;font-size: 15px;font-align:left">1.至少0.01个以太坊起提，半个小时内到账</p>
      
      <p style="margin-top:10px;float: left;font-size: 15px;font-align:left">2.每次提现，收取1%的网络矿工费(至少0.001ETH)</p>
   
   	 
    
    </div>
  </div>
</div>
</body>

<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/geren_123.js?t=1"></script>

</html>