package com.zot.xing.view.service;

import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.xing.view.common.IdVO;

public class ServiceMgrService {
	private static final String QRY_SERVICES = "select * from t_zot_work_order where cust_id = ?";
	private static final String UPDATE_SERVICES = "update t_zot_work_order set eval_desc_type = ?, eval_desc = ? where id = ?";
	
	public static List<XingWorkOrderVO> queryServices(IdVO id)
	{
		JDBCTemplate<List<XingWorkOrderVO>> jt = new JDBCTemplate<List<XingWorkOrderVO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(id.getId());
		
		List<XingWorkOrderVO> services = jt.query(QRY_SERVICES, params, new ServiceResultHandlerImpl());
		
		return services;
	}
	
	public static boolean complainService(IdVO id, XingWorkOrderVO order)
	{
		JDBCTemplate<Object> jt = new JDBCTemplate<Object>();
		
		List<Object> params = new ArrayList<Object>();
		params.add(Integer.valueOf(order.getEvalType()));
		params.add(order.getEvalDesc());
		params.add(order.getWorkOrderId());
		
		jt.execute(UPDATE_SERVICES, params);
		
		return true;
	}
}
