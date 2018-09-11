package com.webpos.common;

/**
 * 支付方式
 * 
 */
public enum PayWay {

	
	/**
	 * 扫码支付
	 *  
	 */
	SCAN_PAY("2","扫码支付",GlobalConstant.OFFLINE),
	
	/**
	 * 固定二维码支付
	 */
	JSAPI_QR_CODE_PAY("1","固定二维码支付",GlobalConstant.ONLINE),
	
	/**
	 *  二维码支付
	 */
	QR_CODE_PAY("0","二维码支付",GlobalConstant.OFFLINE);
	
	private String type;//数据库保存的数字
	private String desc;//中文描述
	private String line;//线上线下消费方式
	private PayWay(String type,String desc,String line){
		this.type = type;
		this.desc = desc;
		this.line = line;
	}
	public String getType(){
		return this.type;
	}
	public String getLine(){
		return this.line;
	}
	public String getDesc(){
		return this.desc;
	}
	public static PayWay getPayWayByType(String type){
		PayWay payWayReturn = null;
		for (PayWay payWay : PayWay.values()) {
			if (payWay.getType().equals(type)) {
				payWayReturn = payWay;
				break;
			}
		}
		return payWayReturn;
	}
}
