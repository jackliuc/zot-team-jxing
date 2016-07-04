package com.zot.xing.view.subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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
import com.zot.xing.dao.subscribe.EnableWorkOrderBO;
import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;
import com.zot.xing.dao.subscribe.WorkOrderAS;
import com.zot.xing.dao.subscribe.WorkOrderASImpl;
import com.zot.xing.dao.subscribe.WorkOrderBO;
import com.zot.xing.dao.subscribe.WorkOrderDetailBO;
import com.zot.xing.view.common.BookInfoVO;
import com.zot.xing.view.common.IdVO;
import com.zot.xing.view.service.AmountVO;
import com.zot.xing.view.service.DailyAmountCntVO;
import com.zot.xing.view.service.DailyServiceCntVO;
import com.zot.xing.view.service.DailyWorkOrderCntVO;
import com.zot.xing.view.service.ServiceVO;
import com.zot.xing.view.service.WorkOrderVO;

public class WorkOrderService {
	private static Logger log = Logger.getLogger(WorkOrderService.class);
	
	private static Map<String, String> servImgMap = new HashMap<String, String>();
	private static Map<String, String> evalTypeDisMap = new HashMap<String, String>();
	
	static
	{
		servImgMap.put("10001", "assets/image/kx.jpg");
		servImgMap.put("10002", "assets/image/jx.jpg");
		servImgMap.put("10003", "assets/image/mr.jpg");
		servImgMap.put("10006", "assets/image/yq.jpg");
	}
	
	static
	{
		evalTypeDisMap.put("1", "满意");
		evalTypeDisMap.put("2", "一般");
		evalTypeDisMap.put("3", "不满意");
	}
	
	/**
	 * 根据条件查询订单对象，不仅仅为预约和已取消两种状态
	 * @param status
	 * @param phoneno
	 * @param carno
	 * @return
	 */
	public static List<WorkOrderVO> qryWorkOrders(String status, String phoneno, String carno)
	{
		//构造查询对象
		WorkOrderBO workOrderCond = new WorkOrderBO();
		if (!StringUtils.isEmpty(status)
			&& !"-1".equals(status))
		{
			workOrderCond.setStatus(status);
		}
		if (!StringUtils.isEmpty(phoneno))
		{
			workOrderCond.setPhoneno(phoneno);
		}
		if (!StringUtils.isEmpty(carno))
		{
			workOrderCond.setCarno(carno);
		}
		
		//查询并构造返回VO对象列表
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		List<EnableWorkOrderBO> enableOrders = workOrderAS.queryOrdersByCondition(workOrderCond);
		
		List<WorkOrderVO> orderVOs = new ArrayList<WorkOrderVO>();
		if (enableOrders != null)
		{
			for (WorkOrderBO order : enableOrders)
			{				
				//将汇总和明细订单，转化为适合界面展现的订单VO对象
				orderVOs.add(cvt2WorkOrderVO(order));
			}	
		}
		
		return orderVOs;
	}
	
	/**
	 * 查询预约或已取消的订单
	 * @param status
	 * @param phoneno
	 * @param carno
	 * @return
	 */
	public static List<WorkOrderVO> qryBookingOrders(String status, String phoneno, String carno)
	{
		List<WorkOrderVO> orderVOs = null;
		
		//根据手机号查询订单
		if(!StringUtils.isEmpty(phoneno))
		{
			orderVOs = qryBookingOrdersByPhoneno(status, phoneno);
			return orderVOs;
		}
		
		//根据车牌号查询订单
		if(!StringUtils.isEmpty(carno))
		{
			orderVOs = qryBookingOrdersByCarno(status, carno);
			return orderVOs;
		}
		
		//根据状态查询订单
		orderVOs = qryBookingOrdersByStatus(status);
		return orderVOs;
	}
	
