<%@page import="com.webpos.common.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title class="txt_merchant_manager">商品管理</title>
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
				<div class="am-cf _div_tilte">商品列表</div>
			</div>		
		   		
			<div class="am-panel-hd " style="background-color: #fff;">	
				 <form class="am-form am-form-inline am-cf"  action="<%=request.getContextPath()%>/goods_list.do" method="post" id="ListForm">	
				  <input type="hidden" id="currentPage" name="currentPage" >
				 
				  <div class="am-form-group">			
		 			 <label class="am-text-sm _merchant_short" style="margin-bottom: 0px;font-size: 1.5rem;">倍数</label>:
		 		  </div>
		 		   <div class="am-form-group">			
				      <input type="text" value="${beishu }" name="beishu" id="beishu" class="am-input-sm" style="width:180px ;height: 30px"/> 
				  </div>	
				  
				   <div class="am-form-group">
					    	&nbsp;&nbsp;<label class="_pay_mode" style="margin-bottom: 0px;font-size: 1.5rem;">状态</label>:
					   </div>				
					   <div class="am-form-group">
							 <select name="status"  class="pay-m" style="width: 100px;">
								
								<option value="normal" <c:if test="${status eq 'normal'}">selected</c:if>>正常</option>	
								<option  value="delete" <c:if test="${status eq 'delete'}">selected</c:if>>下架</option>	
								
							</select>
					 </div>	
				 
				  <a	class="am-btn  am-btn-sm am-btn-fail am-fr" onclick="_jia()">添加商品</a>
				 
				  <a class="am-btn  am-btn-sm am-btn-danger am-fr" href="<%=request.getContextPath()%>/users.do">用户列表</a>	
				  
				  <button class="am-btn  am-btn-sm am-btn-primary am-fr _query">查询</button>	
				  
				  			 
				 </form>		
			</div>
			
		   <div class="am-panel-bd">			
		        <p style="color:red" id="p_tongji"></p>		
				<table  class="am-table am-table-bordered am-table-centered am-text-sm">
					<thead style="background-color: #f5f5f5;">
						<tr >
							<th class="_merchant_short">商品名</th>
							<th class="_contact">图片</th>
							
							<th class="_contact">价格</th>
							
							<th class="_contact" >现价</th>
							
							<th class="_contact" >倍数</th>
							<th class="_contact" >状态</th>
							
							<th class="_endtime" >创建时间</th>
							<th class="_operation" >操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objs }" var="user">
							<tr>
								<td>${user.name }</td>
								<td><img style="width:100px;height:100px" src="<%=request.getContextPath() %>/upload/${user.img }"></td>
								
								<td>${user.price }</td>
								
								<td>${user.nowprice }</td>
								<td>${user.beishu }</td>
								<td><c:if test="${user.status eq 'normal'}">正常</c:if><c:if test="${user.status eq 'delete'}">下架</c:if></td>
								
								<td><fmt:formatDate value="${user.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								
								<td class="merchant-m">
									
									<c:if test="${user.status eq 'normal'}">
									<a class="operate _detail" id="_in" onclick="deal('${user.id}','${user.name}')">下架</a>		
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
    <div id="qrCode-xia"  class="hide" style="height: 100%;padding: 15px;" >
    	<table style="text-align: center;width: 100%;height: 100%">
    		确定下架所选商品？
    		<tr >
    			<td colspan="4" >
    				<a id="button-query" style="font-size: 1rem" 
    				class="button-a button-a-primary" onclick="xia_btn()" href="javascript:void(0);">确定</a>
    			</td>
    		</tr>
    	</table>
    </div>
	
	
    <!-- 修改用户 -->
    <div id="qrCode-jia"  class="hide" style="height: 100%;padding: 15px;" >
    
    	<table style="text-align: center;width: 100%;height: 100%">
    		<tr height="30%">
    			<td colspan="4">
    				商品名:<input id="add_name" style="width:350px"></input>
    			</td>
    		</tr>
    		
    		<tr>
    			<td colspan="4">
    			 <input id="add_img" style="display:none" value=""/>
    			 <form id="uploadForm" enctype="multipart/form-data" method="POST" action="uploadimg.do">
    			上传图片:<input id="file" type="file" name="file"></input>
    			<input type="button" id="imgbtn"  value="上传"></input>
    			</form>
    			</td>
    		</tr>
    		
    		
    		<tr >
    			<td colspan="4">
    				价格:<input id="add_price" style="width:350px"></input>
    				
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4">
    				现价:<input id="add_nowprice" style="width:350px"></input>
    				
    			</td>
    		</tr>
    		
    		<tr >
    			<td colspan="4">
    				倍数:<select id="add_beishu" name="add_beishu" class="pay-m" style="width: 100px;">
								
								<option value="2" >2</option>	
								<option  value="6">6</option>	
								<option  value="250">250</option>	
								
						</select>
    			
    			</td>
    		</tr>
    		
    		
    		<tr >
    			<td colspan="4" >
    				<a id="button-query" style="font-size: 1rem" 
    				class="button-a button-a-primary" onclick="jia_btn()" href="javascript:void(0);">确定</a>
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


$("#imgbtn").click(function(){
	//var form = new FormData($("#uploadForm"));
	var formData = new FormData($("#uploadForm")[0]);
 $.ajax({
     url:"uploadimg.do",
     async : false,
     cache : false,
     type : "post",
     data : formData,
     dataType : 'json',
     contentType: false, //必须
     processData: false, //必须
     success:function(data){
	       if(data=="fail"){
	    	   alert("上传失败");
	       }else{
	    	   
	    	   $("#add_img").val(""+data);
	    	   alert("上传成功"+data);
	       }
     },
     error:function(e){
         alert("错误！！");
     }
 }); 
});
		
var selectid="";
function deal(id,amount,user_id){
	 selectid = ""+id;
	
	layer.open({
		  title : "下架提醒",
		  type: 1, //page层
		  area: ['400px', '400px'],
		  shade: 0.6, //遮罩透明度
		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
		  shift: 1, //0-6的动画形式，-1不开启
		  content: $('#qrCode-xia')
	});  
}


function _jia() {

	layer.open({
		title : "添加",
		type : 1, //page层
		area : [ '400px', '400px' ],
		shade : 0.6, //遮罩透明度
		moveType : 1, //拖拽风格，0是默认，1是传统拖动
		shift : 1, //0-6的动画形式，-1不开启
		content : $('#qrCode-jia')
	});

}

function xia_btn(){
	$.ajax({
		url:'goods_xia.do',
        data:"id="+selectid,
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data=="SUCCESS"){
        		alert('成功，请刷新页面!');
        	}else 
        	{
        		alert(''+data);
        	}
        }
    });
}

 function jia_btn(){
	
	 $.ajax({
		url:'goods_jia.do',
        data:"add_name="+$("#add_name").val()+"&add_price="+$("#add_price").val()+"&add_nowprice="+$("#add_nowprice").val()
        	+"&add_beishu="+$("#add_beishu").val()+"&add_img="+$("#add_img").val(),
        type:'post',
        dataType:'json',
        traditional:true,
        success:function(data){
        	if(data=="SUCCESS"){
        		alert('成功，请刷新页面!');
        	}else 
        	{
        		alert(''+data);
        	}
        }
    }); 
} 


</script>
</html>
 