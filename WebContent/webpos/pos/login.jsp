<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/webpos/pos/assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="<%=request.getContextPath()%>/webpos/pos/assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
 
  <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/webpos/pos/assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="<%=request.getContextPath()%>/webpos/pos/assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/assets/css/amazeui.min.css">

 <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/assets/css/app.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/css/public.css?v=0.12"/>
<link href="<%=request.getContextPath()%>/webpos/pos/css/style.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css">
<style type="text/css">
.weixinTip{
	position:fixed;
	left:0px;
	top:0px;
	width:100%;
    z-index:1000;
	background-color:rgba(0,0,0,0.8);
	filter:alpha(opacity=80)
}
.weixinTip p{
	text-align:center;
	margin-top:10%;
	padding-left:5%;
	padding-right:5%
}

.spinner9{
  margin: 30px auto;
  width: 50px;
  height: 60px;
  text-align: center;
  font-size: 10px;
}
 
.spinner9 > div {
  background-color: #67CF22;
  height: 100%;
  width: 6px;
  display: inline-block;
   
  -webkit-animation: stretchdelay 1.2s infinite ease-in-out;
  animation: stretchdelay 1.2s infinite ease-in-out;
}
 
.spinner9 .rect2 {
  -webkit-animation-delay: -1.1s;
  animation-delay: -1.1s;
}
 
.spinner9 .rect3 {
  -webkit-animation-delay: -1.0s;
  animation-delay: -1.0s;
}
 
.spinner9 .rect4 {
  -webkit-animation-delay: -0.9s;
  animation-delay: -0.9s;
}
 
.spinner9 .rect5 {
  -webkit-animation-delay: -0.8s;
  animation-delay: -0.8s;
}
 
@-webkit-keyframes stretchdelay {
  0%, 40%, 100% { -webkit-transform: scaleY(0.4) } 
  20% { -webkit-transform: scaleY(1.0) }
}
 
@keyframes stretchdelay {
  0%, 40%, 100% {
    transform: scaleY(0.4);
    -webkit-transform: scaleY(0.4);
  }  20% {
    transform: scaleY(1.0);
    -webkit-transform: scaleY(1.0);
  }
}
</style>

<title><spring:message code="title"/></title>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?d08321caa4ded9e8f406743dac85e273";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</head>
<body style="background-image: url(<%=request.getContextPath()%>/webpos/pos/img/bg.jpg);background-attachment: fixed;background-repeat: no-repeat;background-size: cover;" >


<input id="tips_fail1" value="<spring:message code="thank_you_join"/>" style="display:none">
<input id="tips_fail2" value="<spring:message code="bad_luck"/>" style="display:none">
<input id="tips_fail3" value="<spring:message code="once_more"/>" style="display:none">
<input id="tips_fail4" value="<spring:message code="lost_a_little"/>" style="display:none">
<input id="tips_fail5" value="<spring:message code="goon_join"/>" style="display:none">
<input id="tips_success" value="<spring:message code="win_success"/>" style="display:none">

