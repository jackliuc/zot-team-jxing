package com.zot.xing.view.subscribe;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

public class CheckSubscribeAction  implements PrefixService {
	
	private static Logger log = Logger.getLogger(CheckSubscribeAction.class);
	
	public String action(Map<String,String> context) 
	{
		boolean isValid = true;
		//检查用户是否在重复预约相同的服务类型，对于重复预约非法
		try
		{
			//获取微信号、预约类型，预约时间参数
			String data = context.get("data");
			log.error("data:" + data);
			
			SubscribeVO subVO = JSONObject.parseObject(data, SubscribeVO.class);
			
			isValid = WorkOrderService.checkWorkOrder(subVO);
			log.info("check subscribe result: " + isValid);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();	
			log.error(ex.getMessage(), ex);
			isValid = false;
		}
		
		if (!isValid)
		{
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}
}
