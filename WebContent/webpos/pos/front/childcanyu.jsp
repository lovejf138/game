<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, minimum-scale= 1.0, initial-scale= 1.0">
<title>推广用户参与列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/canyu.css">
<style>
.fenye a {
    color: #fff;
    margin-left: 5px;
    margin-right: 5px;
    padding-top: 2px;
    padding-bottom: 2px;
    padding-left: 5px;
    padding-right: 7px;
    border: 1px solid #fff;
    font-weight:600
}
</style>
</head>
<body style="background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);">

<div class="" style="width: 100%;overflow: hidden;position: fixed;top: 0;height: 40px;line-height: 40px;">
	<a class="weizhi" href="gerenzhongxin.do" style="height: 40px;line-height: 40px;"> <img style="width: 30px;" src="<%=request.getContextPath()%>/webpos/pos/front/img/room/return.png" alt=""></a>
	<div class="zhongzi" style="height: 40px;line-height: 40px;font-size: 20px;color:#fff;font-weight:700">
		推广用户参与列表
	</div>
 
</div>

 <section class="aui-flexView" style="padding-top:40px">
           
            <section class="aui-scrollView">
                <div class="aui-order-title">
    
    				<form class="am-form am-form-inline am-cf"
							action="<%=request.getContextPath()%>/childcanyu.do"
							method="post" id="ListForm">
							<input style="display: none" id="currentPage" name="currentPage" />
				 	 </form>
				  
    				<c:forEach items="${objs }" var="detail">            
                     <div class="aui-order-box">
                        <a href="droom.do?roomid=${detail.roomid}&qiname=${detail.qiname}&type=_childcanyu" class="aui-well-item">
                            <div class="aui-well-item-bd">
                                <h3>${detail.qiname}期</h3>
                            </div>
                            <span class="aui-well-item-fr" <c:if test="${detail.status =='wait'}">style="color:red"</c:if>>                    
                              <c:if test="${detail.status =='wait'}">等待开奖</c:if>
                              <c:if test="${detail.status =='finish'}">已结算</c:if>
                            
                        </a>
                        <p class="aui-order-fl aui-order-time"><fmt:formatDate value="${detail.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                         <p class="aui-order-fl aui-order-address">参与用户:<font style="font-size: 15px;font-weight: 600;">&nbsp&nbsp${detail.shortid}</font></p>
                        <p class="aui-order-fl aui-order-door">竞技号码:<font style="color: #fd0086;font-size: 15px;font-weight: 600;">&nbsp&nbsp${detail.number}</font></p>
                        <p class="aui-order-fl aui-order-address">参与数量:<font style="font-size: 15px;font-weight: 600;">&nbsp&nbsp${detail.amount}</font>ETH</p>
                        <c:if test="${detail.status =='finish'}">
                        	<p class="aui-order-fl aui-order-door">用户奖金:
                               <font style="font-size: 15px;font-weight: 600;">
                        		&nbsp${detail.award}</font>&nbspETH
                
   							</p>
   							<c:if test="${detail.ifyingkui ==1}">
                        	 <p class="aui-order-fl aui-order-address">盈利:
                               <font style="font-size: 15px;color: red;font-weight: 600;">
                        		&nbsp${detail.yingkui}</font>&nbspETH
                             </p>
                        	</c:if>
                        	<c:if test="${detail.ifyingkui ==-1}">
                        	 <p class="aui-order-fl aui-order-address">亏损:
                               <font style="font-size: 15px;font-weight: 600;">
                        		&nbsp${detail.yingkui}</font>&nbspETH
                        		</p>
                        	</c:if>
                       
                      
                      		 <p class="aui-order-fl aui-order-address">我的收益:<font style="color: red;font-size: 15px;font-weight: 600;">&nbsp&nbsp${detail.parentaward}</font>ETH(用户盈利部分的1%)</p>
                       </c:if>
                     </div>
                   </c:forEach>
                   
                   <div style="padding-top: 20px;right: 20px;color:#fff;text-align: left;float: bottom;height: 20px;min-width: 600px;bottom:-10px" class="fenye">${gotoPageFormHTML1}</div>
                </div>

            </section>
        </section>
        
        
    </body>
    
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
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
