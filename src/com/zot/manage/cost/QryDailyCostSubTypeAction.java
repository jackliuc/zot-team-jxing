package com.zot.manage.cost;

import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import com.zot.manage.service.cost.CostService;
import com.zot.manage.service.cost.DailyCostSubTypeCntVO;
import com.zot.util.DateAS;
import com.zot.view.contorler.PrefixService;

public class QryDailyCostSubTypeAction implements PrefixService 
{
	private static Logger log = Logger.getLogger(QryDailyCostSubTypeAction.class);
	
	@Override
	public DailyCostSubTypeCntVO action(Map<String, String> context) {

		DailyCostSubTypeCntVO costSubTypeCntVO = null;
		try
		{	
			//Date currentDay = new Date();
			Date currentDay = DateAS.getDateFromString("2016-07-06", "yyyy-MM-dd");
			costSubTypeCntVO = CostService.qryDailyCostSubTypeCnt(currentDay);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			costSubTypeCntVO = null;
		}
		
		return costSubTypeCntVO;
	}
}
