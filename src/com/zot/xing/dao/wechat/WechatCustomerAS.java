/**
 * 微信用户订阅更新类
 */
package com.zot.xing.dao.wechat;

/**
 * @author jack
 *
 */
public interface WechatCustomerAS {

	/**
	 * 增加微信订阅用户,对于相同用户重复插入
	 * @param customer 订阅用户信息
	 */
	public void addWechatCustomer(WechatCustomer customer);
	
	/**
	 * 更新微信取消订阅用户信息
	 * @param wechatNo
	 */
	public void updateCalSubCustomer(String wechatNo);
}
