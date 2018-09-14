package com.webpos.controller;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Locale;import javax.annotation.Resource;import javax.servlet.http.Cookie;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.servlet.ModelAndView;import org.springframework.web.servlet.support.RequestContext;import com.alibaba.fastjson.JSON;import com.api.utils.GeetestConfig;import com.api.utils.GeetestLib;import com.webpos.dao.SystemMapper;import com.webpos.entity.DSystem;import com.webpos.entity.DetailExample;import com.webpos.entity.DetailsExample;import com.webpos.entity.User;import com.webpos.entity.UserExample;import com.webpos.service.AccountService;import com.webpos.service.DetailsService;import com.webpos.service.UserService;import com.webpos.tools.CommUtil;import com.webpos.tools.Gift;import com.webpos.tools.JModelAndView;import com.webpos.tools.Md5Encrypt;import com.webpos.tools.Pagination;@Controllerpublic class UserController  extends ApiWebABaseController{  private Logger log = Logger.getLogger(getClass());  @Autowired  private UserService userService;  @Autowired  private AccountService accountService;  @Autowired  private DetailsService detailService;  @Resource  private SystemMapper systemDao;    @RequestMapping({"/recharge_page.do"})  public ModelAndView recharge_page(HttpServletRequest request, HttpSession httpSession, Model model, HttpServletResponse response)  {    ModelAndView mv = new JModelAndView("pos/personal_list", 0, request, response);    mv.addObject("type", "recharge");    mv.addObject("login_user", super.getLoginUser());        CommUtil.addIPageList2ModelAndView1("", "", "", null, mv);        return mv;  }    @RequestMapping({"/self_play_list.do"})  public ModelAndView self_play_list(HttpServletRequest request, HttpSession httpSession, Model model, HttpServletResponse response)  {    if (!super.isLogin()) {      return new ModelAndView("pos/login");    }    User user = super.getLoginUser();    User user_last = this.userService.selectByParent(user.getId_md5());        String currentPage = request.getParameter("currentPage");        ModelAndView mv = new JModelAndView("pos/personal_list", 0, request, response);        DetailsExample meExamplee = new DetailsExample();    meExamplee.clear();    meExamplee.setPageSize(15);    meExamplee.setOrderByClause("ctime desc");    meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));        DetailsExample.Criteria criteria = meExamplee.createCriteria();        List<String> types = new ArrayList();    types.add("join");    types.add("win");    criteria.andTypeIn(types);    criteria.andUserIdEqual(user_last.getUser_id());    mv.addObject("type", "self_play");    mv.addObject("login_user", user);        Pagination pList = this.detailService.getObjectListWithPage(meExamplee);    CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);        return mv;  }    @RequestMapping({"/share_play_list.do"})  public ModelAndView share_play_list(HttpServletRequest request, HttpSession httpSession, Model model, HttpServletResponse response)  {    if (!super.isLogin()) {      return new ModelAndView("pos/login");    }    User user = super.getLoginUser();    User user_last = this.userService.selectByParent(user.getId_md5());        String currentPage = request.getParameter("currentPage");        ModelAndView mv = new JModelAndView("pos/personal_list", 0, request, response);        DetailsExample meExamplee = new DetailsExample();    meExamplee.clear();    meExamplee.setPageSize(15);    meExamplee.setOrderByClause("ctime desc");    meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));        DetailsExample.Criteria criteria = meExamplee.createCriteria();        List<String> types = new ArrayList();    types.add("join");    criteria.andTypeIn(types);    criteria.andParentIdEqual(user_last.getUser_id());    mv.addObject("type", "share_play");    mv.addObject("login_user", user);        Pagination pList = this.detailService.getObjectListWithPage(meExamplee);    CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);        return mv;  }    @RequestMapping({"/share_list.do"})  public ModelAndView share_list(HttpServletRequest request, HttpSession httpSession, Model model, HttpServletResponse response)  {    if (!super.isLogin()) {      return new ModelAndView("pos/login");    }    User user = super.getLoginUser();    User user_last = this.userService.selectByParent(user.getId_md5());        String currentPage = request.getParameter("currentPage");        ModelAndView mv = new JModelAndView("pos/personal_list", 0, request, response);        UserExample meExamplee = new UserExample();    meExamplee.clear();    meExamplee.setPageSize(15);    meExamplee.setOrderByClause("ctime desc");    meExamplee.setPageNo(Pagination.cpn(Integer.valueOf(CommUtil.null2Int(currentPage))));        UserExample.Criteria criteria = meExamplee.createCriteria();    criteria.andParentEqualTo(user_last.getUser_id());        mv.addObject("type", "share");    mv.addObject("login_user", user);    Pagination pList = this.userService.getObjectListWithPage(meExamplee);    CommUtil.addIPageList2ModelAndView1("", "", "", pList, mv);        return mv;  }    public static String getMessage(HttpServletRequest request, String key)  {    RequestContext requestContext = new RequestContext(request);    String value = requestContext.getMessage(key);    return value;  }    private Locale getLocale(String language)  {    Locale locale = new Locale("zh", "CN");    if ((language != null) && (language.equals("en"))) {      locale = new Locale("en", "US");    } else if (language.equals("ko")) {      locale = new Locale("ko", "KO");    } else if (language.equals("jp")) {      locale = new Locale("jp", "JP");    } else if (language.equals("ru")) {      locale = new Locale("ru", "RU");    } else if (language.equals("al")) {      locale = new Locale("al", "AL");    } else if (language.equals("zh")) {      locale = new Locale("zh", "ZH");    }    return locale;  }    @RequestMapping({"/gee_regist.do"})  @ResponseBody  public Object gee_regist(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model)  {    GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),       GeetestConfig.isnewfailback());        String resStr = "{}";        String userid = httpSession.getId();        HashMap<String, String> param = new HashMap();    param.put("user_id", userid);    param.put("client_type", "web");    param.put("ip_address", "127.0.0.1");        int gtServerStatus = gtSdk.preProcess(param);        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, Integer.valueOf(gtServerStatus));        request.getSession().setAttribute("userid", userid);        resStr = gtSdk.getResponseStr();        Object o = JSON.parse(resStr);        return o;  }    @RequestMapping({"/dologin.do"})  @ResponseBody  public LoginReturnData dologin(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model)  {    LoginReturnData lrd = new LoginReturnData();        User user = new User();    String loginname = request.getParameter("eth_address");    String parent = request.getParameter("parent");    String remark = request.getParameter("remark");    String pass = request.getParameter("pass");    String sign1 = request.getParameter("sign1");    String rp = "";        String localtime = (String)httpSession.getAttribute("localtime");        String self_sign_1 = Md5Encrypt.md5(loginname + "@" + localtime + "!#@#Qsaswe@#./1!");    if (loginname != null)    {      String lower_loginname = loginname.toLowerCase();      if (loginname.length() < 10)      {        lrd.setResult("请输入正确的以太坊地址");        return lrd;      }            if(pass==null||pass.length()<3) {    	  lrd.setResult("地址或密码错误");    	  return lrd;      }            if (!self_sign_1.equals(sign1))      {        lrd.setResult("非法请求");        return lrd;      }      user = this.userService.selectByUserId(lower_loginname);      if (user != null)      {//已存在    	      	if(pass.equals(user.getPass())) {    		httpSession.setAttribute("user", user);    		lrd.setResult("SUCCESS");            return lrd;    	}            	else {    		 lrd.setResult("地址或密码错误");    		 return lrd;    	}      }            if (loginname.length() < 30)      {        lrd.setResult("请输入正确的以太坊地址");        return lrd;      }      String challenge = request.getParameter("geetest_challenge");      String validate = request.getParameter("geetest_validate");      String seccode = request.getParameter("geetest_seccode");      if ((challenge == null) || (validate == null) || (seccode == null) ||         (challenge == "") || (validate == "") || (seccode == ""))      {        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),           GeetestConfig.isnewfailback());                String resStr = "{}";                String userid = httpSession.getId();                HashMap<String, String> param = new HashMap();        param.put("user_id", lower_loginname);        param.put("client_type", "web");        param.put("ip_address", "127.0.0.1");                int gtServerStatus = gtSdk.preProcess(param);                request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, Integer.valueOf(gtServerStatus));                request.getSession().setAttribute("userid", userid);                resStr = gtSdk.getResponseStr();                Object o = JSON.parse(resStr);                lrd.setResult("need_verify");        lrd.setDesc(o);        return lrd;      }      String sign2 = request.getParameter("sign2");            String self_sign_2 = Md5Encrypt.md5(loginname + "@" + challenge + localtime + "!#@#Qsaswe@#./1!");      System.out.println("localtime:" + localtime + ",parent:" + parent + ",remark:" + remark + ",loginname:" + loginname + ",challenge:" + challenge +         ",validate:" + validate + ",seccode:" + validate + ",sign2:" + sign2 + ",self_sign2" + self_sign_2);      if (!self_sign_2.equals(sign2))      {        lrd.setResult("非法请求");        return lrd;      }      GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),         GeetestConfig.isnewfailback());            int gt_server_status_code = ((Integer)request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey)).intValue();            String userid = (String)request.getSession().getAttribute("userid");            HashMap<String, String> param = new HashMap();      param.put("user_id", lower_loginname);      param.put("client_type", "web");      param.put("ip_address", "127.0.0.1");            int gtResult = 0;      if (gt_server_status_code == 1) {        gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);      } else {        gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);      }      if (gtResult != 1)      {        lrd.setResult("验证码错误");        return lrd;      }      User new_user = this.userService.register(lower_loginname,pass, parent, remark);      if (new_user == null)      {        lrd.setResult("未知错误");        return lrd;      }      new_user.setParent("");      new_user.setAll_eth(Double.valueOf(0.0D));      httpSession.setAttribute("user", new_user);            lrd.setResult("SUCCESS");      return lrd;    }    lrd.setResult("请输入正确的以太坊地址");    return lrd;  }  //  @RequestMapping({"/activity.do"})//  public String activity(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model)//  {//    String lan = request.getParameter("lan");//    if ((lan == null) || (lan == ""))//    {//      String lan_1 = (String)httpSession.getAttribute("lan");//      if (lan_1 != null) {//        lan = lan_1;//      } else {//        lan = "zh";//      }//    }//    String parent = request.getParameter("parent");//   //    String time = ""+System.currentTimeMillis();//    return "redirect:x_" + time + ".do?lan=" + lan + "&parent=" + parent;//  }//  //  @RequestMapping({"/x_{id}.do"})//  public String x(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model)//  {//    String localtime = ""+System.currentTimeMillis();//    httpSession.setAttribute("localtime", localtime);//    //    String number = (String)httpSession.getAttribute("number");//    if ((number == null) || (number.equals("")))//    {//      number = "0.005";//      httpSession.setAttribute("number", number);//    }//    String lan = request.getParameter("lan");//    if ((lan == null) || (lan == ""))//    {//      String lan_1 = (String)httpSession.getAttribute("lan");//      if (lan_1 != null) {//        lan = lan_1;//      } else {//        lan = "zh";//      }//    }//    Locale newLocale = getLocale(lan);//    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);//    if (localeResolver == null) {//      throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");//    }//    localeResolver.setLocale(request, response, newLocale);//    //    httpSession.setAttribute("lan", lan);//    //    User user = new User();//    String parent = request.getParameter("parent");//    //    httpSession.setAttribute("parent", parent);//    if (super.isLogin())//    {//      User u1 = super.getLoginUser();//      User user_last = this.userService.selectByParent(u1.getId_md5());//      //      user_last.setParent("");//      //      user_last.setWin_rate(Integer.valueOf(0));//      user_last.setAll_eth(Double.valueOf(0.0D));//      //      httpSession.setAttribute("user", user_last);//      return "pos/login";//    }//    return "pos/login";//  }//  //  @RequestMapping({"/p.do"})//  public String login(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Model model)//  {//    return "redirect:https://www.ethereum.org/";//  }//    @RequestMapping({"/withdraw.do"})  @ResponseBody  public BuyReturnData withdraw(HttpServletRequest request, HttpSession httpSession, Model model)  {    DSystem system = this.systemDao.selectAll();    BuyReturnData result = new BuyReturnData();    if (CommUtil.ifClose())    {      result.setResult("FAIL");      result.setDesc("error_service_time");      return result;    }    if (!super.isLogin())    {      result.setResult("FAIL");      result.setDesc("error_login_first");      return result;    }    String amount = request.getParameter("amount");    String sign = request.getParameter("sign");        String locatime = (String)httpSession.getAttribute("localtime");        String self_ = Md5Encrypt.md5(amount + locatime + "!#@#Qsaswe@#./1!");        User user = super.getLoginUser();    User user_last = this.userService.selectByParent(user.getId_md5());    if (user_last == null)    {      result.setResult("FAIL");      result.setDesc("error_login_again");      return result;    }    if (!self_.equals(sign))    {      result.setResult("FAIL");      result.setDesc("error_sign");      return result;    }    double f_amount = 0.0D;    try    {      f_amount = Double.parseDouble(amount);    }    catch (Exception e)    {      f_amount = 0.0D;    }    if (f_amount < 0.1D)    {      result.setResult("FAIL");      result.setDesc("error_least_withdraw_number");      return result;    }    double b_amount = -1.0D;    try    {      b_amount = Double.parseDouble(user_last.getBalance());    }    catch (Exception e)    {      b_amount = -1.0D;    }    if (f_amount > b_amount)    {      result.setResult("FAIL");      result.setDesc("error_balance_not_enough");      return result;    }    if (system.getWithdraw_number().intValue() > 0)    {      double max_withdraw = CommUtil.div(user_last.getPlay_sum(), system.getWithdraw_number());      if (f_amount > max_withdraw)      {        result.setResult("FAIL_MAX_WITHDRAW");        result.setDesc(""+max_withdraw);        return result;      }    }    boolean deal_r = this.userService.withdraw(user_last.getUser_id(),""+f_amount);    if (deal_r)    {      result.setResult("SUCCESS");    }    else    {      result.setResult("FAIL");      result.setDesc("error_network");    }    return result;  }    public String getRemoteAddress(HttpServletRequest request)  {    String ip = request.getHeader("x-forwarded-for");    if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase("unknown"))) {      ip = request.getHeader("Proxy-Client-IP");    }    if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase("unknown"))) {      ip = request.getHeader("WL-Proxy-Client-IP");    }    if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase("unknown"))) {      ip = request.getRemoteAddr();    }    return ip;  }    @RequestMapping({"/start_buy.do"})  @ResponseBody  public BuyReturnData start_buy(HttpServletRequest request, HttpSession httpSession, Model model)  {    BuyReturnData result = new BuyReturnData();    if (CommUtil.ifClose())    {      result.setResult("FAIL");      result.setDesc("error_service_time");      return result;    }    Integer play_time = (Integer)httpSession.getAttribute("play_time");    if (play_time == null) {      play_time = Integer.valueOf(1);    } else {      play_time = Integer.valueOf(play_time.intValue() + 1);    }    httpSession.setAttribute("play_time", play_time);    if (!super.isLogin())    {      result.setResult("FAIL");      result.setDesc("error_login_first");      return result;    }    String amount = request.getParameter("amount");        httpSession.setAttribute("number", amount);        String sign = request.getParameter("sign");        String locatime = (String)httpSession.getAttribute("localtime");    String self_ = Md5Encrypt.md5(amount + locatime + "!#@#Qsaswe@#./1!");        User user = super.getLoginUser();    User user_last = this.userService.selectByParent(user.getId_md5());    if (user_last == null)    {      result.setResult("FAIL");      result.setDesc("error_login_again");      return result;    }    if (!self_.equals(sign))    {      result.setResult("FAIL");      result.setDesc("error_sign");      return result;    }    double f_amount = 0.0D;    try    {      f_amount = Double.parseDouble(amount);    }    catch (Exception e)    {      f_amount = 0.0D;    }    if (f_amount < 0.005D)    {      result.setResult("FAIL");      result.setDesc("error_least_join_number");      return result;    }    double b_amount = -1.0D;    try    {      b_amount = Double.parseDouble(user_last.getBalance());    }    catch (Exception e)    {      b_amount = -1.0D;    }    if (f_amount > b_amount)    {      result.setResult("FAIL");      result.setDesc("error_balance_not_enough");      return result;    }    Double realeth = user_last.getAll_eth();    if (realeth == null) {      realeth = Double.valueOf(0.0D);    }    Gift g = getGiftByStagegy(f_amount, b_amount, realeth, play_time, 50);        boolean b_f = true;    if (g.getId() % 2 != 0) {      b_f = false;    }    boolean deal_r = this.userService.buyeth(user_last.getUser_id(), ""+f_amount, b_f, g.getBeishu());    if (deal_r)    {      if (b_f)      {        result.setResult("SUCCESS_WIN");        result.setDesc(""+g.getId());      }      else      {        result.setResult("SUCCESS_FAIL");        result.setDesc(""+g.getId());      }    }    else    {      result.setResult("FAIL");      result.setDesc("error_network");    }    return result;  }    private Gift getGiftByStagegy(double f_amount, double b_amount, Double realeth, Integer play_time, Integer win_rate)  {    System.out.println("play_time:" + play_time + ",b_amount:" + b_amount + ",realeth:" + realeth + ",f_amount:" + f_amount);    Gift g = null;    if (f_amount >= 20.0D)    {      g = getGift(0);      return g;    }    g = getGift(win_rate.intValue());    return g;  }    @RequestMapping({"/logout.do"})  public String logout(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response)  {    User user = (User)httpSession.getAttribute("user");    Cookie[] cookies = request.getCookies();    String role = "";    if (cookies != null)    {      Cookie[] arrayOfCookie1;      int j = (arrayOfCookie1 = cookies).length;      for (int i = 0; i < j; i++)      {        Cookie cookie = arrayOfCookie1[i];        if ("userRole".equals(cookie.getName()))        {          role = cookie.getValue();          cookie.setValue(null);          cookie.setMaxAge(0);          response.addCookie(cookie);        }      }    }    httpSession.removeAttribute("user");        return "pos/login";  }    private Gift getGift(int user_win_rate)  {    Double z_ = Double.valueOf(0.2D * (user_win_rate / 100.0D));    Double f_ = Double.valueOf(0.2D * (100 - user_win_rate) / 100.0D);        Gift g1 = new Gift(1, "????????", f_.doubleValue(), 0.1D);    Gift g2 = new Gift(2, "??????", z_.doubleValue(), 1.6D);    Gift g3 = new Gift(3, "????????", f_.doubleValue(), 0.5D);    Gift g4 = new Gift(4, "??????", z_.doubleValue(), 2.0D);    Gift g5 = new Gift(5, "????????", f_.doubleValue(), 0.9D);    Gift g6 = new Gift(6, "??????", z_.doubleValue(), 1.8D);    Gift g7 = new Gift(7, "????????", f_.doubleValue(), 0.7D);    Gift g8 = new Gift(8, "??????", z_.doubleValue(), 1.4D);    Gift g9 = new Gift(9, "????????", f_.doubleValue(), 0.3D);    Gift g10 = new Gift(10, "??????", z_.doubleValue(), 1.2D);        List<Gift> list = new ArrayList();    list.add(g1);    list.add(g2);    list.add(g3);    list.add(g4);    list.add(g5);    list.add(g6);    list.add(g7);    list.add(g8);    list.add(g9);    list.add(g10);        //int index = DrawLotteryUtil.drawGift(list);    return (Gift)list.get(0);  }    public static void main(String[] args)  {    int user_win_rate = 47;        Double z_ = Double.valueOf(0.2D * (user_win_rate / 100.0D));    Double f_ = Double.valueOf(0.2D * (100 - user_win_rate) / 100.0D);        System.out.println("z_:" + z_ + ",f_:" + f_);        Gift g1 = new Gift(1, "????????", f_.doubleValue(), 0.1D);    Gift g2 = new Gift(2, "??????", z_.doubleValue(), 1.6D);    Gift g3 = new Gift(3, "????????", f_.doubleValue(), 0.5D);    Gift g4 = new Gift(4, "??????", z_.doubleValue(), 2.0D);    Gift g5 = new Gift(5, "????????", f_.doubleValue(), 0.9D);    Gift g6 = new Gift(6, "??????", z_.doubleValue(), 1.8D);    Gift g7 = new Gift(7, "????????", f_.doubleValue(), 0.7D);    Gift g8 = new Gift(8, "??????", z_.doubleValue(), 1.4D);    Gift g9 = new Gift(9, "????????", f_.doubleValue(), 0.3D);    Gift g10 = new Gift(10, "??????", z_.doubleValue(), 1.2D);        List<Gift> list = new ArrayList();    list.add(g1);list.add(g2);list.add(g3);list.add(g4);list.add(g5);list.add(g6);    list.add(g7);list.add(g8);list.add(g9);    list.add(g10);    for (int i = 0; i < 100; i++)    {      //int index = DrawLotteryUtil.drawGift(list);      System.out.println(((Gift)list.get(0)).getName());    }  }}