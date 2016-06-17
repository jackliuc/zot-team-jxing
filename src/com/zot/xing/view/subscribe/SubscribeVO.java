package com.zot.xing.view.subscribe;

public class SubscribeVO {
	/**
	 * 微信号
	 */
	private String wechatno = null;
	
	/**
	 * 预约时间
	 */
	
	private String orderTime = null;
	
	/**
	 * 车牌号
	 */
	private String carno = null;
	
	/**
	 * 预约订单类型
	 */
	private String orderType = null;
	
	/**
	 * 是否达到，对于微信预约则，固定为N；登记时固定为Y
	 */
	private String arrived = null;
	
	/**
	 * 手机号码
	 */
	private String phoneno = null;

	public String getWechatno() {
		return wechatno;
	}

	public void setWechatno(String wechatno) {
		this.wechatno = wechatno;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getArrived() {
		return arrived;
	}

	public void setArrived(String arrived) {
		this.arrived = arrived;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
}
