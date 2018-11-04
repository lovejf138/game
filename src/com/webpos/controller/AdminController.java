
package com.webpos.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.webpos.common.GlobalConstant;
import com.webpos.dao.SystemMapper;
import com.webpos.entity.Account;
import com.webpos.entity.AccountExample;
import com.webpos.entity.Admin;
import com.webpos.entity.Award;
import com.webpos.entity.CaiFan;
import com.webpos.entity.DSystem;
import com.webpos.entity.DetailExample;
import com.webpos.entity.Goods;
import com.webpos.entity.GoodsExample;
import com.webpos.entity.MessageExample;
import com.webpos.entity.User;
import com.webpos.entity.UserExample;
import com.webpos.service.AccountService;
import com.webpos.service.AdminService;
import com.webpos.service.AwardService;
import com.webpos.service.DetailService;
import com.webpos.service.GoodsService;
import com.webpos.service.MessageService;
import com.webpos.service.SystemService;
import com.webpos.service.UserService;
import com.webpos.tools.CommUtil;
import com.webpos.tools.JModelAndView;
import com.webpos.tools.Pagination;

@Controller
public class AdminController extends ApiWebABaseController {
	private Logger log = Logger.getLogger(getClass());
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DetailService detailService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private AwardService awardService;
	@Autowired
	private GoodsService goodsService;

	@Resource
	private SystemMapper systemDao;

