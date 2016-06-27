package com.zot.manage.action.checkin;

import java.util.Map;

import com.zot.manage.service.checkin.CheckInService;
import com.zot.manage.vo.checkin.CustomerInfoVO;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

public class SaveCustomerInfoAction implements PrefixService {

	/* (non-Javadoc)
	 * @see com.zot.view.contorler.PrefixService#action(java.util.Map)
	 */
	@Override
	public Object action(Map<String, String> context) 
	{
		try
		{
			CustomerInfoVO customerInfoVO = getCustomerInfo(context);
			CheckInService.saveCustomerInfo(customerInfoVO);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}
	
	private CustomerInfoVO getCustomerInfo(Map<String, String> context)
	{
		CustomerInfoVO customerInfoVO = new CustomerInfoVO();
		
		customerInfoVO.setAddress(context.get("address"));
		customerInfoVO.setCarno(context.get("carno"));
		customerInfoVO.setPhoneno(context.get("phoneNum"));
		customerInfoVO.setCustname(context.get("customerName"));
		customerInfoVO.setWechatno(context.get("wechatno"));
		customerInfoVO.setSex(context.get("sex"));
		customerInfoVO.setRemark(context.get("otherCus"));
		customerInfoVO.setServiceType(context.get("serviceType"));
		customerInfoVO.setCustId(context.get("custId"));
		customerInfoVO.setOrderId(context.get("orderId"));
		
		return customerInfoVO;
	}

}
