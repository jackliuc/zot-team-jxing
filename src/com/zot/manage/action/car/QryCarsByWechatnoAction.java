package com.zot.manage.action.car;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.manage.service.car.CarService;
import com.zot.view.contorler.PrefixService;
import com.zot.xing.dao.subscribe.CarBO;

public class QryCarsByWechatnoAction implements PrefixService {

	private static Logger log = Logger.getLogger(QryCarsByWechatnoAction.class);
	
	@Override
	public List<CarBO> action(Map<String, String> context) {
		String wechatno = context.get("wechatno");
		
		List<CarBO> cars = Collections.emptyList();
		try
		{
			cars = CarService.queryCarsByWechatno(wechatno);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			cars = Collections.emptyList();
		}
		
		return cars;
	}

}