	/**
	 * 商品下架
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/goods_jia.do" })
	@ResponseBody
	public String goods_jia(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "fail";
		if (!super.isAdminLogin()) {
			return "login_error";
		}
		
		try {
			Double add_price = Double.parseDouble(request.getParameter("add_price"));
			Double add_nowprice = Double.parseDouble(request.getParameter("add_nowprice"));
			int add_beishu = Integer.parseInt(request.getParameter("add_beishu"));
			String img = request.getParameter("add_img");
			String name = request.getParameter("add_name");

			if(add_beishu!=2&&add_beishu!=6&&add_beishu!=250) {
				return "倍数错误";
			}
			if(name==null||name.length()<=0) {
				return "商品名称不能为空";
			}
			
			Goods g = new Goods();
			g.setBeishu(add_beishu);
			g.setCtime(new Date());
			g.setImg(img);
			g.setName(name);
			g.setPrice(CommUtil.add(add_price, 0.0));
			g.setNowprice(CommUtil.add(add_nowprice, 0.0));
			
			this.goodsService.insert(g);
			
		}catch(Exception e) {
			return "有数据错误";
		}
		

		return "SUCCESS";
	}
	
	/**
	 * 商品下架
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/goods_xia.do" })
	@ResponseBody
	public String goods_xia(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "fail";
		if (!super.isAdminLogin()) {
			return "login_error";
		}
		Long id = Long.valueOf(Long.parseLong(request.getParameter("id")));

		Goods goods = this.goodsService.selectByPrimaryKey(id);
		if (goods == null) {
			return "商品不存在";
		}
		goods.setStatus("delete");
		this.goodsService.update(goods);

		return "SUCCESS";
	}

	@RequestMapping({ "/withdraw_list.do" })
	public ModelAndView withdraw_list(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		if (admin == null) {
			return new ModelAndView("pos/a_login");
		}
		String currentPage = request.getParameter("currentPage");

		ModelAndView mv = new JModelAndView("pos/withdraw_list", 0, request, response);
		String jc = request.getParameter("jc");
		String status = request.getParameter("status");
		String order = request.getParameter("order");

		String type = request.getParameter("type");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if ((type != null) && (type.equals("in"))) {
			type = "in";
			status = "success";
		} else {
			type = "withdraw";
		}
		String order_clause = "";
		if ((order == null) || (order == "")) {
			order_clause = "ctime desc";
			order = "ctime_desc";
		} else if (order.equals("ctime_asc")) {
			order_clause = "ctime asc";
		} else if (order.equals("imtoken_desc")) {
			order_clause = "all_eth  desc";
		} else if (order.equals("imtoken_asc")) {
			order_clause = "all_eth asc";
		} else if (order.equals("amount_asc")) {
			order_clause = "amount asc";
		} else if (order.equals("amount_desc")) {
			order_clause = "amount desc";
		} else {
			order_clause = "ctime desc";
			order = "ctime_desc";
		}
		AccountExample meExamplee = new AccountExample();
		meExamplee.clear();
		meExamplee.setPageSize(15);
		meExamplee.setOrderByClause(order_clause);
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		AccountExample.Criteria criteria = meExamplee.createCriteria();

		criteria.andTypeEqualTo(type);
		criteria.andStatusEqualTo(status);
		mv.addObject("status", status);
		mv.addObject("type", type);
		mv.addObject("order", order);
		mv.addObject("startDate", startDate);
		mv.addObject("endDate", endDate);
		if ((jc != null) && (!jc.equals(""))) {
			jc = jc.toLowerCase();
			criteria.andUserlikeTo(jc);
			mv.addObject("jc", jc);
		}
		if ((startDate != null) && (!startDate.equals(""))) {
			criteria.andDtAddtimeGreaterThanOrEqualTo(startDate);
		}
		if ((endDate != null) && (!endDate.equals(""))) {
			criteria.andDtAddtimeLessThanOrEqualTo(endDate);
		}
		Pagination pList = this.accountService.getObjectListWithPage(meExamplee);
		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

//	@RequestMapping({ "/play_list.do" })
//	public ModelAndView play_list(HttpServletRequest request, HttpSession httpSession, Model model,
//			HttpServletResponse response) {
//		Admin admin = (Admin) httpSession.getAttribute("admin");
//		if (admin == null) {
//			return new ModelAndView("pos/a_login");
//		}
//		String currentPage = request.getParameter("currentPage");
//
//		ModelAndView mv = new JModelAndView("pos/play_list", 0, request, response);
//		String jc = request.getParameter("jc");
//		String startDate = request.getParameter("startDate");
//		String endDate = request.getParameter("endDate");
//
//		DetailsExample meExamplee = new DetailsExample();
//		meExamplee.clear();
//		meExamplee.setPageSize(15);
//		meExamplee.setOrderByClause("ctime desc");
//		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));
//
//		DetailsExample.Criteria criteria = meExamplee.createCriteria();
//
//		List<String> types = new ArrayList();
//		types.add("join");
//		types.add("win");
//		criteria.andTypeIn(types);
//		mv.addObject("startDate", startDate);
//		mv.addObject("endDate", endDate);
//		if ((jc != null) && (!jc.equals(""))) {
//			jc = jc.toLowerCase();
//			criteria.andUserIdlike(jc);
//			mv.addObject("jc", jc);
//		}
//		if ((startDate != null) && (!startDate.equals(""))) {
//			criteria.andDtAddtimeGreaterThanOrEqualTo(startDate);
//		}
//		if ((endDate != null) && (!endDate.equals(""))) {
//			criteria.andDtAddtimeLessThanOrEqualTo(endDate);
//		}
//		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);
//		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);
//
//		return mv;
//	}
	@RequestMapping({ "/goods_list.do" })
	public ModelAndView goods_list(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		if (admin == null) {
			return new ModelAndView("pos/a_login");
		}
		String currentPage = request.getParameter("currentPage");

		ModelAndView mv = new JModelAndView("pos/goods_list", 0, request, response);

		String beishu = request.getParameter("beishu");
		String status = request.getParameter("status");

		GoodsExample meExamplee = new GoodsExample();
		meExamplee.clear();
		meExamplee.setPageSize(15);
		meExamplee.setOrderByClause("nowprice asc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		GoodsExample.Criteria criteria = meExamplee.createCriteria();

		if ((beishu != null) && (!beishu.equals(""))) {
			criteria.andBeishuEqual(Integer.parseInt(beishu));
			mv.addObject("beishu", beishu);
		}
		if ((status != null) && (!status.equals(""))) {
			criteria.andStatusEqual(status);
			mv.addObject("status", status);
		}

		Pagination pList = this.goodsService.getObjectListWithPage(meExamplee);
		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/play_list.do" })
	public ModelAndView play_list(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		if (admin == null) {
			return new ModelAndView("pos/a_login");
		}
		String currentPage = request.getParameter("currentPage");

		ModelAndView mv = new JModelAndView("pos/play_list", 0, request, response);
		String jc = request.getParameter("jc");
		String qiname = request.getParameter("qiname");
		String roomid = request.getParameter("roomid");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		DetailExample meExamplee = new DetailExample();
		meExamplee.clear();
		meExamplee.setPageSize(15);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		DetailExample.Criteria criteria = meExamplee.createCriteria();

		mv.addObject("startDate", startDate);
		mv.addObject("endDate", endDate);
		if ((jc != null) && (!jc.equals(""))) {
			jc = jc.toLowerCase();
			criteria.andUserIdEqual(jc);
			mv.addObject("jc", jc);
		}
		if ((roomid != null) && (!roomid.equals(""))) {
			criteria.andRoomIdEqual(Long.parseLong(roomid));
			mv.addObject("roomid", roomid);
		}
		if ((qiname != null) && (!qiname.equals(""))) {
			criteria.andQinameEqual(qiname);
			mv.addObject("qiname", qiname);
		}
		if ((startDate != null) && (!startDate.equals(""))) {
			criteria.andDtAddtimeGreaterThanOrEqualTo(startDate);
		}
		if ((endDate != null) && (!endDate.equals(""))) {
			criteria.andDtAddtimeLessThanOrEqualTo(endDate);
		}
		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);
		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/users.do" })
	public ModelAndView users(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		if (admin == null) {
			return new ModelAndView("pos/a_login");
		}
		DSystem s = this.systemDao.selectAll();
		httpSession.setAttribute("dsystem", s);
		String currentPage = request.getParameter("currentPage");

		String order = request.getParameter("order");
		String type = request.getParameter("type");

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if ((type != null) && (type.equals("parent"))) {
			type = "parent";
		} else {
			type = "child";
		}
		String order_clause = "";
		if ((order == null) || (order == "")) {
			order_clause = "ctime desc";
			order = "ctime_desc";
		} else if (order.equals("ctime_asc")) {
			order_clause = "ctime asc";
		} else if (order.equals("imtoken_desc")) {
			order_clause = "all_eth  desc";
		} else if (order.equals("imtoken_asc")) {
			order_clause = "all_eth asc";
		} else if (order.equals("amount_asc")) {
			order_clause = "balance asc";
		} else if (order.equals("amount_desc")) {
			order_clause = "balance desc";
		} else if (order.equals("recharge_asc")) {
			order_clause = "recharge_sum asc";
		} else if (order.equals("recharge_desc")) {
			order_clause = "recharge_sum desc";
		} else if (order.equals("withdraw_asc")) {
			order_clause = "withdraw_sum asc";
		} else if (order.equals("withdraw_desc")) {
			order_clause = "withdraw_sum desc";
		} else if (order.equals("share_asc")) {
			order_clause = "child_sum asc";
		} else if (order.equals("share_desc")) {
			order_clause = "child_sum desc";
		} else if (order.equals("play_asc")) {
			order_clause = "play_sum asc";
		} else if (order.equals("play_desc")) {
			order_clause = "play_sum desc";
		} else {
			order_clause = "ctime desc";
			order = "ctime_desc";
		}
		ModelAndView mv = new JModelAndView("pos/user", 0, request, response);
		String jc = request.getParameter("jc");
		UserExample meExamplee = new UserExample();
		meExamplee.clear();
		meExamplee.setPageSize(15);
		meExamplee.setOrderByClause(order_clause);
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		UserExample.Criteria criteria = meExamplee.createCriteria();

		mv.addObject("order", order);
		mv.addObject("type", type);
		mv.addObject("startDate", startDate);
		mv.addObject("endDate", endDate);
		if ((jc != null) && (!jc.equals(""))) {
			jc = jc.toLowerCase();
			if (type.endsWith("parent")) {
				criteria.andParentLikeTo(jc);
			} else {
				criteria.andNameLikeTo(jc);
			}
			mv.addObject("jc", jc);
		}
		if ((startDate != null) && (!startDate.equals(""))) {
			criteria.andDtAddtimeGreaterThanOrEqualTo(startDate);
		}
		if ((endDate != null) && (!endDate.equals(""))) {
			criteria.andDtAddtimeLessThanOrEqualTo(endDate);
		}
		Pagination pList = this.userService.getObjectListWithPage(meExamplee);
		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/messages.do" })
	public ModelAndView messages(HttpServletRequest request, HttpSession httpSession, Model model,
			HttpServletResponse response) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		if (admin == null) {
			return new ModelAndView("pos/a_login");
		}

		String currentPage = request.getParameter("currentPage");

		String roomid = request.getParameter("roomid");

		ModelAndView mv = new JModelAndView("pos/message", 0, request, response);
		String jc = request.getParameter("jc");
		MessageExample meExamplee = new MessageExample();
		meExamplee.clear();
		meExamplee.setPageSize(15);
		meExamplee.setOrderByClause("ctime desc");
		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));

		MessageExample.Criteria criteria = meExamplee.createCriteria();

		if ((jc != null) && (!jc.equals(""))) {
			jc = jc.toLowerCase();
			criteria.andUserEqualTo(jc);

			mv.addObject("jc", jc);
		}

		if (roomid != null && !roomid.equals("")) {
			try {
				int room = Integer.parseInt(roomid);
				criteria.andRoomEqualTo(room);
			} catch (Exception e) {

			}
			mv.addObject("roomid", roomid);
		}

		Pagination pList = this.messageService.getObjectListWithPage(meExamplee);
		CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);

		return mv;
	}

	@RequestMapping({ "/xxxxxx.do" })
	public String login(HttpServletRequest request, HttpSession httpSession, Model model) {
		if (super.isAdminLogin()) {
			httpSession.setAttribute("admin", super.getAdminLogin());
		}
		return "pos/a_login";
	}

	@RequestMapping({ "/system_deal.do" })
	@ResponseBody
	public String system_deal(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "fail";
		if (!super.isAdminLogin()) {
			return "login_error";
		}
		String content = request.getParameter("content");
		String amount = request.getParameter("amount");
		String number = request.getParameter("number");

		DSystem ds = new DSystem();
		ds.setAccount(amount);
		ds.setContent(content);
		ds.setWithdraw_number(Integer.valueOf(Integer.parseInt(number)));
		this.systemService.updateAll(ds);

		return "SUCCESS";
	}

	@RequestMapping({ "/bj_machine.do" })
	@ResponseBody
	public String bj_machine(HttpServletRequest request, HttpSession httpSession, Model model) {
		if (!super.isAdminLogin()) {
			return "请登陆";
		}
		String user_id = request.getParameter("user_id");
		String is_machine = request.getParameter("is_machine");
		int machine = 0;
		try {
			machine = Integer.parseInt(is_machine);
		} catch (Exception e) {
			machine = 0;
		}
		User u = this.userService.selectByUserId(user_id);
		u.setIs_machine(Integer.valueOf(machine));
		this.userService.updateByPrimaryKeySelective(u);

		return "SUCCESS";
	}

	@RequestMapping({ "/in.do" })
	@ResponseBody
	public String in(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "fail";
		if (!super.isAdminLogin()) {
			return "login_error";
		}
		String user_id = request.getParameter("user_id");
		String amount = request.getParameter("amount");
		if ((user_id == null) || (user_id.trim().length() <= 3)) {
			return "user_error";
		}
		double f_amount = 0.0D;
		try {
			f_amount = Double.parseDouble(amount);
		} catch (Exception e) {
			f_amount = 0.0D;
		}
		if (f_amount < 0.01D) {
			return "amount_error";
		}
		r = this.userService.recharge(user_id, f_amount);

		return r;
	}

	@RequestMapping({ "/draw_deal.do" })
	@ResponseBody
	public String draw_deal(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "fail";
		if (!super.isAdminLogin()) {
			return "login_error";
		}
		Long id = Long.valueOf(Long.parseLong(request.getParameter("id")));

		Account account = this.accountService.getById(id);
		if (account == null) {
			return "account_error";
		}
		account.setStatus("success");
		this.accountService.updateByPrimaryKeySelective(account);

		return "SUCCESS";
	}

	@RequestMapping({ "/edit_user.do" })
	@ResponseBody
	public String edit_user(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "fail";
		if (!super.isAdminLogin()) {
			return "login_error";
		}
		String user_id = request.getParameter("user_id");
		String amount = request.getParameter("amount");
		String rate = request.getParameter("rate");
		if ((user_id == null) || (user_id.trim().length() <= 3)) {
			return "user_error";
		}
		double f_amount = 0.0D;
		try {
			f_amount = Double.parseDouble(amount);
		} catch (Exception e) {
			f_amount = 0.0D;
		}
		if (f_amount < 0.0D) {
			return "amount_error";
		}
		int i_rate = 47;
		try {
			i_rate = Integer.parseInt(rate);
		} catch (Exception e) {
			i_rate = 47;
		}
		if (i_rate < 0) {
			i_rate = 0;
		} else if (i_rate > 100) {
			i_rate = 100;
		}
		User user_last = this.userService.selectByUserId(user_id);
		if (user_last == null) {
			return "user_error";
		}
		double final_amount = CommUtil.add("0", amount);
		user_last.setAll_eth(Double.valueOf(final_amount));
		// user_last.setWin_rate(Integer.valueOf(i_rate));
		this.userService.updateByPrimaryKeySelective(user_last);

		return "SUCCESS";
	}

	@RequestMapping({ "/do_xqsdf.do" })
	public String doxxxxxx(HttpServletRequest request, HttpSession httpSession, Model model) {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		model.addAttribute("name", name);
		if ((name != null) && (pass != null)) {
			if ((!name.equals("")) && (!pass.equals(""))) {
				Admin admin = this.adminService.selectByName(name);
				if (admin != null) {
					if (pass.endsWith(admin.getPass())) {
						httpSession.setAttribute("admin", admin);
						return "redirect:/users.do";
					}
					request.setAttribute("shid", "商户或密码错误");
					return "pos/a_login";
				}
				request.setAttribute("shid", "商户或密码错误");
				return "pos/a_login";
			}
			request.setAttribute("shid", "商户或密码错误");
			return "pos/a_login";
		}
		request.setAttribute("shid", "商户或密码错误");
		return "pos/a_login";
	}

	@RequestMapping({ "/dologout.do" })
	public String logout(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Cookie[] cookies = request.getCookies();
		String role = "";
		if (cookies != null) {
			Cookie[] arrayOfCookie1;
			int j = (arrayOfCookie1 = cookies).length;
			for (int i = 0; i < j; i++) {
				Cookie cookie = arrayOfCookie1[i];
				if ("adminRole".equals(cookie.getName())) {
					role = cookie.getValue();
					cookie.setValue(null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		httpSession.removeAttribute("admin");

		return "pos/a_login";
	}

	@RequestMapping({ "/kaijiang.do" })
	@ResponseBody
	public String kaijiang(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "未知错误";
		if (!super.isAdminLogin()) {
			return "登录超时";
		}
		String name = request.getParameter("name");
		String no1 = request.getParameter("no1");
		String no2 = request.getParameter("no2");
		String no3 = request.getParameter("no3");
		String no4 = request.getParameter("no4");
		String no5 = request.getParameter("no5");
		int int_no1 = 0, int_no2 = 0, int_no3 = 0, int_no4 = 0, int_no5 = 0;
		if (name == null || name.length() != 8) {
			return "期号错误";
		}

		try {
			int_no1 = Integer.parseInt(no1);
			int_no2 = Integer.parseInt(no2);
			int_no3 = Integer.parseInt(no3);
			int_no4 = Integer.parseInt(no4);
			int_no5 = Integer.parseInt(no5);
		} catch (Exception e) {
			return "开奖号码有错";
		}

		if (int_no1 == int_no2 || int_no1 == int_no3 || int_no1 == int_no4 || int_no1 == int_no5 || int_no2 == int_no3
				|| int_no2 == int_no4 || int_no2 == int_no5 || int_no3 == int_no4 || int_no3 == int_no5
				|| int_no4 == int_no5) {
			return "开奖号码有重复";
		}

		if (int_no1 < 1 || int_no1 > 11 || int_no2 < 1 || int_no2 > 11 || int_no3 < 1 || int_no3 > 11 || int_no4 < 1
				|| int_no4 > 11 || int_no5 < 1 || int_no5 > 11) {
			return "开奖号码有错误";
		}

		/**
		 * 判断期号是否存在
		 */
		Award last_a = awardService.getByName(name);
		if (last_a != null) {
			return "期号已存在";
		}

