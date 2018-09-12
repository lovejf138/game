<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">消息列表</title>
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
				<div class="am-cf _div_tilte">消息列表</div>
			</div>		
		   		
			<div class="am-panel-hd " style="background-color: #fff;">	
				 <form class="am-form am-form-inline am-cf"  action="<%=request.getContextPath()%>/messages.do" method="post" id="ListForm">	
				  <input type="hidden" id="currentPage" name="currentPage" >
				 
				  <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">地址：</label>:
		 		  </div>
				  <div class="am-form-group">			
				      <input type="text" value="${jc }" name="jc" id="jc" class="am-input-sm" style="width:180px ;height: 30px"/> 
				  </div>	 
				  
				   <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">房间号：</label>:
		 		  </div>
				  <div class="am-form-group">			
				      <input type="text" value="${roomid }" name="roomid" id="roomid" class="am-input-sm" style="width:80px ;height: 30px"/> 
				  </div>	 
				  	
				  	
				 
				  <!--  <a class="am-btn  am-btn-sm am-btn-primary am-fr" onclick="sys()">系统设置</a>	-->
				  <a class="am-btn  am-btn-sm am-btn-success am-fr" href="<%=request.getContextPath()%>/users.do">用户列表</a>	
				 <button class="am-btn  am-btn-sm am-btn-primary am-fr _query">查询</button>
				 </form>		
			</div>
			
		   <div class="am-panel-bd">	
		        <p style="color:red" id="p_tongji"></p>		
				<table  class="am-table am-table-bordered am-table-centered am-text-sm">
					<thead style="background-color: #f5f5f5;">
						<tr >
							<th class="_merchant_short" >ID</th>
							
							<th class="_contact" >房间</th>
							<th class="_contact" >用户</th>
							<th class="_contact" >消息</th>
							
							<th class="_endtime" >创建时间</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objs }" var="user">
							<tr >
								<td>${user.id}</td>
								
								<td>${user.roomid }</td>
								
								<td>${user.userid }</td>
								<td>${user.message }</td>
								
								<td><fmt:formatDate value="${user.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								
															
							</tr>
						</c:forEach>
					</tbody>
				</table>			
								
				<div class="fenye">${gotoPageFormHTML1}</div>						
				 
			</div>
		</div>		    					
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

</script>
</html>
 