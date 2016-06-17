/**
 * 
 */
package com.zot.xing.view.subscribe;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.view.contorler.PrefixService;
import com.zot.xing.dao.subscribe.WorkOrderBO;

/**
 * @author jack
 *
 */
public class SubscribeAction implements PrefixService {
	
	private static Logger logger = Logger.getLogger(SubscribeAction.class);
	
	public WorkOrderBO action(Map<String,String> context) 
	{
		WorkOrderBO wo = null;
		
		try
		{
			//获取微信号、预约类型，预约时间参数
			String data = context.get("data");
			logger.error("data:" + data);
			
			SubscribeVO subVO = JSONObject.parseObject(data, SubscribeVO.class);
			subVO.setArrived("N");
			subVO.setOrderTime(subVO.getOrderTime().replaceAll("T", " "));
			
			wo = WorkOrderService.addWorkOrder(subVO);
			
			logger.debug("Create order successed.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();	
			logger.error(ex.getMessage(), ex);
			wo = null;
		}
		
		return wo; 
	}

}
