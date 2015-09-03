/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.util.DateAS;
import com.zot.view.contorler.PrefixService;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.XingWorkOrderBO;

/**
 * @author jack
 *
 */
public class SubscribeAction implements PrefixService {
	
	private static Logger logger = Logger.getLogger(SubscribeAction.class);
	
	public XingWorkOrderBO action(Map<String,String> context) {
		WorkOrderAS workOrderA = new WorkOrderASImpl();
		XingWorkOrderBO wo = new XingWorkOrderBO();
		
		try
		{
			wo.setCust_id("101");  //？？？？？
			String subTime = context.get("subtime").replaceAll("T", " ");
			wo.setOrder_time(DateAS.getSQLTimestampFromString(subTime));
			wo.setOrder_type(context.get("subtype"));
			
			logger.error("time:" + subTime + "&orderType:" + wo.getOrder_type());
			
			workOrderA.addPreSubscribeService(wo);
			
			logger.error("Finished.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();	
			logger.error(ex.getMessage(), ex);
		}
		
		return wo; 
	}

}
