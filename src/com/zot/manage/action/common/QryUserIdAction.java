package com.zot.manage.action.common;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.WXAppOpenApi;
import com.zot.xing.view.subscribe.SubscribeAction;

public class QryUserIdAction implements PrefixService {
	
	private static Logger logger = Logger.getLogger(SubscribeAction.class);
	
	public String action(Map<String,String> context) 
	{
		String userId = null;
		try
		{
			//获取微信号、预约类型，预约时间参数
			String code = context.get("code");
			logger.info("code:" + code);
			WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
			userId = api.getUserId(code);		
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			userId = null;
		}
		
		logger.info("return userId:" + userId);
		return userId;
	}
}
