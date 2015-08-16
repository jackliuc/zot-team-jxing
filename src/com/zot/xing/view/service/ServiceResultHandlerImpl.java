package com.zot.xing.view.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.ResultSetHandler;

public class ServiceResultHandlerImpl extends ResultSetHandler<List<XingWorkOrderVO>> {

	public List<XingWorkOrderVO> rsHandler(ResultSet rs) throws SQLException
	{
		List<XingWorkOrderVO> services = new ArrayList<XingWorkOrderVO>();
		XingWorkOrderVO service = null;
		
		while (rs.next())
		{
			service = new XingWorkOrderVO();
			service.setCarNo(rs.getString("cust_id"));
			service.setServieTime(rs.getDate("service_time"));
			service.setWorkOrderId(rs.getString("id"));
			service.setWorkOrderType(rs.getInt("type"));
			service.setCustId(rs.getString("cust_id"));
			service.setEvalType(rs.getInt("eval_desc_type"));
			service.setEvalDesc(rs.getString("eval_desc"));
			service.setServicePerson(rs.getString("service_person_num"));			
			
			services.add(service);
		}
		
		return services;
	}
}
