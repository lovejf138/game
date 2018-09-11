
var show_lan=false;

var turnplate={
		restaraunts:[],				//大转盘奖品名称
		
		colors:[],					//大转盘奖品区块对应背景颜色
		outsideRadius:192,			//大转盘外圆的半径
		textRadius:155,				//大转盘奖品位置距离圆心的距离
		insideRadius:68,			//大转盘内圆的半径
		startAngle:0,				//开始角度
		randomRate:[],              //控制获奖率，百分制(相加需等于100%)，对应restaraunts(顺序需要保持一致)，
		bRotate:false				//false:停止;ture:旋转
};
var status=0;//0是试用，1为正式
//$(document).ready(function(){
	
	//动态添加大转盘的奖品与奖品区域背景颜色
	turnplate.randomRate = ["10%", '10%', '10%', '10%', '10%', '10%', '10%', '10%', '10%', '10%'];  //小心设置按100%分配
	
	turnplate.restaraunts = [$("#tips_fail1").val(), $("#tips_success").val(), $("#tips_fail2").val(), $("#tips_success").val(), 
		$("#tips_fail3").val(), $("#tips_success").val(), $("#tips_fail4").val(),
		$("#tips_success").val(), $("#tips_fail5").val(),$("#tips_success").val()];
//	turnplate.restaraunts = [$("#tips_success").val(), $("#tips_success").val(), $("#tips_success").val(), $("#tips_success").val(), 
//		$("#tips_success").val(), $("#tips_success").val(), $("#tips_success").val(),
//		$("#tips_success").val(), $("#tips_success").val(),$("#tips_success").val()];
	turnplate.restaraunts2 = ["0", "2.0", "0", "2.0", "0", "2.0", "0", "2.0", "0", "2.0"];
	//turnplate.restaraunts2 = ["0.1", "1.6", "0.5", "2.0", "0.9", "1.8", "0.7", "1.4", "0.3", "1.2"];
	turnplate.colors = ["#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF","#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF","#FFF4D6", "#FFFFFF"];


	var rotateTimeOut = function (){
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:2160,
			duration:3000,
			callback:function (){
				alert($("#error_network").val());
			}
		});
		$('#turnplate_div').rotate({
			angle:0,
			animateTo:2160,
			duration:3000,
			callback:function (){
		
			}
		});
	};

	//旋转转盘 item:奖品位置; txt：提示语;[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
	var rotateFn = function (item, txt,time){
		var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length*2));
		
		if(angles<270){
			angles = 270 - angles;
		}else{
			angles = 360 - angles + 270;
		}
	
		$('#turnplate_div').stopRotate();
		$('#turnplate_div').rotate({
			angle:0,
			animateTo:3600,
			duration:time,
			callback:function (){
				
			}
		});
		
		$('#wheelcanvas').stopRotate();
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:time,
			callback:function (){
				turnplate.bRotate = !turnplate.bRotate;
				alert(txt);
				if(status==1){
					 window.location.reload();
				}
				
			}
		});
		
		
	};

