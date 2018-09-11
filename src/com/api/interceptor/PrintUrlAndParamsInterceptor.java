package com.api.interceptor;

import com.webpos.tools.CommUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PrintUrlAndParamsInterceptor
  implements HandlerInterceptor
{
  private Logger log = Logger.getLogger(getClass());
  
  public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object arg2, Exception arg3)
    throws Exception
  {}
  
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
    throws Exception
  {}
  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2)
    throws Exception
  {
    String url = request.getRequestURL().toString();
    
    String params = CommUtil.getMapToString(request.getParameterMap());
    
    this.log.info(String.format("PrintUrlAndParamsInterceptor.preHandle url:%s,params:%s", new Object[] { url, params }));
    return true;
  }
}
