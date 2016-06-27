/**
 * 
 */
package com.zot.xing.dao.subscribe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.db.ResultSetHandler;
import com.zot.util.DateAS;
import com.zot.util.StringUtils;
import com.zot.wechat.msg.Constant;


/**
 * @author jack
 *
 */
public class WorkOrderASImpl implements WorkOrderAS {

	private static final String QRY_WORK_ORDER_BY_CUST_ID = "select * from t_zot_work_order "
			+ "where cust_id = ? order by create_time desc";
	private static final String QRY_WORK_ORDERS_BY_STATUS = "select * from t_zot_work_order "
			+ "where status = ?";
	private static final String QRY_WORK_ORDERS_BY_CARNO = "select * from t_zot_work_order "
			+ "where carno = ?";
	private static final String QRY_WORK_ORDERS_BY_PHONENO = "select * from t_zot_work_order "
			+ "where phoneno = ?";
	private static final String QRY_WORK_ORDER_BY_ORDER_ID = "select * from t_zot_work_order where order_id = ?";
	private static final String UPDATE_WORK_ORDER_EVAL = "update t_zot_work_order set eval_desc_type = ?, "
			+ "eval_desc = ? where order_id = ?";	
	private static final String UPDATE_WORK_ORDER_PAID = "update t_zot_work_order set status = ?, paytype = ?,"
			+ " amount = ?, remark = ? where order_id = ?";
	private static final String UPDATE_WORK_ORDER_DETAIL_PAID = "update t_zot_work_order_detail set status = ?,"
			+ " over_time = ?, employ_id = ? where order_id = ? and order_type = ?";
	private static final String UPDATE_SERVICES_ARRIVED = "update t_zot_work_order set arrived = ?"
			+ " where order_id = ?";
	private static final String UPDATE_SERVICES_DETAIL = "update t_zot_work_order_detail set service_time = ?"
			+ ", employ_id = ?"
			+ " where order_id = ? and order_type = ?";
	private static final String CANCEL_WORK_ORDER = "update t_zot_work_order set status = '6' where order_id = ?";
	private static final String CANCEL_WORK_ORDER_DETAIL = "update t_zot_work_order_detail set status = '6'"
			+ " where order_id = ?";
	private static final String INSERT_T_ZOT_WORK_ORDER_SQL = "INSERT INTO t_zot_work_order("+
            "order_id, create_time, order_time, status, cust_id, carno, service_object_id, arrived, phoneno"+ 
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_WORK_ORDER_DETAIL_SQL = "INSERT INTO t_zot_work_order_detail("+
            "order_id, order_type, status, create_time"+ 
            ") VALUES (?, ?, ?, ?)";
	private static final String QRY_WORK_ORDER_DETAIL_CNT_SQL = "select count(1) cnt from t_zot_work_order_detail"
			+ " where order_type = ?";
	private static final String QRY_WORK_ORDER_DETAIL_BY_ORDERTYPE_SQL = "select * from t_zot_work_order_detail"
			+ " where order_type = ?";
	private static final String QRY_WORK_ORDER_DETAIL_BY_ORDERID_SQL = "select * from t_zot_work_order_detail"
			+ " where order_id = ?";
	
	private static final String DAILY_CNT_SQL = "select o.order_id, o.create_time, d.over_time,"
			+ " d.order_type, o.paytype,o.amount, o.order_time, o.phoneno, o.carno, o.status, o.cust_id"
			+ " from t_zot_work_order o, t_zot_work_order_detail d where o.order_id = d.order_id"
			+ " and (d.over_time > ? and d.over_time <= ?)";
	
