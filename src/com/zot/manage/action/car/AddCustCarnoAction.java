package com.zot.manage.action.car;

import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.manage.service.car.CarService;
import com.zot.util.StringUtils;
import com.zot.view.contorler.PrefixService;
import com.zot.wechat.msg.Constant;

public class AddCustCarnoAction implements PrefixService {

	private static Logger log = Logger.getLogger(AddCustCarnoAction.class);
	
	@Override
	public String action(Map<String, String> context) {
		String wechatno = context.get("wechatno");
		String carno = context.get("carno");
		if(!StringUtils.isEmpty(carno))
		{
			carno = carno.toUpperCase(Locale.US);
		}
		
		try
		{
			int retCode = CarService.addCarNo(wechatno, carno);
			if (retCode != CarService.SUCCESS)
			{
				return Constant.FAIL;
			}
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			return Constant.FAIL;
		}
		
		return Constant.SUCC;
	}

}
