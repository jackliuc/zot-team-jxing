package com.zot.manage.action.common;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

public class QryAuthMenuURLAction implements PrefixService {
	
	private static Logger log = Logger.getLogger(QryAuthMenuURLAction.class);
	
	public String action(Map<String,String> context) 
	{
		String menuURL = null;
		try
		{
			//获取微信号、预约类型，预约时间参数
			String menuId = context.get("menuId");
			log.info("menuId:" + menuId);
			
			menuURL = Constant.AUTH_MENU_MAP.get(menuId);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage());
			menuURL = null;
		}
		
		log.info("return menuURL:" + menuURL);
		return menuURL;
	}
}

