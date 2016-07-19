package com.zot.xing.dao.card;

import java.util.Date;

public class CardLogBO {
	private String id;
	private String cardNo;
	private String custId;
	private String operId;
	private String logType;
	private Date createTime;
	private Float amount;
	private Float beforeAmt;
	private Float afterAmt;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Float getBeforeAmt() {
		return beforeAmt;
	}
	public void setBeforeAmt(Float beforeAmt) {
		this.beforeAmt = beforeAmt;
	}
	public Float getAfterAmt() {
		return afterAmt;
	}
	public void setAfterAmt(Float afterAmt) {
		this.afterAmt = afterAmt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
