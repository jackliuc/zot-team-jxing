package com.zot.manage.cost;

import java.util.List;
import java.util.Map;

import com.zot.manage.cost.dao.Cost;
import com.zot.manage.cost.dao.CostAS;
import com.zot.manage.cost.dao.CostASImpl;
import com.zot.view.contorler.PrefixService;

public class ListCostAction implements PrefixService {

	@Override
	public List<Cost> action(Map<String, String> context) {
		
		CostAS cas = new CostASImpl();
		
		List<Cost> result = cas.listCost(null);
		
		return result;
	}

}
