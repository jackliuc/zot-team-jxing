package com.zot.web.rs.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="/order")
public class OrderResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> queryOrders() {
		
		// Get customer's webchat from cookie.
		List<Order> orders = new ArrayList<Order>();
		
		Order order = null;
		order = new Order();
		order.setCarNumber("苏AB265K");
		order.setBestArrivalTime("10:30");
		order.setServiceType("10001");
		order.setServiceNumber("T20160331003");
		order.setServiceDate("2016-03-31");
		orders.add(order);
		
		return orders;
	}
	
	@GET
	@Path("{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order queryOrder(@PathParam("orderId") String orderId) {
		
		// Get customer's webchat from cookie.
		
		Order order = null;
//		order = new Order();
//		order.setCarNumber("苏AB265K");
//		order.setBestArrivalTime("10:00-10:30");
//		order.setServiceType("10001");
//		order.setServiceNumber("T20160331003");
		return order;
	}
	
	@GET
	@Path("{orderId}/2")
	@Produces(MediaType.APPLICATION_JSON)
	public Order queryOrder2(@PathParam("orderId") String orderId) {
		
		// Get customer's webchat from cookie.
		
		Order order = null;
		order = new Order();
		order.setCarNumber("苏AB265K");
		order.setBestArrivalTime("10:00-10:30");
		order.setServiceType("10001");
		order.setServiceNumber("T20160331003");
		return order;
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder(
			@FormParam("carNumber") String carNumber,
			@FormParam("serviceType") String serviceType) {
		System.out.println(carNumber + "：" +serviceType );
		
		Order order = new Order();
		order.setCarNumber(carNumber);
		order.setBestArrivalTime("10:00-10:30");
		order.setServiceType(serviceType);
		return order;
	}
}
