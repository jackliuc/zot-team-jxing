package com.zot.web.rs.resource;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zot.xing.dao.subscribe.ServiceBO;
import com.zot.xing.dao.subscribe.ServiceUtils;

@Path(value="/service")
public class ServiceResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ServiceBO> fetchServices() {
		return ServiceUtils.queryAllServices();
	}
	
}
