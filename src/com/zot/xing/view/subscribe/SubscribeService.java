package com.zot.xing.view.subscribe;

import java.util.List;

import org.apache.log4j.Logger;

import com.zot.xing.dao.subscribe.CarAS;
import com.zot.xing.dao.subscribe.CarASImpl;
import com.zot.xing.dao.subscribe.CarBO;
import com.zot.xing.dao.subscribe.CustomerAS;
import com.zot.xing.dao.subscribe.CustomerASImpl;
import com.zot.xing.dao.subscribe.CustomerBO;

public class SubscribeService {
	private static Logger log = Logger.getLogger(SubscribeService.class);
	
	/**
	 * 根据微信号获取车牌号，逻辑如下：
	 * 如果根据微信号查询客户不存在，则车牌号直接返回空；
	 * 如果查询到客户，客户下无车辆信息，则车牌号返回空；
	 * 如果查询到客户，客户下仅一辆车，则返回对应的车牌号；
	 * 如果查询到客户，且客户有多辆车，则返回最近登记的车辆车牌号。
	 * 
	 * 对于手机号码直接从客户信息中获取，如不存在客户，则返回空。
	 * @param wechatno
	 * @return
	 */
	public static SubscribeVO getSubscribeInfoByWechatno(String wechatno)
	{
		CustomerAS custAS = new CustomerASImpl();
		CustomerBO customer = custAS.queryCustomerByWechatno(wechatno);
		
		if (customer == null)
		{
			log.debug("can not find customer by wechatno, wechatno:" + wechatno);
			return null;
		}
		
		log.debug("customer id is:" + customer.getCustId());
		CarAS caras = new CarASImpl();
		List<CarBO> cars = caras.queryCarByCustId(customer.getCustId());
		
		String carno = null;
		if (cars != null && !cars.isEmpty())
		{
			carno = cars.get(0).getCarno();
			log.debug("query car by customer id, carno is:" + carno);
		}
		
		log.debug("get carno is:" + carno);
		
		SubscribeVO subVO = new SubscribeVO();
		subVO.setCarno(carno);
		subVO.setPhoneno(customer.getPhoneno());
		
		return subVO;
	}
}
