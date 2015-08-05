package com.zot.xing.view.service;

import java.util.List;

import com.zot.xing.view.common.IdVO;

import junit.framework.TestCase;
import net.sf.json.JSONArray;

public class TestServiceMgrService extends TestCase{
	public void testQueryServices()
	{
		List<ServiceVO> services = ServiceMgrService.queryServices(new IdVO());
		
		JSONArray array = JSONArray.fromObject(services);
	    String jsonstr = array.toString();
		
	}
}
