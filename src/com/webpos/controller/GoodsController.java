
package com.webpos.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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

import com.webpos.entity.Award;
import com.webpos.entity.Detail2;
import com.webpos.entity.Detail2Example;
import com.webpos.entity.Goods;
import com.webpos.entity.GoodsExample;
import com.webpos.entity.Goodsdeal;
import com.webpos.entity.Info;
import com.webpos.entity.User;
import com.webpos.service.AwardService;
import com.webpos.service.Detail2Service;
import com.webpos.service.GoodsService;
import com.webpos.service.InfoService;
import com.webpos.service.UserService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.JModelAndView;
import com.webpos.tools.Md5Encrypt;
import com.webpos.tools.Pagination;

@Controller
public class GoodsController extends ApiWebABaseController {
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private Detail2Service detail2Service;
	@Autowired
	private AwardService awardService;
	@Autowired
	private InfoService  infoService;
	@Autowired
	private UserService userService;

	/**
	 * 领取商品
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/linggoodsdeal.do" })
	@ResponseBody
	public BuyReturnData  linggoodsdeal(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		BuyReturnData result = new BuyReturnData();
		String orderid = request.getParameter("orderid");

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}
		
		User user = super.getLoginUser();

		long oid = 0;
		try {
			oid = Long.parseLong(orderid);
		}catch(Exception e) {oid=0;}
		Detail2 detail = detail2Service.selectByPrimaryKey(oid);
		if(detail==null) {
			result.setResult("FAIL");
			result.setDesc("订单不存在");
			return result;
		}else if(!detail.getStatus().equals("waitling")) {
			result.setResult("FAIL");
			result.setDesc("该订单已领奖过！");
			return result;
		}
		if(!user.getPhone().equals(detail.getUserid())){
			result.setResult("FAIL");
			result.setDesc("不要乱动别人的订单！");
			return result;
		}
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		if(name==null||name.length()<=0) {
			result.setResult("FAIL");
			result.setDesc("姓名不能为空");
			return result;
		}
		
		if(address==null||address.length()<=0) {
			result.setResult("FAIL");
			result.setDesc("收货地址不能为空");
			return result;
		}
		
		if(phone==null||phone.length()<=0) {
			result.setResult("FAIL");
			result.setDesc("手机号不能为空");
			return result;
		}
		
		
		Goodsdeal gd = new Goodsdeal();
		gd.setAddress(address);
		gd.setCtime(new Date());
		gd.setGoodsname(detail.getGoodsname());
		gd.setName(name);
		gd.setPhone(phone);
		gd.setStatus("request");
		gd.setUserid(user.getId());
		gd.setDetailid(detail.getId());
		String r = goodsService.lingqugoods(detail, gd, user);
		if(!r.equals("SUCCESS")) {
			result.setResult("FAIL");
			result.setDesc("操作失败！"+r);
			return result;
		}
		result.setResult("SUCCESS");

		return result;
	}
	
	/**
	 * 领取奖品页面
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/lingqugoods.do" })
	public ModelAndView lingqugoods(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		String orderid = request.getParameter("orderid");

		if (!super.isLogin()) {
			if (!super.isLogin()) {
				return new ModelAndView("redirect:/login.do");
			}
		}
		
		User user = super.getLoginUser();

		Info info = infoService.selectByUserid(user.getId());
		long oid = 0;
		try {
			oid = Long.parseLong(orderid);
		}catch(Exception e) {oid=0;}
		Detail2 detail = detail2Service.selectByPrimaryKey(oid);

		ModelAndView mv = new JModelAndView("pos/front/lingqugoods", 0, request, response);

		mv.addObject("detail",detail);
		mv.addObject("info",info);
		mv.addObject("orderid",orderid);
		
		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}
	
	/**
	 * 兑换至余额
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/backtobalance.do" })
	@ResponseBody
	public BuyReturnData  backtobalance(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		BuyReturnData result = new BuyReturnData();
		String orderid = request.getParameter("orderid");

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}
		
		User user = super.getLoginUser();

		long oid = 0;
		try {
			oid = Long.parseLong(orderid);
		}catch(Exception e) {oid=0;}
		Detail2 detail = detail2Service.selectByPrimaryKey(oid);
		if(detail==null) {
			result.setResult("FAIL");
			result.setDesc("订单不存在");
			return result;
		}else if(!detail.getStatus().equals("waitling")) {
			result.setResult("FAIL");
			result.setDesc("该订单已领奖过！");
			return result;
		}
		if(!user.getPhone().equals(detail.getUserid())){
			result.setResult("FAIL");
			result.setDesc("不要乱动别人的订单！");
			return result;
		}
		
		String r = goodsService.backtobalance(detail, user);
		if(!r.equals("SUCCESS")) {
			result.setResult("FAIL");
			result.setDesc("操作失败！"+r);
			return result;
		}
		result.setResult("SUCCESS");

		return result;
	}
	
	/**
	 * 我的订单
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/orders.do" })
	public ModelAndView canyu(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		String currentPage = request.getParameter("currentPage");

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		User user = super.getLoginUser();

		ModelAndView mv = new JModelAndView("pos/front/orders", 0, request, response);

		Detail2Example meExamplee = new Detail2Example();
		meExamplee.clear();
		meExamplee.setPageSize(10);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		Detail2Example.Criteria criteria = meExamplee.createCriteria();
		criteria.andUserIdEqual(user.getPhone());
		Pagination pList = this.detail2Service.getObjectListWithPage(meExamplee);

		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}
	
	@RequestMapping({ "/goodsbuy.do" })
	@ResponseBody
	public BuyReturnData goodsbuy(HttpServletRequest request, HttpSession httpSession, Model model) {
		BuyReturnData result = new BuyReturnData();

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}
		String goodsid = request.getParameter("goodsid");

		if (goodsid == null || goodsid.length() <= 0) {
			result.setResult("FAIL");
			result.setDesc("商品不存在");
			return result;
		}

		String nextname = request.getParameter("nextname");
		if (nextname == null || nextname.length() != 8) {
			result.setResult("FAIL");
			result.setDesc("期号错误，请刷新页面");
			return result;
		}

		/*********** 判断是否可以下注及获得下注期号start **************/
		int nextsecond = CommUtil.getSecondFromQi(nextname);
		if (nextsecond <= 0) {
			result.setResult("FAIL");
			result.setDesc("抱歉！正在等待上一期开奖，请稍后抢购");
			return result;
		}
		/*********** 判断是否可以下注及获得下注期号end *****************/

