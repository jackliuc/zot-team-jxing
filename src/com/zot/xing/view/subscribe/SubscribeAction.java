/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.util.Map;

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
	
	public XingWorkOrderBO action(Map<String,String> context) {
		WorkOrderAS workOrderA = new WorkOrderASImpl();
		XingWorkOrderBO wo = new XingWorkOrderBO();
		
		wo.setCust_id(null);  //？？？？？
		wo.setOrder_time(DateAS.getSQLTimestampFromString(context.get("subtime")));
		wo.setOrder_type(context.get("subtype"));
		
		
		workOrderA.addPreSubscribeService(wo);
		return wo; 
	}

}
