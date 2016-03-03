package com.zot.xing.view.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zot.db.ResultSetHandler;
import com.zot.xing.dao.subscribe.XingWorkOrderBO;

public class WorkOrderResultHandlerImpl extends ResultSetHandler<List<XingWorkOrderBO>> {

	public List<XingWorkOrderBO> rsHandler(ResultSet rs) throws SQLException
	{
		List<XingWorkOrderBO> orders = new ArrayList<XingWorkOrderBO>();
		XingWorkOrderBO order = null;
		
		while (rs.next())
		{
			order = new XingWorkOrderBO();
			order.setCust_id(rs.getString("cust_id"));
			order.setCreate_time(rs.getTimestamp("create_time"));
			order.setService_time(rs.getTimestamp("service_time"));			
			order.setOver_time(rs.getTimestamp("over_time"));
			order.setRemind_time(rs.getTimestamp("remind_time"));
			order.setId(rs.getString("id"));
			order.setOrder_type(rs.getString("order_type"));
			order.setEval_desc_type(rs.getInt("eval_desc_type"));
			order.setEval_desc(rs.getString("eval_desc"));
			order.setEval_result(rs.getString("eval_result"));
			order.setService_person_num(rs.getString("service_person_num"));
			order.setService_object_id(rs.getString("service_object_id"));
			order.setService_pro_desc(rs.getString("service_pro_desc"));

			orders.add(order);
		}
		
		return orders;
	}
}
