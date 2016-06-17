package com.zot.xing.view.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;

public class CancelWorkOrderAction implements PrefixService {
	private static Logger log = Logger.getLogger(CancelWorkOrderAction.class);
	
	@Override
	public String action(Map<String, String> context) {
		String orderId = context.get("orderId");
		
		try
		{
			WorkOrderAS workOrderAS = new WorkOrderASImpl();
			workOrderAS.cancelWorkOrder(orderId);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage());
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}
}
