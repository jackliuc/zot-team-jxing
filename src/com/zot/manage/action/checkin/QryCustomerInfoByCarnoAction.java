/**
 * 
 */
package com.zot.manage.action.checkin;

import java.util.Map;

import com.zot.manage.service.checkin.CheckInService;
import com.zot.manage.vo.checkin.CustomerInfoVO;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

/**
 * @author jack
 *
 */
public class QryCustomerInfoByCarnoAction implements PrefixService {

	/* (non-Javadoc)
	 * @see com.zot.view.contorler.PrefixService#action(java.util.Map)
	 */
	@Override
	public Object action(Map<String, String> context) {
		String carno = context.get("carno");
		
		CustomerInfoVO customerInfoVO = CheckInService.queryCustomerInfoByCarno(carno);
		if (customerInfoVO == null)
		{
			return Constant.SUCC;
		}
		
		return customerInfoVO;
	}

}
