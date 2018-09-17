var tixian=false;

	$("#btn_withdraw").click(function(event){
		//$("#withdraw_tip").html("");
		if(tixian) return;
		
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
		
		if(_amount<0.01){
			$("#withdraw_tip").html("至少0.01个ETH");
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
			$("#withdraw_tip").html("余额不足!");
			return ;
		}
	
		var x = _amount+$("#error_time").val()+"!#@#Qsaswe@#./1!";
		var _sign = hex_md5(x);
		tixian = true;
		$("#withdraw_tip").html("正在提交申请，请勿关闭页面");
		$.ajax({
			async:true,
			type:'post',
			url:'withdraw.do',
			data:{amount:""+_amount,sign:""+_sign},
			dataType:'json',
			success:function(result,textStatus){
				
				
				if(result.result=="SUCCESS"){
					var f_id = result.desc;
			
					$("#withdraw_tip").html("提现成功，正在刷新页面");
					window.location.reload();
				}else if (result.result=="FAIL"){
					var f_id = result.desc;
					tixian = false;
					$("#withdraw_tip").html("提现失败"+f_id);
					
				}
				
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				tixian = false;
				$("#withdraw_tip").html("网络错误");
			}
		});
	});
