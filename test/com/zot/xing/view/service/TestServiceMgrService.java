package com.zot.xing.view.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import junit.framework.TestCase;

public class TestServiceMgrService extends TestCase{
	public void testQueryServices()
	{
		//List<ServiceVO> services = ServiceMgrService.queryServices(new IdVO());
		List<ServiceVO> services = new ArrayList<ServiceVO>();
		
		ServiceVO vo = new ServiceVO();
		vo.setCarNo("101");
		vo.setServiceId("good");
		services.add(vo);
		
		String jsonstr = JSON.toJSONString(services);
		
	    System.out.println(jsonstr);
	}
}