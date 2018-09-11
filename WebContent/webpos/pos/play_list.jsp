<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">转盘列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/public.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/region_select.js"></script>
<!-- fenye  css 、 js-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/template.css">	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugin/amaze-ui/css/amazeui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugin/amaze-ui/css/admin.css" />
	<script type="text/javascript">
	
	//表单方式分页
	function gotoPage(n){
		jQuery("#currentPage").val(n);
		jQuery("#ListForm").submit();
	}
	</script>
</head>

<body class="user-account">
<div class="am-cf admin-main">
	<div class="admin-content" style="margin-top: 5px">
	    
		<div class="am-panel am-panel-default">
			<div class="am-panel-hd">
				<div class="am-cf _div_tilte">转盘列表</div>
			</div>		
		   		
			<div class="am-panel-hd " style="background-color: #fff;">	
				 <form class="am-form am-form-inline am-cf"  action="<%=request.getContextPath()%>/play_list.do" method="post" id="ListForm">	
				  <input type="hidden" id="currentPage" name="currentPage" >
				 
				  <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">地址</label>:
		 		  </div>
		 		   <div class="am-form-group">			
				      <input type="text" value="${jc }" name="jc"id="jc" class="am-input-sm" style="width:180px ;height: 30px"/> 
				  </div>	
				
				 <div class="am-form-group">
						    	&nbsp;&nbsp;<label class="_Trading_time" style="margin-bottom: 0px;font-size: 1.5rem;">时间</label>:
					</div>
					<div class="am-form-group">
								<input type="text" id="time1" name="startDate" style="height: 30px;text-align: center;width: 174px;" <c:if test="${startDate ne null}">value="${startDate}"</c:if> ><i class="iconfont heng" >&#xe601;</i>
								<input type="text" id="time2" name="endDate" style="height: 30px;text-align: center;width: 174px;" <c:if test="${endDate ne null}">value="${endDate}"</c:if> >&nbsp;&nbsp;
				 </div>
				   <a class="am-btn  am-btn-sm am-btn-danger am-fr" href="<%=request.getContextPath()%>/users.do">用户列表</a>	
				  <button class="am-btn  am-btn-sm am-btn-primary am-fr _query">查询</button>	
				  
				 			 
				 </form>		
			</div>
			
		   <div class="am-panel-bd">	
		   <p style="color:red" id="p_tongji"></p>		
		   <p style="color:red" id="p_tongji2"></p>				
				<table  class="am-table am-table-bordered am-table-centered am-text-sm">
					<thead style="background-color: #f5f5f5;">
						<tr >
							<th class="_merchant_short">地址</th>
							
							<th class="_contact" width="130px">类型</th>
							<th class="_endtime" width="120px">金额</th>
							<th class="_endtime" width="120px">备注</th>
							
							<th class="_endtime" width="120px">创建时间</th>
							<th class="_operation" width="210px">余额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objs }" var="user">
							<tr <c:if test="${user.remark >1.0}">style="background-color: #ffe7e7;" </c:if>>
								<td>${user.user_id }</td>
								<td>
								<c:if test="${user.type eq 'join'}">
								下注
								</c:if>
								<c:if test="${user.type eq 'win'}">
								下注赢钱
								</c:if>
								
								</td>
								<td>${user.result }</td>
								<td>${user.remark }</td>
								<td><fmt:formatDate value="${user.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								
								<td>
									${user.balance}
								</td>									
							</tr>
						</c:forEach>
					</tbody>
				</table>			
								
				<div class="fenye">${gotoPageFormHTML1}</div>						
				 
			</div>
		</div>		    					
	</div>
	
	
	
    <!-- 修改用户 -->
    <div id="qrCode-edit"  class="hide" style="height: 100%;padding: 15px;" >
    	<table style="text-align: center;width: 100%;height: 100%">
    		<tr height="30%">
    			<td colspan="4">
    			<input id="et_input_id" style="width:350px;display:none"/>
    				提现地址:<a id="et_input_address" style="width:350px"></a>
    			</td>
    		</tr>
    		
    		
    		<tr >
    			<td colspan="4">
    				数量:<a id="et_input_number" style="width:350px"></a>个eth
    				
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4">
    				 <div id="qrcode-ti" style="text-align: center;margin: auto;">
    				
    			</td>
    		</tr>
    		
    	
    		
    		<tr >
    			<td colspan="4" >
    				<a id="button-query" style="font-size: 1rem" 
    				class="button-a button-a-primary" onclick="et_btn()" href="javascript:void(0);">确定</a>
    			</td>
    		</tr>
    	</table>
    </div>
    
	
	
