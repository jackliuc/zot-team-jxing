package com.zot.manage.cost.dao;

import java.util.Date;

public class CostBal {
	private String costCarNo;
	private float costBalance;
	private Date lastUpdDate;
	public String getCostCarNo() {
		return costCarNo;
	}
	public void setCostCarNo(String costCarNo) {
		this.costCarNo = costCarNo;
	}
	public float getCostBalance() {
		return costBalance;
	}
	public void setCostBalance(float costBalance) {
		this.costBalance = costBalance;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
}
