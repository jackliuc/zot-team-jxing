package com.zot.xing.view.service;

import java.util.Date;
import java.util.Map;

import com.zot.util.DateAS;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.WXAppOpenApi;
import com.zot.xing.view.common.IdVO;

public class ServiceDealAction implements PrefixService {

	@Override
	public Object action(Map<String, String> context) {
		String code = context.get("code");
		String orderId = context.get("orderId");
		String carNo = context.get("carNo");
		
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String userId = api.getUserId(code);		
		
		IdVO id = new IdVO(0,userId);
		XingWorkOrderVO order = new XingWorkOrderVO();		
		Date serviceTime = new Date();
		order.setServiceTime(serviceTime);
		order.setServicePerson(userId);
		order.setWorkOrderId(orderId);
		order.setCarNo(carNo);
		
		ServiceMgrService.dealOrder(id, order);
		
		order.setDisServiceTime(DateAS.convertDate2Str(order.getServiceTime()));
		return order;
	}
	
}
