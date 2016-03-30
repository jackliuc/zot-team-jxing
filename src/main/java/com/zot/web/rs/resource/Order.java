package com.zot.web.rs.resource;

public class Order {
	private String serviceNumber;
	private String carNumber;
	private String serviceType;
	private String bestArrivalTime;
	private String serviceDate;
	
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getBestArrivalTime() {
		return bestArrivalTime;
	}
	public void setBestArrivalTime(String bestArrivalTime) {
		this.bestArrivalTime = bestArrivalTime;
	}
	/**
	 * @return the serviceNumber
	 */
	public String getServiceNumber() {
		return serviceNumber;
	}
	/**
	 * @param serviceNumber the serviceNumber to set
	 */
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	/**
	 * @return the serviceDate
	 */
	public String getServiceDate() {
		return serviceDate;
	}
	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
}
