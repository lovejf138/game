package com.webpos.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.webpos.tools.CommUtil;

/**
 * 全局常量
 * 
 */
public class GlobalConstant {

	//加密key
	public final static String API_SIGN_KEY = "8EDTzniPlQORVi69IVX1n5vbKXh2ECch";//接口调用签名key
	public final static String API_TOKEN_KEY = "&vwdS*F6xlt*9iC@SHtoyu6pQX74Igbp";//接口调用token key
	public final static String KEY_JSAPI_COOKIE = "oj6OcYT3nZpoUsjl";//固定二维码cookie加密KEY
	

	public final static String ONLINE = "ON-LINE";//线上
	public final static String OFFLINE = "OFF-LINE";//线下
	
	public final static String ORDERSTATUS_ALL = "ALL";//全部
	public final static String ORDERSTATUS_EARN = "EARN";//已实收状态
	
	public final static String MENU_SELECT_NAME = "selectedMenuId";//设置菜单选中的对象名
	
	//二维码压缩包保存路径
	private static String PATH_LOCAL_QRCODE_ZIP = "%s/qrcodezip/%s/%s";
	
	//固定二维码保存的本地文件夹名字,需要格式化此字符串，传入参数
	private static String PATH_LOCAL_PAY_JSAPI_QRCODE = "/share/webpos/qrcode/JSAPI/%s/%s/%s_%sX%s.jpg";
	//固定二维码保存的URL文件夹名字,需要格式化此字符串，传入参数
	private static String PATH_URL_PAY_JSAPI_QRCODE = "%s/qrcode/JSAPI/%s/%s/%s_%sX%s.jpg";
	
	//固定二维码保存的本地文件夹名字,需要格式化此字符串，传入参数
	private static String PATH_LOCAL_PAY_JSAPI_QRCODE_CONSTANTS_AMOUNT = "/share/webpos/qrcode/JSAPI/CONSTANTS/%s_%sX%s.jpg";
	//固定二维码保存的URL文件夹名字,需要格式化此字符串，传入参数
	private static String PATH_URL_PAY_JSAPI_QRCODE_CONSTANTS_AMOUNT = "%s/qrcode/JSAPI/CONSTANTS/%s_%sX%s.jpg";
	
	private static String PATH_LIVE_PAY_QRCODE_IMAGE_NAME = "%s_%s.jpg";
	//动态二维码支付保存的本地文件夹名字,需要格式化此字符串，传入参数
	private static String PATH_LOCAL_PAY_LIVE_QRCODE = "/share/webpos/qrcode/live/%s/"+PATH_LIVE_PAY_QRCODE_IMAGE_NAME;
	//动态二维码支付保存的URL文件夹名字,需要格式化此字符串，传入参数
	private static String PATH_URL_PAY_LIVE_QRCODE = "%s/qrcode/live/%s/"+PATH_LIVE_PAY_QRCODE_IMAGE_NAME;

	//证书保存目录   1.支付渠道 2.证书名字
	private static String PATH_LOCAL_CERT = "/share/webpos/cert/%s/%s";
	
	//民生证书保存目录 1.支付渠道 2.证书名字
	private static String PATH_LOCAL_CERT_MINSHENG = "/share/webpos/cert/minsheng/%s/%s";
	
	private static String PATH_LOCAL_VERSION_FILE = "/share/webpos/terminal/%s/version-no/%s";
	//版本更新文件url目录  1.url根路径 2.终端 3.版本号 
	private static String PATH_URL_VERSION_FILE = "%s/terminal/%s/version-no/%s/";
		
	//微信固定二维码跳转地址
	public final static String WEIXIN_JSAPI_PAY_REDIRECT_URL = "/web_pay_weixin_JSAPI_redirect.do";
	//微信固定二维码跳转地址第三方授权地址
	public final static String WEIXIN_JSAPI_PAY_REDIRECT_URL_THIRD = "/web_pay_weixin_JSAPI_redirect_third.do";
	
	//微信固定二维码固定金额跳转地址
	public final static String WEIXIN_JSAPI_PAY_CONSTANTS_AMOUNT_REDIRECT_URL = "/web_pay_constants_weixin_JSAPI_redirect.do";
		
