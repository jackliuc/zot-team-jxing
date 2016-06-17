package com.zot.xing.view.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.WXAppOpenApi;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.WorkOrderBO;

public class QueryWorkOrderAction implements PrefixService {
	private static Logger log = Logger.getLogger(QueryWorkOrderAction.class);
	
	@Override
	public String action(Map<String, String> context) {
		String code = context.get("code");
		String orderId = context.get("orderId");
		
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String wechatno = api.getUserId(code);		
		log.error("code:" + code + "&wechatno:" + wechatno);
		
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		WorkOrderBO workOrder = workOrderAS.queryWorkOrderByOrderId(orderId);
		String carno = null;
		if (workOrder != null)
		{
			carno = workOrder.getCarno();
		}
		
		log.debug("return carno is:" + carno);
		return carno;
	}
	
}
