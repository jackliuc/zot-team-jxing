package com.zot.web.rs.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.common.collect.Lists;

@Path(value="/contacts")
public class ContactResource {
	
	@GET
	@Produces(value={"application/json"})
	public List<String> getContacts() {
//		List<String> 
		List<String> contacts = Lists.newArrayList();
		contacts.add("A contact");
		contacts.add("B contact");
		contacts.add("C contact");
		return contacts;
	}
	
	
	
	@GET
	@Path(value="/ids")
	@Produces(value={"application/json"})
	public List<String> getContactIds() {
		List<String> ids = Lists.newArrayList();
		ids.add("11111111111");
		ids.add("11111111112");
		return ids;
	}
	

}