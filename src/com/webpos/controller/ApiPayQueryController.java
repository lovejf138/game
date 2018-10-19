//package com.webpos.controller;
//
//import javax.annotation.PreDestroy;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import com.webpos.service.AwardService;
//
//@Controller
//public class ApiPayQueryController implements InitializingBean, DisposableBean {
//
//	private Logger log = Logger.getLogger(getClass());
//	@Autowired
//	private AwardService awardService;
//	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		log.info(PiaoGetUtils.createHeader("自动更新器开始调用更新程序", "*"));
////		 InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
////		 String hostAddress = address.getHostAddress();//192.168.0.121      
//		// System.out.println("address:"+hostAddress);
//		// if(hostAddress.equals("10.116.147.118")){//120.25.126.168
////			 PiaoGetUtils.init(this,awardService);
//		// }
//		
//		//PiaoGetUtils.getDataFromOnlineByDate(awardService,"2018-10-19",40);
//		
//	}
//
//	@PreDestroy
//	public void preDestroy() {
//		log.info(PiaoGetUtils.createHeader("自动更新器开始调用关闭程序", "*"));
//		//OrderUpdatUtils.showdown();
//	//	PiaoGetUtils.getDataFromOnlineByDate(null);
//	}
//
//	@Override
//	public void destroy() throws Exception {
//
//	}
//}
