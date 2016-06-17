package com.zot.xing.view.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;

public class ComplainServiceAction implements PrefixService {
	
	private static Logger log = Logger.getLogger(ComplainServiceAction.class);
	
	public String action(Map<String,String> context) 
	{
		//获取微信号、预约类型，预约时间参数
		/*
		String code = context.get("code");
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String wechatno = api.getUserId(code);
		log.debug("code:" + code + "&wechatno:" + wechatno);
		*/
		
		//订单号、评价类型，评价内容
		String orderId = context.get("orderId");
		String evalType = context.get("evalType");
		String evalDesc = context.get("evalDesc");
		
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		workOrderAS.updateWorkOrderEval(orderId, evalType, evalDesc);
		
		log.debug("update eval succeeded.");
		
		return Constant.SUCC;
	}
}
