package com.zot.xing.view.subscribe;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;

public class QuerySubscribeInfoAction implements PrefixService {
	
	private static Logger log = Logger.getLogger(QuerySubscribeInfoAction.class);
	
	public SubscribeVO action(Map<String,String> context) 
	{
		SubscribeVO subVO = null;
		
		try
		{
			//获取微信号、预约类型，预约时间参数
			String userId = context.get("userId");
			log.error("userId:" + userId);
			
			subVO = SubscribeService.getSubscribeInfoByWechatno(userId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();	
			log.error(ex.getMessage(), ex);
			subVO = null;
		}
		
		return subVO;
	}

}