//	$('.pointer').click(function (){
//		if(turnplate.bRotate)return;
//		status=0;
//		turnplate.bRotate = !turnplate.bRotate;
//		//获取随机数(奖品个数范围内)
//		var item = rnd(turnplate.randomRate);
//		//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
//		rotateFn(item, turnplate.restaraunts[item-1]);
//	});
	
    
//	$("#btn_free").click(function (){
//		if(turnplate.bRotate)return;
//		status=0;
//		turnplate.bRotate = !turnplate.bRotate;
//		//获取随机数(奖品个数范围内)
//		var item = rnd(turnplate.randomRate);
//		//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
//		rotateFn(item, turnplate.restaraunts[item-1]);
//	});
	
	$("#div_tips").click(function(event){
		$("#div_btn_tips").click();
	});
	
	$("#div_gift").click(function(event){
		$("#div_btn_gift").click();
	});
	
	
	$("#qrcode-spread").qrcode({ 
	    render: "canvas", //table方式 
	    width: 100, //宽度 
	    height:100, //高度 
	    text: $("#spread-text").html() //任意内容 
	}); 
	//var qrcode = new QRCode("qrcode-spread");
	
	//qrcode.makeCode($("#spread-text").html());
	    
	$("#btn_exit").click(function(event){
		window.location.href="logout.do";
	});
	
	
	$("#btn_withdraw").click(function(event){
		var fee=$("#amount").val();
		
		var txt_withdraw= $("#eth_withdraw").val();
		var _amount=0;
		
		try{
			_amount = parseFloat(txt_withdraw);
		}catch(e) {
			_amount = 0;
		}
		var wl = txt_withdraw.trim().length;
		
		if(wl<=0) {
			_amount=0;
		}
		
		if(_amount<0.1){
			alert($("#error_least_withdraw_number").val());
			return ;
		}
		
		
		var txt_balance = $("#balance").val();
		var _balance=0;
		var bl = txt_balance.trim().length;
		
		if(bl<=0) {
			_balance=0;
		}
		try{
			_balance = parseFloat(txt_balance);
		}catch(e) {
			_balance = 0;
		}
		if(_amount>_balance){
			alert($("#error_balance_not_enough").val());
			return ;
		}
	
		var x = _amount+$("#error_time").val()+"!#@#Qsaswe@#./1!";
		var _sign = hex_md5(x);
		
		$.ajax({
			async:true,
			type:'post',
			url:'withdraw.do',
			data:{amount:""+_amount,sign:""+_sign},
			dataType:'json',
			success:function(result,textStatus){
				
				
				if(result.result=="SUCCESS"){
					var f_id = result.desc;
					//alert("恭喜你，中奖了");
//					rotateFn(f_id, "恭喜你，中奖了");
					alert($('#error_sumbit_success').val());
					window.location.reload();
				}else if (result.result=="FAIL"){
					var f_id = result.desc;
					//alert("抱歉，再接再厉！！！");
					alert($("#"+result.desc).val());
					
				}else if(result.result=="FAIL_MAX_WITHDRAW"){
					alert($("#error_max_withdraw").val()+" "+result.desc);
				}
				
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				
				alert($("#error_network").val());
			}
		});
	});
	
	
	$("#lan_menu").click(function(event){
		//$("#lan_ul").removeClass("dispaly_class");
		if(show_lan){
			show_lan=false;
			$("#lan_ul").addClass("dispaly_class");
		}else{
			var show_lan=true;
			$("#lan_ul").removeClass("dispaly_class");
		}
	});
	
	

	
	var number_remark="";
	$("#btn_login").click(function(event){
		
		
		
		var eth_address=$("#eth_address").val();
		
		var el = eth_address.trim().length;
		
		if(eth_address.toString().indexOf("VIP")>=0||eth_address.toString().indexOf("vip")>=0){
			$("#remark").val("");
			gotoLogin();
			 return;
		}
		if(el<=30) {
			alert($("#please_input_address").val());
			$("#eth_address").val("");
			
			return;
		}
		$("#btn_wait_show").click();
		
		  var Web3 = require('web3');
		   var web3 = new Web3();
		   web3.setProvider(new web3.providers.HttpProvider("https://mainnet.infura.io/<APIKEY>"));
		   try{
		   var originalBalance = web3.eth.getBalance(eth_address).toNumber();//.
		   var ss_a = web3.fromWei(originalBalance);
		   number_remark=ss_a;
		   $("#remark").val(""+ss_a);
//		   $("#form1").submit();
		   gotoLogin();
		  // 	if_verfiy();
		   }catch(e){
			   
			   if(e.toString().indexOf("invalid address")>0){
				   $("#wait_close").click();
				   alert($("#please_input_address").val());
				   
				   
			   }else{
				   if(e.toString().indexOf("CONNECTION ERROR")>0){
					  // if_verfiy();
					   $("#remark").val("");
					   gotoLogin();
//					   $("#form1").submit();
//					   $("#wait_close").click();
					   return;
				   }else{
					   $("#wait_close").click();
					   alert(e.toString());
					   
					 
				   }
			   }
		   }
		
	});
	
