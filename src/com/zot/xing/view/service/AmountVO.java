package com.zot.xing.view.service;

public class AmountVO {
	private String payType;
	private String payTypeName;
	private Float amount;
	
	public AmountVO()
	{
	}
	
	public AmountVO(String payTypeName, Float amount)
	{
		this.payTypeName = payTypeName;
		this.amount = amount;
	}
	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
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
}
