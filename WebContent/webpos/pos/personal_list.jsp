<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">个人中心</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/public.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/region_select.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/date/laydate.dev.js"></script>
<!-- fenye  css 、 js-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/template.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/plugin/amaze-ui/css/amazeui.min.css" />


</head>

<body >
	<div class="am-cf admin-main">
		<div class="admin-content" style="margin-top: 5px">

			<div >
				
				<div class="am-panel-hd " style="background-color: #fff;">
					<input id="div_type" style="display: none" value="${type}" /> 
					<a
						class="am-btn  am-btn-sm am-btn-warning am-fr" style="font-size:25px"
						href="<%=request.getContextPath()%>/self_play_list.do">转盘参与列表</a>
					<a class="am-btn  am-btn-sm am-btn-danger am-fr" style="font-size:25px"
						href="<%=request.getContextPath()%>/share_play_list.do">推广收益列表</a>
					<a class="am-btn  am-btn-sm am-btn-secondary am-fr" style="font-size:25px"
						href="<%=request.getContextPath()%>/share_list.do">推广用户列表</a> 
						<a
						class="am-btn  am-btn-sm am-btn-success am-fr" style="font-size:25px"
						href="<%=request.getContextPath()%>/activity.do">回到首页</a> 
					<a
						class="am-btn  am-btn-sm am-btn-danger am-fr" style="font-size:25px"
						href="<%=request.getContextPath()%>/recharge_page.do">充值</a>

				</div>
				

				<div style="font-size: 30px;margin-top: 100px;">
					<p style="color: red" id="p_tongji"></p>


					<c:if test="${type == 'recharge'}">

						<div class="am-modal-bd" style="line-height: 100px;">

							<p style="margin-left: 70px;">
								<spring:message code='in_address_success' />
								</p>
							

							<a
								style="user-select: auto; -webkit-user-select: auto; -moz-user-select: auto; -ms-user-select: auto; user-select: auto;">0x0F23430D929F24b86CB3Bf375B94d96361d0420a</a>


							<p>
								<img style="width: 300px"
									src="<%=request.getContextPath()%>/webpos/pos/img/imtoken.png"></img>
							</p>
							<blockquote style="background-color: #f3f3f3; font-size: 25px;">
								<p>
									<spring:message code='in_question_contact' />

									QQ:1371247467

								</p>
							</blockquote>
						</div>
					</c:if>
					<c:if test="${type == 'self_play'}">
						<form class="am-form am-form-inline am-cf"
							action="<%=request.getContextPath()%>/self_play_list.do"
							method="post" id="ListForm">
							<input style="display: none" id="currentPage" name="currentPage" />
						</form>
						<table style="font-size: 20px;"
							class="am-table am-table-bordered am-table-centered am-text-sm">
							<thead style="background-color: #f5f5f5;">
								<tr>
									<th class="_merchant_short">类型</th>
									<th class="_merchant_short">金额</th>
									<th class="_endtime">创建时间</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${objs }" var="user">
									<tr>
										<td><c:if test="${user.type eq 'join'}">
								下注
								</c:if> <c:if test="${user.type eq 'win'}">
								下注赢钱
								</c:if></td>
										<td>${user.result }</td>

										<td><fmt:formatDate value="${user.ctime }"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>

					<c:if test="${type == 'share_play'}">
						<form class="am-form am-form-inline am-cf"
							action="<%=request.getContextPath()%>/share_play_list.do"
							method="post" id="ListForm">
							<input style="display: none" id="currentPage" name="currentPage" />
						</form>
						<table
						   style="font-size: 20px;"
							class="am-table am-table-bordered am-table-centered am-text-sm">
							<thead style="background-color: #f5f5f5;">
								<tr>
									<th class="_merchant_short">用户</th>
									<th class="_merchant_short">类型</th>
									<th class="_merchant_short">金额</th>
									<th class="_merchant_short">收益</th>
									<th class="_endtime">创建时间</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${objs }" var="user">
									<tr>
										<td>${user.short_id }</td>
										<td>下注</td>
										<td>${user.temp_amount }</td>
										<td>${user.temp_amount_100 }</td>
										<td><fmt:formatDate value="${user.ctime }"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>

									</tr>
								</c:forEach>
							</tbody>

						</table>
					</c:if>

					<c:if test="${type == 'share'}">




						<form class="am-form am-form-inline am-cf"
							action="<%=request.getContextPath()%>/share_list.do"
							method="post" id="ListForm">
							<input style="display: none" id="currentPage" name="currentPage" />
						</form>

						<div data-am-widget="list_news"
							class="am-list-news am-list-news-default">
							<!--列表标题-->
							<div class="am-list-news-hd am-cf">
								<!--带更多链接-->
								<p>合计推广数量:${login_user.child_sum}</p>
							</div>

							<div class="am-list-news-bd">
								<ul class="am-list">
									<c:forEach items="${objs }" var="user">
									<li class="am-g">
									  ${user.id_short }
									  
									 <p style="font-size:25px"> <fmt:formatDate value="${user.ctime }"
												pattern="yyyy-MM-dd HH:mm:ss" /></p>
									 </li>
									</c:forEach>
									
								</ul>
							</div>
							
					</c:if>

					<div style="padding-top: 20px;" class="fenye">${gotoPageFormHTML1}</div>

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
	src="<%=request.getContextPath()%>/webpos/pos/js/layer/layer.js"></script>
<script type="text/javascript">
	function gotoPage(n) {
		//alert(n);
		$("#currentPage").val("" + n);
		$("#ListForm").submit();
	}
	
	summaryDetails();
	//交易详情统计
	function summaryDetails(){
		
		var type=$("#div_type").val();
		if(type=="share_play"){
			$.ajax({
				url:'<%=request.getContextPath()%>/summary_share_play.do',
		        data:"",
		        type:'post',
		        dataType:'json',
		        traditional:true,
		        success:function(data){
		        	if(data.result == "SUCCESS"){
		        		$("#p_tongji").html("累计收益:"+data.sum_amount);
		        	}
		        }
		    });
		}
		
		if(type=="self_play"){
			$.ajax({
				url:'<%=request.getContextPath()%>/summary_self_play.do',
		        data:"",
		        type:'post',
		        dataType:'json',
		        traditional:true,
		        success:function(data){
		        	if(data.result == "SUCCESS"){
		        		$("#p_tongji").html("下注金额累计:"+data.amount+",赢钱金额累计："+data.win_amount+",胜率:"+data.win_rate);
		        	}
		        }
		    });
		}
		
		
		
		
	}
</script>
</html>
