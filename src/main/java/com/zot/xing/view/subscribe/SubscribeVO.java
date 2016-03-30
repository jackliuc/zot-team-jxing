package com.zot.xing.view.subscribe;

public class SubscribeVO {
	/**
	 * 价格
	 */
	private Long price = null;
	
	/**
	 * 预约时间
	 */
	
	private String subTime = null;
	
	/**
	 * 完成时间
	 */
	private String overTime = null;
	
	private String subType = null;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	
	
}
