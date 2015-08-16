package com.zot.xing.view.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zot.view.contorler.PrefixService;
import com.zot.xing.view.common.IdVO;

public class ServiceQueryAction extends PrefixService{
	@Override
	
	public String action() {
		//String subtype = (String)getValue("subtype");
		
		IdVO id = new IdVO();
		id.setIdType(1);
		id.setId("101");
		
		List<XingWorkOrderVO> services = ServiceMgrService.queryServices(id);
		
		String jsonstr = JSON.toJSONString(services);
		
		return jsonstr;
	}
}
