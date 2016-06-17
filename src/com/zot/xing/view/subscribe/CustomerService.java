package com.zot.xing.view.subscribe;

import java.util.Date;

import org.apache.log4j.Logger;

import com.zot.util.IdGen;
import com.zot.xing.dao.subscribe.CustomerAS;
import com.zot.xing.dao.subscribe.CustomerASImpl;
import com.zot.xing.dao.subscribe.CustomerBO;

public class CustomerService {
	private static Logger logger = Logger.getLogger(CustomerService.class);
	
	/**
	 * 根据微信号查询客户id，如果客户不存在，则自动创建后返回custId
	 * @param wechatno 微信号
	 * @param phoneno 手机号
	 * @return 客户id
	 */
	public static String queryOrCreateCustIdByWechatno(String wechatno, String phoneno)
	{
		logger.debug(wechatno+","+phoneno);

		CustomerAS custAS = new CustomerASImpl();
		CustomerBO customer = custAS.queryCustomerByWechatno(wechatno);
		if (customer == null)
		{
			logger.debug("customer is not exist, create customer by wechatno, phoneno.");
			customer = getCustomerBOByWechatno(wechatno, phoneno);
			custAS.addCustomer(customer);
		}
		
		logger.debug("return custid is:" + customer.getCustId());
		return customer.getCustId();
	}
	
	private static CustomerBO getCustomerBOByWechatno(String wechatno, String phoneno)
	{
		CustomerBO customer = new CustomerBO();
		
		customer.setCustId(IdGen.genCustId());
		customer.setWechatno(wechatno);
		customer.setPhoneno(phoneno);
		customer.setSubTime(new Date());
		
		return customer;
	}
}
