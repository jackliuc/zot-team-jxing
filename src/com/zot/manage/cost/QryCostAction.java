package com.zot.manage.cost;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.manage.service.cost.CostService;
import com.zot.manage.service.cost.CostVO;
import com.zot.view.contorler.PrefixService;

public class QryCostAction implements PrefixService {

	private static Logger log = Logger.getLogger(QryCostAction.class);
	
	@Override
	public List<CostVO> action(Map<String, String> context) {
		String costType = context.get("costType");
		String operatorId = context.get("operatorId");
		
		List<CostVO> costVOs = null;
		try
		{	
			CostVO cond = new CostVO();
			cond.setCostType(costType);
			cond.setCostOperator(operatorId);
			costVOs = CostService.qryCosts(cond);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			costVOs = Collections.emptyList();
		}
		
		return costVOs;
	}
}
