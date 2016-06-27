package com.zot.manage.action.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zot.manage.service.employ.EmployService;
import com.zot.view.contorler.PrefixService;
import com.zot.xing.dao.employ.EmployBO;

public class QryEmployList implements PrefixService {
	
	private static Logger logger = Logger.getLogger(QryEmployList.class);
	
	public List<EmployBO> action(Map<String,String> context) 
	{
		List<EmployBO> employs = null;
		try
		{
			employs = EmployService.getEmploys();	
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
			employs = Collections.emptyList();
		}
		
		logger.info("return employ size:" + employs.size());
		return employs;
	}
}