<div style="background-color: #000;">
	<input id="error_login_first" value="<spring:message code='error_login_first'/>" style="display:none"/>
	<input id="error_login_again" value="<spring:message code='error_login_again'/>" style="display:none"/>
	<input id="error_least_join_number" value="<spring:message code='error_least_join_number'/>" style="display:none"/>
	<input id="error_least_withdraw_number" value="<spring:message code='error_least_withdraw_number'/>" style="display:none"/>
	<input id="error_balance_not_enough" value="<spring:message code='error_balance_not_enough'/>" style="display:none"/>
	<input id="error_network" value="<spring:message code='error_network'/>" style="display:none"/>
	<input id="error_max_withdraw" value="<spring:message code='error_max_withdraw'/>" style="display:none"/>
	<input id="error_sumbit_success" value="<spring:message code='error_sumbit_success'/>" style="display:none"/>
	<input id="please_input_address" value="<spring:message code='please_input_address'/>" style="display:none"/>
	<input id="error_verfiy" value="<spring:message code='error_verfiy'/>" style="display:none"/>
	<input id="error_sign" value="<spring:message code='error_sign'/>" style="display:none"/>
	<input id="error_time" value="${localtime}" style="display:none"/>
	<input id="error_service_time" value="<spring:message code='error_service_time'/>" style="display:none"/>
	
	
	<input id="lan" <c:if test="${lan == 'zh'}">value="zh-cn"</c:if> <c:if test="${lan != 'zh'}">value="en"</c:if> style="display:none"/>
  <!-- <div style="width:50px;float: left;">
   	<button id="btn_exit"  style="<c:if test="${user eq null}">display:none</c:if> width:50px;height:30px;background-image: url(/webpos/pos/img/exit.png);background-repeat: no-repeat;"></button>	
  </div>-->
  <button id="btn_wait_show" style="display:none" data-am-modal="{target: '#doc-modal-wait', closeViaDimmer: 0, width: 200, height: 125}"></button> 
  
  <div style="margin-right:0px">
  	
  	 <div>
  	      <div style="width:80px;float:right;">
  
 			 <ul class="menu" id="lan_menu">
                
                <li class="is-hidden-mobile"><div class="lang closed">
                    <span class="flag <c:if test="${lan == 'en'}">en_img</c:if> 
                    <c:if test="${lan == 'jp'}">jp_img</c:if>
                    <c:if test="${lan == 'ru'}">ru_img</c:if> 
                    <c:if test="${lan == 'ko'}">ko_img</c:if>
                    <c:if test="${lan == 'zh'}">zh_img</c:if>
                    <c:if test="${lan == 'al'}">al_img</c:if>"             
                    >
                    <c:if test="${lan == 'en'}">English</c:if> 
                    <c:if test="${lan == 'jp'}">日本語</c:if>
                    <c:if test="${lan == 'ru'}">Русский</c:if> 
                    <c:if test="${lan == 'ko'}">한국어</c:if>
                    <c:if test="${lan == 'al'}">العربية</c:if>
                    <c:if test="${lan == 'zh'}">汉语</c:if>
                    </span>
                    <input id="lan_input" value="${lan}" style="display:none">
                    <ul id="lan_ul" class="lan_list dispaly_class" style="padding-bottom:15px;width: 120px;">
                      <li ><img style="width: 20px;" src="<%=request.getContextPath()%>/webpos/pos/img/en.png"/><a  class="<c:if test="${lan == 'en'}">active</c:if>" href="activity.do?lan=en&parent=${parent}">English</a></li>
                      <!-- <li><img style="width: 20px;" src="<%=request.getContextPath()%>/webpos/pos/img/jp.png"/><a  class="<c:if test="${lan == 'jp'}">active</c:if>" href="activity.do?lan=jp&parent=${parent}">日本語</a></li>
                      <li><img style="width: 20px;" src="<%=request.getContextPath()%>/webpos/pos/img/ru.png"/><a  class="<c:if test="${lan == 'ru'}">active</c:if>" href="activity.do?lan=ru&parent=${parent}">Русский</a></li>
                      <li><img style="width: 20px;" src="<%=request.getContextPath()%>/webpos/pos/img/ko.png"/><a  class="<c:if test="${lan == 'ko'}">active</c:if>" href="activity.do?lan=ko&parent=${parent}" > 한국어</a></li>
                        <li><img style="width: 20px;" src="<%=request.getContextPath()%>/webpos/pos/img/al.png"/><a  class="<c:if test="${lan == 'al'}">active</c:if>" href="activity.do?lan=al&parent=${parent}">العربية</a></li> -->
                        <li><img style="width: 20px;" src="<%=request.getContextPath()%>/webpos/pos/img/cn.png"/><a  class="<c:if test="${lan == 'zh'}">active</c:if>" href="activity.do?lan=zh&parent=${parent}">汉语</a></li>
       
                    </ul>
                  </div>
                </li>
              </ul>
  	    
  		 </div>
  		<div class="scroll" style="margin-right:80px">
           <marquee id="scrollText" onmouseover="scrollText.stop();" style="color: #fff;"
   				 onmouseout="scrollText.start();" scrollAmount="2" direction="left" >
			<img style="width: 12px;margin-right: 10px;" src="<%=request.getContextPath()%>/webpos/pos/img/message.png"/> <spring:message code="message"/>
            </marquee>
          </div> 
            
 	</div>
 
 	<div style="background-color: #000;color: #fff;font-size: 8px;text-align: center;">
      <spring:message code="center_amount"/>：<a id="pool_amount" style="line-height: 30px; color: #FFFF00;font-size: 15px;font-weight: bold;">0 </a>ETH
 	</div></div>
 	
</div>


 
<div style="padding-top: 1rem;padding-bottom: 1rem;}">

<c:if test="${user eq null}">
<div class="navigation" style="text-align: center;font-size:20px;">
             
              <p>
                <a style="text-decoration: none;color: #FFFFFF;line-height: 20px;"><spring:message code="input_address_get_eth_right"/></a><br><br>
                <a style="text-decoration: none;color: #FFFFFF;line-height: 20px;"><spring:message code="share_success_get"/></a><br><br>
                
              </p>
              
               <form id="form1" action="<%=request.getContextPath()%>/activity.do" method="post" style="margin-top: 20px;">
                 <input class="input-address" name="eth_address" id="eth_address" placeholder="<spring:message code="please_input_address"/>" type="text">
                 <input style="display:none" name="parent" id="parent" value="${parent}">
                 <input style="display:none" name="remark" id="remark" value="">
                 <input style="display:none" name="geetest_challenge" id="geetest_challenge" value="">
                 <input style="display:none" name="geetest_validate" id="geetest_validate" value="">
                 <input style="display:none" name="geetest_seccode" id="geetest_seccode" value="">
            
          		 <input id="btn_login" value="<spring:message code="string_Login"/>" type="button" class="input-login">
               
              </form>
