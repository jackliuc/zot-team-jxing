package com.zot.manage.service.car;

import java.util.List;

import com.zot.xing.dao.subscribe.CarBO;

public class CarService 
{
	/**
	 * 查询客户已关联的车辆
	 * @param wechatno
	 * @return
	 */
	public static List<CarBO> queryCarsByWechatno(String wechatno)
	{
		//根据微信号查询到客户，然后根据客户查询到Car的列表
		return null;
	}
	
	/**
	 * 添加关联的车辆
	 * @param wechatno
	 * @param carno
	 */
	public static void addCarNo(String wechatno, String carno)
	{
		//根据车牌号查询车辆，如果有，则根据车辆关联的客户，进行客户上微信号的设置，更新前判断微信号是否已设置。
		//如果不存在车辆，则创建车辆，同时根据微信号创建客户，并建立关联关系。
	}
}
