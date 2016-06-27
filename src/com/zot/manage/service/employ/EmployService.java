package com.zot.manage.service.employ;

import java.util.List;

import com.zot.xing.dao.employ.EmployAS;
import com.zot.xing.dao.employ.EmployASImpl;
import com.zot.xing.dao.employ.EmployBO;

public class EmployService {
	private static List<EmployBO> allEmploys = null;
	
	public static List<EmployBO> getEmploys()
	{
		if (allEmploys == null)
		{
			synchronized (EmployService.class) {
				if (allEmploys == null)
				{
					EmployAS employAS = new EmployASImpl();
					allEmploys = employAS.queryAllEmploys();
				}
			}
		}
		
		return allEmploys;
	}
}