function gotoLogin(){
	
		//$("#form1").submit();
	var x = $("#eth_address").val()+"@"+$("#parent").val()+"@"+$("#remark").val()+$("#error_time").val()+"!#@#Qsaswe@#./1!";
	var _sign = hex_md5(x);
	
	$.ajax({
        
	    url: "yy.do",//?eth_address=" +$("#eth_address").val()+"&parent="+$("#parent").val()+"&remark="+$("#remark").val(),//+"&geetest_challenge="+$("#geetest_challenge").val()+"&geetest_validate="+$("#geetest_validate").val()+"&geetest_seccode="+$("#geetest_seccode").val()
	    type: "post",
	    data:{eth_address:$("#eth_address").val(),parent:$("#parent").val(),remark:$("#remark").val(),sign1:_sign},
	    dataType: "json",
	    success: function (data) {
	    	
	    	if(data.result=="SUCCESS"){
	    		window.location.reload();
	    	}else if(data.result=="need_verify"){
	    		//$("#wait_close").click();
	    		
	    		 // 调用 initGeetest 初始化参数
		        // 参数1：配置参数
		        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接
		        initGeetest({
		            gt: data.desc.gt,
		            challenge: data.desc.challenge,
		            new_captcha: data.desc.new_captcha, // 用于宕机时表示是新验证码的宕机
		            offline: !data.desc.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
		            product: "bind", // 产品形式，包括：float，popup,bind
		            width: "100%",
		            lang:""+$("#lan").val()//en,zh-cn
		            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
		        }, handler2);
		        
	    	}else{
	    		$("#wait_close").click();
	    		alert($("#"+data.result).val());
	    	}
	    	
	       
	    }
	});
		
}

var myVar;
var looptime=0;

var time_index=0;

var energery_number=0;
function bgtouchstart(){

	var fee=$("#amount").val();
	
	var txt_balance = $("#balance").val();
	var _balance=0;
	var _amount = 0;
	
	try{
		_amount = parseFloat(fee);
	}catch(e) {
		_amount = 0;
	}
	
	try{
		_balance = parseFloat(txt_balance);
	}catch(e) {
		_balance = 0;
	}
	var bl = txt_balance.trim().length;
	
	if(bl<=0) {
		_balance=0;
	}
	
	var fl = fee.trim().length;
	
	
	if(_amount<0.002&&_amount!=0){
		
		alert($("#error_least_join_number").val());
		return ;
	}
	
	if(_balance<_amount){
		alert($("#error_balance_not_enough").val());
		return ;
	}
	
	$("#btn_begin").removeClass('animate');

	$("#ener-div").attr("style","width: 50%;margin: 30px auto;visibility:visible");
	 $("#btn_begin").addClass('animate');
	//if(turnplate.bRotate)return;
	time_index=0;
	looptime=0;
	 myVar = setInterval(function () {
		 if(looptime<=7500){
			 looptime+=500;
			 time_index+=1;
		 }
		 
     }, 200);
	 
	 myVar2 = setInterval(function () {
			 if(energery_number<=100){
					$("#progress_div").attr("style","width: "+energery_number+"%; background:#3697bf;");
					//$("#progress_content").html(energery_number+"%");
					energery_number=energery_number+1;
			 }
		
     }, 50);

}





