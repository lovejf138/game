 var waitdialog=null;
	
 $("#go_btn").click(function(){
	 var price = $(".cz_list .on").text();
	 var type = $(".zf_list .on").attr("data-name");
	 
	 var x = price+type+"@"+$("#error_time").val()+"!#@#Qsaswe@#./1!";
		var _sign = hex_md5(x);
		
	 waitdialog = new TipBox({type:'load',str:'正在提交',hasBtn:false});
		$.ajax({
			async:true,
			type:'post',
			url:'payrequest.do',
			data:{amount:""+price,type:""+type,sign:""+_sign},
			dataType:'json',
			success:function(result,textStatus){
				
				
				waitdialog.destroy();
				if(result.result=="SUCCESS"){
					//new TipBox({type:'success',str:'请求成功！',hasBtn:true});
					//alert(result.desc);
					new TipBox({type:'success',str:'请前往付款，付款成功后5分钟内会自动到账',hasBtn:true});
					window.location.href = result.desc;
				}else{
					
					new TipBox({type:'error',str:''+result.desc,hasBtn:true});
					
				}
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				
				waitdialog.destroy();
				new TipBox({type:'error',str:'网络错误',hasBtn:true});
				
			}
		});
 });
 
 
 	$(function(){
      $(document).on("click",".btns a",function(){
 	     $(".zz").remove();
 	  })
      $(".cz_list li").click(function(){
      	 $(this).addClass("on").siblings().removeClass();
      }).not(".cz_list li:last").click(function(){$(".cz_list li:last input").val("其他金额").css("color","#666")})
      $(".cz_list li input").focus(function(e){
      	 $(this).css("color","white").val("");
      	 e.stopPropagation()
      }).blur(function(){
      	 var reg=/^\d+$/;
      	 if(reg.test($(this).val())==false){
      	 	 $(this).val("其他金额");
      	 	 $(window).on("click",".btns a",function(){$(".zz").remove()})
      	 }else{
      	 	 $(this).hide().parent().html($(this).val())
      	 }
      })
      var that=null;
      $(".zf_list li").click(function(){
      	 $(this).addClass("on").siblings().removeClass(); 
      })
      
 	})