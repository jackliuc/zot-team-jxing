/**
 * 
 */
package com.zot.xing.dao.subscribe;

import java.util.Date;
import java.util.List;

/**
 * @author jack
 *
 */
public class WorkOrderASImpl implements WorkOrderAS {

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#addPreSubscribeService(com.zot.xing.dao.subscribe.XingWorkOrderBO)
	 */
	@Override
	public void addPreSubscribeService(XingWorkOrderBO workOrder) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.zot.xing.dao.subscribe.WorkOrderAS#updateWorkOrderEvalResult(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateWorkOrderEvalResult(String id, String evalResult) {
		// TODO Auto-generated method stub

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
