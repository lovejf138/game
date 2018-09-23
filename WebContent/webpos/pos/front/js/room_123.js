
var baseurl=$("#baseurl").val();
var onoff1 = true;//这是导航状态
var tanonoff = true;//弹幕的状态
var sendoff = false;//语音状态
var viewOnoff = true;//全景状态
$(".pothook").click(function(){
	showNav();//显示或隐藏导航
});


var intDiff = parseInt($("#nextsecond").val());
var wait = false;//等待开奖
if (intDiff < 0) {
	intDiff = intDiff * -1;
	wait = true;
	$("#name_wait1").html("待" + $("#nextname").val() + "期开奖：");
} else {
	$("#name_wait1").html("离" + $("#nextname").val() + "期结束：");
}
/* var intDiff = parseInt(120);//倒计时总秒数量 */
function timer(intDiff) {
	window.setInterval(function() {
		var second = 0;//时间默认值
		if (intDiff > 0) {
			second = intDiff;
		}
	
		if (second <= 9)
			second = '0' + second;
		$('#name_wait2').html(''+second );
		if (wait) {
			intDiff++;
		} else {
			intDiff--;
		}

	}, 1000);
}
$(function() {
	timer(intDiff);
});


function showNav(){//导航栏
	$(".navContent").slideToggle(500);
	 if (onoff1) {
        $(".pothook").animate({ top: 0 }, 500)
        $(".pothook")[0].style.background = "url("+baseurl+"/webpos/pos/front/img/room/navdown2.gif) no-repeat";
        $(".pothook")[0].style.backgroundSize = "100% 80%";

    } else {
        $(".pothook").animate({ top: "60px" }, 500)
        $(".pothook")[0].style.background = "url("+baseurl+"/webpos/pos/front/img/room/navup2.gif) no-repeat";
        $(".pothook")[0].style.backgroundSize = "100% 80%";
    }
	onoff1 = !onoff1;
}

