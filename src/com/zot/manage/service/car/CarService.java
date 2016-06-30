package com.zot.manage.service.car;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.zot.util.StringUtils;
import com.zot.xing.dao.subscribe.CarAS;
import com.zot.xing.dao.subscribe.CarASImpl;
import com.zot.xing.dao.subscribe.CarBO;
import com.zot.xing.dao.subscribe.CustomerAS;
import com.zot.xing.dao.subscribe.CustomerASImpl;
import com.zot.xing.dao.subscribe.CustomerBO;
import com.zot.xing.view.subscribe.CustomerService;

public class CarService 
{
	private static Logger log = Logger.getLogger(CarService.class);
	
	public static final int SYSTEM_ERR = 999999;
	public static final int REPEATE_ERR = 100001;
	public static final int SUCCESS = 0;
	
	private static final String DEMO_PHONENO = "00000000000";
	
	/**
	 * 查询客户已关联的车辆
	 * @param wechatno
	 * @return
	 */
	public static List<CarBO> queryCarsByWechatno(String wechatno)
	{
		//根据微信号查询到客户，然后根据客户查询到Car的列表
		CustomerAS customerAS = new CustomerASImpl();
		CustomerBO customer = customerAS.queryCustomerByWechatno(wechatno);
		List<CarBO> cars = Collections.emptyList();
		if (customer != null)
		{
			CarAS caras = new CarASImpl();
			cars = caras.queryCarByCustId(customer.getCustId());
		}
		
		return cars;
	}
	
	/**
	 * 添加关联的车辆
	 * @param wechatno
	 * @param carno
	 */
	public static int addCarNo(String wechatno, String carno)
	{
		int retCode = SUCCESS;
		
		//根据车牌号查询车辆，如果有，则根据车辆关联的客户，进行客户上微信号的设置，更新前判断微信号是否已设置。
		//如果不存在车辆，则创建车辆，同时根据微信号创建客户，并建立关联关系。
		CarAS caras = new CarASImpl();
		CarBO car = caras.queryCarByCarno(carno);
		CustomerAS customerAS = new CustomerASImpl();
		if (car != null)//存在Car信息,则进行客户的微信号关联
		{
			CustomerBO customer = customerAS.queryCustomerByCustId(car.getCustId());
			if (customer == null 
				|| !StringUtils.isEmpty(customer.getWechatno()))//数据异常或已绑定微信号
			{
				log.error("The carno is already associate with wechatno, carno:" + carno);
				retCode = REPEATE_ERR;
			}
			else//进行微信号的绑定
			{
				customer.setWechatno(wechatno);
				customerAS.updateCustomer(customer);
			}
		}
		else//创建Car和Customer，客户以微信号进行创建
		{
			//查询或创建客户
			CustomerBO customer = CustomerService.queryOrCreateCustIdByWechatno(wechatno, DEMO_PHONENO);
			
			//创建Car信息
			car = new CarBO();
			car.setCarno(carno);
			car.setCreateTime(new Date());
			car.setCustId(customer.getCustId());
			caras.addCar(car);
		}
		
		return retCode;
	}
}
