<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">发货列表</title>
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
				<div class="am-cf _div_tilte">发货列表</div>
			</div>		
		   		
			<div class="am-panel-hd " style="background-color: #fff;">	
				 <form class="am-form am-form-inline am-cf"  action="<%=request.getContextPath()%>/sendgoods_list.do" method="post" id="ListForm">	
				  <input type="hidden" id="currentPage" name="currentPage" >
				 
				  <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">用户</label>:
		 		  </div>
		 		   <div class="am-form-group">			
				      <input type="text" value="${jc }" name="jc" id="jc" class="am-input-sm" style="width:180px ;height: 30px"/> 
				  </div>	
				  
				  <div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">状态</label>:
					   </div>	
					   <input id="div_type" style="display:none" value="${status}"/>			
					   <div class="am-form-group">
							 <select name="status"  class="pay-m" style="width: 50px;">
								
								<option value="request" <c:if test="${status eq 'request'}">selected</c:if>>待处理</option>	
								<option  value="finish" <c:if test="${status eq 'finish'}">selected</c:if>>已完成</option>		
								<option  value="send" <c:if test="${status eq 'send'}">selected</c:if>>已发货</option>		
								
							</select>
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
							<th class="_contact"  width="20px">用户</th>
							<th class="_contact" width="20px">商品</th>
							<th class="_merchant_short" width="130px">详情</th>
							<th class="_endtime" width="120px">创建时间</th>
							<th class="_operation" width="20px">状态</th>
							<th class="_operation" width="20px">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objs }" var="user">
							<tr>
								<td>${user.userid }</td>
								<td>
								${user.goodsname }
								</td>
								<td>
									姓名：${user.name }</br>
									手机：${user.phone }</br>
									地址：${user.address }
								</td>
								
								<td><fmt:formatDate value="${user.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								
								<td>
									${user.status}
								</td>	
								<td>
									<c:if test="${user.status == 'request'}">
									<a class="operate _interface"  onclick="deal('${user.id}')">标记已发货</a>
									<a class="operate _interface"  onclick="deal2('${user.id}')">标记已完成</a>
									</c:if>
									<c:if test="${user.status == 'send'}">
									<a class="operate _interface"  onclick="deal2('${user.id}')">标记已完成</a>
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
    		<tr height="30%" id="edit_tip">
    		</tr>
    		
    		<input id="edit_type" style="display:none"/>
    		<input id="edit_id" style="display:none"/>
    		
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
		    jc : $("#jc").val(),
		    roomid:$("#roomid").val(),
		    qiname:$("#qiname").val()
	};
	
	$.ajax({
		url:'<%=request.getContextPath()%>/summary_play.do',
        data:params,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data.result == "SUCCESS"){
        		$("#p_tongji").html("下注总数:"+data.amount_sum+",奖金总数："+data.award_sum);
        	}
        }
    }); 
	
}

function deal(id){

   $("#edit_tip").html("是否标记为已发货？");
	$("#edit_id").attr("value",id);
	$("#edit_type").attr("value","1");
	
	layer.open({
		  title : "发货处理",
		  type: 1, //page层
		  area: ['400px', '400px'],
		  shade: 0.6, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: $('#qrCode-edit')
	});  
}

function deal2(id){

	 $("#edit_tip").html("是否标记为已完成？");
	 $("#edit_id").attr("value",id);
	 $("#edit_type").attr("value","2");
	layer.open({
		  title : "发货处理",
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
		url:'goods_deal.do',
        data:"id="+$("#edit_id").val()+"&type="+$("#edit_type").val(),
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	 if(data=="success"){
        		alert('成功，请刷新页面!');
        	}
        	else {
        		alert('失败 !'+data);
        	}
        }
    }); 
}
	




</script>
</html>
 