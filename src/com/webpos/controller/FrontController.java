
package com.webpos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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

import com.webpos.dao.SystemMapper;
import com.webpos.entity.AccountExample;
import com.webpos.entity.Award;
import com.webpos.entity.Detail;
import com.webpos.entity.DetailExample;
import com.webpos.entity.GoodsExample;
import com.webpos.entity.Info;
import com.webpos.entity.OneDetail;
import com.webpos.entity.OneDetailExample;
import com.webpos.entity.Room;
import com.webpos.entity.SumDetail;
import com.webpos.entity.TestDetail;
import com.webpos.entity.TestDetailExample;
import com.webpos.entity.User;
import com.webpos.service.AccountService;
import com.webpos.service.AwardService;
import com.webpos.service.DetailService;
import com.webpos.service.GoodsService;
import com.webpos.service.InfoService;
import com.webpos.service.OneDetailService;
import com.webpos.service.RoomService;
import com.webpos.service.TestDetailService;
import com.webpos.service.UserService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.JModelAndView;
import com.webpos.tools.Md5Encrypt;
import com.webpos.tools.Pagination;

@Controller
public class FrontController extends ApiWebABaseController {
	private Logger log = Logger.getLogger(getClass());
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private GoodsService  goodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private DetailService detailService;
	@Autowired
	private TestDetailService testdetailService;
	@Autowired
	private OneDetailService onedetailService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private InfoService infoService;
	@Resource
	private SystemMapper systemDao;

	private Integer sumAmount = 0;
	
	@RequestMapping({ "/savepersonal.do" })
	@ResponseBody
	public BuyReturnData savepersonal(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		BuyReturnData result = new BuyReturnData();
		if (!super.isLogin()) {
			if (!super.isLogin()) {
				result.setResult("FAIL");
				result.setDesc("请登录");
				return result;
			}
		}
		User user = super.getLoginUser();
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String cardname = request.getParameter("cardname");
		String cardno = request.getParameter("cardno");
		String cardbank = request.getParameter("cardbank");

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
		
		if(cardname==null||cardname.length()<=0) {
			result.setResult("FAIL");
			result.setDesc("银行卡开户名不能为空");
			return result;
		}
		
		if(cardno==null||cardno.length()<=0) {
			result.setResult("FAIL");
			result.setDesc("银行卡账号不能为空");
			return result;
		}
		
		if(cardbank==null||cardbank.length()<=0) {
			result.setResult("FAIL");
			result.setDesc("银行卡支行不能为空");
			return result;
		}
		
		try {
			
		
		Info info = infoService.selectByUserid(user.getId());
		if(info==null) {
			info = new Info();
			info.setUserid(user.getId());
			info.setAddress(address);
			info.setCardbank(cardbank);
			info.setCardname(cardname);
			info.setCardno(cardno);
			info.setCtime(new Date());
			info.setPhone(user.getPhone());
			info.setName(name);
			infoService.insert(info);
		}else {
			info.setUserid(user.getId());
			info.setAddress(address);
			info.setCardbank(cardbank);
			info.setCardname(cardname);
			info.setCardno(cardno);
			info.setCtime(new Date());
			info.setPhone(user.getPhone());
			info.setName(name);
			infoService.update(info);
		}
	
		result.setResult("success");
		}catch(Exception e) {
			result.setDesc("error:"+e.getMessage().toString());
			result.setResult("fail");
		}
		
		return result;
	}
	
	@RequestMapping({ "/personal.do" })
	public ModelAndView personal(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		User user = super.getLoginUser();
		Info info = infoService.selectByUserid(user.getId());
		if(info==null) {
			info = new Info();
		}

		ModelAndView mv = new JModelAndView("pos/front/personal", 0, request, response);

		mv.addObject("info", info);
		mv.addObject("user", user);
		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}
	
	@RequestMapping({ "/login.do" })
	public ModelAndView login(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		String parent = request.getParameter("parent");

		if (parent == null || parent.equals("")) {
			parent = (String) httpSession.getAttribute("parent");
		}
		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);
		httpSession.setAttribute("parent", parent);