</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/date/laydate.dev.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webpos/pos/js/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/qrcode.min.js"></script>
<script type="text/javascript">

$(function () {
	
	laydate({
        elem: '#time1',
        max: laydate.now(0), //最大值是今天
        istime: true,
        format: 'YYYY-MM-DD hh:mm:ss' //日期格式
	});
    laydate({
        elem: '#time2',
        max: laydate.now(0), //最大值是今天
        istime: true,
        format: 'YYYY-MM-DD hh:mm:ss', //日期格式
	});
    
    summaryDetails();
});


//交易详情统计
function summaryDetails(){
	 var params = {
			type : "join",
			startDate : $("#time1").val(),
			endDate : $("#time2").val(),
		    jc : $("#jc").val()
	};
	
	$.ajax({
		url:'<%=request.getContextPath()%>/summary_play.do',
        data:params,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data.result == "SUCCESS"){
        		$("#p_tongji").html("下注总数:"+data.amount_sum);
        	}
        }
    }); 
	
	
	var params2 = {
			type : "win",
			startDate : $("#time1").val(),
			endDate : $("#time2").val(),
		    jc : $("#jc").val()
	};
	
	$.ajax({
		url:'<%=request.getContextPath()%>/summary_play.do',
        data:params2,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data.result == "SUCCESS"){
        		$("#p_tongji2").html("下注赢钱总数:"+data.amount_sum);
        	}
        }
    }); 
}

function deal(id,amount,user_id){
	
	$('#qrcode-ti').html("");
	//var qrcode = new QRCode("qrcode-ti");
	
	var qrcode = new QRCode('qrcode-ti', {
		  text: user_id,
		  width: 100,
		  height: 100,
		  colorDark : '#000000',
		  colorLight : '#ffffff',
		  correctLevel : QRCode.CorrectLevel.H
		});
	
	//qrcode.makeCode(user_id);
	
	$("#et_input_address").html(user_id);
	$("#et_input_id").attr("value",id);
	$("#et_input_number").html(amount);
	layer.open({
		  title : "提现处理",
		  type: 1, //page层
		  area: ['400px', '400px'],
		  shade: 0.6, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: $('#qrCode-edit')
	});  
}



function et_btn(){
	$.ajax({
		url:'draw_deal.do',
        data:"id="+$("#et_input_id").val(),
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data=="fail"){
        		alert('网络连接失败，请稍后再试!');
        	}else if(data=="SUCCESS"){
        		alert('成功，请刷新页面!');
        	}else if(data=="user_error"){
        		alert('地址有误!');
        	}
        	else if(data=="amount_error"){
        		alert('数量有误!');
        	}
        	else if(data=="login_error"){
        		alert('请先登陆!');
        	}
        }
    });
}
	
function in_btn(){
	$.ajax({
		url:'in.do',
        data:"user_id="+$("#cz_input_address").val()+"&amount="+$("#cz_input_number").val(),
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data=="fail"){
        		alert('网络连接失败，请稍后再试!');
        	}else if(data=="SUCCESS"){
        		alert('充值成功，请刷新页面!');
        	}else if(data=="user_error"){
        		alert('地址有误!');
        	}
        	else if(data=="amount_error"){
        		alert('数量有误!');
        	}
        	else if(data=="login_error"){
        		alert('请先登陆!');
        	}
        }
    });
}


</script>
</html>
 