package com.zot.xing.view.service;

public class DailyWorkOrderCntVO {
	private DailyServiceCntVO dailyServiceCnt;
	private DailyAmountCntVO dailyAmountCnt;
	
	public DailyServiceCntVO getDailyServiceCnt() {
		return dailyServiceCnt;
	}
	public void setDailyServiceCnt(DailyServiceCntVO dailyServiceCnt) {
		this.dailyServiceCnt = dailyServiceCnt;
	}
	public DailyAmountCntVO getDailyAmountCnt() {
		return dailyAmountCnt;
	}
	public void setDailyAmountCnt(DailyAmountCntVO dailyAmountCnt) {
		this.dailyAmountCnt = dailyAmountCnt;
	}
}
