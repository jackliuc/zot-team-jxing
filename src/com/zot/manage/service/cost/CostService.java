package com.zot.manage.service.cost;

import java.io.File;
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
import com.zot.util.FileAS;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.employ.EmployBO;

public class CostService 
{
	public static void addCost(Cost cost)
	{
		//查询余额
		CostBalAS costBalAS = new CostBalASImpl();
		CostBal costBal = costBalAS.queryCostBal();
		
		//判断本次新增是收入还是支出
		float cardBal = costBal.getCostBalance();
		if (Constant.COST_INCOME.equals(cost.getCostType()))//收入
		{
			cardBal = cardBal + cost.getCostAmount();
		}
		else//支出
		{
			cardBal = cardBal - cost.getCostAmount();
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
	
	public static void importExcel(File file, int index)
	{
		List<String> lines = FileAS.readExcel(file, index);
		Cost cost = null;
		if (lines != null)
		{
			for (String line : lines)
			{
				cost = cvt2Cost(line);
				if (cost != null)
				{
					addCost(cost);
				}
			}
		}
	}
	
	private static Cost cvt2Cost(String line)
	{
		Cost cost = null;
		String[] arry = line.split(FileAS.FIELD_SEP);
		if (arry != null && arry.length >= 6)
		{
			cost = new Cost();
			cost.setCostTime(arry[0]);
			cost.setCostOperator(arry[5]);
			cost.setCostSubType(arry[2]);
			cost.setCostType(arry[1]);
			cost.setCostAmount(Float.parseFloat(arry[4]));
			cost.setRemark(arry[3]);
		}

		return cost;
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
	
	public static void main(String[] args) 
	{
		CostService.importExcel(new File("D:/壹号车/汇总明细-07-03.xls"), 4);
		System.out.println("finished.");
	}
}
