<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>推荐用户列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/template.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/plugin/amaze-ui/css/amazeui.min.css" />
	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">


	
</head>
<body>
<div class="nav">
	<a class="weizhi" href="gerenzhongxin.do"> <img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/zuo.png" alt=""></a>
	<div class="zhongzi">
		推荐用户列表
	</div>
   <!--   <a class="shoucang" href="tixian.html">我要提现</a>-->
</div>

<div style="margin-top: 1rem;float:right;padding-right:5px">
	<!--带更多链接-->
	<p style="color:red;">合计推广数量:${login_user.child_sum}</p>
</div>
							
<div class="tx-jl">
	<ul>
					<form class="am-form am-form-inline am-cf"
							action="<%=request.getContextPath()%>/childs.do"
							method="post" id="ListForm">
							<input style="display: none" id="currentPage" name="currentPage" />
				  </form>
		<c:forEach items="${objs }" var="user">
		<li style="height: 50px;line-height: 50px;">
			<p style="height: 50px;line-height: 50px;">${user.id_short}<span style="font-size: 12px; color: #c7b9ba;float: right;"><fmt:formatDate value="${user.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
		</li>
		</c:forEach>
		
	</ul>
	
</div>

<div style="padding-top: 20px;position: absolute;right: 20px;color: #4476a7;text-align: right;float: bottom;height: 20px;min-width: 600px;bottom:-10px" class="fenye">${gotoPageFormHTML1}</div>

</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/webpos/pos/front/js/index_fontsize.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.js"></script>
<script type="text/javascript">
	function gotoPage(n) {
		//alert(n);
		$("#currentPage").val("" + n);
		$("#ListForm").submit();
	}
</script>
</html>
