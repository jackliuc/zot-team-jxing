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
	public void addPreSubscribeService(XingWorkOrderBO workOrder);
	
	/**
	 * 订单状态变更为服务中
	 */
	public void updateWorkOrderToGoing(XingWorkOrderBO workOrder);
	
	/**
	 * 订单状态变更为结束
	 */
	public void updateWorkOrderToOver(XingWorkOrderBO workOrder);
	
	/**
	 * 根据订单号，修改评价类型和评价内容
	 * @param id
	 * @param evalType 评价类型
	 * @param evalDesc 评价内容
	 */
	public void updateWorkOrderEval(String id,String evalType,String evalDesc);
	
	/**
	 * 更新投诉处理结果
	 * @param id
	 * @param evalResult
	 */
	public void updateWorkOrderEvalResult(String id,String evalResult);
	
	/**
	 * 根据客户Id查询客户订单信息，查询此客户的所有订单
	 * @param cusId 客户ID
	 * @return
	 */
	public List<XingWorkOrderBO> queryWorkOrderByCusID(String cusId);
	
	/**
	 * 根据用户ID查询客户服务信息，时间默认为当天
	 * @param cusId 客户ID
	 * @param beginTime 开始时间
	 * @param endTime   结束时间
	 * @return
	 */
	public List<XingWorkOrderBO> queryWorkOrderByCusID(String cusId,Date beginTime,Date endTime);
	
	/**
	 * 根据用户ID查询客户正在服务的订单
	 * @param cusId 客户ID
	 * @return
	 */
	public List<XingWorkOrderBO> queryGoingWorkOrderByCusID(String cusId);
}