	private static final String QRY_ENABLE_WORK_ORDER_SQL = "select o.order_id, o.create_time, d.over_time,"
			+ " d.order_type, o.paytype,o.amount, o.order_time, o.phoneno, o.carno, o.status, o.cust_id"
			+ " from t_zot_work_order o, t_zot_work_order_detail d where o.order_id = d.order_id "
			+ Constant.SQL_COND + " order by o.create_time desc limit 10";
	
	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#addPreSubscribeService(com.zot.xing.dao.subscribe.XingWorkOrderBO)
	 */
	@Override
	public void addWorkOrder(WorkOrderBO workOrder) {
		
		//订单主表
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		params.add(workOrder.getOrderId());
		params.add(DateAS.getSQLTimestamp(workOrder.getCreateTime()));
		params.add(workOrder.getOrderTime());
		params.add(workOrder.getStatus());
		params.add(workOrder.getCustId());
		params.add(workOrder.getCarno());
		params.add(workOrder.getServiceObjId());
		params.add(workOrder.getArrived());
		params.add(workOrder.getPhoneno());
		sqlTemplate.execute(INSERT_T_ZOT_WORK_ORDER_SQL, params);
		
		//订单明细表
		if (workOrder.getDetails() != null
			&& workOrder.getDetails().size() > 0)
		{
			for (WorkOrderDetailBO detail : workOrder.getDetails())
			{
				sqlTemplate = new JDBCTemplate<Object>();
				params = new ArrayList<Object>();
				params.add(detail.getOrderId());
				params.add(detail.getOrderType());
				params.add(detail.getStatus());
				params.add(DateAS.getSQLTimestamp(detail.getCreateTime()));
				sqlTemplate.execute(INSERT_WORK_ORDER_DETAIL_SQL, params);
			}
		}
	}
	
	@Override
	public void cancelWorkOrder(String orderId) {
		JDBCTemplate<Object> jt = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		
		jt.execute(CANCEL_WORK_ORDER, params);
		jt.execute(CANCEL_WORK_ORDER_DETAIL, params);
	}
	
	@Override
	public void updateWorkOrderPaid(WorkOrderBO workOrder) {
		//更新订单主表
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();	
		params.add(workOrder.getStatus());
		params.add(workOrder.getPayType());
		params.add(workOrder.getAmount());
		params.add(workOrder.getRemark());
		params.add(workOrder.getOrderId());
		sqlTemplate.execute(UPDATE_WORK_ORDER_PAID, params);
		
		//更新订单从表
		if (workOrder.getDetails() != null
			&& workOrder.getDetails().size() > 0)
		{
			for(WorkOrderDetailBO detail: workOrder.getDetails())
			{
				params = new ArrayList<Object>();	
				params.add(detail.getStatus());
				params.add(DateAS.getSQLTimestamp(detail.getFinishedTime()));
				params.add(detail.getEmployId());
				params.add(detail.getOrderId());
				params.add(detail.getOrderType());
				sqlTemplate.execute(UPDATE_WORK_ORDER_DETAIL_PAID, params);
			}
		}
	}
	
