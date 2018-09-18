<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, minimum-scale= 1.0, initial-scale= 1.0">
<title>中奖及规则说明</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webpos/pos/front/css/index_style.css?t=6">
<link rel="stylesheet" href="<%=request.getContextPath()%>/webpos/pos/front/css/guize.css">

</head>
<body>

<div class="nav" style="height: 40px;line-height: 40px;">
	<a class="weizhi" href="index.do" style="height: 40px;line-height: 40px;"> <img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/zuo.png" alt=""></a>
	<div class="zhongzi" style="height: 40px;line-height: 40px;font-size: 20px;">
		中奖及规则说明
	</div>
 
</div>


<div class="container" style="margin-top: 50px;">

	<div id="timeline">
		<div class="timeline-item">
			<div class="timeline-icon">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/star.svg" alt="">
			</div>
			<div class="timeline-content">
				<h2>1. 如何参与？</h2>
				<p style="font-size: 15px;"></br>使用以太坊地址登录，往系统指定地址转入以太坊数量，前往首页选择房间进入即可参与</p>
				
			</div>
		</div>

		<div class="timeline-item">
			<div class="timeline-icon">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/book.svg" alt="">
			</div>
			<div class="timeline-content right">
				<h2>2. 中奖号码如何产生？公平吗？</h2>
				<p style="font-size: 15px;"></br>系统每期都按照广东11选5开奖获取结果前5位号码，每天84期。09:00～23:00期间每10分钟一期。保证开奖结果任何人都不能作弊，绝对公平</p>
			
			</div>
		</div>

		<div class="timeline-item">
			<div class="timeline-icon">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/star.svg" alt="">
			</div>
			<div class="timeline-content">
				<h2>3. 开奖号码后6位如何产生？</h2>
				<p style="font-size: 15px;"></br>根据开奖的前5位数， 第6位至11位开奖规则： </br>(1).计算前面n位的开奖数字的和x</br>(2).将x取余x ＝ x％12；</br>
	 (3).判断x是否在前面n位数出现过，若没出现过，即第n位的开奖数字，接下来转到第1步计算n＋1位开奖数字。若出现过，转到第4步;</br>
	 (4).先另x=x+1, 若x>12,则x=x-11.将得到的x返回第3步判断</p>
			</div>
		</div>
		
		
		<div class="timeline-item">
			<div class="timeline-icon">
				<img src="<%=request.getContextPath()%>/webpos/pos/front/img/index/book.svg" alt="">
			</div>
			<div class="timeline-content">
				<h2>4. 我参与后奖金怎么算？</h2>
				<p style="font-size: 15px;">
				</br>(1).进房间参与后会获得一个参与号码，比如5（号码按照参与的顺序自动得到）；
				</br>(2).假如此次开奖号码为7，8，2，4，5。。。
				</br>(3).系统会先结算7，8，2，4号码的参与用户，若结算完房间币池还有剩余，便结算号码5的奖金
				</br>(4).奖金最多为参与金额的2倍
				</p>
			</div>
		</div>
		
		
	</div>

</div>


</body>
</html>
