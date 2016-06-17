package com.zot.manage.cost;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zot.manage.cost.dao.Cost;
import com.zot.manage.cost.dao.CostAS;
import com.zot.manage.cost.dao.CostASImpl;
import com.zot.view.contorler.PrefixService;

public class AddCostAction implements PrefixService {

	//private static Logger logger = Logger.getLogger(AddCostAction.class);
	
	@Override
	public List<Cost> action(Map<String, String> context) {
		String costChangesJsonStr = context.get("data");
		
		List<Cost> costs = JSONObject.parseArray(costChangesJsonStr, Cost.class);
		
		CostAS cas = new CostASImpl();
		
		cas.saveCosts(costs);
		
		List<Cost> result = cas.listCost(null);
		
		return result;
	}

}