</div>
</c:if> 

<c:if test="${user != null}">
		
		 <div style="width:100%;font-size:16px">
		 	
            
            <div style="float:right;width:160px;height:70px">
                <!-- <button class="btn_in" style="overflow: hidden;word-break: break-all;" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 300}"><spring:message code="balance_in"/></button>  -->
                <button class="btn_in" style="overflow: hidden;word-break: break-all;" onclick="window.location.href='<%=request.getContextPath()%>/recharge_page.do'"><spring:message code="personal_center"/></button> 
                
                <button class="btn_out" style="overflow: hidden;margin-right: 25px;word-break: break-all;" data-am-modal="{target: '#doc-modal-2', closeViaDimmer: 0, width: 400, height: 260}"><spring:message code="balance_out"/></button> 
            </div>            
        		
        	<div style="margin-right:160px;height:70px">
                <a style="text-decoration: none;color: #FFFFFF;">${user.id_short}</a>
               
                </br>
                </br>    
                <a style="text-decoration: none;color: #FFD74B;"><spring:message code="balance"/>：${user.balance}ETH</a> 
            </div>
             
         </div>
              
		
		 
		  <input id="balance" value="${user.balance}" style="display:none" type="text">
		
     	  <div>
            <input class="input-address" style="margin-left: 10px; margin-right: 10px;max-width: 90%;" name="amount" id="amount"  value="${number}"
              placeholder="<spring:message code='pleanse_input_join_number'/>" type="number">
         
		  </div>
           

     </c:if>
       
</div> 


<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-3" style="top:auto;bottom:auto">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd">
       </br>
       <blockquote style="background-color: #f3f3f3;font-size: 15px;">
     
      <p style="line-height: 20px;">1.<spring:message code='share_reason1'/></p>
     </blockquote>
     
     <blockquote style="background-color: #f3f3f3;font-size: 15px;">
     
      <p style="line-height: 20px;">2.<spring:message code='share_reason2'/></p>
     </blockquote>
     
      <blockquote style="background-color: #f3f3f3;font-size: 15px;">
    
      <p style="line-height: 20px;">3.<spring:message code='share_reason3'/></p>
     
     </blockquote>
    
    </div>
  </div>
</div>


<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1" style="top:auto;bottom:auto">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="line-height: 20px;"><spring:message code='balance_in'/>
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd" style="line-height: 20px;">
      <spring:message code='in_address_success'/>
   
     <a style="user-select:auto; -webkit-user-select:auto; -moz-user-select:auto; -ms-user-select:auto; user-select:auto;">0x0F23430D929F24b86CB3Bf375B94d96361d0420a</a>
    
     <img style="width:90px" src="<%=request.getContextPath()%>/webpos/pos/img/imtoken.png"></img>
      <blockquote style="background-color: #f3f3f3;font-size: 15px;">
      <p><spring:message code='in_question_contact'/>
      
      <c:if test="${lan == 'zh'}">
      QQ:1371247467
      </c:if>
      <c:if test="${lan != 'zh'}">
      FaceBook:Token eth
      </c:if>
      </p>
     </blockquote>
    </div>
  </div>
</div>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-wait" style="top:auto;bottom:auto">
 <a  id="wait_close" href="javascript: void(0)" style="display:none" class="am-close am-close-spin" data-am-modal-close>&times;</a>
  <div class="am-modal-dialog">
   <div class="spinner9">
  <div class="rect1"></div>
  <div class="rect2"></div>
  <div class="rect3"></div>
  <div class="rect4"></div>
  <div class="rect5"></div>
  </div>
  </div>
</div>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-2" style="top:auto;bottom:auto">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="line-height:20px"><spring:message code='balance_out'/>（<spring:message code='out_only_login_address'/>）
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    
    <div class="am-modal-bd" style="margin-top: 10px;">
      <input class="input-address" name="eth_withdraw" id="eth_withdraw" placeholder="<spring:message code='input_out_number'/>" type="number">   
      <input id="btn_withdraw" value="<spring:message code='submit'/>" type="button" class="input-login">
    
      <blockquote style="background-color: #f3f3f3;font-size: 15px;">
      
      <p style="line-height:20px"><spring:message code='out_question_contact'/>
      <c:if test="${lan == 'zh'}">
      QQ:1977325434
      </c:if>
      <c:if test="${lan != 'zh'}">
      FaceBook:Imtoken Eth
      </c:if>
      </p></br>
      
       <p><spring:message code='least_out_number'/></p></br>
      
       <p><spring:message code='out_service_charge'/></p></br>
   
     </blockquote>
    </div>
  </div>
