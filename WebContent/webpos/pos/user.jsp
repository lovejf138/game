<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">用户管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/public.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/js/region_select.js"></script>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/js/date/laydate.dev.js"></script>
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
				<div class="am-cf _div_tilte">用户列表</div>
			</div>		
		   		
			<div class="am-panel-hd " style="background-color: #fff;">	
				 <form class="am-form am-form-inline am-cf"  action="<%=request.getContextPath()%>/users.do" method="post" id="ListForm">	
				  <input type="hidden" id="currentPage" name="currentPage" >
				 
				  <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">地址</label>:
		 		  </div>
				  <div class="am-form-group">			
				      <input type="text" value="${jc }" name="jc" id="jc" class="am-input-sm" style="width:180px ;height: 30px"/> 
				  </div>	 
				  	
				  	<div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">类型</label>:
					   </div>	
					   <input id="div_type" style="display:none" value="${type}"/>			
					   <div class="am-form-group">
							 <select name="type"  class="pay-m" style="width: 50px;">
								
								<option value="child" <c:if test="${type eq 'child'}">selected</c:if>>子</option>	
								<option  value="parent" <c:if test="${type eq 'parent'}">selected</c:if>>父</option>		
								
							</select>
			         </div>	
			         
				      <div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">排序</label>:
					   </div>				
					   <div class="am-form-group">
							 <select name="order"  class="pay-m" style="width: 150px;">
								
								<option value="amount_asc" <c:if test="${order eq 'amount_asc'}">selected</c:if>>余额升序</option>	
								<option  value="amount_desc" <c:if test="${order eq 'amount_desc'}">selected</c:if>>余额降序</option>	
								<option  value="imtoken_asc" <c:if test="${order eq 'imtoken_asc'}">selected</c:if>>imtoken升序</option>	
								<option  value="imtoken_desc" <c:if test="${order eq 'imtoken_desc'}">selected</c:if>>imtoken降序</option>	
								<option  value="ctime_asc" <c:if test="${order eq 'ctime_asc'}">selected</c:if>>创建时间升序</option>	
								<option  value="ctime_desc" <c:if test="${order eq 'ctime_desc'}">selected</c:if>>创建时间降序</option>
								
								<option  value="recharge_asc" <c:if test="${order eq 'recharge_asc'}">selected</c:if>>充值升序</option>
								<option  value="recharge_desc" <c:if test="${order eq 'recharge_desc'}">selected</c:if>>充值降序</option>
								
								<option  value="withdraw_asc" <c:if test="${order eq 'withdraw_asc'}">selected</c:if>>提现升序</option>
								<option  value="withdraw_desc" <c:if test="${order eq 'withdraw_desc'}">selected</c:if>>提现降序</option>
								
								<option  value="share_asc" <c:if test="${order eq 'share_asc'}">selected</c:if>>推广升序</option>	
								<option  value="share_desc" <c:if test="${order eq 'share_desc'}">selected</c:if>>推广降序</option>
								
								<option  value="play_asc" <c:if test="${order eq 'play_asc'}">selected</c:if>>下注升序</option>
								<option  value="play_desc" <c:if test="${order eq 'play_desc'}">selected</c:if>>下注降序</option>
									
								
							</select>
			         </div>	
				 
				   <div class="am-form-group">
						    	&nbsp;&nbsp;<label class="_Trading_time" style="margin-bottom: 0px;font-size: 1.5rem;">时间</label>:
					</div>
					<div class="am-form-group">
								<input type="text" id="time1" name="startDate" style="height: 30px;text-align: center;width: 174px;" <c:if test="${startDate ne null}">value="${startDate}"</c:if> ><i class="iconfont heng" >&#xe601;</i>
								<input type="text" id="time2" name="endDate" style="height: 30px;text-align: center;width: 174px;" <c:if test="${endDate ne null}">value="${endDate}"</c:if> >&nbsp;&nbsp;
				    </div>
							
				  <!--  <a class="am-btn  am-btn-sm am-btn-primary am-fr" onclick="sys()">系统设置</a>	-->
				  <a class="am-btn  am-btn-sm am-btn-success am-fr" href="<%=request.getContextPath()%>/play_list.do">转盘列表</a>	
				  <a class="am-btn  am-btn-sm am-btn-danger am-fr" href="<%=request.getContextPath()%>/withdraw_list.do?status=request">资金出入列表</a>				 
					<button class="am-btn  am-btn-sm am-btn-primary am-fr _query">查询</button>
				 </form>		
			</div>
			
		   <div class="am-panel-bd">	
		        <p style="color:red" id="p_tongji"></p>		
				<table  class="am-table am-table-bordered am-table-centered am-text-sm">
					<thead style="background-color: #f5f5f5;">
						<tr >
							<th class="_merchant_short" >地址</th>
							
							<th class="_contact" >余额</th>
							<th class="_contact" >充值总额</th>
							<th class="_contact" >提现总额</th>
							<th class="_contact" >推广个数</th>
							<th class="_contact" >轮盘总额</th>
							
							<th class="_contact" >imtoken</th>
							<th class="_status">胜率</th>
							<th class="_endtime" >创建时间</th>
							<th class="_operation" >操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objs }" var="user">
							<tr style="<c:if test='${user.is_machine==1}' >background-color: #a9a9a9;</c:if>
							  <c:if test='${user.is_machine==0}' >background-color: #fff;</c:if>">
								<td>${user.user_id }<br><font style="color:#e2aaaa;size:10px">${user.parent }</font></td>
								
								<td>${user.balance }</td>
								
								<td>${user.recharge_sum }</td>
								<td>${user.withdraw_sum }</td>
								<td>${user.child_sum }</td>
								<td>${user.play_sum }</td>
								
								<td>${user.all_eth }</td>
								<td>${user.win_rate}%</td>
								<td><fmt:formatDate value="${user.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								
								<td class="merchant-m">
									
									<a class="operate _detail" id="_in" onclick="cz('${user.user_id}')">充值</a>	|					
									<a class="operate _interface" id="_winrate" onclick="et('${user.user_id}','${user.win_rate}','${user.all_eth}')">胜率修改</a>
									<c:if test="${user.is_machine==0}" >
									|					
									<a class="operate _interface" id="_winrate" onclick="bj('${user.user_id}',1)">标记机器人</a>
									</c:if>
									<c:if test="${user.is_machine==1}" >
									|					
									<a class="operate _interface" id="_winrate" onclick="bj('${user.user_id}',0)">取消机器人</a>
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
	
	<!-- 二维码弹窗 -->
    <div id="qrCode-sys"  class="hide" style="height: 100%;padding: 15px;" >
    	<table style="text-align: center;width: 100%;height: 100%">
    		<tr height="30%">
    			<td colspan="4">
    				消息中心:<input id="sys_input_content" style="width:350px" style="text" value="${dsystem.content}"/>
    			</td>
    		</tr>
    		<tr >
    			<td colspan="4">
    				币池:<input id="sys_input_number" style="width:200px" value="${dsystem.account}"/>个ETH
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4">
    				打流水倍数:<input id="sys_withdraw_number" style="width:200px" value="${dsystem.withdraw_number}"/>倍可提现
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4" >
    				<a id="button-query2" style="font-size: 1rem" 
    				class="button-a button-a-primary" onclick="sys_btn()" href="javascript:void(0);">确定</a>
    			</td>
    		</tr>
    	</table>
    </div>
    
	<!-- 二维码弹窗 -->
    <div id="qrCode-in"  class="hide" style="height: 100%;padding: 15px;" >
    	<table style="text-align: center;width: 100%;height: 100%">
    		<tr height="30%">
    			<td colspan="4">
    				地址:<input id="cz_input_address" style="width:350px" style="text"/>
    			</td>
    		</tr>
    		<tr >
    			<td colspan="4">
    				数量:<input id="cz_input_number" style="width:100px"/>个ETH
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4" >
    				<a id="button-query1" style="font-size: 1rem" 
    				class="button-a button-a-primary" onclick="in_btn()" href="javascript:void(0);">确定</a>
    			</td>
    		</tr>
    	</table>
    </div>
    
    <!-- 修改用户 -->
    <div id="qrCode-edit"  class="hide" style="height: 100%;padding: 15px;" >
    	<table style="text-align: center;width: 100%;height: 100%">
    		<tr height="30%">
    			<td colspan="4">
    				地址:<input id="et_input_address" style="width:350px" style="text"/>
    			</td>
    		</tr>
    		<tr >
    			<td colspan="4">
    				胜率:<input id="et_input_rate" style="width:100px"/>%
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4">
    				imtoken余额:<input id="et_input_number" style="width:100px"/>个eth
    				
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4">
    				手动查imtoken余额地址：
    				<a>https://www.etherchain.org/account/1</a>
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
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/webpos/pos/js/layer/layer.js"></script>
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

