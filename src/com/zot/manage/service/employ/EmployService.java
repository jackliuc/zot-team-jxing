package com.zot.manage.service.employ;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zot.xing.dao.employ.EmployAS;
import com.zot.xing.dao.employ.EmployASImpl;
import com.zot.xing.dao.employ.EmployBO;

public class EmployService {
	private static Map<String, EmployBO> allEmploysMap = new LinkedHashMap<String, EmployBO>();
	
	static
	{
		EmployAS employAS = new EmployASImpl();
		List<EmployBO> allEmploys = employAS.queryAllEmploys();
		if (allEmploys != null)
		{
			for (EmployBO employ : allEmploys)
			{
				allEmploysMap.put(employ.getEmployId(), employ);
			}
		}
	}
	
	public static List<EmployBO> getEmploys()
	{
		return new ArrayList<EmployBO>(allEmploysMap.values());
	}
	
	public static EmployBO getEmploy(String employId)
	{
		return allEmploysMap.get(employId);
	}
}
