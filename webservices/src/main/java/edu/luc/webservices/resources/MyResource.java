package edu.luc.webservices.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.luc.webservices.model.Customer;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    /*@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }*/
	
	@GET
	@Path("/{login}")
	@Produces({ "application/json" })
    public Customer getIt(@PathParam("login") String name) {
		Customer c = new Customer(name, "abc", 123, "city", "brasil", 456);
		return c;
    }
}