		ModelAndView mv = new JModelAndView("pos/front/login", 0, request, response);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}
	@RequestMapping({ "/reg.do" })
	public ModelAndView reg(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		String parent = request.getParameter("parent");

		if (parent == null || parent.equals("")) {
			parent = (String) httpSession.getAttribute("parent");
		}
		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);
		httpSession.setAttribute("parent", parent);

		ModelAndView mv = new JModelAndView("pos/front/reg", 0, request, response);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/guize.do" })
	public ModelAndView guize(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		ModelAndView mv = new JModelAndView("pos/front/guize", 0, request, response);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/childcanyu.do" })
	public ModelAndView childcanyu(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		String currentPage = request.getParameter("currentPage");

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		User user = super.getLoginUser();

		ModelAndView mv = new JModelAndView("pos/front/childcanyu", 0, request, response);

		DetailExample meExamplee = new DetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(10);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		DetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andParentIdEqual(user.getUser_id());
		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);

		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/canyu.do" })
	public ModelAndView canyu(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		String currentPage = request.getParameter("currentPage");

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		User user = super.getLoginUser();

		ModelAndView mv = new JModelAndView("pos/front/canyu", 0, request, response);

		DetailExample meExamplee = new DetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(10);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		DetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andUserIdEqual(user.getUser_id());
		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);

		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/index.do" })
	public ModelAndView index(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

//		if (!super.isLogin()) {
//			return new ModelAndView("redirect:/login.do");
//		}

		String parent = request.getParameter("parent");
		// String hide_room =request.getParameter("hide_room");
		httpSession.setAttribute("parent", parent);

		Award award = awardService.getLast();
		String nextname = CommUtil.getNextQi(award.getName());

//		RoomExample meExamplee = new RoomExample();
//		meExamplee.clear();
//		meExamplee.setPageSize(10000);
//		meExamplee.setOrderByClause("id asc");
//		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));
//
//		RoomExample.Criteria criteria = meExamplee.createCriteria();
//		
//		String if_hide_room = (String) httpSession.getAttribute("if_hide_room");
//		if(if_hide_room==null) {
//			if_hide_room = "_false";
//		}
//		
//		if(hide_room!=null&&hide_room.length()>0) {//考虑隐藏房间与否情况
//		
//			if(if_hide_room.equals("_false")) {
//				if_hide_room = "_true";
//			}else {
//				if_hide_room = "_false";
//			}
//		}
//		httpSession.setAttribute("if_hide_room",if_hide_room);
//		
//		if(if_hide_room.equals("_true")) {
//			criteria.andProgressLess(10);
//		}
//		
//		Pagination pList = this.roomService.getObjectListWithPage(meExamplee);

		Room room = roomService.selectByPrimaryKey((long) 1);
		ModelAndView mv = new JModelAndView("pos/front/index", 0, request, response);

		int nextsecond = CommUtil.getSecondFromQi(nextname);
		mv.addObject("award", award);
		mv.addObject("room", room);
		mv.addObject("nextname", nextname);
		mv.addObject("nextsecond", nextsecond);
		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/gerenzhongxin.do" })
	public ModelAndView gerenzhongxin(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}

		User user = userService.selectByUserId(super.getLoginUser().getUser_id());

		ModelAndView mv = new JModelAndView("pos/front/gerenzhongxin", 0, request, response);

		mv.addObject("user", user);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/chongzhimingxi.do" })
	public ModelAndView chongzhimingxi(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}

		// User user = userService.selectByUserId(super.getLoginUser().getUser_id());

		ModelAndView mv = new JModelAndView("pos/front/chongzhimingxi", 0, request, response);

		AccountExample meExamplee = new AccountExample();
		meExamplee.clear();
		meExamplee.setPageSize(30);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		AccountExample.Criteria criteria = meExamplee.createCriteria();

		criteria.andUserEqualTo(super.getLoginUser().getUser_id());
		criteria.andTypeEqualTo("in");

		Pagination pList = this.accountService.getObjectListWithPage(meExamplee);

		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/tixianmingxi.do" })
	public ModelAndView tixianmingxi(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}

		// User user = userService.selectByUserId(super.getLoginUser().getUser_id());

		ModelAndView mv = new JModelAndView("pos/front/tixianmingxi", 0, request, response);
		AccountExample meExamplee = new AccountExample();
		meExamplee.clear();
		meExamplee.setPageSize(30);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		AccountExample.Criteria criteria = meExamplee.createCriteria();

		criteria.andUserEqualTo(super.getLoginUser().getUser_id());
		criteria.andTypeEqualTo("withdraw");

		Pagination pList = this.accountService.getObjectListWithPage(meExamplee);

		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	/**
	 * 开奖信息页面
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/kj.do" })
	public ModelAndView kj(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

//		if (!super.isLogin()) {
//			return new ModelAndView("redirect:/login.do");
//		}

		Award award = awardService.getLast();
		List<Award> awards = awardService.getLastList(30);
		String nextname = CommUtil.getNextQi(award.getName());

		ModelAndView mv = new JModelAndView("pos/front/kaijiang", 0, request, response);

		int nextsecond = CommUtil.getSecondFromQi(nextname);
		mv.addObject("award", award);
		mv.addObject("awards", awards);
		mv.addObject("nextname", nextname);
		mv.addObject("nextsecond", nextsecond);
		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	/**
	 * 排行信息页面
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping({ "/paihang.do" })
	public ModelAndView paihang(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

//		if (!super.isLogin()) {
//			return new ModelAndView("redirect:/login.do");
//		}

		List<User> awards = userService.getPaihangaward();
		List<User> plays = userService.getPaihangplay();

		ModelAndView mv = new JModelAndView("pos/front/paihang", 0, request, response);

		User award1 = awards.get(0);
		User award2 = awards.get(1);
		User award3 = awards.get(2);

		User play1 = plays.get(0);
		User play2 = plays.get(1);
		User play3 = plays.get(2);
		mv.addObject("award1", award1);
		mv.addObject("award2", award2);
		mv.addObject("award3", award3);
		mv.addObject("play1", play1);
		mv.addObject("play2", play2);
		mv.addObject("play3", play3);

		mv.addObject("awards", awards);
		mv.addObject("plays", plays);
		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/onejoin.do" })
	@ResponseBody
	public BuyReturnData onejoin(HttpServletRequest request, HttpSession httpSession, Model model) {
		BuyReturnData result = new BuyReturnData();

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}

		User user = super.getLoginUser();

		/********* 判断号码 *********************/
		String str_number = request.getParameter("number");
		int number = 0;
		try {
			number = Integer.parseInt(str_number);
		} catch (Exception e) {
			number = 0;
		}
		if (number <= 0 || number >= 12) {
			result.setResult("FAIL");
			result.setDesc("抱歉，请选择号码");
			return result;
		}

		/********** 判断参与金额start *************/
		String amount = request.getParameter("amount");

		double f_amount = 0;
		try {
			f_amount = Double.parseDouble(amount);
		} catch (Exception e) {
			f_amount = 0;
		}
		if (f_amount < 0.005) {
			result.setResult("FAIL");
			result.setDesc("抱歉，最少0.005ETH");
			return result;
		}
		if (f_amount > 10) {
			result.setResult("FAIL");
			result.setDesc("抱歉，最多10ETH");
			return result;
		}
		/********** 判断参与金额end *************/

		/*************** 判断余额start ********************/
		User user_last = this.userService.selectByParent(user.getId_md5());
		if (user_last == null) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}
		double b_amount = user_last.getBalance();

		if (f_amount > b_amount) {
			result.setResult("FAIL");
			result.setDesc("余额不足！！！");
			return result;
		}
		/*************** 判断余额end ********************/

//		Award award = awardService.getLast();
//		String nextname = CommUtil.getNextQi(award.getName());
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
			result.setDesc("抱歉！正在收获上一季，请稍后播种");
			return result;
		}
		/*********** 判断是否可以下注及获得下注期号end *****************/

		/****************** 判断签名start ***********************/

		String sign = request.getParameter("sign");
		String locatime = (String) httpSession.getAttribute("localtime");
		String self_ = Md5Encrypt.md5(amount + locatime + "!#@#Qsaswe@#./1!");

		if (!self_.equals(sign)) {
			result.setResult("FAIL");
			result.setDesc("非法请求");
			return result;
		}
		/****************** 判断签名end ***********************/

		String r = userService.onejoin(user_last.getUser_id(), user_last.getParent(), nextname, f_amount, number);

		if (r.equals("SUCCESS")) {
			user_last = this.userService.selectByParent(user.getId_md5());
			result.setDesc("" + user_last.getBalance());
			result.setResult("SUCCESS");
		} else {
			result.setResult("FAIL");
			result.setDesc(r);
		}

		return result;
	}

	@RequestMapping({ "/join.do" })
	@ResponseBody
	public BuyReturnData join(HttpServletRequest request, HttpSession httpSession, Model model) {
		BuyReturnData result = new BuyReturnData();

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}

