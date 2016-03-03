package com.zot.xing.view.service;

import java.util.Date;

import com.zot.xing.view.common.OrderStatus;

public class XingWorkOrderVO {
	
	private String workOrderId;//订单号
	private int workOrderType;//订单类型，对应服务定义表中的serviceId
	private String serviceName;//服务名称
	private String serviceImg;//服务名称对应的图片
	private Date serviceTime;//服务时间
	private String disCreateTime;//创建时间
	private String carNo;//车牌号
	private int evalType;//评价类型
	private String evalDesc;//评价内容
	private String custId;//客户Id
	private String servicePerson;//服务人员
	private OrderStatus status;//订单状态
	private String statusDes;//订单状态描述，直接国际化的显示，如正在进行，已完成等
	private String disServiceTime;//界面展现的格式化服务时间
	private int isDisplayEval = 0;//是否显示评价：1-显示；0-不显示
	private String disEvalType;//显示评价类型

	public String getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}
	public int getWorkOrderType() {
		return workOrderType;
	}
	public void setWorkOrderType(int workOrderType) {
		this.workOrderType = workOrderType;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Date getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public int getEvalType() {
		return evalType;
	}
	public void setEvalType(int evalType) {
		this.evalType = evalType;
	}
	public String getEvalDesc() {
		return evalDesc;
	}
	public void setEvalDesc(String evalDesc) {
		this.evalDesc = evalDesc;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getServicePerson() {
		return servicePerson;
	}
	public void setServicePerson(String servicePerson) {
		this.servicePerson = servicePerson;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getStatusDes() {
		return statusDes;
	}
	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}
	public String getDisServiceTime() {
		return disServiceTime;
	}
	public void setDisServiceTime(String disServiceTime) {
		this.disServiceTime = disServiceTime;
	}
	public String getServiceImg() {
		return serviceImg;
	}
	public void setServiceImg(String serviceImg) {
		this.serviceImg = serviceImg;
	}	
	public String getDisCreateTime() {
		return disCreateTime;
	}
	public void setDisCreateTime(String disCreateTime) {
		this.disCreateTime = disCreateTime;
	}
	public int getIsDisplayEval() {
		return isDisplayEval;
	}
	public void setIsDisplayEval(int isDisplayEval) {
		this.isDisplayEval = isDisplayEval;
	}
	public String getDisEvalType() {
		return disEvalType;
	}
	public void setDisEvalType(String disEvalType) {
		this.disEvalType = disEvalType;
	}
}
