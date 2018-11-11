package com.webpos.tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.webpos.entity.Detail;
import com.webpos.entity.User;

import net.sf.json.JSONArray;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * <p>
 * Title: CommUtil
 * </p>
 * <p>
 * Description: Ipay工具类
 * </p>
 * 
 * @author ljx
 * @date 2015-4-28下午5:42:30
 * @version 1.0
 */
public class IpayUtil {
	private static Logger logger = Logger.getLogger(IpayUtil.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat dateFullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static int totalFolder;
	static int totalFile;

	public static final String yyyyMMdd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

	
	
	/**
	 * 商户号：1811111457870106
商户名：易便利社区生活体验店
交易秘钥：AC6FA95146C349B79DAF2C03BE9164A5
appid:  181111481
后台：http://lpay-admin.usehzf.com/login#
账号：ybl5736
密码：ybl5736!@#
	 */
	private static String merId = "1811111457870106";// 商户号
	private static String appId = "181111481";// secretKey
	private static String url = "lpay.usehzf.com";
	private static String mkey = "AC6FA95146C349B79DAF2C03BE9164A5";// secretKey
	private static String notifyUrl = "http://eth-game.club/ipaynotify.do";
	// amount以元为单位
	public static String weixinWapPay(double amount, String orderid) {

		String furl = "http://"+url+"/wap/weixin/gateway";
		double fee = CommUtil.mul(amount, 100);
		int reqFee = (int) fee;
		String para = "appId="+appId+"&extInfo=test&itemDesc=goods&itemName=goods&merId="+merId+
				"&merOrderId="+orderid+"&notifyUrl="+notifyUrl + 
				"&payerId="+orderid.substring(orderid.length()-11, orderid.length())+"&reqFee="+reqFee;
		String presign = para+"&key="+mkey;
		
		String sign = Md5Encrypt.md5(presign).toUpperCase();
		//System.out.println("sign:"+sign);
		String param = para+"&signValue="+sign;
		String result ="";
		try {
			result = HttpRequest.sendPost(furl,param);
			JSONObject jo = new JSONObject(result);
			String status = jo.getString("status");
			if("OK".equals(status)) {
				JSONObject data = jo.getJSONObject("data");
				result = data.getString("payUrl");
			}else {
				result = jo.getString("message");
			}
		} catch (Exception e) {
			result = "fail";
		}
		
		//System.out.println("result:"+result);
		return result;
	}

	// amount以元为单位
	public static String alipayWapPay(double amount, String orderid) {
		String furl = "http://"+url+"/wap/alipay/gateway";
		double fee = CommUtil.mul(amount, 100);
		int reqFee = (int) fee;
		String para = "appId="+appId+"&extInfo=test&itemDesc=goods&itemName=goods&merId="+merId+
				"&merOrderId="+orderid+"&notifyUrl="+notifyUrl + 
				"&payerId="+orderid.substring(orderid.length()-11, orderid.length())+"&reqFee="+reqFee;
		String presign = para+"&key="+mkey;
		
		String sign = Md5Encrypt.md5(presign).toUpperCase();
		//System.out.println("sign:"+sign);
		String param = para+"&signValue="+sign;
		String result ="";
		try {
			result = HttpRequest.sendPost(furl,param);
			JSONObject jo = new JSONObject(result);
			String status = jo.getString("status");
			if("OK".equals(status)) {
				JSONObject data = jo.getJSONObject("data");
				result = data.getString("payUrl");
			}else {
				result = jo.getString("message");
			}
		} catch (Exception e) {
			result = "fail";
		}
		return result;
	}

	// amount以元为单位
	public static String bankWapPay(double amount, String orderid) {
		String furl = "http://"+url+"/wap/quick/gateway";
		double fee = CommUtil.mul(amount, 100);
		int reqFee = (int) fee;
		String para = "appId="+appId+"&clientIp=127.0.0.1&extInfo=test&itemDesc=goods&itemName=goods&merId="+merId+
				"&merOrderId="+orderid+"&notifyUrl="+notifyUrl + 
				"&payerId="+orderid.substring(orderid.length()-11, orderid.length())+"&reqFee="+reqFee;
		String presign = para+"&key="+mkey;
		
		String sign = Md5Encrypt.md5(presign).toUpperCase();
		//System.out.println("sign:"+sign);
		String param = para+"&signValue="+sign;
		String result ="";
		try {
			result = HttpRequest.sendPost(furl,param);
			JSONObject jo = new JSONObject(result);
			String status = jo.getString("status");
			if("OK".equals(status)) {
				JSONObject data = jo.getJSONObject("data");
				result = data.getString("payUrl");
			}else {
				result = jo.getString("message");
			}
		} catch (Exception e) {
			result = "fail";
		}
		return result;
	}

	public static boolean checksign(Map<String, String> mas) {
		boolean r = false;
		String para = "amount="+mas.get("amount")+"&appId="+mas.get("appId")+"&extInfo="+mas.get("extInfo")+"&merId="+mas.get("merId")+
				"&merOrderId="+mas.get("merOrderId")+"&orderId="+mas.get("orderId") + 
				"&orderTime="+mas.get("orderTime");
		String presign = para+"&key="+mkey;
//		System.out.println("presign:"+presign);
		String sign = Md5Encrypt.md5(presign).toUpperCase();
//		System.out.println("sign:"+sign+",signValue:"+mas.get("signValue"));
		if(sign.equals(mas.get("signValue"))) {
			return true;
		}
		return r;
	}
}
