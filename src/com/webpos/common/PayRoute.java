package com.webpos.common;

public enum PayRoute {

	/**
	 * 标准通道
	 */
	STANDARD_ROUTE(0, "标准通道"),
	/**
	 * 光大通道
	 */
	GUANGDA_ROUTE(1, "深圳光大通道"),
	/**
	 * 民生通道
	 */
	MINSHENG_ROUTE(2, "成都民生通道"),
	/**
	 * 浦发通道
	 */
	PUFA_ROUTE(3, "广州浦发通道"),
	/**
	 * 中信通道
	 */
	ZHONGXIN_ROUTE(4, "深圳中信通道"),
	/**
	 * 厦门民生通道
	 */
	XIAMEN_MINSHENG_ROUTE(5, "厦门民生通道"),
	
	/**
	 * 香港TAS通道
	 */
	HK_TAS_ROUTE(6, "香港TAS通道"),
	
	/**
	 * 香港TAS通道(新系统)
	 */
	HK_TAS_NEW_ROUTE(7, "香港TAS通道（新）"),
	
	/**
	 * 个人钱包通道
	 */
	PERSONAL_ROUTE(8, "个人钱包通道");
	
	private Integer type;//数据库中保存的数字
	private String desc;//中文描述
	
	private PayRoute(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}
	
	public String getDesc() {
		return desc;
	}
	
	/**
	 * @param type
	 * @return
	 */
	public static PayRoute castByType(Integer type){
		PayRoute payRoute = null;
		for (PayRoute route : PayRoute.values()) {
			if (route.getType() == type) {
				payRoute = route;
				break;
			}
		}
		return payRoute;
	}

}