	/**
	 * 根据手机号码查询预约或已取消的订单
	 * @param status
	 * @param phoneno
	 * @return
	 */
	public static List<WorkOrderVO> qryBookingOrdersByPhoneno(String status, String phoneno)
	{
		List<WorkOrderBO> orders = null;
		
		//根据手机号码查询订单
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		orders = workOrderAS.queryOrdersByPhoneno(phoneno);
		
		//根据查询的状态进行过滤
		if (orders != null)
		{
			orders = filterByStatus(orders, status);
		}
		
		//转化为VO返回
		List<WorkOrderVO> orderVOs = null;
		if (orders != null)
		{
			orderVOs = new ArrayList<WorkOrderVO>(orders.size());
			
			List<WorkOrderDetailBO> orderDetails = null;
			for (WorkOrderBO order : orders)
			{
				//根据订单基本信息查询明细列表
				//orderDetails = workOrderAS.queryWorkOrderDetailsByOrderId(order.getOrderId());
				order.setDetails(orderDetails);
				
				//将汇总和明细订单，转化为适合界面展现的订单VO对象
				orderVOs.add(cvt2WorkOrderVO(order));
			}			
		}
		
		return orderVOs;
	}
	
	/**
	 * 根据车牌号查询预约或已取消的订单
	 * @param status
	 * @param carno
	 * @return
	 */
	public static List<WorkOrderVO> qryBookingOrdersByCarno(String status, String carno)
	{	
		//根据车牌号码查询订单
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		List<WorkOrderBO> orders = workOrderAS.queryOrdersByCarno(carno);
		
		//根据查询的状态进行过滤
		if (orders != null)
		{
			orders = filterByStatus(orders, status);
		}
		
		//转化为VO返回
		List<WorkOrderVO> orderVOs = null;
		if (orders != null)
		{
			orderVOs = new ArrayList<WorkOrderVO>(orders.size());
			List<WorkOrderDetailBO> orderDetails = null;
			for (WorkOrderBO order : orders)
			{
				//根据订单基本信息查询明细列表
				//orderDetails = workOrderAS.queryWorkOrderDetailsByOrderId(order.getOrderId());
				order.setDetails(orderDetails);
				
				//将汇总和明细订单，转化为适合界面展现的订单VO对象
				orderVOs.add(cvt2WorkOrderVO(order));
			}	
		}
		
		return orderVOs;
	}
	
	/**
	 * 根据状态查询预约或已取消的订单
	 * @param status
	 * @return
	 */
	public static List<WorkOrderVO> qryBookingOrdersByStatus(String status)
	{	
		List<WorkOrderBO> orders = new ArrayList<WorkOrderBO>();
		
		//查询预约状态订单
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		if (Constant.ORDER_ALL.equals(status)
			|| Constant.ORDER_WAITING.equals(status))
		{
			List<WorkOrderBO> waitingOrders = workOrderAS.queryOrdersByStatus(Constant.ORDER_WAITING);
			if (waitingOrders != null)
			{
				orders.addAll(waitingOrders);
			}
		}
		
		//查询已取消状态订单
		if (Constant.ORDER_ALL.equals(status)
			|| Constant.ORDER_CANCEL.equals(status))
		{
			List<WorkOrderBO> cancelOrders = workOrderAS.queryOrdersByStatus(Constant.ORDER_CANCEL);
			if (cancelOrders != null)
			{
				orders.addAll(cancelOrders);
			}
		}
		
		//转化为VO返回
		List<WorkOrderVO> orderVOs = null;
		if (orders != null)
		{
			orderVOs = new ArrayList<WorkOrderVO>(orders.size());
			List<WorkOrderDetailBO> orderDetails = null;
			for (WorkOrderBO order : orders)
			{
				//根据订单基本信息查询明细列表
				//orderDetails = workOrderAS.queryWorkOrderDetailsByOrderId(order.getOrderId());
				order.setDetails(orderDetails);
				
				//将汇总和明细订单，转化为适合界面展现的订单VO对象
				orderVOs.add(cvt2WorkOrderVO(order));
			}	
		}
		
		return orderVOs;
	}
	
	private static List<WorkOrderBO> filterByStatus(List<WorkOrderBO> orders, String status)
	{
		List<WorkOrderBO> filterOrders = new ArrayList<WorkOrderBO>();
		for (WorkOrderBO order: orders)
		{
			if (!isBookingStatus(order.getStatus()))
			{
				continue;
			}
			
			if (status.equals(order.getStatus())
				|| Constant.ORDER_ALL.equals(status))
			{
				filterOrders.add(order);
			}
		}
		
		return filterOrders;
	}
	
	private static boolean isBookingStatus(String status)
	{
		return Constant.ORDER_WAITING.equals(status) 
				|| Constant.ORDER_CANCEL.equals(status);
	}
	
