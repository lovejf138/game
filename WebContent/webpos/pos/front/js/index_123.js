
$('#btn_login').click(function () {
	    $('.login').addClass('test');
	    setTimeout(function () {
	        $('.login').addClass('testtwo');
	    }, 300);
	    setTimeout(function () {
	        $('.authent').show().animate({ right: -320 }, {
	            easing: 'easeOutQuint',
	            duration: 600,
	            queue: false
	        });
	        $('.authent').animate({ opacity: 1 }, {
	            duration: 200,
	            queue: false
	        }).addClass('visible');
	    }, 500);
	    setTimeout(function () {
	        $('.authent').show().animate({ right: 90 }, {
	            easing: 'easeOutQuint',
	            duration: 600,
	            queue: false
	        });
	        $('.authent').animate({ opacity: 0 }, {
	            duration: 200,
	            queue: false
	        }).addClass('visible');
	        $('.login').removeClass('testtwo');
	    }, 2500);
	    setTimeout(function () {
	        $('.login').removeClass('test');
	        $('.login div').fadeOut(123);
	    }, 2800);
	    setTimeout(function () {
	        $('.success').fadeIn();
	    }, 3200);
	});
	$('input[type="text"],input[type="password"]').focus(function () {
	    $(this).prev().animate({ 'opacity': '1' }, 200);
	});
	$('input[type="text"],input[type="password"]').blur(function () {
	    $(this).prev().animate({ 'opacity': '.5' }, 200);
	});
	$('input[type="text"],input[type="password"]').keyup(function () {
	    if (!$(this).val() == '') {
	        $(this).next().animate({
	            'opacity': '1',
	            'right': '30'
	        }, 200);
	    } else {
	        $(this).next().animate({
	            'opacity': '0',
	            'right': '20'
	        }, 200);
	    }
	});
	var open = 0;
	$('.tab').click(function () {
	    $(this).fadeOut(200, function () {
	        $(this).parent().animate({ 'left': '0' });
	    });
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

};


  
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
	            lang:"zh-cn"//en,zh-cn
	            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
	        }, handler2);
	    }
	});
}