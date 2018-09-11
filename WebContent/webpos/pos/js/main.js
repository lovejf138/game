// $(function(){
// 	// 左侧导航
// 	var side_index =0;
// 	$('#left-side dl dt').click(function(){
// 	    side_index = $(this).index('dt');
// 		// console.log(side_index);
// 		$(this).next('dd').toggle().siblings('dd').hide();
// 		$.cookie('side_index',side_index);
// 		if($('#left-side dl dt').hasClass('active')){
// 			console.log($('#left-side dl dt').hasClass('active'));
// 			$(this).next('dd').show().siblings('dd').hide();
// 		}
// 		$('#left-side dl dt').eq(0).addClass('active');
// 		$('#left-side dl dt').eq($.cookie('side_index')).addClass('active').siblings('dt').removeClass('active')

// 	})
// 	$('#left-side dl dt').eq($.cookie('side_index')).next('dd').show();
// 	$('#left-side dl dt').eq($.cookie('side_index')).addClass('active').siblings('dt').removeClass('active');
// })
$(function(){
	
	$('#left-side dl dt').click(function(){
		$(this).addClass('active').siblings('dt').removeClass('active');
		$(this).next('dd').toggle();
		
	});
	$("dl dt").toggle(
			function(){
				$(this).find('img').attr('src','image/right.png');
			},	
			function(){
				$(this).find('img').attr('src','image/bottom.png');
			}
	);

	// 门店管理
	// $('.store-m .edit').click(function(){
	// 	var $input = $(this).parent('td').siblings('td').children('input');
	// 	if($input.attr('disabled')=='disabled'){
	// 		$input.removeAttr('disabled').css('border','1px solid #dadada');
	// 	}else{
	// 		$input.attr('disabled','disabled').css('border',0);
	// 	}
	// })
	// 添加账号弹窗
	$('.store-m .add-account').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".add-store").fadeIn(500);
	});
	$('.store-m .add-store .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".store-m .add-store").fadeOut(500);
	})
	// 批量添加门店
	$('.store-m .add-account-batch').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".add-stores").fadeIn(500);
	})	
	
	$('.store-m .add-stores .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".store-m .add-stores").fadeOut(500);
	})
	$('.store-m .add-stores .save').click(function(){
		$(".store-m .add-stores .save").unbind("click");
		$(".store-m .add-stores .save").unbind("dblclick");
		$(".store-m .add-stores .save").css("background-color","#ccc");
	})		
	//批量分配门店
	$("#piliangfenpeimendiantoagent").click(function(){
		$(".pop-mask").fadeIn(500);
		$(".fenpeistore").fadeIn(500);
	})
	$('.store-m .fenpeistore .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".store-m .fenpeistore").fadeOut(500);
	})
	
	// 批量导出收款二维码
	$('.store-m .download-qrcode-batch').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".download-qrcodes").fadeIn(500);
	})
	$('.store-m .download-qrcodes .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".store-m .download-qrcodes").fadeOut(500);
	})
	// 二维码弹窗
	$('.store-m .add-store .eweima').click(function(){
		$('.store-m .eweima-pop').fadeIn(500);
	})
	$('.store-m .eweima-pop .cancel').click(function(){
	    $('.store-m .eweima-pop').fadeOut(500);
	})
	// 编辑弹窗
	$('.store-m .edit').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".store-m .edit-store").fadeIn(500);
	});
	$('.store-m .edit-store .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".store-m .edit-store").fadeOut(500);
	})
	$('.store-m .edit-store .eweima').click(function(){
		$('.store-m .edit-eweima-pop').fadeIn(500);
	})
	$('.store-m .edit-eweima-pop .cancel').click(function(){
	    $('.store-m .edit-eweima-pop').fadeOut(500);
	})

	// bs-group
	$('.bs-group .add-account').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".bs-group .add-info").fadeIn(500);
	});
	$('.bs-group .add-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".bs-group .add-info").fadeOut(500);
	})
	$('.bs-group .operate .edit').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".bs-group .edit-info").fadeIn(500);
	});
	$('.bs-group .edit-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".bs-group .edit-info").fadeOut(500);
	})

	// pay
	$('.pay .jiaojie').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".jiesuan").fadeIn(500);
	});
	$('.pay .jiesuan .cancel, .pay .jiesuan .print').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".jiesuan").fadeOut(500);
	})

	// new-m
	$('.new-m .password').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".set-pwd").fadeIn(500);
	});
	$('.new-m .set-pwd .cancel, .new-m .set-pwd .save').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".set-pwd").fadeOut(500);
	})

	// cash-account
	$('.cash-account .add-account').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".cash-account .add-info").fadeIn(500);
	});
	$('.cash-account .add-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".cash-account .add-info").fadeOut(500);
	})
	$('.cash-account .edit').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".cash-account .edit-info").fadeIn(500);
	});
	$('.cash-account .edit-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".cash-account .edit-info").fadeOut(500);
	})

	// bs-account
	$('.bs-account .add-account').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".bs-account .add-info").fadeIn(500);
	});
	$('.bs-account .add-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".bs-account .add-info").fadeOut(500);
	})
	$('.bs-account .operate .edit').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".bs-account .edit-info").fadeIn(500);
	});
	$('.bs-account .edit-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".bs-account .edit-info").fadeOut(500);
	})

	// authority
	$(".authority .authority-c dl dt input[type='checkbox']").click(function(){
		var check = $(this).attr('checked');
		$(this).closest('dt').siblings('dd').find("input[type=checkbox]").attr('checked',check == 'checked');
	})
	$(".authority .authority-c dl dd input[type='checkbox']").click(function(){
		var checkl = $(this).closest('dd').siblings('dd').find("input[type=checkbox]:checked").length;
		var ddl = $(this).closest('dd').siblings('dd').length;
		var check = $(this).attr('checked');
		if(checkl ==ddl){
			$(this).closest('dd').siblings('dt').find("input[type=checkbox]").attr('checked',check == 'checked');
		}
		// console.log(checkl);
	})

	//merchant-m
	$('.merchant-m .operate .edit').click(function(){
		$(".pop-mask").fadeIn(500);
		$(".merchant-m .edit-info").fadeIn(500);
	});
	$('.merchant-m .edit-info .cancel').click(function(){
		$(".pop-mask").fadeOut(500);
	    $(".merchant-m .edit-info").fadeOut(500);
	})
})