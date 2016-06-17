package com.zot.xing.dao.subscribe;

import java.util.Date;

public class EnableWorkOrderBO {
	private String orderId = null;
	private Date createTime = null;
	private Date finishedTime = null;
	private String orderType = null;
	private String payType = null;
	private Float amount = null;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
}