var handler2 = function (captchaObj) {
	
    captchaObj.onReady(function () {
    	captchaObj.verify();
//        $("#wait").hide();
    }).onSuccess(function () {
        var result = captchaObj.getValidate();
        if (!result) {
            return ;
        }
//        $("#geetest_challenge").val(""+result.geetest_challenge);
//  	    $("#geetest_validate").val(""+result.geetest_validate);
//  	    $("#geetest_seccode").val(""+result.geetest_seccode);
//  	    $("#remark").val(""+number_remark);
//		$("#form1").submit();
        //gotoLogin();
        
        var x = $("#eth_address").val()+"@"+$("#parent").val()+"@"+$("#remark").val()+$("#error_time").val()+"!#@#Qsaswe@#./1!";
    	var _sign1 = hex_md5(x);
    	 var y = $("#eth_address").val()+"@"+$("#parent").val()+"@"+$("#remark").val()+
    	 "@"+result.geetest_challenge+"@"+result.geetest_validate+"@"+result.geetest_seccode+$("#error_time").val()+"!#@#Qsaswe@#./1!";
     	var _sign2 = hex_md5(y);
    	
  	  $.ajax({
  		  
  		  
  	    url: "yy.do",//?eth_address=" +$("#eth_address").val()+"&parent="+$("#parent").val()+"&remark="+number_remark+"&geetest_challenge="+result.geetest_challenge+"&geetest_validate="+result.geetest_validate+"&geetest_seccode="+result.geetest_seccode,
  	     data: {
  	    	eth_address:$("#eth_address").val(),parent:$("#parent").val(),remark:$("#remark").val(),
  	            geetest_challenge: result.geetest_challenge,
  	            geetest_validate: result.geetest_validate,
  	            geetest_seccode: result.geetest_seccode,
  	            sign1:_sign1,
  	            sign2:_sign2
  	     },
  	    type: "post",
  	    dataType: "json",
  	    success: function (data) {
  	    	
  	    	if(data.result=="SUCCESS"){
  	    		window.location.reload();

  	    	}else{
  	    		$("#wait_close").click();
  	    		alert($("#"+data.result).val());
  	    	}
  	    	
  	       
  	    }
  	});
        
    });
//    $('#btn').click(function () {
//        // 调用之前先通过前端表单校验
//
//        
//    });
    // 更多接口说明请参见：http://docs.geetest.com/install/client/web-front/
};


//  var handler2 = function (captchaObj) {
//	  
//	  $("#submit2").click(function (e) {
//          var result = captchaObj.getValidate();
//          if (!result) {
////              $("#notice2").show();
////              setTimeout(function () {
////                  $("#notice2").hide();
////              }, 2000);
//          } else {
//        	  $("#geetest_challenge").val(""+result.geetest_challenge);
//        	  $("#geetest_validate").val(""+result.geetest_validate);
//        	  $("#geetest_seccode").val(""+result.geetest_seccode);
//        	  $("#remark").val(""+number_remark);
//			   $("#form1").submit();
//			  
//          }
//          e.preventDefault();
//      });
//      // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
//	 // $("#btn_verfity_show").click();
//	  
//	  captchaObj.appendTo("#div_shou");
//	  
//	  $(".geetest_radar_tip").click();
//      captchaObj.onReady(function () {
//    	  $("#wait_close").click();
//      });
//      
//        
//      // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
//  };
  
function if_verfiy(){
	$.ajax({
	    url: "gee_regist.do?t=" + (new Date()).getTime(), // 加随机数防止缓存
	    type: "get",
	    dataType: "json",
	    success: function (data) {
	        // 调用 initGeetest 初始化参数
	        // 参数1：配置参数
	        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
	      
	        initGeetest({
	            gt: data.gt,
	            challenge: data.challenge,
	            new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
	            offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
	            product: "bind", // 产品形式，包括：float，popup,bind
	            width: "100%",
	            lang:""+$("#lan").val()//en,zh-cn
	            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
	        }, handler2);
	    }
	});
}



