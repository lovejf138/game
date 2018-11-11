
package com.webpos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webpos.entity.Account;
import com.webpos.entity.User;
import com.webpos.service.AccountService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.IpayUtil;
import com.webpos.tools.JModelAndView;
import com.webpos.tools.Md5Encrypt;

/**
 * 支付相关
 * @author hugaoliang
 *
 */
@Controller
public class PayController extends ApiWebABaseController {
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private AccountService accountService;
	

	@RequestMapping({ "/chongzhi.do" })
	public ModelAndView chongzhi(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
	
		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);
		
		ModelAndView mv = new JModelAndView("pos/front/chongzhi", 0, request, response);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}
	
	/**
	 * 将参数转换成map
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> makeMap(HttpServletRequest request) {

		Map<String, String> map = new HashMap<>();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			String value = request.getParameter(paraName);

			map.put(paraName, value);
		}

		return map;
	}

	
	@RequestMapping("/ipaynotify.do")
	public void ipaynotify(BufferedReader reader, Writer writer) throws IOException {

		String responseMsg = "fail";

		Map<String, String> parameterMap = this.makeMap(request);
		
		
//		if(!IpayUtil.checksign(parameterMap)) {//签名错误
//			writer.write(responseMsg);
//			return;
//		}
		
		// 更新订单状态
		String orderNo = parameterMap.get("merOrderId");

		Account acount = accountService.getByRemark(orderNo);
		if(acount==null) {
			return;
		}
		if(!acount.getStatus().equals("request")) {
			responseMsg = "success";
			writer.write(responseMsg);
			return;
		}
		
		String orderStatus = parameterMap.get("orderStatus");
		if(!"0".equals(orderStatus)) {
			return;
		}
		
		String amount = parameterMap.get("amount");
		Double _amount = CommUtil.div(amount, 100);
		
		String r = accountService.in(acount, _amount);
		if(!r.equals("success")) {return;}
		responseMsg = "success";
		
		writer.write(responseMsg);
	}
	
	/**
	 * 请求支付
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/payrequest.do" })
	@ResponseBody
	public BuyReturnData  payrequest(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		BuyReturnData result = new BuyReturnData();
		String amount = request.getParameter("amount");
		String sign = request.getParameter("sign");
		String type = request.getParameter("type");

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}
		
		User user = super.getLoginUser();

		Double _amount = 0.0;
		try {
			_amount = Double.parseDouble(amount);
		}catch(Exception e) {_amount=0.0;}
		if(_amount<10) {
			result.setResult("FAIL");
			result.setDesc("最少充值金额为10元");
			return result;
		}
		else if(_amount>10000) {
			result.setResult("FAIL");
			result.setDesc("最大充值金额为10000元，超过10000请联系客服");
			return result;
		}
		
		if(!type.equals("weixin")&&!type.equals("alipay")&&!type.equals("bank")){
			result.setResult("FAIL");
			result.setDesc("请选择支付类型，有问题请联系客服");
			return result;
		}
		
	    String localtime = (String)httpSession.getAttribute("localtime");
	    String self_sign_1 = Md5Encrypt.md5(amount+type+ "@" + localtime + "!#@#Qsaswe@#./1!");
	    if (!self_sign_1.equals(sign))
	    {
	    	result.setResult("FAIL");
			result.setDesc("非法请求");
			return result;
	    }
	    
	    String orderid = UUID.randomUUID().toString();
	    //请求支付
	    Account a = new Account();
	    a.setAll_eth(0.0);
	    a.setAmount(_amount);
	    a.setType("in");
	    a.setStatus("request");
	    a.setBalance(0.0);
	    a.setCtime(new Date());
	    a.setFianl_amount(""+_amount);
	    a.setIs_machine(0);
	    a.setPlay_sum(0.0);
	    a.setRemark(orderid);
	    a.setWithdraw_sum(0.0);
	    a.setRecharge_sum(0.0);
	    a.setUser_id(user.getPhone());
	    accountService.insert(a);
		
		String r = "fail";
		String desc = "";
		if(type.equals("weixin")){
//			String s = IpayUtil.weixinWapPay(_amount, orderid);
//			System.out.println(s);
//			if(s.startsWith("http:")) {//成功
//				r = "SUCCESS";
//				desc = s;
//			}else {
//				r = "fail";
//				desc = s;
//			}
			r = "fail";
			desc = "微信支付通道暂时关闭，请联系客服充值";
		}else if(type.equals("alipay")){
			String s = IpayUtil.alipayWapPay(_amount, orderid);
			if(s.startsWith("http:")||s.startsWith("alipays")) {//成功
				r = "SUCCESS";
				desc = s;
			}else {
				r = "fail";
				desc = s;
			}
		}if(type.equals("bank")){
			String s = IpayUtil.bankWapPay(_amount, orderid);
			if(s.startsWith("http:")) {//成功
				r = "SUCCESS";
				desc = s;
			}else {
				r = "fail";
				desc = s;
			}
		}
		
		result.setResult(r);
		result.setDesc(desc);
		return result;
	}
}
