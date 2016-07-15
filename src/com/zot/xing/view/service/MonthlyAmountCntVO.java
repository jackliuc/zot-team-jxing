package com.zot.xing.view.service;

import java.util.List;

public class MonthlyAmountCntVO {
	private String title;
	private List<NameAmountVO> nameAmounts;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<NameAmountVO> getNameAmounts() {
		return nameAmounts;
	}
	public void setNameAmounts(List<NameAmountVO> nameAmounts) {
		this.nameAmounts = nameAmounts;
	}
}