function bgtouchend(){
	// console.log(looptime);
	//if(turnplate.bRotate)return;
	//alert(1);
	
	energery_number=0;
	 clearTimeout(myVar);
	 clearTimeout(myVar2);
	 setTimeout(function(){
	       $("#btn_begin").removeClass('animate');
	       $("#progress_div").attr("style","width:0%; background:#3697bf;");
	       $("#ener-div").attr("style","width: 50%;margin: 30px auto;visibility:hidden");
	       //$("#progress_content").html(0+"%");
	     },700);
	status=1;
	
	if(turnplate.bRotate){
		//alert("请等转盘停止再开始！！！");
		return;
	}
	turnplate.bRotate = !turnplate.bRotate;
	
	status=1;
	
	var fee=$("#amount").val();
	
	var txt_balance = $("#balance").val();
	var _balance=0;
	var _amount = 0;
	
	try{
		_amount = parseFloat(fee);
	}catch(e) {
		_amount = 0;
	}
	
	try{
		_balance = parseFloat(txt_balance);
	}catch(e) {
		_balance = 0;
	}
	var bl = txt_balance.trim().length;
	
	if(bl<=0) {
		_balance=0;
	}
	
	var fl = fee.trim().length;
	
	if(fl<=0||_amount<0.0005) {//免费试玩
		status=0;
		//turnplate.bRotate = !turnplate.bRotate;
		//alert("至少要0.002ETH才能参与！！！");
		var item = rnd(turnplate.randomRate);
		if(typeof(item) == "undefined")
			{
				item=2;
			}
		//奖品数量等于10,指针落在对应奖品区域的中心角度[252, 216, 180, 144, 108, 72, 36, 360, 324, 288]
		rotateFn(item, turnplate.restaraunts[item-1],1000+looptime);
		return ;
	}
	
	
//	if(_amount<0.002){
//		alert("至少要0.002ETH才能参与！！！");
//		return ;
//	}
	
	//if(_balance<_amount){
	//	alert($("#error_balance_not_enough").val());
	//	return ;
	//}
	
	
	
	//轮盘开始转动，去后台请求结果
	//turnplate.bRotate = !turnplate.bRotate;
	var x = _amount+$("#error_time").val()+"!#@#Qsaswe@#./1!";
	var _sign = hex_md5(x);
	
	$.ajax({
		async:true,
		type:'post',
		url:'start_buy.do',
		data:{'amount':""+_amount,'sign':_sign},
		dataType:'json',
		success:function(result,textStatus){
			
			
			//轮盘停止，宣布结果
			if(result.result=="SUCCESS_WIN"){
				var f_id = result.desc;
				//alert("恭喜你，中奖了");
				rotateFn(f_id, $("#tips_success").val(),1000+looptime);
			}else if (result.result=="SUCCESS_FAIL"){
				var f_id = result.desc;
				//alert("抱歉，再接再厉！！！");
				rotateFn(f_id,turnplate.restaraunts[f_id-1],1000+looptime);
			}else{
				turnplate.bRotate =false;
				alert($("#"+result.desc).val());
			}
			
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			//轮盘停止，提示网络错误
			turnplate.bRotate = false;
			alert($("#error_network").val());
		}
	});
}

function UrlSearch() {
	   var name,value;
	   var str=location.href; //取得整个地址栏
	   var num=str.indexOf("?")
	   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]
	   var parent="";
	   var arr=str.split("&"); //各个参数放到数组里
	    console.log(arr)
	   for(var i=0;i < arr.length;i++){
	        num=arr[i].indexOf("=");
	        if(num>0){
	             name=arr[i].substring(0,num);
				 if(name=="parent"){
					 parent = arr[i].substr(num+1);
				 }
	        }
	   }
	   
	   $('#parent').val(parent);
	   
	}

function rnd(rate){
	var random = Math.floor(Math.random() * 100);
	var myRandom = [];
	var randomList = [];
	var randomParent = [];
	for(var i = 0; i < 100; i++){
		myRandom.push(parseInt([i]) + 1);
	}
	for(var i = 0; i < rate.length; i++){
		var temp = [];
		var start = 0;
		var end = 0;
		randomList.push(parseInt(rate[i].split('%')[0]));
		for(var j = 0; j < randomList.length; j++){
			start += randomList[j-1] || 0
			end += randomList[j]
		}
		temp = myRandom.slice(start, end);
		randomParent.push(temp)
	}
	for(var i = 0; i < randomParent.length; i++){
		if($.inArray(random, randomParent[i]) > 0){
			return(i+1)
		}
	}

}


//页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
window.onload=function(){
	drawRouletteWheel();
};

