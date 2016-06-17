package com.zot.manage.service.checkin;

import java.util.Date;
import java.util.List;
import com.zot.manage.vo.checkin.CustomerInfoVO;
import com.zot.util.DateAS;
import com.zot.util.IdGen;
import com.zot.util.StringUtils;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.subscribe.CarAS;
import com.zot.xing.dao.subscribe.CarASImpl;
import com.zot.xing.dao.subscribe.CarBO;
import com.zot.xing.dao.subscribe.CustomerAS;
import com.zot.xing.dao.subscribe.CustomerASImpl;
import com.zot.xing.dao.subscribe.CustomerBO;
import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.WorkOrderBO;
import com.zot.xing.dao.subscribe.WorkOrderDetailBO;
import com.zot.xing.view.subscribe.SubscribeVO;
import com.zot.xing.view.subscribe.WorkOrderService;

public class CheckInService {
	
	public static void saveCustomerInfo(CustomerInfoVO customerInfo)
	{
		//判断是否存在客户，不存在则新增，否则进行更新
		CustomerAS customerAS = new CustomerASImpl();
		CustomerBO customer = getCustomerBO(customerInfo);
		if (!StringUtils.isEmpty(customer.getCustId()))//更新客户资料
		{
			customerAS.updateCustomer(customer);
		}
		else//新增客户
		{
			customer.setCustId(IdGen.genCustId());
			customer.setSubTime(new Date());
			customerAS.addCustomer(customer);
		}
		
		//查询Car信息是否存在，不存在则进行保存。
		CarAS caras = new CarASImpl();
		CarBO car = caras.queryCarByCarno(customerInfo.getCarno());
		if (car == null)//Car信息新增
		{
			car = new CarBO();
			car.setCarno(customerInfo.getCarno());
			car.setCustId(customer.getCustId());
			caras.addCar(car);
		}
		
		//如果orderId为空，则没有微信预约，直接进行订单新增
		if (StringUtils.isEmpty(customerInfo.getOrderId()))
		{
			if (!StringUtils.isEmpty(customerInfo.getServiceType()))
			{
				SubscribeVO subscribeVO = getSubscriberVO(customerInfo.getCarno(), customerInfo.getServiceType());
				WorkOrderService.addWorkOrder(subscribeVO, customer.getCustId());
			}
		}
		else//已预约客户，进行达到状态更新
		{
			WorkOrderAS workOrderAS = new WorkOrderASImpl();
			WorkOrderBO workOrder = new WorkOrderBO();
			workOrder.setOrderId(customerInfo.getOrderId());
			workOrder.setArrived("Y");
			workOrderAS.updateWorkOrderArrived(workOrder);
		}
	}
	
	public static CustomerInfoVO queryCustomerInfoByCarno(String carno)
	{
		//根据车牌号查询车辆信息
		CarAS carAS = new CarASImpl();
		CarBO car = carAS.queryCarByCarno(carno);
		
		CustomerInfoVO customerInfoVO = null;
		if (car != null)
		{
			//查询客户资料
			CustomerAS customerAS = new CustomerASImpl();
			CustomerBO customer = customerAS.queryCustomerByCustId(car.getCustId());
			if (customer != null)
			{
				customerInfoVO = getCustomerInfo(customer);
				customerInfoVO.setCarno(carno);
				
				//查询客户订购资料，仅查询当前预约的订单
				WorkOrderAS workOrderAS = new WorkOrderASImpl();
				List<WorkOrderBO> orders = workOrderAS.queryWorkOrderByCusId(customer.getCustId());
				if (orders != null)
				{
					for (WorkOrderBO workOrderBO : orders)
					{
						//预约状态
						if (workOrderBO.getStatus() != null
							&& workOrderBO.getStatus().equals(Constant.ORDER_WAITING))
						{
							//当前仅认为客户只有一个预约订单
							setCustomerSubscribeInfo(customerInfoVO, workOrderBO);
							break;
						}
					}
				}
			}
		}
		
		return customerInfoVO;
	}
	
	private static SubscribeVO getSubscriberVO(String carno, String serviceType)
	{
		SubscribeVO subscriberVO = new SubscribeVO();
		subscriberVO.setCarno(carno);
		subscriberVO.setOrderType(serviceType);
		subscriberVO.setOrderTime(DateAS.getCurrentDateYHM());
		subscriberVO.setArrived("Y");
		
		return subscriberVO;
	}
	
	private static CustomerInfoVO getCustomerInfo(CustomerBO customer)
	{
		CustomerInfoVO customerInfoVO = new CustomerInfoVO();
		
		customerInfoVO.setAddress(customer.getAddress());
		customerInfoVO.setWechatno(customer.getWechatno());
		customerInfoVO.setCustname(customer.getCustName());
		customerInfoVO.setPhoneno(customer.getPhoneno());
		customerInfoVO.setSex(customer.getSex());
		customerInfoVO.setRemark(customer.getRemark());
		customerInfoVO.setCustId(customer.getCustId());
		
		return customerInfoVO;
	}
	
	private static CustomerBO getCustomerBO(CustomerInfoVO CustomerInfoVO)
	{
		CustomerBO customer = new CustomerBO();
		
		customer.setAddress(CustomerInfoVO.getAddress());
		customer.setWechatno(CustomerInfoVO.getWechatno());
		customer.setPhoneno(CustomerInfoVO.getPhoneno());
		customer.setSex(CustomerInfoVO.getSex());
		customer.setRemark(CustomerInfoVO.getRemark());
		customer.setCustName(CustomerInfoVO.getCustname());
		customer.setCustId(CustomerInfoVO.getCustId());
		
		return customer;
	}
	
	private static void setCustomerSubscribeInfo(CustomerInfoVO customerInfoVO, WorkOrderBO workOrderBO)
	{
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		List<WorkOrderDetailBO> orderDetails = workOrderAS.queryWorkOrderDetailsByOrderId(workOrderBO.getOrderId());

		if (orderDetails != null)
		{
			for (WorkOrderDetailBO detail : orderDetails)
			{
				if (Constant.ORDER_WAITING.equals(detail.getStatus()))
				{
					customerInfoVO.setServiceType(detail.getOrderType());
					ServiceBO service = ServiceUtils.queryServiceBySKey(detail.getOrderType());
					if (service != null)
					{
						customerInfoVO.setServiceName(service.getService_name());
					}
					customerInfoVO.setOrderId(detail.getOrderId());
					break;
				}
			}
		}
	}
}
