package com.zot.xing.dao.subscribe;

public interface CustomerAS {
	/**
	 * 创建客户
	 * @param customer
	 */
	void addCustomer(CustomerBO customer);
	
	/**
	 * 更新客户
	 * @param customer
	 */
	void updateCustomer(CustomerBO customer);
	
	/**
	 * 根据微信号查询客户
	 * @param wechatno 微信号
	 * @return
	 */
	CustomerBO queryCustomerByWechatno(String wechatno);
	
	/**
	 * 根据客户Id查询客户
	 * @param custId 客户号
	 * @return
	 */
	CustomerBO queryCustomerByCustId(String custId);
	
	/**
	 * 根据手机或固定电话查询客户
	 * @param phoneno 手机或固定电话号码
	 * @return
	 */
	CustomerBO queryCustomerByPhoneno(String phoneno);
}
