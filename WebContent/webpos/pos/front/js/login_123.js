var userexist = "true";
//function searchuser(){
//	$("#error_msg").html("");
//	var eth_address = $("#address").val();
//	var el = eth_address.trim().length;
//
//	if (eth_address.toString().indexOf("VIP") >= 0
//			|| eth_address.toString().indexOf("vip") >= 0) {
//	}else if (el <= 30) {
//		$("#error_msg").html("请输入正确的地址");
//		return;
//	}
//	
//	$.ajax({
//
//		url : "searchuser.do",// ?eth_address="
//						// +$("#eth_address").val()+"&parent="+$("#parent").val()+"&remark="+$("#remark").val(),//+"&geetest_challenge="+$("#geetest_challenge").val()+"&geetest_validate="+$("#geetest_validate").val()+"&geetest_seccode="+$("#geetest_seccode").val()
//		type : "post",
//		data : {
//			eth_address : $("#address").val()
//		},
//		dataType : "json",
//		success : function(data) {
//
//			if (data == "false") {
//				userexist = "false";
//				//显示确认密码
//				$("#login_fields__repassword").attr("style","display: block");
//				
//			} else if (data == "true") {
//				userexist = "true";
//				$("#login_fields__repassword").attr("style","display: none");
//			} else {
//				$("#error_msg").html(""+data);
//			}
//
//		}
//	});
//	
//	
//}

function showWait() {
	$('.login').addClass('test');
	setTimeout(function() {
		$('.login').addClass('testtwo');
	}, 300);
	setTimeout(function() {
		$('.authent').show().animate({
			right : -0
		}, {
			easing : 'easeOutQuint',
			duration : 600,
			queue : false
		});
		$('.authent').animate({
			opacity : 1
		}, {
			duration : 200,
			queue : false
		}).addClass('visible');
	}, 500);
}

function closeWait() {
	setTimeout(function() {
		$('.authent').show().animate({
			right : 90
		}, {
			easing : 'easeOutQuint',
			duration : 600,
			queue : false
		});
//		$('.authent').animate({
//			opacity : 0
//		}, {
//			duration : 200,
//			queue : false
//		}).removeClass('visible');
		
		$('.authent').animate({
			opacity : 0
		}, {
			duration : 200,
			queue : false
		}).attr('style','');
		
		$('.login').removeClass('testtwo');
	}, 2500);
	setTimeout(function() {
		$('.login').removeClass('test');
		//$('.login div').fadeOut(123);
	}, 2800);
//	setTimeout(function() {
//		$('.success').fadeIn();
//	}, 3200);
}

$('input[type="text"],input[type="password"]').focus(function() {
	$(this).prev().animate({
		'opacity' : '1'
	}, 200);
});
$('input[type="text"],input[type="password"]').blur(function() {
	$(this).prev().animate({
		'opacity' : '.5'
	}, 200);
});
$('input[type="text"],input[type="password"]').keyup(function() {
	if (!$(this).val() == '') {
		$(this).next().animate({
			'opacity' : '1',
			'right' : '30'
		}, 200);
	} else {
		$(this).next().animate({
			'opacity' : '0',
			'right' : '20'
		}, 200);
	}
});
var open = 0;
$('.tab').click(function() {
	$(this).fadeOut(200, function() {
		$(this).parent().animate({
			'left' : '0'
		});
	});
});

var number_remark = "";
$("#btn_login").click(
		function(event) {

			$("#error_msg").html("");
			 var phone = $("#address").val();
			    if(!(/^1[34578]\d{9}$/.test(phone))){ 
			    	$("#error_msg").html("手机号码有误，请重填");  
			        return false; 
			    } 
			
			
			gotoLogin();
			
		});

function gotoLogin() {

	showWait();
	
	// $("#form1").submit();
	var x = $("#address").val() + "@" + $("#error_time").val() + "!#@#Qsaswe@#./1!";
	var _sign = hex_md5(x);
	
	 var shaObj = new amallEncryption($("#pass").val());
 	 var _pass = shaObj.getHash();
	
	$.ajax({

		url : "dologin.do",// ?eth_address="
						// +$("#eth_address").val()+"&parent="+$("#parent").val()+"&remark="+$("#remark").val(),//+"&geetest_challenge="+$("#geetest_challenge").val()+"&geetest_validate="+$("#geetest_validate").val()+"&geetest_seccode="+$("#geetest_seccode").val()
		type : "post",
		data : {
			eth_address : $("#address").val(),
			pass:_pass,
			parent : $("#parent").val(),
			remark : $("#remark").val(),
			sign1 : _sign
		},
		dataType : "json",
		success : function(data) {

			if (data.result == "SUCCESS") {
				//window.location.reload();//跳转到index.do
				setTimeout(function() {
					window.location.href='goods.do';
				},1000);
				
			} 
//			else if (data.result == "need_verify") {
//				
//				initGeetest({
//					gt : data.desc.gt,
//					challenge : data.desc.challenge,
//					new_captcha : data.desc.new_captcha, // 用于宕机时表示是新验证码的宕机
//					offline : !data.desc.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
//					product : "bind", // 产品形式，包括：float，popup,bind
//					width : "100%",
//					lang : "zh-cn"// en,zh-cn
//				// 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
//				}, handler2);
//
//			} 
			else {
				closeWait();
				setTimeout(function() {
					$("#error_msg").html(""+data.result);
				},1000);
			}

		}
	});

}

//var handler2 = function(captchaObj) {
//
//	captchaObj.onReady(function() {
//		captchaObj.verify();
//		// $("#wait").hide();
//	}).onSuccess(
//			function() {
//				var result = captchaObj.getValidate();
//				if (!result) {
//					return;
//				}
//				// $("#geetest_challenge").val(""+result.geetest_challenge);
//				// $("#geetest_validate").val(""+result.geetest_validate);
//				// $("#geetest_seccode").val(""+result.geetest_seccode);
//				// $("#remark").val(""+number_remark);
//				// $("#form1").submit();
//				// gotoLogin();
//				 var shaObj = new amallEncryption($("#pass").val());
//			 	 var _pass = shaObj.getHash();
//			 	 
//				var x = $("#address").val() + "@"+ $("#error_time").val()
//						+ "!#@#Qsaswe@#./1!";
//				var _sign1 = hex_md5(x);
//				var y = $("#address").val() + "@"
//						+ result.geetest_challenge  + $("#error_time").val()
//						+ "!#@#Qsaswe@#./1!";
//				var _sign2 = hex_md5(y);
//
//				$.ajax({
//
//					url : "dologin.do",// ?eth_address="
//									// +$("#eth_address").val()+"&parent="+$("#parent").val()+"&remark="+number_remark+"&geetest_challenge="+result.geetest_challenge+"&geetest_validate="+result.geetest_validate+"&geetest_seccode="+result.geetest_seccode,
//					data : {
//						eth_address : $("#address").val(),
//						parent : $("#parent").val(),
//						pass:_pass,
//						remark : $("#remark").val(),
//						geetest_challenge : result.geetest_challenge,
//						geetest_validate : result.geetest_validate,
//						geetest_seccode : result.geetest_seccode,
//						sign1 : _sign1,
//						sign2 : _sign2
//					},
//					type : "post",
//					dataType : "json",
//					success : function(data) {
//
//						if (data.result == "SUCCESS") {
//							//window.location.reload();//跳转到index.do
//							setTimeout(function() {
//								window.location.href='index.do';
//							},1000);
//						} else {
//							closeWait();
//							setTimeout(function() {
//								$("#error_msg").html(""+data.result);
//							},1000);
//						}
//
//					}
//				});
//
//			});
//
//};