		Goods goods = this.goodsService.selectByPrimaryKey(Long.parseLong(goodsid));
		if (goods == null) {
			result.setResult("FAIL");
			result.setDesc("商品不存在");
			return result;
		}
		int type = 2;
		if (goods.getBeishu() == 6) {
			type = 6;
		} else if (goods.getBeishu() == 250) {
			type = 250;
		}

		User user = super.getLoginUser();

		String fangan = request.getParameter("fangan");
		if (fangan == null || fangan.length() <= 0) {
			result.setResult("FAIL");
			result.setDesc("请选择方案");
			return result;
		}

		/****************** 判断签名start ***********************/

		String sign = request.getParameter("sign");
		String locatime = (String) httpSession.getAttribute("localtime");
		String self_ = Md5Encrypt.md5(fangan + locatime + "!#@#Qsaswe@#./1!");

		if (!self_.equals(sign)) {
			result.setResult("FAIL");
			result.setDesc("非法请求");
			return result;
		}
		/****************** 判断签名end ***********************/

		String r = "";
		if (type == 2) {// 猜大小

			if (!fangan.equals("1") && !fangan.equals("0")) {
				result.setResult("FAIL");
				result.setDesc("请选择大小");
				return result;
			}
			User user_last = this.userService.selectByPhone(user.getPhone());
			if (user_last == null) {
				result.setResult("FAIL");
				result.setDesc("请登录");
				return result;
			}
			double b_amount = user_last.getBalance();

			if (goods.getNowprice() > user_last.getBalance()) {
				result.setResult("FAIL");
				result.setDesc("余额不足！！！");
				return result;
			}

			r = goodsService.buy2(user_last, goods, goods.getNowprice(), nextname, fangan);
		} else if (type == 6) {// 猜第一位数字
			User user_last = this.userService.selectByPhone(user.getPhone());
			if (user_last == null) {
				result.setResult("FAIL");
				result.setDesc("请登录");
				return result;
			}

			String[] fangs = fangan.split(",");
			if (fangs.length <= 0 || fangs.length > 11) {
				result.setResult("FAIL");
				result.setDesc("方案有误！！！");
				return result;
			}
			double ap = CommUtil.mul(goods.getNowprice(), fangs.length);
			if (ap > user_last.getBalance()) {
				result.setResult("FAIL");
				result.setDesc("余额不足！！！");
				return result;
			}

			r = goodsService.buy6(user_last, goods, ap, nextname, fangan);
		} else if (type == 250) {// 猜5个数字

			User user_last = this.userService.selectByPhone(user.getPhone());
			if (user_last == null) {
				result.setResult("FAIL");
				result.setDesc("请登录");
				return result;
			}
			String[] fangs = fangan.split(",");
			if (fangs.length <= 0 || fangs.length > 11) {
				result.setResult("FAIL");
				result.setDesc("方案有误！！！");
				return result;
			}
			int beishu = 1;
			if (fangs.length == 5)
				beishu = 1;
			else if (fangs.length == 6)
				beishu = 6;
			else if (fangs.length == 7)
				beishu = 21;
			else if (fangs.length == 8)
				beishu = 56;
			else if (fangs.length == 9)
				beishu = 126;
			else if (fangs.length == 10)
				beishu = 252;
			else if (fangs.length == 11)
				beishu = 462;

			double ap = CommUtil.mul(goods.getNowprice(), beishu);
			if (ap > user_last.getBalance()) {
				result.setResult("FAIL");
				result.setDesc("余额不足！！！");
				return result;
			}
			r = goodsService.buy250(user_last, goods, goods.getNowprice(), nextname, fangan);
		}