var arrdata = [
	{"headimg":baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg","message":"66666","vote":3,"id":11,"state":1},
	{"headimg":baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg","message":"不错666","vote":21,"id":12,"state":0},
	{"headimg":baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg","message":"很好666很好很好很好","vote":2,"id":13,"state":1}
]

var locaScreen = [120,160,200,240,280];//弹幕位置
//初始化
var init = {
	"navdown":baseurl+"/webpos/pos/front/img/room/navdown2.gif",
	"navup":baseurl+"/webpos/pos/front/img/room/navup2.gif",
	"timer":500,
}

var num = -1;
var timer;
var timer1;
//initScreen();//初始屏幕
//function initScreen(){//初始化屏幕
//	timer = setInterval(function(){
//	    num ++;
//	    if(num>arrdata.length-1){
//	    	num = 0
//	    }
////	  var div = document.createElement("div");
////	  div.className = "tanmu";
////	  div.innerHTML = '<span class="headImg"><img src="'+arrdata[num].headimg+'" /></span>'+arrdata[num].message;
////	  div.innerHTML += '<div class="praiseBox"><span class="t-praise"></span><span class="t-numer">'+arrdata[num].vote+'</span></div>';	
////	  div.innerHTML += '</span>';
//	  $("#wrap").append(createScreenPraise(arrdata[num].headimg,arrdata[num].message,arrdata[num].vote,arrdata[num].state,arrdata[num].id));
//		
//	},6000);				
//}	
//生成弹幕，给弹幕添加praise
function createScreenPraise(heading,message,vote,state,id){
	var div = document.createElement("div");
	var divContent = document.createElement("div");
	var divPraise = document.createElement("div");
	var color = "";
	var praise = '';
	if(state){//点过的状态1有颜色
		color = " t-color";
		praise = " t-praise1"
	}	
	divPraise.onclick = function(){
		vote++;
		alert(id);
	}	
	//消息和头像部分
	divContent.className = "tanmuContent";
	divContent.innerHTML = '<span class="headImg"><img src="'+heading+'" /></span>'+message+'&nbsp&nbsp';
	
	//放入到最后tanmu元素里
	div.className = "tanmu";
	div.appendChild(divContent);
	createScreen(div)//执行生成弹幕的动画
	return div;
}


$(function(){//点击发送弹幕
	$(".s_sub").click(function(){
		
		clearInterval(timer);
		clearTimeout(timer1);
	    var text =$(".s_txt").val();
	    text = text.trim();
	    if(!text.length){
	   		//alert("请输入想发表的话");//这里是如果发送内容为空；可以加以提示
	   		$(".send").hide();
	   		sendoff = false;
	   		return;
	    }
	    
	    var x = $("#roomid").val() + "@" + $("#userid").val() + "!#@#Qsaswe@#./1!"+"@"+text;
	    var _sign = hex_md5(x);
	    text = $("#roomid").val()+"&&__"+$("#userid").val()+"&&__"+text+"&&__"+_sign;
	   
	  //  var img =baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg";
	   
	    //$("#wrap").append(createScreenPraise(img,text,0,0));

	    $(".s_txt")[0].value = "";
	    $(".send").hide();
	    sendoff = false;
	    send(text);
	    
//	    timer1 = setTimeout(function(){
//	   		initScreen();//初始化屏幕
//	    },2000)
	})
});

//给创建的弹幕增加事件
//function clickPraise(elem){
//	$(elem).click(function(){
//		alert(123)
//	})
//}

//生成弹幕
var olN = 0;
function createScreen(elem){
	var _top=0;
	var _left=$(window).width();
	var _height=$(window).height();
	
	var lN= Math.floor(5*Math.random());
	
	if(olN ==lN){
		lN++;
		if(lN>locaScreen.length-1){
			lN = 0;
		}	
	}
	olN = lN;
	_top=locaScreen[lN];
//	console.log(locaScreen.length,_top)
	//初始弹幕的位置
	$(elem).css({left:_left,top:_top,color:"#333"});
	//执行动画时间
	var time=20000;
	//执行动画
	$(elem).animate({left:"-"+_left+"px"},time,function(){
		var docum = document.getElementById("wrap");
		docum.removeChild(this);
	});
	
}
//随机获取颜色值 ；这一个没有加可以在80行“初始弹幕的位置”color：getReandomColor();这样就会生效
function getReandomColor(){
	return '#'+(function(h){
	return new Array(7-h.length).join("0")+h
	})((Math.random()*0x1000000<<0).toString(16))
}

//导航栏部分的点击事件执行函数
var onoffTxt =  false;//文字滚动按钮加状态

var initScOnff = true;
//var times = aud.duration;//这里获取到的是秒，定时器转换毫秒；但是iso获取不audio的时间长度，可以用后台传过来
var times = 106*1000;//这里是


$(".c-tanmu")[0].onclick = function(){//弹幕开关
	if(tanonoff){
		clearInterval(timer);
		$(".navIco-tanmu")[0].style.background = "url("+baseurl+"/webpos/pos/front/img/room/icotanmuoff.png) no-repeat";
		$(".navIco-tanmu")[0].style.backgroundSize = "100% 100%";
		for(var i = 0;i<$(".tanmu").length;i++){
			$(".tanmu")[i].style.display= "none";
		}		
	}else{
		$(".navIco-tanmu")[0].style.background = "url("+baseurl+"/webpos/pos/front/img/room/icotanmu.png) no-repeat";
		$(".navIco-tanmu")[0].style.backgroundSize = "100% 100%";
		for(var i = 0;i<$(".tanmu").length;i++){
			$(".tanmu")[i].style.display= "block";
		}
		//initScreen();
	}
	tanonoff = !tanonoff;
	
}
$(".c-fatan")[0].onclick = function(){//发弹幕
	
	if(sendoff){
		$(".send").hide();
	}else{
		$(".send").show();
	}
	sendoff = !sendoff;
	
}

var is_op = false;
var waitdialog=null;
$("#btn_ok").click(function(event){
	if(is_op)return;
	var _amount=0;
	try{
		_amount = parseFloat($("#input_amount").val());
	}catch(e) {
		_amount = 0;
	}
	if(_amount<0.005){
		alert("最少参与数量0.005");
		return ;
	}
	
	var x = $("#input_amount").val()+$("#error_time").val()+"!#@#Qsaswe@#./1!";
	var _sign = hex_md5(x);
	
	is_op = true;
	waitdialog = new TipBox({type:'load',str:'正在提交',hasBtn:false});
	$.ajax({
		async:true,
		type:'post',
		url:'join.do',
		data:{amount:""+$("#input_amount").val(),sign:""+_sign,roomid:""+$("#roomid").val()},
		dataType:'json',
		success:function(result,textStatus){
			
			waitdialog.destroy();
			is_op = false;
			if(result.result=="SUCCESS"){
				window.location.reload();
				alert("参与成功，正在刷新页面...");
				 
			}else{
				alert(result.desc);
				
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			is_op = false;
			waitdialog.destroy();
			alert("网络错误");
		}
	});
});

var websocket=null;
var _top=80;
var index=0;

var host=window.location.host;
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
	websocket=new WebSocket("ws://eth-game.club/websocket");/*("ws://"+host+"/Danmu/websocket");*/
}
else{
	alert("当前浏览器不支持发送弹幕!");
}


//连接发生错误的回调方法
websocket.onerror = function(){
   // setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function(){
   // setMessageInnerHTML("open");
}

//接收到消息的回调方法
websocket.onmessage = function(event){
	if(tanonoff){
		setMessageInnerHTML(event.data);
	}
}

//连接关闭的回调方法
websocket.onclose = function(){
  //  setMessageInnerHTML("close");
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}


//将消息显示在网页上
function setMessageInnerHTML(msg){
	
  var img =baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg";
	   
    $("#wrap").append(createScreenPraise(img,msg,0,0));

}

/*websocket.onmessage = function(msg) {
    alert(msg.data);
    setMessageInnerHTML(msg) ;
};*/


//发送消息
function send(text){
    //var message = document.getElementById('text').value;
   // var message = $(".s_text").val();
    /*alert($(".s_text").val(""));
    alert(message);*/
    websocket.send(text);
}
