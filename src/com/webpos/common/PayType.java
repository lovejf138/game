package com.webpos.common;

/**
 * 支付类型
 * 
 */
public enum PayType {

	/**
	 * 微信支付
	 *  
	 */
	WEIXIN_PAY("微信支付","0"),
	
	/**
	 *  支付宝支付 
	 */
	ALI_PAY("支付宝支付","1"),
	
	/**
	 * QQ钱包支付
	 */
	QQ_PAY("QQ钱包支付","2"),
	
	/**
	 * 京东钱包支付
	 */
	JD_PAY("京东钱包支付","4"),
	/**
	 * 百度钱包支付
	 */
	BD_PAY("百度钱包支付","3");
	
	public final static String ALL = "ALL";
	
	private String desc;//中文描述
	private String type;//数据库保存的数字
	private PayType(String desc,String type){
		this.desc = desc;
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public String getDesc(){
		return this.desc;
	}
	
	public static PayType castByType(String type){
		PayType payTypeReturn = null;
		for (PayType payType : PayType.values()) {
			if (payType.getType().equals(type)) {
				payTypeReturn = payType;
				break;
			}
		}
		return payTypeReturn;
	}
}
