/**
 * 
 */
package com.zot.xing.dao.subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.zot.db.JDBCTemplate;
import com.zot.util.DateAS;
import com.zot.xing.view.service.WorkOrderResultHandlerImpl;


/**
 * @author jack
 *
 */
public class WorkOrderASImpl implements WorkOrderAS {

	private static final String QRY_SERVICES = "select * from t_zot_work_order where cust_id = ? order by create_time desc";
	private static final String UPDATE_SERVICES = "update t_zot_work_order set eval_desc_type = ?, eval_desc = ? where id = ?";	
	private static final String INSERT_T_ZOT_WORK_ORDER_SQL = "INSERT INTO t_zot_work_order("+
            "id, create_time, cust_id, "+ 
            "order_time, order_type) VALUES (?, ?, ?, ?, ?)";
	
	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#addPreSubscribeService(com.zot.xing.dao.subscribe.XingWorkOrderBO)
	 */
	@Override
	public void addPreSubscribeService(XingWorkOrderBO workOrder) {
		JDBCTemplate<Object> sqlTemplate = new JDBCTemplate<Object>();
		List<Object> params = new ArrayList<Object>();
		params.add(UUID.randomUUID());
		params.add(DateAS.getCurrentSQLTimestamp());
		params.add(workOrder.getCust_id());
		params.add(workOrder.getOrder_time());
		params.add(workOrder.getOrder_type());
		sqlTemplate.execute(INSERT_T_ZOT_WORK_ORDER_SQL, params);
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#updateWorkOrderToGoing()
	 */
	@Override
	public void updateWorkOrderToGoing() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#updateWorkOrderToOver()
	 */
	@Override
	public void updateWorkOrderToOver() {
		// TODO Auto-generated method stub

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
		
		jt.execute(UPDATE_SERVICES, params);
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#updateWorkOrderEvalResult(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateWorkOrderEvalResult(String id, String evalResult) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<XingWorkOrderBO> queryWorkOrderByCusID(String cusId) {
		JDBCTemplate<List<XingWorkOrderBO>> jt = new JDBCTemplate<List<XingWorkOrderBO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(cusId);
		
		List<XingWorkOrderBO> orders = jt.query(QRY_SERVICES, params, new WorkOrderResultHandlerImpl());
		
		return orders;
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#queryWorkOrderByCusID(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<XingWorkOrderBO> queryWorkOrderByCusID(String cusId, Date beginTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#queryGoingWorkOrderByCusID(java.lang.String)
	 */
	@Override
	public List<XingWorkOrderBO> queryGoingWorkOrderByCusID(String cusId) {
		// TODO Auto-generated method stub
		return null;
	}

}
