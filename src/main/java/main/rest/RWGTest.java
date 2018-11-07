package main.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class RWGTest {
	@GET
	@Path("/test1")
	public String testRest() {
		return "RWGRest tested succesfully!!!";

	}
	@GET
	@Path("/test2")
	public String testRest2() {
		return "RWGRest2 tested succesfully!!!";

	}
}
