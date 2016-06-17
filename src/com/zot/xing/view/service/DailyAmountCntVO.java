package com.zot.xing.view.service;

import java.util.List;

public class DailyAmountCntVO {
	private String title;
	private List<AmountVO> amounts;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<AmountVO> getAmounts() {
		return amounts;
	}
	public void setAmounts(List<AmountVO> amounts) {
		this.amounts = amounts;
	}
	
	
}
