package com.zot.xing.view.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.util.DateAS;
import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.XingWorkOrderBO;
import com.zot.xing.view.common.IdVO;
import com.zot.xing.view.common.OrderStatus;

public class ServiceMgrService {
	
	private static Logger logger = Logger.getLogger(ServiceMgrService.class);
	private static Map<String, String> servImgMap = new HashMap<String, String>();
	private static Map<String, String> evalTypeDisMap = new HashMap<String, String>();
	
	static
	{
		servImgMap.put("10001", "../assets/image/jx3.jpg");
		servImgMap.put("10002", "../assets/image/zq.jpg");
		servImgMap.put("10003", "../assets/image/bj.jpg");
		servImgMap.put("10004", "../assets/image/zh.jpg");
	}
	
	static
	{
		evalTypeDisMap.put("1", "好评");
		evalTypeDisMap.put("2", "差评");
	}
	
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
	
	public static void dealOrder(IdVO id, XingWorkOrderVO order)
	{
		WorkOrderAS workOrderAS = new WorkOrderASImpl();	
		
		XingWorkOrderBO workOrder = new XingWorkOrderBO();
		workOrder.setId(order.getWorkOrderId());
		workOrder.setService_person_num(order.getServicePerson());		
		workOrder.setService_time(order.getServiceTime());
		workOrder.setService_object_id(order.getCarNo());		
				
		workOrderAS.updateWorkOrderToGoing(workOrder);
		
		logger.debug("dealOrder finished, orderId:" + order.getWorkOrderId());
	}
	
	/*
	public static void logRequestParams(HttpServletRequest request)
	{
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String elem = en.nextElement();			
			logger.error("name:" + elem + "|value:" + request.getParameter(elem));		
			System.out.println("name:" + elem + "|value:" + request.getParameter(elem));	
		}
	}
	*/
	
	private static XingWorkOrderVO cvt2XingWorkOrderVO(XingWorkOrderBO orderBO)
	{
		XingWorkOrderVO orderVO = new XingWorkOrderVO();
		orderVO.setCustId(orderBO.getCust_id());
		
		ServiceBO service = ServiceUtils.queryServiceByKey(orderBO.getOrder_type());
		if (service != null)
		{
			orderVO.setServiceName(service.getService_name());
		}
		orderVO.setEvalDesc(orderBO.getEval_desc());
		orderVO.setEvalType(orderBO.getEval_desc_type());
		String servPerson = orderBO.getService_person_num() == null ? "" : orderBO.getService_person_num();
		orderVO.setServicePerson(servPerson);
		orderVO.setServiceTime(orderBO.getService_time());
		orderVO.setWorkOrderId(orderBO.getId());
		orderVO.setWorkOrderType(Integer.parseInt(orderBO.getOrder_type()));
		orderVO.setStatus(getStatusByTime(orderBO));
		orderVO.setStatusDes(orderVO.getStatus().toString());
		orderVO.setDisServiceTime(DateAS.convertDate2Str(orderBO.getService_time()));
		orderVO.setDisCreateTime(DateAS.convertDate2Str(orderBO.getCreate_time()));
		orderVO.setServiceImg(servImgMap.get(orderBO.getOrder_type()));
		if(orderVO.getStatus() == OrderStatus.FINISHED)
		{
			orderVO.setIsDisplayEval(1);
		}
		orderVO.setDisEvalType(evalTypeDisMap.get(String.valueOf(orderBO.getEval_desc_type())));
		
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
