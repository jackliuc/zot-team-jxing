package com.zot.xing.view.service;

import java.util.ArrayList;
import java.util.List;
import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.XingWorkOrderBO;
import com.zot.xing.view.common.IdVO;
import com.zot.xing.view.common.OrderStatus;

public class ServiceMgrService {
	
	public static List<XingWorkOrderVO> queryOrders(IdVO id)
	{
		List<XingWorkOrderVO> orderVOs = new ArrayList<XingWorkOrderVO>();
		
		WorkOrderAS workOrderAS = new WorkOrderASImpl();		
		List<XingWorkOrderBO> orderBOs = workOrderAS.queryWorkOrderByCusID(id.getId());
		if (orderBOs != null)
		{
			for (XingWorkOrderBO orderBO : orderBOs)
			{
				orderVOs.add(cvt2XingWorkOrderVO(orderBO));
			}				
		}
		
		return orderVOs;
	}
	
	public static void complainOrder(IdVO id, XingWorkOrderVO order)
	{
		WorkOrderAS workOrderAS = new WorkOrderASImpl();	
		
		workOrderAS.updateWorkOrderEval(order.getWorkOrderId(), String.valueOf(order.getEvalType()), order.getEvalDesc());
	}
	
	private static XingWorkOrderVO cvt2XingWorkOrderVO(XingWorkOrderBO orderBO)
	{
		XingWorkOrderVO orderVO = new XingWorkOrderVO();
		orderVO.setCustId(orderBO.getCust_id());
		
		ServiceBO service = ServiceUtils.queryServiceBySKey(orderBO.getOrder_type());
		if (service != null)
		{
			orderVO.setServiceName(service.getService_name());
		}
		orderVO.setEvalDesc(orderBO.getEval_desc());
		orderVO.setEvalType(orderBO.getEval_desc_type());
		String servPerson = orderBO.getService_person_num() == null ? "" : orderBO.getService_person_num();
		orderVO.setServicePerson(servPerson);
		orderVO.setServieTime(orderBO.getService_time());
		orderVO.setWorkOrderId(orderBO.getId());
		orderVO.setWorkOrderType(Integer.parseInt(orderBO.getOrder_type()));
		orderVO.setStatus(getStatusByTime(orderBO));
		orderVO.setStatusDes(orderVO.getStatus().toString());
		
		return orderVO;
	}
	
	private static OrderStatus getStatusByTime(XingWorkOrderBO orderBO)
	{
		if (orderBO.getOver_time() != null)
		{
			return OrderStatus.FINISHED;
		}
		
		if (orderBO.getService_time() != null)
		{
			return OrderStatus.DOING;
		}
		
		return OrderStatus.WAITING;
	}
}
