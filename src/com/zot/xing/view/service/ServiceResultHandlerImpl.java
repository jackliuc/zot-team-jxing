package com.zot.xing.view.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.ResultSetHandler;

public class ServiceResultHandlerImpl implements ResultSetHandler<ServiceVO> {

	public List<ServiceVO> rsHandler(ResultSet rs) throws SQLException
	{
		List<ServiceVO> services = new ArrayList<ServiceVO>();
		ServiceVO service = null;
		
		while (rs.next())
		{
			service = new ServiceVO();
			service.setCarNo(rs.getString("cust_id"));
			service.setServiceId(rs.getString("eval_desc"));
			
			services.add(service);
		}
		
		return services;
	}
}
