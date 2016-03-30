/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.zot.util.DateAS;
import com.zot.view.contoller.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.WXAppOpenApi;
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
			String code = context.get("code");
			WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
			String userId = api.getUserId(code);
			logger.error("code:" + code + "&userId:" + userId);
			
			if (userId == null) {userId = "101";}//本地调测提供默认值
			wo.setCust_id(userId); 
			String subTime = context.get("subtime").replaceAll("T", " ");
			wo.setOrder_time(DateAS.getSQLTimestampFromString(subTime));
			wo.setOrder_type(context.get("subtype"));			
			logger.debug("time:" + subTime + "&orderType:" + wo.getOrder_type());
			wo.setId(UUID.randomUUID().toString());
			
			workOrderA.addPreSubscribeService(wo);
			
			logger.debug("Finished.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();	
			logger.error(ex.getMessage(), ex);
		}
		
		return wo; 
	}

}
