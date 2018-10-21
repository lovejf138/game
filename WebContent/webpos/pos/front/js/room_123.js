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
	$("#name_wait1").html("待" + $("#nextname").val() + "季收获：");
} else {
	$("#name_wait1").html("离" + $("#nextname").val() + "季结束：");
}
var hasGetKaijiang = false;
/* var intDiff = parseInt(120);//倒计时总秒数量 */
function timer(intDiff) {
	window.setInterval(function() {
		var nowTime = new Date().getTime();
		var duoyu = parseInt((nowTime-serverTime)/1000);
		
		if(wait){
			$("#name_wait1").html("待" + $("#nextname").val() + "季收获：");
			if(sourceDiff<0){
				intDiff =(sourceDiff*-1)+duoyu;
			}else{
				intDiff = (sourceDiff-duoyu)*-1;
			}
			if(intDiff>=180){//最多等待180秒
				if(hasGetKaijiang==false){
					getKaijiang();
				}
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

var arrdata = [
	{"headimg":baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg","message":"66666","vote":3,"id":11,"state":1},
	{"headimg":baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg","message":"不错666","vote":21,"id":12,"state":0},
	{"headimg":baseurl+"/webpos/pos/front/img/room/tanmuhead.jpg","message":"很好666很好很好很好","vote":2,"id":13,"state":1}
]

var locaScreen = [50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,210,220,230,240,20,260,270,280,290,300];//弹幕位置
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
	  
	    setMessageInnerHTML(text);
	    
	    var x = $("#roomid").val() + "@" + $("#userid").val() + "!#@#Qsaswe@#./1!"+"@"+text;
	    var _sign = hex_md5(x);
	    text = $("#roomid").val()+"&&__"+$("#userid").val()+"&&__1&&__"+text+"&&__"+_sign;
	   
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

setSeclectBall();
$('._myamount1_ball').attr("style","background-color: #ff962c;");	  

//设置已下注过的号码
function setSeclectBall(){
	$(".myamount").each(function(){
		var my=$(this).html();
		var aid=$(this).attr('id');
		if(my!='0'){
			
			$('.'+aid+"_ball").attr("style","background-color:rgb(167, 165, 165);");	  
		}else{
			$('.'+aid+"_ball").attr("style","background-color: rgb(29, 12, 12);");
		}
		
    });
}

var select_number=1;
$(".xuan_ball").click(function(){
//	$(".xuan_ball").each(function(){
//		$(this).attr("style","background-color: rgb(29, 12, 12);");	  
//    });
	
	setSeclectBall();
	
	 var selfball = $(this);
	 select_number = selfball.html();
	 $("#select_number").html("已选号码："+selfball.html());
	 $(this).attr("style","background-color: #ff962c;");
	 $("#input_amount").val("");
	
});

var is_op = false;
var waitdialog=null;
var is_first_join=1;
$("#btn_ok").click(function(event){
	if(is_op)return;
	var _amount=0;
	try{
		_amount = parseInt($("#input_amount").val());
	}catch(e) {
		_amount = 0;
	}
	if(_amount<10){
		new TipBox({type:'error',str:'最少播10个种子',hasBtn:true});
		return ;
	}
	
	is_first_join=1;
	
	var x = $("#input_amount").val()+$("#error_time").val()+"!#@#Qsaswe@#./1!";
	var _sign = hex_md5(x);
	
	is_op = true;
	waitdialog = new TipBox({type:'load',str:'正在提交',hasBtn:false});
	$.ajax({
		async:true,
		type:'post',
		url:'join.do',
		data:{amount:""+$("#input_amount").val(),sign:""+_sign,roomid:""+$("#roomid").val(),number:""+select_number,nextname:""+$("#nextname").val()},
		dataType:'json',
		success:function(result,textStatus){
			
			waitdialog.destroy();
			is_op = false;
			if(result.result=="SUCCESS"){
				new TipBox({type:'success',str:'操作成功',hasBtn:true});
			    
				//更新相关数据
				$("#my_balance").html(""+result.desc);
				var sumamount = parseInt($("#sumamount").html());
				sumamount = sumamount+_amount;
				//sumamount = sumamount.toFixed(4);
				$("#sumamount").html(""+sumamount);
				
				var _count=parseInt($("#_count"+select_number).html());
				var _sumamount=parseInt($("#_sumamount"+select_number).html());
				var _maxamount=parseInt($("#_maxamount"+select_number).html());
				var _myamount=parseInt($("#_myamount"+select_number).html());
				
				if(_myamount<=0){//不是加注
					is_first_join=1;
					_count = _count+1;
					$("#_count"+select_number).html(""+_count);
				}else{
					is_first_join=0;
				}
				_myamount = _myamount+_amount;
				$("#_myamount"+select_number).html(""+_myamount);
				
				_sumamount = _sumamount+_amount;
				$("#_sumamount"+select_number).html(""+_sumamount);
				
				
				if(_myamount>_maxamount){
					_maxamount = _myamount;
				}
				$("#_maxamount"+select_number).html(""+_maxamount);
				
				//通知其他伙伴
				var text=is_first_join+"****"+_amount+"****"+select_number+"****"+_maxamount;
				var x = $("#roomid").val() + "@" + $("#userid").val() + "!#@#Qsaswe@#./1!"+"@"+text;
			    var _sign = hex_md5(x);
			    text = $("#roomid").val()+"&&__"+$("#userid").val()+"&&__3&&__"+text+"&&__"+_sign;
			    send(text);
			}else{
				new TipBox({type:'error',str:''+result.desc,hasBtn:true});
				
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			is_op = false;
			waitdialog.destroy();
			new TipBox({type:'error',str:'网络错误',hasBtn:true});
			
		}
	});
});

var waitkaijiangdialog=null;
//获取开奖信息
function getKaijiang(){
	hasGetKaijiang= true;
	if(waitkaijiangdialog!=null){
		return;
	}
	waitkaijiangdialog = new TipBox({type:'load',str:'正在获取收获结果',hasBtn:false});
	$.ajax({
		async:true,
		type:'post',
		url:'getResult.do',
		data:{qiname:""+$("#nextname").val()},//$("#nextname").val()
		dataType:'json',
		success:function(result,textStatus){
			
			waitkaijiangdialog.destroy();
			waitkaijiangdialog=null;
			if(result.result=="SUCCESS"){
				//new TipBox({type:'success',str:'获取成功',hasBtn:true});
				
				$("#kj_qiname").html(""+$("#nextname").val());
				$("#kj_join_number").html(""+result.amount);
				$("#kj_award_number").html(""+result.award);
				
				$("#kj_no1").html(""+result.awards.no1);
				$("#kj_no2").html(""+result.awards.no2);
				$("#kj_no3").html(""+result.awards.no3);
				$("#kj_no4").html(""+result.awards.no4);
				$("#kj_no5").html(""+result.awards.no5);
				$("#kj_no6").html(""+result.awards.no6);
				$("#kj_no7").html(""+result.awards.no7);
				$("#kj_no8").html(""+result.awards.no8);
				$("#kj_no9").html(""+result.awards.no9);
				$("#kj_no10").html(""+result.awards.no10);
				$("#kj_no11").html(""+result.awards.no11);
				
				$("#tbody").html("");
				 var datas = result.details;//根据K值得到一个LIST
                 if(datas){
                        for(var i = 0;i<datas.length;i++){  //循环LIST
                           var veh = datas[i];//获取LIST里面的对象
                           $("#tbody").append("<tr style='height: 30px;line-height: 30px;'>"+
					            "<td class='td_font' style='font-size:15px;font-weight:600'>"+veh.number+"</td>"+					       
					            "<td class='td_font' >"+veh.amount+"</td>"+
					           "<td class='td_font' >"+veh.award+"</td>"+   
					        "</tr>");
                       
                        }
                 }
                 
                 document.getElementById("gold_audio").play();
                 
				$('#sampledata3').bringins({
					"position":"center",
					"color":"black",
					"closeButton":"white",
					"width":"100%",
					"z-index":"1000"
				});
			}else{
				//new TipBox({type:'error',str:''+result.result,hasBtn:true});
				if (confirm(result.result+"，重试？")) {  
					getKaijiang();
		        }  
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			waitkaijiangdialog.destroy();
			waitkaijiangdialog=null;
			if (confirm("网络错误，重试？")) {  
				getKaijiang();
	        }  
	        
			//new TipBox({type:'error',str:'',hasBtn:true});
			
		}
	});
}

var websocket=null;
var _top=80;
var index=0;

var lockReconnect = false;  //避免ws重复连接
createWebSocket();   //连接ws

var host=window.location.host;
function createWebSocket(){
	//判断当前浏览器是否支持WebSocket
	if('WebSocket' in window){
		//websocket=new WebSocket("ws://localhost:8080/_game/websocket");/*("ws://"+host+"/Danmu/websocket");*/
		websocket=new WebSocket("ws://eth-game.club/websocket");
		initEventHandle();
	}
	else{
		alert("当前浏览器不支持发送弹幕!");
	}
}


function initEventHandle() {
	websocket.onclose = function () {
        console.log("llws连接关闭!" + new Date().toUTCString());
        reconnect();
    };
    websocket.onerror = function () {
        console.log("llws连接错误!");
        reconnect();
    };
    websocket.onopen = function () {
        heartCheck.reset().start();      //心跳检测重置
        console.log("llws连接成功!" + new Date().toUTCString());
       
    };
    websocket.onmessage = function (event) {    //如果获取到消息，心跳检测重置
        heartCheck.reset().start();      //拿到任何消息都说明当前连接是正常的
        var data_str = event.data;

    	var ss = data_str.split("&&__");
    	if(ss[0]=="1"){//聊天信息
    		if(tanonoff){
    			setMessageInnerHTML(ss[1]);
    		}
    	}
    	else if(ss[0]=="2"){//有人登录
    		$("#online_number").html(""+ss[1]);
    	}else if(ss[0]=="3"){//有人下注
    		var msg = ss[1].split("****");
    	
    		var is_first_join = parseInt(msg[0]);
    		var _amount = parseInt(msg[1]);
    		var select_number = parseInt(msg[2]);
    		var _maxamount = parseInt(msg[3]);
    		
    		//更新相关数据
    		var sumamount = parseInt($("#sumamount").html());
    		sumamount = sumamount+_amount;
    		$("#sumamount").html(""+sumamount);
    		
    		var _count=parseInt($("#_count"+select_number).html());
    		var _sumamount=parseInt($("#_sumamount"+select_number).html());
    		
    		if(is_first_join==1){//不是加注
    			_count = _count+1;
    			$("#_count"+select_number).html(""+_count);
    		}
    		
    		_sumamount = _sumamount+_amount;
    		$("#_sumamount"+select_number).html(""+_sumamount);
    	    $("#_maxamount"+select_number).html(""+_maxamount);
    		
    	}else if(ss[0]=="4"){

    		if(wait){
    			getKaijiang();
    		}
    		
    	}
    };
}

// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
	websocket.close();
}

function reconnect(url) {
    if (lockReconnect) return;
    lockReconnect = true;
    setTimeout(function () {     //没连接上会一直重连，设置延迟避免请求过多
        createWebSocket(url);
        lockReconnect = false;
    }, 2000);
}

//心跳检测
var heartCheck = {
    //timeout: 540000,        //9分钟发一次心跳
    //timeout: 3600,        //1分钟发一次心跳
    timeout: 10800,        //3分钟发一次心跳
    timeoutObj: null,
    serverTimeoutObj: null,
    reset: function () {
        clearTimeout(this.timeoutObj);
        clearTimeout(this.serverTimeoutObj);
        return this;
    },
    start: function () {
        var self = this;
        this.timeoutObj = setTimeout(function () {
            //这里发送一个心跳，后端收到后，返回一个心跳消息，
            //onmessage拿到返回的心跳就说明连接正常
        	websocket.send("ping");
            console.log("ping!")
            self.serverTimeoutObj = setTimeout(function () {
                //如果超过一定时间还没重置，说明后端主动断开了
                //如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
            	websocket.close();     
            },self.timeout)
        },this.timeout)
    }
}
//====================================================心跳包重连CODE END=========================================

//
////连接发生错误的回调方法
//websocket.onerror = function(){
//   // setMessageInnerHTML("error");
//};
//
//
//
////连接成功建立的回调方法
//websocket.onopen = function(){
//	//setInterval(function(){  websocket.send("1"); },55000);
//}
//
////接收到消息的回调方法
//websocket.onmessage = function(event){
//	
//	var data_str = event.data;
//
//	var ss = data_str.split("&&__");
//	if(ss[0]=="1"){//聊天信息
//		if(tanonoff){
//			setMessageInnerHTML(ss[1]);
//		}
//	}
//	else if(ss[0]=="2"){//有人登录
//		$("#online_number").html(""+ss[1]);
//	}else if(ss[0]=="3"){//有人下注
//		var msg = ss[1].split("****");
//	
//		var is_first_join = parseInt(msg[0]);
//		var _amount = parseInt(msg[1]);
//		var select_number = parseInt(msg[2]);
//		var _maxamount = parseInt(msg[3]);
//		
//		//更新相关数据
//		var sumamount = parseInt($("#sumamount").html());
//		sumamount = sumamount+_amount;
//		$("#sumamount").html(""+sumamount);
//		
//		var _count=parseInt($("#_count"+select_number).html());
//		var _sumamount=parseInt($("#_sumamount"+select_number).html());
//		//var _maxamount=parseFloat($("#_maxamount"+select_number).html());
//		//var _myamount=parseFloat($("#_myamount"+select_number).html());
//		
//		if(is_first_join==1){//不是加注
//			_count = _count+1;
//			$("#_count"+select_number).html(""+_count);
//		}
//		
//		_sumamount = _sumamount+_amount;
//		$("#_sumamount"+select_number).html(""+_sumamount);
//	    $("#_maxamount"+select_number).html(""+_maxamount);
//		
//	}else if(ss[0]=="4"){
////		alert(""+ss[1]);
////		alert($("#nextname").val());
////		if(ss[1]!=$("#nextname").val()){
//		//wait=true;
//		if(wait){
//			getKaijiang();
//		}
//		//}
//	}
//	
//	
//}
//
////连接关闭的回调方法
//websocket.onclose = function(){
//  //  setMessageInnerHTML("close");
//}
//
//
////监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
//window.onbeforeunload = function(){
//    websocket.close();
//}


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
	//alert(text);
   websocket.send(text);
}
