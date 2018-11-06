var baseurl=$("#baseurl").val();
var onoff1 = true;//这是导航状态
var tanonoff = true;//弹幕的状态
var sendoff = false;//语音状态
var viewOnoff = true;//全景状态
$(".pothook").click(function(){
	showNav();//显示或隐藏导航
});


var intDiff = parseInt($("#nextsecond").val());
var sourceDiff = intDiff;
var serverTime = new Date().getTime();

var wait = false;//等待开奖
if (intDiff < 0) {
	intDiff = intDiff * -1;
	wait = true;
	$("#name_wait1").html("待" + $("#nextname").val() + "期开奖：");
} else {
	$("#name_wait1").html("离" + $("#nextname").val() + "期结束：");
}
var hasGetKaijiang = false;

/* var intDiff = parseInt(120);//倒计时总秒数量 */
function timer(intDiff) {
	window.setInterval(function() {
		var nowTime = new Date().getTime();
		var duoyu = parseInt((nowTime-serverTime)/1000);
		
		if(wait){
			$("#name_wait1").html("待" + $("#nextname").val() + "期开奖：");
			if(sourceDiff<0){
				intDiff =(sourceDiff*-1)+duoyu;
			}else{
				intDiff = (sourceDiff-duoyu)*-1;
			}
			if(intDiff>=120){//最多等待180秒
				//if(hasGetKaijiang==false){
					getKaijiang();
				//}
			}
		}else{
			if(sourceDiff<=0){
				intDiff = (sourceDiff*-1)-duoyu;
			}else{
				intDiff = sourceDiff-duoyu;
			}
			
			if(intDiff<0){
				intDiff = intDiff*-1;
				
				wait=true;
			}
		}
		
		var second = 0;//时间默认值
		if (intDiff > 0) {
			second = intDiff;
		}
	
		if (second <= 9)
			second = '0' + second;
		$('#name_wait2').html(''+second );
//		if (wait) {
//			intDiff++;
//		} else {
//			intDiff--;
//		}

	}, 1000);
}
$(function() {
	timer(intDiff);
});


function showNav(){//导航栏
	$(".navContent1").slideToggle(500);
	$(".navContent").slideToggle(500);
	
	 if (onoff1) {
		 
        $(".pothook").animate({ top: 0 }, 500)
        $(".pothook")[0].style.background = "url("+baseurl+"/webpos/pos/front/img/room/navdown2.gif) no-repeat";
        $(".pothook")[0].style.backgroundSize = "100% 80%";
       // $(".in-body").attr("style","margin-top:40px");
        $(".in-body").animate({ "margin-top": "40px" }, 500);

    } else {
        $(".pothook").animate({ top: "200px" }, 500)
        $(".pothook")[0].style.background = "url("+baseurl+"/webpos/pos/front/img/room/navup2.gif) no-repeat";
        $(".pothook")[0].style.backgroundSize = "100% 80%";
        $(".in-body").animate({ "margin-top": "240px" }, 500);
       // $(".in-body").attr("style","margin-top:240px");
    }
	onoff1 = !onoff1;
}

var num = -1;
var timer;
var timer1;

//导航栏部分的点击事件执行函数
var onoffTxt =  false;//文字滚动按钮加状态

var initScOnff = true;
//var times = aud.duration;//这里获取到的是秒，定时器转换毫秒；但是iso获取不audio的时间长度，可以用后台传过来
var times = 106*1000;//这里是

//购买方案	
$(".footer a").click(function(){
	var nindex=$(this).index();
	$(".zzc").fadeIn();
	$(".sxlist").fadeIn();
});
var waitdialog=null;
$(".sxlist a.ok").click(function(){
	var str="";
	$("li.active").each(function(index,item){
	   str=$(this).attr("value");
    })
	

	var x = str+$("#error_time").val()+"!#@#Qsaswe@#./1!";
	var _sign = hex_md5(x);
	
	
	waitdialog = new TipBox({type:'load',str:'正在提交',hasBtn:false});
	$.ajax({
		async:true,
		type:'post',
		url:'goodsbuy.do',
		data:{fangan:""+str,sign:""+_sign,goodsid:""+$("#goodsid").val(),nextname:""+$("#nextname").val()},
		dataType:'json',
		success:function(result,textStatus){
			
			waitdialog.destroy();
			
			if(result.result=="SUCCESS"){
				new TipBox({type:'success',str:'方案购买成功，可前往订单中心查看购买和开奖结果',hasBtn:true});
				$("#my_balance2").html("¥"+result.desc);
				$("#my_balance").html("¥"+result.desc);
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
$(".maintitle i").click(function(){
	$(".zzc").fadeOut();
	$(".sxlist").fadeOut();
});
$(".sxlist li").click(function(){
	$(this).addClass("active").siblings().removeClass();
});

//获取开奖信息
function getKaijiang(){
	
    document.getElementById("gold_audio").play();
     
	$('#sampledata3').bringins({
		"position":"center",
		"color":"black",
		"closeButton":"white",
		"width":"100%",
		"z-index":"1000"
	});
			
}