	/**
	 * 根据服务类型，获取服务类型对应的预约信息
	 * @param orderType
	 * @return
	 */
	public static BookInfoVO getBookInfo(String orderType)
	{
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		BookInfoVO infov = new BookInfoVO();
		
		//获取当前所有的预约订单，并根据OrderType进行过滤，统计总预约数以及已到店数据
		List<WorkOrderBO> orders = workOrderAS.queryOrdersByStatus(Constant.ORDER_WAITING);
		int arrivedCnt = 0;//预约总数中已到店数
		int bookingCnt = 0;//预约总数
		if (orders != null)
		{
			List<WorkOrderDetailBO> orderDetails = null;
			for (WorkOrderBO order : orders)
			{
				orderDetails = workOrderAS.queryWorkOrderDetailsByOrderId(order.getOrderId());
				if (orderDetails != null)
				{
					for(WorkOrderDetailBO detail : orderDetails)
					{
						//存在当前服务类型的预约，则预约数加1
						if (detail.getOrderType().equals(orderType))
						{
							bookingCnt++;
							//如果已达到，则已到店数加1
							if (Constant.ARRIVED_YES.equals(order.getArrived()))
							{
								arrivedCnt++;
							}
							//由于同一个订单下的同一种订单类型只能有一个，则直接break
							break;
						}
					}
				}
			}
		}
		
		//根据订单类型，获取配置的各业务类型下的效率
		ServiceBO service = ServiceUtils.queryServiceBySKey(orderType);
		
		//构造BookInfoVO对象
		infov.setBookingNum(bookingCnt);
		int currentCnt = service.getCurrent_cnt().intValue();
		int idleCnt = (currentCnt - arrivedCnt) <= 0 ? 0 : (currentCnt - arrivedCnt);
		infov.setIdleNum(idleCnt);
		infov.setArrivedNum(arrivedCnt);
		
		//建议预约时间
		Date suggestTime = null;
		if (idleCnt > 0)//如果有空闲工位，则直接建议预约时间为当前时间
		{
			suggestTime = new Date();
		}
		else
		{
			int loop = arrivedCnt / currentCnt;
			long waitingTime = loop * service.getCost_time() * 60L * 1000L;
			suggestTime = new Date(System.currentTimeMillis() + waitingTime);
		}
		infov.setSuggestTime(suggestTime);
		infov.setDisSuggestTime(DateAS.convertDate2Str(suggestTime));
		infov.setCostTime(service.getCost_time());
		infov.setBookName(service.getService_name());
		infov.setOrderType(Integer.parseInt(orderType));
		
		return infov;
	}
	
