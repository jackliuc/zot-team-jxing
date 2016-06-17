package com.zot.xing.view.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.view.common.IdVO;
import com.zot.xing.view.subscribe.WorkOrderService;

public class ServiceQueryAction implements PrefixService{
	private static Logger logger = Logger.getLogger(ServiceQueryAction.class);
	
	public List<WorkOrderVO> action(Map<String,String> context) {
		String userId = context.get("userId");
		logger.error("userId:" + userId);		
		
		List<WorkOrderVO> orders = WorkOrderService.queryOrders(new IdVO(Constant.ID_WECHATNO, userId));
		
		return orders;
	}
}
