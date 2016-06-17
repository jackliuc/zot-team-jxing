package com.zot.xing.view.service;

import java.util.List;

public class DailyServiceCntVO {
	private String title;
	private List<ServiceVO> services;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ServiceVO> getServices() {
		return services;
	}
	public void setServices(List<ServiceVO> services) {
		this.services = services;
	}
}