//
//		User user = super.getLoginUser();
//
//		/********* 判断号码 *********************/
//		String str_number = request.getParameter("number");
//		int number = 0;
//		try {
//			number = Integer.parseInt(str_number);
//		} catch (Exception e) {
//			number = 0;
//		}
//		if (number <= 0 || number >= 12) {
//			result.setResult("FAIL");
//			result.setDesc("抱歉，请选择号码");
//			return result;
//		}
//
//		/********** 判断参与金额start *************/
//		String amount = request.getParameter("amount");
//
//		Integer f_amount = 0;
//		try {
//			f_amount = Integer.parseInt(amount);
//		} catch (Exception e) {
//			f_amount = 0;
//		}
//		if (f_amount < 10) {
//			result.setResult("FAIL");
//			result.setDesc("抱歉，最少播10个种子");
//			return result;
//		}
//		/********** 判断参与金额end *************/
//
//		/*************** 判断余额start ********************/
//		User user_last = this.userService.selectByParent(user.getId_md5());
//		if (user_last == null) {
//			result.setResult("FAIL");
//			result.setDesc("请登录");
//			return result;
//		}
//		Integer b_amount = user_last.getBalance();
//
//		if (f_amount > b_amount) {
//			result.setResult("FAIL");
//			result.setDesc("余额不足！！！");
//			return result;
//		}
//		/*************** 判断余额end ********************/
//
////		Award award = awardService.getLast();
////		String nextname = CommUtil.getNextQi(award.getName());
//		String nextname = request.getParameter("nextname");
//		if (nextname == null || nextname.length() != 8) {
//			result.setResult("FAIL");
//			result.setDesc("期号错误，请刷新页面");
//			return result;
//		}
//
//		/*********** 判断是否可以下注及获得下注期号start **************/
//		int nextsecond = CommUtil.getSecondFromQi(nextname);
//		if (nextsecond <= 0) {
//			result.setResult("FAIL");
//			result.setDesc("抱歉！正在收获上一季，请稍后播种");
//			return result;
//		}
//		/*********** 判断是否可以下注及获得下注期号end *****************/
//
//		/****************** 判断签名start ***********************/
//
//		String sign = request.getParameter("sign");
//		String locatime = (String) httpSession.getAttribute("localtime");
//		String self_ = Md5Encrypt.md5(amount + locatime + "!#@#Qsaswe@#./1!");
//
//		if (!self_.equals(sign)) {
//			result.setResult("FAIL");
//			result.setDesc("非法请求");
//			return result;
//		}
//		/****************** 判断签名end ***********************/
//
//		String r = userService.join((long) 1, user_last.getUser_id(), user_last.getParent(), user_last.getId_short(),
//				nextname, f_amount, number);
//
//		if (r.equals("SUCCESS")) {
//			user_last = this.userService.selectByParent(user.getId_md5());
//			result.setDesc("" + user_last.getBalance());
//			result.setResult("SUCCESS");
//		} else {
//			result.setResult("FAIL");
//			result.setDesc(r);
//		}

		return result;
	}

	@RequestMapping({ "/testjoin.do" })
	@ResponseBody
	public BuyReturnData testjoin(HttpServletRequest request, HttpSession httpSession, Model model) {
		BuyReturnData result = new BuyReturnData();

		if (!super.isLogin()) {
			result.setResult("FAIL");
			result.setDesc("请登录");
			return result;
		}
		String userid = "";
		User user = super.getLoginUser();
		userid = user.getUser_id();
//		if (super.isLogin()) {
//			User user = super.getLoginUser();
//			userid = user.getUser_id();
//		}else {
		// userid = httpSession.getId();
		// }
		/********* 判断号码 *********************/
		String str_number = request.getParameter("number");
		int number = 0;
		try {
			number = Integer.parseInt(str_number);
		} catch (Exception e) {
			number = 0;
		}
		if (number <= 0 || number >= 12) {
			result.setResult("FAIL");
			result.setDesc("抱歉，请选择号码");
			return result;
		}

		/********** 判断参与金额start *************/
		String amount = request.getParameter("amount");

		Integer f_amount = 0;
		try {
			f_amount = Integer.parseInt(amount);
		} catch (Exception e) {
			f_amount = 0;
		}
		if (f_amount < 10) {
			result.setResult("FAIL");
			result.setDesc("抱歉，最少播10个种子");
			return result;
		}

		if (f_amount > 1000000) {
			result.setResult("FAIL");
			result.setDesc("抱歉，测试最多1000000个种子");
			return result;
		}
		/********** 判断参与金额end *************/

//		Award award = awardService.getLast();
//		String nextname = CommUtil.getNextQi(award.getName());
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
			result.setDesc("抱歉！正在收获上一季，请稍后播种");
			return result;
		}
		/*********** 判断是否可以下注及获得下注期号end *****************/

		/****************** 判断签名start ***********************/

		String sign = request.getParameter("sign");
		String locatime = (String) httpSession.getAttribute("localtime");
		String self_ = Md5Encrypt.md5(amount + locatime + "!#@#Qsaswe@#./1!");

		if (!self_.equals(sign)) {
			result.setResult("FAIL");
			result.setDesc("非法请求");
			return result;
		}
		/****************** 判断签名end ***********************/

		String r = userService.testjoin((long) 1, userid, nextname, f_amount, number);

		if (r.equals("SUCCESS")) {
			result.setResult("SUCCESS");
		} else {
			result.setResult("FAIL");
			result.setDesc(r);
		}

		return result;
	}

	@RequestMapping({ "/room.do" })
	public ModelAndView room(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		// String userid = request.getParameter("userid");//压力测试用
		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		String userid = super.getLoginUser().getUser_id();

		User user = userService.selectByUserId(userid);

		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);

		ModelAndView mv = new JModelAndView("pos/front/room", 0, request, response);

		long rid = 1;
