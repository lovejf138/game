<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">资金出入表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/public.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">

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
				<div class="am-cf _div_tilte">资金出入表</div>
			</div>		
		   		
			<div class="am-panel-hd " style="background-color: #fff;">	
				 <form class="am-form am-form-inline am-cf"  action="<%=request.getContextPath()%>/withdraw_list.do" method="post" id="ListForm">	
				  <input type="hidden" id="currentPage" name="currentPage" >
				 
				  <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">地址</label>:
		 		  </div>
		 		   <div class="am-form-group">			
				      <input type="text" value="${jc }" name="jc"id="jc" class="am-input-sm" style="width:180px ;height: 30px"/> 
				  </div>	
				  
				   <div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">类型</label>:
					   </div>				
					   <div class="am-form-group">
							 <select name="type"  class="pay-m" style="width: 100px;">
								
								<option value="in" <c:if test="${type eq 'in'}">selected</c:if>>充值</option>	
								<option  value="withdraw" <c:if test="${type eq 'withdraw'}">selected</c:if>>提现</option>	
								
							</select>
					 </div>	
					  <input id="div_type" style="display:none" value="${type}"/>	
					   <input id="div_status" style="display:none" value="${status}"/>	
		 		   <div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">状态</label>:
					   </div>				
					   <div class="am-form-group">
							 <select name="status"  class="pay-m" style="width: 100px;">
								
								<option value="request" <c:if test="${status eq 'request'}">selected</c:if>>待处理</option>	
								<option  value="success" <c:if test="${status eq 'success'}">selected</c:if>>完成</option>	
								
							</select>
					 </div>	
					 
					  <div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">排序</label>:
					   </div>				
					   <div class="am-form-group">
							 <select name="order"  class="pay-m" style="width: 150px;">
								
								<option value="amount_asc" <c:if test="${order eq 'amount_asc'}">selected</c:if>>金额升序</option>	
								<option  value="amount_desc" <c:if test="${order eq 'amount_desc'}">selected</c:if>>金额降序</option>	
								<option  value="imtoken_asc" <c:if test="${order eq 'imtoken_asc'}">selected</c:if>>imtoken升序</option>	
								<option  value="imtoken_desc" <c:if test="${order eq 'imtoken_desc'}">selected</c:if>>imtoken降序</option>	
								<option  value="ctime_asc" <c:if test="${order eq 'ctime_asc'}">selected</c:if>>创建时间升序</option>	
								<option  value="ctime_desc" <c:if test="${order eq 'ctime_desc'}">selected</c:if>>创建时间降序</option>	
								
							</select>
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
				<table  class="am-table am-table-bordered am-table-centered am-text-sm">
					<thead style="background-color: #f5f5f5;">
						<tr >
							<th class="_merchant_short">地址</th>
							
							<th class="_contact">金额</th>
							
							<th class="_contact" >imtoken余额</th>
							
							<th class="_contact" >系统余额</th>
							<th class="_contact" >推广人数</th>
							<th class="_contact" >充值总数</th>
							<th class="_contact" >提现总数</th>
							<th class="_contact" >下注总数</th>
							
							<th class="_endtime" >创建时间</th>
							<th class="_operation" >操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objs }" var="user">
							<tr style="<c:if test='${user.is_machine==1}' >background-color: #a9a9a9;</c:if>
							  <c:if test='${user.is_machine==0}' >background-color: #fff;</c:if>">
								<td>${user.user_id }</td>
								<td>${user.amount }</td>
								
								<td>${user.all_eth }</td>
								
								<td>${user.balance }</td>
								<td>${user.child_sum }</td>
								<td>${user.recharge_sum }</td>
								<td>${user.withdraw_sum }</td>
								<td>${user.play_sum }</td>
								
								<td><fmt:formatDate value="${user.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								
								<td class="merchant-m">
									
									<c:if test="${status eq 'request'}">
									<a class="operate _detail" id="_in" onclick="deal('${user.id}','${user.fianl_amount}','${user.user_id}')">处理</a>	|					
									</c:if>
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
			type : $("#div_type").val(),
			startDate : $("#time1").val(),
			endDate : $("#time2").val(),
		    jc : $("#jc").val(),
		    status:$("#div_status").val()
	};
	
	$.ajax({
		url:'<%=request.getContextPath()%>/summary_withdraw.do',
        data:params,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data.result == "SUCCESS"){
        		$("#p_tongji").html("用户总数:"+data.user_sum+", 余额总数:"+data.amount_sum);
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
 