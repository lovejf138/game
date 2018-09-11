<%@page import="com.webpos.common.GlobalConstant"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/public.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/font/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/css/main.css?v=1" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>

</head>
<body>
	
	<div id="left-side">
		<dl id="leftMenu">
			
				<dt>
					<a class="am-btn  am-btn-sm am-btn-primary am-fr" href="<%=request.getContextPath()%>/users.do">用户列表</a>	
				</dt>
				
				<dt>
					<a class="am-btn  am-btn-sm am-btn-primary am-fr" href="<%=request.getContextPath()%>/users.do">用户列表</a>	
				</dt>
				
				<dt>
					<a class="am-btn  am-btn-sm am-btn-primary am-fr" href="<%=request.getContextPath()%>/users.do">用户列表</a>	
				</dt>
				
			
		</dl>
	</div>
</body>
</html>

