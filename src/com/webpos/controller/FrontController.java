package com.webpos.controller;import java.util.List;import javax.annotation.Resource;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.servlet.ModelAndView;import com.webpos.dao.SystemMapper;import com.webpos.entity.AccountExample;import com.webpos.entity.Award;import com.webpos.entity.Detail;import com.webpos.entity.DetailExample;import com.webpos.entity.Room;import com.webpos.entity.RoomExample;import com.webpos.entity.User;import com.webpos.service.AccountService;import com.webpos.service.AwardService;import com.webpos.service.DetailService;import com.webpos.service.RoomService;import com.webpos.service.UserService;import com.webpos.tools.CommUtil;import com.webpos.tools.JModelAndView;import com.webpos.tools.Md5Encrypt;import com.webpos.tools.Pagination;@Controllerpublic class FrontController extends ApiWebABaseController {	private Logger log = Logger.getLogger(getClass());	@Autowired	private AccountService accountService;	@Autowired	private RoomService roomService;	@Autowired	private UserService userService;	@Autowired	private DetailService detailService;	@Autowired	private AwardService awardService;	@Resource	private SystemMapper systemDao;	@RequestMapping({ "/login.do" })	public ModelAndView login(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {				String parent = request.getParameter("parent");				if(parent==null||parent.equals("")) {			parent = (String) httpSession.getAttribute("parent");		}		String time = "" + System.currentTimeMillis();		httpSession.setAttribute("localtime", time);		httpSession.setAttribute("parent", parent);		ModelAndView mv = new JModelAndView("pos/front/login", 0, request, response);		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}	@RequestMapping({ "/guize.do" })	public ModelAndView guize(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {						ModelAndView mv = new JModelAndView("pos/front/guize", 0, request, response);				CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}		@RequestMapping({ "/index.do" })	public ModelAndView index(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {//		if (!super.isLogin()) {//			return new ModelAndView("redirect:/login.do");//		}		String parent = request.getParameter("parent");		httpSession.setAttribute("parent", parent);				Award award = awardService.getLast();		String nextname = CommUtil.getNextQi(award.getName());					RoomExample meExamplee = new RoomExample();		meExamplee.clear();		meExamplee.setPageSize(10000);		meExamplee.setOrderByClause("id asc");		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));				Pagination pList = this.roomService.getObjectListWithPage(meExamplee);				ModelAndView mv = new JModelAndView("pos/front/index", 0, request, response);		int nextsecond = CommUtil.getSecondFromQi(nextname);		mv.addObject("award", award);		mv.addObject("rooms", pList.getList());		mv.addObject("nextname", nextname);		mv.addObject("nextsecond", nextsecond);		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}		@RequestMapping({ "/gerenzhongxin.do" })	public ModelAndView gerenzhongxin(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {		if (!super.isLogin()) {			return new ModelAndView("redirect:/login.do");		}		User user = userService.selectByUserId(super.getLoginUser().getUser_id());					ModelAndView mv = new JModelAndView("pos/front/gerenzhongxin", 0, request, response);				mv.addObject("user", user);				CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}		@RequestMapping({ "/chongzhimingxi.do" })	public ModelAndView chongzhimingxi(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {		if (!super.isLogin()) {			return new ModelAndView("redirect:/login.do");		}		//User user = userService.selectByUserId(super.getLoginUser().getUser_id());					ModelAndView mv = new JModelAndView("pos/front/chongzhimingxi", 0, request, response);		AccountExample meExamplee = new AccountExample();		meExamplee.clear();		meExamplee.setPageSize(30);		meExamplee.setOrderByClause("ctime desc");		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));		AccountExample.Criteria criteria = meExamplee.createCriteria();		criteria.andUserEqualTo(super.getLoginUser().getUser_id());		criteria.andTypeEqualTo("in");				Pagination pList = this.accountService.getObjectListWithPage(meExamplee);				CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);		return mv;	}		@RequestMapping({ "/tixianmingxi.do" })	public ModelAndView tixianmingxi(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {		if (!super.isLogin()) {			return new ModelAndView("redirect:/login.do");		}		//User user = userService.selectByUserId(super.getLoginUser().getUser_id());					ModelAndView mv = new JModelAndView("pos/front/tixianmingxi", 0, request, response);		AccountExample meExamplee = new AccountExample();		meExamplee.clear();		meExamplee.setPageSize(30);		meExamplee.setOrderByClause("ctime desc");		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));		AccountExample.Criteria criteria = meExamplee.createCriteria();		criteria.andUserEqualTo(super.getLoginUser().getUser_id());		criteria.andTypeEqualTo("withdraw");				Pagination pList = this.accountService.getObjectListWithPage(meExamplee);				CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);		return mv;	}	/**	 * 开奖信息页面	 * 	 * @param request	 * @param httpSession	 * @param model	 * @param response	 * @return	 */	@RequestMapping({ "/kj.do" })	public ModelAndView kj(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {		if (!super.isLogin()) {			return new ModelAndView("redirect:/login.do");		}		Award award = awardService.getLast();		List<Award> awards = awardService.getLastList();		String nextname = CommUtil.getNextQi(award.getName());		ModelAndView mv = new JModelAndView("pos/front/kaijiang", 0, request, response);		int nextsecond = CommUtil.getSecondFromQi(nextname);		mv.addObject("award", award);		mv.addObject("awards", awards);		mv.addObject("nextname", nextname);		mv.addObject("nextsecond", nextsecond);		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}		/**	 * 排行信息页面	 * 	 * @param request	 * @param httpSession	 * @param model	 * @param response	 * @return	 */	@RequestMapping({ "/paihang.do" })	public ModelAndView paihang(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {		if (!super.isLogin()) {			return new ModelAndView("redirect:/login.do");		}				List<User> awards = userService.getPaihangaward();		List<User> plays = userService.getPaihangplay();				ModelAndView mv = new JModelAndView("pos/front/paihang", 0, request, response);						User award1 = awards.get(0);		User award2 = awards.get(1);		User award3 = awards.get(2);				User play1 = plays.get(0);		User play2 = plays.get(1);		User play3 = plays.get(2);		mv.addObject("award1", award1);		mv.addObject("award2", award2);		mv.addObject("award3", award3);		mv.addObject("play1", play1);		mv.addObject("play2", play2);		mv.addObject("play3", play3);				mv.addObject("awards", awards);		mv.addObject("plays", plays);		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}	@RequestMapping({ "/join.do" })	@ResponseBody	public BuyReturnData join(HttpServletRequest request, HttpSession httpSession, Model model) {		BuyReturnData result = new BuyReturnData();		if (!super.isLogin()) {			result.setResult("FAIL");			result.setDesc("请登录");			return result;		}				User user = super.getLoginUser();		//		if (CommUtil.ifClose()) {//			result.setResult("FAIL");//			result.setDesc("error_service_time");//			return result;//		}		/**********判断参与金额start*************/		String amount = request.getParameter("amount");						double f_amount = 0.0D;		try {			f_amount = Double.parseDouble(amount);		} catch (Exception e) {			f_amount = 0.0D;		}		if (f_amount < 0.005D) {			result.setResult("FAIL");			result.setDesc("抱歉，最少参与数量0.005");			return result;		}		/**********判断参与金额end*************/			   /***************判断余额start********************/		 User user_last = this.userService.selectByParent(user.getId_md5());		    if (user_last == null)		    {		      result.setResult("FAIL");		      result.setDesc("请登录");		      return result;		    }		    double b_amount = -1.0D;		    try		    {		      b_amount = Double.parseDouble(user_last.getBalance());		    }		    catch (Exception e)		    {		      b_amount = -1.0D;		    }		    if (f_amount > b_amount)		    {		      result.setResult("FAIL");		      result.setDesc("余额不足！！！");		      return result;		    }		/***************判断余额end********************/		    		Award award = awardService.getLast();		String nextname = CommUtil.getNextQi(award.getName());				/***********判断是否可以下注及获得下注期号start**************/		int nextsecond = CommUtil.getSecondFromQi(nextname);		if(nextsecond<=0) {			result.setResult("FAIL");			result.setDesc("抱歉！正在等待上一期开奖，请稍后下注");			return result;		}		/***********判断是否可以下注及获得下注期号end*****************/				/*********判断房间是否满11个人start************/		String roomid = request.getParameter("roomid");		long rid = 1;		try {			rid = Long.parseLong(roomid);		} catch (Exception e) {			rid = 1;		}		Room room = roomService.selectByPrimaryKey(rid);		if(room.getProgress()>=11)		{			result.setResult("FAIL");			result.setDesc("抱歉！当前房间下注已满11人，请等下一期或前往其他房间");			return result;		}		/*********判断房间是否满11个人end************/								/******************判断签名start***********************/					String sign = request.getParameter("sign");						String locatime = (String) httpSession.getAttribute("localtime");		String self_ = Md5Encrypt.md5(amount + locatime + "!#@#Qsaswe@#./1!");		//System.out.println("self_:"+self_+",sign:"+sign+",localtime:"+locatime+",amount:"+amount);		if (!self_.equals(sign)) {			result.setResult("FAIL");			result.setDesc("非法请求");			return result;		}		/******************判断签名end***********************/				String r = userService.join(rid, user.getUser_id(),user.getParent(), user.getId_short(), nextname, f_amount);				if(r.equals("SUCCESS")) {			result.setResult("SUCCESS");		}else {			result.setResult("FAIL");			result.setDesc(r);		}						return result;	}	@RequestMapping({ "/room.do" })	public ModelAndView room(HttpServletRequest request, HttpSession httpSession, Model model,			HttpServletResponse response) {		if (!super.isLogin()) {			return new ModelAndView("redirect:/login.do");		}		String roomid = request.getParameter("roomid");		String userid = super.getLoginUser().getUser_id();		String time = "" + System.currentTimeMillis();		httpSession.setAttribute("localtime", time);		ModelAndView mv = new JModelAndView("pos/front/room", 0, request, response);		long rid = 1;		try {			rid = Long.parseLong(roomid);		} catch (Exception e) {			rid = 1;		}		Room room = roomService.selectByPrimaryKey(rid);		mv.addObject("room", room);		mv.addObject("userid", "" + userid);		Award award = awardService.getLast();		String nextname = CommUtil.getNextQi(award.getName());		int nextsecond = CommUtil.getSecondFromQi(nextname);		// mv.addObject("award",award);		mv.addObject("nextname", nextname);		mv.addObject("nextsecond", nextsecond);		DetailExample meExamplee = new DetailExample();		meExamplee.clear();		meExamplee.setPageSize(11);		meExamplee.setOrderByClause("number desc");		meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(1))));		DetailExample.Criteria criteria = meExamplee.createCriteria();		criteria.andQinameEqual(nextname);		criteria.andRoomIdEqual(room.getId());		Pagination pList = this.detailService.getObjectListWithPage(meExamplee);		List<Detail> ds = (List<Detail>) pList.getList();//		int detailsize=0;//		if(ds==null||ds.size()<=0) {//			detailsize=0;//		}else {//			detailsize=1;//		}//		mv.addObject("detailsize", detailsize);		mv.addObject("details", ds);		CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);		return mv;	}}