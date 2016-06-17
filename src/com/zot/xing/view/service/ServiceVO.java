package com.zot.xing.view.service;

public class ServiceVO {
	private String serviceKey;
	private String serviceName;
	private int serviceCnt = 0;
	private Float amount;
	
	public ServiceVO()
	{
	}
	
	public ServiceVO(String serviceName, int serviceCnt, Float amount)
	{
		this.serviceName = serviceName;
		this.serviceCnt = serviceCnt;
		this.amount = amount;
	}
	
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServiceCnt() {
		return serviceCnt;
	}
	public void setServiceCnt(int serviceCnt) {
		this.serviceCnt = serviceCnt;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	public void addAmount(float amount)
	{
		if (this.amount == null)
		{
			this.amount = Float.valueOf(amount);
		}
		else
		{
			this.amount = Float.valueOf(this.amount.floatValue() + amount);
		}
	}
	
	public void addServiceCnt()
	{
		this.serviceCnt++;
	}
}
