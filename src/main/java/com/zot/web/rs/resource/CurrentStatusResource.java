package com.zot.web.rs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="/current-status")
public class CurrentStatusResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CurrentStatus currentStatus() {
		CurrentStatus status = null;
		status = new CurrentStatus();
		status.setAverageServiceTime(90);
		status.setFinishedCars(100);
		status.setInQueueCars(6);
		return status;
	}
}
