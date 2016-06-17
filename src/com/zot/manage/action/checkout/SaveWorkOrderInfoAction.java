package com.zot.manage.action.checkout;

import java.util.Map;

import com.zot.manage.service.checkout.CheckOutService;
import com.zot.manage.vo.checkout.WorkOrderInfoVO;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

public class SaveWorkOrderInfoAction implements PrefixService {

	/* (non-Javadoc)
	 * @see com.zot.view.contorler.PrefixService#action(java.util.Map)
	 */
	@Override
	public Object action(Map<String, String> context) {
		
		try
		{
			WorkOrderInfoVO workOrderInfoVO = getWorkOrderInfo(context);
			CheckOutService.saveWorkOrderInfo(workOrderInfoVO);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}
	
	private WorkOrderInfoVO getWorkOrderInfo(Map<String, String> context)
	{
		WorkOrderInfoVO workOrderInfo = new WorkOrderInfoVO();
		
		workOrderInfo.setEmployId(context.get("employId"));
		workOrderInfo.setCarno(context.get("carno"));
		workOrderInfo.setPayType(context.get("paytype"));
		workOrderInfo.setPayAmount(context.get("amount"));
		workOrderInfo.setRemark(context.get("remark"));
		workOrderInfo.setCustId(context.get("custId"));
		workOrderInfo.setOrderId(context.get("orderId"));
		workOrderInfo.setOrderType(context.get("orderType"));
		
		return workOrderInfo;
	}
}
