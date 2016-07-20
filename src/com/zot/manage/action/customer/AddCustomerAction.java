package com.zot.manage.action.customer;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zot.manage.vo.checkin.CustomerInfoVO;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;
import com.zot.xing.view.subscribe.CustomerService;

public class AddCustomerAction implements PrefixService {

	private static Logger log = Logger.getLogger(AddCustomerAction.class);
	
	@Override
	public String action(Map<String, String> context) 
	{
		String data = context.get("data");
		try
		{
			CustomerInfoVO custVO = JSONObject.parseObject(data, CustomerInfoVO.class);
			CustomerService.createCustomer(custVO);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}

}

