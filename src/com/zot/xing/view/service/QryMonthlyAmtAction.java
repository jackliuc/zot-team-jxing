package com.zot.xing.view.service;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.xing.view.subscribe.WorkOrderService;

public class QryMonthlyAmtAction implements PrefixService {
	private static Logger log = Logger.getLogger(QryMonthlyAmtAction.class);
	
	@Override
	public MonthlyAmountCntVO action(Map<String, String> context) {

		MonthlyAmountCntVO monthlyAmt = null;
		try
		{
			Date current = new Date();
			monthlyAmt = WorkOrderService.queryMonthlyAmount(current);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage());
			monthlyAmt = new MonthlyAmountCntVO();
		}
		
		return monthlyAmt;
	}
}
