package com.webpos.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.webpos.entity.Award;
import com.webpos.entity.CaiFan;
import com.webpos.service.AwardService;
import com.webpos.tools.CommUtil;

/**
 * 后台同步获取订单列表并同步到Sheet_Ali表中
 * 
 * @author admin
 *
 */
public class PiaoGetUtils {
	private static Logger log = Logger.getLogger(PiaoGetUtils.class);
	// 是否已经初始化的标识
	private static boolean isInit = false;
	/***
	 * 是否是测试
	 */
	protected static boolean isTest = true;

	private static String starttime = "18";
	private static String endtime = "2018-04-18 00:02:59";
	/**
	 * 任务执行器
	 */
	// private static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8,
	// 0l, TimeUnit.MILLISECONDS,
	// new LinkedBlockingQueue<Runnable>(), new
	// DefaultThreadFactory("swift-order-get-excutor"));
	/**
	 * 任务分配器
	 */
//	private static ScheduledThreadPoolExecutor divider = new ScheduledThreadPoolExecutor(1,
//			new DefaultThreadFactory("swift-order-get-divider"));
//	
	/**
	 * 配置文件自动更新器
	 */
	// private static ScheduledThreadPoolExecutor timeIntervalIniter = new
	// ScheduledThreadPoolExecutor(1,
	// new DefaultThreadFactory("swift-order-get-init-timeIntervalIniter"));
	/**
	 * 订单查询与更新
	 */
	// private static ApiPayQueryController payQuery;
	//private static AwardService awardService;

//	/**
//	 * 默认线程创建工厂
//	 */
//	static class DefaultThreadFactory implements ThreadFactory {
//		private static final AtomicInteger poolNumber = new AtomicInteger(1);
//		private final ThreadGroup group;
//		private final AtomicInteger threadNumber = new AtomicInteger(1);
//		private final String namePrefix;
//
//		DefaultThreadFactory(String poolName) {
//			SecurityManager s = System.getSecurityManager();
//			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
//			namePrefix = poolName + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
//		}
//
//		public Thread newThread(Runnable r) {
//			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
//			if (t.isDaemon())
//				t.setDaemon(false);
//			if (t.getPriority() != Thread.NORM_PRIORITY)
//				t.setPriority(Thread.NORM_PRIORITY);
//			return t;
//		}
//	}

//	/**
//	 * 查询时间安排，以毫秒为单位
//	 */
//	// private static int SECONDS = 60 * 1000;
//	// protected static double timeInterval[] = {20 * SECONDS};
//	/**
//	 * 初始化程序
//	 * 
//	 * @param _payQuery
//	 * @param _detailsService
//	 */
//	public static void init(ApiPayQueryController apiPayQueryController, AwardService _awardService) {
//		if (!isInit) {
//			log.info(createHeader("开始初始化自动更新器", "#"));
//			// payQuery = _payQuery;
//			awardService = _awardService;
//			// 生产环境的时候为1分钟一次心跳,测试时间1秒为一次心跳
//			long initialDelay = isTest ? 1 * 1000 : 1 * 60 * 1000;
//			long period = isTest ? 5 * 1000 : 20 * 1000;// 心跳时间设置(每次30秒)
//
//			// long timeIntperiod = isTest ? 2 * 1000 : 20 * 1000;
//			// setXmlTime("789");
//
//			boolean r = getXmlTime();
//
//			if (r) {
//				divider.scheduleAtFixedRate(new DividerTask(), initialDelay, period, TimeUnit.MILLISECONDS);
//
//				isInit = true;
//
//				log.info(createHeader("开始初始化自动更新器", "#"));
//			}
//		}
//	}

//	private static boolean getXmlTime() {
//		boolean r = false;
//		
//		//System.out.println("###########stringaddress#########:");
//		String timeIntervalConfPath = PiaoGetUtils.class.getResource("/OrderUtils.xml").getFile().toString();
//		//System.out.println("###########stringaddres2222s#########:"+timeIntervalConfPath);
//		timeIntervalConfPath = timeIntervalConfPath.substring(0, timeIntervalConfPath.length() - 27)
//				+ "OrderUtils.xml";
//		try {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document doc = db.parse(timeIntervalConfPath);
//
//			String starttimeXpath = "root/starttime";
//
//			XPathFactory f = XPathFactory.newInstance();
//			XPath path = f.newXPath();
//			Node starttimeNode = (Node) path.evaluate(starttimeXpath, doc, XPathConstants.NODE);
//
//			starttime = starttimeNode.getTextContent();
//
//			System.out.println(starttime);
////			if (!DateUtils.isRightDateStr(starttime, "yyyyMMdd HH:mm:ss")) {
////				r = false;
////				log.error("读取" + timeIntervalConfPath + "发生了错误,时间格式有误，正确的格式为yyyy-MM-dd HH:mm:ss");
////			} else {
////				endtime = DateUtils.getTimeByMinute(starttime, 2);
////				endtime = endtime.substring(0, endtime.length() - 2) + "59";
////				r = true;
////			}
//
//		} catch (Exception e) {
//			log.error("读取" + timeIntervalConfPath + "发生了错误," + e);
//		}
//		return r;
//	}

//	private static void setXmlTime() {
//		// boolean r = false;
//		starttime = DateUtils.getTimeByMinute(starttime, 3);
//		endtime = DateUtils.getTimeByMinute(endtime, 3);
//		endtime = endtime.substring(0, endtime.length() - 2) + "59";
//		String timeIntervalConfPath = PiaoGetUtils.class.getResource("/OrderUpdatUtils.xml").getFile().toString();
//		timeIntervalConfPath = timeIntervalConfPath.substring(0, timeIntervalConfPath.length() - 27)
//				+ "SwiftOrderUtils.xml";
//		try {
//			org.dom4j.Document doc = new SAXReader().read(timeIntervalConfPath);
//			Element nameEle = doc.getRootElement().element("starttime");
//			nameEle.setText(starttime);
//
//			// 设置输出格式,默认createCompactFormat()，不带格式输出
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			// 设置输出编码
//			format.setEncoding("utf-8");
//			XMLWriter xw = new XMLWriter(new FileWriter(new File(timeIntervalConfPath)), format);
//
//			xw.write(doc);
//			xw.close();
//
//			// log.error("写入成功");
//		} catch (Exception e) {
//			log.error("写入" + timeIntervalConfPath + "发生了错误," + e);
//		}
//		// return r;
//	}

//	/**
//	 * 服务器关闭的时候需要关闭线程池
//	 */
//	public static void showdown() {
//
//		log.info(createHeader("威富通订单自动更新器关闭", "#"));
//
//		if (null != divider && (!divider.isShutdown())) {
//			divider.shutdown();
//		}
//		// if (null != executor && (!executor.isShutdown())) {
//		// executor.shutdown();
//		// }
//		log.info(createHeader("威富通订单自动更新器关闭完成", "#"));
//	}

