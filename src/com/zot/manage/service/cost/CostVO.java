package com.zot.manage.service.cost;

import com.zot.manage.cost.dao.Cost;

public class CostVO extends Cost{
	public CostVO()
	{
	}
	
	public CostVO(Cost cost)
	{
		this.setCostAmount(cost.getCostAmount());
		this.setCostBalance(cost.getCostBalance());
		this.setCostCarNo(cost.getCostCarNo());
		this.setCostId(cost.getCostId());
		this.setCostOperator(cost.getCostOperator());
		this.setCostSubType(cost.getCostSubType());
		this.setCostTime(cost.getCostTime());
		this.setCostType(cost.getCostType());
		this.setCreateTime(cost.getCreateTime());
		this.setRemark(cost.getRemark());
	}
	
	private String disOperator;
	private String disCostType;
	private String disCostSubType;
	public String getDisOperator() {
		return disOperator;
	}

	public void setDisOperator(String disOperator) {
		this.disOperator = disOperator;
	}

	public String getDisCostType() {
		return disCostType;
	}

	public void setDisCostType(String disCostType) {
		this.disCostType = disCostType;
	}

	public String getDisCostSubType() {
		return disCostSubType;
	}

	public void setDisCostSubType(String disCostSubType) {
		this.disCostSubType = disCostSubType;
	}
}
