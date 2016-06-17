package com.zot.xing.view.service;

public class WorkOrderVO {
	
	private String workOrderId;//订单号
	private String orderType;//服务类型，对应服务定义表中的serviceId
	private String serviceName;//服务名称
	private String serviceImg;//服务名称对应的图片
	private String disCreateTime;//创建时间
	private String disOrderTime;//预约时间
	private String disServiceTime;//服务时间
	private String carNo;//车牌号
	
	private String custId;//客户Id
	private String phoneno;//客户手机号
	
	private int isDisplayEval = 0;//是否显示评价：1-显示；0-不显示
	private String disEvalType;//显示评价类型
	private int evalType;//评价类型
	private String evalDesc;//评价内容
	
	private String employId;//服务人员
	private String status;//订单状态
	private String statusDes;//显示状态
	
	private int canCancel = 0;//订单是否可取消：1-可取消；0-不能取消
	
	private String payType;//付款方式
	private String amount; //付款金额
	public String getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
	public String getDisOrderTime() {
		return disOrderTime;
	}
	public void setDisOrderTime(String disOrderTime) {
		this.disOrderTime = disOrderTime;
	}
	public String getDisServiceTime() {
		return disServiceTime;
	}
	public void setDisServiceTime(String disServiceTime) {
		this.disServiceTime = disServiceTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
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
	public String getEmployId() {
		return employId;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCanCancel() {
		return canCancel;
	}
	public void setCanCancel(int canCancel) {
		this.canCancel = canCancel;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatusDes() {
		return statusDes;
	}
	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}
	
}
