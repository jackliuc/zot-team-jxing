package com.zot.web.rs.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.collect.Lists;

@Path(value="/customer")
public class CustomerResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cars")
	public List<String> getCars() {
		List<String> cars = Lists.newArrayList();
		cars.add("苏AB265K");
		cars.add("苏AB266K");
		cars.add("苏AB267K");
		return cars;
	}
}
