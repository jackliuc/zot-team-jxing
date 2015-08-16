package com.zot.xing.view.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zot.view.contorler.PrefixService;
import com.zot.xing.view.common.IdVO;

public class ServiceQueryAction implements PrefixService{

	public List<ServiceVO> action(Map<String,String> context) {
		//String subtype = (String)getValue("subtype");
		
		IdVO id = new IdVO();
		id.setIdType(1);
		id.setId("101");
		
		List<ServiceVO> services = ServiceMgrService.queryServices(id);
	
    	return services;
	}
}
