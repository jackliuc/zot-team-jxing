package com.zot.xing.view.service;

import java.util.List;

import com.zot.view.contorler.PrefixService;
import com.zot.xing.view.common.IdVO;

import net.sf.json.JSONArray;

public class ServiceQueryAction extends PrefixService{
	@Override
	
	public String action() {
		//String subtype = (String)getValue("subtype");
		
		IdVO id = new IdVO();
		id.setIdType(1);
		id.setId("101");
		
		List<ServiceVO> services = ServiceMgrService.queryServices(id);
		
		JSONArray array = JSONArray.fromObject(services);
	    String jsonstr = array.toString();
		
		return jsonstr;
	}
}
