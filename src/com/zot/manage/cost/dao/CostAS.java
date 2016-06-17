package com.zot.manage.cost.dao;

import java.util.List;

public interface CostAS {

	List<Cost> listCost(Cost c);
	
	Cost createCost(Cost c);
	
	Cost updateCost(Cost c);
	
	List<Cost> saveCosts(List<Cost> costs);
}
