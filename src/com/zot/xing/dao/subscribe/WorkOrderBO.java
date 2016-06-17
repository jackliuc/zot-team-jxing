/**
 * 订单对象
 */
package com.zot.xing.dao.subscribe;

import java.util.Date;
import java.util.List;

/**
 * @author jack
 * 爱行订单对象
 */
public class WorkOrderBO {
	/**
	 * 订单号
	 */
	private String orderId = null;
	private Date createTime = null;
	/**
	 * 预定时间
	 * 
	 */
	private Date orderTime = null;
	private Date remindTime = null;
	
	
	private String custId = null;
	private String carno = null;
	private String phoneno = null;
	private String status = null;
	/**
	 * 服务对象id
	 * 
	 */
	private String serviceObjId = null;
	/**
	 * 评价类型：...
	 * 
	 */
	private Integer evalDescType = null;
	/**
	 * 评价内容
	 * 
	 */
	private String evalDesc = null;

	/** 如果是投诉一定要有处理结果，并且向客户反馈
	  * 
	  */
	private String evalResult = null;
	private String remark = null;
	private String payType = null;
	private Float amount = null;
	private String arrived = null;
	
	private List<WorkOrderDetailBO> details;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getServiceObjId() {
		return serviceObjId;
	}

	public void setServiceObjId(String serviceObjId) {
		this.serviceObjId = serviceObjId;
	}

	public Integer getEvalDescType() {
		return evalDescType;
	}

	public void setEvalDescType(Integer evalDescType) {
		this.evalDescType = evalDescType;
	}

	public String getEvalDesc() {
		return evalDesc;
	}

	public void setEvalDesc(String evalDesc) {
		this.evalDesc = evalDesc;
	}

	public String getEvalResult() {
		return evalResult;
	}

	public void setEvalResult(String evalResult) {
		this.evalResult = evalResult;
	}

	public List<WorkOrderDetailBO> getDetails() {
		return details;
	}

	public void setDetails(List<WorkOrderDetailBO> details) {
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
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