	public static boolean checkWorkOrder(SubscribeVO subscribeVO)
	{
		//检查客户是否重复预约
		CustomerAS custAS = new CustomerASImpl();
		CustomerBO customer = custAS.queryCustomerByWechatno(subscribeVO.getWechatno());
		//客户不存在，则直接返回合法
		if (customer == null)
		{
			return true;
		}
		
		//暂时限制，客户只能预约一个订单，后续可以放开，限制同类型的服务，只能预约一个
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		List<WorkOrderBO> orders = workOrderAS.queryWorkOrderByCusId(customer.getCustId());
		if (orders != null)
		{
			for (WorkOrderBO orderBO : orders)
			{
				if (Constant.ORDER_WAITING.equals(orderBO.getStatus()))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static WorkOrderBO addWorkOrder(SubscribeVO subscribeVO)
	{
		//查询客户信息，如果不存在，则直接构造客户信息，并入库
		//TODO:待排查，同样的微信号存在两个客户
		CustomerBO customer = CustomerService.queryOrCreateCustIdByWechatno
							(subscribeVO.getWechatno(), subscribeVO.getPhoneno());
		String custId = customer.getCustId();
		
		//检查当前Carno是否有对应的车辆信息，如果无，则创建
		CarAS caras = new CarASImpl();
		CarBO car = caras.queryCarByCarno(subscribeVO.getCarno());
		if (car == null)
		{
			log.debug("car is not exist, create car by carno:" + subscribeVO.getCarno());
			car = new CarBO();
			car.setCarno(subscribeVO.getCarno());
			car.setCustId(custId);
			car.setCreateTime(new Date());
			caras.addCar(car);
		}

		//构造订单并完成新增
		WorkOrderBO wo = getWorkOrderBO(subscribeVO, custId);
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		workOrderAS.addWorkOrder(wo);
		
		return wo;
	}
	
	public static WorkOrderBO addWorkOrder(SubscribeVO subscribeVO, String custId)
	{
		//构造订单并完成新增
		WorkOrderBO wo = getWorkOrderBO(subscribeVO, custId);
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		workOrderAS.addWorkOrder(wo);
		
		return wo;
	}
	
	public static List<WorkOrderVO> queryOrders(IdVO id)
	{
		List<WorkOrderVO> orderVOs = new ArrayList<WorkOrderVO>();
		
		//根据微信号查询custId
		CustomerAS customerAS = new CustomerASImpl();
		CustomerBO customer = customerAS.queryCustomerByWechatno(id.getId());
		if (customer == null || customer.getCustId().isEmpty())
		{
			log.debug("can not find customer by wechatno:" + id.getId());
			return orderVOs;
		}
		
		//根据客户id查询订单
		WorkOrderAS workOrderAS = new WorkOrderASImpl();		
		List<WorkOrderBO> orderBOs = workOrderAS.queryWorkOrderByCusId(customer.getCustId());
		if (orderBOs != null)
		{
			List<WorkOrderDetailBO> orderDetails = null;
			for (WorkOrderBO orderBO : orderBOs)
			{
				//根据订单基本信息查询明细列表
				orderDetails = workOrderAS.queryWorkOrderDetailsByOrderId(orderBO.getOrderId());
				orderBO.setDetails(orderDetails);
				
				//将汇总和明细订单，转化为适合界面展现的订单VO对象
				orderVOs.add(cvt2WorkOrderVO(orderBO));
			}				
		}
		
		return orderVOs;
	}
	
	public static void complainOrder(IdVO id, WorkOrderVO order)
	{
		WorkOrderAS workOrderAS = new WorkOrderASImpl();	
		
		workOrderAS.updateWorkOrderEval(order.getWorkOrderId(), String.valueOf(order.getEvalType()), order.getEvalDesc());
	}
	
	public static void dealOrder(IdVO id, WorkOrderVO order)
	{
		log.debug("dealOrder finished, orderId:" + order.getWorkOrderId());
	}
	
	public static DailyWorkOrderCntVO qryDailyWorkOrderCnt(Date qryDate)
	{
		String qryD = DateAS.convertDate2Str(qryDate, "yyyy-MM-dd");
		DailyWorkOrderCntVO dailyWorkOrderCnt = initDailyWorkOrderCnt(qryD);
		
		//构造查询条件，进行数据查询
		String beginDate = qryD + " 00:00:00";
		String endDate = qryD + " 23:59:59";
		
		WorkOrderAS workOrderAS = new WorkOrderASImpl();
		List<EnableWorkOrderBO> orders = workOrderAS.queryOrdersByDate(beginDate, endDate);
		if (orders != null)
		{
			Map<String, ServiceVO> serviceMap = new HashMap<String, ServiceVO>();
			Map<String, AmountVO> payTypeMap = new HashMap<String, AmountVO>();
			ServiceVO serviceVO = null;
			AmountVO amountVO = null;
			float orderAmount = 0;
			//进行数据汇总统计
			for (EnableWorkOrderBO enableOrder : orders)
			{
				//累加服务金额,服务订单数
				serviceVO = serviceMap.get(enableOrder.getOrderType());
				orderAmount = enableOrder.getAmount() == null ? 0 : enableOrder.getAmount().floatValue();
				if (serviceVO == null)
				{
					serviceVO = getServiceVO(enableOrder.getOrderType(), orderAmount, 1);
					serviceMap.put(enableOrder.getOrderType(), serviceVO);
				}
				else
				{
					serviceVO.addAmount(orderAmount);
					serviceVO.addServiceCnt();
				}

				//累计收入金额
				amountVO = payTypeMap.get(enableOrder.getPayType());
				if (amountVO == null)
				{
					amountVO = getAmountVO(enableOrder.getPayType(), orderAmount);
					payTypeMap.put(enableOrder.getPayType(), amountVO);
				}
				else
				{
					amountVO.addAmount(orderAmount);
				}
			}
			
			//根据统计值，更新每日订单对象
			setDailyWorkOrderCnt(dailyWorkOrderCnt, serviceMap, payTypeMap);
		}
		
		return dailyWorkOrderCnt;
	}
	
	private static void setDailyWorkOrderCnt(DailyWorkOrderCntVO dailyWorkOrderCnt, 
											Map<String, ServiceVO> serviceMap,
											Map<String, AmountVO> payTypeMap)
	{
		//设置服务项目的统计值
		int allServiceCnt=0;
		float allServAmount=0;
		List<ServiceVO> services = dailyWorkOrderCnt.getDailyServiceCnt().getServices();
		for (ServiceVO service : services)
		{
			ServiceVO serviceCnt = serviceMap.get(service.getServiceKey());
			if (serviceCnt != null)
			{
				service.setAmount(serviceCnt.getAmount());
				service.setServiceCnt(serviceCnt.getServiceCnt());
			}
			
			allServiceCnt+=service.getServiceCnt();
			allServAmount+=service.getAmount().floatValue();
		}
		
		//新增服务汇总值
		services.add(new ServiceVO("汇总", allServiceCnt, Float.valueOf(allServAmount)));
		
		//设置收入的统计值
		List<AmountVO> amounts = dailyWorkOrderCnt.getDailyAmountCnt().getAmounts();
		float allAmount = 0;
		for (AmountVO amount : amounts)
		{
			AmountVO amountCnt = payTypeMap.get(amount.getPayType());
			if (amountCnt != null)
			{
				amount.setAmount(amountCnt.getAmount());
			}
			
			allAmount+=amount.getAmount().floatValue();
		}
		
		//新增收入汇总值
		amounts.add(new AmountVO("汇总", Float.valueOf(allAmount)));
	}
	
	/**
	 * 构造空的对象结构，每个指标上都有值
	 * @return
	 */
	private static DailyWorkOrderCntVO initDailyWorkOrderCnt(String qryDate)
	{
		DailyWorkOrderCntVO dailyWorkOrderCnt = new DailyWorkOrderCntVO();
		
		//构造空的每日服务统计数据
		dailyWorkOrderCnt.setDailyServiceCnt(initDailyServiceCnt(qryDate));
		//构造空的每日金额数据
		dailyWorkOrderCnt.setDailyAmountCnt(initDailyAmountCnt(qryDate));
		
		return dailyWorkOrderCnt;
	}
	
	private static DailyServiceCntVO initDailyServiceCnt(String qryDate)
	{
		DailyServiceCntVO dailyServiceCntVO = new DailyServiceCntVO();
		dailyServiceCntVO.setTitle(qryDate + " 日服务项目统计");
		
		List<ServiceVO> services = new ArrayList<ServiceVO>();
		List<ServiceBO> servDefs = ServiceUtils.queryAllServices();
		if (servDefs != null)
		{
			for (ServiceBO servDef : servDefs)
			{
				services.add(getServiceVO(servDef.getService_id(), 0, 0));
			}
		}
		dailyServiceCntVO.setServices(services);
		
		return dailyServiceCntVO;
	}
	
	private static DailyAmountCntVO initDailyAmountCnt(String qryDate)
	{
		DailyAmountCntVO dailyAmountCntVO = new DailyAmountCntVO();
		dailyAmountCntVO.setTitle(qryDate + " 日收入统计");
		
		List<AmountVO> amounts = new ArrayList<AmountVO>();
		for (String paytype : Constant.PAY_TYPE_MAP.keySet())
		{
			amounts.add(getAmountVO(paytype, 0));
		}
		dailyAmountCntVO.setAmounts(amounts);
		
		return dailyAmountCntVO;
	}
	
	private static ServiceVO getServiceVO(String orderType, float amount, int serviceCnt)
	{
		ServiceVO serviceVO = new ServiceVO();
		ServiceBO serviceBO = ServiceUtils.queryServiceBySKey(orderType);
		if (serviceBO != null)
		{
			serviceVO.setServiceName(serviceBO.getService_name());
		}
		serviceVO.setServiceKey(orderType);
		serviceVO.setAmount(Float.valueOf(amount));
		serviceVO.setServiceCnt(serviceCnt);
		
		return serviceVO;
	}
	
	private static AmountVO getAmountVO(String payType, float amount)
	{
		AmountVO amountVO = new AmountVO();
		String payTypeName = Constant.PAY_TYPE_MAP.get(payType);
		amountVO.setPayType(payType);
		amountVO.setPayTypeName(payTypeName);
		amountVO.setAmount(Float.valueOf(amount));
		
		return amountVO;
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
	
	private static WorkOrderVO cvt2WorkOrderVO(WorkOrderBO orderBO)
	{
		WorkOrderVO orderVO = new WorkOrderVO();
		orderVO.setCustId(orderBO.getCustId());
		orderVO.setCarNo(orderBO.getCarno());
		orderVO.setPhoneno(orderBO.getPhoneno());
		orderVO.setEvalDesc(orderBO.getEvalDesc());
		if (orderBO.getEvalDescType() != null)
		{
			orderVO.setEvalType(orderBO.getEvalDescType());
		}
		orderVO.setWorkOrderId(orderBO.getOrderId());
		orderVO.setStatus(orderBO.getStatus());
		orderVO.setStatusDes(Constant.STATUS_MAP.get(orderBO.getStatus()));
		orderVO.setDisCreateTime(DateAS.convertDate2Str(orderBO.getCreateTime()));
		orderVO.setDisOrderTime(DateAS.convertDate2Str(orderBO.getOrderTime()));
		if(Constant.ORDER_EVAING.equals(orderVO.getStatus()))
		{
			orderVO.setIsDisplayEval(1);
		}
		else if (Constant.ORDER_WAITING.equals(orderVO.getStatus()))
		{
			orderVO.setCanCancel(1);
		}
		orderVO.setDisEvalType(evalTypeDisMap.get(String.valueOf(orderBO.getEvalDescType())));
		
		if (orderBO.getDetails() != null && orderBO.getDetails().size() > 0)
		{
			WorkOrderDetailBO detail = orderBO.getDetails().get(0);
			ServiceBO service = ServiceUtils.queryServiceBySKey(detail.getOrderType());
			if (service != null)
			{
				orderVO.setServiceName(service.getService_name());
			}
			orderVO.setOrderType(detail.getOrderType());
			orderVO.setServiceImg(servImgMap.get(detail.getOrderType()));
			
			String employId = detail.getEmployId() == null ? "" : detail.getEmployId();
			orderVO.setEmployId(employId);
		}
		
		String amount = orderBO.getAmount() == null ? "0" : String.valueOf(orderBO.getAmount());
		orderVO.setAmount(amount);
		
		//对于增强型订单对象，直接转化
		if (orderBO instanceof EnableWorkOrderBO)
		{
			EnableWorkOrderBO enableWorkOrder = (EnableWorkOrderBO)orderBO;
			orderVO.setOrderType(enableWorkOrder.getOrderType());
			ServiceBO service = ServiceUtils.queryServiceBySKey(enableWorkOrder.getOrderType());
			if (service != null)
			{
				orderVO.setServiceName(service.getService_name());
			}
			orderVO.setDisFinishedTime(DateAS.convertDate2Str(enableWorkOrder.getFinishedTime()));
		}
		
		return orderVO;
	}
	
	private static WorkOrderBO getWorkOrderBO(SubscribeVO subscribeVO, String custId)
	{
		WorkOrderBO wo = new WorkOrderBO();
		
		//构造订单主表信息
		String orderId = IdGen.genOrderId();
		wo.setOrderId(orderId);
		wo.setCreateTime(new Date());
		wo.setStatus(Constant.ORDER_WAITING);
		wo.setOrderTime(DateAS.getSQLTimestampFromString(subscribeVO.getOrderTime()));	
		wo.setCustId(custId); 
		wo.setCarno(subscribeVO.getCarno());
		wo.setArrived(subscribeVO.getArrived());
		wo.setPhoneno(subscribeVO.getPhoneno());
		
		//构造订单明细列表
		WorkOrderDetailBO detail = new WorkOrderDetailBO();
		detail.setOrderId(orderId);
		detail.setOrderType(subscribeVO.getOrderType());
		detail.setStatus(Constant.ORDER_WAITING);
		detail.setCreateTime(new Date());
		
		List<WorkOrderDetailBO> details = new ArrayList<WorkOrderDetailBO>();
		details.add(detail);
		
		wo.setDetails(details);
		
		return wo;
	} 
}