</div>


<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-spread" style="top:auto;bottom:auto">
  <div class="am-modal-dialog">
    <div class="am-modal-hd" style="line-height: 20px;"><spring:message code='share_success_get'/>
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    
    <div class="am-modal-bd" style="margin-top: 10px;" id="spread_inpiut">
      <spring:message code='share_url'/>:<a id="spread-text">http://eth-office.club/activity.do?lan=${lan}&parent=${user.id_md5}</a>
    </div>
    <div id="qrcode-spread" style="text-align: center;margin: auto;">
    </div>
  </div>
</div>



<div style="margin-top: -40px;">
	
  <img src="<%=request.getContextPath()%>/webpos/pos/img/1.png" id="shan-img" style="display:none;">
    <img src="<%=request.getContextPath()%>/webpos/pos/img/2.png" id="sorry-img" style="display:none;">
	<div class="banner" style="max-width: 300px;">
		<div class="turnplate">
			<div class="turnplate" id="turnplate_div" style="background-image:url(<%=request.getContextPath()%>/webpos/pos/img/bg.png);background-size:100% 100%;margin-top:30%;">
			<canvas class="item" id="wheelcanvas" width="422px" height="422px"></canvas></div>
			
			<img class="pointer" ontouchstart="gtouchstart()" ontouchend="gtouchend()" id="pointer" src="<%=request.getContextPath()%>/webpos/pos/img/img_03.png">
		</div>
	</div>
	
	<c:if test="${user != null}">
	<div class="progress" style="width: 50%;margin: 10px auto;visibility:hidden" id="ener-div">
			<div class="progress-bar" id="progress_div" style="width: 0%; background:#3697bf;">
					<span id="progress_content"></span>
			</div>
	</div>
	<button id="btn_begin" onmouseup="bgtouchend()" onmousedown="bgtouchstart()" ontouchstart="bgtouchstart()" ontouchend="bgtouchend()" type="button" class="bubbly-button css-3d-btn"
	style="background-image: url(<%=request.getContextPath()%>/webpos/pos/img/login_btn.png);width: 150px;height: 44px;background-repeat: no-repeat;"
	><spring:message code='press_me'/></button>
	
	</c:if>
	
	
	
</div>

<button id="div_btn_gift" style="display:none"  data-am-modal="{target: '#doc-modal-spread', closeViaDimmer: 0, width: 400, height: 225}"></button>
<img id="div_gift" style="width: 70px;position:absolute;left:0;bottom:100px;" src="<%=request.getContextPath()%>/webpos/pos/img/gift.png"
	/>
	
<button id="div_btn_tips" style="display:none"  data-am-modal="{target: '#doc-modal-3', closeViaDimmer: 0, width: 400, height: 330}"></button>
<img id="div_tips" style="width: 70px;position:absolute;left:0;bottom:0;" src="<%=request.getContextPath()%>/webpos/pos/img/cs.png"
	/>
<!-- <script type="text/javascript">
		function is_weixin() {
		    var ua = navigator.userAgent.toLowerCase();
		    if (ua.match(/MicroMessenger/i) == "micromessenger") {
		        return true;
		    } else {
		        return false;
		    }
		}
		var isWeixin = is_weixin();
		var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
		function loadHtml(){
			var div = document.createElement('div');
			div.id = 'weixin-tip';
			div.innerHTML = '<p><img style="width:80%" src="<%=request.getContextPath()%>/webpos/pos/img/live_weixin.png" alt="微信打开"/></p>';
			document.body.appendChild(div);
		}
		
		function loadStyleText(cssText) {
	        var style = document.createElement('style');
	        style.rel = 'stylesheet';
	        style.type = 'text/css';
	        try {
	            style.appendChild(document.createTextNode(cssText));
	        } catch (e) {
	            style.styleSheet.cssText = cssText; //ie9以下
	        }
            var head=document.getElementsByTagName("head")[0]; //head标签之间加上style样式
            head.appendChild(style); 
	    }
	    var cssText = "#weixin-tip{position: fixed; left:0; top:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80); width: 100%; height:100%; z-index: 100;} #weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%;}";
		if(isWeixin){
			loadHtml();
			loadStyleText(cssText);
		}
</script> -->
<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery-1.8.2.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/awardRotate.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/bignumber.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/web3.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/qrcode.min.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/gt.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/md5.js"></script>
<script src="<%=request.getContextPath()%>/webpos/pos/js/xx.js?v=<%=System.currentTimeMillis()%>"></script>

</body>
</html>
