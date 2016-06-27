package com.zot.xing.dao.subscribe;

import java.util.Date;

public class EnableWorkOrderBO extends WorkOrderBO
{
	private Date finishedTime = null;
	private String orderType = null;

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
}