//		try {
//			rid = Long.parseLong(roomid);
//		} catch (Exception e) {
//			rid = 1;
//		}
		Room room = roomService.selectByPrimaryKey(rid);
		mv.addObject("room", room);
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

		DetailExample meExamplee = new DetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(100000);
		meExamplee.setOrderByClause("number asc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		DetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andQinameEqual(nextname);
		criteria.andRoomIdEqual(room.getId());
		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);

		List<Detail> ds = (List<Detail>) pList.getList();
		List<SumDetail> sd = getSumDetailFromDetail(ds, userid);

//		int detailsize=0;
//		if(ds==null||ds.size()<=0) {
//			detailsize=0;
//		}else {
//			detailsize=1;
//		}
//		mv.addObject("detailsize", detailsize);
		mv.addObject("details", sd);
		mv.addObject("sumamount", sumAmount);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/oneroom.do" })
	public ModelAndView oneroom(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		// String userid = request.getParameter("userid");//压力测试用
		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		String userid = super.getLoginUser().getUser_id();

		User user = userService.selectByUserId(userid);

		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);

		ModelAndView mv = new JModelAndView("pos/front/oneroom", 0, request, response);

		mv.addObject("user", user);

		Award award = awardService.getLast();
		String nextname = CommUtil.getNextQi(award.getName());
		int nextsecond = CommUtil.getSecondFromQi(nextname);
		// mv.addObject("award",award);
		mv.addObject("nextname", nextname);
		mv.addObject("nextsecond", nextsecond);

		int systemnum = CommUtil.getNumberFromUseridAndqiname(userid, nextname);
		mv.addObject("systemnum", systemnum);

		List<Award> awards = awardService.getLastList(5);
		Collections.reverse(awards);
		mv.addObject("awards", awards);

		OneDetailExample meExamplee = new OneDetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(1);
		// meExamplee.setOrderByClause("number asc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		OneDetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andQinameEqual(nextname);
		criteria.andUserIdEqual(userid);
		Pagination pList = this.onedetailService.getObjectListWithPage(meExamplee);

		List<OneDetail> ds = (List<OneDetail>) pList.getList();
		String has_number = "0";
		String has_haoma = "";
		if (ds != null) {
			if (ds.size() > 0) {
				OneDetail o = ds.get(0);
				has_number = "" + o.getAmount();
				has_haoma = "" + o.getNumber();
			}
		}
		mv.addObject("has_number", has_number);
		mv.addObject("has_haoma", has_haoma);
		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/testroom.do" })
	public ModelAndView testroom(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		// String userid = request.getParameter("userid");//压力测试用
		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}
		String userid = super.getLoginUser().getUser_id();