	@Override
	public void updateWorkOrderArrived(WorkOrderBO workOrder) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();	
		params.add(workOrder.getArrived());
		params.add(workOrder.getOrderId());
		sqlTemplate.execute(UPDATE_SERVICES_ARRIVED, params);
	}
	
	@Override
	public void updateWorkOrderDetail(WorkOrderDetailBO workOrderDetail) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();	
		params.add(DateAS.getSQLTimestamp(workOrderDetail.getServiceTime()));
		params.add(workOrderDetail.getEmployId());
		params.add(workOrderDetail.getOrderId());
		params.add(workOrderDetail.getOrderType());
		sqlTemplate.execute(UPDATE_SERVICES_DETAIL, params);
	}


	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#updateWorkOrderEval(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateWorkOrderEval(String id, String evalType, String evalDesc) {
		JDBCTemplate<Object> jt = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.valueOf(evalType));
		params.add(evalDesc);
		params.add(id);
		
		jt.execute(UPDATE_WORK_ORDER_EVAL, params);
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#updateWorkOrderEvalResult(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateWorkOrderEvalResult(String id, String evalResult) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<WorkOrderBO> queryWorkOrderByCusId(String cusId) {
		JDBCTemplate<List<WorkOrderBO>> jt = new JDBCTemplate<List<WorkOrderBO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(cusId);
		
		List<WorkOrderBO> orders = jt.query(QRY_WORK_ORDER_BY_CUST_ID, params, new ResultSetHandler<List<WorkOrderBO>>()
				{
					@Override
					public List<WorkOrderBO> rsHandler(ResultSet rs) throws SQLException {
						List<WorkOrderBO> orders = new ArrayList<WorkOrderBO>();
						WorkOrderBO order = null;
						
						while (rs.next())
						{
							order = cvt2WorkOrder(rs);

							orders.add(order);
						}
						
						return orders;
					}
				});
		
		return orders;
	}
	
	public WorkOrderBO queryWorkOrderByOrderId(String orderId)
	{
		JDBCTemplate<WorkOrderBO> jt = new JDBCTemplate<WorkOrderBO>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		
		WorkOrderBO workOrder = jt.query(QRY_WORK_ORDER_BY_ORDER_ID, params, new ResultSetHandler<WorkOrderBO>()
				{
					@Override
					public WorkOrderBO rsHandler(ResultSet rs) throws SQLException {
						WorkOrderBO order = null;
						
						if (rs.next())
						{
							order = cvt2WorkOrder(rs);
						}
						
						return order;
					}
				});
		
		return workOrder;
	}
	
	public List<WorkOrderDetailBO> queryWorkOrderDetailsByType(String orderType) {
		JDBCTemplate<List<WorkOrderDetailBO>> jt = new JDBCTemplate<List<WorkOrderDetailBO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(orderType);
		
		List<WorkOrderDetailBO> orders = jt.query(QRY_WORK_ORDER_DETAIL_BY_ORDERTYPE_SQL, params, new ResultSetHandler<List<WorkOrderDetailBO>>()
				{
					@Override
					public List<WorkOrderDetailBO> rsHandler(ResultSet rs) throws SQLException {
						List<WorkOrderDetailBO> orderDetails = new ArrayList<WorkOrderDetailBO>();
						WorkOrderDetailBO orderDetail = null;
						
						while (rs.next())
						{
							orderDetail = cvt2WorkOrderDetail(rs);

							orderDetails.add(orderDetail);
						}
						
						return orderDetails;
					}
					
				});
		
		return orders;
	}
	
	@Override
	public List<WorkOrderBO> queryOrdersByStatus(String status) {
		JDBCTemplate<List<WorkOrderBO>> jt = new JDBCTemplate<List<WorkOrderBO>>();
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		
		List<WorkOrderBO> orders = jt.query(QRY_WORK_ORDERS_BY_STATUS, params, 
				new ResultSetHandler<List<WorkOrderBO>>()
				{
					@Override
					public List<WorkOrderBO> rsHandler(ResultSet rs) throws SQLException {
						List<WorkOrderBO> orders = new ArrayList<WorkOrderBO>();
						WorkOrderBO order = null;
						
						while (rs.next())
						{
							order = cvt2WorkOrder(rs);
		
							orders.add(order);
						}
						
						return orders;
					}
				});
		
		return orders;
	}
	
	
	@Override
	public List<WorkOrderBO> queryOrdersByPhoneno(String phoneno) {
		JDBCTemplate<List<WorkOrderBO>> jt = new JDBCTemplate<List<WorkOrderBO>>();
		List<Object> params = new ArrayList<Object>();
		params.add(phoneno);

		List<WorkOrderBO> orders = jt.query(QRY_WORK_ORDERS_BY_PHONENO, params, 
				new ResultSetHandler<List<WorkOrderBO>>()
				{
					@Override
					public List<WorkOrderBO> rsHandler(ResultSet rs) throws SQLException {
						List<WorkOrderBO> orders = new ArrayList<WorkOrderBO>();
						WorkOrderBO order = null;
						
						while (rs.next())
						{
							order = cvt2WorkOrder(rs);
		
							orders.add(order);
						}
						
						return orders;
					}
				});
		
		return orders;
	}
	
	@Override
	public List<WorkOrderBO> queryOrdersByCarno(String carno) {
		JDBCTemplate<List<WorkOrderBO>> jt = new JDBCTemplate<List<WorkOrderBO>>();
		List<Object> params = new ArrayList<Object>();
		params.add(carno);

		List<WorkOrderBO> orders = jt.query(QRY_WORK_ORDERS_BY_CARNO, params, 
				new ResultSetHandler<List<WorkOrderBO>>()
				{
					@Override
					public List<WorkOrderBO> rsHandler(ResultSet rs) throws SQLException {
						List<WorkOrderBO> orders = new ArrayList<WorkOrderBO>();
						WorkOrderBO order = null;
						
						while (rs.next())
						{
							order = cvt2WorkOrder(rs);
		
							orders.add(order);
						}
						
						return orders;
					}
				});
		
		return orders;
	}

	public List<WorkOrderDetailBO> queryWorkOrderDetailsByOrderId(String orderId) {
		JDBCTemplate<List<WorkOrderDetailBO>> jt = new JDBCTemplate<List<WorkOrderDetailBO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		
		List<WorkOrderDetailBO> orderDetails = jt.query(QRY_WORK_ORDER_DETAIL_BY_ORDERID_SQL, params, 
				new ResultSetHandler<List<WorkOrderDetailBO>>()
				{
					@Override
					public List<WorkOrderDetailBO> rsHandler(ResultSet rs) throws SQLException {
						List<WorkOrderDetailBO> orderDetails = new ArrayList<WorkOrderDetailBO>();
						WorkOrderDetailBO orderDetail = null;
						
						while (rs.next())
						{
							orderDetail = cvt2WorkOrderDetail(rs);

							orderDetails.add(orderDetail);
						}
						
						return orderDetails;
					}
					
				});
		
		return orderDetails;
	}
	
	@Override
	public int queryWorkOrdersNumByType(String orderType) {
		JDBCTemplate<Integer> jt = new JDBCTemplate<Integer>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(orderType);
		
		Integer cnt = jt.query(QRY_WORK_ORDER_DETAIL_CNT_SQL, params, new ResultSetHandler<Integer>()
				{
					@Override
					public Integer rsHandler(ResultSet rs) throws SQLException {
						Integer cnt = Integer.valueOf(0);
						
						if (rs.next())
						{
							cnt = Integer.valueOf(rs.getInt("cnt"));			
						}
						
						return cnt;
					}
				});
		
		return cnt.intValue();
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#queryWorkOrderByCusID(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WorkOrderBO> queryWorkOrderByCusId(String cusId, Date beginTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List<EnableWorkOrderBO> queryOrdersByDate(String beginDate, String endDate) {

		JDBCTemplate<List<EnableWorkOrderBO>> jt = new JDBCTemplate<List<EnableWorkOrderBO>>();
		List<Object> params = new ArrayList<Object>();
		params.add(beginDate);
		params.add(endDate);

		List<EnableWorkOrderBO> orders = jt.query(DAILY_CNT_SQL, params, 
				new ResultSetHandler<List<EnableWorkOrderBO>>()
				{
					@Override
					public List<EnableWorkOrderBO> rsHandler(ResultSet rs) throws SQLException {
						List<EnableWorkOrderBO> orders = new ArrayList<EnableWorkOrderBO>();
						EnableWorkOrderBO order = null;
						
						while (rs.next())
						{
							order = cvt2EnableWorkOrder(rs);
		
							orders.add(order);
						}
						
						return orders;
					}
				});
		
		return orders;
	}
	
	@Override
	public List<EnableWorkOrderBO> queryOrdersByCondition(WorkOrderBO workOrderCond) 
	{
		JDBCTemplate<List<EnableWorkOrderBO>> jt = new JDBCTemplate<List<EnableWorkOrderBO>>();
		
		//构造查询的where条件和参数
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer();
		if (!StringUtils.isEmpty(workOrderCond.getStatus()))
		{
			buf.append(" and o.status = ?");
			params.add(workOrderCond.getStatus());
		}
		if (!StringUtils.isEmpty(workOrderCond.getPhoneno()))
		{
			buf.append(" and o.phoneno = ?");
			params.add(workOrderCond.getPhoneno());
		}
		if (!StringUtils.isEmpty(workOrderCond.getCarno()))
		{
			buf.append(" and o.carno = ?");
			params.add(workOrderCond.getCarno());
		}
		
		String qrySQL = QRY_ENABLE_WORK_ORDER_SQL.replaceAll(Constant.SQL_COND, buf.toString());

		List<EnableWorkOrderBO> orders = jt.query(qrySQL, params, 
				new ResultSetHandler<List<EnableWorkOrderBO>>()
				{
					@Override
					public List<EnableWorkOrderBO> rsHandler(ResultSet rs) throws SQLException {
						List<EnableWorkOrderBO> orders = new ArrayList<EnableWorkOrderBO>();
						EnableWorkOrderBO order = null;
						
						while (rs.next())
						{
							order = cvt2EnableWorkOrder(rs);
		
							orders.add(order);
						}
						
						return orders;
					}
				});
		
		return orders;
	}

	private EnableWorkOrderBO cvt2EnableWorkOrder(ResultSet rs) throws SQLException
	{
		EnableWorkOrderBO enableWorkOrder = new EnableWorkOrderBO();
		
		enableWorkOrder.setOrderId((rs.getString("order_id")));
		enableWorkOrder.setCreateTime(rs.getTimestamp("create_time"));
		enableWorkOrder.setFinishedTime(rs.getTimestamp("over_time"));	
		enableWorkOrder.setOrderType(rs.getString("order_type"));
		enableWorkOrder.setPayType(rs.getString("paytype"));
		enableWorkOrder.setAmount(rs.getFloat("amount"));
		
		enableWorkOrder.setOrderTime(rs.getTimestamp("order_time"));	
		enableWorkOrder.setPhoneno(rs.getString("phoneno"));
		enableWorkOrder.setCarno(rs.getString("carno"));
		enableWorkOrder.setStatus(rs.getString("status"));
		enableWorkOrder.setCustId(rs.getString("cust_id"));
		
		return enableWorkOrder;
	}

	private WorkOrderBO cvt2WorkOrder(ResultSet rs) throws SQLException
	{
		WorkOrderBO workOrder = new WorkOrderBO();
		
		workOrder.setCustId(rs.getString("cust_id"));
		workOrder.setCreateTime(rs.getTimestamp("create_time"));
		workOrder.setOrderTime(rs.getTimestamp("order_time"));	
		workOrder.setRemindTime(rs.getTimestamp("remind_time"));
		workOrder.setOrderId(rs.getString("order_id"));
		workOrder.setStatus(rs.getString("status"));
		workOrder.setEvalDescType(rs.getInt("eval_desc_type"));
		workOrder.setEvalDesc(rs.getString("eval_desc"));
		workOrder.setEvalResult(rs.getString("eval_result"));
		workOrder.setServiceObjId(rs.getString("service_object_id"));
		workOrder.setCarno(rs.getString("carno"));
		workOrder.setPayType(rs.getString("paytype"));
		workOrder.setAmount(rs.getFloat("amount"));
		workOrder.setArrived(rs.getString("arrived"));
		workOrder.setPhoneno(rs.getString("phoneno"));
		
		return workOrder;
	}
	
	private WorkOrderDetailBO cvt2WorkOrderDetail(ResultSet rs) throws SQLException
	{
		WorkOrderDetailBO workOrderDetail = new WorkOrderDetailBO();
		
		workOrderDetail.setOrderId(rs.getString("order_id"));
		workOrderDetail.setOrderType(rs.getString("order_type"));		
		workOrderDetail.setCreateTime(rs.getTimestamp("create_time"));
		workOrderDetail.setServiceTime(rs.getTimestamp("service_time"));
		workOrderDetail.setFinishedTime(rs.getTimestamp("over_time"));
		workOrderDetail.setOderPorDesc(rs.getString("order_pro_desc"));
		workOrderDetail.setEmployId(rs.getString("employ_id"));
		workOrderDetail.setRemark(rs.getString("remark"));
		workOrderDetail.setStatus(rs.getString("status"));
		
		return workOrderDetail;
	}
}
