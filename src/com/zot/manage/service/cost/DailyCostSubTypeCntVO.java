package com.zot.manage.service.cost;

import java.util.List;

import com.zot.manage.cost.dao.CostSubTypeCnt;

public class DailyCostSubTypeCntVO
{
	private String title;
	private List<CostSubTypeCnt> costSubTypeCnts;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<CostSubTypeCnt> getCostSubTypeCnts() {
		return costSubTypeCnts;
	}
	public void setCostSubTypeCnts(List<CostSubTypeCnt> costSubTypeCnts) {
		this.costSubTypeCnts = costSubTypeCnts;
	}
}