		if (r.equals("SUCCESS")) {
			User user_last = this.userService.selectByPhone(user.getPhone());
			result.setDesc("" + user_last.getBalance());
			result.setResult("SUCCESS");
		} else {
			result.setResult("FAIL");
			result.setDesc(r);
		}

		return result;
	}

	@RequestMapping({ "/goodsbuypage.do" })
	public ModelAndView goodsbuypage(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}

		String goodsid = request.getParameter("goodsid");

		if (goodsid == null || goodsid.length() <= 0) {
			return new ModelAndView("redirect:/goods.do");
		}
		Goods goods = this.goodsService.selectByPrimaryKey(Long.parseLong(goodsid));

		ModelAndView mv = new JModelAndView("pos/front/goodsbuypage2", 0, request, response);
		if (goods.getBeishu() == 6) {
			mv = new JModelAndView("pos/front/goodsbuypage6", 0, request, response);
		}
		if (goods.getBeishu() == 250) {
			mv = new JModelAndView("pos/front/goodsbuypage250", 0, request, response);
		}

		User user = userService.selectByPhone(super.getLoginUser().getPhone());

		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);

		mv.addObject("goods", goods);
		mv.addObject("user", user);

		Award award = awardService.getLast();
		String nextname = CommUtil.getNextQi(award.getName());
		int nextsecond = CommUtil.getSecondFromQi(nextname);
		// mv.addObject("award",award);
		mv.addObject("nextname", nextname);
		mv.addObject("nextsecond", nextsecond);

		List<Award> awards = awardService.getLastList(5);
		Collections.reverse(awards);
		mv.addObject("awards", awards);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	/**
	 * 获取商品
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/goods.do" })
	public ModelAndView goods(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		String beishu = request.getParameter("beishu");

		int _beishu = 2;
		try {
			_beishu = Integer.parseInt(beishu);
		} catch (Exception e) {
			_beishu = 2;
		}

		ModelAndView mv = new JModelAndView("pos/front/goods", 0, request, response);

		GoodsExample meExamplee = new GoodsExample();
		meExamplee.clear();
		meExamplee.setPageSize(100);
		meExamplee.setOrderByClause("nowprice asc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		GoodsExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andBeishuEqual(_beishu);
		criteria.andStatusEqual("normal");
		Pagination pList = this.goodsService.getObjectListWithPage(meExamplee);

		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);
		return mv;
	}

	/**
	 * 尝试购买，判断登录和余额
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/buygoodstry.do" })
	@ResponseBody
	public BuyReturnData buygoodstry(HttpServletRequest request, HttpSession httpSession, Model model) {
		BuyReturnData result = new BuyReturnData();

		if (!super.isLogin()) {
			result.setResult("not_login");
			result.setDesc("请登录");
			return result;
		}

		User user = super.getLoginUser();

		User user_last = this.userService.selectByPhone(user.getPhone());
		if (user_last == null) {
			result.setResult("not_login");
			result.setDesc("请登录");
			return result;
		}

		String goodsid = request.getParameter("goodsid");

		if (goodsid == null || goodsid.length() <= 0) {
			result.setResult("select_goods");
			result.setDesc("请选择商品");
			return result;
		}
		Goods goods = this.goodsService.selectByPrimaryKey(Long.parseLong(goodsid));
		if (goods == null || !goods.getStatus().equals("normal")) {
			result.setResult("goods_down");
			result.setDesc("商品已下架，请刷新页面");
			return result;
		}

		if (user_last.getBalance() < goods.getNowprice()) {
			result.setResult("balance_not_enough");
			result.setDesc("余额不足，请充值");
			return result;
		}
		result.setResult("success");
		return result;
	}

}