		int[] finals = CommUtil.getFinalNumberFrom5(int_no1, int_no2, int_no3, int_no4, int_no5);

		// String re = awardService.kaijiang(finals, name,true);
		String re = awardService.onekaijiang(finals, name);
		// awardService.testkaijiang(finals, name,true);
		return re;
	}

	@RequestMapping({ "/getkaijiang.do" })
	@ResponseBody
	public String sendkaijiang(HttpServletRequest request, HttpSession httpSession, Model model) {
		String r = "未知错误";
		if (!super.isAdminLogin()) {
			return "登录超时";
		}
		String time = request.getParameter("time");
		String maxqi = request.getParameter("maxqi");
		getDataFromOnlineByDate(time, Integer.parseInt(maxqi));
		return "SUCCESS";
	}

	private boolean getDataFromOnlineByDate(String date, int max) {
		boolean r = true;

		List<CaiFan> CaiFans = new ArrayList<CaiFan>();

		try {

			String resString = com.webpos.tools.HttpRequest.sendGet(
					"http://e.apiplus.net/daily.do?token=td8bb600ad2eb3d7ak&code=gd11x5&format=json&date=" + date,
					null);
			// System.out.println("resString:" + resString);

			JSONObject os = new JSONObject(resString);
			if (os != null) {
				int rows = os.getInt("rows");
				if (rows > 0) {
					org.json.JSONArray arrs = os.getJSONArray("data");
					for (int i = 0; i < arrs.length(); i++) {
						JSONObject o = arrs.getJSONObject(i);
						CaiFan c = new CaiFan();
						c.setCode(o.getString("opencode"));
						c.setQiname(o.getString("expect"));
						CaiFans.add(c);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exception:" + e.getMessage().toString());
			r = false;
		}

		try {
			int i = 0;
			for (CaiFan c : CaiFans) {
				if (i > 30)
					break;
				i++;
				String name = c.getQiname();
				String qi = name.substring(2, name.length());
				int shu = Integer.parseInt(name.substring(name.length() - 2, name.length()));
				String[] nos5 = c.getCode().split(",");
				if (shu < max) {
					// System.out.println("qi:"+qi);
					Award last_a = awardService.getByName(qi);
					if (last_a == null) {

						int int_no1 = 0, int_no2 = 0, int_no3 = 0, int_no4 = 0, int_no5 = 0;

						try {
							int_no1 = Integer.parseInt(nos5[0]);
							int_no2 = Integer.parseInt(nos5[1]);
							int_no3 = Integer.parseInt(nos5[2]);
							int_no4 = Integer.parseInt(nos5[3]);
							int_no5 = Integer.parseInt(nos5[4]);
						} catch (Exception e) {
							continue;
						}

						int[] finals = CommUtil.getFinalNumberFrom5(int_no1, int_no2, int_no3, int_no4, int_no5);

						awardService.kaijiang(finals, qi, false);
					}
				}

			}
		} catch (Exception e) {
			System.out.println("exception:" + e.getMessage().toString());
			r = false;
		}

		return r;

	}
	

//
//	/**
//	 * 连接websocket，并发送开奖信息
//	 * 
//	 * @param url
//	 * @param qiname
//	 * @param finals
//	 * @return
//	 */
//	public String sendMe(String url, String qiname, int[] finals) {
//
//		try {
//			MsgWebSocketClient client = new MsgWebSocketClient(url);
//			client.send("测试websocket。。。");
//		} catch (Exception e) {
//			return "error:" + e.toString();
//		}
//		return "success";
//	}
	
	
	@RequestMapping({ "/uploadimg.do" })
	@ResponseBody
	public String uploadimg(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
		if(!file.isEmpty()){
			// 获得原始文件名
			String fileName=file.getOriginalFilename();
			// 重命名文件
			String newfileName = new Date().getTime()+String.valueOf(fileName);
			String pathRoot=request.getSession().getServletContext().getRealPath("");
			String path = "/upload/"+newfileName;
			//System.out.println("path:"+pathRoot+path);
			File tempFile= new File(pathRoot+path);
				if(!tempFile.getParentFile().exists()){
					tempFile.getParentFile().mkdir();
				}
				
				if (!tempFile.exists()) {
					tempFile.mkdir();
					}
				try {
					file.transferTo(tempFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ""+newfileName;
		}
		return "fail";
	}

}
