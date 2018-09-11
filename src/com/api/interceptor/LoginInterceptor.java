package com.api.interceptor;

import com.webpos.controller.ApiWebABaseController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor
  extends ApiWebABaseController
  implements HandlerInterceptor
{
  private Logger log = Logger.getLogger(getClass());
  private static List<String> whiteList = new ArrayList();
  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2)
    throws Exception
  {
    if (isWhiteList(request)) {
      return true;
    }
    if (!super.isLogin())
    {
      this.log.info("LoginInterceptor.preHandle login timeout... url:" + request.getRequestURL());
      if ((request.getHeader("x-requested-with") != null) && (request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))) {
        response.setHeader("sessionstatus", "timeout");
      } else {
        response.sendRedirect("/logout.do");
      }
      return false;
    }
    return true;
  }
  
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
    throws Exception
  {}
  
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
    throws Exception
  {}
  
  private boolean isWhiteList(HttpServletRequest request)
  {
    StringBuffer url = request.getRequestURL();
    for (String white : whiteList) {
      if (url.indexOf(white) > -1) {
        return true;
      }
    }
    return false;
  }
}