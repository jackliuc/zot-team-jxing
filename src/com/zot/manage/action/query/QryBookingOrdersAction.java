package com.zot.manage.action.query;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.view.service.WorkOrderVO;
import com.zot.xing.view.subscribe.WorkOrderService;

public class QryBookingOrdersAction implements PrefixService {

	private static Logger log = Logger.getLogger(QryBookingOrdersAction.class);

	@Override
	public Object action(Map<String, String> context) {
		String carno = context.get("carno");
		String phoneno = context.get("phoneno");
		String status = context.get("status");
		
		try
		{
			List<WorkOrderVO> workOrderVOs = WorkOrderService.qryBookingOrders(status, phoneno, carno);
			return workOrderVOs;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
			
		return Constant.FAIL;
	}
}
