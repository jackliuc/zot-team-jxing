package com.zot.web.rs.resource;

public class CurrentStatus {
	private int inQueueCars;
	private int averageServiceTime;
	private int finishedCars;
	private int forecastServiceTime;
	
	public int getInQueueCars() {
		return inQueueCars;
	}
	public void setInQueueCars(int inQueueCars) {
		this.inQueueCars = inQueueCars;
	}
	public int getAverageServiceTime() {
		return averageServiceTime;
	}
	public void setAverageServiceTime(int averageServiceTime) {
		this.averageServiceTime = averageServiceTime;
	}
	public int getFinishedCars() {
		return finishedCars;
	}
	public void setFinishedCars(int finishedCars) {
		this.finishedCars = finishedCars;
	}
	public int getForecastServiceTime() {
		return forecastServiceTime;
	}
	public void setForecastServiceTime(int forecastServiceTime) {
		this.forecastServiceTime = forecastServiceTime;
	}
	
}