	//微信身份证二维码跳转地址
	public final static String WEIXIN_JSAPI_BIND_OPENID_REDIRECT_URL = "/web_weixin_JSAPI_bind_openid_redirect.do";
	//微信身份证二维码跳转地址第三方授权地址
	public final static String WEIXIN_JSAPI_BIND_OPENID_REDIRECT_URL_THIRD = "/web_weixin_JSAPI_bind_openid_redirect_third.do";
	
	//第三方获取openid跳转地址
	public final static String PARTNER_WEIXIN_JSAPI_GET_OPENID = "/api/pay/notify/get_openid_redirect.do";
	//第三方获取openid跳转地址第三方授权地址
		public final static String PARTNER_WEIXIN_JSAPI_GET_OPENID_THIRD = "/api/pay/notify/get_openid_redirect_third.do";
		
	//固定二维码 支付cookie
	public final static String COOKIE_JSAPI_USER_LOGINNAME = "userLoginName";
	public final static String COOKIE_JSAPI_OPENID = "openId";
	
	public final static String VIEW_NAME_JSP_404 = "404";
	
	//商户logo保存路径
	public final static String MERCHANT_LOGO_LOCAL_PATH = "/share/webpos/uploadfiles/headImage/";
	
	//第三方授权域名
	public final static String THIRD_GET_OPENID_DOMAIN = "http://mp.ylmo2o.com";
	/**
	 * 获取本地二维码压缩包路径
	 */
	@SuppressWarnings("deprecation")
	public static String getPathQrcodeZip(HttpServletRequest request, String merchantNo, String fileName){
		String format = new String(PATH_LOCAL_QRCODE_ZIP);
		return String.format(format, request.getRealPath("/"), merchantNo, fileName);
	}
	
	/**
	 * 获取二维码压缩包下载路径
	 */
	public static String getPathQrcodeZipDownUrl(HttpServletRequest request, String merchantNo, String fileName){
		String format = new String(PATH_LOCAL_QRCODE_ZIP);
		return String.format(format, CommUtil.getURL(request), merchantNo, fileName);
	}
	
	/**
	 * 获取固定二维码金额保存的本地文件路径 
	 */
	public static String getPathLocalJSAPIQrcodeConstatnsAmount(Long codeid,Integer width,Integer height){
		String format = new String(PATH_LOCAL_PAY_JSAPI_QRCODE_CONSTANTS_AMOUNT);
		return String.format(format,codeid,width,height);
	}
	/**
	 * 获取固定二维码金额访问的URL文件路径
	 */
	public static String getPathUrlJSAPIQrcodeConstatnsAmount(HttpServletRequest request,Long codeid,Integer width,Integer height){
		String format = new String(PATH_URL_PAY_JSAPI_QRCODE_CONSTANTS_AMOUNT);
		return String.format(format, CommUtil.getURL(request),codeid,width,height);
	}
	
	
	/**
	 * 获取动态二维码保存的文件名
	 */
	public static String getPathLiveQrcodeImageName(PayType payType,String orderNo){
		String format = new String(PATH_LIVE_PAY_QRCODE_IMAGE_NAME);
		return String.format(format, payType.name(),orderNo);
	}
	/**
	 * 获取动态二维码保存的本地文件路径 
	 */
	public static String getPathLocalLiveQrcode(PayType payType,String orderNo){
		String format = new String(PATH_LOCAL_PAY_LIVE_QRCODE);
		return String.format(format, CommUtil.formatDate(new Date()),payType.name(),orderNo);
	}
	/**
	 * 获取动态二维码访问的URL文件路径 
	 */
	public static String getPathUrlLiveQrcode(HttpServletRequest request,PayType payType,String orderNo){
		String format = new String(PATH_URL_PAY_LIVE_QRCODE);
		return String.format(format, CommUtil.getURL(request) ,CommUtil.formatDate(new Date()),payType.name(),orderNo);
	}
	/**
	 * 获取证书的保存目录
	 */
	public static String getPathLocalCert(PayType payType,String certName){
		String foramt = new String(PATH_LOCAL_CERT);
		return String.format(foramt, payType.name(),certName);
	}
	
	/**
	 * 获取民生证书的保存目录
	 */
	public static String getPathLocalCertMinsheng(PayType payType,String certName){
		String foramt = new String(PATH_LOCAL_CERT_MINSHENG);
		return String.format(foramt, payType.name(),certName);
	}

}