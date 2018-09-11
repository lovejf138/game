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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
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
 * Description: 公共的工具类
 * </p>
 * <p>
 * Company: www.hg-sem.com
 * </p>
 * 
 * @author ljx
 * @date 2015-4-28下午5:42:30
 * @version 1.0
 */
public class CommUtil {
	private static Logger logger = Logger.getLogger(CommUtil.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	static int totalFolder;
	static int totalFile;

	public static final String yyyyMMdd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	
	
	public static boolean ifClose()
	{
	    int starttime = 1;
	    int endtime = 8;
	    
	    Calendar cal = Calendar.getInstance();
	    int hour = cal.get(11);
	    if ((hour >= starttime) && (hour <= endtime)) {
	      return true;
	    }
	    return false;
	}
	
	/**
	 * 
	 * <p>
	 * Title: first2low
	 * </p>
	 * <p>
	 * Description: 将传入的字符串 第一个字符转换成小写
	 * </p>
	 * 
	 * @param str
	 *            传入的字符串
	 * @return String 第一个字符小写的字符串
	 */
	public static String first2low(String str) {
		String s = "";
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: first2upper
	 * </p>
	 * <p>
	 * Description: 将传入的字符串 第一个字符转换成大写
	 * </p>
	 * 
	 * @param str
	 *            传入的字符串
	 * @return String 第一个字符大写的字符串
	 */
	public static String first2upper(String str) {
		String s = "";
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: str2list
	 * </p>
	 * <p>
	 * Description: 将传入的字符串 转换为list存储
	 * </p>
	 * 
	 * @param str
	 *            传入的字符串
	 * @return String 存储字符串的list集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> str2list(String s) throws IOException {
		List list = new ArrayList();
		if ((s != null) && (!s.equals(""))) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline = "";
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}
	
	/**
	 * yyyyMMddHHmmss 字符串转为  yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	private static String formatDateString(String time){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMddHHmmss").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	/**
	 * 
	 * <p>
	 * Title: formatDate
	 * </p>
	 * <p>
	 * Description: 将传入的字符串转换为日期类型
	 * </p>
	 * 
	 * @param s
	 *            传入日期格式的字符串
	 * @return Date 格式化后的日期
	 */
	public static Date formatDate(String s) {
		Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception localException) {
			localException.printStackTrace();
			logger.error(localException.getMessage(), localException);
		}
		return d;
	}

	/**
	 * 
	 * <p>
	 * Title: formatDate
	 * </p>
	 * <p>
	 * Description: 将传入的字符串，按照传入的指定格式 格式化，转换为日期类型
	 * </p>
	 * 
	 * @param s
	 *            日期格式的字符串
	 * @param format
	 *            指定的日期格式
	 * @return date 格式化后的日期
	 */
	public static Date formatDate(String s, String format) {
		Date d = null;
		try {
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception localException) {
			logger.error(localException.getMessage(), localException);
		}
		return d;
	}

	/**
	 * 
	 * <p>
	 * Title: formatTime
	 * </p>
	 * <p>
	 * Description: 将传入的object类型，按照传入的指定格式 格式化，转换为String类型
	 * </p>
	 * 
	 * @param format
	 *            指定的格式
	 * @param v
	 *            object
	 * @return String 转换后的字符串
	 */
	public static String formatTime(String format, Object v) {
		if (v == null)
			return null;
		if (v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	/**
	 * 
	 * <p>
	 * Title: formatLongDate
	 * </p>
	 * <p>
	 * Description: 将传入的object类型，按照 yyyy-MM-dd HH:mm:ss 格式 格式化，转换为String类型
	 * </p>
	 * 
	 * @param v
	 *            object
	 * @return String 转换后的字符串
	 */
	public static String formatLongDate(Object v) {
		if ((v == null) || (v.equals("")))
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String format2Date(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static Date formatString2Date(String v) {
		Date date = new Date();
		try {
			if ((v == null) || (v.equals("")))
				return null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			date = df.parse(v);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * <p>
	 * Title: formatShortDate
	 * </p>
	 * <p>
	 * Description: 将传入的object类型，按照 yyyy-MM-dd 格式 格式化，转换为String类型
	 * </p>
	 * 
	 * @param v
	 *            object
	 * @return String 转换后的字符串
	 */
	public static String formatShortDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}

	public static String formatDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(v);
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description: 使用UTF-8编码类型对 传入的路径 解码
	 * </p>
	 * 
	 * @param s
	 *            传入的路径
	 * @return String 解码后的 路径
	 */
	public static String decode(String s) {
		String ret = s;
		try {
			ret = URLDecoder.decode(s.trim(), "UTF-8");
		} catch (Exception localException) {
			localException.printStackTrace();
			logger.error(localException.getMessage(), localException);
		}
		return ret;
	}

	/**
	 * 
	 * <p>
	 * Title: encode
	 * </p>
	 * <p>
	 * Description: 使用UTF-8编码类型对 传入的路径 编码
	 * </p>
	 * 
	 * @param s
	 *            传入的路径
	 * @return String 编码后的 路径
	 */
	public static String encode(String s) {
		String ret = s;
		try {
			ret = URLEncoder.encode(s.trim(), "UTF-8");
		} catch (Exception localException) {
			logger.error(localException.getMessage(), localException);
		}
		return ret;
	}

	/**
	 * 
	 * @param str
	 * @return 返回编码格式
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
			logger.error(exception1.getMessage(), exception1);
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
			logger.error(exception2.getMessage(), exception2);
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
			logger.error(exception3.getMessage(), exception3);
		}
		return "";
	}

	/**
	 * 
	 * <p>
	 * Title: convert
	 * </p>
	 * <p>
	 * Description: 先将传入的 str按ISO-8859-1编码，转换为字节数组 再通过使用指定的 coding 解码指定的
	 * 此数组，构造一个新的 String。
	 * </p>
	 * 
	 * @param str
	 *            传入的字符串
	 * @param coding
	 *            指定的编码格式
	 * @return String 转换化的字符串
	 */
	public static String convert(String str, String coding) {
		String newStr = "";
		if (str != null)
			try {
				newStr = new String(str.getBytes("ISO-8859-1"), coding);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return newStr;
			}
		return newStr;
	}

	/**
	 * 
	 * <p>
	 * Title: saveFileToServer
	 * </p>
	 * <p>
	 * Description: 保存文件到服务器 同时将 对应的信息存储到map中
	 * </p>
	 * 
	 * @param request
	 * @param filePath
	 *            文件路径
	 * @param addFilePathName
	 *            保存文件路径名称
	 * @param addFileName
	 *            保存文件名称
	 * @param extendes
	 * @return map 存储了文件上传过程中所有的参数
	 * @throws IOException
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map saveFileToServer(HttpServletRequest request,
			String filePath, String addFilePathName, String addFileName,
			String[] extendes) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(filePath);
		Map map = new HashMap();
		if ((file != null) && (!file.isEmpty())) {
			String extend = file.getOriginalFilename()
					// 后缀名
					.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if ((addFileName == null) || (addFileName.trim().equals(""))) {
				addFileName = UUID.randomUUID().toString() + "." + extend; // 文件名
			}
			if (addFileName.lastIndexOf(".") < 0) {
				addFileName = addFileName + "." + extend; // 文件名
			}
			float fileSize = Float.valueOf((float) file.getSize()).floatValue();
			List errors = new ArrayList();
			boolean flag = true;
			if (extendes != null) {
				for (String s : extendes) {
					if (extend.toLowerCase().equals(s))
						flag = true;
				}
			}
			if (flag) {
				File path = new File(addFilePathName);
				if (!path.exists()) {
					path.mkdir();
				}
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(addFilePathName + File.separator
								+ addFileName));
				InputStream is = null;
				try {
					is = file.getInputStream();
					int size = (int) fileSize;
					byte[] buffer = new byte[size];
					while (is.read(buffer) > 0)
						out.write(buffer);
				} catch (IOException exception) {
					logger.error(exception.getMessage(), exception);
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (out != null) {
						out.close();
					}
				}
				if (isImg(extend)) {
					File img = new File(addFilePathName + File.separator
							+ addFileName);
					try {
						BufferedImage bis = ImageIO.read(img);
						int w = bis.getWidth();
						int h = bis.getHeight();
						map.put("width", Integer.valueOf(w));
						map.put("height", Integer.valueOf(h));
					} catch (Exception localException) {
						logger.error(localException.getMessage(), localException);
					}
				}
				String fileName = addFileName.substring(0,
						addFileName.indexOf("."));
				map.put("mime", extend);
				map.put("noExtFileName", fileName);
				map.put("fileName", addFileName);
				map.put("fileSize", Float.valueOf(fileSize));
				map.put("error", errors);
				map.put("oldName", file.getOriginalFilename());
			} else {
				errors.add("不允许的扩展名");
			}
		} else {
			map.put("width", Integer.valueOf(0));
			map.put("height", Integer.valueOf(0));
			map.put("mime", "");
			map.put("fileName", "");
			map.put("fileSize", Float.valueOf(0.0F));
			map.put("oldName", "");
		}
		return map;
	}

	/**
	 * 
	 * <p>
	 * Title: isImg
	 * </p>
	 * <p>
	 * Description: 判断是否是图片
	 * </p>
	 * 
	 * @param extend
	 *            文件的后缀名
	 * @return boolean
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new ArrayList();
		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}

	public static final void waterMarkWithImage(String pressImg,
			String targetImg, int pos, float alpha) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, 1);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);

			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			g.setComposite(AlphaComposite.getInstance(10, alpha / 100.0F));
			int width_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			int x = 0;
			int y = 0;

			if (pos == 2) {
				x = (width - width_biao) / 2;
				y = 0;
			}
			if (pos == 3) {
				x = width - width_biao;
				y = 0;
			}
			if (pos == 4) {
				x = width - width_biao;
				y = (height - height_biao) / 2;
			}
			if (pos == 5) {
				x = width - width_biao;
				y = height - height_biao;
			}
			if (pos == 6) {
				x = (width - width_biao) / 2;
				y = height - height_biao;
			}
			if (pos == 7) {
				x = 0;
				y = height - height_biao;
			}
			if (pos == 8) {
				x = 0;
				y = (height - height_biao) / 2;
			}
			if (pos == 9) {
				x = (width - width_biao) / 2;
				y = (height - height_biao) / 2;
			}
			g.drawImage(src_biao, x, y, width_biao, height_biao, null);

			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("serial")
	public static boolean waterMarkWithText(String filePath, String outPath,
			String text, String markContentColor, Font font, int pos,
			float qualNum) {
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);
		BufferedImage bimage = new BufferedImage(width, height, 1);
		Graphics2D g = bimage.createGraphics();
		if (font == null) {
			font = new Font("黑体", 1, 30);
			g.setFont(font);
		} else {
			g.setFont(font);
		}
		g.setColor(getColor(markContentColor));
		g.setBackground(Color.white);
		g.drawImage(theImg, 0, 0, null);
		FontMetrics metrics = new FontMetrics(font) {
		};
		Rectangle2D bounds = metrics.getStringBounds(text, null);
		int widthInPixels = (int) bounds.getWidth();
		int heightInPixels = (int) bounds.getHeight();
		int left = 0;
		int top = heightInPixels;

		if (pos == 2) {
			left = width / 2;
			top = heightInPixels;
		}
		if (pos == 3) {
			left = width - widthInPixels;
			top = heightInPixels;
		}
		if (pos == 4) {
			left = width - widthInPixels;
			top = height / 2;
		}
		if (pos == 5) {
			left = width - widthInPixels;
			top = height - heightInPixels;
		}
		if (pos == 6) {
			left = width / 2;
			top = height - heightInPixels;
		}
		if (pos == 7) {
			left = 0;
			top = height - heightInPixels;
		}
		if (pos == 8) {
			left = 0;
			top = height / 2;
		}
		if (pos == 9) {
			left = width / 2;
			top = height / 2;
		}
		g.drawString(text, left, top);
		g.dispose();
		try {
			FileOutputStream out = new FileOutputStream(outPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(qualNum, true);
			encoder.encode(bimage, param);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Title: createFolder
	 * </p>
	 * <p>
	 * Description: 根据传入的路径创建文件夹
	 * </p>
	 * 
	 * @param folderPath
	 * @return
	 */
	public static boolean createFolder(String folderPath) {
		boolean ret = true;
		try {
			File myFilePath = new File(folderPath);
			if ((!myFilePath.exists()) && (!myFilePath.isDirectory())) {
				ret = myFilePath.mkdirs();
				if (!ret)
					System.out.println("创建文件夹出错");
			}
		} catch (Exception e) {
			System.out.println("创建文件夹出错");
			ret = false;
		}
		return ret;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toRowChildList(List list, int perNum) {
		List l = new ArrayList();
		if (list == null) {
			return l;
		}
		for (int i = 0; i < list.size(); i += perNum) {
			List cList = new ArrayList();
			for (int j = 0; j < perNum; j++)
				if (i + j < list.size())
					cList.add(list.get(i + j));
			l.add(cList);
		}
		return l;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List copyList(List list, int begin, int end) {
		List l = new ArrayList();
		if (list == null)
			return l;
		if (end > list.size())
			end = list.size();
		for (int i = begin; i < end; i++) {
			l.add(list.get(i));
		}
		return l;
	}

	public static boolean isNotNull(Object obj) {
		return (obj != null) && (!obj.toString().equals(""));
	}

	@SuppressWarnings({ "unused", "resource" })
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];

				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	public static boolean deleteFolder(String path) {
		boolean flag = false;
		File file = new File(path);

		if (!file.exists()) {
			return flag;
		}

		if (file.isFile()) {
			return deleteFile(path);
		}
		return deleteDirectory(path);
	}

	public static boolean deleteFile(String path) {
		boolean flag = false;
		File file = new File(path);

		if ((file.isFile()) && (file.exists())) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public static boolean deleteDirectory(String path) {
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		File dirFile = new File(path);

		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;

		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			return false;
		}

		return dirFile.delete();
	}

	/**
	 * 
	 * <p>
	 * Title: showPageStaticHtml
	 * </p>
	 * <p>
	 * Description: 静态分页处理
	 * </p>
	 * 
	 * @param url
	 *            路径
	 * @param currentPage
	 *            当前页码
	 * @param pages
	 *            总页码数
	 * @return String 点击页码后的路径
	 */
	public static String showPageStaticHtml(String url, int currentPage,
			int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s + "<a href='" + url + "_1.htm' class='_home_page'>首页</a> ";
				if (currentPage > 1) {
					s = s + "<a href='" + url + "_" + (currentPage - 1)
							+ ".htm' class='_font_page'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s + "<a class='this' href='" + url + "_" + i
								+ ".htm'>" + i + "</a> ";
					else
						s = s + "<a href='" + url + "_" + i + ".htm'>" + i
								+ "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s + "<a href='" + url + "_" + (currentPage + 1)
							+ ".htm' class='_next_page'>下一页</a> ";
				}
				s = s + "<a href='" + url + "_" + pages + ".htm' class='_last_page'>末页</a> ";
			}
		}
		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: showPageHtml
	 * </p>
	 * <p>
	 * Description: 分页处理
	 * </p>
	 * 
	 * @param url
	 *            路径
	 * @param params
	 *            参数
	 * @param currentPage
	 *            当前页码数
	 * @param pages
	 *            总页码数
	 * @return String 点击页码后的路径
	 */
	public static String showPageHtml(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s + "<a href='" + url + "?currentPage=1" + params
						+ "' class='_home_page'>首页</a> ";
				if (currentPage > 1) {
					s = s + "<a class='_font_page' href='" + url + "?currentPage="
							+ (currentPage - 1) + params + "'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s + "<a class='this' href='" + url
								+ "?currentPage=" + i + params + "'>" + i
								+ "</a> ";
					else
						s = s + "<a href='" + url + "?currentPage=" + i
								+ params + "'>" + i + "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s + "<a href='" + url + "?currentPage="
							+ (currentPage + 1) + params + "' class='_next_page'>下一页</a> ";
				}
				s = s + "<a href='" + url + "?currentPage=" + pages + params
						+ "' class='_last_page'>末页</a> ";
			}
		}

		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: showPageFormHtml
	 * </p>
	 * <p>
	 * Description: 表单页码处理
	 * </p>
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pages
	 *            总页码数
	 * @return String 点击页码后的路径
	 */
	public static String showPageFormHtml(int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage(1)' class='_home_page'>首页</a> ";
				if (currentPage > 1) {
					s = s
							+ "<a class='_font_page' href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage - 1) + ")'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s
							+ "<a href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage + 1) + ")' class='_next_page'>下一页</a> ";
				}
				s = s
						+ "<a id='lastPageNo' href='javascript:void(0);' onclick='return gotoPage("
						+ pages + ")' class='_last_page'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageFormHtml1(int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage(1)' class='_home_page'>首页</a> ";
				if (currentPage > 1) {
					s = s
							+ "<a class='_font_page' href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage - 1) + ")'>上一页</a> ";
				}
			}
			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s
							+ "<a href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage + 1) + ")' class='_next_page'>下一页</a> ";
				}
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage("
						+ pages + ")' class='_last_page'>末页</a> ";
			}
		}

		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: showPageAjaxHtml
	 * </p>
	 * <p>
	 * Description: 异步分页处理
	 * </p>
	 * 
	 * @param url
	 *            路径
	 * @param params
	 *            参数
	 * @param currentPage
	 *            当前页码数
	 * @param pages
	 *            总页码数
	 * @return String 点击页码后的路径
	 */
	public static String showPageAjaxHtml(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			String address = url + "?1=1" + params;
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\",1,this)' class='_home_page'>首页</a> ";
				s = s
						+ "<a class='_font_page' href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + (currentPage - 1)
						+ ",this)'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return ajaxPage(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + (currentPage + 1)
						+ ",this)' class='_next_page'>下一页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + pages + ",this)' class='_last_page'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageAjaxHtml1(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			String address = url + "?1=1" + params;
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage1(\""
						+ address + "\",1,this)' class='_home_page'>首页</a> ";
				s = s
						+ "<a class='_font_page' href='javascript:void(0);' onclick='return ajaxPage1(\""
						+ address + "\"," + (currentPage - 1)
						+ ",this)'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return ajaxPage1(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return ajaxPage1(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage1(\""
						+ address + "\"," + (currentPage + 1)
						+ ",this)' class='_next_page'>下一页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage1(\""
						+ address + "\"," + pages + ",this)' class='_last_page'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageAjaxHtml2(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			String address = url + "?1=1" + params;
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage2(\""
						+ address + "\",1,this)' class='_home_page'>首页</a> ";
				s = s
						+ "<a class='_font_page' href='javascript:void(0);' onclick='return ajaxPage2(\""
						+ address + "\"," + (currentPage - 1)
						+ ",this)'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); j++) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return ajaxPage2(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return ajaxPage2(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					i++;
				}

				s = s + "<a class='_page'>页</a>　";
			}
			if (currentPage <= pages) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage2(\""
						+ address + "\"," + (currentPage + 1)
						+ ",this)' class='_next_page'>下一页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage2(\""
						+ address + "\"," + pages + ",this)' class='_last_page'>末页</a> ";
			}
		}

		return s;
	}

	public static void addIPageList2ModelAndView(String url, String staticURL,
			String params, Pagination pList, ModelAndView mv) {
		if (pList != null) {
			
			mv.addObject("objs", pList.getList());
			mv.addObject("totalPage", Pagination.cpn(pList.getTotalPage()));
			mv.addObject("pageSize", Pagination.cpn(pList.getPageSize()));
			mv.addObject("rows", Pagination.cpn(pList.getPageSize()));
			mv.addObject("currentPage", Pagination.cpn(pList.getPageNo()));
			mv.addObject(
					"gotoPageHTML",
					showPageHtml(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageFormHTML",
					showPageFormHtml(Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageStaticHTML",
					showPageStaticHtml(staticURL,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageAjaxHTML",
					showPageAjaxHtml(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			// 在同一个页面中如果有两个以上分页的话点击事件的方法会冲突，所以加了以下的方法
			mv.addObject(
					"gotoPageAjaxHTML1",
					showPageAjaxHtml1(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageAjaxHTML2",
					showPageAjaxHtml2(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
		}
	}

	public static void addIPageList2ModelAndView1(String url, String staticURL,
			String params, Pagination pList, ModelAndView mv) {
		if (pList != null) {
			mv.addObject("objs", pList.getList());
			mv.addObject("totalPage", Pagination.cpn(pList.getTotalPage()));
			mv.addObject("pageSize", Pagination.cpn(pList.getPageSize()));
			mv.addObject("rows", Pagination.cpn(pList.getPageSize()));
			mv.addObject("currentPage", Pagination.cpn(pList.getPageNo()));
			mv.addObject(
					"gotoPageHTML",
					showPageHtml(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageFormHTML1",
					showPageFormHtml(Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageStaticHTML",
					showPageStaticHtml(staticURL,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageAjaxHTML",
					showPageAjaxHtml(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			// 在同一个页面中如果有两个以上分页的话点击事件的方法会冲突，所以加了以下的方法
			mv.addObject(
					"gotoPageAjaxHTML1",
					showPageAjaxHtml1(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
			mv.addObject(
					"gotoPageAjaxHTML2",
					showPageAjaxHtml2(url, params,
							Pagination.cpn(pList.getPageNo()),
							Pagination.cpn(pList.getTotalPage())));
		}
	}

	/**
	 * 
	 * <p>
	 * Title: randomChar
	 * </p>
	 * <p>
	 * Description: 在52个大小写字母中随机获得一个字母
	 * </p>
	 * 
	 * @return char 单个字母
	 */
	public static char randomChar() {
		char[] chars = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f',
				'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l',
				'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r',
				'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x',
				'X', 'y', 'Y', 'z', 'Z' };
		int index = (int) (Math.random() * 52.0D) - 1;
		if (index < 0) {
			index = 0;
		}
		return chars[index];
	}

	/**
	 * 
	 * <p>
	 * Title: splitByChar
	 * </p>
	 * <p>
	 * Description: 根据第二个参数 切割第一个参数获得新的字符串数组
	 * </p>
	 * 
	 * @param s
	 * @param c
	 * @return String[] 字符串数组
	 */
	public static String[] splitByChar(String s, String c) {
		String[] list = s.split(c);
		return list;
	}

	/**
	 * 
	 * <p>
	 * Title: requestByParam
	 * </p>
	 * <p>
	 * Description: 总request中获得参数
	 * </p>
	 * 
	 * @param request
	 * @param param
	 *            参数名称
	 * @return object 参数
	 */
	public static Object requestByParam(HttpServletRequest request, String param) {
		if (!request.getParameter(param).equals("")) {
			return request.getParameter(param);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: substring
	 * </p>
	 * <p>
	 * Description: 根据传入的所以截取 字符串，获得指定索要之后的字符串
	 * </p>
	 * 
	 * @param s
	 * @param maxLength
	 * @return String 截取后的字符串 +...
	 */
	public static String substring(String s, int maxLength) {
		if (!StringUtils.hasLength(s))
			return s;
		if (s.length() <= maxLength) {
			return s;
		}
		return s.substring(0, maxLength) + "...";
	}

	/**
	 * 
	 * <p>
	 * Title: substringfrom
	 * </p>
	 * <p>
	 * Description: 截取第二个字符串在第一个字符串第一次出现的位置之后的内容 默认是 ""
	 * </p>
	 * 
	 * @param s
	 * @param from
	 * @return String 新的字符串
	 */
	public static String substringfrom(String s, String from) {
		if (s.indexOf(from) < 0)
			return "";
		return s.substring(s.indexOf(from) + from.length());
	}

	/**
	 * 判断是否为null，不是就直接返回，是就转换为int类型 默认是1
	 * 
	 * @param object
	 *            类型
	 * @return int类型
	 */
	public static int null2Int(Object s) {
		int v = 1;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 判断是否为null，不是就直接返回，是就转换为float类型 默认是 0.0F
	 * 
	 * @param object
	 *            类型
	 * @return float类型
	 */
	public static float null2Float(Object s) {
		float v = 0.0F;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 判断是否为null，不是就直接返回，是就转换为double类型 默认是0.0D
	 * 
	 * @param object
	 *            类型
	 * @return double类型
	 */
	public static double null2Double(Object s) {
		double v = 0.0D;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception localException) {
			}
		return v;
	}

	public static BigDecimal null2BigDecimal(Object s) {
		BigDecimal decimal = null;
		if (s != null)
			try {
				decimal = new BigDecimal(null2String(s));
			} catch (Exception localException) {
			}
		return decimal;
	}

	/**
	 * 判断是否为null，不是就直接返回，是就转换为boolean类型 默认是false
	 * 
	 * @param object
	 *            类型
	 * @return boolean类型
	 */
	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 判断是否为null，是就转换为"" ,不是就去掉空格转换为String类型
	 * 
	 * @param object
	 *            类型
	 * @return String类型
	 */
	public static String null2String(Object s) {
		return s == null ? "" : s.toString().trim();
	}

	/**
	 * 判断是否为null，不是就直接返回，是就转换为Long类型的 -1
	 * 
	 * @param object
	 * @return Long类型
	 */
	public static Long null2Long(Object s) {
		Long v = Long.valueOf(-1L);
		if (s != null)
			try {
				v = Long.valueOf(Long.parseLong(s.toString()));
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 将传入的值 转换为具体的描述 xx小时xx分钟xx秒
	 * 
	 * @param time
	 *            long类型
	 * @return String xx小时xx分钟xx秒
	 */
	public static String getTimeInfo(long time) {
		int hour = (int) time / 3600000;
		long balance = time - hour * 1000 * 60 * 60;
		int minute = (int) balance / 60000;
		balance -= minute * 1000 * 60;
		int seconds = (int) balance / 1000;
		String ret = "";
		if (hour > 0)
			ret = ret + hour + "小时";
		if (minute > 0)
			ret = ret + minute + "分";
		else if ((minute <= 0) && (seconds > 0))
			ret = ret + "零";
		if (seconds > 0)
			ret = ret + seconds + "秒";
		return ret;
	}

	/**
	 * 获得访问客户端ip
	 * 
	 * @param request
	 * @return String 从request中得到的ip
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得字符串去掉前后空白之后的长度
	 * 
	 * @param s
	 * @param sub
	 *            实际传入的字符串内容
	 * @return int 字符串的实际长度
	 */
	public static int indexOf(String s, String sub) {
		return s.trim().indexOf(sub.trim());
	}

	/**
	 * 将传入的开始时间 和结束时间 计算出 具体的天 、小时、分钟、秒
	 * 
	 * @param begin
	 * @param end
	 * @return map key：时间单位描述（day、hour、min、second） value:具体对应的数
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static Map cal_time_space(Date begin, Date end) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long l = end.getTime() - begin.getTime();
		long day = l / 86400000L;
		long hour = l / 3600000L - day * 24L;
		long min = l / 60000L - day * 24L * 60L - hour * 60L;
		long second = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L
				- min * 60L;
		Map map = new HashMap();
		map.put("day", Long.valueOf(day));
		map.put("hour", Long.valueOf(hour));
		map.put("min", Long.valueOf(min));
		map.put("second", Long.valueOf(second));
		return map;
	}

	/**
	 * 根据传入的长度返回，长度个随机字母和数字组成的字符串
	 * 
	 * @param length
	 *            长度
	 * @return String 字母和数字组成的字符串
	 */
	public static final String randomString(int length) {
		char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				.toCharArray();
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 根据传入的长度返回随机数字组成的字符串
	 * 
	 * @param length
	 *            长度
	 * @return String 字母和数字组成的字符串
	 */
	public static final String randomNumberString(int length) {
		char[] numberss = "0123456789".toCharArray();
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numberss[randGen.nextInt(numberss.length)];
		}
		return new String(randBuffer);
	}

	/**
	 * 根据传入的长度返回，长度个 数字组成的字符串
	 * 
	 * @param length
	 *            长度
	 * @return String 数字组成的字符串
	 */
	public static final String randomInt(int length) {
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] numbersAndLetters = "0123456789".toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	/**
	 * 根据传入的两个毫秒值，得出两个时间之间 相差的天数
	 * 
	 * @param time1
	 *            后一个时间
	 * @param time2
	 *            前一个时间
	 * @return long 两个时间之间相差的天数
	 */
	public static long getDateDistance(String time1, String time2) {
		long quot = 0L;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000L / 60L / 60L / 24L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static double div(Object a, Object b) {
		double ret = 0.0D;
		if ((!null2String(a).equals("")) && (!null2String(b).equals(""))) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0.0D)
				ret = e.divide(f, 3, 1).doubleValue();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static int divToInt(Object a, Object b) {
		int ret = 0;
		if ((!null2String(a).equals("")) && (!null2String(b).equals(""))) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0.0D)
				ret = e.divide(f, 3, 1).intValue();
		}
		return Integer.valueOf(ret).intValue();
	}

	public static double subtract(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.subtract(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double add(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.add(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static String mulBigDecimal(Object a, Object b) {
		BigDecimal e = null2BigDecimal(a);
		BigDecimal f = null2BigDecimal(b);
		BigDecimal ret = e.multiply(f);
		return ret.toString();
	}

	public static double mul(Object a, Object b) {
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		double ret = e.multiply(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double formatMoney(Object money) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(money)).doubleValue();
	}

	/**
	 * 将传入的 兆 转化为字节 M--KB--B
	 * 
	 * @param m
	 *            传入的兆大小
	 * @return int 字节大小
	 */
	public static int M2byte(float m) {
		float a = m * 1024.0F * 1024.0F;
		return (int) a;
	}

	/**
	 * 判断该值是否等于0
	 * 
	 * @param intValue
	 * @return true： 不为0 false：为0
	 */
	public static boolean convertIntToBoolean(int intValue) {
		return intValue != 0;
	}

	/**
	 * 获得完整的URL
	 * 
	 * @param request
	 * @return String url
	 */
	public static String getURL(HttpServletRequest request) {
		
		// 获得相对路径
		
		String contextPath = request.getContextPath().equals("/") ? ""
				: request.getContextPath();
		// 拼接url
		String url = "http://" + request.getServerName();
		if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
			url = url + ":"
					+ null2Int(Integer.valueOf(request.getServerPort()))
					+ contextPath;
		else {
			url = url + contextPath;
		}
		return url;
	}

	/**
	 * 根据窜入的类型和日期得到具体的日期数字
	 * 
	 * @param type
	 *            String类型的日期描述 y/M/d/H/m/s
	 * @param date
	 *            日期
	 * @return int 具体的日期时间
	 */
	public static int parseDate(String type, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("y")) {
			return cal.get(1);
		}
		if (type.equals("M")) {
			return cal.get(2) + 1;
		}
		if (type.equals("d")) {
			return cal.get(5);
		}
		if (type.equals("H")) {
			return cal.get(11);
		}
		if (type.equals("m")) {
			return cal.get(12);
		}
		if (type.equals("s")) {
			return cal.get(13);
		}
		return 0;
	}

	/**
	 * 获取图片的宽和高
	 * 
	 * @param imgurl
	 *            图片路径
	 * @return int 数组 长度为2 获取成功时 第一个为宽，第二个为高，获取失败值为空
	 */
	public static int[] readImgWH(String imgurl) {
		boolean b = false;
		try {
			URL url = new URL(imgurl);

			BufferedInputStream bis = new BufferedInputStream(url.openStream());

			byte[] bytes = new byte[100];

			OutputStream bos = new FileOutputStream(new File(
					"C:\\thetempimg.gif"));
			int len;
			while ((len = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			bis.close();
			bos.flush();
			bos.close();

			b = true;
		} catch (Exception e) {
			b = false;
		}
		int[] a = new int[2];
		if (b) {
			File file = new File("C:\\thetempimg.gif");
			BufferedImage bi = null;
			boolean imgwrong = false;
			try {
				bi = ImageIO.read(file);
				try {
					bi.getType();
					imgwrong = true;
				} catch (Exception e) {
					imgwrong = false;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if (imgwrong) {
				a[0] = bi.getWidth();
				a[1] = bi.getHeight();
			} else {
				a = null;
			}

			file.delete();
		} else {
			a = null;
		}
		return a;
	}

	/**
	 * 判断文件路径是否存在
	 * 
	 * @param path
	 * @return true or flase
	 */
	public static boolean fileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 
	 * <p>
	 * Title: splitLength
	 * </p>
	 * <p>
	 * Description: 获得 根据第二个字符串 截取第一个字符串获得的数组长度
	 * </p>
	 * 
	 * @param s
	 * @param c
	 * @return 数组长度
	 */
	public static int splitLength(String s, String c) {
		int v = 0;
		if (!s.trim().equals("")) {
			v = s.split(c).length;
		}
		return v;
	}

	/**
	 * 
	 * <p>
	 * Title: fileSize
	 * </p>
	 * <p>
	 * Description: 获得文件夹大小
	 * </p>
	 * 
	 * @param folder
	 * @return double 大小
	 */
	public static double fileSize(File folder) {
		totalFolder += 1;

		long foldersize = 0L;
		File[] filelist = folder.listFiles();
		if (filelist != null) {
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					foldersize = (long) (foldersize + fileSize(filelist[i]));
				} else {
					totalFile += 1;
					foldersize += filelist[i].length();
				}
			}
		}
		return div(Long.valueOf(foldersize), Integer.valueOf(1024));
	}

	/**
	 * 
	 * <p>
	 * Title: fileCount
	 * </p>
	 * <p>
	 * Description: 统计问价夹下所有文件的数量
	 * </p>
	 * 
	 * @param file
	 * @return int 数量
	 */
	public static int fileCount(File file) {
		if (file == null) {
			return 0;
		}
		if (!file.isDirectory()) {
			return 1;
		}
		int fileCount = 0;
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				fileCount++;
			} else if (f.isDirectory()) {
				fileCount++;
				fileCount += fileCount(file);
			}
		}
		return fileCount;
	}

	/**
	 * 
	 * <p>
	 * Title: get_all_url
	 * </p>
	 * <p>
	 * Description: 获得所有URL
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	public static String get_all_url(HttpServletRequest request) {
		String query_url = request.getRequestURI();
		if ((request.getQueryString() != null)
				&& (!request.getQueryString().equals(""))) {
			query_url = query_url + "?" + request.getQueryString();
		}
		return query_url;
	}

	/**
	 * 
	 * <p>
	 * Title: getColor
	 * </p>
	 * <p>
	 * Description: 根据传入的 颜色数值 # xx xx xx 获得颜色对象
	 * </p>
	 * 
	 * @param color
	 * @return Color 对象
	 */
	public static Color getColor(String color) {
		if (color.charAt(0) == '#') {
			color = color.substring(1);
		}
		if (color.length() != 6)
			return null;
		try {
			int r = Integer.parseInt(color.substring(0, 2), 16);
			int g = Integer.parseInt(color.substring(2, 4), 16);
			int b = Integer.parseInt(color.substring(4), 16);
			return new Color(r, g, b);
		} catch (NumberFormatException nfe) {
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set<Integer> randomInt(int a, int length) {
		Set list = new TreeSet();
		int size = length;
		if (length > a) {
			size = a;
		}
		while (list.size() < size) {
			Random random = new Random();
			int b = random.nextInt(a);
			list.add(Integer.valueOf(b));
		}
		return list;
	}

	@SuppressWarnings("unused")
	public static Double formatDouble(Object obj, int len) {
		Double ret = Double.valueOf(0.0D);
		String format = "0.0";
		for (int i = 1; i < len; i++) {
			format = format + "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		return Double.valueOf(df.format(obj));
	}

	/**
	 * 
	 * <p>
	 * Title: isChinese
	 * </p>
	 * <p>
	 * Description: 判断是否是中文
	 * </p>
	 * 
	 * @param c
	 *            char
	 * @return 是就返回true
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		return (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
				|| (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
	}

	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0.0F;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			// 确定指定字符是否为字母或数字
			if (Character.isLetterOrDigit(c))
				continue;
			if (!isChinese(c)) {
				count += 1.0F;
				System.out.print(c);
			}
		}

		float result = count / chLength;

		return result > 0.4D;
	}

	/**
	 * 
	 * <p>
	 * Title: trimSpaces
	 * </p>
	 * <p>
	 * Description: 去掉IP地址字符串的前后空格
	 * </p>
	 * 
	 * @param IP
	 * @return
	 */
	public static String trimSpaces(String IP) {
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	/**
	 * 
	 * <p>
	 * Title: isIp
	 * </p>
	 * <p>
	 * Description: 判断是否是IP地址
	 * </p>
	 * 
	 * @param IP
	 * @return boolean 是就返回true
	 */
	public static boolean isIp(String IP) {
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String[] s = IP.split("\\.");
			if ((Integer.parseInt(s[0]) < 255)
					&& (Integer.parseInt(s[1]) < 255)
					&& (Integer.parseInt(s[2]) < 255)
					&& (Integer.parseInt(s[3]) < 255))
				b = true;
		}
		return b;
	}

	/**
	 * 
	 * <p>
	 * Title: generic_domain
	 * </p>
	 * <p>
	 * Description: 获得服务器名称
	 * </p>
	 * 
	 * @param request
	 * @return string 服务器名称
	 */
	public static String generic_domain(HttpServletRequest request) {
		String system_domain = "localhost";
		String serverName = request.getServerName();
		if (isIp(serverName))
			system_domain = serverName;
		else {
			system_domain = serverName.substring(serverName.indexOf(".") + 1);
		}

		return system_domain;
	}

	/**
	 * 
	 * @todo 两个对象进行相除,返回百分数
	 * @date 2015年6月18日 下午7:05:16
	 * @return String
	 * @return
	 */
	public static int divide(Object a, Object b) {

		double aNew = Double.parseDouble(CommUtil.null2String(a));
		double bNew = Double.parseDouble(CommUtil.null2String(b));
		double r = 0.0D;
		if (bNew != 0) {
			r = (aNew / bNew) * 100;
		}
		return (int) Math.floor(r);
	}

	/**
	 * 根据cookie名取得对应cookie
	 * 
	 * @param name
	 * @param cookies
	 * @return
	 */
	public static Cookie getCookieValue(String name, Cookie[] cookies) {
		Cookie retCookie = null;

		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					/* 存在这个name,取出对应的值 */
					retCookie = cookie;
				}
			}
		}
		return retCookie;
	}

	/**
	 * 判断两个时间是否属于同一天
	 * 
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isSameDay(String oldDate, String newDate) {
		Date old = new Date(Long.valueOf(oldDate));
		Date newD = new Date(Long.valueOf(newDate));
		if (old.getYear() == newD.getYear()
				&& old.getMonth() == newD.getMonth()
				&& old.getDay() == newD.getDay()) {
			return true;
		}

		return false;
	}

	/**
	 * @Title: updateCookie
	 * @Description: 更新cookie
	 * @param cookie
	 * @param seconds
	 * @param response
	 * @return void
	 * @author tangxiang
	 * @date 2015年8月8日 上午11:40:40
	 */
	public static void updateCookie(Cookie cookie, int seconds,
			HttpServletResponse response) {
		cookie.setMaxAge(seconds);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * @Title: addCookie
	 * @Description: 增加一个新cookie，按秒设置cookie存在时间
	 * @param key
	 * @param value
	 * @param secs
	 * @param response
	 * @return void
	 * @author tangxiang
	 * @date 2015年8月8日 上午10:57:33
	 */
	public static void addCookie(String key, String value, int seconds,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(key.trim(), value.trim());
		cookie.setMaxAge(seconds);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * @Title: removeCookie
	 * @Description: 删除单个cookie
	 * @param name
	 * @param cookies
	 * @return void
	 * @author tangxiang
	 * @date 2015年8月8日 上午10:39:14
	 */
	public static void removeCookie(String name, Cookie cookie,
			HttpServletResponse response) {
		cookie.setMaxAge(0);// 设置0立即删除
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * @Title: removeCookie
	 * @Description: 删除单个cookie
	 * @param name
	 * @param cookies
	 * @return void
	 * @author tangxiang
	 * @date 2015年8月8日 上午10:39:14
	 */
	public static void removeCookie(String name, HttpServletResponse response,
			Cookie[] cookies) {
		Cookie cookie = getCookieValue(name, cookies);
		cookie.setMaxAge(0);// 设置0立即删除
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * @Title: isContainTime
	 * @Description: 计算两个时间的间隔天数
	 * @param newDate
	 * @param oldDate
	 * @return
	 * @return int
	 * @author tangxiang
	 * @date 2015年8月28日 下午5:27:10
	 */
	public static int isContainTime(Date newDate, Date oldDate) {
		Long newTime = newDate.getTime();
		Long oldTime = oldDate.getTime();

		Long days = (newTime - oldTime) / (1000 * 60 * 60 * 24);

		return days.intValue();
	}

	/**
	 * 比较两个日期的大小
	 * <p>
	 * Title: compare_date
	 * </p>
	 * 
	 * @author tc
	 *         <p>
	 *         Description:
	 *         </p>
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compareDate(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		int i = 0;
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				i = 1;
				return i;
			} else if (dt1.getTime() < dt2.getTime()) {
				i = -1;
				return i;
			} else if (dt1.getTime() == dt2.getTime()) {
				i = 0;
				return i;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return i;
	}

	public static boolean checkUserLogin(HttpSession httpSeesion) {
		User user = (User) httpSeesion.getAttribute("user");
		User userr = (User) httpSeesion.getAttribute("userr");
		User causer = (User) httpSeesion.getAttribute("causer");
		if (user == null || userr == null || causer == null) {
			return false;
		}
		return true;
	}

	/**
	 * 获取本机Ip
	 * 
	 * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。 获得符合
	 * <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getLocalIp() {
		String ip = null;
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface
						.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("获取本机Ip失败:异常信息:" + e.getMessage());
		}
		return ip;
	}

	
	/**
	 * 生成二维码图片 不存储 直接以流的形式输出到页面
	 * 
	 * @param content
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Image encodeQrcodeImage(String content) {
		Image image  = null;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content,
					BarcodeFormat.QR_CODE, 300, 300, hints);
			image = toBufferedImage(bitMatrix);
			
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return image;
	}
	
	/**
	 * 生成二维码图片 不存储 直接以流的形式输出到页面
	 * 
	 * @param content
	 * @param response
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, HttpServletResponse response) {
		if (!StringUtils.hasLength(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
	
		try {
			bitMatrix = multiFormatWriter.encode(content,
					BarcodeFormat.QR_CODE, 300, 300, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "png", response.getOutputStream());
				response.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
*/
	
	/**
	 * 生成二维码300*300图片 保存到本地
	 * 
	 * @param content
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, String fullPath) {
		if (!StringUtils.hasLength(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		
		try {
			bitMatrix = multiFormatWriter.encode(content,
					BarcodeFormat.QR_CODE, 300, 350, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			
			OutputStream os = new FileOutputStream(fullPath);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "jpg", os);
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 生成二维码图片 保存到本地
	 * 
	 * @param content
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, String fullPath,int height,int width) {
		if (!StringUtils.hasLength(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		
		try {
			bitMatrix = multiFormatWriter.encode(content,
					BarcodeFormat.QR_CODE, height, width, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			OutputStream os = new FileOutputStream(fullPath);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "jpg", os);
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 生成二维码图片(为图片添加文字)
	 * 
	 * @param content
	 * @param fullPath
	 * @param height
	 * @param width
	 * @param word 
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, String fullPath,int height,int width,String word) {
		if (!StringUtils.hasLength(content))
			return;
		
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		
		try {
			bitMatrix = multiFormatWriter.encode(content,BarcodeFormat.QR_CODE, width, height+50, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			OutputStream os = new FileOutputStream(fullPath);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "jpg", os);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//在二维码下方添加文字（文字居中）
            pressText(word, fullPath, fullPath, 1, Color.BLACK, 22,  width,  height+50) ;
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
     * @为图片添加文字
     * @param pressText 文字
     * @param newImg    带文字的图片
     * @param targetImg 需要添加文字的图片
     * @param fontStyle 
     * @param color
     * @param fontSize
     * @param width
     * @param heigh
     */
    public static void pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color, int fontSize, int width, int height) {
        
        try {
            File file = new File(targetImg);
            Image src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font("宋体", fontStyle, fontSize));
            
            //得到当前的font metrics
            FontMetrics fm = g.getFontMetrics();
            //计算文字开始的位置
            int stringWidth = fm.stringWidth(pressText);
            //x开始的位置：
            int startX = (width-stringWidth)/2;
            //y开始的位置：图片高度-（图片高度-图片宽度）/2
            int startY = height-(height-width)/2; 
            
            g.drawString(pressText, startX, startY);
            g.dispose();
            
            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		return image;
	}

	/**
	 * @Title: beanListToJsonStr
	 * @Description: javabean list 转化成json字符串
	 * @param list
	 * @return
	 * @return String
	 * @author tangxiang
	 * @date 2015年8月8日 下午3:10:45 
	 */
	public static String beanListToJsonStr(List<? extends Object> list)
	{
		JSONArray array = new JSONArray();
		for(Object obj:list)
		{
			array.add(obj);
		}
		
		return array.toString();
	}
	
	
	
	/**
	 * 检查文件是否存在
	 * @param fullName
	 * @return
	 * @date 2016年1月8日 下午1:34:58
	 */
	public static boolean checkFileIsExist(String fullName){
		File file = new File(fullName);
		return file.exists();
	}
	
	
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static Map parseXmlToList2(String xml) {
		Map retMap = new HashMap();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	/**
	 * 请求Map转换ＸＭＬ
	 * @param parameters
	 * @return
	 * @date 2016年1月12日 下午6:10:33
	 */
	public static String getRequestXml(SortedMap<String,String> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
	}
	
	public static String generateOrderNo(Long mid){
//		Date now = new Date();
//		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		String s = outFormat.format(now);
		
		//String strTime = s.substring(0, 8);// 8位日期
		final int ORDER_NO_LENGTH = 20;
		String orderNo = "";
		String random = "";
		String mNo = "";
		String nowTime = String.valueOf(System.nanoTime());//当前时间纳秒
		//System.out.println("nowTime:"+nowTime);
		if (mid == null){
			random = randomInt(ORDER_NO_LENGTH - nowTime.length());
		} else {
			mNo = mid + "";
			random = randomInt(ORDER_NO_LENGTH - nowTime.length() - mNo.length());
		}
		
		orderNo = mNo + nowTime + random;
		if (orderNo.length() > ORDER_NO_LENGTH){
			orderNo = orderNo.substring(0, 19);
			CommUtil.logger.error("CommUtil.generateOrderNo 注意：》》》》订单号超过20位了", null);
		}
		
		return orderNo;
	}
	
	
	/**
	 * 元转换成分
	 * 
	 * @param money
	 * @return
	 */
	public static String yuanConvertMinute(String amount) {
		if (amount == null) {
			return "";
		}
		// 金额转化为分为单位
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}
	
	
	  /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
	 * （人民币单位转换）元转分
	 */
	public static String yuanChangeFen(String amount){
		BigDecimal money = new BigDecimal("0.00");
		money = money.add(new BigDecimal(amount));
		Double doubleMoney = money.doubleValue();
		Integer integerMoney = (int) (doubleMoney * 100);
		return integerMoney.toString();
	}
	
	/**
	 * 请求中的map重写tostring方法
	 */
	public static String getMapToString(Map<String, String[]> map){
		
		if (null == map || map.size() == 0){
			return "";
		}
		
		StringBuffer params = new StringBuffer();
		
		params.append("mapToString: ");
		Set<Entry<String, String[]>> entrySet = map.entrySet();
		Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
		while (iterator.hasNext()){
			Entry<String, String[]> entry = iterator.next();
			
			String key = entry.getKey();
			String value = ( entry.getValue()!=null?entry.getValue()[0]:null );
			
			params.append(key).append("=").append(value).append("&");
		}
		
		return params.substring(0, params.length() - 1);
	}
	
	/**
	 * 请求中的map转为普通map
	 */
	public static Map<String, String> getRequestMapToMap(Map<String, String[]> map){
		
		if (null == map || map.size() == 0){
			return null;
		}
		
		Map<String, String> result = new HashMap<String, String>();
		
		Set<Entry<String, String[]>> entrySet = map.entrySet();
		Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
		while (iterator.hasNext()){
			Entry<String, String[]> entry = iterator.next();
			
			String key = entry.getKey();
			String value = ( entry.getValue() != null ? entry.getValue()[0] : null );
			
			result.put(key, value);
		}
		
		return result;
	}
	
	/**
	 * 请求中的字符map转为对象map
	 */
	public static Map<String, Object> getStrMapToObjMap(Map<String, String> map){
		
		if (null == map || map.size() == 0){
			return null;
		}
		
		Map<String, Object> result = new HashMap<>();
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			
			String key = entry.getKey();
			String value = ( entry.getValue() != null ? entry.getValue() : null );
			
			result.put(key, value);
		}
		
		return result;
	}
	
	/**
	 * map对象封装为class对象
	 */
	public static Object convertMap(Class<?> type, Map<String, String> map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
		
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
            	Object value = map.get(propertyName);
                
                Object[] args = new Object[1];
                args[0] = value;

                try {
                	descriptor.getWriteMethod().invoke(obj, args);
            	} catch (Exception e) {
            		
            	}
            }
        }
        return obj;
    }
	
	/**
	 * 处理double字符串四舍五入保留两位小数
	 */
	public static String formatDoubleString (String strAmount){
		
		try {
			Double douAmount = Double.parseDouble(strAmount);
			DecimalFormat df = new DecimalFormat("0.00"); 
			return df.format(douAmount);
		} catch (Exception e) {
			logger.error("转换金额格式失败");
			logger.error(e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * 比较两个BigDecimal类型的数据， 如果相等返回true
	 */
	public static Boolean compareToBigDecimal(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		
		if (null == bigDecimal1 && null == bigDecimal2){
			return true;
		}
		
		if (null != bigDecimal1 && null != bigDecimal2){
			return bigDecimal1.compareTo(bigDecimal2) == 0 ? true : false;
		}
		
		return false;
	}
	
	/**
	 * 获取汉字首位大写字母
	 */
	public static String getPinYinHeadChar(String str) throws BadHanyuPinyinOutputFormatCombination {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            HanyuPinyinOutputFormat out = new HanyuPinyinOutputFormat();
            out.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word, out);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }
	
	/**
	 * 判断字符串是否为数字
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
	/**
	 * 获取区间内随机数[min,max]
	 */
	public static int getRandom(int min,int max){ 
		max=max+1;

		int s = new Random().nextInt(max-min) + min;
		return s;
	}
}
