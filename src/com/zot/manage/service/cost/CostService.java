package com.zot.manage.service.cost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zot.manage.cost.dao.Cost;
import com.zot.manage.cost.dao.CostAS;
import com.zot.manage.cost.dao.CostASImpl;
import com.zot.manage.cost.dao.CostBal;
import com.zot.manage.cost.dao.CostBalAS;
import com.zot.manage.cost.dao.CostBalASImpl;
import com.zot.manage.service.employ.EmployService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.employ.EmployBO;

public class CostService 
{
	public static void addCost(CostVO cost)
	{
		//查询余额
		CostBalAS costBalAS = new CostBalASImpl();
		CostBal costBal = costBalAS.queryCostBal();
		
		//判断本次新增是收入还是支出
		float cardBal = costBal.getCostBalance();
		if (Constant.COST_INCOME.equals(cost.getCostType()))//收入
		{
			cardBal += cost.getCostAmount();
		}
		else//支出
		{
			cardBal -= cost.getCostAmount();
		}
		costBal.setCostBalance(cardBal);
		
		//进行消费记录的新增，以及卡余额的更新
		CostAS costAS = new CostASImpl();
		cost.setCostBalance(cardBal);
		cost.setCostCarNo(costBal.getCostCarNo());
		costAS.createCost(cost);
		
		costBal.setLastUpdDate(new Date());
		costBalAS.updateCostBal(costBal);
	}
	
	public static List<CostVO> qryCosts(CostVO cond)
	{
		CostAS costAS = new CostASImpl();
		List<Cost> costs = costAS.queryCost(cond);
		
		List<CostVO> costVOs = new ArrayList<CostVO>();
		if (costs != null && costs.size() > 0)
		{
			for (Cost cost : costs)
			{
				costVOs.add(cvt2CostVO(cost));
			}
		}
		
		return costVOs;
	}
	
	private static CostVO cvt2CostVO(Cost cost)
	{
		CostVO costVO = new CostVO(cost);
		costVO.setDisCostSubType(Constant.COST_SUBTYPE_MAP.get(cost.getCostSubType()));
		costVO.setDisCostType(Constant.COST_TYPE_MAP.get(cost.getCostType()));
		EmployBO employ = EmployService.getEmploy(cost.getCostOperator());
		if (employ != null)
		{
			costVO.setDisOperator(employ.getEmployName());
		}
		
		return costVO;
	}
}
