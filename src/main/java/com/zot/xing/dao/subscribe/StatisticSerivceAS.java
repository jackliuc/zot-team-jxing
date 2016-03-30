/**
 * 服务统计接口
 */
package com.zot.xing.dao.subscribe;

/**
 * @author jack
 *
 */
public interface StatisticSerivceAS {
	
	/**
	 * 根据服务类型计算当前服务数
	 * @param serviceType
	 * @return
	 */
	public int computeCntCurrentService(String serviceType);
	
	/**
	 * 统计所有服务数
	 * @return 服务数量
	 */
	public int computeCntAllCurrentService();
	
}
