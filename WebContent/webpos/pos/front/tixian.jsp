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
	    	<div class="top-l"><a href="gerenzhongxin.do"><i class="icons">&#xe600;</i></a></div>
	    	<h1>提现</h1>
	    </div>
	    
	    <input value="${orderid}" id="orderid" style="display:none"/>

	    <div class="container" style="padding-bottom:0">
	    	<div style="background:#fff;margin-bottom:10px" id="yysb">
	    		<h4 style="height:40px;line-height:40px;text-indent:10px;color:#bbb;font-size:16px">可提现金额(100起提)</h4>
	    		<p style="height:80px;line-height:60px;text-align:center;color:#666;font-size:24px">${user.balance}</p>
	    	</div>
	    	<div class="tx_form">
	    	<form>
		    	<ul class="sm_list">
		    	  <li><p>手机号：</p><input type="text" value="${user.phone}" placeholder="请输入手机号码" id="phone" name="phone" /></li>
		    	  <li><p>姓名：</p><input type="text" value="${info.cardname}" placeholder="请输入开户名" id="name" name="name"/></li>
		    	  <li><p>银行卡号：</p><input type="text" value="${info.cardno}" placeholder="请输入银行卡账号" id="cardno" name="cardno"/></li>
		    	   <li><p>支行名称：</p><input type="text" value="${info.cardbank}" placeholder="请输入支行名" id="cardbank" name="cardbank"/></li>
		    	 <li><p>提现金额：</p><input type="text" value="${user.balance}" placeholder="请输入提现金额" id="amount" name="amount"/></li>
		    	 
		    	</ul>
		    	<p class="warning">请保证您输入的信息正确！</p>
	    	</form>
	    	</div>
	    	<a href="javascript:;" class="ljzf" onclick="gotoback();">提交</a>
	    </div>

    </div>
    
</body>
<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
 	
 <script type="text/javascript">
	
	var waitdialog=null;
	
	function gotoback(){
		waitdialog = new TipBox({type:'load',str:'正在提交',hasBtn:false});
		$.ajax({
			async:true,
			type:'post',
			url:'withdraw.do',
			data:{amount:""+$("#amount").val(),name:""+$("#name").val(),phone:""+$("#phone").val(),cardno:""+$("#cardno").val(),cardbank:""+$("#cardbank").val()},
			dataType:'json',
			success:function(result,textStatus){
				
				waitdialog.destroy();
				
				if(result.result=="SUCCESS"){
					new TipBox({type:'success',str:'提现成功，半小时不到账请联系平台客服！',hasBtn:true});
					
				}else{
					new TipBox({type:'error',str:''+result.desc,hasBtn:true});
					
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