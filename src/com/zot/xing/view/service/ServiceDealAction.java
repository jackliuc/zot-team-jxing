package com.zot.xing.view.service;

import java.util.Map;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.WXAppOpenApi;
import com.zot.xing.view.common.IdVO;
import com.zot.xing.view.subscribe.WorkOrderService;

public class ServiceDealAction implements PrefixService {

	@Override
	public Object action(Map<String, String> context) {
		String code = context.get("code");
		String orderId = context.get("orderId");
		String carNo = context.get("carNo");
		
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String userId = api.getUserId(code);		
		
		IdVO id = new IdVO(0,userId);
		WorkOrderVO order = new WorkOrderVO();		
		//Date serviceTime = new Date();
		//order.sets(serviceTime);
		//order.setEmployId(userId);
		order.setWorkOrderId(orderId);
		order.setCarNo(carNo);
		
		WorkOrderService.dealOrder(id, order);
		
		//order.setDisServiceTime(DateAS.convertDate2Str(order.getServiceTime()));
		return order;
	}
	
}
