package com.zot.xing.dao.subscribe;

import java.util.Date;

public class WorkOrderDetailBO 
{	
	private String orderId;
	
	/**
	 * 创建时间
	 * 
	 */
	private Date createTime = null;
	/**
	 * 服务时间
	 * 
	 */
	private Date serviceTime = null;
	
	/**
	 * 完成时间
	 * 
	 */
	private Date finishedTime = null;
	
	/**
	 * 订单明细状态
	 */
	private String status = null;
	
	private String oderPorDesc;
	private String employId;
	/**
	 * 订单类型...
	 */
	private String orderType = null;
	private String remark;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOderPorDesc() {
		return oderPorDesc;
	}
	public void setOderPorDesc(String oderPorDesc) {
		this.oderPorDesc = oderPorDesc;
	}
	public String getEmployId() {
		return employId;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	public Date getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
