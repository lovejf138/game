package com.webpos.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class JModelAndView extends ModelAndView {
	public JModelAndView(String viewName) {
		super.setViewName(viewName);
	}

	// 设置站点域名
	public JModelAndView(String viewName, HttpServletRequest request,
			HttpServletResponse response) {
		String contextPath = request.getContextPath().equals("/") ? ""
				: request.getContextPath();
		String webPath = CommUtil.getURL(request);
		String port = ":"
				+ CommUtil.null2Int(Integer.valueOf(request.getServerPort()));

		super.setViewName(viewName);
		super.addObject("domainPath", CommUtil.generic_domain(request));
		// 判断图片服务器路径是否为空，不为空则设置为图片服务器，为空则设置为当前项目路径

		super.addObject("webPath", webPath);

		super.addObject("httpInclude", new HttpInclude(request, response));
		String query_url = "";
		if ((request.getQueryString() != null)
				&& (!request.getQueryString().equals(""))) {
			query_url = "?" + request.getQueryString();
		}
		super.addObject("current_url", request.getRequestURI() + query_url);
		boolean second_domain_view = false;
		String serverName = request.getServerName().toLowerCase();
		if ((serverName.indexOf("www.") < 0) && (serverName.indexOf(".") >= 0)
				&& (serverName.indexOf(".") != serverName.lastIndexOf("."))) {
			String secondDomain = serverName.substring(0,
					serverName.indexOf("."));
			second_domain_view = true;
			super.addObject("secondDomain", secondDomain);
		}
		super.addObject("second_domain_view",
				Boolean.valueOf(second_domain_view));
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 根据传入的不同数值 判断后 进行路径的不同设置，同时设置站点域名
	 * </p>
	 * 
	 * @param viewName
	 *            页面地址
	 * @param config
	 *            系统参数
	 * @param uconfig
	 *            用户信息
	 * @param type
	 *            指定参数设置不同的视图路径
	 * @param request
	 * @param response
	 */
	public JModelAndView(String viewName, int type, HttpServletRequest request,
			HttpServletResponse response) {

		// 根据传入的不同数值 判断后 进行路径的不同设置
		if (type == 0) {
			super.setViewName("webpos/pos/" + viewName);
		}

		super.setViewName(viewName);

		super.addObject("CommUtil", new CommUtil());
		String contextPath = request.getContextPath().equals("/") ? ""
				: request.getContextPath();
		String webPath = CommUtil.getURL(request); // 设置页面请求路径
		String port = ":"
				+ CommUtil.null2Int(Integer.valueOf(request.getServerPort()));
		super.addObject("domainPath", CommUtil.generic_domain(request));
		super.addObject("webPath", webPath);

		super.addObject("httpInclude", new HttpInclude(request, response));
		String query_url = "";
		if ((request.getQueryString() != null)
				&& (!request.getQueryString().equals(""))) {
			query_url = "?" + request.getQueryString();
		}
		super.addObject("current_url", request.getRequestURI() + query_url);
		boolean second_domain_view = false; // 设置第二域名
		String serverName = request.getServerName().toLowerCase();
		if ((serverName.indexOf("www.") < 0) && (serverName.indexOf(".") >= 0)
				&& (serverName.indexOf(".") != serverName.lastIndexOf("."))) {
			String secondDomain = serverName.substring(0,
					serverName.indexOf("."));
			second_domain_view = true;
			super.addObject("secondDomain", secondDomain);
		}
		super.addObject("second_domain_view",
				Boolean.valueOf(second_domain_view));
	}
}
