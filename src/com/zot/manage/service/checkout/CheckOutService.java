package com.zot.manage.service.checkout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zot.manage.vo.checkout.WorkOrderInfoVO;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.WorkOrderBO;
import com.zot.xing.dao.subscribe.WorkOrderDetailBO;

public class CheckOutService {
	public static void saveWorkOrderInfo(WorkOrderInfoVO workOrderInfoVO)
	{
		WorkOrderBO workOrder = getWorkOrderBO(workOrderInfoVO);
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		workOrderAS.updateWorkOrderPaid(workOrder);
	}
	
	private static WorkOrderBO getWorkOrderBO(WorkOrderInfoVO workOrderInfoVO)
	{
		WorkOrderBO workOrder = new WorkOrderBO();
		
		//构造订单主表
		workOrder.setPayType(workOrderInfoVO.getPayType());
		workOrder.setAmount(Float.valueOf(workOrderInfoVO.getPayAmount()));
		workOrder.setPayType(workOrderInfoVO.getPayType());
		workOrder.setStatus(Constant.ORDER_EVAING);
		workOrder.setRemark(workOrderInfoVO.getRemark());
		workOrder.setOrderId(workOrderInfoVO.getOrderId());
		
		//构造订单明细
		List<WorkOrderDetailBO> details = new ArrayList<WorkOrderDetailBO>();
		WorkOrderDetailBO detail = new WorkOrderDetailBO();
		detail.setOrderId(workOrderInfoVO.getOrderId());
		detail.setOrderType(workOrderInfoVO.getOrderType());
		detail.setStatus(Constant.ORDER_EVAING);
		detail.setFinishedTime(new Date());
		detail.setEmployId(workOrderInfoVO.getEmployId());
		details.add(detail);
		workOrder.setDetails(details);
		
		return workOrder;
	}
}
