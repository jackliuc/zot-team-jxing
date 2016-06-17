package com.zot.xing.view.subscribe;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.xing.view.common.BookInfoVO;

public class OrderTypeSelectAction implements PrefixService 
{
	private static Logger logger = Logger.getLogger(OrderTypeSelectAction.class);
	
	@Override
	public BookInfoVO action(Map<String, String> context) 
	{
		BookInfoVO infov = null;
		
		try
		{
			String orderType = context.get("orderType");
			logger.info("orderType:" + orderType);
			
			infov = WorkOrderService.getBookInfo(orderType);
	
			logger.debug("Finished.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();	
			logger.error(ex.getMessage(), ex);
			infov = null;
		}
		
		return infov;
	}	
}