	/**
	 * 显示标题的公共方法
	 * 
	 * @param title显示的标题
	 * @param header分隔符
	 * @return返回的标题字符串
	 */
	public static String createHeader(String title, String header) {
		if (StringUtils.isBlank(title) || StringUtils.isBlank(header))
			return "";
		String stars = StringUtils.repeat(header, 86);
		String centered = StringUtils.center(title, 83, header);
		String heading = StringUtils.join(new Object[] { stars, centered, stars }, "\n");
		return "\n" + heading;
	}
	// 初始化队列

	// //是否需要循环初始(默认true)
	// private static boolean isLoopInitConfig = true;
	// //是否在每次心跳的时候，将没有处理的日志显示出来(默认true)
	// private static boolean isShowEachHeartbeatDisplayLog = true;

//	/**
//	 * 任务分配器的每一个订单任务的分配
//	 * 
//	 * @author admin
//	 *
//	 */
//	static class DividerTask implements Runnable {
//		@Override
//		public void run() {
//
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date newDate = null;
//			try {
//				newDate = dateFormat.parse(starttime);
//			} catch (ParseException pe) {
//				// logger.error(pe.getMessage(),pe);
//				newDate = null;
//			}
//			double minute = DateUtils.getIntervaltime(new Date(), newDate, 5, 365);
//			//System.out.println("模拟去威富通拿数据111_____________" + minute);
//			if (minute >= 3.0) {
//				boolean r = getDataFromSwiftPass();
//				
//				try{swiftPassService.callproduce();}catch(Exception e){
//					 //System.out.println("模拟去威富通拿数据_____________ERROR:" + e.getMessage());
//				}
//			   // System.out.println("模拟去威富通拿数据_____________" + starttime);
//				if (r) {
//					setXmlTime();
//				}
//
//			}
//
//		}
//
//	}

	public static boolean getDataFromOnlineByDate(AwardService awardService,String date,int max) {
		boolean r = true;
		
		List<CaiFan> CaiFans = new ArrayList<CaiFan>();
		
			try {
				
				String resString = com.webpos.tools.HttpRequest.sendGet("http://e.apiplus.net/daily.do?token=td8bb600ad2eb3d7ak&code=gd11x5&format=json&date="+date, null);
				//System.out.println("resString:" + resString);
				
				JSONObject os = new JSONObject(resString);
				if(os!=null) {
					int rows = os.getInt("rows");
					if(rows>0) {
						org.json.JSONArray arrs = os.getJSONArray("data");
						for(int i=0;i<arrs.length();i++) {
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
			for(CaiFan c:CaiFans) {
				String name = c.getQiname();
				String qi = name.substring(2, name.length());
				int shu = Integer.parseInt(name.substring(name.length()-2, name.length()));
				String[] nos5 =  c.getCode().split(",");
				if(shu<max) {
					//System.out.println("qi:"+qi);
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

						awardService.kaijiang(finals, qi,false);
					}
				}
				
			}
			}catch (Exception e) {
				System.out.println("exception:" + e.getMessage().toString());
				r = false;
			}
	
		

		return r;

	}

}