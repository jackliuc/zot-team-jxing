/**
 *  服务预定接口
 */
package com.zot.xing.dao.subscribe;

import java.util.Date;
import java.util.List;

/**
 * @author jack
 *
 */
public interface WorkOrderAS {
	
	/**
	 * 增加一个 新订单
	 * @param workOrder
	 */
	public void addWorkOrder(WorkOrderBO workOrder);
	
	/**
	 * 更新订单状态为付款完成
	 */
	public void updateWorkOrderPaid(WorkOrderBO workOrder);
	
	/**
	 * 取消订单
	 */
	public void cancelWorkOrder(String orderId);
	
	/**
	 * 更新订单达到状态
	 * @param workOrder
	 */
	public void updateWorkOrderArrived(WorkOrderBO workOrder);
	
	/**
	 * 更新订单明细
	 */
	public void updateWorkOrderDetail(WorkOrderDetailBO workOrderDetail);
	
	/**
	 * 根据订单号，修改评价类型和评价内容
	 * @param orderId
	 * @param evalType 评价类型
	 * @param evalDesc 评价内容
	 */
	public void updateWorkOrderEval(String orderId,String evalType,String evalDesc);
	
	/**
	 * 更新投诉处理结果
	 * @param orderId
	 * @param evalResult
	 */
	public void updateWorkOrderEvalResult(String orderId,String evalResult);
	
	/**
	 * 根据客户Id查询客户订单信息，查询此客户的所有订单
	 * @param cusId 客户ID
	 * @return
	 */
	public List<WorkOrderBO> queryWorkOrderByCusId(String cusId);
	
	/**
	 * 根据订单Id查询订单信息
	 * @param orderId 订单Id
	 * @return
	 */
	public WorkOrderBO queryWorkOrderByOrderId(String orderId);
	
	/**
	 * 根据订单类型查询订单数量
	 * @param orderType 订单类型
	 * @return
	 */
	public int queryWorkOrdersNumByType(String orderType);
	
	/**
	 * 根据订单类型查询订单明细
	 * @param orderType 订单类型
	 * @return
	 */
	public List<WorkOrderDetailBO> queryWorkOrderDetailsByType(String orderType);
	
	/**
	 * 根据订单Id查询订单明细
	 * @param orderId 订单Id
	 * @return
	 */
	public List<WorkOrderDetailBO> queryWorkOrderDetailsByOrderId(String orderId);
	
	/**
	 * 根据用户ID查询客户服务信息，时间默认为当天
	 * @param cusId 客户ID
	 * @param beginTime 开始时间
	 * @param endTime   结束时间
	 * @return
	 */
	public List<WorkOrderBO> queryWorkOrderByCusId(String cusId,Date beginTime,Date endTime);
	
	/**
	 * 根据状态查询订单
	 * @return
	 */
	public List<WorkOrderBO> queryOrdersByStatus(String status);
	
	/**
	 * 根据车牌号查询订单
	 * @return
	 */
	public List<WorkOrderBO> queryOrdersByCarno(String carno);
	
	/**
	 * 根据手机号查询订单
	 * @return
	 */
	public List<WorkOrderBO> queryOrdersByPhoneno(String phoneno);
	
	
	/**
	 * 查询指定时间范围内的订单
	 * @return
	 */
	public List<EnableWorkOrderBO> queryOrdersByDate(String beginDate, String endDate);
	
	/**
	 * 根据特定条件查询增强型订单列表
	 * @return
	 */
	public List<EnableWorkOrderBO> queryOrdersByCondition(WorkOrderBO workOrderCond);
}
