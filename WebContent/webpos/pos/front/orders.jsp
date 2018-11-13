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
<title>我的订单</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/js/page/pageGroup.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">
	<link rel='stylesheet' href="<%=request.getContextPath()%>/webpos/pos/front/css/mdialog.css?t=3">
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/canyu.css?t=2">

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
		我的订单
	</div>
 
</div>

 <section class="aui-flexView" style="padding-top:40px">
           
            <section class="aui-scrollView">
                <div class="aui-order-title">
    
    				<form class="am-form am-form-inline am-cf"
							action="<%=request.getContextPath()%>/orders.do"
							method="post" id="ListForm">
							<input style="display: none" id="currentPage" name="currentPage" />
				 	 </form>
				  
    				<c:forEach items="${objs }" var="detail">
    				 <c:if test="${detail.goodsid =='1'}">
    				<div class="aui-order-box">
                        <a   class="aui-well-item">
                            <div class="aui-well-item-bd">
                                <h3></h3>
                            </div>
                            <span class="aui-well-item-fr" <c:if test="${detail.status =='wait'}">style="color:yellowgreen"</c:if>
                               <c:if test="${detail.status =='waitling'}">style="color:red"</c:if>>                    
                              <c:if test="${detail.status =='wait'}">等待开奖</c:if>
                              <c:if test="${detail.status =='finish'}">已完成</c:if>
                               <c:if test="${detail.status =='waitling'}">等待领奖</c:if>
                                <c:if test="${detail.status =='notjiang'}">未中奖</c:if>
                                 <c:if test="${detail.status =='waitsend'}">待发货</c:if>
                                  <c:if test="${detail.status =='balance'}">已退至余额</c:if>
                                   <c:if test="${detail.status =='hassend'}">已发货</c:if>
                           </span>
                           
                           
                        </a>
                        <p class="aui-order-fl aui-order-time">时间<fmt:formatDate value="${detail.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                       
                        <p class="aui-order-fl aui-order-door">商品:<font style="color: #fd0086;font-size: 15px;font-weight: 600;">&nbsp&nbsp${detail.goodsname}</font></p>
                        <p class="aui-order-fl aui-order-address">价格:<font >¥${detail.price}</font>
                        </p> 
                           <p class="aui-order-fl aui-order-door">实付款:
                        		<font style="font-size: 15px;font-weight: 600;">&nbsp&nbsp¥${detail.actualprice}</font>
                        	</p>	
        				 <c:if test="${detail.status =='waitling'}">
        				 	<p class="aui-order-fl aui-order-door">
         						<button onclick="ling(${detail.id})" style="float: right;background-color: #fd2291;border-radius: 20px; height: 40px; width: 120px; color: #fff; line-height: 40px; text-align: center; font-size: 18px; font-weight: 1000;">领取商品</button>
         				 	</p>
         				 
        				 </c:if>
         
                     </div>
                     
                     </c:if>
                     <!--  -->
    				 <c:if test="${detail.goodsid !='1'}">
    				            
                     <div class="aui-order-box">
                        <a   class="aui-well-item">
                            <div class="aui-well-item-bd">
                                <h3>${detail.qiname}期</h3>
                            </div>
                            <span class="aui-well-item-fr" <c:if test="${detail.status =='wait'}">style="color:yellowgreen"</c:if>
                               <c:if test="${detail.status =='waitling'}">style="color:red"</c:if>>                    
                              <c:if test="${detail.status =='wait'}">等待开奖</c:if>
                              <c:if test="${detail.status =='finish'}">已完成</c:if>
                               <c:if test="${detail.status =='waitling'}">等待领奖</c:if>
                                <c:if test="${detail.status =='notjiang'}">未中奖</c:if>
                                 <c:if test="${detail.status =='waitsend'}">待发货</c:if>
                                  <c:if test="${detail.status =='balance'}">已退至余额</c:if>
                                   <c:if test="${detail.status =='hassend'}">已发货</c:if>
                           </span>
                           
                           
                        </a>
                        <p class="aui-order-fl aui-order-time">开奖时间<fmt:formatDate value="${detail.kjtime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                       
                        <p class="aui-order-fl aui-order-door">商品:<font style="color: #fd0086;font-size: 15px;font-weight: 600;">&nbsp&nbsp${detail.goodsname}</font></p>
                        <p class="aui-order-fl aui-order-address">价格:<font style="text-decoration: line-through;">¥${detail.price}</font>
                        	<font style="font-size: 15px;font-weight: 600;">&nbsp&nbsp¥${detail.nowprice}</font></p>
                      
         				<p class="aui-order-fl aui-order-door">方案类型:<font style="color: #c7d0ff;font-size: 15px;font-weight: 600;">
         					&nbsp&nbsp
         					 <c:if test="${detail.type ==2}">猜大小</c:if>
         					 <c:if test="${detail.type ==6}">猜第一个号码</c:if>
         					 <c:if test="${detail.type ==250}">任选5</c:if>
         				</font></p>
                        <p class="aui-order-fl aui-order-address">我购买的方案:
                        	<font style="font-size: 15px;font-weight: 600;">
                             <c:if test="${detail.type ==2}">
                             		 <c:if test="${detail.number1 ==1}">大</c:if>
                             		 <c:if test="${detail.number1 ==0}">小</c:if>
                             </c:if>
         					 <c:if test="${detail.type ==6}">
         					 	${detail.number2}
							</c:if>
         					 <c:if test="${detail.type ==250}">
         					 		${detail.number3}
         					 </c:if>
                        	</font>
                        </p>
                           <p class="aui-order-fl aui-order-door">实付款:
                        		<font style="font-size: 15px;font-weight: 600;">&nbsp&nbsp¥${detail.actualprice}</font></p>
                        	
                        <c:if test="${detail.status !='wait'}">
                         <p class="aui-order-fl aui-order-address">开奖结果:<font style="color: #fd0086;font-size: 15px;font-weight: 600;">
         					&nbsp&nbsp${detail.kjnumber}
         					 <c:if test="${detail.type ==2}">
         					 		 <c:if test="${detail.resultnumber1 ==1}">(大)</c:if>
                             		 <c:if test="${detail.resultnumber1 ==0}">(小)</c:if>
         					 </c:if>
         					
         				 </font></p>
        				</c:if>
        				
        				 <c:if test="${detail.status =='waitling'}">
        				 	<p class="aui-order-fl aui-order-door">
         					
         						<button onclick="duihuan(${detail.id},${detail.price})" style="background-color: #229ffd;border-radius: 20px; height: 40px; width: 120px; color: #fff; line-height: 40px; text-align: center; font-size: 18px; font-weight: 1000;">兑成余额</button>
         						<button onclick="ling(${detail.id})" style="float: right;background-color: #fd2291;border-radius: 20px; height: 40px; width: 120px; color: #fff; line-height: 40px; text-align: center; font-size: 18px; font-weight: 1000;">领取商品</button>
         				 	</p>
         				 
        				 </c:if>
         
                     </div>
                     
                     </c:if>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/mdialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpos/pos/front/js/bringins.js?t=3"></script>
<script type="text/javascript">
	function gotoPage(n) {
		//alert(n);
		$("#currentPage").val("" + n);
		$("#ListForm").submit();
	}
	
	function duihuan(detailid,price){
		var zhong = price*0.97;
		zhong = zhong.toFixed(2);
		if (confirm("您确定兑换"+zhong+"元到余额吗？（备注:商品价值"+price+"元，平台收取3%商品回收费用）")) {  
			gotoback(detailid);
        }else{
        	
        }

	}
	
	var waitdialog=null;
	function gotoback(detailid){
		waitdialog = new TipBox({type:'load',str:'正在提交',hasBtn:false});
		$.ajax({
			async:true,
			type:'post',
			url:'backtobalance.do',
			data:{orderid:""+detailid,},
			dataType:'json',
			success:function(result,textStatus){
				
				waitdialog.destroy();
				
				if(result.result=="SUCCESS"){
					new TipBox({type:'success',str:'兑换成功，请刷新当前页面！',hasBtn:true});
					window.location.reload();
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
	
	function ling(detailid){
		window.location.href='lingqugoods.do?orderid='+detailid;
	}
</script>

</html>