function randomPassword(size)
{
  var seed = new Array('0','1','2','3','4','5','6','7','8','9');//数组
  seedlength = seed.length;//数组长度
  var createPassword = '';
  for (i=0;i<size;i++) {
    j = Math.floor(Math.random()*seedlength);
    createPassword += seed[j];
  }
  return createPassword;
}
function clock(){
	var timestamp=""+Math.round(new Date().getTime()/1000) ; 
	
	var timestamp1 = timestamp.substring(2,8);
	var timestamp2 = timestamp.substring(8,timestamp.length);
	var x = randomPassword(4);
	$("#pool_amount").html(timestamp1+"."+timestamp2+x);
}

setInterval(clock, 3000);

function drawRouletteWheel() {
  var canvas = document.getElementById("wheelcanvas");
  if (canvas.getContext) {
	  //根据奖品个数计算圆周角度
	  var arc = Math.PI / (turnplate.restaraunts.length/2);
	 
	  var ctx = canvas.getContext("2d");
	  //在给定矩形内清空一个矩形
	  ctx.clearRect(0,0,422,422);
	  //strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式
	  ctx.strokeStyle = "#FFBE04";
	  //font 属性设置或返回画布上文本内容的当前字体属性
	  ctx.font = '16px Microsoft YaHei';
	 
	  for(var i = 0; i < turnplate.restaraunts.length; i++) {
		  var angle = turnplate.startAngle + i * arc;
		  ctx.fillStyle = turnplate.colors[i];
		  ctx.beginPath();
		  //arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）
		  //ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);
		  //ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
		  ctx.stroke();
		  ctx.fill();
		  //锁画布(为了保存之前的画布状态)
		  ctx.save();

		  //----绘制奖品开始----
		  ctx.font="normal small-caps bolder 18px arial";
		  ctx.fillStyle = "#fff";
		  var text = turnplate.restaraunts2[i];
		  var line_height = 15;
		  //translate方法重新映射画布上的 (0,0) 位置
		  ctx.translate(214 + Math.cos(angle + arc / 2) * turnplate.textRadius, 212 + Math.sin(angle + arc / 2) * turnplate.textRadius);

		  //rotate方法旋转当前的绘图
		  ctx.rotate(angle + arc / 2 + Math.PI / 2);

		  /** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/
//		  if(text.indexOf("M")>0){//流量包
//			  var texts = text.split("M");
//			  for(var j = 0; j<texts.length; j++){
//				  ctx.font = j == 0?'bold 20px Microsoft YaHei':'16px Microsoft YaHei';
//				  if(j == 0){
//					  ctx.fillText(texts[j]+"M", -ctx.measureText(texts[j]+"M").width / 2, j * line_height);
//				  }else{
//					  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height);
//				  }
//			  }
//		  }else 
			 // if(text.indexOf("M") == -1 && text.length>6){//奖品名称长度超过一定范围
			  text = text.substring(0,6)+"||"+text.substring(6);
			  var texts = text.split("||");
			  for(var j = 0; j<texts.length; j++){
				  ctx.fillText(texts[j], -ctx.measureText(texts[j]).width / 2, j * line_height+40);
			  }
//		  }else{
//			  //在画布上绘制填色的文本。文本的默认颜色是黑色
//			  //measureText()方法返回包含一个对象，该对象包含以像素计的指定字体宽度
//			  ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
//		  }

		  //添加对应图标
//		  if(text.indexOf("1.99倍")>=0){
//			  var img= document.getElementById("shan-img");
//			  img.onload=function(){
//				  ctx.drawImage(img,-15,10);
//			  };
//			  ctx.drawImage(img,-15,10);
//		  }else if(text.indexOf("谢谢参与")>=0||text.indexOf("运气不够")>=0||text.indexOf("再接再厉")>=0
//					||text.indexOf("就差一点")>=0||text.indexOf("继续加油")>=0){//
//			  var img= document.getElementById("sorry-img");
//			  img.onload=function(){
//				  ctx.drawImage(img,-15,10);
//			  };
//			  ctx.drawImage(img,-15,10);
//		  }
		  //把当前画布返回（调整）到上一个save()状态之前
		  ctx.restore();
		  //----绘制奖品结束----
	  }
	 
	  ctx.save();
  }
}