function sys(){
	
	layer.open({
		  title : "系统设置",
		  type: 1, //page层
		  area: ['400px', '400px'],
		  shade: 0.6, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: $('#qrCode-sys')
	});  
}

function cz(user_id){
	
	$("#cz_input_address").attr("value",user_id);
	layer.open({
		  title : "充值",
		  type: 1, //page层
		  area: ['400px', '400px'],
		  shade: 0.6, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: $('#qrCode-in')
	});  
}


function bj(_user_id,_is_machine){
	
	var params = {
			user_id : _user_id,
			is_machine : _is_machine
	};
	
	$.ajax({
		url:'<%=request.getContextPath()%>/bj_machine.do',
        data:params,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(result){
        	if(result == "SUCCESS"){
        		window.location.reload();
        	}else{
        		alert(result);
        	}
        }
    });
}

function et(user_id,win_rate,all_eth){
	
	$("#et_input_address").attr("value",user_id);
	$("#et_input_rate").attr("value",win_rate);
	$("#et_input_number").attr("value",all_eth);
	layer.open({
		  title : "编辑",
		  type: 1, //page层
		  area: ['400px', '400px'],
		  shade: 0.6, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: $('#qrCode-edit')
	});  
}

//交易详情统计
function summaryDetails(){
	var params = {
			type : $("#div_type").val(),
			startDate : $("#time1").val(),
			endDate : $("#time2").val(),
		    jc : $("#jc").val()
	};
	
	$.ajax({
		url:'<%=request.getContextPath()%>/summary_users.do',
        data:params,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data.result == "SUCCESS"){
        		$("#p_tongji").html("用户总数:"+data.user_sum+", 余额总数:"+data.balance_sum+", 提现总数:"+data.withdraw_sum+
        				", 充值总数:"+data.recharge_sum+", 推广总数:"+data.share_sum+"，下注总额:"+data.play_sum);
        	}
        }
    });
}

function sys_btn(){
	$.ajax({
		url:'system_deal.do',
        data:"content="+$("#sys_input_content").val()+"&amount="+$("#sys_input_number").val()+"&number="+$("#sys_withdraw_number").val(),
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data=="fail"){
        		alert('网络连接失败，请稍后再试!');
        	}else if(data=="SUCCESS"){
        		alert('修改成功，请刷新页面!');
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

function et_btn(){
	$.ajax({
		url:'edit_user.do',
        data:"user_id="+$("#et_input_address").val()+"&amount="+$("#et_input_number").val()+"&rate="+$("#et_input_rate").val(),
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data=="fail"){
        		alert('网络连接失败，请稍后再试!');
        	}else if(data=="SUCCESS"){
        		alert('修改成功，请刷新页面!');
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
 