//
//		User user = userService.selectByUserId(userid);

//		String userid = "";
//		if (super.isLogin()) {
//			userid = super.getLoginUser().getUser_id();
//		}else {
		// userid = httpSession.getId();
		// }
		String time = "" + System.currentTimeMillis();
		httpSession.setAttribute("localtime", time);

		ModelAndView mv = new JModelAndView("pos/front/testroom", 0, request, response);

		long rid = 1;
//		try {
//			rid = Long.parseLong(roomid);
//		} catch (Exception e) {
//			rid = 1;
//		}
		Room room = roomService.selectByPrimaryKey(rid);
		mv.addObject("room", room);
		mv.addObject("userid", userid);

		Award award = awardService.getLast();
		String nextname = CommUtil.getNextQi(award.getName());
		int nextsecond = CommUtil.getSecondFromQi(nextname);
		// mv.addObject("award",award);
		mv.addObject("nextname", nextname);
		mv.addObject("nextsecond", nextsecond);

		List<Award> awards = awardService.getLastList(5);
		Collections.reverse(awards);
		mv.addObject("awards", awards);

		TestDetailExample meExamplee = new TestDetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(100000);
		meExamplee.setOrderByClause("number asc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		TestDetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andQinameEqual(nextname);
		criteria.andRoomIdEqual(room.getId());
		Pagination pList = this.testdetailService.getObjectListWithPage(meExamplee);

		List<TestDetail> ds = (List<TestDetail>) pList.getList();
		List<SumDetail> sd = getSumDetailFromTestDetail(ds, userid);

//		int detailsize=0;
//		if(ds==null||ds.size()<=0) {
//			detailsize=0;
//		}else {
//			detailsize=1;
//		}
//		mv.addObject("detailsize", detailsize);
		mv.addObject("details", sd);
		mv.addObject("sumamount", sumAmount);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/droom.do" })
	public ModelAndView droom(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

		if (!super.isLogin()) {
			return new ModelAndView("redirect:/login.do");
		}

		String type = request.getParameter("type");
		String qiname = request.getParameter("qiname");
		String url = "canyu.do";
		if (type.equals("_last")) {// 上一期详情
			url = "room.do";

		} else if (type.equals("_now")) {// 当前开奖
			url = "room.do";

		} else if (type.equals("_canyu")) {// 我的参与
			url = "canyu.do";

		} else if (type.equals("_childcanyu")) {// 我的参与
			url = "childcanyu.do";

		}

		if (qiname == null || qiname.equals("")) {
			return new ModelAndView("redirect:/" + url);
		}

		if (type.equals("_last")) {// 上一期详情
			// 计算上一期
			qiname = CommUtil.getLastQi(qiname);
		}

		// String userid = super.getLoginUser().getUser_id();
//		String time = "" + System.currentTimeMillis();
//		httpSession.setAttribute("localtime", time);
		ModelAndView mv = new JModelAndView("pos/front/droom", 0, request, response);
//		long rid = 1;
//		try {
//			rid = Long.parseLong(roomid);
//		} catch (Exception e) {
//			rid = 1;
//		}

		DetailExample meExamplee = new DetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(10000);
		meExamplee.setOrderByClause("award desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		DetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andQinameEqual(qiname);
		// criteria.andRoomIdEqual(rid);
		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);

		// mv.addObject("roomid", roomid);
		mv.addObject("qiname", qiname);

		List<Detail> ds = (List<Detail>) pList.getList();

		List<Detail> result_ds = new ArrayList<Detail>();

		Integer _sum = 0;
		for (Detail d : ds) {
			_sum = _sum + d.getAmount();
		}

		Award award = awardService.getByName(qiname);
		if (award != null) {
			mv.addObject("kj", 1);
			mv.addObject("award", award);

			for (int i = 0; i < 11; i++) {
				List<Detail> remove_ds = new ArrayList<Detail>();
				int number = 0;
				if (i == 0)
					number = award.getNo1();
				else if (i == 1)
					number = award.getNo2();
				else if (i == 2)
					number = award.getNo3();
				else if (i == 3)
					number = award.getNo4();
				else if (i == 4)
					number = award.getNo5();
				else if (i == 5)
					number = award.getNo6();
				else if (i == 6)
					number = award.getNo7();
				else if (i == 7)
					number = award.getNo8();
				else if (i == 8)
					number = award.getNo9();
				else if (i == 9)
					number = award.getNo10();
				else if (i == 10)
					number = award.getNo11();

				for (Detail d : ds) {
					if (d.getNumber() == number) {
						result_ds.add(d);
						remove_ds.add(d);
					}
				}
				ds.removeAll(remove_ds);

			}

		}

		mv.addObject("_sum", _sum);
		mv.addObject("url", url);
		mv.addObject("ds", result_ds);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	@RequestMapping({ "/dtroom.do" })
	public ModelAndView dtroom(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {

//		if (!super.isLogin()) {
//			return new ModelAndView("redirect:/login.do");
//		}

		String type = request.getParameter("type");
		String qiname = request.getParameter("qiname");
		String url = "testroom.do";
//		if (type.equals("_last")) {// 上一期详情
//			url = "testroom.do";
//
//		} else if (type.equals("_now")) {// 当前开奖
//			url = "testroom.do";
//
//		} else if (type.equals("_canyu")) {// 我的参与
//			url = "canyu.do";
//
//		} else if (type.equals("_childcanyu")) {// 我的参与
//			url = "childcanyu.do";
//
//		}

		if (qiname == null || qiname.equals("")) {
			return new ModelAndView("redirect:/" + url);
		}

		if (type.equals("_last")) {// 上一期详情
			// 计算上一期
			qiname = CommUtil.getLastQi(qiname);
		}

		// String userid = super.getLoginUser().getUser_id();
//		String time = "" + System.currentTimeMillis();
//		httpSession.setAttribute("localtime", time);
		ModelAndView mv = new JModelAndView("pos/front/dtroom", 0, request, response);
//		long rid = 1;
//		try {
//			rid = Long.parseLong(roomid);
//		} catch (Exception e) {
//			rid = 1;
//		}

		TestDetailExample meExamplee = new TestDetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(10000);
		meExamplee.setOrderByClause("award desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));

		TestDetailExample.Criteria criteria = meExamplee.createCriteria();
		criteria.andQinameEqual(qiname);
		// criteria.andRoomIdEqual(rid);
		Pagination pList = this.testdetailService.getObjectListWithPage(meExamplee);

		// mv.addObject("roomid", roomid);
		mv.addObject("qiname", qiname);

		List<TestDetail> ds = (List<TestDetail>) pList.getList();

		List<TestDetail> result_ds = new ArrayList<TestDetail>();

		Integer _sum = 0;
		for (TestDetail d : ds) {
			_sum = _sum + d.getAmount();
		}

		Award award = awardService.getByName(qiname);
		if (award != null) {
			mv.addObject("kj", 1);
			mv.addObject("award", award);

			for (int i = 0; i < 11; i++) {
				List<TestDetail> remove_ds = new ArrayList<TestDetail>();
				int number = 0;
				if (i == 0)
					number = award.getNo1();
				else if (i == 1)
					number = award.getNo2();
				else if (i == 2)
					number = award.getNo3();
				else if (i == 3)
					number = award.getNo4();
				else if (i == 4)
					number = award.getNo5();
				else if (i == 5)
					number = award.getNo6();
				else if (i == 6)
					number = award.getNo7();
				else if (i == 7)
					number = award.getNo8();
				else if (i == 8)
					number = award.getNo9();
				else if (i == 9)
					number = award.getNo10();
				else if (i == 10)
					number = award.getNo11();

				for (TestDetail d : ds) {
					if (d.getNumber() == number) {
						result_ds.add(d);
						remove_ds.add(d);
					}
				}
				ds.removeAll(remove_ds);

			}

		}

		mv.addObject("_sum", _sum);
		mv.addObject("url", url);
		mv.addObject("ds", result_ds);

		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);

		return mv;
	}

	/**
	 * 统计每个号码
	 * 
	 * @param ds
	 * @param myuserid
	 * @return
	 */
	private List<SumDetail> getSumDetailFromTestDetail(List<TestDetail> ds, String myuserid) {

		List<SumDetail> sdetail = new ArrayList<SumDetail>();
		sumAmount = 0;
		for (int i = 1; i <= 11; i++) {
			int count = 0;
			int sumamount = 0;
			int myamount = 0;
			int maxamount = 0;
			List<TestDetail> temp_ds = new ArrayList<TestDetail>();
			for (TestDetail d : ds) {
				if (i == d.getNumber()) {
					count++;
					sumamount = sumamount + d.getAmount();

					if (d.getAmount() > maxamount) {
						maxamount = d.getAmount();
					}
					if (d.getUserid().equals(myuserid)) {
						myamount = d.getAmount();
					}
					temp_ds.add(d);
				}
			}

			ds.removeAll(temp_ds);
			sumAmount = sumAmount + sumamount;
			SumDetail sumDetail = new SumDetail(i, count, sumamount, maxamount, myamount);
			sdetail.add(sumDetail);
		}
		return sdetail;
	}

	/**
	 * 统计每个号码
	 * 
	 * @param ds
	 * @param myuserid
	 * @return
	 */
	private List<SumDetail> getSumDetailFromDetail(List<Detail> ds, String myuserid) {

		List<SumDetail> sdetail = new ArrayList<SumDetail>();
		sumAmount = 0;
		for (int i = 1; i <= 11; i++) {
			int count = 0;
			int sumamount = 0;
			int myamount = 0;
			int maxamount = 0;
			List<Detail> temp_ds = new ArrayList<Detail>();
			for (Detail d : ds) {
				if (i == d.getNumber()) {
					count++;
					sumamount = sumamount + d.getAmount();

					if (d.getAmount() > maxamount) {
						maxamount = d.getAmount();
					}
					if (d.getUserid().equals(myuserid)) {
						myamount = d.getAmount();
					}
					temp_ds.add(d);
				}
			}

			ds.removeAll(temp_ds);
			sumAmount = sumAmount + sumamount;
			SumDetail sumDetail = new SumDetail(i, count, sumamount, maxamount, myamount);
			sdetail.add(sumDetail);
		}
		return sdetail;
	}

}
