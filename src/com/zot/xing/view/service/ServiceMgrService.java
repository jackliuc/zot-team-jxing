package com.zot.xing.view.service;

import java.util.ArrayList;
import java.util.List;

import com.zot.db.JDBCTemplate;
import com.zot.xing.view.common.IdVO;

public class ServiceMgrService {
	private static final String QRY_SERVICES = "select cust_id, eval_desc from t_zot_work_order where cust_id = ?";
	
	public static List<ServiceVO> queryServices(IdVO id)
	{
		JDBCTemplate<List<ServiceVO>> jt = new JDBCTemplate<List<ServiceVO>>();
		
		List<Object> params = new ArrayList<Object>();
		params.add("101");
		
		List<ServiceVO> services = jt.query(QRY_SERVICES, params, new ServiceResultHandlerImpl());
		
		return services;
	}
}
