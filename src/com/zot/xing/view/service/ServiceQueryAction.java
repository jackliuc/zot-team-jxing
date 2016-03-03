package com.zot.xing.view.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.wechat.util.WXAppOpenApi;
import com.zot.xing.view.common.IdVO;

public class ServiceQueryAction implements PrefixService{
	private static Logger logger = Logger.getLogger(ServiceQueryAction.class);
	
	public List<XingWorkOrderVO> action(Map<String,String> context) {
		String code = context.get("code");
		WXAppOpenApi api = new WXAppOpenApi(Constant.sCorpID, Constant.sCorpSecret, Constant.sAppID);
		String userId = api.getUserId(code);
		logger.error("code:" + code + "&userId:" + userId);		
		
		if (userId == null) {userId = "101";}//本地调测提供默认值
		List<XingWorkOrderVO> orders = ServiceMgrService.queryOrders(new IdVO(0, userId));
		
		return orders;
	}
}
