package com.zot.manage.cost;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.manage.service.cost.CostService;
import com.zot.manage.service.cost.CostVO;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

public class AddCostAction implements PrefixService {

	private static Logger log = Logger.getLogger(AddCostAction.class);
	
	@Override
	public String action(Map<String, String> context) {
		String costChangesJsonStr = context.get("data");
		
		try
		{
			CostVO cost = JSONObject.parseObject(costChangesJsonStr, CostVO.class);
			
			CostService.addCost(cost);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}

}
